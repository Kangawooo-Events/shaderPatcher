package me.titruc.shaderPatcher.items;

//libs
import me.titruc.shaderPatcher.ShaderPatcher;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import java.util.Map;

//plugin's files
import me.titruc.shaderPatcher.playerManager.PlayerManager;

//this class is meat to be extend, to create an item that depend on shader option just extend this and overwrite the default.
// Then create method for each shader option that you want to cover and add it to register by overwrite "registerBehavior"
// by adding that for exemple : register("basic", this::basic);
//and when you instanciate do .init() after created the item
public abstract class ShaderItem {

    //the actual item
    private final ItemStack item;

    //register all behavior for different shader option
    private final Map<String, Runnable> behaviors = new HashMap<>();

    //constructor
    protected ShaderItem(Material material)
    {
        this.item = new ItemStack(material);
    }

    //this methods need to be overwritten to register all behavior
    protected abstract void registerBehaviors();

    //init method (must be called after construction)
    public final void init() {
        registerBehaviors();
    }

    //register a behavior
    protected void register(String optionName, Runnable action) {
        behaviors.put(optionName, action);
    }

    //get the item depending on the player (so is shader option)
    public ItemStack getItemFromPlayer(Player player) {

        //get the option index
        int option = PlayerManager.getPlayerShaderOption(player);

        //clamp index for security
        int max = ShaderPatcher.shaderOption.option.size() - 1;
        option = Math.min(option, max);

        //set default case to have all thing that aren't related to a shader option
        defaultCase();

        //search for the closest option
        for (int i = option; i >= 0; i--) {

            String optionName =
                    ShaderPatcher.shaderOption.option.get(i).optionName;

            Runnable setBehavior = behaviors.get(optionName);

            if (setBehavior != null) {
                setBehavior.run();
                break;
            }
        }

        //clone to avoid shared ItemStack
        return item.clone();
    }

    //overwrite to put the default case, so all meta data that aren't changing depending on shader option
    protected void defaultCase() {}

    //get the item
    public ItemStack getItem() {
        return item.clone();
    }
}
