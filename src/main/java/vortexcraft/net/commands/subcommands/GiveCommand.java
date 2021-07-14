package vortexcraft.net.commands.subcommands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import vortexcraft.net.TeleportBow;
import vortexcraft.net.commands.SubCommand;
import vortexcraft.net.events.TeleportEvent;

import java.util.ArrayList;
import java.util.List;

public class GiveCommand extends SubCommand {
    @Override
    public String getName() {
        return "give";
    }

    @Override
    public String getDescription() {
        return "Gives you the bow";
    }

    @Override
    public String getSyntax() {
        return "/tpbow give";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("tpbow.spawnbow")) {

            TeleportBow.getInstance().giveBow(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', TeleportBow.getInstance().getConfig().getString("messages.spawned")));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', TeleportBow.getInstance().getConfig().getString("messages.no-perms")));
        }
    }

    }

