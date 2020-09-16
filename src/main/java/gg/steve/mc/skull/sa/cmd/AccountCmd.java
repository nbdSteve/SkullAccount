package gg.steve.mc.skull.sa.cmd;

import gg.steve.mc.skull.sa.SkullAccount;
import gg.steve.mc.skull.sa.framework.cmd.MainCommand;
import gg.steve.mc.skull.sa.framework.cmd.misc.HelpSubCmd;
import gg.steve.mc.skull.sa.framework.cmd.misc.ReloadSubCmd;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class AccountCmd extends MainCommand {

    public AccountCmd() {
        addSubCommand(new HelpSubCmd(), true);
        addSubCommand(new ReloadSubCmd(SkullAccount.getInstance()), false);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return onCommand(sender, args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return onTabComplete(args);
    }
}
