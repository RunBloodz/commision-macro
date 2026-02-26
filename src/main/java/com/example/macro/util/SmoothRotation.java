package com.example.macro.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class SmoothRotation {
    private float targetYaw;
    private float targetPitch;
    private boolean active = false;
    private float speed = 0.15f;

    public void setTarget(float yaw, float pitch) {
        this.targetYaw = yaw;
        this.targetPitch = pitch;
        this.active = true;
    }

    public void tick() {
        if (!active) return;

        Minecraft client = Minecraft.getInstance();
        if (client.player == null) return;

        float currentYaw = client.player.getYRot();
        float currentPitch = client.player.getXRot();

        float newYaw = lerpAngle(currentYaw, targetYaw, speed);
        float newPitch = lerpAngle(currentPitch, targetPitch, speed);

        client.player.setYRot(newYaw);
        client.player.setXRot(newPitch);

        if (Math.abs(newYaw - targetYaw) < 1.0f && Math.abs(newPitch - targetPitch) < 1.0f) {
            active = false;
        }
    }

    private float lerpAngle(float start, float end, float pct) {
        float diff = Mth.wrapDegrees(end - start);
        return start + diff * pct;
    }

    public boolean isActive() {
        return active;
    }
}
