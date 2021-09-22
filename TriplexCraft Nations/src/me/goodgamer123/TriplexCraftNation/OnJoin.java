package me.goodgamer123.TriplexCraftNation;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if (DataManager.hasNation(e.getPlayer().getUniqueId())) {
			if (DataManager.getRole(e.getPlayer().getUniqueId()) == 4) {
				if (TCN.warList.containsKey(DataManager.getNation(e.getPlayer().getUniqueId()))) {
					e.getPlayer().sendMessage(ChatColor.GOLD + TCN.warList.get(DataManager.getNation(e.getPlayer().getUniqueId())) + ChatColor.AQUA + " has declared war. Use " + ChatColor.GOLD + "/TCN war accept" + ChatColor.AQUA + " or " + ChatColor.GOLD + "/TCN war deny" + ChatColor.AQUA + ".");
				} else if (TCN.allyList.containsKey(DataManager.getNation(e.getPlayer().getUniqueId()))) {
					e.getPlayer().sendMessage(ChatColor.GOLD + TCN.warList.get(DataManager.getNation(e.getPlayer().getUniqueId())) + ChatColor.AQUA + " wants to be allies. Use " + ChatColor.GOLD + "/TCN war accept" + ChatColor.AQUA + " or " + ChatColor.GOLD + "/TCN war deny" + ChatColor.AQUA + ".");
				}
			}
		}
	}
	
}
