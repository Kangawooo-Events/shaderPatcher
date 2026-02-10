package me.titruc.shaderPatcher.playerManager;


import me.titruc.shaderPatcher.shaderOption.ShaderOption;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import me.titruc.shaderPatcher.ShaderPatcher;
import me.titruc.shaderPatcher.ConfigHandler;
import org.bukkit.persistence.PersistentDataType;

public class PlayerManager
{
    static public void changePlayerShaderOption(Player player, int option)
    {
        NamespacedKey key = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.shaderOptionPersistentDataName);
        player.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, option);
    }
}
