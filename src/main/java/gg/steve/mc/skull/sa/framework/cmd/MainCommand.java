package gg.steve.mc.skull.sa.framework.cmd;

import gg.steve.mc.skull.sa.framework.message.DebugMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MainCommand implements TabExecutor {
    private Map<SubCommand, Boolean> subs;

    public MainCommand() {
        subs = new HashMap<>();
    }

    public void addSubCommand(SubCommand sub, boolean noArgsCommand) {
        subs.put(sub, noArgsCommand);
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            return this.noArgsCommand(sender, args);
        }
        for (SubCommand subCommand : subs.keySet()) {
            if (!subCommand.isExecutor(args[0])) continue;
            if (!subCommand.isValidCommand(sender, args)) return true;
            subCommand.onCommand(sender, args);
            return true;
        }
        DebugMessage.INVALID_COMMAND.message(sender);
        return true;
    }

    public boolean noArgsCommand(CommandSender sender, String[] args) {
        for (Map.Entry sub : subs.entrySet()) {
            if ((boolean) sub.getValue()) {
                SubCommand cmd = (SubCommand) sub.getKey();
                if (!cmd.isValidCommand(sender, args)) return true;
                return cmd.onCommand(sender, args);
            }
        }
        return false;
    }

    public List<String> onTabComplete(String[] args) {
        List<String> tabCmds = new ArrayList<>();
        if (args.length == 1) {
            for (SubCommand sub : subs.keySet()) tabCmds.add(sub.getName());
            return tabCmds;
        }
        return tabCmds;
    }
}
