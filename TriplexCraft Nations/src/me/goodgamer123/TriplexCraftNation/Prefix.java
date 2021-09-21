package me.goodgamer123.TriplexCraftNation;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Prefix implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		
		Player p = (Player) e.getPlayer();
		if (DataManager.hasNation(p.getUniqueId())) {
			if (DataManager.getRole(p.getUniqueId()) == 4) e.setFormat("[President of " + DataManager.getNation(p.getUniqueId()) + "] " + e.getFormat());
			else if (DataManager.getRole(p.getUniqueId()) == 3) e.setFormat("[Vice-President of " + DataManager.getNation(p.getUniqueId()) + "] " + e.getFormat());
			else if (DataManager.getRole(p.getUniqueId()) == 2) e.setFormat("[Council of " + DataManager.getNation(p.getUniqueId()) + "] " + e.getFormat());
			else if (DataManager.getRole(p.getUniqueId()) <= 1) e.setFormat("[Member of " + DataManager.getNation(p.getUniqueId()) + "] " + e.getFormat());
		}
	}
	
}
