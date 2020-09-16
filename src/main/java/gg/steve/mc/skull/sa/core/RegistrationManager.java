package gg.steve.mc.skull.sa.core;

import gg.steve.mc.skull.sa.SkullAccount;
import gg.steve.mc.skull.sa.framework.utils.LogUtil;
import gg.steve.mc.skull.sa.framework.yml.Files;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.*;

public class RegistrationManager {
    private static Map<UUID, RegisteredPlayer> registeredPlayers;
    private static int counter;

    public static void load() {
        YamlConfiguration data = Files.DATA.get();
        if (data.getKeys(false).isEmpty()) return;
        for (String entry : data.getKeys(false)) {
            UUID playerId = UUID.fromString(entry);
            getRegisteredPlayers().put(playerId, new RegisteredPlayer(playerId));
        }
        counter = Bukkit.getScheduler().scheduleAsyncRepeatingTask(SkullAccount.getInstance(), () -> {
            if (getRegisteredPlayers().isEmpty()) return;
            List<UUID> removed = new ArrayList<>();
            for (UUID playerId : getRegisteredPlayers().keySet()) {
                if (getRegisteredPlayers().get(playerId).decrementRemaining()) {
                    removed.add(playerId);
                }
            }
            if (removed.isEmpty()) return;
            for (UUID playerId : removed) {
                getRegisteredPlayers().remove(playerId);
            }
        }, 0L, 20L);
    }

    public static void shutdown() {
        Bukkit.getScheduler().cancelTask(counter);
        if (getRegisteredPlayers().isEmpty()) return;
        for (UUID playerId : getRegisteredPlayers().keySet()) {
            getRegisteredPlayers().get(playerId).saveToFile();
        }
        getRegisteredPlayers().clear();
    }

    public static boolean isRegistered(Player player) {
        return getRegisteredPlayers().containsKey(player.getUniqueId());
    }

    public static boolean isRegistered(UUID playerId) {
        return getRegisteredPlayers().containsKey(playerId);
    }

    public static boolean isSameIp(UUID playerId, InetAddress address) {
        if (!isRegistered(playerId)) return true;
        return getRegisteredPlayers().get(playerId).isSameIp(address);
    }

    public static boolean registerPlayerIp(Player player) {
        if (isRegistered(player)) return false;
        getRegisteredPlayers().put(player.getUniqueId(), new RegisteredPlayer(player, Files.CONFIG.get().getInt("registration-length")));
        return true;
    }

    public static Map<UUID, RegisteredPlayer> getRegisteredPlayers() {
        if (registeredPlayers == null) registeredPlayers = new HashMap<>();
        return registeredPlayers;
    }
}
