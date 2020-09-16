package gg.steve.mc.skull.sa.framework.yml;

import gg.steve.mc.skull.sa.framework.yml.utils.FileManagerUtil;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public enum Files {
    // generic
    CONFIG("skull-account.yml"),
    // permissions
    PERMISSIONS("permissions.yml"),
    // lang
    DEBUG("lang" + File.separator + "debug.yml"),
    MESSAGES("lang" + File.separator + "messages.yml"),
    //data
    DATA("data" + File.separator + "registry.yml"),
    ;

    private final String path;

    Files(String path) {
        this.path = path;
    }

    public void load(FileManagerUtil fileManager) {
        fileManager.add(name(), this.path);
    }

    public YamlConfiguration get() {
        return FileManagerUtil.get(name());
    }

    public void save() {
        FileManagerUtil.save(name());
    }

    public static void reload() {
        FileManagerUtil.reload();
    }
}
