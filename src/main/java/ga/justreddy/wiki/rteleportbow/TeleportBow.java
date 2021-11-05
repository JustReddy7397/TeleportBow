package ga.justreddy.wiki.rteleportbow;

import ga.justreddy.wiki.rteleportbow.metrics.Metrics;
import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import ga.justreddy.wiki.rteleportbow.commands.CommandManager;
import ga.justreddy.wiki.rteleportbow.events.OnWorldChange;
import ga.justreddy.wiki.rteleportbow.events.SpawnEvent;
import ga.justreddy.wiki.rteleportbow.events.TeleportEvent;
import ga.justreddy.wiki.rteleportbow.tasks.VersionCheckerTask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class TeleportBow extends JavaPlugin implements VersionManager {
    private static TeleportBow instance;

    public static ArrayList<Player> bow = new ArrayList<>();

    public static TeleportBow getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        int id = 79733;
        getServer().getConsoleSender().sendMessage(Utils.c("&a=-=-=-=-TeleportBow-=-=-=-="));
        getServer().getConsoleSender().sendMessage(Utils.c("&bMade by: " + getDescription().getAuthors()));
        getServer().getConsoleSender().sendMessage(Utils.c("&bVersion: " + getDescription().getVersion()));
        getServer().getConsoleSender().sendMessage(Utils.c("&bChecking for updates..."));
        new VersionCheckerTask(this, id).getVersion(v -> {
            if(getDescription().getVersion().equalsIgnoreCase(v)){
                getServer().getConsoleSender().sendMessage(Utils.c("&aLooks like there isn't a new update available!"));
            }else{
                getServer().getConsoleSender().sendMessage(Utils.c("&cLooks like there is a new update available!"));
                getServer().getConsoleSender().sendMessage(Utils.c("&Link: https://www.spigotmc.org/resources/teleportbow.79733/"));
            }
        });
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getServer().getConsoleSender().sendMessage(Utils.c("&aPlaceholderAPI found! Using it!"));
        }else{
            getServer().getConsoleSender().sendMessage(Utils.c("&cPlaceholderAPI not found! Not using it!"));
        }
            getServer().getConsoleSender().sendMessage(Utils.c("&a=-=-=-=-TeleportBow-=-=-=-="));
        new Metrics(this, id);

        getServer().getPluginManager().registerEvents(new TeleportEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new SpawnEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new OnWorldChange(), this);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        getCommand("tpbow").setExecutor(new CommandManager());
    }


    public void giveBow(Player player) {
        ItemStack teleport_bow = new ItemStack(Material.BOW);
        ItemMeta meta = teleport_bow.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("bow-name")));
        List<String> lore = getConfig().getStringList("bow-description").stream().map(a -> PlaceholderAPI.setPlaceholders(player, a)).collect(Collectors.toList());
        meta.setLore(Utils.cList(lore));
        if (mc18) {
            meta.spigot().setUnbreakable(true);
        } else {
            meta.setUnbreakable(true);
        }
        teleport_bow.setItemMeta(meta);
        teleport_bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta ameta = arrow.getItemMeta();
        ameta.setDisplayName(ChatColor.GREEN + "Arrow");
        arrow.setItemMeta(ameta);

        Inventory inv = player.getInventory();
        inv.setItem(9, arrow);
        inv.remove(teleport_bow);
        inv.setItem(getConfig().getInt("bow-slot"), teleport_bow);
    }

    public void removeBow(Player p) {
        ItemStack teleport_bow = new ItemStack(Material.BOW);
        ItemMeta meta = teleport_bow.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("bow-name")));
        List<String> lore = getConfig().getStringList("bow-description").stream().map(a -> PlaceholderAPI.setPlaceholders(p, a)).collect(Collectors.toList());
        meta.setLore(Utils.cList(lore));
        if (mc18) {
            meta.spigot().setUnbreakable(true);
        } else {
            meta.setUnbreakable(true);
        }
        teleport_bow.setItemMeta(meta);
        teleport_bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        Inventory inv = p.getInventory();
        inv.remove(teleport_bow);
        ItemStack arrow = new ItemStack(Material.ARROW);
        ItemMeta ameta = arrow.getItemMeta();
        ameta.setDisplayName(ChatColor.GREEN + "Arrow");
        arrow.setItemMeta(ameta);
        inv.remove(arrow);
    }

}