package Gloves.DamageIndicator.hook;

import Gloves.glovedplugin;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Beelzebu
 */
public class HookManager {

    private final Set<Hook> hooks = new HashSet<>();

    public HookManager(glovedplugin plugin) {

    }

    public boolean isCritic(EntityDamageEvent e) {
        for (Hook hook : hooks) {
            if (hook.isCritic(e)) {
                return true;
            }
        }
        return false;
    }
}