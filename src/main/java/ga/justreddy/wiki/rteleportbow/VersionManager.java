package ga.justreddy.wiki.rteleportbow;

import org.bukkit.Bukkit;

public interface VersionManager {

    boolean mc18 = Bukkit.getServer().getVersion().contains("1.8.8");
    boolean mc19 = Bukkit.getServer().getVersion().contains("1.9.4");
    boolean mc10 = Bukkit.getServer().getVersion().contains("1.10.2");
    boolean mc11 = Bukkit.getServer().getVersion().contains("1.11.2");
    boolean mc12 = Bukkit.getServer().getVersion().contains("1.12.2");
    boolean mc13 = Bukkit.getServer().getVersion().contains("1.13.2");
    boolean mc14 = Bukkit.getServer().getVersion().contains("1.14.4");
    boolean mc15 = Bukkit.getServer().getVersion().contains("1.15.2");
    boolean mc16 = Bukkit.getServer().getVersion().contains("1.16.5");


}
