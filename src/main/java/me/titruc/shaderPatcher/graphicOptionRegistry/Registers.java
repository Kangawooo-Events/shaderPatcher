package me.titruc.shaderPatcher.graphicOptionRegistry;

import java.util.List;

public class Registers
{
    public static List<ShaderOption> optionRegister;

    public static void addShaderOptionToRegister(ShaderOption option)
    {
        optionRegister.add(option);
    }

}
