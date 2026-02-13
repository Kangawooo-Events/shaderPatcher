package me.titruc.shaderPatcher.commands.adminCommand;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.commands.adminCommand.branch.ShaderPatcherBranchGet;
import me.titruc.shaderPatcher.commands.adminCommand.branch.ShaderPatcherBranchRefresh;
import me.titruc.shaderPatcher.commands.adminCommand.branch.ShaderPatcherBranchVersion;

import java.util.ArrayList;
import java.util.List;

public class ShaderPatcherOptionArgumentBuilder {

    private static final List<CommandBranch> branches = new ArrayList<>();

    static {
        branches.add(new ShaderPatcherBranchGet());
        branches.add(new ShaderPatcherBranchVersion());
        branches.add(new ShaderPatcherBranchRefresh());
    }

    static LiteralArgumentBuilder<CommandSourceStack> getCommandArgumentBuilder()
    {
        var shaderPatcherCmd =  Commands.literal(ConfigHandler.getShaderPatcherCommandName).requires(source -> source.getSender().hasPermission("shaderpatcher.admin"));

        for (CommandBranch branch : branches) {
            branch.addBranchToTree(shaderPatcherCmd);
        }

        return shaderPatcherCmd;
    }

    public static LiteralCommandNode<CommandSourceStack> getCommandNode()
    {
        return getCommandArgumentBuilder().build();
    }
}
