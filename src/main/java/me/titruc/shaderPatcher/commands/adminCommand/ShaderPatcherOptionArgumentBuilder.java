package me.titruc.shaderPatcher.commands.adminCommand;

//libs
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import java.util.ArrayList;
import java.util.List;

//plugin's files
import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.commands.adminCommand.branch.ShaderPatcherBranchGet;
import me.titruc.shaderPatcher.commands.adminCommand.branch.ShaderPatcherBranchRefresh;
import me.titruc.shaderPatcher.commands.adminCommand.branch.ShaderPatcherBranchVersion;

//create a command for admin to use, this command handle all subcommands of this plugin
public class ShaderPatcherOptionArgumentBuilder {

    //register all command branch
    private static final List<CommandBranch> branches = new ArrayList<>();

    //do the register
    static {
        branches.add(new ShaderPatcherBranchGet());
        branches.add(new ShaderPatcherBranchVersion());
        branches.add(new ShaderPatcherBranchRefresh());
    }

    //build the command
    static LiteralArgumentBuilder<CommandSourceStack> getCommandArgumentBuilder()
    {
        var shaderPatcherCmd =  Commands.literal(ConfigHandler.getShaderPatcherCommandName).requires(source -> source.getSender().hasPermission("shaderpatcher.admin"));

        //create a subcommand for each branches
        for (CommandBranch branch : branches) {
            branch.addBranchToTree(shaderPatcherCmd);
        }

        return shaderPatcherCmd;
    }

    //brigadier stuff
    public static LiteralCommandNode<CommandSourceStack> getCommandNode()
    {
        return getCommandArgumentBuilder().build();
    }
}
