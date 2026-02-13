package me.titruc.shaderPatcher.listener;

import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.message.TextDisplayManager;
import me.titruc.shaderPatcher.playerManager.PlayerManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ListenerManager implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        //get player
        Player player = event.getPlayer();

        if(PlayerManager.getPlayerShaderOption(player) == ConfigHandler.shaderOptionDefaultId)
        {
            TextDisplayManager.putTextDisplayAsPassenger(player);
        }
        PlayerManager.handlePlayerChange(player);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent entity) {

        String uuid = entity.getPlayer().getUniqueId().toString();
        TextDisplayManager.removeFromPlayer(uuid);
    }
}
