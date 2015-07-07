package io.github.Nick3306.Duel;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.*;

public class Duel 
{
	private Main plugin;
	public Duel(Main plugin)
	 {
	   this.plugin = plugin;
	 }

	Player player1;
	Player player2;
	Location loc1;
	Location loc2;
	Arena arena;
	Duel(Player player1, Player player2, Location loc1, Location loc2, Arena a)
	{
		this.player1 = player1;
		this.player2 = player2;
		this.loc1 = loc1;
		this.loc2 = loc2;
		arena = a;
		
		//player1.sendMessage("Set all but arena" + " size is: " + this.plugin.arenas.size());
		//arena = this.plugin.arenas.get(1);
	}
	void setArena()			//Sets a random arena, wont work until we get the arenas into a list
	{
		/*int num = this.plugin.arenas.size();
		Random rand = new Random();
		int index = rand.nextInt(num);
		*/
		arena = this.plugin.arenas.get(0);
	}
	Arena getArena()
	{
		return arena;
	}
	
	String getArenaName()
	{
		return arena.getName();
	}
	
	Player getPlayer1()
	{
		return player1;
	}
	Player getPlayer2()
	{
		return player2;
	}
	Location getLoc1()
	{
		return loc1;
	}
	Location getLoc2()
	{
		return loc2;
	}
	
}
