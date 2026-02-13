package me.titruc.shaderPatcher.commands.adminCommand;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;

public abstract class CommandBranch {
    public abstract void addBranchToTree(LiteralArgumentBuilder<CommandSourceStack> cmd);
}
