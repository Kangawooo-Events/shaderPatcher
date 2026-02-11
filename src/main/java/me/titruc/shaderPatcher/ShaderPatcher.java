package me.titruc.shaderPatcher;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.titruc.shaderPatcher.commands.playerCommands.changeShaderOption.ChangeShaderOptionArgumentBuilder;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShaderPatcher extends JavaPlugin {

    public static FileConfiguration config;
    public static JavaPlugin singleton;

    @Override
    public void onEnable() {
        // save default config
        saveDefaultConfig();

        // singleton
        singleton = this;

        // update config singleton
        config = getConfig();

        // reload ConfigHandler
        ConfigHandler.refresh();

        // setup Brigadier commands
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(ChangeShaderOptionArgumentBuilder.getCommandNode());
        });
    }

    @Override
    public void reloadConfig()
    {
        //do normal reload stuff
        super.reloadConfig();
        // update config singleton
        config = getConfig();
        //reload the config api
        ConfigHandler.refresh();
    }
}
