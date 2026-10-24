package com.elementallegends.capability;

import com.elementallegends.ElementalLegends;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public final class ModCapabilities {
    public static void register(RegisterCapabilitiesEvent event) {
        ElementalData.register(event);
    }

    public static void attachEntity(AttachCapabilitiesEvent<Entity> event) {
        ElementalData.attachEntity(event);
    }

    public static void onPlayerClone(PlayerEvent.Clone event) {
        ElementalData.onPlayerClone(event);
    }

    public static void onPlayerJoin(EntityJoinLevelEvent event) {
        ElementalData.onPlayerJoin(event);
    }

    public static ElementalData get(Player player) {
        return ElementalData.get(player);
    }
}
