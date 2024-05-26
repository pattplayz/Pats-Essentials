package com.pattplayz.patessentials.utilities;

import com.pattplayz.patessentials.PatEssentials;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static FileConfiguration _settings;
    private static PatEssentials plugin;
    private static File db;
    private String dbURL;
    private int dbPort;
    private String dbUser;
    private String dbPassword;
    private String dbTablePrefix;
    private Connection conn = null;

    public DBHelper(PatEssentials instance) throws IOException, InvalidConfigurationException {
        plugin = instance;
        File f = new File(plugin.getDataFolder(), "settings.yml");
        _settings = new YamlConfiguration();
        _settings.load(f);

        if(_settings.getString("database").equals("sqlite")) {
            dbURL = "jdbc:sqlite:" + db.getAbsolutePath();
        } else if(_settings.getString("database").equals("mysql")) {
            dbURL = _settings.getString("database.host").toLowerCase();
            dbPort = _settings.getInt("database.port");
            dbUser = _settings.getString("database.user");
            dbPassword = _settings.getString("database.password");
            dbTablePrefix = _settings.getString("database.tablePrefix");
        }
    }

    public void open() {
        try {
            if(_settings.getString("database").equals("sqlite"))
                conn = DriverManager.getConnection(dbURL);
            else if(_settings.getString("database").equals("mysql"))
                conn = DriverManager.getConnection(dbURL + ":" + dbPort, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(String sql) throws SQLException {
        if(conn == null) return;

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.executeUpdate();

        ps.close();
    }

    public List<String> query(String sql) throws SQLException {
        if(conn == null) return null;

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<String> list = new ArrayList<>();
        while(rs.next()) {
            list.add(rs.getString(1));
        }
        rs.close();
        ps.close();
        return list;
    }

    public void close() throws SQLException {
        if(conn != null) {
            conn.close();
        }
    }
}
