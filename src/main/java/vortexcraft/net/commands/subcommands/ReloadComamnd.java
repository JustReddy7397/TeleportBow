package vortexcraft.net.commands.subcommands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import vortexcraft.net.TeleportBow;
import vortexcraft.net.commands.SubCommand;

public class ReloadComamnd extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the config.yml";
    }

    @Override
    public String getSyntax() {
        return "/tpbow reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if(!player.hasPermission("tpbow.reload")){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', TeleportBow.getInstance().getConfig().getString("messages.no-perms")));
        }
        TeleportBow.getInstance().reloadConfig();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', TeleportBow.getInstance().getConfig().getString("messages.reloaded")));
    }
}
