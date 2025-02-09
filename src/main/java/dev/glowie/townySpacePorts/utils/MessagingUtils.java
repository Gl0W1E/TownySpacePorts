package dev.glowie.townySpacePorts.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessagingUtils {

    public static String getPrefix() {
        return "§1[§eTownySpacePorts§1]§r ";
    }

    public static void alertPlayer(Player player, String message) {
        player.sendMessage(getPrefix() + "§4" + message);
    }

    public static void errorPlayer(Player player, String message) {
        player.sendMessage(getPrefix() + "§c" + message);
    }

    public static void messagePlayer(Player player, String message) {
        player.sendMessage(getPrefix() + "§a" + message);
    }

    public static void consoleOutput(String message) {
        Bukkit.getLogger().info(getPrefix() + "§7" + message);
    }

}
