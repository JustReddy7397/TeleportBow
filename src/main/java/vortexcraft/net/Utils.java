package vortexcraft.net;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String c(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static List<String> cList(List<String> input) {
        List<String> ret = new ArrayList<>();
        for (String line : input) ret.add(ChatColor.translateAlternateColorCodes('&', line));
        return ret;
    }
}
