package me.titruc.shaderPatcher.commands.playerCommands.changeShaderOption;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.ShaderPatcher;
import me.titruc.shaderPatcher.playerManager.PlayerManager;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.Component;

public class ChangeShaderOptionArgumentBuilder
{
    private static final SimpleCommandExceptionType ALREADY_SET =
            new SimpleCommandExceptionType(new Message() {
                @Override
                public String getString() {
                    return ConfigHandler.ShaderOptionCommandErrorAlready;
                }
            });

    static LiteralArgumentBuilder<CommandSourceStack> getCommandArgumentBuilder()
    {
        var testcmd =  Commands.literal(ConfigHandler.getShaderOptionCommandName);

        for(int i = 0; i < ShaderPatcher.shaderOption.option.size(); i++)
        {
            int index = i;

            testcmd.then(Commands.literal(ShaderPatcher.shaderOption.option.get(i).optionName)
                    .executes(ctx -> {
                        var sender = ctx.getSource().getSender();
                        if(sender instanceof Player player)
                        {
                            int optionId = ShaderPatcher.shaderOption.option.get(index).optionId;

                            if(PlayerManager.getPlayerShaderOption(player) == optionId)
                            {
                                throw ALREADY_SET.create();
                            }
                            PlayerManager.setPlayerShaderOption(player, optionId);
                        }
                        return 1;
                    }));
        }

        return testcmd;
    }

    public static LiteralCommandNode<CommandSourceStack> getCommandNode()
    {
        return getCommandArgumentBuilder().build();
    }
}
