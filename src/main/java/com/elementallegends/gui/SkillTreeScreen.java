package com.elementallegends.gui;

import com.elementallegends.core.ElementType;
import com.elementallegends.core.SkillNode;
import com.elementallegends.core.SkillTree;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SkillTreeScreen extends Screen {
    private final Player player;

    public SkillTreeScreen(Player player) {
        super(Component.translatable("gui.elementallegends.skilltree.title"));
        this.player = player;
    }

    @Override
    protected void init() {
        super.init();

        int startX = width / 2 - 180;
        int startY = height / 2 - 120;

        int index = 0;
        for (ElementType element : ElementType.values()) {
            List<SkillNode> skills = SkillTree.forElement(element);
            if (skills.isEmpty()) {
                continue;
            }

            int x = startX + (index % 3) * 130;
            int y = startY + (index / 3) * 80;
            addRenderableWidget(Button.builder(
                    Component.literal(element.getDisplayName()),
                    button -> {
                        // Visual skill tree menu only. Unlock logic is validated server-side through the capability layer.
                    }
            ).bounds(x, y, 120, 20).build());
            index++;
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(guiGraphics);
        guiGraphics.drawCenteredString(this.font, Component.translatable("gui.elementallegends.skilltree.title"), width / 2, 20, 0xD4D0FF);

        int x = width / 2 - 180;
        int y = height / 2 - 90;

        for (ElementType element : ElementType.values()) {
            guiGraphics.fill(x, y, x + 90, y + 18, 0x66000000 | (element.getColor() & 0xFFFFFF));
            guiGraphics.drawString(this.font, element.getDisplayName(), x + 8, y + 4, 0xFFFFFF);
            y += 24;
            if (y > height - 80) {
                y = height / 2 - 90;
                x += 110;
            }
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
