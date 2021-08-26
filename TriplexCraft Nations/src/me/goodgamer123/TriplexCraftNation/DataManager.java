package me.goodgamer123.TriplexCraftNation;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
			else if (role.equals("Vice-President")) return 3;
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
	}
	
	static void setVicePresident(UUID uuid, String Nation) {
		removePlayer(uuid, Nation);
		
		nationsData.set("Nations." + Nation + ".VicePresident", uuid.toString());
		
		playerData.set(uuid.toString() + ".Nation", Nation);
		playerData.set(uuid.toString() + ".Role", "VicePresident");
		
		saveFiles();
	}
	
	static void setCounsil(UUID uuid, String Nation) {
		removePlayer(uuid, Nation);
		
		List<String> counsils = nationsData.getStringList("Nations." + Nation + ".Counsils");
		counsils.add(uuid.toString());
		
		nationsData.set("Nations." + Nation + ".Counsils", counsils);
		
		playerData.set(uuid.toString() + ".Nation", Nation);
		playerData.set(uuid.toString() + ".Role", "Counsil");
		
		saveFiles();
	}
	
	static void setBuilder(UUID uuid, String Nation) {
		removePlayer(uuid, Nation);
		
		List<String> builders = nationsData.getStringList("Nations." + Nation + ".Builders");
		builders.add(uuid.toString());
		
		nationsData.set("Nations." + Nation + ".Builders", builders);
		
		playerData.set(uuid.toString() + ".Nation", Nation);
		playerData.set(uuid.toString() + ".Role", "Builder");
		
		saveFiles();
	}
	
	static void setDonator(UUID uuid, String Nation) {
		List<String> donators = nationsData.getStringList("Nations." + Nation + ".Donators");
		donators.add(uuid.toString());
		
		nationsData.set("Nations." + Nation + ".Donators", donators);
		
		saveFiles();
	}
	
	static void setMember(UUID uuid, String Nation) {
		List<String> members = nationsData.getStringList("Nations." + Nation + ".Members");
		members.add(uuid.toString());
		
		nationsData.set("Nations." + Nation + ".Members", members);
		
		playerData.set(uuid.toString() + ".Nation", Nation);
		playerData.set(uuid.toString() + ".Role", "Member");
		
		saveFiles();
	}
	
	static void setAllowed(UUID uuid, String Nation) {
		List<String> allowed = nationsData.getStringList("Nations." + Nation + ".Allowed");
		allowed.add(uuid.toString());
		
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
		if (getRole(uuid) > 1) {
			if (playerData.getString(uuid.toString() + ".Role") == "Builder") {
				List<String> builders = nationsData.getStringList("Nations." + Nation + ".Builders");
				builders.remove(uuid.toString());
				
				nationsData.set("Nations." + Nation + ".Builders", builders);
			} else if (playerData.getString(uuid.toString() + ".Role") == "Counsil") {
				List<String> counsils = nationsData.getStringList("Nations." + Nation + ".Counsils");
				counsils.remove(uuid.toString());
				
				nationsData.set("Nations." + Nation + ".Counsils", counsils);
			} else if (playerData.getString(uuid.toString() + ".Role") == "Vice-President") {
				nationsData.set("Nations." + Nation + ".Vice-President", null);
			} else if (nationsData.getString(uuid.toString() + ".Role") == "President") {
				if (nationsData.get("Nations." + Nation + ".Vice-President") != null) {
					setPresident(UUID.fromString(nationsData.getString("Nations." + Nation + ".Vice-President")), Nation);
				} else {
					nationsData.set("Nations." + Nation, null);
				}
			}
		}
		
		playerData.set(uuid.toString(), null);
		saveFiles();
	}
	
	static int getTreasuryAmount(String Nation) {
		return nationsData.getInt("Nations." + Nation + ".Treasury");
	}
	
	static void addTreasury(String Nation, int Amount) {
		nationsData.set("Nations." + Nation + ".Treasury", nationsData.getInt("Nations." + Nation + ".Treasury") + Amount);
		
		saveFiles();
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
	
	static String getUuid(String name) {
		String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));           
            if(UUIDJson.isEmpty()) return "NA";                       
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            return UUIDObject.get("id").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "NA";
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
