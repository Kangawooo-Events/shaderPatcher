package me.titruc.shaderPatcher.playerManager;

//libs
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

//plugin's file
import me.titruc.shaderPatcher.ShaderPatcher;
import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.message.TextDisplayManager;


import java.util.Objects;

//tools to get/set info on players
public class PlayerManager
{
    //create all persistent data key
    private static final NamespacedKey shaderOptionKey = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.shaderOptionPersistentDataName);
    private static final NamespacedKey setupKey = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.playerSetupPersistentDataName);
    private static final NamespacedKey keyVersion = new NamespacedKey(ShaderPatcher.singleton, ConfigHandler.playerVersionPersistentDataName);

    //set player shader option in persistent data
    static public void setPlayerShaderOption(Player player, int option)
    {
        if(PlayerManager.getPlayerShaderOption(player) == ConfigHandler.shaderOptionDefaultId)
        {
            TextDisplayManager.removeFromPlayer(player.getUniqueId().toString());
        }

        player.getPersistentDataContainer().set(shaderOptionKey, PersistentDataType.INTEGER, option);
    }

    //get the current shader option of a player
    static public int getPlayerShaderOption(Player player)
    {
        var playerData = player.getPersistentDataContainer().get(shaderOptionKey, PersistentDataType.INTEGER);

        if(playerData != null)
        {
            return playerData;
        }

        return -1;
    }

    //check if the player already have a shader option
    static public boolean playerIsSetup(Player player)
    {
        return player.getPersistentDataContainer().has(setupKey, PersistentDataType.BOOLEAN);
    }

    //set player shaderPatcher version in persistent data
    static public void setPlayerVersion(Player player)
    {
        player.getPersistentDataContainer().set(keyVersion, PersistentDataType.STRING, ShaderPatcher.shaderPatcherVersion);
    }

    //get player shaderPatcher version
    static public String getPlayerVersion(Player player)
    {
        return player.getPersistentDataContainer().get(keyVersion, PersistentDataType.STRING);
    }

    //setup player version/default shader option; used usually on first join (check playerIsSetup)
    static public void setupPlayer(Player player)
    {
        player.getPersistentDataContainer().set(setupKey, PersistentDataType.BOOLEAN, true);
        //set version
        setPlayerVersion(player);
        //set player's shader option to default
        setDefaultShaderOption(player);
    }

    //check if a player have a version register
    static public boolean playerHasOptionVersion(Player player)
    {
        return player.getPersistentDataContainer().has(keyVersion, PersistentDataType.STRING);
    }

    //ser player's shader option to default
    static public void setDefaultShaderOption(Player player)
    {
        setPlayerShaderOption(player, ConfigHandler.shaderOptionDefaultId);
    }

    //handle player when they first join/ when their version isn't up to date
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
