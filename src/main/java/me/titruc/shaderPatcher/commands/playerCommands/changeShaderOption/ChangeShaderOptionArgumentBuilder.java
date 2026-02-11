package me.titruc.shaderPatcher.commands.playerCommands.changeShaderOption;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

public class ChangeShaderOptionArgumentBuilder
{
    static LiteralArgumentBuilder<CommandSourceStack> getCommandArgumentBuilder()
    {
        return Commands.literal("testcmd")
                // /testcmd seul fonctionne
                .executes(ctx -> {
                    ctx.getSource().getSender().sendMessage("Vous avez tapé /testcmd !");
                    return 1;
                })

                // /testcmd argument_one
                .then(Commands.literal("argument_one")
                        .executes(ctx -> {
                            ctx.getSource().getSender().sendMessage("Argument one !");
                            return 1;
                        }))

                // /testcmd argument_two
                .then(Commands.literal("argument_two")
                        .executes(ctx -> {
                            ctx.getSource().getSender().sendMessage("Argument two !");
                            return 1;
                        }));
    }

    public static LiteralCommandNode<CommandSourceStack> getCommandNode()
    {
        return getCommandArgumentBuilder().build();
    }
}
