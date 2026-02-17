package me.titruc.shaderPatcher.listener;

//libs
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

//plugin's files
import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.message.TextDisplayManager;
import me.titruc.shaderPatcher.playerManager.PlayerManager;

public class ListenerManager implements Listener
{
    //display warning and setup player
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        //get player
        Player player = event.getPlayer();

        //display warning only if on the default settings (the one with shadervanilla on)
        if(PlayerManager.getPlayerShaderOption(player) == ConfigHandler.shaderOptionDefaultId)
        {
            TextDisplayManager.putTextDisplayAsPassenger(player);
        }
        //setup player
        PlayerManager.handlePlayerChange(player);

    }

    //removing potential warning sign if a player log out before the warning is removed
    @EventHandler
    public void onQuit(PlayerQuitEvent entity) {
        //get player and delete
        String uuid = entity.getPlayer().getUniqueId().toString();
        TextDisplayManager.removeFromPlayer(uuid);
    }
}
