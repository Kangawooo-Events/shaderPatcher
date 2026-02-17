package me.titruc.shaderPatcher.shaderOption;

public class ShaderOptionObject {

    //basicly a struct to contain shader option properties
    public String optionName;
    public int optionId;

    public ShaderOptionObject(String _optionName, int _id)
    {
        optionName = _optionName;
        //id isn't necessary but i've put it anyway for security and easy access to this information
        optionId = _id;
    }
}
