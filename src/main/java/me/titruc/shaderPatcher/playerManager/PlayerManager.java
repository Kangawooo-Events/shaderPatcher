package me.titruc.shaderPatcher.playerManager;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import me.titruc.shaderPatcher.ShaderPatcher;
import me.titruc.shaderPatcher.ConfigHandler;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

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

    static public boolean playerIsSetup(Player player)
    {
        NamespacedKey key = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.playerSetupPersistentDataName);
        return player.getPersistentDataContainer().has(key, PersistentDataType.BOOLEAN);
    }

    static public void setPlayerVersion(Player player)
    {
        NamespacedKey keyVersion = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.playerVersionPersistentDataName);
        player.getPersistentDataContainer().set(keyVersion, PersistentDataType.STRING, ShaderPatcher.shaderPatcherVersion);
    }

    static public String getPlayerVersion(Player player)
    {
        NamespacedKey keyVersion = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.playerVersionPersistentDataName);
        return player.getPersistentDataContainer().get(keyVersion, PersistentDataType.STRING);
    }

    static public void setupPlayer(Player player)
    {
        NamespacedKey keySetup = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.playerSetupPersistentDataName);
        player.getPersistentDataContainer().set(keySetup, PersistentDataType.BOOLEAN, true);

        setPlayerVersion(player);

        setDefaultShaderOption(player);
    }

    static public boolean playerHasOptionVersion(Player player)
    {
        NamespacedKey key = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.playerVersionPersistentDataName);
        return player.getPersistentDataContainer().has(key, PersistentDataType.STRING);
    }

    static public void setDefaultShaderOption(Player player)
    {
        setPlayerShaderOption(player, ConfigHandler.shaderOptionDefaultId);
    }

    static public void handlePlayerChange(Player player)
    {
        //set default shader setting when first join
        if (!PlayerManager.playerIsSetup(player) || !PlayerManager.playerHasOptionVersion(player)) {
            PlayerManager.setupPlayer(player);
        }
        else if(!Objects.equals(PlayerManager.getPlayerVersion(player), ShaderPatcher.shaderPatcherVersion))
        {

            player.sendMessage(Component.text(
                    ConfigHandler.ShaderPatcheErrorVersion + ConfigHandler.getShaderOptionCommandName,
                    NamedTextColor.RED
            ));


            PlayerManager.setPlayerVersion(player);
            PlayerManager.setDefaultShaderOption(player);
        }
    }
}
