package me.titruc.shaderPatcher;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShaderTweaker extends JavaPlugin {

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
    }

    @Override
    public void reloadConfig()
    {
        //do normal reload stuff
        super.reloadConfig();

        //update config singleton
        config = getConfig();

        //reload the config api
        ConfigHandler.refresh();
    }
}
