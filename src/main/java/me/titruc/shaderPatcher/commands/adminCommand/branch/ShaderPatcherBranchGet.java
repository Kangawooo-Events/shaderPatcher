package me.titruc.shaderPatcher.commands.adminCommand.branch;

//libs
import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

//plugin's files
import me.titruc.shaderPatcher.ShaderPatcher;
import me.titruc.shaderPatcher.commands.Error;
import me.titruc.shaderPatcher.commands.adminCommand.CommandBranch;
import me.titruc.shaderPatcher.playerManager.PlayerManager;

//create the get command to get the current setting of a player
public class ShaderPatcherBranchGet extends CommandBranch {

    @Override
    public void addBranchToTree(LiteralArgumentBuilder<CommandSourceStack> cmd)
    {
        cmd.then(
                Commands.literal("get")
                        .then(
                                Commands.argument("playerName", StringArgumentType.word())
                                        //create player suggestion
                                        .suggests((ctx, builder) -> {
                                            ShaderPatcher.singleton.getServer().getOnlinePlayers()
                                                    .forEach(p -> builder.suggest(p.getName()));
                                            return builder.buildFuture();
                                        })
                                        //logic
                                        .executes(ctx -> {
                                            String playerName = StringArgumentType.getString(ctx, "playerName");

                                            var player = ShaderPatcher.singleton.getServer().getPlayer(playerName);

                                            if (player == null) {
                                                throw Error.PLAYER_NOWHERE_TO_BE_FIND.create();
                                            }
                                            //display the current player option
                                            ctx.getSource().getSender().sendMessage(
                                                    player.getName() + "'s shader parameter: " + ShaderPatcher.shaderOption.option.get(PlayerManager.getPlayerShaderOption(player)).optionName
                                            );

                                            return 1;
                                        })
                        )
        );
    }
}
