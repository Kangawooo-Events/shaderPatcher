package me.titruc.shaderPatcher.commands;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import me.titruc.shaderPatcher.ConfigHandler;

//store all command error
public class Error
{
    //error, if the entered player isn't online
    public static final SimpleCommandExceptionType PLAYER_NOWHERE_TO_BE_FIND =
            new SimpleCommandExceptionType(new Message() {
                @Override
                public String getString() {
                    return ConfigHandler.ShaderPatcherCommandErrorPlayerNowhereToBeFound;
                }
            });

    //create an error, this error is return if a player is trying to set an option he already have
    public static final SimpleCommandExceptionType ALREADY_SET =
            new SimpleCommandExceptionType(new Message() {
                @Override
                public String getString() {
                    return ConfigHandler.ShaderOptionCommandErrorAlready;
                }
            });
}
