package me.titruc.shaderPatcher.commands.adminCommand.branch;

//libs
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;

//plugin's files
import me.titruc.shaderPatcher.ShaderPatcher;
import me.titruc.shaderPatcher.commands.adminCommand.CommandBranch;

//refresh config
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

                        ShaderPatcher.singleton.reloadConfig();

                        return 1;
                    })

        );
    }
}
