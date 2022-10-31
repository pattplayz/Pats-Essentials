package com.pattplayz.patessentials;

import com.pattplayz.patessentials.commands.Commands;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PatEssentials extends JavaPlugin {

    public static final Logger console = Logger.getLogger("Pat's Essentials");
    File pluginDir = new File("plugins/PEssentials");

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        PluginDescriptionFile pdFile = this.getDescription();
        String ver = pdFile.getVersion();

        console.log(Level.INFO,"[PE] Starting Pat's Essentials v" + ver + "...");

        if(!pluginDir.exists()) {
            console.log(Level.INFO,"[PE] Performing first-time setup...");
            pluginDir.mkdir();
            console.log(Level.INFO,"[PE] First-time setup complete!");
        }

        File settings = new File(getDataFolder(), "settings.yml");
        if(!settings.exists()) {
            saveResource("settings.yml", false);
        }

        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        getCommand("").setExecutor(new Commands(this));
    }
}
