package com.example.macro;

import com.example.macro.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.Vec3;
import java.util.List;
import java.util.Random;

public class MacroClient implements net.fabricmc.api.ClientModInitializer {
    private enum State { IDLE, SCANNING, TRAVELLING, MINING, CLAIMING, PAUSING }
    private static State currentState = State.IDLE;
    private static State nextState = State.IDLE;
    private static List<BlockPos> currentPath = null;
    private static int pathIndex = 0;
    private static SmoothRotation rotation = new SmoothRotation();
    private static int tickCounter = 0;
    private static int pauseTicks = 0;
    private static final Random random = new Random();

    @Override
    public void onInitializeClient() {
        System.out.println("Skyblock Macro Initialized! Use /macro to open the menu.");
    }

    public static void onTick(Minecraft client) {
        if (!MacroConfig.enabled || client.player == null) return;

        tickCounter++;
        rotation.tick();

        if (currentState == State.PAUSING) {
            pauseTicks--;
            if (pauseTicks <= 0) {
                currentState = nextState;
            }
            return;
        }

        if (tickCounter % 20 == 0) {
            updateLogic(client);
        }

        if (currentState == State.TRAVELLING) {
            handleMovement(client);
        } else if (currentState == State.MINING) {
            handleMining(client);
        } else if (currentState == State.CLAIMING) {
            handleClaiming(client);
        }
    }

    private static void startPause(State next, int ticks) {
        currentState = State.PAUSING;
        nextState = next;
        pauseTicks = ticks;
    }

    private static void updateLogic(Minecraft client) {
        switch (currentState) {
            case IDLE:
                startPause(State.SCANNING, 40);
                break;
            case SCANNING:
                List<String> commissions = TabListParser.getCommissions();
                boolean anyDone = commissions.stream().anyMatch(c -> c.contains("100%") || c.contains("DONE"));

                if (anyDone) {
                    currentState = State.CLAIMING;
                    calculatePath(client, MacroConfig.kingPos);
                } else {
                    String commission = commissions.isEmpty() ? "" : commissions.get(0);
                    BlockPos pos = decideLocation(commission);
                    if (pos != null) {
                        calculatePath(client, pos);
                        currentState = State.TRAVELLING;
                    } else {
                        // Fallback if no specific area found
                        calculatePath(client, MacroConfig.currentTarget);
                        currentState = State.TRAVELLING;
                    }
                }
                break;
            case TRAVELLING:
                if (currentPath != null && !currentPath.isEmpty()) {
                    BlockPos target = currentPath.get(currentPath.size() - 1);
                    if (client.player.blockPosition().distManhattan(target) < 4) {
                        startPause(State.MINING, random.nextInt(40) + 20);
                    }
                } else {
                    currentState = State.SCANNING;
                }
                break;
            case MINING:
                // Periodically check if done or if there's Titanium nearby
                if (tickCounter % 200 == 0) {
                    currentState = State.SCANNING;
                }
                break;
            case CLAIMING:
                if (client.player.blockPosition().distManhattan(MacroConfig.kingPos) < 4) {
                    // Right click simulation logic would go here
                    startPause(State.SCANNING, 100);
                }
                break;
        }
    }

    private static void lookAt(Vec3 target, Minecraft client) {
        double dx = target.x - client.player.getX();
        double dy = target.y - client.player.getEyeY();
        double dz = target.z - client.player.getZ();
        double distance = Math.sqrt(dx * dx + dz * dz);
        float yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
        float pitch = (float) -Math.toDegrees(Math.atan2(dy, distance));
        rotation.setTarget(yaw, pitch);
    }

    private static void handleMovement(Minecraft client) {
        if (currentPath == null || pathIndex >= currentPath.size()) return;

        BlockPos next = currentPath.get(pathIndex);
        Vec3 targetVec = Vec3.atCenterOf(next);

        double dx = targetVec.x - client.player.getX();
        double dz = targetVec.z - client.player.getZ();
        float yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
        rotation.setTarget(yaw + (random.nextFloat() - 0.5f) * 3, 0);

        Options options = client.options;
        options.keyUp.setDown(true);

        if (client.player.blockPosition().distSqr(next) < 2.2) {
            pathIndex++;
            if (pathIndex >= currentPath.size()) {
                options.keyUp.setDown(false);
            }
        }

        if (client.player.horizontalCollision) {
            options.keyJump.setDown(true);
        } else if (random.nextInt(100) == 0) {
             options.keyJump.setDown(false);
        }
    }

    private static void handleMining(Minecraft client) {
        BlockPos nearest = MiningHandler.findNearestTarget(client.player.blockPosition(), 5);
        if (nearest != null) {
            lookAt(Vec3.atCenterOf(nearest), client);

            if (!rotation.isActive()) {
                if (client.gameMode != null) {
                    client.gameMode.continueDestroyBlock(nearest, Direction.UP);
                    client.player.swing(InteractionHand.MAIN_HAND);
                }
            }
        } else {
            // No blocks found, move slightly or scan
            if (random.nextInt(50) == 0) currentState = State.SCANNING;
        }
    }

    private static void handleClaiming(Minecraft client) {
        if (client.player.blockPosition().distManhattan(MacroConfig.kingPos) >= 4) {
            handleMovement(client);
        }
    }

    private static void calculatePath(Minecraft client, BlockPos target) {
        if (target == null) return;
        currentPath = Pathfinder.findPath(client.player.blockPosition(), target, client.level, 2500);
        pathIndex = 0;
    }

    private static BlockPos decideLocation(String commission) {
        for (String area : MacroConfig.LOCATIONS.keySet()) {
            if (commission.toLowerCase().contains(area.toLowerCase().replace("'s", ""))) {
                return MacroConfig.LOCATIONS.get(area);
            }
        }
        if (commission.contains("Mithril") || commission.contains("Titanium")) {
            return MacroConfig.currentTarget;
        }
        return null;
    }
}
