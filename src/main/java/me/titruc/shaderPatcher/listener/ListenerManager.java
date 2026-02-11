package me.titruc.shaderPatcher.listener;

import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.playerManager.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ListenerManager implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        //get player
        Player player = event.getPlayer();

        //set default shader setting when first join
        if (!player.hasPlayedBefore()) {
            PlayerManager.setDefaultShaderOption(player);
        }
    }
}
