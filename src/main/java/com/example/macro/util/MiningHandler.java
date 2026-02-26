package com.example.macro.util;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MiningHandler {
    public static BlockPos findNearestTarget(BlockPos start, int range) {
        Minecraft client = Minecraft.getInstance();
        if (client.level == null) return null;

        BlockPos bestPos = null;
        double bestDist = Double.MAX_VALUE;

        for (int x = -range; x <= range; x++) {
            for (int y = -range; y <= range; y++) {
                for (int z = -range; z <= range; z++) {
                    BlockPos pos = start.offset(x, y, z);
                    if (isTargetBlock(client.level.getBlockState(pos))) {
                        double dist = start.distSqr(pos);
                        if (dist < bestDist) {
                            bestDist = dist;
                            bestPos = pos;
                        }
                    }
                }
            }
        }
        return bestPos;
    }

    public static boolean isTargetBlock(BlockState state) {
        Block block = state.getBlock();
        return block == Blocks.PRISMARINE ||
               block == Blocks.PRISMARINE_BRICKS ||
               block == Blocks.DARK_PRISMARINE ||
               block == Blocks.POLISHED_DIORITE ||
               block == Blocks.GRAY_WOOL ||
               block == Blocks.LIGHT_BLUE_WOOL ||
               block == Blocks.CYAN_WOOL;
    }
}
