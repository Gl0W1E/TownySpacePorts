package dev.glowie.townySpacePorts.commands;

import dev.glowie.townySpacePorts.utils.ConfigUtils;
import dev.glowie.townySpacePorts.utils.SpaceUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WarpTabCompletion implements TabCompleter {



    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            return List.of("NONE");
        }

        Player p = (Player) sender;
        List<String> allowedWorlds = ConfigUtils.getAllowedWorlds(SpaceUtils.findPortFromPlayer(p));

        if (args.length == 0) {
            return allowedWorlds;
        }

        List<String> allowedPorts = ConfigUtils.getAllowedPorts(SpaceUtils.findPortFromPlayer(p), args[0]);

        if (args.length != 1) {
            return allowedPorts;
        }

        return List.of("NONE");
    }
}
