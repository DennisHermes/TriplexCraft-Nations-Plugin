package me.goodgamer123.TriplexCraftNation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.UserDoesNotExistException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

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
				p.sendMessage(ChatColor.AQUA + "Creating Nation...");
    			
    			//Values
    			Location loc = p.getLocation().getBlock().getLocation();
    			int nationSize = Integer.parseInt(ChatColor.stripColor(e.getClickedInventory().getItem(13).getItemMeta().getLore().get(0).replace("Nation size: ", "").replace(" block", "")));
    			String nationName = ChatColor.stripColor(e.getClickedInventory().getItem(13).getItemMeta().getDisplayName()).replace("Nation name: ", "");
				
    			//Ignored block list
    			List<Material> ignoredBlocks = new ArrayList<Material>();
    			ignoredBlocks.add(Material.AIR);
    			ignoredBlocks.add(Material.BROWN_MUSHROOM_BLOCK);
    			ignoredBlocks.add(Material.RED_MUSHROOM_BLOCK);
    			ignoredBlocks.add(Material.RED_MUSHROOM);
    			ignoredBlocks.add(Material.BROWN_MUSHROOM);
    			ignoredBlocks.add(Material.MUSHROOM_STEM);
    			ignoredBlocks.add(Material.BIRCH_LOG);
    			ignoredBlocks.add(Material.ACACIA_LEAVES);
    			ignoredBlocks.add(Material.JUNGLE_LEAVES);
    			ignoredBlocks.add(Material.DARK_OAK_LEAVES);
    			ignoredBlocks.add(Material.OAK_LEAVES);
    			ignoredBlocks.add(Material.SPRUCE_LEAVES);
    			ignoredBlocks.add(Material.ACACIA_LOG);
    			ignoredBlocks.add(Material.JUNGLE_LOG);
    			ignoredBlocks.add(Material.DARK_OAK_LOG);
    			ignoredBlocks.add(Material.OAK_LOG);
    			ignoredBlocks.add(Material.SPRUCE_LOG);
    			ignoredBlocks.add(Material.BIRCH_LOG);
    			ignoredBlocks.add(Material.BIRCH_LEAVES);
    			ignoredBlocks.add(Material.CACTUS);
    			ignoredBlocks.add(Material.DEAD_BUSH);
    			
    			//Already claimed check
    			for (int x = -(nationSize / 2); x < (nationSize / 2); x++) {
    				Location loc1 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - x, 0, loc.getZ() - (nationSize / 2))).getLocation();
    				Location loc2 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - x, 0, loc.getZ() - (nationSize / 2))).getLocation();
    				Location loc3 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - x, 0, loc.getZ() + (nationSize / 2))).getLocation();
    				Location loc4 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - x, 0, loc.getZ() + (nationSize / 2))).getLocation();
    				
    				while (ignoredBlocks.contains(loc1.getBlock().getType())) loc1.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc2.getBlock().getType())) loc2.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc3.getBlock().getType())) loc3.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc4.getBlock().getType())) loc4.add(0, -1, 0);
    				
    				if (DataManager.nationsData.get("Nations") != null) {
	    				if (loc1.getBlock().getType().equals(Material.RED_WOOL)) {
							p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "There is allready a other nation here.");
							return;
	    				}
	    				if (loc2.getBlock().getType().equals(Material.RED_WOOL)) {
	    					p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    	    		    p.sendMessage(ChatColor.RED + "There is allready a other nation here.");
	    					return;
	    				}
	    				if (loc3.getBlock().getType().equals(Material.RED_WOOL)) {
	    					p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    	    		    p.sendMessage(ChatColor.RED + "There is allready a other nation here.");
	    					return;
	    				}
	    				if (loc4.getBlock().getType().equals(Material.RED_WOOL)) {
							p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "There is allready a other nation here.");
							return;
	    				}
    				}
    	    	}
    			for (int z = -(nationSize / 2); z < (nationSize / 2); z++) {
    				Location loc1 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - (nationSize / 2), 0, loc.getZ() - z)).getLocation();
    				Location loc2 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - (nationSize / 2), 0, loc.getZ() - z)).getLocation();
    				Location loc3 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() + (nationSize / 2), 0, loc.getZ() - z)).getLocation();
    				Location loc4 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() + (nationSize / 2), 0, loc.getZ() - z)).getLocation();
    				
    				while (ignoredBlocks.contains(loc1.getBlock().getType())) loc1.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc2.getBlock().getType())) loc2.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc3.getBlock().getType())) loc3.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc4.getBlock().getType())) loc4.add(0, -1, 0);
    				
    				if (DataManager.nationsData.get("Nations") != null) {
	    				if (loc1.getBlock().getType().equals(Material.RED_WOOL)) {
							p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "There is allready a other nation here.");
							return;
	    				}
	    				if (loc2.getBlock().getType().equals(Material.RED_WOOL)) {
	    					p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    	    		    p.sendMessage(ChatColor.RED + "There is allready a other nation here.");
	    					return;
	    				}
	    				if (loc3.getBlock().getType().equals(Material.RED_WOOL)) {
	    					p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    	    		    p.sendMessage(ChatColor.RED + "There is allready a other nation here.");
	    					return;
	    				}
	    				if (loc4.getBlock().getType().equals(Material.RED_WOOL)) {
							p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "There is allready a other nation here.");
							return;
	    				}
    				}
    	    	}
    			
    			BigDecimal cost = BigDecimal.valueOf((nationSize - 250) * );
    			
    			try {
					if (!Economy.hasEnough(p.getUniqueId(), cost)) {
						p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
						p.sendMessage(ChatColor.RED + "You don't have enough money to create a nation.");
						return;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
					p.sendMessage(ChatColor.RED + "Something went wrong...");
					return;
				}
    			
    			//Calculating corners
    			Location cornerLoc1 = new Location(loc.getWorld(), loc.getX() - (nationSize / 2), 0, loc.getZ() - (nationSize / 2));
				Location cornerLoc2 = new Location(loc.getWorld(), loc.getX() + (nationSize / 2), 0, loc.getZ() + (nationSize / 2));
    			
    			//Creating region
				BlockVector3 min = BlockVector3.at(cornerLoc1.getBlockX(), 320, cornerLoc1.getBlockZ());
    			BlockVector3 max = BlockVector3.at(cornerLoc2.getBlockX(), 0, cornerLoc2.getBlockZ());
    			ProtectedRegion region = new ProtectedCuboidRegion(nationName, min, max);
    			RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
    			RegionManager regions = container.get(BukkitAdapter.adapt(loc.getWorld()));
    			regions.addRegion(region);
    			
    			//Setting flags
    			region.setFlag(Flags.ENTRY, StateFlag.State.DENY);
    			
    			//Set owner
    			DefaultDomain owner = region.getOwners();
    			owner.addPlayer(p.getUniqueId());
    			
    			//Data files
    			DataManager.createVirtualNation(nationName, p.getUniqueId(), loc);
    			
    			//Border drawing
    			for (int x = -(nationSize / 2); x < (nationSize / 2); x++) {
    				Location loc1 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - x, 0, loc.getZ() - (nationSize / 2))).getLocation();
    				Location loc2 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - x, 0, loc.getZ() - (nationSize / 2))).getLocation();
    				Location loc3 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - x, 0, loc.getZ() + (nationSize / 2))).getLocation();
    				Location loc4 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - x, 0, loc.getZ() + (nationSize / 2))).getLocation();
    				
    				while (ignoredBlocks.contains(loc1.getBlock().getType())) loc1.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc2.getBlock().getType())) loc2.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc3.getBlock().getType())) loc3.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc4.getBlock().getType())) loc4.add(0, -1, 0);
    				
    				loc1.getBlock().setType(Material.RED_WOOL);
    				loc2.getBlock().setType(Material.RED_WOOL);
    				loc3.getBlock().setType(Material.RED_WOOL);
    				loc4.getBlock().setType(Material.RED_WOOL);
    	    	}
    	    	for (int z = -(nationSize / 2); z < (nationSize / 2); z++) {
    				Location loc1 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - (nationSize / 2), 0, loc.getZ() - z)).getLocation();
    				Location loc2 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() - (nationSize / 2), 0, loc.getZ() - z)).getLocation();
    				Location loc3 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() + (nationSize / 2), 0, loc.getZ() - z)).getLocation();
    				Location loc4 = loc.getWorld().getHighestBlockAt(new Location(loc.getWorld(), loc.getX() + (nationSize / 2), 0, loc.getZ() - z)).getLocation();
    				
    				while (ignoredBlocks.contains(loc1.getBlock().getType())) loc1.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc2.getBlock().getType())) loc2.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc3.getBlock().getType())) loc3.add(0, -1, 0);
    				while (ignoredBlocks.contains(loc4.getBlock().getType())) loc4.add(0, -1, 0);
    				
    				loc1.getBlock().setType(Material.RED_WOOL);
    				loc2.getBlock().setType(Material.RED_WOOL);
    				loc3.getBlock().setType(Material.RED_WOOL);
    				loc4.getBlock().setType(Material.RED_WOOL);
    	    	}
    	    	
    	    	//echo
    			p.sendMessage(ChatColor.GREEN + "Your nation " + nationName + ChatColor.GREEN + " has been successfully created!");
			}
		}
	}
	
	Inventory sizeChange(Inventory inv, String title, int add) {
		int oldSize = Integer.parseInt(ChatColor.stripColor(inv.getItem(13).getItemMeta().getLore().get(0).replace("Nation size: ", "").replace(" block", "")));
		int newSize = 250;
		if ((oldSize + add) >= 250) {
			newSize = oldSize + add;
		} else {
			newSize = oldSize;
		}
		
		ItemStack borderItem = inv.getItem(13);
		ItemMeta borderMeta = borderItem.getItemMeta();
		List<String> borderLore = new ArrayList<String>();
		borderLore.add(ChatColor.AQUA + "Nation size: " + ChatColor.GOLD + newSize + ChatColor.AQUA + " block");
		borderMeta.setLore(borderLore);
		borderItem.setItemMeta(borderMeta);
		
		Inventory borderSize = Bukkit.createInventory(null, 36, ChatColor.AQUA + "Nation size: " + ChatColor.GOLD + "§l" + newSize + ChatColor.AQUA + " block");
		borderSize.setItem(9, inv.getItem(9));
		borderSize.setItem(10, inv.getItem(10));
		borderSize.setItem(11, inv.getItem(11));
		borderSize.setItem(13, borderItem);
		borderSize.setItem(15, inv.getItem(15));
		borderSize.setItem(16, inv.getItem(16));
		borderSize.setItem(17, inv.getItem(17));
		borderSize.setItem(30, inv.getItem(30));
		borderSize.setItem(32, inv.getItem(32));
		
		return borderSize;
	}
	
}
