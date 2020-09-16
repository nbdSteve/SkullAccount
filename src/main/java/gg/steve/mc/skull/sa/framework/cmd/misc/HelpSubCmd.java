package gg.steve.mc.skull.sa.framework.cmd.misc;

import gg.steve.mc.skull.sa.framework.cmd.SubCommand;
import gg.steve.mc.skull.sa.framework.message.GeneralMessage;
import gg.steve.mc.skull.sa.framework.permission.PermissionNode;
import org.bukkit.command.CommandSender;

public class HelpSubCmd extends SubCommand {

    public HelpSubCmd() {
        super("help", 0, 1, false, PermissionNode.HELP);
        addAlias("h");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        GeneralMessage.HELP.message(sender);
        return true;
    }
}
