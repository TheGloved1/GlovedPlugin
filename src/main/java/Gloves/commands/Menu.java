package Gloves.commands;

import Gloves.glovedplugin;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import static org.apache.logging.log4j.LogManager.getLogger;

public class Menu implements Listener, CommandExecutor {
    private final String invName = "Commands";
    private final String invPlayer = "Inventory";

    public Menu(glovedplugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        getLogger().info("Menu loaded!");

    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(invName)) {
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

        if (event.getView().getTitle().equals(invPlayer)) {
            Player player = (Player) event.getWhoClicked();
            int slot = event.getSlot();

            if (slot == 13) {
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                player.closeInventory();
                player.performCommand("menu");
            }
            event.setCancelled(true);

        }
    }

    @EventHandler
    public void onPlayerInvChange(InventoryEvent event) {
        if (event.getView().getTitle().equals(invPlayer)) {
            Inventory player = new PlayerInventory() {
                @Override
                public @Nullable ItemStack @NotNull [] getArmorContents() {
                    return new ItemStack[0];
                }

                @Override
                public @Nullable ItemStack @NotNull [] getExtraContents() {
                    return new ItemStack[0];
                }

                @Override
                public @Nullable ItemStack getHelmet() {
                    return null;
                }

                @Override
                public @Nullable ItemStack getChestplate() {
                    return null;
                }

                @Override
                public @Nullable ItemStack getLeggings() {
                    return null;
                }

                @Override
                public @Nullable ItemStack getBoots() {
                    return null;
                }

                @Override
                public void setItem(int index, @Nullable ItemStack item) {

                }

                @Override
                public void setItem(@NotNull EquipmentSlot slot, @Nullable ItemStack item) {

                }

                @Override
                public @NotNull ItemStack getItem(@NotNull EquipmentSlot slot) {
                    return null;
                }

                @Override
                public void setArmorContents(@Nullable ItemStack[] items) {

                }

                @Override
                public void setExtraContents(@Nullable ItemStack[] items) {

                }

                @Override
                public void setHelmet(@Nullable ItemStack helmet) {

                }

                @Override
                public void setChestplate(@Nullable ItemStack chestplate) {

                }

                @Override
                public void setLeggings(@Nullable ItemStack leggings) {

                }

                @Override
                public void setBoots(@Nullable ItemStack boots) {

                }

                @Override
                public @NotNull ItemStack getItemInMainHand() {
                    return null;
                }

                @Override
                public void setItemInMainHand(@Nullable ItemStack item) {

                }

                @Override
                public @NotNull ItemStack getItemInOffHand() {
                    return null;
                }

                @Override
                public void setItemInOffHand(@Nullable ItemStack item) {

                }

                @Override
                public @NotNull ItemStack getItemInHand() {
                    return null;
                }

                @Override
                public void setItemInHand(@Nullable ItemStack stack) {

                }

                @Override
                public int getHeldItemSlot() {
                    return 0;
                }

                @Override
                public void setHeldItemSlot(int slot) {

                }

                @Override
                public @Nullable HumanEntity getHolder() {
                    return null;
                }

                @Override
                public int getSize() {
                    return 0;
                }

                @Override
                public int getMaxStackSize() {
                    return 0;
                }

                @Override
                public void setMaxStackSize(int size) {

                }

                @Override
                public @Nullable ItemStack getItem(int index) {
                    return null;
                }

                @Override
                public @NotNull HashMap<Integer, ItemStack> addItem(@NotNull ItemStack... items) throws IllegalArgumentException {
                    return null;
                }

                @Override
                public @NotNull HashMap<Integer, ItemStack> removeItem(@NotNull ItemStack... items) throws IllegalArgumentException {
                    return null;
                }

                @Override
                public @NotNull HashMap<Integer, ItemStack> removeItemAnySlot(@NotNull ItemStack... items) throws IllegalArgumentException {
                    return null;
                }

                @Override
                public @Nullable ItemStack @NotNull [] getContents() {
                    return new ItemStack[0];
                }

                @Override
                public void setContents(@Nullable ItemStack @NotNull [] items) throws IllegalArgumentException {

                }

                @Override
                public @Nullable ItemStack @NotNull [] getStorageContents() {
                    return new ItemStack[0];
                }

                @Override
                public void setStorageContents(@Nullable ItemStack @NotNull [] items) throws IllegalArgumentException {

                }

                @Override
                public boolean contains(@NotNull Material material) throws IllegalArgumentException {
                    return false;
                }

                @Override
                public boolean contains(@Nullable ItemStack item) {
                    return false;
                }

                @Override
                public boolean contains(@NotNull Material material, int amount) throws IllegalArgumentException {
                    return false;
                }

                @Override
                public boolean contains(@Nullable ItemStack item, int amount) {
                    return false;
                }

                @Override
                public boolean containsAtLeast(@Nullable ItemStack item, int amount) {
                    return false;
                }

                @Override
                public @NotNull HashMap<Integer, ? extends ItemStack> all(@NotNull Material material) throws IllegalArgumentException {
                    return null;
                }

                @Override
                public @NotNull HashMap<Integer, ? extends ItemStack> all(@Nullable ItemStack item) {
                    return null;
                }

                @Override
                public int first(@NotNull Material material) throws IllegalArgumentException {
                    return 0;
                }

                @Override
                public int first(@NotNull ItemStack item) {
                    return 0;
                }

                @Override
                public int firstEmpty() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public void remove(@NotNull Material material) throws IllegalArgumentException {

                }

                @Override
                public void remove(@NotNull ItemStack item) {

                }

                @Override
                public void clear(int index) {

                }

                @Override
                public void clear() {

                }

                @Override
                public int close() {
                    return 0;
                }

                @Override
                public @NotNull List<HumanEntity> getViewers() {
                    return null;
                }

                @Override
                public @NotNull InventoryType getType() {
                    return null;
                }

                @Override
                public @Nullable InventoryHolder getHolder(boolean useSnapshot) {
                    return null;
                }

                @Override
                public @NotNull ListIterator<ItemStack> iterator() {
                    return null;
                }

                @Override
                public @NotNull ListIterator<ItemStack> iterator(int index) {
                    return null;
                }

                @Override
                public @Nullable Location getLocation() {
                    return null;
                }
            };
            player.setItem(17, getItem(new ItemStack(Material.NETHER_STAR), "&b&lMenu", "&aClick to Open the Menu!"));
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