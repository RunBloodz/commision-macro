package com.example.macro.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TabListParser {
    public static List<String> getTabListNames() {
        List<String> names = new ArrayList<>();
        Minecraft client = Minecraft.getInstance();
        if (client.getConnection() == null) return names;

        Collection<PlayerInfo> playerInfos = client.getConnection().getOnlinePlayers();
        for (PlayerInfo info : playerInfos) {
            Component name = info.getTabListDisplayName();
            if (name != null) {
                names.add(name.getString());
            } else {
                names.add(info.getProfile().getName());
            }
        }
        return names;
    }

    public static List<String> getCommissions() {
        List<String> commissions = new ArrayList<>();
        List<String> tabList = getTabListNames();
        boolean inCommissionsSection = false;

        for (String line : tabList) {
            String trimmed = line.trim();
            if (trimmed.contains("Commissions:")) {
                inCommissionsSection = true;
                continue;
            }
            if (inCommissionsSection) {
                if (trimmed.contains(":")) {
                    commissions.add(trimmed);
                }
            }
        }
        return commissions;
    }
}
