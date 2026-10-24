package com.elementallegends.item;

import com.elementallegends.capability.ElementalData;
import com.elementallegends.core.ElementType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StaffItem extends Item {
    private final ElementType element;

    public StaffItem(ElementType element, Properties properties) {
        super(properties);
        this.element = element;
    }

    public ElementType getElement() {
        return element;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            ElementalData data = ElementalData.get(player);
            int manaCost = 8;
            if (!data.isElementUnlocked(element)) {
                player.displayClientMessage(net.minecraft.network.chat.Component.literal("This element is locked."), true);
                return InteractionResultHolder.fail(stack);
            }

            if (!data.consumeMana(manaCost)) {
                player.displayClientMessage(net.minecraft.network.chat.Component.literal("Not enough mana."), true);
                return InteractionResultHolder.fail(stack);
            }

            if (player instanceof ServerPlayer serverPlayer) {
                serverPlayer.getLevel().sendParticles(
                        ParticleTypes.FLAME,
                        player.getX(),
                        player.getY() + 1.0D,
                        player.getZ(),
                        18,
                        0.5D,
                        0.5D,
                        0.5D,
                        0.05D
                );
            }

            player.playSound(SoundEvents.FIRECHARGE_USE, 1.0F, 1.0F);

            if (element == ElementType.WATER) {
                player.playSound(SoundEvents.WATER_AMBIENT, 0.6F, 1.0F);
            }
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}
