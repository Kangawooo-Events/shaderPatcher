package me.titruc.shaderPatcher;

import java.util.List;

import static me.titruc.shaderPatcher.ShaderPatcher.config;

public class ConfigHandler
{
    public static String shaderOptionPersistentDataName;
    public static String getShaderOptionCommandName;
    public static String ShaderOptionCommandErrorAlready;
    public static int shaderOptionDefaultId;
    public static List<String> shaderOption;

    public static void refresh()
    {
        shaderOptionPersistentDataName = config.getString("SHADER_OPTION_PERSISTENT_DATA_NAME");
        getShaderOptionCommandName = config.getString("SHADER_OPTION_COMMAND_NAME");
        ShaderOptionCommandErrorAlready = config.getString("SHADER_OPTION_COMMAND_ERROR_ALREADY");
        shaderOptionDefaultId = config.getInt("SHADER_OPTION_DEFAULT_ID");
        shaderOption = config.getStringList("SHADER_OPTION");
    }

}