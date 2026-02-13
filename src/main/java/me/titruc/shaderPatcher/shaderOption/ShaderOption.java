package me.titruc.shaderPatcher.shaderOption;

import java.util.ArrayList;
import java.util.List;

public class ShaderOption {

    public ArrayList<ShaderOptionObject> option;

    public ShaderOption(List<String> _option)
    {
        option = new ArrayList<>();

        for(int i = 0; i < _option.toArray().length; i++)
        {
            option.add(new ShaderOptionObject(_option.get(i), i));
        }
    }

    public String getVersion()
    {
        StringBuilder version = new StringBuilder();

        for(int i = 0; i < option.toArray().length; i++)
        {
            version.append(option.get(i).optionName);
        }

        return  version.toString();
    }

}
