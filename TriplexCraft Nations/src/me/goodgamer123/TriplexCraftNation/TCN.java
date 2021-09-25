package me.goodgamer123.TriplexCraftNation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.earth2me.essentials.api.Economy;

public class TCN implements CommandExecutor {
	
	static HashMap<Player, String> inviteList = new HashMap<Player, String>();
	static HashMap<String, String> allyList = new HashMap<String, String>();
	static HashMap<String, String> warList = new HashMap<String, String>();
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "je moet een speler zijn om dit te kunnen doen!");
			return false;
		}
		
		Player p = (Player)sender;
    
	    if (cmd.getName().equalsIgnoreCase("TCN")) {
	    	if (args.length <= 0) {
	    		p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN help" + ChatColor.RED + " to get a list of the commands you can use.");
	    	} else {
	    		
//===========================================//
//											 //
//					Creation				 //
//				 							 //
//===========================================//
	    		
	    		if (args[0].equalsIgnoreCase("create")) {
	    			if (!DataManager.hasNation(p.getUniqueId())) {
	    				if (args.length <= 1) {
		    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
		    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN create <nation name>");
		    			} else {
		    				if (!args[1].chars().allMatch(Character::isLetterOrDigit)) {
		    					p.sendMessage(ChatColor.RED + "Nation name must be alphanumeric.");
		    				} else if (args[1].length() >= 15 && args[1].length() <= 3) {
		    					p.sendMessage(ChatColor.RED + "Nation name must be between 3 and 15 characters.");
		    				} else {
				    			ItemStack borderItem = new ItemStack(Material.END_PORTAL_FRAME);
				    			ItemMeta borderMeta = borderItem.getItemMeta();
				    			borderMeta.setDisplayName(ChatColor.AQUA + "Nation name: " + ChatColor.GOLD + "§l" + args[1]);
				    			List<String> borderLore = new ArrayList<String>();
				    			borderLore.add(ChatColor.AQUA + "Nation size: " + ChatColor.GOLD + "§l250" + ChatColor.AQUA + " block");
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
				    			
				    			ItemStack confirm = new ItemStack(Material.EMERALD);
				    			ItemMeta confirmMeta = confirm.getItemMeta();
				    			confirmMeta.setDisplayName(ChatColor.GREEN + "§lConfirm");
				    			List<String> confirmLore = new ArrayList<String>();
				    			confirmLore.add("");
				    			confirmLore.add(ChatColor.AQUA + "The nation will be centred");
				    			confirmLore.add(ChatColor.AQUA + "on your current position.");
			                    confirmMeta.setLore(confirmLore);
				    			confirm.setItemMeta(confirmMeta);
				    			
				    			ItemStack cancel = new ItemStack(Material.BARRIER);
				    			ItemMeta cancelMeta = cancel.getItemMeta();
				    			cancelMeta.setDisplayName(ChatColor.RED + "§lCancel");
				    			cancel.setItemMeta(cancelMeta);
				    			
				    			Inventory borderSize = Bukkit.createInventory(null, 36, ChatColor.AQUA + "Nation size: " + ChatColor.GOLD + "§l250" + ChatColor.AQUA + " block");
				    			borderSize.setItem(9, min50);
				    			borderSize.setItem(10, min10);
				    			borderSize.setItem(11, min1);
				    			borderSize.setItem(13, borderItem);
				    			borderSize.setItem(15, plus1);
				    			borderSize.setItem(16, plus10);
				    			borderSize.setItem(17, plus50);
				    			borderSize.setItem(30, confirm);
				    			borderSize.setItem(32, cancel);
				    			
				    			p.openInventory(borderSize);
		    				}
		    			}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are already a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("builder")) {
	    			if (args.length <= 1) {
	    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
	    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN builder <player name>");
	    			} else {
		    			if (DataManager.hasNation(p.getUniqueId())) {
		    				if (DataManager.getRole(p.getUniqueId()) == 4) {
		    					if (DataManager.getUuid(args[1]) != null) {
		    						if (DataManager.isFinished(DataManager.getNation(p.getUniqueId()))) {
		    							if (DataManager.hasNation(DataManager.getUuid(args[1]))) {
		    								if (DataManager.getNation(DataManager.getUuid(args[1])).equals(DataManager.getNation(p.getUniqueId()))) {
		    									DataManager.setBuilder(DataManager.getUuid(args[1]), DataManager.getNation(p.getUniqueId()));
			    								p.sendMessage(ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " has successfully become builder of your nation!");
		    								} else {
		    									p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
			    		    		    		p.sendMessage(ChatColor.RED + "The player must first become a member of your nation.");
		    								}
		    							} else {
		    								p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
		    		    		    		p.sendMessage(ChatColor.RED + "The player must first become a member of your nation.");
		    							}
		    						} else {
		    							if (!DataManager.hasNation(DataManager.getUuid(args[1]))) {
		    								DataManager.setBuilder(DataManager.getUuid(args[1]), DataManager.getNation(p.getUniqueId()));
		    								p.sendMessage(ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " has successfully become builder of your nation!");
		    							} else {
		    								p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
		    		    		    		p.sendMessage(ChatColor.RED + "The player is allready in another nation.");
		    							}
		    						}
		    					} else {
		    						p.sendMessage(ChatColor.RED + "§lCannot find player!");
			    		    		p.sendMessage(ChatColor.RED + "The specified player cannot be found.");
		    					}
		    				} else {
		    					p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
		    		    		p.sendMessage(ChatColor.RED + "Only the nation's president can appoint builders.");
		    				}
		    			} else {
		    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "You are not president of a nation.");
		    			}
	    			}
	    		} else if (args[0].equalsIgnoreCase("finish")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
	    				if (DataManager.getRole(p.getUniqueId()) == 4) {
	    					DataManager.finishNation(DataManager.getNation(p.getUniqueId()));
	    					p.sendMessage(ChatColor.GREEN + "Your nation is successfully finished. Other players can now enter.");
	    				} else {
	    					p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
	    		    		p.sendMessage(ChatColor.RED + "Only the nation's president can finish the nation.");
	    				}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not president of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("list")) {
    				p.sendMessage(ChatColor.AQUA + "List of nations:");
    				String nations = "";
	    			for (String s : DataManager.nationsData.getConfigurationSection("Nations").getKeys(false)) {
	    	            nations = nations + s + ", ";
	    	        }
	    			p.sendMessage(nations);
	    		} else if (args[0].equalsIgnoreCase("info")) {
	    			if (args.length <= 1) {
	    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
	    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN info <nation name>");
	    			} else {
	    				if (DataManager.nationsExists(args[1])) {
	    					p.sendMessage(ChatColor.AQUA + "Info about " + ChatColor.GOLD + args[1] + ChatColor.AQUA + ":");
			    			for (String s : DataManager.nationsData.getConfigurationSection("Nations." + args[1]).getKeys(false)) {
			    				p.sendMessage("   " + ChatColor.GOLD + s + ChatColor.AQUA + ": "  + DataManager.nationsData.get("Nations." + args[1] + "." + s).toString());
			    	        }
	    				} else {
	    					p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
		    	    		p.sendMessage(ChatColor.RED + "Nation does not exist.");
	    				}
	    			}
	    		} else if (args[0].equalsIgnoreCase("setdescription")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			if (DataManager.getRole(p.getUniqueId()) == 4) {
			    			if (args.length <= 1) {
			    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
			    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN setdescription <description>");
			    			} else {
			    				String description = "";
			    				for(int i = 1; i < args.length; i++) {
			    					description = description + args[i] + " ";
			    	    		}
			    				DataManager.nationsData.set("Nations." + DataManager.getNation(p.getUniqueId()) + ".Description", description);
			    				DataManager.saveFiles();
			    				p.sendMessage(ChatColor.GREEN + "Description successfully set to:");
			    				p.sendMessage(description);
			    			}
		    			} else {
		    				p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
	    		    		p.sendMessage(ChatColor.RED + "Only the nation's president can change the description.");
		    			}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("setstatus")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			if (DataManager.getRole(p.getUniqueId()) == 4) {
			    			if (args.length <= 1) {
			    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
			    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN setstatus <public | private>");
			    			} else {
			    				if (args[1].equalsIgnoreCase("public")) {
			    					if (DataManager.isPublic(DataManager.getNation(p.getUniqueId()))) {
			    						p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
			        		    		p.sendMessage(ChatColor.RED + "Your nation is already in public state.");
			    					} else {
			    						DataManager.nationsData.set("Nations." + DataManager.getNation(p.getUniqueId()) + ".Public", true);
			    						DataManager.saveFiles();
			    						p.sendMessage(ChatColor.GREEN + "status succesfully changed to " + ChatColor.DARK_GREEN + "public" + ChatColor.GREEN + ".");
			    					}
			    				} else if (args[1].equalsIgnoreCase("private")) {
			    					if (DataManager.isPublic(DataManager.getNation(p.getUniqueId()))) {
			    						DataManager.nationsData.set("Nations." + DataManager.getNation(p.getUniqueId()) + ".Public", false);
			    						DataManager.saveFiles();
			    						p.sendMessage(ChatColor.GREEN + "status succesfully changed to " + ChatColor.DARK_GREEN + "private" + ChatColor.GREEN + ".");
			    					} else {
			    						p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
			        		    		p.sendMessage(ChatColor.RED + "Your nation is already in private state.");
			    					}
			    				} else {
			    					p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
				    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN setstatus <public | private>");
			    				}
			    			}
		    			} else {
		    				p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
	    		    		p.sendMessage(ChatColor.RED + "Only the nation's president can change the status.");
		    			}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("join")) {
	    			if (args.length <= 1) {
	    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
	    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN join <nation name>");
	    			} else {
	    				if (DataManager.hasNation(p.getUniqueId())) {
	    					p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "You are already member of another nation.");
	    				} else {
	    					if (DataManager.nationsExists(args[1])) {
		    					if (DataManager.isFinished(args[1])) {
		    						if (DataManager.isPublic(args[1])) {
		    							DataManager.setMember(p.getUniqueId(), args[1]);
		    							p.sendMessage(ChatColor.GREEN + "You successfully joined the nation " + ChatColor.DARK_GREEN + args[1] + ChatColor.GREEN + ".");
		    						} else {
		    							if (DataManager.isAllowed(p.getUniqueId(), args[1])) {
		    								DataManager.setMember(p.getUniqueId(), args[1]);
		    								p.sendMessage(ChatColor.GREEN + "You successfully joined the nation " + ChatColor.DARK_GREEN + args[1] + ChatColor.GREEN + ".");
		    							} else {
		    								p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
					    		    		p.sendMessage(ChatColor.RED + "You are not allowed to join this nation.");
		    							}
		    						}
		    					} else {
		    						p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
			    		    		p.sendMessage(ChatColor.RED + "This nation in not yet finished.");
		    					}
	    					} else {
	    						p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
		    		    		p.sendMessage(ChatColor.RED + "Could not find the nation " + ChatColor.DARK_RED + args[1] + ChatColor.RED + ".");
	    					}
	    				}
	    			}
	    		} else if (args[0].equalsIgnoreCase("leave")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
	    				DataManager.removePlayer(p.getUniqueId(), DataManager.getNation(p.getUniqueId()));
	    				p.sendMessage(ChatColor.GREEN + "You have successfully left the nation");
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("allow")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			if (DataManager.getRole(p.getUniqueId()) >= 3) {
		    				if (args.length <= 1) {
			    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
			    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN allow <player name>");
			    			} else {
			    				if (DataManager.isPublic(DataManager.getNation(p.getUniqueId()))) {
			    					p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
			    		    		p.sendMessage(ChatColor.RED + "Your nation is public to everyone.");
			    				} else {
			    					if (DataManager.getUuid(args[1]) == null) {
			    						p.sendMessage(ChatColor.RED + "§lCannot find player!");
				    		    		p.sendMessage(ChatColor.RED + "The specified player cannot be found.");
			    					} else {
			    						if (DataManager.isAllowed(DataManager.getUuid(args[1]), DataManager.getNation(p.getUniqueId()))) {
			    							p.sendMessage(ChatColor.RED + "§lCannot find player!");
					    		    		p.sendMessage(ChatColor.RED + "This player is already allowed to join your nation.");
			    						} else {
			    							DataManager.setAllowed(DataManager.getUuid(args[1]), DataManager.getNation(p.getUniqueId()));
			    							p.sendMessage(ChatColor.GREEN + "Succesfully allowed " + ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " to join your nation.");
			    						}
			    					}
			    				}
			    			}
		    			} else {
		    				p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
	    		    		p.sendMessage(ChatColor.RED + "Only the nation's president and vice-president can allow and disallow players.");
		    			}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("disallow")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			if (DataManager.getRole(p.getUniqueId()) >= 3) {
		    				if (args.length <= 1) {
			    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
			    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN allow <player name>");
			    			} else {
			    				if (DataManager.getUuid(args[1]) == null) {
		    						p.sendMessage(ChatColor.RED + "§lCannot find player!");
			    		    		p.sendMessage(ChatColor.RED + "The specified player cannot be found.");
		    					} else {
		    						if (DataManager.isAllowed(DataManager.getUuid(args[1]), DataManager.getNation(p.getUniqueId()))) {
		    							List<String> allowed = DataManager.nationsData.getStringList("Nations." + DataManager.getNation(p.getUniqueId()) + ".Allowed");
		    							allowed.remove(DataManager.getUuid(args[1]).toString());
		    							DataManager.nationsData.set("Nations." + DataManager.getNation(p.getUniqueId()) + ".Allowed", allowed);
		    							DataManager.saveFiles();
		    							p.sendMessage(ChatColor.GREEN + "Succesfully disallowed " + ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " to join your nation.");
		    						} else {
		    							p.sendMessage(ChatColor.RED + "§lCannot find player!");
				    		    		p.sendMessage(ChatColor.RED + "This player is already not allowed to join your nation.");
		    						}
		    					}
			    			}
			    		} else {
			    			p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
	    		    		p.sendMessage(ChatColor.RED + "Only the nation's president and vice-president can allow and disallow players.");
			    		}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("invite")) {
	    			if (args.length <= 1) {
	    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
	    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN invite <player name>");
	    			} else {
		    			if (DataManager.hasNation(p.getUniqueId())) {
		    				if (DataManager.isPublic(DataManager.getNation(p.getUniqueId()))) {
		    					if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
		    						inviteList.put(Bukkit.getPlayer(args[1]), DataManager.getNation(p.getUniqueId()));
		    						Bukkit.getPlayer(args[1]).sendMessage(ChatColor.AQUA + "You are invited to the nation " + ChatColor.GOLD + DataManager.getNation(p.getUniqueId()) + ChatColor.AQUA + ". Use " + ChatColor.GOLD + "/TCN accept" + ChatColor.AQUA + " or " + ChatColor.GOLD + "/TCN deny" + ChatColor.AQUA + ".");
		    						p.sendMessage(ChatColor.GREEN + "Invite succesfully send to " + Bukkit.getPlayer(args[1]).getName());
		    					} else {
		    						p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
			    		    		p.sendMessage(ChatColor.RED + "The player you want to invite is not online.");
		    					}
		    				} else {
		    					if (DataManager.getRole(p.getUniqueId()) >= 3) {
		    						if (Bukkit.getOfflinePlayer(args[1]).isOnline()) {
		    							inviteList.put(Bukkit.getPlayer(args[1]), DataManager.getNation(p.getUniqueId()));
			    						Bukkit.getPlayer(args[1]).sendMessage(ChatColor.AQUA + "You are invited to the nation " + ChatColor.GOLD + DataManager.getNation(p.getUniqueId()) + ChatColor.AQUA + ". Use " + ChatColor.GOLD + "/TCN accept" + ChatColor.AQUA + " or " + ChatColor.GOLD + "/TCN deny" + ChatColor.AQUA + ".");
			    						p.sendMessage(ChatColor.GREEN + "Invite succesfully send to " + Bukkit.getPlayer(args[1]).getName());
			    					} else {
			    						p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
				    		    		p.sendMessage(ChatColor.RED + "The player you want to invite is not online.");
			    					}
		    					} else {
				    				p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
			    		    		p.sendMessage(ChatColor.RED + "Only the nation's president and vice-president can invite players in private state.");
				    			}
		    				}
		    			} else {
		    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
		    			}
	    			}
	    		} else if (args[0].equalsIgnoreCase("kick")) {
	    			if (args.length <= 1) {
	    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
	    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN kick <player name>");
	    			} else {
	    				if (DataManager.hasNation(p.getUniqueId())) {
		    				if (DataManager.getUuid(args[1]) == null) {
		    					p.sendMessage(ChatColor.RED + "§lCan't kick this player!");
		    		    		p.sendMessage(ChatColor.RED + "This player is not a member of your nation.");
		    				} else {
			    				if (DataManager.playerData.contains(DataManager.getUuid(args[1]).toString())) {
			    					if (DataManager.getNation(DataManager.getUuid(args[1])).equals(DataManager.getNation(p.getUniqueId()))) {
			    						if (DataManager.getRole(p.getUniqueId()) == 4) {
			    							DataManager.removePlayer(DataManager.getUuid(args[1]), DataManager.getNation(p.getUniqueId()));
			    							p.sendMessage(ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " has been successfully kicked out of the nation!");
			    						} else {
			    							p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
					    		    		p.sendMessage(ChatColor.RED + "Only the nation's president can kick players.");
			    						}
			    					} else {
			    						p.sendMessage(ChatColor.RED + "§lCan't kick this player!");
				    		    		p.sendMessage(ChatColor.RED + "This player is not a member of your nation.");
			    					}
			    				} else {
			    					p.sendMessage(ChatColor.RED + "§lCan't kick this player!");
			    		    		p.sendMessage(ChatColor.RED + "This player is not a member of your nation.");
			    				}
		    				}
		    			} else {
		    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
		    			}
	    			}
	    		} else if (args[0].equalsIgnoreCase("accept")) {
	    			if (inviteList.containsKey(p)) {
	    				Bukkit.dispatchCommand(p, "tcn join " + inviteList.get(p));
	    				inviteList.remove(p);
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You don't have any invites.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("deny")) {
	    			if (inviteList.containsKey(p)) {
	    				inviteList.remove(p);
	    				p.sendMessage(ChatColor.GREEN + "Invite successfully denied.");
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You don't have any invites.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("promote")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			if (DataManager.getRole(p.getUniqueId()) >= 3) {
		    				if (args.length <= 1) {
			    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
			    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN promote <player name>");
			    			} else {
			    				if (DataManager.getUuid(args[1]) == null) {
			    					p.sendMessage(ChatColor.RED + "§lCan't promote this player!");
			    		    		p.sendMessage(ChatColor.RED + "This player is not a member of your nation.");
			    				} else {
			    					if (DataManager.hasNation(DataManager.getUuid(args[1]))) {
				    					if (DataManager.getNation(DataManager.getUuid(args[1])).equals(DataManager.getNation(p.getUniqueId()))) {
				    						if (DataManager.getRole(DataManager.getUuid(args[1])) == 4) {
				    							p.sendMessage(ChatColor.RED + "§lCan't promote this player!");
						    		    		p.sendMessage(ChatColor.RED + "This player allready has the highest rank.");
				    						} else if (DataManager.getRole(DataManager.getUuid(args[1])) == 3) {
				    							if (DataManager.getRole(p.getUniqueId()) == 4) {
				    								p.sendMessage(ChatColor.RED + "§lCan't promote this player!");
						    		    			p.sendMessage(ChatColor.RED + "You need to resign to do this.");
				    							} else {
				    								p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
							    		    		p.sendMessage(ChatColor.RED + "Only the nation's president can kick players.");
				    							}
				    						} else if (DataManager.getRole(DataManager.getUuid(args[1])) == 2) {
				    							if (DataManager.getRole(p.getUniqueId()) == 4) {
				    								if (!DataManager.nationsData.contains("Nations." + DataManager.getNation(DataManager.getUuid(args[1])) + ".VicePresident")) {
				    									DataManager.setVicePresident(DataManager.getUuid(args[1]), DataManager.getNation(DataManager.getUuid(args[1])));
				    									p.sendMessage(ChatColor.GREEN + "Succesfully promoted " + ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " to " + ChatColor.DARK_GREEN + "vice-president" + ChatColor.GREEN + ".");
				    								} else {
				    									p.sendMessage(ChatColor.RED + "§lCan't promote this player!");
								    		    		p.sendMessage(ChatColor.RED + "There allready is a vice-president.");
				    								}
				    							} else {
				    								p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
							    		    		p.sendMessage(ChatColor.RED + "Only the nation's president can kick players.");
				    							}
				    						} else if (DataManager.getRole(DataManager.getUuid(args[1])) <= 1) {
				    							List<String> counsils = DataManager.nationsData.getStringList("Nations." + DataManager.getNation(DataManager.getUuid(args[1])) + ".Counsils");
				    							if (counsils.size() != 5) {
				    								DataManager.setCounsil(DataManager.getUuid(args[1]), DataManager.getNation(DataManager.getUuid(args[1])));
				    								p.sendMessage(ChatColor.GREEN + "Succesfully promoted " + ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " to " + ChatColor.DARK_GREEN + "counsil" + ChatColor.GREEN + ".");
				    							} else {
				    								p.sendMessage(ChatColor.RED + "§lCan't promote this player!");
							    		    		p.sendMessage(ChatColor.RED + "There are allready 5 counsils.");
				    							}
				    						}
				    					} else {
				    						p.sendMessage(ChatColor.RED + "§lCan't promote this player!");
					    		    		p.sendMessage(ChatColor.RED + "This player is not a member of your nation.");
				    					}
			    					} else {
			    						p.sendMessage(ChatColor.RED + "§lCan't promote this player!");
				    		    		p.sendMessage(ChatColor.RED + "This player is not a member of your nation.");
			    					}
			    				}
			    			}
		    			} else {
		    				p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
	    		    		p.sendMessage(ChatColor.RED + "Only the nation's president and vice-president can promote players.");
		    			}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("demote")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			if (DataManager.getRole(p.getUniqueId()) >= 3) {
		    				if (args.length <= 1) {
			    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
			    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN demote <player name>");
			    			} else {
			    				if (DataManager.getUuid(args[1]) == null) {
			    					p.sendMessage(ChatColor.RED + "§lCan't promote this player!");
			    		    		p.sendMessage(ChatColor.RED + "This player is not a member of your nation.");
			    				} else {
			    					if (DataManager.hasNation(DataManager.getUuid(args[1]))) {
				    					if (DataManager.getNation(DataManager.getUuid(args[1])).equals(DataManager.getNation(p.getUniqueId()))) {
				    						if (DataManager.getRole(DataManager.getUuid(args[1])) == 1) {
				    							DataManager.setMember(DataManager.getUuid(args[1]), DataManager.getNation(DataManager.getUuid(args[1])));
				    							p.sendMessage(ChatColor.GREEN + "Succesfully demoted " + ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " to " + ChatColor.DARK_GREEN + "member" + ChatColor.GREEN + ".");
				    						} else if (DataManager.getRole(DataManager.getUuid(args[1])) == 2) {
				    							if (DataManager.getRole(p.getUniqueId()) == 4) {
				    								DataManager.setMember(DataManager.getUuid(args[1]), DataManager.getNation(DataManager.getUuid(args[1])));
					    							p.sendMessage(ChatColor.GREEN + "Succesfully demoted " + ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " to " + ChatColor.DARK_GREEN + "member" + ChatColor.GREEN + ".");
				    							} else {
				    								DataManager.setMember(DataManager.getUuid(args[1]), DataManager.getNation(DataManager.getUuid(args[1])));
					    							p.sendMessage(ChatColor.GREEN + "Succesfully demoted " + ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " to " + ChatColor.DARK_GREEN + "member" + ChatColor.GREEN + ".");
				    							}
				    						} else if (DataManager.getRole(DataManager.getUuid(args[1])) == 3) {
				    							if (DataManager.getRole(p.getUniqueId()) == 4) {
				    								List<String> counsils = DataManager.nationsData.getStringList("Nations." + DataManager.getNation(DataManager.getUuid(args[1])) + ".Counsils");
					    							if (counsils.size() != 5) {
					    								DataManager.setCounsil(DataManager.getUuid(args[1]), DataManager.getNation(DataManager.getUuid(args[1])));
						    							p.sendMessage(ChatColor.GREEN + "Succesfully demoted " + ChatColor.DARK_GREEN + DataManager.getName(DataManager.getUuid(args[1]).toString()) + ChatColor.GREEN + " to " + ChatColor.DARK_GREEN + "counsil" + ChatColor.GREEN + ".");
					    							} else {
					    								p.sendMessage(ChatColor.RED + "§lCan't promote this player!");
								    		    		p.sendMessage(ChatColor.RED + "There are allready 5 counsils.");
					    							}
				    							} else {
				    								p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
							    		    		p.sendMessage(ChatColor.RED + "Only the nation's president can demote players.");
				    							}
				    						} else if (DataManager.getRole(DataManager.getUuid(args[1])) == 4) {
			    								p.sendMessage(ChatColor.RED + "§lCan't demote this player!");
					    		    			p.sendMessage(ChatColor.RED + "You need to resign to do this.");
				    						} else {
				    							p.sendMessage(ChatColor.RED + "§lCan't demote this player!");
						    		    		p.sendMessage(ChatColor.RED + "This player allready has the lowest rank.");
				    						}
				    					} else {
				    						p.sendMessage(ChatColor.RED + "§lCan't demote this player!");
					    		    		p.sendMessage(ChatColor.RED + "This player is not a member of your nation.");
				    					}
			    					} else {
			    						p.sendMessage(ChatColor.RED + "§lCan't demote this player!");
				    		    		p.sendMessage(ChatColor.RED + "This player is not a member of your nation.");
			    					}
			    				}
			    			}
		    			} else {
		    				p.sendMessage(ChatColor.RED + "§lNot enough rights to do this!");
	    		    		p.sendMessage(ChatColor.RED + "Only the nation's president and vice-president can demote players.");
		    			}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("resign")) {
	    			String nation = DataManager.getNation(p.getUniqueId());
	    			if (DataManager.hasNation(p.getUniqueId())) {
	    				if (DataManager.getRole(p.getUniqueId()) >= 1) {
	    					DataManager.removePlayer(p.getUniqueId(), nation);
	    					if (DataManager.nationsExists(nation)) {
	    						DataManager.setMember(p.getUniqueId(), DataManager.getNation(p.getUniqueId()));
	    					}
	    					p.sendMessage(ChatColor.GREEN + "You have successfully resigned from your position.");
	    				} else {
	    					p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "You are not president, vice-president or a counsil of your nation.");
	    				}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    			
//===========================================//
//											 //
//					 War					 //
//											 //
//===========================================//
	    			
	    		} else if (args[0].equalsIgnoreCase("war")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
	    				if (DataManager.getRole(p.getUniqueId()) == 4) {
			    			if (args.length <= 1) {
			    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
			    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN war <nation name | accept | deny | end>");
			    			} else {
			    	    		if (args[1].equalsIgnoreCase("accept")) {
					    			if (warList.containsKey(DataManager.getNation(p.getUniqueId()))) {
					    				DataManager.setWar(DataManager.getNation(p.getUniqueId()), warList.get(DataManager.getNation(p.getUniqueId())));
					    				warList.remove(DataManager.getNation(p.getUniqueId()));
					    				p.sendMessage(ChatColor.GREEN + "War request succesfully accepted.");
					    			} else {
					    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
				    		    		p.sendMessage(ChatColor.RED + "You don't have war requests.");
					    			}
					    		} else if (args[1].equalsIgnoreCase("deny")) {
					    			if (warList.containsKey(DataManager.getNation(p.getUniqueId()))) {
					    				warList.remove(DataManager.getNation(p.getUniqueId()));
					    				p.sendMessage(ChatColor.GREEN + "War request successfully denied.");
					    			} else {
					    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
				    		    		p.sendMessage(ChatColor.RED + "You don't have war requests.");
					    			}
					    		} else if (args[1].equalsIgnoreCase("end")) {
					    			if (args.length <= 2) { 
					    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
					    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN war end <nation name>");
					    			} else {
					    	    		List<String> enemies = DataManager.nationsData.getStringList("Nations." + DataManager.getNation(p.getUniqueId()) + ".Enemies");
						    			if (enemies.contains(args[2])) {
						    				DataManager.endWar(DataManager.getNation(p.getUniqueId()), args[2]);
						    				p.sendMessage(ChatColor.GREEN + "Succesfully ended the war with " + ChatColor.DARK_GREEN + args[2] + ChatColor.GREEN + ".");
						    			} else {
						    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
					    		    		p.sendMessage(ChatColor.RED + "You are not in war with this nation.");
						    			}
					    			}
					    		} else {
					    			if (DataManager.getNation(p.getUniqueId()) == args[1]) {
					    				if (DataManager.nationsExists(args[1])) {
					    					if (!DataManager.isEnemie(DataManager.getNation(p.getUniqueId()), DataManager.getNation(UUID.fromString(DataManager.getPresident(args[1]))))) {
					    						if (!DataManager.isAlly(DataManager.getNation(p.getUniqueId()), DataManager.getNation(UUID.fromString(DataManager.getPresident(args[1]))))) {
					    							if (DataManager.getTreasuryAmount(DataManager.getNation(p.getUniqueId())) >= DataManager.getWarPrice()) {
								    					if (Bukkit.getOfflinePlayer(UUID.fromString(DataManager.getPresident(args[1]))).isOnline()) {
								    						Bukkit.getPlayer(UUID.fromString(DataManager.getPresident(args[1]))).sendMessage(ChatColor.GOLD + DataManager.getNation(p.getUniqueId()) + ChatColor.AQUA + " has declared war. Use " + ChatColor.GOLD + "/TCN war accept" + ChatColor.AQUA + " or " + ChatColor.GOLD + "/TCN war deny" + ChatColor.AQUA + ".");
								    					}
								    					warList.put(DataManager.getNation(UUID.fromString(DataManager.getPresident(args[1]))), DataManager.getNation(p.getUniqueId()));
								    					p.sendMessage(ChatColor.GREEN + "War request succesfully send to " + ChatColor.DARK_GREEN + args[1]);
					    							} else {
					    								p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
								    		    		p.sendMessage(ChatColor.RED + "You dont have enough treasury.");
					    							}
					    						} else {
					    							p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
							    		    		p.sendMessage(ChatColor.RED + "You are allies with this nation.");
					    						}
					    					} else {
					    						p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
						    		    		p.sendMessage(ChatColor.RED + "You are allready in war with this nation.");
					    					}
					    				} else {
					    					p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
						    	    		p.sendMessage(ChatColor.RED + "Nation does not exist.");
					    				}
					    			} else {
					    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
				    		    		p.sendMessage(ChatColor.RED + "You cant go in war with your own nation.");
					    			}
				    			}
			    			}
		    			} else {
		    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "You are not president of your nation.");
		    			}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("enemies")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			List<String> enemies = DataManager.nationsData.getStringList("Nations." + DataManager.getNation(p.getUniqueId()) + ".Enemies");
		    			String enemiesString = "";
		    			for (int i = 0; i < enemies.size(); i++) {
		    				enemiesString = enemiesString + enemies.get(i) + ", "; 
		    			}
		    			p.sendMessage(ChatColor.GOLD + "Your current enemies are:");
		    			p.sendMessage(ChatColor.AQUA + enemiesString);
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("ally")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			if (DataManager.getRole(p.getUniqueId()) == 4) {
			    			if (args.length <= 1) {
			    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
			    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN ally <nation name | accept | deny | end>");
			    			} else {
			    	    		if (args[1].equalsIgnoreCase("accept")) {
					    			if (allyList.containsKey(DataManager.getNation(p.getUniqueId()))) {
					    				DataManager.setAlly(DataManager.getNation(p.getUniqueId()), allyList.get(DataManager.getNation(p.getUniqueId())));
					    				allyList.remove(DataManager.getNation(p.getUniqueId()));
					    				p.sendMessage(ChatColor.GREEN + "Ally request succesfully accepted.");
					    			} else {
					    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
				    		    		p.sendMessage(ChatColor.RED + "You don't have any ally requests.");
					    			}
					    		} else if (args[1].equalsIgnoreCase("deny")) {
					    			if (allyList.containsKey(DataManager.getNation(p.getUniqueId()))) {
					    				allyList.remove(DataManager.getNation(p.getUniqueId()));
					    				p.sendMessage(ChatColor.GREEN + "Ally request successfully denied.");
					    			} else {
					    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
				    		    		p.sendMessage(ChatColor.RED + "You don't have any ally requests.");
					    			}
					    		} else if (args[1].equalsIgnoreCase("end")) {
					    			if (args.length <= 2) {
					    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
					    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN ally end <nation name>");
					    			} else {
					    	    		List<String> allys = DataManager.nationsData.getStringList("Nations." + DataManager.getNation(p.getUniqueId()) + ".Allys");
						    			if (allys.contains(args[2])) {
						    				DataManager.endAlly(DataManager.getNation(p.getUniqueId()), args[2]);
						    				p.sendMessage(ChatColor.GREEN + "Succesfully ended the ally with " + ChatColor.DARK_GREEN + args[2] + ChatColor.GREEN + ".");
						    			} else {
						    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
					    		    		p.sendMessage(ChatColor.RED + "You are not allies with this nation.");
						    			}
					    			}
					    		} else {
				    				if (DataManager.nationsExists(args[1])) {
				    					if (!DataManager.isAlly(DataManager.getNation(p.getUniqueId()), DataManager.getNation(UUID.fromString(DataManager.getPresident(args[1]))))) {
				    						if (!DataManager.isEnemie(DataManager.getNation(p.getUniqueId()), DataManager.getNation(UUID.fromString(DataManager.getPresident(args[1]))))) {
						    					if (Bukkit.getOfflinePlayer(UUID.fromString(DataManager.getPresident(args[1]))).isOnline()) {
						    						Bukkit.getPlayer(UUID.fromString(DataManager.getPresident(args[1]))).sendMessage(ChatColor.GOLD + DataManager.getNation(p.getUniqueId()) + ChatColor.AQUA + " wants to be allies. Use " + ChatColor.GOLD + "/TCN ally accept" + ChatColor.AQUA + " or " + ChatColor.GOLD + "/TCN ally deny" + ChatColor.AQUA + ".");
						    					}
						    					allyList.put(DataManager.getNation(UUID.fromString(DataManager.getPresident(args[1]))), DataManager.getNation(p.getUniqueId()));
						    					p.sendMessage(ChatColor.GREEN + "Ally request succesfully send to " + ChatColor.DARK_GREEN + args[1]);
				    						} else {
				    							p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
						    		    		p.sendMessage(ChatColor.RED + "You are in war with this nation.");
				    						}
				    					} else {
				    						p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
					    		    		p.sendMessage(ChatColor.RED + "You are allready allies with this nation.");
				    					}
				    				} else {
				    					p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
					    	    		p.sendMessage(ChatColor.RED + "Nation does not exist.");
				    				}
				    			}
			    			}
		    			} else {
		    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
	    		    		p.sendMessage(ChatColor.RED + "You are not president of your nation.");
		    			}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("allies")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			List<String> allies = DataManager.nationsData.getStringList("Nations." + DataManager.getNation(p.getUniqueId()) + ".Allys");
		    			String alliesString = "";
		    			for (int i = 0; i < allies.size(); i++) {
		    				alliesString = alliesString + allies.get(i) + ", "; 
		    			}
		    			p.sendMessage(ChatColor.GOLD + "Your current allies are:");
		    			p.sendMessage(ChatColor.AQUA + alliesString);
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    			
//===========================================//
//											 //
//					Treasury				 //
//											 //
//===========================================//
	    			
	    		} else if (args[0].equalsIgnoreCase("treasury")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			if (args.length <= 1) {
		    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
		    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN treasury <amount | donate | give | send>");
		    			} else {
		    				if (args[1].equalsIgnoreCase("amount")) {
		    					p.sendMessage(ChatColor.AQUA + "You currently have " + ChatColor.GOLD + DataManager.getTreasuryAmount(DataManager.getNation(p.getUniqueId())) + ChatColor.AQUA + " dollars in the treasury.");
		    				} else if (args[1].equalsIgnoreCase("donate")) {
		    					if (args.length <= 2) {
				    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
				    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN treasury donate <amount>");
				    			} else {
				    				if (isInt(args[2]) && Integer.parseInt(args[2]) > 0) {
				    					try {
											if (Economy.hasEnough(p.getName(), Integer.parseInt(args[2]))) {
												Economy.subtract(p.getName(), Integer.parseInt(args[2]));
												DataManager.addTreasury(DataManager.getNation(p.getUniqueId()), Integer.parseInt(args[2]));
												DataManager.setDonator(p.getUniqueId(), DataManager.getNation(p.getUniqueId()));
												p.sendMessage(ChatColor.GREEN + "Succesfully donated " + ChatColor.DARK_GREEN + args[2] + ChatColor.GREEN + " to your nation.");
											} else {
												p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
												p.sendMessage(ChatColor.RED + "Your do not have enough monney.");
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
				    				} else {
		    							p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
					    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN treasury donate <amount>");
		    						}
				    			}
		    				} else if (args[1].equalsIgnoreCase("give")) {
		    					if (args.length <= 2) {
				    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
				    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN treasury give <player name> <amount>");
				    			} else {
				    				if (DataManager.getRole(p.getUniqueId()) == 4) {
				    					if (Economy.playerExists(args[2])) {
					    					if (isInt(args[3]) && Integer.parseInt(args[3]) > 0) {
				    							if (DataManager.getTreasuryAmount(DataManager.getNation(p.getUniqueId())) >= Integer.parseInt(args[3])) {
						    						DataManager.addTreasury(DataManager.getNation(p.getUniqueId()), -Integer.parseInt(args[3]));
						    						try {
														Economy.add(args[2], Integer.parseInt(args[3]));
													} catch (Exception e) {
														e.printStackTrace();
													}
						    						p.sendMessage(ChatColor.GREEN + "Succesfully gave " + ChatColor.DARK_GREEN + args[3] + ChatColor.GREEN + " to " + ChatColor.DARK_GREEN + args[2]);
				    							} else {
				    								p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
							    		    		p.sendMessage(ChatColor.RED + "Your nation does not have enough treasury.");	
				    							}
				    						} else {
				    							p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
							    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN treasury send <nation name> <amount>");
				    						}
				    					} else {
				    						p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
					    		    		p.sendMessage(ChatColor.RED + "Could not find this player.");
				    					}
				    				} else {
					    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
				    		    		p.sendMessage(ChatColor.RED + "You are not president of your nation.");
					    			}
				    			}
		    				} else if (args[1].equalsIgnoreCase("send")) {
		    					if (args.length <= 2) {
				    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
				    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN treasury send <nation name> <amount>");
				    			} else {
				    				if (DataManager.getRole(p.getUniqueId()) == 4) {
				    					if (DataManager.nationsExists(args[2])) {
				    						if (isInt(args[3]) && Integer.parseInt(args[3]) > 0) {
				    							if (DataManager.getTreasuryAmount(DataManager.getNation(p.getUniqueId())) >= Integer.parseInt(args[3])) {
						    						DataManager.addTreasury(DataManager.getNation(p.getUniqueId()), -Integer.parseInt(args[3]));
						    						DataManager.addTreasury(args[2], Integer.parseInt(args[3]));
						    						p.sendMessage(ChatColor.GREEN + "Succesfully gave " + ChatColor.DARK_GREEN + args[3] + ChatColor.GREEN + " to " + ChatColor.DARK_GREEN + args[2]);
				    							} else {
				    								p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
							    		    		p.sendMessage(ChatColor.RED + "Your nation does not have enough treasury.");	
				    							}
				    						} else {
				    							p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
							    	    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN treasury send <nation name> <amount>");
				    						}
				    					} else {
				    						p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
						    	    		p.sendMessage(ChatColor.RED + "Nation does not exist.");
				    					}
				    				} else {
					    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
				    		    		p.sendMessage(ChatColor.RED + "You are not president of your nation.");
					    			}
				    			}
		    				}
		    			}
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("donators")) {
	    			if (DataManager.hasNation(p.getUniqueId())) {
		    			List<String> donators = DataManager.nationsData.getStringList("Nations." + DataManager.getNation(p.getUniqueId()) + ".Donators");
		    			String donatorsString = "";
		    			for (int i = 0; i < donators.size(); i++) {
		    				donatorsString = donatorsString + DataManager.getName(donators.get(i)) + ", ";
		    			}
		    			p.sendMessage(ChatColor.GOLD + "The current donators are:");
		    			p.sendMessage(ChatColor.AQUA + donatorsString);
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lYou cannot do this!");
    		    		p.sendMessage(ChatColor.RED + "You are not a member of a nation.");
	    			}
	    		
//===========================================//
//				 							 //
//					 Other					 //
//											 //
//===========================================//
	    		
	    		} else if (args[0].equalsIgnoreCase("adminpanel")) {
	    			if (p.isOp()) {
	    				ItemStack close = new ItemStack(Material.BARRIER);
		    			ItemMeta closeMeta = close.getItemMeta();
		    			closeMeta.setDisplayName(ChatColor.RED + "§lClose");
		    			close.setItemMeta(closeMeta);
		    			
		    			ItemStack standard = new ItemStack(Material.END_PORTAL_FRAME);
		    			ItemMeta standardMeta = standard.getItemMeta();
		    			standardMeta.setDisplayName(ChatColor.GOLD + "Standard nation price.");
		    			standard.setItemMeta(standardMeta);
		    			
		    			ItemStack block = new ItemStack(Material.GRASS_BLOCK);
		    			ItemMeta blockMeta = block.getItemMeta();
		    			blockMeta.setDisplayName(ChatColor.GOLD + "Price per expending 1 block of nation.");
		    			block.setItemMeta(blockMeta);
		    			
		    			ItemStack war = new ItemStack(Material.IRON_SWORD);
		    			ItemMeta warMeta = war.getItemMeta();
		    			warMeta.setDisplayName(ChatColor.GOLD + "Price to start a war.");
		    			war.setItemMeta(warMeta);
		    			
		    			Inventory adminPanel = Bukkit.createInventory(null, 36, ChatColor.AQUA + "§lAdmin Panel");
		    			adminPanel.setItem(11, standard);
		    			adminPanel.setItem(13, block);
		    			adminPanel.setItem(15, war);
		    			adminPanel.setItem(31, close);
		    			
		    			p.openInventory(adminPanel);
	    			} else {
	    				p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
	    	    		p.sendMessage(ChatColor.RED + "You do not have the permissions to do this.");
	    			}
	    		} else if (args[0].equalsIgnoreCase("help")) {
	    			if (args.length <= 1) {
	    				p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "Nations help" + ChatColor.AQUA + "]---=+=---");
	    				p.sendMessage(ChatColor.GOLD + "/TCN create " + ChatColor.AQUA + "Create a nation.");
	    				p.sendMessage(ChatColor.GOLD + "/TCN builder " + ChatColor.AQUA + "Make someone a builder of your nation.");
	    				p.sendMessage(ChatColor.GOLD + "/TCN finish " + ChatColor.AQUA + "Finish building your nation.");
	    				p.sendMessage(ChatColor.GOLD + "/TCN list " + ChatColor.AQUA + "List of nations.");
	    				p.sendMessage(ChatColor.GOLD + "/TCN info " + ChatColor.AQUA + "Get info of a nation");
	    				p.sendMessage(ChatColor.GOLD + "/TCN setdescription " + ChatColor.AQUA + "Set the description of your nation.");
	    				p.sendMessage(ChatColor.GOLD + "/TCN setstatus " + ChatColor.AQUA + "Set the status of your nation.");
	    				p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "/TCN help 2" + ChatColor.AQUA + "]---=+=---");
	    			} else {
	    				if (args[1].contains("1")) {
	    					p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "Nations help" + ChatColor.AQUA + "]---=+=---");
		    				p.sendMessage(ChatColor.GOLD + "/TCN create " + ChatColor.AQUA + "Create a nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN builder " + ChatColor.AQUA + "Make someone a builder of your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN finish " + ChatColor.AQUA + "Finish building your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN list " + ChatColor.AQUA + "List of nations.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN info " + ChatColor.AQUA + "Get info of a nation");
		    				p.sendMessage(ChatColor.GOLD + "/TCN setdescription " + ChatColor.AQUA + "Set the description of your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN setstatus " + ChatColor.AQUA + "Set the status of your nation.");
		    				p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "/TCN help 2" + ChatColor.AQUA + "]---=+=---");
	    				} else if (args[1].contains("2")) {
	    					p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "Nations help" + ChatColor.AQUA + "]---=+=---");
		    				p.sendMessage(ChatColor.GOLD + "/TCN join " + ChatColor.AQUA + "Join a nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN leave " + ChatColor.AQUA + "Leave your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN allow " + ChatColor.AQUA + "Allow a player to join.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN disallow " + ChatColor.AQUA + "Disallow a player to join.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN invite " + ChatColor.AQUA + "Invite a player to your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN kick " + ChatColor.AQUA + "Kick a player from your nation");
		    				p.sendMessage(ChatColor.GOLD + "/TCN accept " + ChatColor.AQUA + "Accepts an invite.");
		    				p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "/TCN help 3" + ChatColor.AQUA + "]---=+=---");
	    				} else if (args[1].contains("3")) {
	    					p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "Nations help" + ChatColor.AQUA + "]---=+=---");
		    				p.sendMessage(ChatColor.GOLD + "/TCN promote " + ChatColor.AQUA + "Promote a player in your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN demote " + ChatColor.AQUA + "Demote a player in your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN resign " + ChatColor.AQUA + "Resign from your position.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN war " + ChatColor.AQUA + "declair war to other nations.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN enemies " + ChatColor.AQUA + "get a list of your enemies.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN ally " + ChatColor.AQUA + "Be allies with other nations.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN allies " + ChatColor.AQUA + "get a list of your allies.");
		    				p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "/TCN help 4" + ChatColor.AQUA + "]---=+=---");
	    				} else if (args[1].contains("4")) {
	    					p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "Nations help" + ChatColor.AQUA + "]---=+=---");
		    				p.sendMessage(ChatColor.GOLD + "/TCN treasury " + ChatColor.AQUA + "Your nations treasury.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN donators " + ChatColor.AQUA + "Shows a list of who donated to your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN adminpanel " + ChatColor.AQUA + "Admin panel");
		    				p.sendMessage(ChatColor.GOLD + "/TCN help " + ChatColor.AQUA + "Shows a list of commands.");
		    				p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "/TCN help" + ChatColor.AQUA + "]---=+=---");
	    				} else {
	    					p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "Nations help" + ChatColor.AQUA + "]---=+=---");
		    				p.sendMessage(ChatColor.GOLD + "/TCN create " + ChatColor.AQUA + "Create a nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN builder " + ChatColor.AQUA + "Make someone a builder of your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN finish " + ChatColor.AQUA + "Finish building your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN list " + ChatColor.AQUA + "List of nations.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN info " + ChatColor.AQUA + "Get info of a nation");
		    				p.sendMessage(ChatColor.GOLD + "/TCN setdescription " + ChatColor.AQUA + "Set the description of your nation.");
		    				p.sendMessage(ChatColor.GOLD + "/TCN setstatus " + ChatColor.AQUA + "Set the status of your nation.");
		    				p.sendMessage(ChatColor.AQUA +  "---=+=---[" + ChatColor.GOLD + "/TCN help 2" + ChatColor.AQUA + "]---=+=---");
	    				}
	    			}
    			} else {
	    			p.sendMessage(ChatColor.RED + "§lIncorrect argument!");
		    		p.sendMessage(ChatColor.RED + "Use " + ChatColor.DARK_RED + "/TCN help" + ChatColor.RED + " to get a list of the commands you can use!");
	    		}
    		}
    	}
		return false;
    }
	
	public static boolean isInt(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(Exception e) { 
	        return false; 
	    }
	    return true;
	}
	
}
