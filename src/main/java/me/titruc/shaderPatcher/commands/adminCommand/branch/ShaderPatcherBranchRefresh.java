package me.titruc.shaderPatcher.commands.adminCommand.branch;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.ShaderPatcher;
import me.titruc.shaderPatcher.commands.adminCommand.CommandBranch;
import me.titruc.shaderPatcher.playerManager.PlayerManager;

public class ShaderPatcherBranchRefresh extends CommandBranch {

    @Override
    public void addBranchToTree(LiteralArgumentBuilder<CommandSourceStack> cmd)
    {
        cmd.then(
                Commands.literal("refresh")
                    .executes(ctx -> {

                        ctx.getSource().getSender().sendMessage(
                                "refreshing shader patcher config..."
                        );

                        ConfigHandler.refresh();

                        return 1;
                    })

        );
    }
}
