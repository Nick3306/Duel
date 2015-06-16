package io.github.Nick3306.Duel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.World;
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
		this.getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		if(this.getConfig().getConfigurationSection("arenas") != null)
		{
			String[] configArenas = this.getConfig().getConfigurationSection("arenas").getKeys(false).toArray(new String[0]);
			for(int i = 0; i < configArenas.length; i++)
			{
				String name = configArenas[i];
				World world = Bukkit.getWorld(this.getConfig().getString("arenas." + configArenas[i]  + ".world"));
				double x = this.getConfig().getDouble("arenas." + configArenas[i]  + ".x");
				double y = this.getConfig().getDouble("arenas." + configArenas[i]  + ".y");
				double z = this.getConfig().getDouble("arenas." + configArenas[i]  + ".z");
				double x2 = this.getConfig().getDouble("arenas." + configArenas[i]  + ".x2");
				double y2 = this.getConfig().getDouble("arenas." + configArenas[i]  + ".y2");
				double z2 = this.getConfig().getDouble("arenas." + configArenas[i]  + ".z2");
				Arena arena = new Arena(world, name,x,y,z,x2,y2,z2);
				arenas.add(arena);
			}
		}
		
	}
	public void onDisable()
	{
		duels.clear();
	}
}
