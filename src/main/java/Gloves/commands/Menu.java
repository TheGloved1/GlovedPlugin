package Gloves.commands;

import Gloves.glovedplugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Listener, CommandExecutor {
    private String invName = "Class Selector";

    public Menu(glovedplugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private boolean claimed = false;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().title().equals(invName)) {
            return;
        }


        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        if ((slot == 10) && (!claimed)) {
            player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            claimed = true;

        } else if ((slot == 12) && (!claimed)) {
            player.getInventory().addItem(new ItemStack(Material.STICK));
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            claimed = true;

        } else if ((slot == 14) && (!claimed)) {
            player.getInventory().addItem(new ItemStack(Material.SHIELD));
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            claimed = true;

        } else if ((slot == 16) && (!claimed)) {
            player.getInventory().addItem(new ItemStack(Material.BOW));
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            claimed = true;

        }

        event.setCancelled(true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }

        Inventory inv = Bukkit.createInventory(player, 9 * 3, invName);

        inv.setItem(10, getItem(new ItemStack(Material.DIAMOND_SWORD), "&4Melee", "&aComing Soon!"));
        inv.setItem(12, getItem(new ItemStack(Material.STICK), "&4Mage", "&aComing Soon!"));
        inv.setItem(14, getItem(new ItemStack(Material.SHIELD), "&4Tank", "&aComing Soon!"));
        inv.setItem(16, getItem(new ItemStack(Material.BOW), "&4Archer", "&aComing Soon!"));

        player.openInventory(inv);

        return true;
    }

    private ItemStack getItem(ItemStack item, String name, String ... lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lores);

        item.setItemMeta(meta);
        return item;
    }
}