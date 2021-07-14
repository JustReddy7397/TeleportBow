package vortexcraft.net.events;
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
import vortexcraft.net.TeleportBow;

import java.util.List;

public class OnWorldChange implements Listener {

    @EventHandler
    public void onChange(PlayerChangedWorldEvent e){
        Player p = e.getPlayer();
        List<String> worlds = TeleportBow.getInstance().getConfig().getStringList("disabled-worlds");
        if(worlds.contains(p.getWorld().getName())){
            if(TeleportBow.bow.contains(p)){
                ItemStack air = new ItemStack(Material.AIR);
                TeleportBow.getInstance().removeBow(p);
            }
        }else{
            TeleportBow.getInstance().giveBow(p);
        }
    }

}
