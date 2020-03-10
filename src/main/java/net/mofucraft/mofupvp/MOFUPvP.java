package net.mofucraft.mofupvp;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public final class MOFUPvP extends JavaPlugin {

    private static FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        config = getConfig();
        FileConfiguration config = getConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

