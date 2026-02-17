package me.titruc.shaderPatcher.message;

//libs
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.persistence.PersistentDataType;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.bukkit.util.Transformation;
import java.util.HashMap;
import java.util.Map;

//plugin files
import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.ShaderPatcher;

public class TextDisplayManager {

    //all existing warning text display, store like that ; owner_uuid : text_display
    private static final Map<String, TextDisplay> displays = new HashMap<>();

    //namespace for persistent data
    static final private NamespacedKey key = new NamespacedKey(ShaderPatcher.singleton, "shaderWarning");
    static final private NamespacedKey ownerKey = new NamespacedKey(ShaderPatcher.singleton, "shaderWarningPlayerUUID");

    //summon the text display on a player
    public static void putTextDisplayAsPassenger(Player player) {
        //get world
        var world = player.getWorld();
        //get location of the player eyes
        Location eyeLoc = player.getEyeLocation();
        //summon a text display
        TextDisplay textDisplay = (TextDisplay) world.spawnEntity(eyeLoc, EntityType.TEXT_DISPLAY);

        //shadow
        textDisplay.setShadowStrength(0);
        textDisplay.setShadowRadius(0);

        //background
        textDisplay.setBackgroundColor(Color.fromARGB(0, 0, 0, 0));
        textDisplay.setDefaultBackground(false);
        textDisplay.setSeeThrough(false);

        //billboard
        textDisplay.setBillboard(Display.Billboard.CENTER);

        //not necessary but just in case
        textDisplay.setGravity(false);
        textDisplay.setInvulnerable(true);

        //prepare scale and rotation
        Vector3f translation = new Vector3f(0f, -0.25f, -0.5f);
        Quaternionf leftRotation = new Quaternionf();
        Vector3f scale = new Vector3f(0.2f, 0.2f, 0.2f);
        Quaternionf rightRotation = new Quaternionf();

        //set transform
        Transformation transformation = new Transformation(translation, leftRotation, scale, rightRotation);
        textDisplay.setTransformation(transformation);

        //set view range
        textDisplay.setViewRange((float)ConfigHandler.warningDisplayRange);

        //set persistent data to get it later
        textDisplay.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);
        textDisplay.getPersistentDataContainer().set(ownerKey, PersistentDataType.STRING, player.getUniqueId().toString());

        //register the warning text display to get it later easly
        displays.put(player.getUniqueId().toString(), textDisplay);

        //start countdown
        setCountdown(textDisplay, ConfigHandler.warningDisplayTime);

        //set it passager to the player
        player.addPassenger(textDisplay);

    }

    //recursive func to delete the warning sign and display the remaining display time
    public static void setCountdown(TextDisplay textDisplay, int time)
    {
        //check if the text exist
        if (!textDisplay.isValid() || textDisplay.isDead()) return;

        //remove it when the countdown is 0
        if (time < 0) {
            textDisplay.remove();
            return;
        }

        //change text to display remaining display time
        Component message = Component.text(
                String.format(ConfigHandler.warningText, ConfigHandler.getShaderOptionCommandName) + "\n" + time,
                TextColor.fromHexString(ConfigHandler.warningInvisibleColor)
        );
        textDisplay.text(message);

        //recall this func but with the time -1
        Bukkit.getScheduler().runTaskLater(ShaderPatcher.singleton, () -> {
            setCountdown(textDisplay, time - 1);
        }, 20L);
    }

    //remove a warning sign depending on a player UUID
    public static void removeFromPlayer(String uuid)
    {
        //remove the text display reference
        TextDisplay textDisplay = displays.remove(uuid);

        //remove the actual entity
        if (textDisplay != null && textDisplay.isValid() && !textDisplay.isDead()) {
            textDisplay.remove();
        }
    }
}
