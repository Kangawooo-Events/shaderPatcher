package me.titruc.shaderPatcher.commands.playerCommands.changeShaderOption;

//libs
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.entity.Player;

//plugin's files
import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.ShaderPatcher;
import me.titruc.shaderPatcher.playerManager.PlayerManager;
import me.titruc.shaderPatcher.commands.Error;

//a little command so player can change their shader related visual setting
public class ChangeShaderOptionArgumentBuilder
{
    //brigadier stuff
    static LiteralArgumentBuilder<CommandSourceStack> getCommandArgumentBuilder()
    {
        var optionCmd =  Commands.literal(ConfigHandler.getShaderOptionCommandName);

        //create an argument for each option present in the config file
        for(int i = 0; i < ShaderPatcher.shaderOption.option.size(); i++)
        {
            int index = i;

            optionCmd.then(Commands.literal(ShaderPatcher.shaderOption.option.get(i).optionName).requires(source -> source.getSender().hasPermission("shaderpatcher.option"))
                    .executes(ctx -> {
                        var sender = ctx.getSource().getSender();
                        if(sender instanceof Player player)
                        {
                            int optionId = ShaderPatcher.shaderOption.option.get(index).optionId;

                            if(PlayerManager.getPlayerShaderOption(player) == optionId)
                            {
                                throw Error.ALREADY_SET.create();
                            }
                            PlayerManager.setPlayerShaderOption(player, optionId);
                        }
                        return 1;
                    }));
        }

        return optionCmd;
    }

    //brigadier stuff
    public static LiteralCommandNode<CommandSourceStack> getCommandNode()
    {
        return getCommandArgumentBuilder().build();
    }
}
