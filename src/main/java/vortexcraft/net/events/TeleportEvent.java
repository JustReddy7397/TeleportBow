package vortexcraft.net.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import vortexcraft.net.TeleportBow;

import java.util.List;

public class TeleportEvent implements Listener {
    TeleportBow plugin;

    public TeleportEvent(TeleportBow plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBowShoot(ProjectileHitEvent e) {
        try {
            Player player = (Player) e.getEntity().getShooter();
            List<String> worlds = TeleportBow.getInstance().getConfig().getStringList("disabled-worlds");
            if (worlds.contains(player.getWorld().getName())) {
                if (TeleportBow.bow.contains(player)) {
                    return;
                }
            }
            if (player.getItemInHand().getType().equals(Material.BOW) && player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("bow-name")))) {
                Location location = e.getEntity().getLocation();
                e.getEntity().remove();
                location.setYaw(player.getLocation().getYaw());
                location.setPitch(player.getLocation().getPitch());
                player.teleport(location);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.plugin.getConfig().getString("teleported-message")));

            }
            }catch(ClassCastException | NullPointerException ignore){
            }

    }
}