package com.example.macro;

import net.minecraft.core.BlockPos;

public class MacroConfig {
    public static boolean enabled = false;

    // Coordinates (Approximate, users should adjust)
    public static BlockPos kingPos = new BlockPos(0, 128, 0);
    public static BlockPos upperMinesPos = new BlockPos(-50, 150, 0);
    public static BlockPos royalMinesPos = new BlockPos(150, 150, 150);
    public static BlockPos cliffsideVeinsPos = new BlockPos(0, 140, -100);

    public static BlockPos currentTarget = upperMinesPos;
    public static String currentArea = "Upper Mines";

    // Safety
    public static int minPauseTicks = 40; // 2 seconds
    public static int maxPauseTicks = 100; // 5 seconds

    public static void toggle() {
        enabled = !enabled;
    }
}
