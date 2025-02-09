package dev.glowie.townySpacePorts.listeners;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.object.TownBlock;
import dev.glowie.townySpacePorts.utils.ConfigUtils;
import dev.glowie.townySpacePorts.utils.SpaceUtils;
import io.github.townyadvanced.flagwar.events.CellWonEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FlagWarListener implements Listener {

    @EventHandler (ignoreCancelled = true)
    public void onConquer(CellWonEvent event) {

        TownBlock tblck = TownyAPI.getInstance().getTownBlock(event.getCellUnderAttack().getFlagBaseBlock().getLocation());

        if (!SpaceUtils.isPort(tblck)) {
            return;
        }

        ConfigurationSection port = SpaceUtils.findPortFromPlot(tblck);
        String portName = SpaceUtils.findPortNameFromPlot(tblck);

        if (ConfigUtils.conquersRestSameName(port)) {
            for (String world : ConfigUtils.getConfig().getConfigurationSection(portName).getKeys(false)) {
                Location loc = ConfigUtils.getPortLocationXYZ(ConfigUtils.getPortSettings(portName, world), Bukkit.getWorld(world));
                if (!TownyAPI.getInstance().isWilderness(loc)) {
                    TownyAPI.getInstance().getTownBlock(loc).setTown(tblck.getTownOrNull());
                }
            }
        }
    }
}
