package gg.steve.mc.skull.sa;

import gg.steve.mc.skull.sa.framework.SetupManager;
import gg.steve.mc.skull.sa.framework.utils.LogUtil;
import gg.steve.mc.skull.sa.framework.yml.utils.FileManagerUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class SkullAccount extends JavaPlugin {
    private static SkullAccount instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        LogUtil.setInstance(instance, true);
        SetupManager.setupFiles(new FileManagerUtil(instance));
        SetupManager.registerCommands(instance);
        SetupManager.registerEvents(instance);
        SetupManager.loadPluginCache();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        SetupManager.shutdownPluginCache();
    }

    public static SkullAccount getInstance() {
        return instance;
    }
}
