package com.example.macro.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import java.util.*;

public class Pathfinder {
    public static List<BlockPos> findPath(BlockPos start, BlockPos end, Level level, int maxNodes) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingDouble(n -> n.fScore));
        Map<BlockPos, Node> allNodes = new HashMap<>();

        Node startNode = new Node(start, 0, getDistance(start, end));
        openSet.add(startNode);
        allNodes.put(start, startNode);

        int count = 0;
        while (!openSet.isEmpty() && count++ < maxNodes) {
            Node current = openSet.poll();

            if (current.pos.equals(end) || current.pos.distManhattan(end) < 2) {
                return reconstructPath(current);
            }

            for (BlockPos neighbor : getNeighbors(current.pos)) {
                if (!isPassable(neighbor, level)) continue;

                double tentativeGScore = current.gScore + getDistance(current.pos, neighbor);
                Node neighborNode = allNodes.getOrDefault(neighbor, new Node(neighbor, Double.POSITIVE_INFINITY, getDistance(neighbor, end)));

                if (tentativeGScore < neighborNode.gScore) {
                    neighborNode.parent = current;
                    neighborNode.gScore = tentativeGScore;
                    neighborNode.fScore = tentativeGScore + neighborNode.hScore;
                    if (!openSet.contains(neighborNode)) {
                        openSet.add(neighborNode);
                        allNodes.put(neighbor, neighborNode);
                    }
                }
            }
        }

        return null;
    }

    private static double getDistance(BlockPos a, BlockPos b) {
        return Math.sqrt(a.distSqr(b));
    }

    private static List<BlockPos> getNeighbors(BlockPos pos) {
        List<BlockPos> neighbors = new ArrayList<>();
        neighbors.add(pos.north());
        neighbors.add(pos.south());
        neighbors.add(pos.east());
        neighbors.add(pos.west());
        neighbors.add(pos.above());
        neighbors.add(pos.below());
        return neighbors;
    }

    private static boolean isPassable(BlockPos pos, Level level) {
        BlockState state = level.getBlockState(pos);
        BlockState above = level.getBlockState(pos.above());
        return !state.canOcclude() && !above.canOcclude();
    }

    private static List<BlockPos> reconstructPath(Node node) {
        List<BlockPos> path = new ArrayList<>();
        Node current = node;
        while (current != null) {
            path.add(current.pos);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private static class Node {
        BlockPos pos;
        double gScore;
        double hScore;
        double fScore;
        Node parent;

        Node(BlockPos pos, double gScore, double hScore) {
            this.pos = pos;
            this.gScore = gScore;
            this.hScore = hScore;
            this.fScore = gScore + hScore;
        }
    }
}
