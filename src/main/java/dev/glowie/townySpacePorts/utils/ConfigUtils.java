package dev.glowie.townySpacePorts.utils;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.utils.CombatUtil;
import dev.glowie.townySpacePorts.TownySpacePorts;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.management.relation.Relation;
import java.util.List;

public class ConfigUtils {

    public static FileConfiguration getConfig() {
        return TownySpacePorts.instance.getConfig();
    }

    public static ConfigurationSection getPortSettings(String name, String world) {
        if (getConfig().getConfigurationSection(name) != null && getConfig().getConfigurationSection(name).getConfigurationSection(world) != null) {
            return getConfig().getConfigurationSection(name).getConfigurationSection(world);
        }
        return null;
    }

    public static Location getPortLocationXYZ(ConfigurationSection portSettings, World world) {
        int x, y, z;
        int array[];
        array = portSettings.getIntegerList("location").stream().mapToInt(i->i).toArray();
        x = array[0]; y = array[1]; z = array[2];
        return new Location(world, x, y, z);
    }

    public static List<String> getAllowedWorlds(ConfigurationSection portSettings) {
        return portSettings.getConfigurationSection("allowed_ports").getKeys(false).stream().toList();
    }

    public static List<String> getAllowedPorts(ConfigurationSection portSettings, String world) {
        return portSettings.getConfigurationSection("allowed_ports").getStringList(world);
    }

    public static boolean canPlayerTeleportToPort(ConfigurationSection portSettings, Player player, World world) {
        Location l = getPortLocationXYZ(portSettings, world);
        if (TownyAPI.getInstance().isWilderness(l)) {
            return false;
        }
        String relation = "OUTSIDER";
        Town tPort = TownyAPI.getInstance().getTown(l);
        Town tPlayer = TownyAPI.getInstance().getTown(player);
        if (tPlayer != null) {
            if (CombatUtil.isAlly(tPlayer, tPort)) {
                relation = "ALLY";
            }
            if (CombatUtil.isSameNation(tPlayer, tPort)) {
                relation = "NATION";
            }
            if (CombatUtil.isSameTown(tPlayer, tPort)) {
                relation = "TOWN";
            }
            if (CombatUtil.isEnemy(tPort, tPlayer) || CombatUtil.isEnemy(tPlayer, tPort)) {
                relation = "ENEMY";
            }
        }
        return portSettings.getStringList("allowed_relations").contains(relation);

    }


    public static int getWarmupSeconds(ConfigurationSection portSettings) {
        return portSettings.getInt("warmup");
    }

    public static int getCooldownSeconds(ConfigurationSection portSettings) {
        return portSettings.getInt("cooldown");
    }

    public static boolean conquersRestSameName(ConfigurationSection portSettings) {
        return portSettings.getBoolean("conquer_rest");
    }

}
