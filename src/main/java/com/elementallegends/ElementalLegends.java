package com.elementallegends;

import com.elementallegends.capability.ModCapabilities;
import com.elementallegends.event.ModEvents;
import com.elementallegends.item.ModItems;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ElementalLegends.MOD_ID)
public class ElementalLegends {
    public static final String MOD_ID = "elementallegends";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final KeyMapping SKILL_TREE_KEY = new KeyMapping(
            "key.elementallegends.skill_tree",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_K,
            "key.category.elementallegends"
    );

    public ElementalLegends() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(modBus);
        modBus.addListener(ModCapabilities::register);
        modBus.addListener(this::onClientSetup);

        MinecraftForge.EVENT_BUS.register(new ModEvents());
        LOGGER.info("Elemental Legends initialized.");
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        LOGGER.info("Client setup completed.");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientEvents {
        @SubscribeEvent
        public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
            event.register(SKILL_TREE_KEY);
        }
    }
}
