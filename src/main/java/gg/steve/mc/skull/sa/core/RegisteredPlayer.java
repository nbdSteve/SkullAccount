package gg.steve.mc.skull.sa.core;

import gg.steve.mc.skull.sa.framework.utils.EncryptionUtil;
import gg.steve.mc.skull.sa.framework.utils.IpUtil;
import gg.steve.mc.skull.sa.framework.yml.Files;
import gg.steve.mc.skull.sa.gui.GuiManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.UUID;

public class RegisteredPlayer {
    private UUID playerId;
    private String ip;
    private int remaining;

    public RegisteredPlayer(UUID playerId) {
        this.playerId = playerId;
        this.ip = EncryptionUtil.getDecrypted(Files.DATA.get().getString(playerId + ".ip"));
        this.remaining = Files.DATA.get().getInt(playerId + ".remaining");
    }

    public RegisteredPlayer(Player player, int remaining) {
        this.playerId = player.getUniqueId();
        this.ip = IpUtil.convertAddressToString(player.getAddress());
        this.remaining = remaining;
    }

    public boolean decrementRemaining() {
        this.remaining--;
        if (this.remaining <= 0) {
            // open the gui if player online
            if (Bukkit.getPlayer(this.playerId) != null) {
                GuiManager.open(Bukkit.getPlayer(playerId));
            }
            Files.DATA.get().set(String.valueOf(this.playerId), null);
            Files.DATA.save();
            return true;
        }
        return false;
    }

    public void saveToFile() {
        YamlConfiguration config = Files.DATA.get();
        if (config.getConfigurationSection(String.valueOf(this.playerId)) == null) {
            config.createSection(String.valueOf(this.playerId));
        }
        config.set(this.playerId + ".ip", EncryptionUtil.getEncrypted(this.ip));
        config.set(this.playerId + ".remaining", this.remaining);
        Files.DATA.save();
    }

    public boolean isSameIp(InetAddress address) {
        return IpUtil.convertAddressToString(address).equalsIgnoreCase(this.ip);
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public int getRemaining() {
        return remaining;
    }
}
