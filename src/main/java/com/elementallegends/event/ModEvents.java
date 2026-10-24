package com.elementallegends.event;

import com.elementallegends.capability.ElementalData;
import com.elementallegends.capability.ModCapabilities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "elementallegends")
public class ModEvents {
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        ElementalData.register(event);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new net.minecraft.resources.ResourceLocation("elementallegends", "elemental_data"), new ElementalData.Provider());
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        ElementalData.onPlayerClone(event);
    }

    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Player player) {
            ElementalData data = ElementalData.get(player);
            if (data.getUnlockedElements().isEmpty()) {
                data.unlockElement(com.elementallegends.core.ElementType.FIRE);
            }
            data.tickManaRegen();
        }
    }
}
