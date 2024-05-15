package com.pattplayz.patessentials.utilities;

import com.pattplayz.patessentials.PatEssentials;

public class MSGHelper {
    private static PatEssentials plugin;
    public MSGHelper(PatEssentials instance) {
        plugin = instance;
    }

    public static String formatString(String message)
    {
        message = message.replaceAll("&([0-9a-f])", "\u00A7$1");
        message = message.replaceAll("&([k-o])", "\u00A7$1");
        return message;
    }

    public static String formatString(String message, String pName)
    {
        message = message.replaceAll("&([0-9a-f])", "\u00A7$1");
        message = message.replaceAll("&([k-o])", "\u00A7$1");
        message = message.replaceAll("%p", pName);
        return message;
    }

}
