package me.goodgamer123.TriplexCraftNation;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

public class DataManager extends JavaPlugin {

	static File nationsDataFile;
	static File playerDataFile;
	static FileConfiguration nationsData;
	static FileConfiguration playerData;
	
	static Plugin MainClass;
	
	static void loadFiles(Plugin plugin) {
		MainClass = plugin;
		
		final File nationsYml = new File(MainClass.getDataFolder() + "/nationsData.yml");
		final File playerYml = new File(MainClass.getDataFolder() + "/playerData.yml");
		
		nationsDataFile = new File(MainClass.getDataFolder(), "/nationsData.yml");
		nationsData = YamlConfiguration.loadConfiguration(nationsDataFile);
		
		playerDataFile = new File(MainClass.getDataFolder(), "/playersData.yml");
		playerData = YamlConfiguration.loadConfiguration(playerDataFile);
		
		if (!nationsYml.exists() || !playerYml.exists()) {
			saveFiles();
		}
	}
	
	static double getMinimumForWar() {
		return 0.00;
	}
	
	static boolean hasNation(UUID uuid) {
		if (playerData.contains(uuid.toString())) {
			return true;
		} else {
			return false;
		}
	}
	
	static boolean isFinished(String Nation) {
		return nationsData.getBoolean("Nations." + Nation + ".Finished");
	}
	
	static boolean isPublic(String Nation) {
		return nationsData.getBoolean("Nations." + Nation + ".Public");
	}
	
