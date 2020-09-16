package gg.steve.mc.skull.sa.framework.cmd.misc;

import gg.steve.mc.skull.sa.framework.cmd.SubCommand;
import gg.steve.mc.skull.sa.framework.message.GeneralMessage;
import gg.steve.mc.skull.sa.framework.permission.PermissionNode;
import gg.steve.mc.skull.sa.framework.yml.Files;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadSubCmd extends SubCommand {
    private JavaPlugin instance;

    public ReloadSubCmd(JavaPlugin instance) {
        super("reload", 1, 1, false, PermissionNode.RELOAD);
        addAlias("r");
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        Files.reload();
        Bukkit.getPluginManager().disablePlugin(this.instance);
        Bukkit.getPluginManager().enablePlugin(this.instance);
        GeneralMessage.RELOAD.message(sender);
        return true;
    }
}
