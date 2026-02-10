package me.titruc.shaderPatcher.graphicOptionRegistry;

import org.bukkit.plugin.Plugin;

public class ShaderOption
{
    public static String key;
    public static String displayName;
    public static int priority;

    ShaderOption(Plugin plugin, String keyName, int priorityLevel)
    {
        key = plugin.getName().toLowerCase() + ":" + keyName;
        displayName = key;
        priority = priorityLevel;
    }

    String key() {return key;}
    String getDisplayName() {return displayName;}
    int priority() {return priority;}
}