	static boolean nationsExists(String Nation) {
		if (nationsData.get("Nations." + Nation) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	static boolean isAllowed(UUID uuid, String Nation) {
		List<String> allowed = nationsData.getStringList("Nations." + Nation + ".Allowed");
		return allowed.contains(uuid.toString());
	}
	
	static String getNation(UUID uuid) {
		if (hasNation(uuid)) {
			return playerData.getString(uuid.toString() + ".Nation");
		} else {
			return null;
		}
	}
	
	static int getRole(UUID uuid) {
		String role = playerData.getString(uuid.toString() + ".Role");
		if (role != null) {
			if (role.equals("President")) return 4;
			else if (role.equals("VicePresident")) return 3;
			else if (role.equals("Counsil")) return 2;
			else if (role.equals("Builder")) return 1;
			else return 0;
		} else {
			return 0;
		}
	}
	
	static String getPresident(String Nation) {
		return nationsData.getString("Nations." + Nation + ".President");
	}
	
	static void setPresident(UUID uuid, String Nation) {
		removePlayer(uuid, Nation);
		
		nationsData.set("Nations." + Nation + ".President", uuid.toString());
		
		playerData.set(uuid.toString() + ".Nation", Nation);
		playerData.set(uuid.toString() + ".Role", "President");
		
		saveFiles();
		
		World world = nationsData.getLocation("Nations." + Nation + ".Location").getWorld();
		
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionManager regions = container.get(BukkitAdapter.adapt(world));
		ProtectedRegion region = regions.getRegion(Nation);
		
		DefaultDomain memberList = region.getMembers();
		memberList.addPlayer(uuid);
	}
	
	static void setVicePresident(UUID uuid, String Nation) {
		removePlayer(uuid, Nation);
		
		nationsData.set("Nations." + Nation + ".VicePresident", uuid.toString());
		
		playerData.set(uuid.toString() + ".Nation", Nation);
		playerData.set(uuid.toString() + ".Role", "VicePresident");
		
		saveFiles();
		
		World world = nationsData.getLocation("Nations." + Nation + ".Location").getWorld();
		
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionManager regions = container.get(BukkitAdapter.adapt(world));
		ProtectedRegion region = regions.getRegion(Nation);
		
		DefaultDomain memberList = region.getMembers();
		memberList.addPlayer(uuid);
	}
	
	static void setCounsil(UUID uuid, String Nation) {
		removePlayer(uuid, Nation);
		
		List<String> counsils = nationsData.getStringList("Nations." + Nation + ".Counsils");
		if (!counsils.contains(uuid.toString())) counsils.add(uuid.toString());
		
		nationsData.set("Nations." + Nation + ".Counsils", counsils);
		
		playerData.set(uuid.toString() + ".Nation", Nation);
		playerData.set(uuid.toString() + ".Role", "Counsil");
		
		saveFiles();
		
		World world = nationsData.getLocation("Nations." + Nation + ".Location").getWorld();
		
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionManager regions = container.get(BukkitAdapter.adapt(world));
		ProtectedRegion region = regions.getRegion(Nation);
		
		DefaultDomain memberList = region.getMembers();
		memberList.addPlayer(uuid);
	}
	
	static void setBuilder(UUID uuid, String Nation) {
		removePlayer(uuid, Nation);
		
		List<String> builders = nationsData.getStringList("Nations." + Nation + ".Builders");
		builders.add(uuid.toString());
		
		nationsData.set("Nations." + Nation + ".Builders", builders);
		
		playerData.set(uuid.toString() + ".Nation", Nation);
		playerData.set(uuid.toString() + ".Role", "Builder");
		
		saveFiles();
		
		World world = nationsData.getLocation("Nations." + Nation + ".Location").getWorld();
		
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionManager regions = container.get(BukkitAdapter.adapt(world));
		ProtectedRegion region = regions.getRegion(Nation);
		
		DefaultDomain memberList = region.getMembers();
		memberList.addPlayer(uuid);
	}
	
	static void setDonator(UUID uuid, String Nation) {
		List<String> donators = nationsData.getStringList("Nations." + Nation + ".Donators");
		if (!donators.contains(uuid.toString())) donators.add(uuid.toString());
		
		nationsData.set("Nations." + Nation + ".Donators", donators);
		
		saveFiles();
	}
	
	static void setMember(UUID uuid, String Nation) {
		List<String> members = nationsData.getStringList("Nations." + Nation + ".Members");
		if (!members.contains(uuid.toString())) members.add(uuid.toString());
		
		nationsData.set("Nations." + Nation + ".Members", members);
		
		playerData.set(uuid.toString() + ".Nation", Nation);
		playerData.set(uuid.toString() + ".Role", "Member");
		
		saveFiles();
		
		World world = nationsData.getLocation("Nations." + Nation + ".Location").getWorld();
		
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionManager regions = container.get(BukkitAdapter.adapt(world));
		ProtectedRegion region = regions.getRegion(Nation);
		
		DefaultDomain memberList = region.getMembers();
		memberList.addPlayer(uuid);
	}
	
	static void setAllowed(UUID uuid, String Nation) {
		List<String> allowed = nationsData.getStringList("Nations." + Nation + ".Allowed");
		if (!allowed.contains(uuid.toString())) allowed.add(uuid.toString());
		
		nationsData.set("Nations." + Nation + ".Allowed", allowed);
		
		saveFiles();
	}
	
	static void setWar(String Nation1, String Nation2) {
		List<String> enemies = nationsData.getStringList("Nations." + Nation1 + ".Enemies");
		enemies.add(Nation2);
		
		nationsData.set("Nations." + Nation1 + ".Enemies", enemies);
		
		enemies = nationsData.getStringList("Nations." + Nation2 + ".Enemies");
		enemies.add(Nation1);
		
		nationsData.set("Nations." + Nation2 + ".Enemies", enemies);

		saveFiles();
	}
	
	static void setAlly(String Nation1, String Nation2) {
		List<String> allys = nationsData.getStringList("Nations." + Nation1 + ".Allys");
		allys.add(Nation2);
		
		nationsData.set("Nations." + Nation1 + ".Allys", allys);
		
		allys = nationsData.getStringList("Nations." + Nation2 + ".Allys");
		allys.add(Nation1);
		
		nationsData.set("Nations." + Nation2 + ".Allys", allys);

		saveFiles();
	}
	
	static void removePlayer(UUID uuid, String Nation) {
		if (getRole(uuid) == 1) {
			List<String> builders = nationsData.getStringList("Nations." + Nation + ".Builders");
			builders.remove(uuid.toString());
			nationsData.set("Nations." + Nation + ".Builders", builders);
		} else if (getRole(uuid) == 2) {
			List<String> counsils = nationsData.getStringList("Nations." + Nation + ".Counsils");
			counsils.remove(uuid.toString());
			nationsData.set("Nations." + Nation + ".Counsils", counsils);
		} else if (getRole(uuid) == 3) {
			nationsData.set("Nations." + Nation + ".VicePresident", null);
		} else if (getRole(uuid) == 4) {
			if (nationsData.get("Nations." + Nation + ".VicePresident") != null) {
				setPresident(UUID.fromString(nationsData.getString("Nations." + Nation + ".Vice-President")), Nation);
			} else {
				nationsData.set("Nations." + Nation, null);
			}
		}
		
		playerData.set(uuid.toString(), null);
		saveFiles();
	}
	
	static int getTreasuryAmount(String Nation) {
		return nationsData.getInt("Nations." + Nation + ".Treasury");
	}
	
	static void finishNation(String Nation) {
		nationsData.set("Nations." + Nation + ".Finished", true);
		
		saveFiles();
		
		World world = nationsData.getLocation("Nations." + Nation + ".Location").getWorld();
		
		RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
		RegionManager regions = container.get(BukkitAdapter.adapt(world));
		ProtectedRegion region = regions.getRegion(Nation);
		
		region.setFlag(Flags.ENTRY, StateFlag.State.ALLOW);
	}
	
	static void addTreasury(String Nation, int Amount) {
		nationsData.set("Nations." + Nation + ".Treasury", nationsData.getInt("Nations." + Nation + ".Treasury") + Amount);
		
		saveFiles();
	}
	
	static void createVirtualNation(String Nation, UUID uuid, Location loc) {
		DataManager.nationsData.set("Nations." + Nation + ".Finished", false);
		DataManager.nationsData.set("Nations." + Nation + ".Location", loc);
		DataManager.nationsData.set("Nations." + Nation + ".Public", true);
		
		DataManager.saveFiles();
		
		DataManager.setPresident(uuid, Nation);
	}
	
	static void saveFiles() {
		try {
			nationsData.save(nationsDataFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			playerData.save(playerDataFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static UUID getUuid(String name) {
		@SuppressWarnings("deprecation")
		OfflinePlayer op = Bukkit.getOfflinePlayer(name);
		if (op.hasPlayedBefore()) {
		    UUID uuid = op.getUniqueId();
		    return uuid;
		} else {
			return null;
		}
	}
	
	static String getName(String uuid) {
        String url = "https://api.mojang.com/user/profiles/"+uuid.replace("-", "")+"/names";
        try {
            @SuppressWarnings("deprecation")
            String nameJson = IOUtils.toString(new URL(url));           
            JSONArray nameValue = (JSONArray) JSONValue.parseWithException(nameJson);
            String playerSlot = nameValue.get(nameValue.size()-1).toString();
            JSONObject nameObject = (JSONObject) JSONValue.parseWithException(playerSlot);
            return nameObject.get("name").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
	
}
