package me.goodgamer123.TriplexCraftNation;

import org.bukkit.plugin.java.JavaPlugin;

public final class MainClass extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		getCommand("TCN").setExecutor(new TCN());
		
		getServer().getPluginManager().registerEvents(new Prefix(), this);
		getServer().getPluginManager().registerEvents(new GUIEvents(), this);
		getServer().getPluginManager().registerEvents(new NationsEvents(), this);
		getServer().getPluginManager().registerEvents(new OnJoin(), this);
		
		DataManager.loadFiles(this);
	}
}
