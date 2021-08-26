package me.goodgamer123.TriplexCraftNation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GUIEvents implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null) return;
	
		Player p = (Player) e.getWhoClicked();
		
		if (e.getView().getTitle().startsWith(ChatColor.AQUA + "Nation size: ")) {
			if (e.getSlot() > e.getView().getTopInventory().getSize()) return;
			
			if (e.getCurrentItem().getType().equals(Material.END_PORTAL_FRAME) && e.getCurrentItem().getItemMeta().getDisplayName().startsWith(ChatColor.AQUA + "Nation size: ")) {
				e.setCancelled(true);
				p.updateInventory();
			} else if (e.getCurrentItem().getType().equals(Material.MEDIUM_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 1")) {
				e.setCancelled(true);
				p.updateInventory();
				p.openInventory(sizeChange(e.getView().getTopInventory(), e.getView().getTitle(), 1));
			} else if (e.getCurrentItem().getType().equals(Material.LARGE_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 10")) {
				e.setCancelled(true);
				p.updateInventory();
				p.openInventory(sizeChange(e.getView().getTopInventory(), e.getView().getTitle(), 10));
			} else if (e.getCurrentItem().getType().equals(Material.AMETHYST_CLUSTER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 50")) {
				e.setCancelled(true);
				p.updateInventory();
				p.openInventory(sizeChange(e.getView().getTopInventory(), e.getView().getTitle(), 50));
			} else if (e.getCurrentItem().getType().equals(Material.MEDIUM_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 1")) {
				e.setCancelled(true);
				p.updateInventory();
				p.openInventory(sizeChange(e.getView().getTopInventory(), e.getView().getTitle(), -1));
			} else if (e.getCurrentItem().getType().equals(Material.LARGE_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 10")) {
				e.setCancelled(true);
				p.updateInventory();
				p.openInventory(sizeChange(e.getView().getTopInventory(), e.getView().getTitle(), -10));
			} else if (e.getCurrentItem().getType().equals(Material.AMETHYST_CLUSTER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 50")) {
				e.setCancelled(true);
				p.updateInventory();
				p.openInventory(sizeChange(e.getView().getTopInventory(), e.getView().getTitle(), -50));
			} else if (e.getCurrentItem().getType().equals(Material.BARRIER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§lCancel")) {
				e.setCancelled(true);
				p.updateInventory();
				p.closeInventory();
			} else if (e.getCurrentItem().getType().equals(Material.EMERALD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "§lConfirm")) {
				e.setCancelled(true);
				p.updateInventory();
				p.closeInventory();
				//TODO
			}
		}
	}
	
	Inventory sizeChange(Inventory inv, String title, int add) {
		int oldSize = Integer.parseInt(ChatColor.stripColor(title.replace("Nation size: ", "").replace(" block", "")));
		int newSize = 250;
		if ((oldSize + add) >= 250) {
			newSize = oldSize + add;
		} else {
			newSize = oldSize;
		}
		
		Inventory borderSize = Bukkit.createInventory(null, 36, ChatColor.AQUA + "Nation size: " + ChatColor.GOLD + "§l" + newSize + ChatColor.AQUA + " block");
		borderSize.setItem(9, inv.getItem(9));
		borderSize.setItem(10, inv.getItem(10));
		borderSize.setItem(11, inv.getItem(11));
		borderSize.setItem(13, inv.getItem(13));
		borderSize.setItem(15, inv.getItem(15));
		borderSize.setItem(16, inv.getItem(16));
		borderSize.setItem(17, inv.getItem(17));
		borderSize.setItem(30, inv.getItem(30));
		borderSize.setItem(32, inv.getItem(32));
		
		return borderSize;
	}
	
}
