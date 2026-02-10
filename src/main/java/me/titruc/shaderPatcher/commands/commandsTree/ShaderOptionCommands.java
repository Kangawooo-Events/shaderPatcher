package me.titruc.shaderPatcher.commands.commandsTree;

import me.titruc.shaderPatcher.commands.BranchCommand;
import me.titruc.shaderPatcher.commands.SubCommand;
import org.bukkit.entity.Player;

import java.util.List;

public class ShaderOptionCommands extends BranchCommand
{
    public ShaderOptionCommands(List<SubCommand> list) {
        super(list);
    }

    @Override
    public String getName() {
        return "shader patcher option";
    }

    @Override
    public String getDescription() {
        return "Everything related to shader patcher";
    }

    @Override
    public String getSyntax() {
        return "/freezerburn config";
    }

    @Override
    public boolean canUse(Player player) {
        return player.hasPermission("freezerburn.config");
    }
}
