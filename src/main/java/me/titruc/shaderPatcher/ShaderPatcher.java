package me.titruc.shaderPatcher;

import me.titruc.shaderPatcher.commands.CommandManager;
import me.titruc.shaderPatcher.commands.playerCommands.ChangeShaderOption;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShaderPatcher extends JavaPlugin {

    public static FileConfiguration config;
    public static JavaPlugin singleton;

    @Override
    public void onEnable() {
        saveDefaultConfig();
    }

    @Override
    public void reloadConfig()
    {
        //do normal reload stuff
        super.reloadConfig();

        //singleton
        singleton = this;

        //update config singleton
        config = getConfig();

        //reload the config api
        ConfigHandler.refresh();

        //add utility command
        getCommand("shaderpatcher").setExecutor(new CommandManager());

        //add player command
        getCommand("changeshaderoption").setExecutor(new ChangeShaderOption());
    }
}
