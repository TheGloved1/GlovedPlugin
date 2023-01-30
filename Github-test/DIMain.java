package Gloves.DamageIndicator;

import Gloves.DamageIndicator.command.CommandHandler;
import Gloves.DamageIndicator.hook.HookManager;
import Gloves.DamageIndicator.listener.BloodListener;
import Gloves.DamageIndicator.listener.DamageIndicatorListener;
import Gloves.DamageIndicator.storage.SimpleStorageProvider;
import Gloves.DamageIndicator.storage.StorageProvider;
import Gloves.DamageIndicator.util.CompatUtil;
import Gloves.DamageIndicator.util.ConfigUpdateHandler;
import Gloves.commands.Menu;
import com.comphenix.protocol.events.PacketListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class DIMain extends JavaPlugin {

    private DamageIndicatorListener damageIndicatorListener;
    private BloodListener bloodListener;
    private StorageProvider storageProvider = null;

    public void reload() {
        new ConfigUpdateHandler(this).updateConfig();
        reloadConfig();
        if (storageProvider == null) {
            storageProvider = new SimpleStorageProvider();
        }
        if (damageIndicatorListener != null) {
            damageIndicatorListener.getArmorStands().forEach((armor, time) -> armor.remove());
            damageIndicatorListener.getArmorStands().clear();
            damageIndicatorListener.reload();
        } else if (getConfig().getBoolean("Damage Indicator.Enabled")) {
            Bukkit.getPluginManager().registerEvents(damageIndicatorListener = new DamageIndicatorListener(this, new HookManager(this)), this);
        }
        if (bloodListener != null) {
            bloodListener.getBloodItems().forEach((item, time) -> item.remove());
            bloodListener.getBloodItems().clear();
            bloodListener.reload();
        } else if (getConfig().getBoolean("Blood.Enabled")) {
            Bukkit.getPluginManager().registerEvents(bloodListener = new BloodListener(this), this);
        }
    }

    private ProtocolManager protocolManager;
    @Override
    public void onEnable() {
        reload();
        Objects.requireNonNull(getCommand("damageindicator")).setExecutor(new CommandHandler(this));
        startTasks();
        CompatUtil.onEnable();
        getLogger().info("GlovedPlugin Enabled!");
        protocolManager = ProtocolLibrary.getProtocolManager();
        protocolManager.addPacketListener((PacketListener) new DamageIndicatorListener(this, new HookManager(this)));
    }

    @Override
    public void onDisable() {
        if (damageIndicatorListener != null) {
            damageIndicatorListener.getArmorStands().forEach((armor, time) -> armor.remove());
        }
        if (bloodListener != null) {
            bloodListener.getBloodItems().forEach((item, time) -> item.remove());
        }
    }

    private void startTasks() {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            if (damageIndicatorListener != null) {
                Iterator<Map.Entry<ArmorStand, Long>> asit = damageIndicatorListener.getArmorStands().entrySet().iterator();
                while (asit.hasNext()) {
                    Map.Entry<ArmorStand, Long> ent = asit.next();
                    if (ent.getValue() + 1500 <= System.currentTimeMillis()) {
                        ent.getKey().remove();
                        asit.remove();
                    } else {
                        ent.getKey().teleport(ent.getKey().getLocation().clone().add(0.0, 0.07, 0.0));
                    }
                }
            }
            if (bloodListener != null) {
                Iterator<Map.Entry<Item, Long>> bit = bloodListener.getBloodItems().entrySet().iterator();
                while (bit.hasNext()) {
                    Map.Entry<Item, Long> ent = bit.next();
                    if (ent.getValue() + 2000 <= System.currentTimeMillis()) {
                        ent.getKey().remove();
                        bit.remove();
                    }
                }
            }
        }, 0, 1);
    }

    public boolean isDamageIndicator(Entity entity) {
        return isDamageIndicator(entity, true);
    }

    public boolean isDamageIndicator(Entity entity, boolean strict) {
        if (!(entity instanceof ArmorStand as)) {
            return false;
        }
        return as.hasMetadata("GlovedPlugin-DamageIndicator") || !strict && (as.isMarker() && !as.isVisible() && as.isCustomNameVisible() && !as.hasGravity());
    }

    public StorageProvider getStorageProvider() {
        return storageProvider;
    }

}