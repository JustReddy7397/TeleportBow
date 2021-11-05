package ga.justreddy.wiki.rteleportbow.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ga.justreddy.wiki.rteleportbow.TeleportBow;

import java.util.List;

public class SpawnEvent implements Listener {
    TeleportBow plugin;

    public SpawnEvent(TeleportBow plugin) {
        this.plugin = plugin;
    }



    @EventHandler
    public void onJoinEvent(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        List<String> worlds = TeleportBow.getInstance().getConfig().getStringList("disabled-worlds");

        if (TeleportBow.getInstance().getConfig().getBoolean("give-on-join")) {
            if(worlds.contains(p.getWorld().getName())){
                TeleportBow.getInstance().removeBow(p);
            }else {
                TeleportBow.getInstance().giveBow(p);
            }
            TeleportBow.bow.add(p);
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        TeleportBow.getInstance().removeBow(p);

    }

}
