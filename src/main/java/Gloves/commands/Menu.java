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
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.apache.logging.log4j.LogManager.getLogger;
import static org.bukkit.Material.CHEST;

public class Menu implements Listener, CommandExecutor {
    private final String invName = "Commands";


    public Menu(glovedplugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        getLogger().info("Menu loaded!");

    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if ((event.getView().getTitle().equals(invName)) && (Objects.requireNonNull(event.getClickedInventory()).getType().equals(InventoryType.CHEST))) {
            Player player = (Player) event.getWhoClicked();
            int slot = event.getSlot();

            if (slot == 20) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                player.closeInventory();
                player.performCommand("warps");

            } else if (slot == 21) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                player.closeInventory();
                player.performCommand("shop");

            } else if (slot == 22) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                player.closeInventory();
                player.performCommand("spawn");

            } else if (slot == 23) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                player.closeInventory();
                player.performCommand("homes");

            } else if (slot == 24) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                player.closeInventory();
                player.performCommand("rtp");

            } else if (slot == 30) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                player.closeInventory();
                player.performCommand("craft");

            } else if (slot == 31) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                player.closeInventory();
                player.performCommand("skills");

            } else if (slot == 32) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                player.closeInventory();
                player.performCommand("ender");

            } else if (slot == 13) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                player.closeInventory();
                player.performCommand("jobs browse");
            }
            event.setCancelled(true);

        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

            Player player = event.getPlayer();
            Inventory inv = player.getInventory();
            inv.setItem(17, getItem(new ItemStack(CHEST), "&b&lMenu", "&aClick to Open the Menu!"));
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getView().getBottomInventory();
        int slot = event.getSlot();
        if ((inv.equals(event.getClickedInventory())) && (slot == 17)) {

            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            player.closeInventory();
            player.performCommand("menu");
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }

    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can open menu.");
            return true;
        }

        Inventory inv = Bukkit.createInventory(player, 9 * 6, invName);

        inv.setItem(20, getItem(new ItemStack(Material.NETHER_STAR), "&b&lWarps", "&a/warps"));
        inv.setItem(21, getItem(new ItemStack(Material.GOLD_INGOT), "&6&lShop", "&a/shop"));
        inv.setItem(22, getItem(new ItemStack(Material.GRASS_BLOCK), "&3&lSpawn", "&a/spawn"));
        inv.setItem(23, getItem(new ItemStack(Material.RED_BED), "&4&lHomes", "&a/homes"));
        inv.setItem(24, getItem(new ItemStack(Material.COMPASS), "&2&lRTP", "&a/rtp"));
        inv.setItem(30, getItem(new ItemStack(Material.CRAFTING_TABLE), "&6&lCraft", "&a/craft"));
        inv.setItem(31, getItem(new ItemStack(Material.WRITABLE_BOOK), "&4&lSkills", "&a/skills"));
        inv.setItem(32, getItem(new ItemStack(Material.ENDER_CHEST), "&5&lEnder Chest", "&a/ender"));
        inv.setItem(13, getItem(new ItemStack(Material.EMERALD), "&6&lJobs", "&a/jobs browse"));

        player.openInventory(inv);
        getLogger().info(player.getName() + " opened the menu.");

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