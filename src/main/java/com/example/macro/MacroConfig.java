package com.example.macro;

import net.minecraft.core.BlockPos;
import java.util.HashMap;
import java.util.Map;

public class MacroConfig {
    public static boolean enabled = false;

    // Core NPCs
    public static BlockPos kingPos = new BlockPos(0, 128, 0); // Royal Palace center

    // Detailed Area Coordinates (Hypixel Wiki Based)
    public static final Map<String, BlockPos> LOCATIONS = new HashMap<>();
    static {
        LOCATIONS.put("Upper Mines", new BlockPos(-130, 174, -50));
        LOCATIONS.put("Royal Mines", new BlockPos(170, 150, 150));
        LOCATIONS.put("Lava Springs", new BlockPos(60, 197, -15));
        LOCATIONS.put("Cliffside Veins", new BlockPos(0, 128, 46));
        LOCATIONS.put("Rampart's Quarry", new BlockPos(-90, 145, 20));
        LOCATIONS.put("Divan's Gateway", new BlockPos(0, 128, 160));
        LOCATIONS.put("Great Ice Wall", new BlockPos(0, 128, 200));
        LOCATIONS.put("Forge Basin", new BlockPos(0, 148, -10));
        LOCATIONS.put("Goblin Burrows", new BlockPos(-120, 160, 120));
        LOCATIONS.put("Aristocrat Passage", new BlockPos(130, 151, 130));
    }

    public static BlockPos currentTarget = LOCATIONS.get("Upper Mines");
    public static String currentArea = "Upper Mines";

    // Safety
    public static int minPauseTicks = 40;
    public static int maxPauseTicks = 100;

    public static void toggle() {
        enabled = !enabled;
    }
}
