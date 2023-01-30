package Gloves;

import Gloves.DamageIndicator.command.CommandHandler;
import Gloves.DamageIndicator.hook.HookManager;
import Gloves.DamageIndicator.listener.BloodListener;
import Gloves.DamageIndicator.listener.DamageIndicatorListener;
import Gloves.DamageIndicator.storage.SimpleStorageProvider;
import Gloves.DamageIndicator.storage.StorageProvider;
import Gloves.DamageIndicator.util.CompatUtil;
import Gloves.commands.Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
public class glovedplugin extends JavaPlugin {

    private DamageIndicatorListener damageIndicatorListener;
    private BloodListener bloodListener;
    private StorageProvider storageProvider = null;

    public void reload() {
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


    @Override
    public void onEnable() {
        // Plugin startup logic
        reload();
        Objects.requireNonNull(getCommand("damageindicator")).setExecutor(new CommandHandler(this));
        Objects.requireNonNull(getCommand("menu")).setExecutor(new Menu(this));
        startTasks();
        CompatUtil.onEnable();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListener here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
            getLogger().info("PlaceholderAPI is installed!");
            Bukkit.getPluginManager().registerEvents(bloodListener, this);
            Bukkit.getPluginManager().registerEvents(damageIndicatorListener, this);
            getLogger().info("Events registered!");
            getLogger().info("");
            getLogger().info("============================");
            getLogger().info("GlovedPlugin Enabled!");
            getLogger().info("");
            getLogger().info("============================");

        } else {
            /*
             * We inform about the fact that PlaceholderAPI isn't installed and then
             * disable this plugin to prevent issues.
             */
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
            getLogger().warning("GlovedPlugin Disabled!");
        }


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