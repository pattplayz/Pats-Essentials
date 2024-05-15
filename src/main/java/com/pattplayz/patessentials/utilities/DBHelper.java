package com.pattplayz.patessentials.utilities;

import com.pattplayz.patessentials.PatEssentials;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class DBHelper {
    private static FileConfiguration _settings;
    private static PatEssentials plugin;
    private static File db;
    private String dbURL;
    private int dbPort;
    private String dbUser;
    private String dbPassword;
    private String dbTablePrefix;

    public DBHelper(PatEssentials instance) throws IOException, InvalidConfigurationException {
        plugin = instance;
        File f = new File(plugin.getDataFolder(), "settings.yml");
        _settings = new YamlConfiguration();
        _settings.load(f);

        if(_settings.getString("database").equals("sqlite")) {
            dbURL = "jdbc:sqlite:" + f.getAbsolutePath();
        } else if(_settings.getString("database").equals("mysql")) {
            dbURL = _settings.getString("database.host").toLowerCase();
            dbPort = _settings.getInt("database.port");
            dbUser = _settings.getString("database.user");
            dbPassword = _settings.getString("database.password");
            dbTablePrefix = _settings.getString("database.tablePrefix");
        }
    }

    public void open() {

    }

    public void execute(String sql) throws SQLException {

    }

    public String[] query(String sql) throws SQLException {
        return null;
    }

    public void close() {

    }
}
