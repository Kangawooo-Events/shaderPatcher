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

public class ShaderPatcherBranchVersion extends CommandBranch {

    private static final SimpleCommandExceptionType PLAYER_NOWHERE_TO_BE_FIND =
            new SimpleCommandExceptionType(new Message() {
                @Override
                public String getString() {
                    return ConfigHandler.ShaderPatcherCommandErrorPlayerNowhereToBeFound;
                }
            });

    @Override
    public void addBranchToTree(LiteralArgumentBuilder<CommandSourceStack> cmd)
    {
        cmd.then(
                Commands.literal("version")
                        .then(
                                Commands.argument("playerName", StringArgumentType.word())
                                        .suggests((ctx, builder) -> {
                                            ShaderPatcher.singleton.getServer().getOnlinePlayers()
                                                    .forEach(p -> builder.suggest(p.getName()));
                                            return builder.buildFuture();
                                        })
                                        .executes(ctx -> {
                                            String playerName = StringArgumentType.getString(ctx, "playerName");

                                            var player = ShaderPatcher.singleton.getServer().getPlayer(playerName);

                                            if (player == null) {
                                                throw PLAYER_NOWHERE_TO_BE_FIND.create();
                                            }

                                            ctx.getSource().getSender().sendMessage(
                                                    player.getName() + "'s shader patcher version: " + PlayerManager.getPlayerVersion(player)
                                            );

                                            return 1;
                                        })
                        )
        );
    }
}
