package gg.steve.mc.skull.sa.framework;

import gg.steve.mc.skull.sa.cmd.AccountCmd;
import gg.steve.mc.skull.sa.core.RegistrationManager;
import gg.steve.mc.skull.sa.framework.gui.GuiClickListener;
import gg.steve.mc.skull.sa.framework.yml.Files;
import gg.steve.mc.skull.sa.framework.yml.utils.FileManagerUtil;
import gg.steve.mc.skull.sa.listener.ConnectionListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Class that handles setting up the plugin on start
 */
public class SetupManager {
    private static FileManagerUtil fileManager;

    private SetupManager() throws IllegalAccessException {
        throw new IllegalAccessException("Manager class cannot be instantiated.");
    }

    /**
     * Loads the files into the file manager
     */
    public static void setupFiles(FileManagerUtil fm) {
        fileManager = fm;
        Files.CONFIG.load(fm);
        Files.PERMISSIONS.load(fm);
        Files.DEBUG.load(fm);
        Files.MESSAGES.load(fm);
        Files.DATA.load(fm);
    }

    public static void registerCommands(JavaPlugin instance) {
        instance.getCommand("sacc").setExecutor(new AccountCmd());
    }

    /**
     * Register all of the events for the plugin
     *
     * @param instance Plugin, the main plugin instance
     */
    public static void registerEvents(JavaPlugin instance) {
        PluginManager pm = instance.getServer().getPluginManager();
        pm.registerEvents(new ConnectionListener(), instance);
        pm.registerEvents(new GuiClickListener(), instance);
    }

    public static void registerEvent(JavaPlugin instance, Listener listener) {
        instance.getServer().getPluginManager().registerEvents(listener, instance);
    }

    public static void loadPluginCache() {
        RegistrationManager.load();
    }

    public static void shutdownPluginCache() {
        RegistrationManager.shutdown();
    }

    public static FileManagerUtil getFileManager() {
        return fileManager;
    }
}
