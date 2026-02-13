package me.titruc.shaderPatcher;

import java.util.List;

import static me.titruc.shaderPatcher.ShaderPatcher.config;

public class ConfigHandler
{
    public static String shaderOptionPersistentDataName;
    public static String playerSetupPersistentDataName;
    public static String playerVersionPersistentDataName;
    public static String getShaderOptionCommandName;
    public static String warningText;
    public static String warningInvisibleColor;
    public static int warningDisplayTime;
    public static String getShaderPatcherCommandName;
    public static String ShaderOptionCommandErrorAlready;
    public static String ShaderPatcherCommandErrorPlayerNowhereToBeFound;
    public static String ShaderPatcheErrorVersion;
    public static int shaderOptionDefaultId;
    public static List<String> shaderOption;
    public static double warningDisplayRange;

    public static void refresh()
    {
        shaderOptionPersistentDataName = config.getString("SHADER_OPTION_PERSISTENT_DATA_NAME");
        playerSetupPersistentDataName = config.getString("PLAYER_SETUP_PERSISTENT_DATA_NAME");
        playerVersionPersistentDataName = config.getString("PLAYER_VERSION_PERSISTENT_DATA_NAME");
        warningText = config.getString("WARNING_TEXT");
        warningDisplayRange = config.getDouble("WARNING_DISPLAY_RANGE");
        warningInvisibleColor = config.getString("WARNING_INVISIBLE_COLOR");
        getShaderOptionCommandName = config.getString("SHADER_OPTION_COMMAND_NAME");
        getShaderPatcherCommandName = config.getString("SHADER_PATCHER_COMMAND_NAME");
        ShaderOptionCommandErrorAlready = config.getString("SHADER_OPTION_COMMAND_ERROR_ALREADY");
        ShaderPatcherCommandErrorPlayerNowhereToBeFound = config.getString("SHADER_PATCHER_COMMAND_ERROR_PLAYER_NOWHERE_TO_BE_FOUND");
        ShaderPatcheErrorVersion = config.getString("SHADER_PATCHER_ERROR_VERSION");
        shaderOptionDefaultId = config.getInt("SHADER_OPTION_DEFAULT_ID");
        warningDisplayTime = config.getInt("WARNING_DISPLAY_TIME");
        shaderOption = config.getStringList("SHADER_OPTION");
    }

}