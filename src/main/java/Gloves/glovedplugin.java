package Gloves;

import Gloves.commands.Fly;
import Gloves.commands.Menu;
import org.bukkit.plugin.java.JavaPlugin;

public final class glovedplugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("GlovedPlugin Started!");

        getCommand("fly").setExecutor(new Fly());
        getCommand("menu").setExecutor(new Menu(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("GlovedPlugin Stopped!");

    }
}
