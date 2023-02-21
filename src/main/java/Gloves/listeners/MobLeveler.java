package Gloves.listeners;

import Gloves.glovedplugin;
import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.skills.Skills;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

public class MobLeveler implements Listener {


    private final glovedplugin plugin;

    public MobLeveler(glovedplugin plugin) {
        this.plugin = plugin;
        getLogger().info("MobLeveler loaded!");
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        EntityType type = entity.getType();
        Double CheckRange = 20.0;
        Player player = entity.getKiller();
        int playerSkillLevel = AureliumAPI.getSkillLevel(player, Skills.FIGHTING);
        int mobLevel = (playerSkillLevel) + 1;
        // Check if entity is Zombie
        if (type == EntityType.ZOMBIE) {
            entity.setMaxHealth(entity.getMaxHealth() + (mobLevel * 2));
            entity.setHealth(entity.getMaxHealth());
            entity.setCustomName("&f[&8Lvl" + mobLevel + "&f] " + type);
        }

        List<Entity> NearbyPlayers = event.getEntity().getNearbyEntities(20, 20, 20).stream().();
        if (NearbyPlayers.stream().filter(EntityType.PLAYER)) {

        }
    }

    }

}