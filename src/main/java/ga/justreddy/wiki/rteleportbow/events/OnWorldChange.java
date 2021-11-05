package ga.justreddy.wiki.rteleportbow.events;
/*
  Copyright © JustReddy 2021 | All Rights Reserved
  File | OnWorldChange
*/

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.ItemStack;
import ga.justreddy.wiki.rteleportbow.TeleportBow;

import java.util.List;

public class OnWorldChange implements Listener {

    @EventHandler
    public void onChange(PlayerChangedWorldEvent e){
        Player p = e.getPlayer();
        List<String> worlds = TeleportBow.getInstance().getConfig().getStringList("disabled-worlds");
        if(worlds.contains(p.getWorld().getName())){
            if(TeleportBow.bow.contains(p)){
                TeleportBow.getInstance().removeBow(p);
            }
        }else{
            TeleportBow.getInstance().giveBow(p);
        }
    }

}
