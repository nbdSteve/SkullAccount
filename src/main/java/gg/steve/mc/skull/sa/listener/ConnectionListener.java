package gg.steve.mc.skull.sa.listener;

import gg.steve.mc.skull.sa.SkullAccount;
import gg.steve.mc.skull.sa.core.RegistrationManager;
import gg.steve.mc.skull.sa.framework.utils.ColorUtil;
import gg.steve.mc.skull.sa.framework.yml.Files;
import gg.steve.mc.skull.sa.gui.GuiManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;

public class ConnectionListener implements Listener {

    @EventHandler
    public void preJoin(AsyncPlayerPreLoginEvent event) {
        if (RegistrationManager.isRegistered(event.getUniqueId()) && !RegistrationManager.isSameIp(event.getUniqueId(), event.getAddress())) {
            event.disallow(PlayerPreLoginEvent.Result.KICK_OTHER, ColorUtil.colorize(Files.CONFIG.get().getString("kick-message")));
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!RegistrationManager.isRegistered(player)) {
            Bukkit.getScheduler().runTaskLater(SkullAccount.getInstance(), () -> {
                GuiManager.open(player);
            }, 5L);
        }
    }

    @EventHandler
    public void close(InventoryCloseEvent event) {
        Bukkit.getScheduler().runTaskLater(SkullAccount.getInstance(), () -> {
            if (GuiManager.isRequiredPlayer(((Player) event.getPlayer()).getPlayer()))
                GuiManager.open(((Player) event.getPlayer()).getPlayer());
        }, 2L);
    }
}
