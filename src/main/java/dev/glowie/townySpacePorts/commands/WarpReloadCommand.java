package dev.glowie.townySpacePorts.commands;

import dev.glowie.townySpacePorts.TownySpacePorts;
import dev.glowie.townySpacePorts.utils.MessagingUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.hasPermission("townyspaceports.reload")) {
                TownySpacePorts.instance.reloadConfig();
                MessagingUtils.messagePlayer(p, "Config reloaded.");
                return true;
            } else {
                MessagingUtils.alertPlayer(p, "No permission.");
                return true;
            }
        } else {
            Bukkit.getLogger().info("RELOADED TOWNY SPACE PORTS");
            TownySpacePorts.instance.reloadConfig();
        }

        return false;
    }
}
