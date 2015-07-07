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
