package me.titruc.shaderPatcher.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.bukkit.util.Transformation;

import java.awt.*;

public class TextDisplayManager {

    public static void putTextDisplayAsPassenger(Player player) {
        var world = player.getWorld();


        Location eyeLoc = player.getEyeLocation();

        TextDisplay textDisplay = (TextDisplay) world.spawnEntity(eyeLoc, EntityType.TEXT_DISPLAY);


        Component message = Component.text(
                "/!\\ Warning /!\\\nUnsupported shader detected\nChange settings for better experience",
                TextColor.fromHexString("#CD0C33")
        );
        textDisplay.text(message);

        textDisplay.setShadowStrength(0);
        textDisplay.setShadowRadius(0);
        textDisplay.setViewRange(1f);
        textDisplay.setDefaultBackground(false);
        textDisplay.setSeeThrough(false);
        textDisplay.setGravity(false);
        textDisplay.setInvulnerable(true);


        player.addPassenger(textDisplay);


        Vector3f translation = new Vector3f(0f, -0.25f, -0.5f);
        Quaternionf leftRotation = new Quaternionf();
        Vector3f scale = new Vector3f(0.2f, 0.2f, 0.2f);
        Quaternionf rightRotation = new Quaternionf();

        Transformation transformation = new Transformation(translation, leftRotation, scale, rightRotation);
        textDisplay.setTransformation(transformation);

        textDisplay.setBillboard(Display.Billboard.CENTER);

        textDisplay.setBackgroundColor(org.bukkit.Color.fromARGB(0, 0, 0, 0));

        textDisplay.setViewRange(0.005F);
    }
}
