package Gloves;

import Gloves.commands.Fly;
import Gloves.commands.Menu;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class glovedplugin extends JavaPlugin {

    private ProtocolManager protocolManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        protocolManager = ProtocolLibrary.getProtocolManager();
        getLogger().info("GlovedPlugin Enabled!");

        getCommand("fly").setExecutor(new Fly());
        getCommand("menu").setExecutor(new Menu(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("GlovedPlugin Disabled!");

    }
}
