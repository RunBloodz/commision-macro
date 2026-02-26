package com.example.macro.mixin;

import com.example.macro.MacroClient;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "tick", at = @At("END"))
    private void onTick(CallbackInfo ci) {
        MacroClient.onTick((Minecraft) (Object) this);
    }
}
