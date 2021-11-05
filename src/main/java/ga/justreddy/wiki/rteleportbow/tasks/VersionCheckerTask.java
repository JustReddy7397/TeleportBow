package ga.justreddy.wiki.rteleportbow.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class VersionCheckerTask {

    private final JavaPlugin plugin;
    private final int id;

    public VersionCheckerTask(JavaPlugin plugin, int id){
        this.plugin = plugin;
        this.id = id;
    }

    public void getVersion(final Consumer<String> consumer){
            try(InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + id).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if(scanner.hasNext()){
                    consumer.accept(scanner.next());
                }
            }catch (IOException ex){
                this.plugin.getLogger().warning("[TeleprotBow] Cannot look for updates: " + ex.getMessage());
            }
    }
}
