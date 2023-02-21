package Gloves.listeners;

import Gloves.glovedplugin;
import com.archyx.aureliumskills.api.AureliumAPI;
import com.archyx.aureliumskills.skills.Skills;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import static org.apache.logging.log4j.LogManager.getLogger;

public class MobLeveler implements Listener {


    public MobLeveler(glovedplugin plugin) {
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

    }

}