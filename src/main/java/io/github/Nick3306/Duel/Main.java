package io.github.Nick3306.Duel;

import java.util.ArrayList;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin 
{
	ArrayList<Duel> duels = new ArrayList<Duel>();
	ArrayList<Arena> arenas = new ArrayList<Arena>();
	public void onEnable()
	{
		PluginManager pm = getServer().getPluginManager();
		getCommand("duel").setExecutor(new DuelC(this));
		pm.registerEvents(new DuelListen(this), this);
	}
	public void onDisable()
	{
		duels.clear();
	}
}
