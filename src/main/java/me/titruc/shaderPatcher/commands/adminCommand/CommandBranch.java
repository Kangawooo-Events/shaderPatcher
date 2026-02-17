package me.titruc.shaderPatcher.commands.adminCommand;

//libs
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;

//this class is expected to be extended by other class so they all have the same thing in it, if you want to create a branch extend this class and override addBranchToTree
public abstract class CommandBranch {
    public abstract void addBranchToTree(LiteralArgumentBuilder<CommandSourceStack> cmd);
}
