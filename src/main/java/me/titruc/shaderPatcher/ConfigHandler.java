package me.titruc.shaderPatcher;

//import lib
import java.util.List;
//import plugin's files
import static me.titruc.shaderPatcher.ShaderPatcher.config;

public class ConfigHandler
{
    //Persistent data name
    public static String shaderOptionPersistentDataName;
    public static String playerSetupPersistentDataName;
    public static String playerVersionPersistentDataName;

    //commands name
    public static String getShaderOptionCommandName;
    public static String getShaderPatcherCommandName;

    //warning text config
    public static String warningText;
    public static String warningInvisibleColor;
    public static int warningDisplayTime;
    public static double warningDisplayRange;
    public static double warningSize;

    //error text
    public static String ShaderOptionCommandErrorAlready;
    public static String ShaderPatcherCommandErrorPlayerNowhereToBeFound;
    public static String ShaderPatcheErrorVersion;

    //shader option
    public static List<String> shaderOption;
    public static int shaderOptionDefaultId;

    public static void refresh()
    {
        //all data from config file
            //persistent data
        shaderOptionPersistentDataName = config.getString("SHADER_OPTION_PERSISTENT_DATA_NAME");
        playerSetupPersistentDataName = config.getString("PLAYER_SETUP_PERSISTENT_DATA_NAME");
        playerVersionPersistentDataName = config.getString("PLAYER_VERSION_PERSISTENT_DATA_NAME");

            //warning text
        warningText = config.getString("WARNING_TEXT");
        warningDisplayRange = config.getDouble("WARNING_DISPLAY_RANGE");
        warningInvisibleColor = config.getString("WARNING_INVISIBLE_COLOR");
        warningDisplayTime = config.getInt("WARNING_DISPLAY_TIME");
        warningSize = config.getInt("WARNING_SIZE");

            //commands name
        getShaderOptionCommandName = config.getString("SHADER_OPTION_COMMAND_NAME");
        getShaderPatcherCommandName = config.getString("SHADER_PATCHER_COMMAND_NAME");

            //error text
        ShaderOptionCommandErrorAlready = config.getString("SHADER_OPTION_COMMAND_ERROR_ALREADY");
        ShaderPatcherCommandErrorPlayerNowhereToBeFound = config.getString("SHADER_PATCHER_COMMAND_ERROR_PLAYER_NOWHERE_TO_BE_FOUND");
        ShaderPatcheErrorVersion = config.getString("SHADER_PATCHER_ERROR_VERSION");

            //shader option
        shaderOption = config.getStringList("SHADER_OPTION");
        shaderOptionDefaultId = config.getInt("SHADER_OPTION_DEFAULT_ID");

    }

}