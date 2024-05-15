package com.pattplayz.patessentials.commands;

import com.pattplayz.patessentials.PatEssentials;
import com.pattplayz.patessentials.utilities.MSGHelper;
import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Commands implements CommandExecutor {

    PatEssentials plugin;
    public Commands(PatEssentials instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Plugin info
        if(cmd.getName().equalsIgnoreCase("pe")) {
            sender.sendMessage(MSGHelper.formatString("&c[PE] Pat's Essentials v&b" + plugin.getDescription().getVersion()));
        }
        return false;
    }
}
