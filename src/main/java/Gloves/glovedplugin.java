package Gloves;

import Gloves.commands.Fly;
import Gloves.commands.Menu;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class glovedplugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("GlovedPlugin Starting!");

        getCommand("fly").setExecutor(new Fly());
        getCommand("menu").setExecutor(new Menu(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("GlovedPlugin Stopping!");

    }
}
