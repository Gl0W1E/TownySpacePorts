package dev.glowie.townySpacePorts;

import dev.glowie.townySpacePorts.commands.WarpCommand;
import dev.glowie.townySpacePorts.commands.WarpReloadCommand;
import dev.glowie.townySpacePorts.commands.WarpTabCompletion;
import dev.glowie.townySpacePorts.listeners.FlagWarListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TownySpacePorts extends JavaPlugin {

    public static TownySpacePorts instance;

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        getCommand("spwarp").setExecutor(new WarpCommand());
        getCommand("spwarp").setTabCompleter(new WarpTabCompletion());
        getServer().getPluginManager().registerEvents(new FlagWarListener(), this);
        getCommand("reloadspaceports").setExecutor(new WarpReloadCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        instance.getConfig().options().copyDefaults(false);
        instance.saveDefaultConfig();
    }
}
