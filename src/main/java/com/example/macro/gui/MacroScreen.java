package com.example.macro.gui;

import com.example.macro.MacroConfig;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class MacroScreen extends Screen {
    public MacroScreen() {
        super(Component.literal("Skyblock Macro Config"));
    }

    @Override
    protected void init() {
        int buttonWidth = 200;
        int buttonHeight = 20;
        int spacing = 24;

        this.addRenderableWidget(Button.builder(Component.literal("Macro: " + (MacroConfig.enabled ? "ON" : "OFF")), button -> {
            MacroConfig.toggle();
            button.setMessage(Component.literal("Macro: " + (MacroConfig.enabled ? "ON" : "OFF")));
        }).bounds(this.width / 2 - buttonWidth / 2, 60, buttonWidth, buttonHeight).build());

        this.addRenderableWidget(Button.builder(Component.literal("Target: " + MacroConfig.currentArea), button -> {
            if (MacroConfig.currentArea.equals("Upper Mines")) {
                MacroConfig.currentArea = "Royal Mines";
                MacroConfig.currentTarget = MacroConfig.LOCATIONS.get("Royal Mines");
            } else {
                MacroConfig.currentArea = "Upper Mines";
                MacroConfig.currentTarget = MacroConfig.LOCATIONS.get("Upper Mines");
            }
            button.setMessage(Component.literal("Target: " + MacroConfig.currentArea));
        }).bounds(this.width / 2 - buttonWidth / 2, 60 + spacing, buttonWidth, buttonHeight).build());

        this.addRenderableWidget(Button.builder(Component.literal("Done"), button -> {
            this.minecraft.setScreen(null);
        }).bounds(this.width / 2 - buttonWidth / 2, 60 + spacing * 3, buttonWidth, buttonHeight).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(graphics, mouseX, mouseY, delta);
        graphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(graphics, mouseX, mouseY, delta);
    }
}
