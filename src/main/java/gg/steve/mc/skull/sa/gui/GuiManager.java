package gg.steve.mc.skull.sa.gui;

import gg.steve.mc.skull.sa.framework.yml.Files;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GuiManager {
    private static RegistryGui gui;
    private static List<UUID> requiredPlayers;

    public static void open(Player player) {
        if (gui == null) gui = new RegistryGui(Files.CONFIG.get().getConfigurationSection("gui"));
        gui.refresh();
        gui.open(player);
        addRequiredPlayer(player);
    }

    public static void addRequiredPlayer(Player player) {
        if (requiredPlayers == null) requiredPlayers = new ArrayList<>();
        if (requiredPlayers.contains(player.getUniqueId())) return;
        requiredPlayers.add(player.getUniqueId());
    }

    public static void removeRequiredPlayer( Player player) {
        if (requiredPlayers == null || requiredPlayers.isEmpty()) return;
        if (!requiredPlayers.contains(player.getUniqueId())) return;
        requiredPlayers.remove(player.getUniqueId());
    }

    public static boolean isRequiredPlayer(Player player) {
        if (requiredPlayers == null || requiredPlayers.isEmpty()) return false;
        return requiredPlayers.contains(player.getUniqueId());
    }
}
