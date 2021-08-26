package me.goodgamer123.TriplexCraftNation;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class OnPlayerHit implements Listener {
	
	@EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player whoWasHit = (Player) e.getEntity();
            Player whoHit = (Player) e.getDamager();
            if (DataManager.getNation(whoWasHit.getUniqueId()).equals(DataManager.getNation(whoHit.getUniqueId()))) {
            	e.setCancelled(true);
            } else if (DataManager.nationsData.getStringList("Nations." + DataManager.getNation(whoWasHit.getUniqueId()) + ".Allys").contains(DataManager.getNation(whoHit.getUniqueId()))) {
            	e.setCancelled(true);
            }
        }
    }
	
}
