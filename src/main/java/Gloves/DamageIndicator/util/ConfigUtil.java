package Gloves.DamageIndicator.util;

import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Set;

/**
 * @author Beelzebu
 */

public final class ConfigUtil {

    public static boolean isShowIndicator(Entity entity, EntityDamageEvent.DamageCause damageCause, double damage, String metadataTag, boolean enabled, boolean enablePlayer, boolean sneaking, boolean enableMonster, boolean enableAnimal, Set<EntityType> disabledEntities, Set<EntityDamageEvent.DamageCause> disabledDamageCauses) {
        /*
        Bukkit.broadcastMessage("Type: " + entity.getType() + "\n" +
                "Cause: " + damageCause + "\n" +
                "Damage: " + damage + "\n" +
                "Metadata: " + metadataTag + "\n" +
                "Enabled: " + enabled + "\n" +
                "Enabled Player: " + enablePlayer + "\n" +
                "Sneaking: " + sneaking + "\n" +
                "Enabled Monster: " + enableMonster + "\n" +
                "Enabled Animal: " + enableAnimal + "\n" +
                "Disabled Entities: " + disabledEntities + "\n" +
                "Disabled Damage Causes: " + disabledDamageCauses
        );
        */
        if (entity.hasMetadata(metadataTag)) {
            return false;
        }
        if (!enabled) {
            return false;
        }
        if (damage <= 0) {
            return false;
        }
        if (!(entity instanceof LivingEntity)) {
            return false;
        }
        if (entity instanceof ArmorStand) {
            return false;
        }
        if (entity instanceof Player) {
            if (!enablePlayer) {
                return false;
            }
            Player player = (Player) entity;
            if (player.isSneaking() && !sneaking) {
                return false;
            }
            if (entity.hasMetadata("NPC")) {
                return false;
            }
        }
        if ((entity instanceof Monster || entity instanceof Slime) && !enableMonster) {
            return false;
        }
        if (entity instanceof Animals && !enableAnimal) {
            return false;
        }
        if (disabledEntities.contains(entity.getType())) {
            return false;
        }
        return !disabledDamageCauses.contains(damageCause);
    }
}