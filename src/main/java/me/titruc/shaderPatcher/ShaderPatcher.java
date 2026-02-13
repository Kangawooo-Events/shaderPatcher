package me.titruc.shaderPatcher;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import me.titruc.shaderPatcher.commands.adminCommand.ShaderPatcherOptionArgumentBuilder;
import me.titruc.shaderPatcher.commands.playerCommands.changeShaderOption.ChangeShaderOptionArgumentBuilder;
import me.titruc.shaderPatcher.listener.ListenerManager;
import me.titruc.shaderPatcher.shaderOption.ShaderOption;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShaderPatcher extends JavaPlugin {

    public static FileConfiguration config;
    public static ConfigHandler configHandler;
    public static JavaPlugin singleton;
    public static ShaderOption shaderOption;
    public static String shaderPatcherVersion;

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

        //get option
        shaderOption = new ShaderOption(ConfigHandler.shaderOption);

        // setup Brigadier commands
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(ChangeShaderOptionArgumentBuilder.getCommandNode());
        });

        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(ShaderPatcherOptionArgumentBuilder.getCommandNode());
        });

        //setup patcher version
        shaderPatcherVersion = shaderOption.getVersion();

        //setup listener
        getServer().getPluginManager().registerEvents(new ListenerManager(), this);


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
