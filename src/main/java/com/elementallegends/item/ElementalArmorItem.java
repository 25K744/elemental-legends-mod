package com.elementallegends.item;

import com.elementallegends.core.ElementType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ElementalArmorItem extends ArmorItem {
    public enum ArmorSlot {
        HELMET,
        CHESTPLATE,
        LEGGINGS,
        BOOTS
    }

    private final ElementType element;

    public ElementalArmorItem(ArmorSlot slot, ElementType element, Item.Properties properties) {
        super(new ElementalArmorMaterial(element), mapSlot(slot), properties.rarity(Rarity.UNCOMMON));
        this.element = element;
    }

    private static Type mapSlot(ArmorSlot slot) {
        return switch (slot) {
            case HELMET -> Type.HELMET;
            case CHESTPLATE -> Type.CHESTPLATE;
            case LEGGINGS -> Type.LEGGINGS;
            case BOOTS -> Type.BOOTS;
        };
    }

    public ElementType getElement() {
        return element;
    }

    private static class ElementalArmorMaterial implements ArmorMaterial {
        private final ElementType element;

        private ElementalArmorMaterial(ElementType element) {
            this.element = element;
        }

        @Override
        public int getDurabilityForType(Type type) {
            return switch (type) {
                case HELMET -> 220;
                case CHESTPLATE -> 260;
                case LEGGINGS -> 240;
                case BOOTS -> 210;
            };
        }

        @Override
        public int getDefenseForType(Type type) {
            return switch (type) {
                case HELMET -> 2;
                case CHESTPLATE -> 5;
                case LEGGINGS -> 4;
                case BOOTS -> 2;
            };
        }

        @Override
        public int getEnchantmentValue() {
            return 14;
        }

        @Override
        public net.minecraft.sounds.SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_DIAMOND;
        }

        @Override
        public net.minecraft.world.item.Item getRepairIngredient() {
            return net.minecraft.world.item.Items.IRON_INGOT;
        }

        @Override
        public String getName() {
            return "elementallegends:" + element.getId();
        }

        @Override
        public float getToughness() {
            return 1.5F;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.0F;
        }
    }
}
