package com.example.macro.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WorldScanner {
    public static List<BlockPos> findBlocks(BlockPos center, int radius, java.util.function.Predicate<BlockState> predicate) {
        List<BlockPos> found = new ArrayList<>();
        Minecraft client = Minecraft.getInstance();
        Level level = client.level;
        if (level == null) return found;

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = center.offset(x, y, z);
                    if (predicate.test(level.getBlockState(pos))) {
                        found.add(pos);
                    }
                }
            }
        }

        found.sort(Comparator.comparingDouble(p -> p.distSqr(center)));
        return found;
    }

    public static BlockPos findNearest(BlockPos center, int radius, java.util.function.Predicate<BlockState> predicate) {
        List<BlockPos> blocks = findBlocks(center, radius, predicate);
        return blocks.isEmpty() ? null : blocks.get(0);
    }
}
