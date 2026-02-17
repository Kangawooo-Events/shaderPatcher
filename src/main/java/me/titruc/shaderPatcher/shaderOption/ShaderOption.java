package me.titruc.shaderPatcher.shaderOption;

//libs
import java.util.ArrayList;
import java.util.List;

public class ShaderOption {

    //this array contains all option
    public ArrayList<ShaderOptionObject> option;

    //transform list of string into shader option store in "option" array
    public ShaderOption(List<String> _option)
    {
        option = new ArrayList<>();

        for(int i = 0; i < _option.toArray().length; i++)
        {
            option.add(new ShaderOptionObject(_option.get(i), i));
        }
    }

    //get the current plugin version (version depend on available option)
    public String getVersion()
    {
        StringBuilder version = new StringBuilder();

        //the current plugin version is a concatenation of all option
        for(int i = 0; i < option.toArray().length; i++)
        {
            version.append(option.get(i).optionName);
        }

        return  version.toString();
    }

}
