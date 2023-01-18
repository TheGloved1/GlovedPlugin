package Gloves.DamageIndicator.command;

import Gloves.DamageIndicator.DIMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * @author YitanTribal
 */
public final class CommandHandler implements CommandExecutor {

    private final DIMain plugin;

    public CommandHandler(DIMain plugin) {
        this.plugin = plugin;
    }

    private boolean isPlayer(CommandSender sender) {
        if (sender instanceof Player) {
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Only a Player can use this command");
        return false;
    }

    private boolean checkPermissions(CommandSender sender, String permission) {
        if (sender.hasPermission(permission)) {
            return true;
        }
        sender.sendMessage(ChatColor.RED + "You don't have permission to use this command this!");
        return false;
    }

    private boolean checkArguments(CommandSender sender, int args, int num) {
        if (args != num) {
            sender.sendMessage(ChatColor.RED + "Invalid number of arguments");
            return false;
        }
        return true;
    }

    private int getInt(CommandSender sender, String text) {
        int amount;
        try {
            amount = Integer.parseInt(text);
            return amount;
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Invalid given amount");
            return -1;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] strings) {
        onCommand(sender, strings);
        return true;
    }

    private void onCommand(CommandSender sender, String[] strings) {
        if (strings.length >= 1) {
            switch (strings[0]) {
                case "reload":
                    if (checkPermissions(sender, "damageindicator.admin")) {
                        plugin.reload();
                        sender.sendMessage(ChatColor.GREEN + "Reloaded config!");
                    }
                    break;
                case "clear":
                    if (isPlayer(sender) && (checkPermissions(sender, "damageindicator.clear") || checkPermissions(sender, "damageindicator.admin")) && checkArguments(sender, strings.length, 2)) {
                        int range = getInt(sender, strings[1]);
                        if (range >= 0) {
                            int c = 0;
                            c = ((Player) sender).getNearbyEntities(range, range, range).stream().filter(entity -> entity instanceof ArmorStand && plugin.isDamageIndicator(entity, false)).peek(Entity::remove).map(_i -> 1).reduce(c, Integer::sum);
                            sender.sendMessage(ChatColor.GREEN + "" + c + " Damage Indicators were removed");
                        }
                    }
                    break;
                case "clearall":
                    if (isPlayer(sender) && (checkPermissions(sender, "damageindicator.admin"))) {
                        int c = 0;
                        c = ((Player) sender).getLocation().getWorld().getEntitiesByClass(ArmorStand.class).stream().filter(plugin::isDamageIndicator).peek(Entity::remove).map(_i -> 1).reduce(c, Integer::sum);
                        sender.sendMessage(ChatColor.GREEN + "" + c + " Damage Indicators were removed in " + ((Player) sender).getLocation().getWorld().getName());
                    }
                    break;
                case "toggle":
                    if (isPlayer(sender)) {
                        boolean status = !plugin.getStorageProvider().showArmorStand((Player) sender);
                        plugin.getStorageProvider().setShowArmorStand((Player) sender, status);
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', status ? plugin.getConfig().getString("Messages.Damage Indicator.Enabled", "") : plugin.getConfig().getString("Messages.Damage Indicator.Disabled", "")));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis command can't be executed from console."));
                    }
                    break;
            }
            return;
        }
        sender.sendMessage(ChatColor.DARK_AQUA + "<===== Damage Indicator " + Bukkit.getServer().getPluginManager().getPlugin("DamageIndicator").getDescription().getVersion() + " =====>");
        sender.sendMessage(ChatColor.DARK_AQUA + "/di reload");
        sender.sendMessage(ChatColor.DARK_AQUA + "/di clear <range> " + ChatColor.AQUA + "#remove the damage indicators in the range");
        sender.sendMessage(ChatColor.DARK_AQUA + "/di clearall " + ChatColor.AQUA + "#remove the damage indicators in the world (may cause lag)");
        sender.sendMessage(ChatColor.DARK_AQUA + "/di toggle " + ChatColor.AQUA + "#enable/disable damage indicators for you");
    }
}