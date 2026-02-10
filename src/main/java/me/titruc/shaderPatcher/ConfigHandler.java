package me.titruc.shaderPatcher;

import java.util.List;

import static me.titruc.shaderPatcher.ShaderTweaker.config;

public class ConfigHandler
{
    public static List<String> shaderOption;

    public static void refresh()
    {
        shaderOption = config.getStringList("SHADER_OPTION");
    }

}