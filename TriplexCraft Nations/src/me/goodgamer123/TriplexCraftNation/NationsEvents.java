package me.goodgamer123.TriplexCraftNation;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

public class NationsEvents implements Listener {
	
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
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		

		Player p = e.getPlayer();
		if (DataManager.hasNation(p.getUniqueId())) {
			String nation = DataManager.getNation(p.getUniqueId());
			Location loc = e.getPlayer().getLocation();
			
			World world = DataManager.nationsData.getLocation("Nations." + nation + ".Location").getWorld();
			
			RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
			RegionManager regions = container.get(BukkitAdapter.adapt(world));
			ProtectedRegion region = regions.getRegion(nation);
			
			if (region.contains(BlockVector3.at(loc.getX(), loc.getY(), loc.getZ()))) p.setAllowFlight(true); else p.setAllowFlight(false);
		}
	}
	
}
