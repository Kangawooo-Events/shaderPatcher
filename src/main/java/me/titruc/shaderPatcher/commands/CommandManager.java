package me.titruc.shaderPatcher.commands;

import me.titruc.shaderPatcher.commands.commandsTree.ShaderOptionCommands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {

    private final List<SubCommand> subCommands;

    public CommandManager() {
        subCommands= List.of(

                new ShaderOptionCommands(
                        List.of(

                        )
                )
        );
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args)
    {
        if (!(sender instanceof Player player)) return true;

        //find subcommand
        if (args.length > 0)
        {
            for (SubCommand sub : subCommands){

                //is it the radio command
                if (args[0].equalsIgnoreCase(sub.getName()))
                {
                    //preform sub command given
                    return sub.execute(player, args, 1);
                }
            }

            //command does not exist
            player.sendMessage(Component.text("Sub Command Does Not Exist").color(TextColor.color(255, 0, 0)));

        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        return List.of();
    }
    /*
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {


    }
    */
}
