package com.elementallegends.capability;

import com.elementallegends.core.ElementType;
import com.elementallegends.core.SkillNode;
import com.elementallegends.core.SkillTree;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import com.elementallegends.ElementalLegends;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElementalData implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<ElementalData> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});

    private int level = 1;
    private int skillPoints = 2;
    private int mana = 100;
    private int maxMana = 100;
    private int mastery = 0;
    private final EnumMap<ElementType, Integer> elementalMastery = new EnumMap<>(ElementType.class);
    private final Set<ElementType> unlockedElements = new HashSet<>();
    private final Set<String> purchasedSkills = new HashSet<>();

    public ElementalData() {
        for (ElementType type : ElementType.values()) {
            elementalMastery.put(type, 0);
        }
        unlockedElements.add(ElementType.FIRE);
        unlockedElements.add(ElementType.WATER);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = Math.max(0, skillPoints);
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = Math.max(20, maxMana);
        this.mana = Math.min(this.mana, this.maxMana);
    }

    public void addMana(int amount) {
        this.mana = Math.max(0, Math.min(maxMana, this.mana + amount));
    }

    public boolean consumeMana(int amount) {
        if (mana < amount) {
            return false;
        }
        mana -= amount;
        return true;
    }

    public void tickManaRegen() {
        addMana(2 + level / 10);
    }

    public int getMastery() {
        return mastery;
    }

    public void setMastery(int mastery) {
        this.mastery = Math.max(0, mastery);
    }

    public Set<ElementType> getUnlockedElements() {
        return unlockedElements;
    }

    public boolean isElementUnlocked(ElementType element) {
        return unlockedElements.contains(element);
    }

    public void unlockElement(ElementType element) {
        unlockedElements.add(element);
    }

    public void gainMastery(ElementType element, int amount) {
        elementalMastery.put(element, elementalMastery.getOrDefault(element, 0) + amount);
    }

    public int getElementMastery(ElementType element) {
        return elementalMastery.getOrDefault(element, 0);
    }

    public boolean hasPurchased(String skillId) {
        return purchasedSkills.contains(skillId);
    }

    public boolean canUnlockSkill(SkillNode node) {
        if (node == null) {
            return false;
        }
        if (purchasedSkills.contains(node.id())) {
            return false;
        }
        if (skillPoints < node.cost()) {
            return false;
        }
        if (level < node.requiredLevel()) {
            return false;
        }
        if (node.parentId() != null && !purchasedSkills.contains(node.parentId())) {
            return false;
        }
        return true;
    }

    public boolean unlockSkill(SkillNode node) {
        if (!canUnlockSkill(node)) {
            return false;
        }

        skillPoints -= node.cost();
        purchasedSkills.add(node.id());
        unlockElement(node.element());
        gainMastery(node.element(), 1);
        return true;
    }

    public static ElementalData get(Player player) {
        return player.getCapability(ElementalData.CAPABILITY).orElseThrow(() -> new IllegalStateException("Elemental data missing for player " + player.getName().getString()));
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("level", level);
        tag.putInt("skill_points", skillPoints);
        tag.putInt("mana", mana);
        tag.putInt("max_mana", maxMana);
        tag.putInt("mastery", mastery);
        tag.putIntArray("element_mastery", elementalMastery.values().stream().mapToInt(Integer::intValue).toArray());

        CompoundTag unlockedTag = new CompoundTag();
        for (ElementType element : unlockedElements) {
            unlockedTag.putBoolean(element.name(), true);
        }
        tag.put("unlocked_elements", unlockedTag);

        ListTag skillList = new ListTag();
        for (String skillId : purchasedSkills) {
            skillList.add(StringTag.valueOf(skillId));
        }
        tag.put("purchased_skills", skillList);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        level = tag.getInt("level");
        skillPoints = tag.getInt("skill_points");
        mana = tag.getInt("mana");
        maxMana = tag.getInt("max_mana");
        mastery = tag.getInt("mastery");

        CompoundTag unlockedTag = tag.getCompound("unlocked_elements");
        unlockedElements.clear();
        for (ElementType element : ElementType.values()) {
            if (unlockedTag.getBoolean(element.name())) {
                unlockedElements.add(element);
            }
        }

        purchasedSkills.clear();
        for (int i = 0; i < tag.getList("purchased_skills", 8).size(); i++) {
            purchasedSkills.add(tag.getList("purchased_skills", 8).getString(i));
        }
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
        return capability == CAPABILITY ? LazyOptional.of(() -> (T) this).cast() : LazyOptional.empty();
    }

    public static class Provider implements ICapabilitySerializable<CompoundTag> {
        private final ElementalData instance = new ElementalData();

        @Override
        public CompoundTag serializeNBT() {
            return instance.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            instance.deserializeNBT(nbt);
        }

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
            return capability == CAPABILITY ? LazyOptional.of(() -> (T) instance).cast() : LazyOptional.empty();
        }
    }

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(ElementalData.class);
    }

    public static void attachEntity(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(ElementalLegends.MOD_ID, "elemental_data"), new Provider());
        }
    }

    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            ElementalData original = get(event.getOriginal());
            ElementalData clone = get(event.getEntity());
            clone.deserializeNBT(original.serializeNBT());
        }
    }

    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            ElementalData data = get(player);
            if (data.getUnlockedElements().isEmpty()) {
                data.unlockElement(ElementType.FIRE);
                data.unlockElement(ElementType.WATER);
            }
        }
    }
}

