package me.titruc.shaderPatcher;

//import lib
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

//import plugin's files
import me.titruc.shaderPatcher.commands.adminCommand.ShaderPatcherOptionArgumentBuilder;
import me.titruc.shaderPatcher.commands.playerCommands.changeShaderOption.ChangeShaderOptionArgumentBuilder;
import me.titruc.shaderPatcher.listener.ListenerManager;
import me.titruc.shaderPatcher.shaderOption.ShaderOption;

public final class ShaderPatcher extends JavaPlugin {

    //config
    public static FileConfiguration config;
    //configHandler (ty cadden)
    public static ConfigHandler configHandler;
    //easy reference to plugin
    public static JavaPlugin singleton;
    //shader option to choose from
    public static ShaderOption shaderOption;
    //version of the plugin (change if shader options change)
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
