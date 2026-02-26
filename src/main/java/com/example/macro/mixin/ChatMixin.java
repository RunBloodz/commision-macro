package com.example.macro.mixin;

import com.example.macro.gui.MacroScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class ChatMixin {
    // Intercepting chat messages
    @Inject(method = "chat", at = @At("HEAD"), cancellable = true)
    private void onChat(String message, CallbackInfo ci) {
        if (message.equalsIgnoreCase("/macro")) {
            Minecraft client = Minecraft.getInstance();
            client.execute(() -> {
                client.setScreen(new MacroScreen());
            });
            ci.cancel();
        }
    }
}
