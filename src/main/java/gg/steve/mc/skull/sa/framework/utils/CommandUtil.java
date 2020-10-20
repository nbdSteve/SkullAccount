package gg.steve.mc.skull.sa.framework.utils;

import gg.steve.mc.skull.sa.SkullAccount;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class CommandUtil {

    public static void execute(List<String> commands, Player player) {
        for (String command : commands) {
            if (command.startsWith("server")) {
                Bukkit.getMessenger().registerOutgoingPluginChannel(SkullAccount.getInstance(), "BungeeCord");
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                try {
                    out.writeUTF("Connect");
                    out.writeUTF(command.split(" ")[1]);
                } catch (IOException eee) {
                    // Fehler
                }
                player.sendPluginMessage(SkullAccount.getInstance(), "BungeeCord", b.toByteArray());
            } else Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", player.getName()));
        }
    }
}