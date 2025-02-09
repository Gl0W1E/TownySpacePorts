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
            return List.of("NONE1");
        }

        Player p = (Player) sender;
        if (SpaceUtils.findPortFromPlayer(p) == null) {
            return List.of("NONE2");
        }

        List<String> allowedWorlds = ConfigUtils.getAllowedWorlds(SpaceUtils.findPortFromPlayer(p));

        if (args.length == 1) {
            return allowedWorlds;
        }

        List<String> allowedPorts = ConfigUtils.getAllowedPorts(SpaceUtils.findPortFromPlayer(p), args[0]);

        if (args.length != 2) {
            return allowedPorts;
        }

        return allowedPorts;
    }
}
