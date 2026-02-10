package me.titruc.shaderPatcher.commands;

import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;

public abstract class BranchCommand implements SubCommand {

    protected List<SubCommand> subCommands;

    public BranchCommand(List<SubCommand> list)
    {
        //fill sub commands
        subCommands = list;
    }

    @Override
    public boolean execute(Player player, String[] args, int level)
    {
        for(SubCommand cmd : subCommands)
        {
            if(args[level].equalsIgnoreCase(cmd.getName()))
            {
                //preform sub command given
                return cmd.execute(player, args, level + 1);
            }
        }

        return false;
    }


    @Override
    //provides suggestions when writing args
    public List<String> getSubcommandArguments(Player player, String[] args, int level)
    {
        //does player have permission for this branch
        if(!canUse(player))
            return List.of();

        //if the length of the args is equal to the level, we have found the current end node
        if(args.length == level)
        {
            //display this node's sub commands
            ArrayList<String> commandNames = new ArrayList<>();
            subCommands.forEach((command) -> {
                commandNames.add(command.getName());
            });

            return commandNames;
        }

        //doesn't end on this command
        for(SubCommand cmd : subCommands)
        {
            if(cmd.getName().equalsIgnoreCase(args[level-1]))
                return cmd.getSubcommandArguments(player, args, level+1);
        }

        return List.of();
    }
}