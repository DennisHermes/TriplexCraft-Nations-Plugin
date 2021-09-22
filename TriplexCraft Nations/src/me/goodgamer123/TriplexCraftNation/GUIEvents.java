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
		
		if (e.getView().getTitle().equals(ChatColor.AQUA + "§lAdmin Panel")) {
			if (e.getSlot() > e.getView().getTopInventory().getSize()) return;
			
			if (e.getCurrentItem().getType().equals(Material.BARRIER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§lClose")) {
				e.setCancelled(true);
				p.updateInventory();
				p.closeInventory();
			} else if (e.getCurrentItem().getType().equals(Material.END_PORTAL_FRAME) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Standard nation price.")) {
				e.setCancelled(true);
				p.openInventory(priceInv("nationPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.GRASS_BLOCK) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Price per expending 1 block of nation.")) {
				e.setCancelled(true);
				p.openInventory(priceInv("blockPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.IRON_SWORD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Price to start a war.")) {
				e.setCancelled(true);
				p.openInventory(priceInv("warPrice"));
			}
			
			
			
		} else if (e.getView().getTitle().equals(ChatColor.AQUA + "Price to create a nation.")) {
			if (e.getCurrentItem().getType().equals(Material.MEDIUM_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 1")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setNationPrice(DataManager.getNationPrice() + 1);
				DataManager.saveFiles();
				p.openInventory(priceInv("nationPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.LARGE_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 10")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setNationPrice(DataManager.getNationPrice() + 10);
				DataManager.saveFiles();
				p.openInventory(priceInv("nationPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.AMETHYST_CLUSTER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 50")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setNationPrice(DataManager.getNationPrice() + 50);
				DataManager.saveFiles();
				p.openInventory(priceInv("nationPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.MEDIUM_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 1")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setNationPrice(DataManager.getNationPrice() - 1);
				DataManager.saveFiles();
				p.openInventory(priceInv("nationPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.LARGE_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 10")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setNationPrice(DataManager.getNationPrice() - 10);
				DataManager.saveFiles();
				p.openInventory(priceInv("nationPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.AMETHYST_CLUSTER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 50")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setNationPrice(DataManager.getNationPrice() - 50);
				DataManager.saveFiles();
				p.openInventory(priceInv("nationPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.BARRIER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§lCancel")) {
				e.setCancelled(true);
				p.updateInventory();
				p.closeInventory();
			}
		} else if (e.getView().getTitle().equals(ChatColor.AQUA + "Price per extra block for a nation.")) {
			if (e.getCurrentItem().getType().equals(Material.MEDIUM_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 1")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setPricePerBlock(DataManager.getPricePerBlock() + 1);
				DataManager.saveFiles();
				p.openInventory(priceInv("blockPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.LARGE_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 10")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setPricePerBlock(DataManager.getPricePerBlock() + 10);
				DataManager.saveFiles();
				p.openInventory(priceInv("blockPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.AMETHYST_CLUSTER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 50")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setPricePerBlock(DataManager.getPricePerBlock() + 50);
				DataManager.saveFiles();
				p.openInventory(priceInv("blockPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.MEDIUM_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 1")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setPricePerBlock(DataManager.getPricePerBlock() - 1);
				DataManager.saveFiles();
				p.openInventory(priceInv("blockPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.LARGE_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 10")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setPricePerBlock(DataManager.getPricePerBlock() - 10);
				DataManager.saveFiles();
				p.openInventory(priceInv("blockPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.AMETHYST_CLUSTER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 50")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setPricePerBlock(DataManager.getPricePerBlock() - 50);
				DataManager.saveFiles();
				p.openInventory(priceInv("blockPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.BARRIER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§lCancel")) {
				e.setCancelled(true);
				p.updateInventory();
				p.closeInventory();
			}
		} else if (e.getView().getTitle().equals(ChatColor.AQUA + "Price to start a war.")) {
			if (e.getCurrentItem().getType().equals(Material.MEDIUM_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 1")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setWarPrice(DataManager.getWarPrice() + 1);
				DataManager.saveFiles();
				p.openInventory(priceInv("warPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.LARGE_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 10")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setWarPrice(DataManager.getWarPrice() + 10);
				DataManager.saveFiles();
				p.openInventory(priceInv("warPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.AMETHYST_CLUSTER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Add 50")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setWarPrice(DataManager.getWarPrice() + 50);
				DataManager.saveFiles();
				p.openInventory(priceInv("warPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.MEDIUM_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 1")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setWarPrice(DataManager.getWarPrice() - 1);
				DataManager.saveFiles();
				p.openInventory(priceInv("warPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.LARGE_AMETHYST_BUD) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 10")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setWarPrice(DataManager.getWarPrice() - 10);
				DataManager.saveFiles();
				p.openInventory(priceInv("warPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.AMETHYST_CLUSTER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "Remove 50")) {
				e.setCancelled(true);
				p.updateInventory();
				DataManager.setWarPrice(DataManager.getWarPrice() - 50);
				DataManager.saveFiles();
				p.openInventory(priceInv("warPrice"));
			} else if (e.getCurrentItem().getType().equals(Material.BARRIER) && e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "§lCancel")) {
				e.setCancelled(true);
				p.updateInventory();
				p.closeInventory();
			}
			
			
			
		} else if (e.getView().getTitle().startsWith(ChatColor.AQUA + "Nation size: ")) {
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
    			
    			BigDecimal cost = BigDecimal.valueOf((nationSize - 250) * DataManager.getPricePerBlock() + DataManager.getNationPrice());
    			
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
    			
    			try {
					Economy.subtract(p.getUniqueId(), cost);
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
	
	Inventory priceInv(String type) {
		ItemStack borderItem = new ItemStack(Material.END_PORTAL_FRAME);
		ItemMeta borderMeta = borderItem.getItemMeta();
		if (type.equals("nationPrice")) {
			borderMeta.setDisplayName(ChatColor.AQUA + "Nation price: " + ChatColor.GOLD + "§l" + DataManager.getNationPrice());
		} else if (type.equals("blockPrice")) {
			borderMeta.setDisplayName(ChatColor.AQUA + "Block price: " + ChatColor.GOLD + "§l" + DataManager.getPricePerBlock());
		} else if (type.equals("warPrice")) {
			borderMeta.setDisplayName(ChatColor.AQUA + "War price: " + ChatColor.GOLD + "§l" + DataManager.getWarPrice());
		}
		List<String> borderLore = new ArrayList<String>();
		borderMeta.setLore(borderLore);
		borderItem.setItemMeta(borderMeta);
		
		ItemStack plus1 = new ItemStack(Material.MEDIUM_AMETHYST_BUD);
		ItemMeta plus1Meta = plus1.getItemMeta();
		plus1Meta.setDisplayName(ChatColor.GREEN + "Add 1");
		plus1.setItemMeta(plus1Meta);
		
		ItemStack plus10 = new ItemStack(Material.LARGE_AMETHYST_BUD);
		plus10.setAmount(10);
		ItemMeta plus10Meta = plus10.getItemMeta();
		plus10Meta.setDisplayName(ChatColor.GREEN + "Add 10");
		plus10.setItemMeta(plus10Meta);
		
		ItemStack plus50 = new ItemStack(Material.AMETHYST_CLUSTER);
		plus50.setAmount(50);
		ItemMeta plus50Meta = plus50.getItemMeta();
		plus50Meta.setDisplayName(ChatColor.GREEN + "Add 50");
		plus50.setItemMeta(plus50Meta);
		
		ItemStack min1 = new ItemStack(Material.MEDIUM_AMETHYST_BUD);
		ItemMeta min1Meta = min1.getItemMeta();
		min1Meta.setDisplayName(ChatColor.RED + "Remove 1");
		min1.setItemMeta(min1Meta);
		
		ItemStack min10 = new ItemStack(Material.LARGE_AMETHYST_BUD);
		min10.setAmount(10);
		ItemMeta min10Meta = min10.getItemMeta();
		min10Meta.setDisplayName(ChatColor.RED + "Remove 10");
		min10.setItemMeta(min10Meta);
		
		ItemStack min50 = new ItemStack(Material.AMETHYST_CLUSTER);
		min50.setAmount(50);
		ItemMeta min50Meta = min50.getItemMeta();
		min50Meta.setDisplayName(ChatColor.RED + "Remove 50");
		min50.setItemMeta(min50Meta);
		
		ItemStack cancel = new ItemStack(Material.BARRIER);
		ItemMeta cancelMeta = cancel.getItemMeta();
		cancelMeta.setDisplayName(ChatColor.RED + "§lCancel");
		cancel.setItemMeta(cancelMeta);
		
		Inventory inv = null;
		if (type.equals("nationPrice")) {
			inv = Bukkit.createInventory(null, 36, ChatColor.AQUA + "Price to create a nation.");
		} else if (type.equals("blockPrice")) {
			inv = Bukkit.createInventory(null, 36, ChatColor.AQUA + "Price per extra block for a nation.");
		} else if (type.equals("warPrice")) {
			inv = Bukkit.createInventory(null, 36, ChatColor.AQUA + "Price to start a war.");
		}
		inv.setItem(9, min50);
		inv.setItem(10, min10);
		inv.setItem(11, min1);
		inv.setItem(13, borderItem);
		inv.setItem(15, plus1);
		inv.setItem(16, plus10);
		inv.setItem(17, plus50);
		inv.setItem(31, cancel);
		
		return inv;
	}
	
}
