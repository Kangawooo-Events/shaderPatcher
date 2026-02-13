package me.titruc.shaderPatcher.message;

import me.titruc.shaderPatcher.ConfigHandler;
import me.titruc.shaderPatcher.ShaderPatcher;
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

public class TextDisplayManager {

    private static final Map<String, TextDisplay> displays = new HashMap<>();

    static final private NamespacedKey key = new NamespacedKey(ShaderPatcher.singleton, "shaderWarning");
    static final private NamespacedKey ownerKey = new NamespacedKey(ShaderPatcher.singleton, "shaderWarningPlayerUUID");

    public static void putTextDisplayAsPassenger(Player player) {
        var world = player.getWorld();

        Location eyeLoc = player.getEyeLocation();

        TextDisplay textDisplay = (TextDisplay) world.spawnEntity(eyeLoc, EntityType.TEXT_DISPLAY);

        textDisplay.setShadowStrength(0);
        textDisplay.setShadowRadius(0);
        textDisplay.setViewRange(1f);
        textDisplay.setDefaultBackground(false);
        textDisplay.setSeeThrough(false);
        textDisplay.setGravity(false);
        textDisplay.setInvulnerable(true);

        Vector3f translation = new Vector3f(0f, -0.25f, -0.5f);
        Quaternionf leftRotation = new Quaternionf();
        Vector3f scale = new Vector3f(0.2f, 0.2f, 0.2f);
        Quaternionf rightRotation = new Quaternionf();

        Transformation transformation = new Transformation(translation, leftRotation, scale, rightRotation);
        textDisplay.setTransformation(transformation);

        textDisplay.setBillboard(Display.Billboard.CENTER);

        textDisplay.setBackgroundColor(Color.fromARGB(0, 0, 0, 0));

        textDisplay.setViewRange((float)ConfigHandler.warningDisplayRange);


        textDisplay.getPersistentDataContainer().set(key, PersistentDataType.BOOLEAN, true);
        textDisplay.getPersistentDataContainer().set(ownerKey, PersistentDataType.STRING, player.getUniqueId().toString());

        displays.put(player.getUniqueId().toString(), textDisplay);

        setCountdown(textDisplay, ConfigHandler.warningDisplayTime);

        player.addPassenger(textDisplay);

    }

    public static void setCountdown(TextDisplay textDisplay, int time)
    {
        if (!textDisplay.isValid() || textDisplay.isDead()) return;

        if (time < 0) {
            textDisplay.remove();
            return;
        }

        Component message = Component.text(
                String.format(ConfigHandler.warningText, ConfigHandler.getShaderOptionCommandName) + "\n" + time,
                TextColor.fromHexString(ConfigHandler.warningInvisibleColor)
        );
        textDisplay.text(message);

        Bukkit.getScheduler().runTaskLater(ShaderPatcher.singleton, () -> {
            setCountdown(textDisplay, time - 1);
        }, 20L);
    }

    public static void removeFromPlayer(String uuid)
    {

        TextDisplay textDisplay = displays.remove(uuid);

        if (textDisplay != null && textDisplay.isValid() && !textDisplay.isDead()) {
            textDisplay.remove();
        }
    }
}
