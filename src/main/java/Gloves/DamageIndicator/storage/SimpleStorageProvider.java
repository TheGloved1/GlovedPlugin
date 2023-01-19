package Gloves.DamageIndicator.storage;

import Gloves.glovedplugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Beelzebu
 */
public class SimpleStorageProvider implements StorageProvider {

    private final File dataFile = new File(glovedplugin.getPlugin(glovedplugin.class).getDataFolder(), "data.yml");
    private final FileConfiguration data;

    public SimpleStorageProvider() {
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(SimpleStorageProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
    }

    @Override
    public boolean showArmorStand(Player player) {
        return data.getBoolean(player.getUniqueId().toString(), true);
    }

    @Override
    public void setShowArmorStand(Player player, boolean status) {
        data.set(player.getUniqueId().toString(), status);
        save();
    }

    private void save() {
        try {
            data.save(dataFile);
        } catch (IOException ex) {
            Logger.getLogger(SimpleStorageProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
