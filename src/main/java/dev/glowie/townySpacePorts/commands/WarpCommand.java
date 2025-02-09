package dev.glowie.townySpacePorts.commands;

import com.palmergames.bukkit.towny.confirmations.Confirmation;
import dev.glowie.townySpacePorts.utils.ConfigUtils;
import dev.glowie.townySpacePorts.utils.MessagingUtils;
import dev.glowie.townySpacePorts.utils.SpaceUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }

        Player p = (Player) sender;

        if (args.length != 2) {
            MessagingUtils.errorPlayer(p , "Correct usage: /spwarp <world> <name>");
            return true;
        }

        String worldName = args[0];
        String portName = args[1];
        ConfigurationSection port = ConfigUtils.getPortSettings(portName, worldName);

        ConfigurationSection playerPort = SpaceUtils.findPortFromPlayer(p);
        //String playerWorldName = p.getWorld().toString();
        //String playerPortName = SpaceUtils.findPortNameFromPlayer(p);

        if (playerPort == null) {
            MessagingUtils.errorPlayer(p, "You are not nearby any space port.");
            return true;
        }


        if (!ConfigUtils.getAllowedWorlds(playerPort).contains(worldName)) {
            MessagingUtils.errorPlayer(p, "Not allowed to travel to this world from here.");
            return true;
        }

        if (!ConfigUtils.getAllowedPorts(playerPort, worldName).contains(portName)) {
            MessagingUtils.errorPlayer(p, "Not allowed to travel to this space port from here.");
            return true;
        }

        if (!ConfigUtils.canPlayerTeleportToPort(port, p, Bukkit.getWorld(worldName))) {
            MessagingUtils.errorPlayer(p , "The space port owner does not allow you to travel there.");
            return true;
        }

        SpaceUtils.teleportPlayer(port, p, Bukkit.getWorld(worldName));

        return true;
    }
}
