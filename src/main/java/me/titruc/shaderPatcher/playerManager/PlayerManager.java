package me.titruc.shaderPatcher.playerManager;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import me.titruc.shaderPatcher.ShaderPatcher;
import me.titruc.shaderPatcher.ConfigHandler;
import org.bukkit.persistence.PersistentDataType;

public class PlayerManager
{
    static public void setPlayerShaderOption(Player player, int option)
    {
        NamespacedKey key = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.shaderOptionPersistentDataName);
        player.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, option);
    }

    static public int getPlayerShaderOption(Player player)
    {
        NamespacedKey key = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.shaderOptionPersistentDataName);
        var playerData = player.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);

        if(playerData != null)
        {
            return playerData;
        }

        return -1;
    }

    static public void setDefaultShaderOption(Player player)
    {
        setPlayerShaderOption(player, ConfigHandler.shaderOptionDefaultId);
    }
}
