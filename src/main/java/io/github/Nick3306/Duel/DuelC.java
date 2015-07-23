package io.github.Nick3306.Duel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.*;

public class DuelC implements CommandExecutor
{
	private Main plugin;
	public DuelC(Main plugin)
	 {
	   this.plugin = plugin;
	 }

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		
		if (cmd.getName().equalsIgnoreCase("duel")) 
		{
			if(sender.hasPermission("duel.user") || sender.hasPermission("duel.admin"))
			{
				if(args[0].equalsIgnoreCase("challenge"))
				{
					if(args.length > 2)
					{
						sender.sendMessage("Too many arguments!");
						return false;
					}
					if (args.length < 2)
					{
						sender.sendMessage("You must define a player to challenge!");
						return false;
					}
				
					if(args.length == 2)
					{
					
						for (int i = 0; i < this.plugin.duels.size(); i++)
						{
							if(sender ==  this.plugin.duels.get(i).getPlayer1() || sender ==  this.plugin.duels.get(i).getPlayer2())
							{
								sender.sendMessage("You already have a pending challenge!");
								return false;
							}
							if(Bukkit.getPlayerExact(args[1]) == this.plugin.duels.get(i).getPlayer1() || Bukkit.getPlayerExact(args[1]) == this.plugin.duels.get(i).getPlayer1())
							{
								sender.sendMessage("Player " + args[1] + " is already beign challenged!");
								return false;
							}
						}
						if (Bukkit.getPlayerExact(args[1]) == null)
						{
							sender.sendMessage("Player " + args[1] + " is not online!");
							return false;
						}
					
					
				 
						else if (Bukkit.getPlayerExact(args[1]) != null)
						{
							Player player1 = (Player) sender;
							Player player2 = Bukkit.getPlayerExact(args[1]);
							//sender.sendMessage("defined players");
							Location loc1 = player1.getLocation();
							//sender.sendMessage("player one location set");
							Location loc2 = player2.getLocation();
							//sender.sendMessage("set locations");
							Arena a = setArena();
							Duel d = new Duel(player1, player2, loc1, loc2, a);
							//sender.sendMessage("created class");
							this.plugin.duels.add(d);
							sender.sendMessage("Asking player " + args[1] + " to duel");
							player2.sendMessage("Player " + player1.getName() + " has asked you to duel. /duel accept to accept it /duel reject to reject");
							return true;
						}
					}
				}
			
			if(args[0].equalsIgnoreCase("accept"))
			{
				for(int i = 0; i< this.plugin.duels.size(); i++)
				{
					if (this.plugin.duels.get(i).getPlayer2() == sender)
					{
						World world = this.plugin.duels.get(i).getArena().getWorld();
						Location loc1 = new Location(world, this.plugin.duels.get(i).getArena().getX(), this.plugin.duels.get(i).getArena().getY(), this.plugin.duels.get(i).getArena().getZ());
						this.plugin.duels.get(i).getPlayer1().teleport(loc1);
						Location loc2 = new Location(world, this.plugin.duels.get(i).getArena().getX2(), this.plugin.duels.get(i).getArena().getY2(), this.plugin.duels.get(i).getArena().getZ2());
						this.plugin.duels.get(i).getPlayer2().teleport(loc2);
						return true;
					}
				}
				sender.sendMessage("No one has challeneged you!");
				return false;
			}
			if(args[0].equalsIgnoreCase("reject"))
			{
				for(int i = 0; i< this.plugin.duels.size(); i++)
				{
					if (this.plugin.duels.get(i).getPlayer2() == sender)
					{
						this.plugin.duels.get(i).getPlayer1().sendMessage("Player " + sender.getName() + " has rejected the duel");
						this.plugin.duels.remove(i);
						return true;
					}
				}
				sender.sendMessage("No one has challeneged you!");
				return false;
			}
			
			if(args[0].equalsIgnoreCase("createarena"))
			{
				
				if(sender.hasPermission("duel.admin"))
				{
					Player creator = Bukkit.getPlayerExact(sender.getName());
					if(args.length < 2)
					{
						sender.sendMessage("Not enough arguments!");
						return false;
					}
					if(args.length > 2)
					{
						sender.sendMessage("Too many arguments!");
						return false;
					}
					if(args.length == 2)
					{
					
						plugin.getConfig().set("arenas." + args[1], "");
						plugin.getConfig().set("arenas." + args[1]+ ".world", creator.getWorld().getName());
						sender.sendMessage("Arena created");
						plugin.saveConfig();
						return true;
					}
				}
				else 
				{
					sender.sendMessage("You do not have access to that command!");
					return false;
				}
			}
			if(args[0].equalsIgnoreCase("setspawn"))		
			{
				if(sender.hasPermission("duel.admin"))
				{
					if(args.length > 3)
					{
						sender.sendMessage("Too many arguments!");
					}
					if(args.length < 3)
					{
						sender.sendMessage("Not enough arguments!");
					}
					if(args.length == 3)
					{
						if(plugin.getConfig().contains("arenas." +args[1]))
						{
							if(args[2].equalsIgnoreCase("player1"))
							{
								Player creator = Bukkit.getPlayerExact(sender.getName());
								Location loc = creator.getLocation();
								plugin.getConfig().set("arenas." + args[1] + ".x", loc.getX());
								plugin.getConfig().set("arenas." + args[1] + ".y", loc.getY());
								plugin.getConfig().set("arenas." + args[1] + ".z", loc.getZ());		
								plugin.saveConfig();
								sender.sendMessage("Player 1 spawn set for " + args[1]);
								return true;
							}
							if(args[2].equalsIgnoreCase("player2"))
							{
								Player creator = Bukkit.getPlayerExact(sender.getName());
								Location loc = creator.getLocation();
								plugin.getConfig().set("arenas." + args[1] + ".x2", loc.getX());
								plugin.getConfig().set("arenas." + args[1] + ".y2", loc.getY());
								plugin.getConfig().set("arenas." + args[1] + ".z2", loc.getZ());		
								plugin.saveConfig();
								sender.sendMessage("Player 2 spawn set for " + args[1]);
								return true;
							}
						}
						else
						{
							sender.sendMessage("That arena does not exist!");
							return false;
						}
					
					}
					else
					{
						sender.sendMessage("Incorrect usage /duel setspawn arena player1/2");
						return false;
					}
				}
				else
				{
					sender.sendMessage("You do not have access to that command!");
					return false;
				}
				
			}
			if (args[0].equalsIgnoreCase("removearena"))
			{
				if(sender.hasPermission("duel.user"))
				{
					if(args.length == 2)
					{
						if(plugin.getConfig().contains("arenas." +args[1]))
						{
							for(int i = 0; i < this.plugin.arenas.size(); i++)
							{
								if(plugin.arenas.get(i).getName().equals(args[1]))
								{
									plugin.arenas.remove(i);
								}
							}
							plugin.getConfig().getConfigurationSection("arenas").set(args[1], null);
							plugin.saveConfig();
							sender.sendMessage("Arena removed!");
							return true;
						}
						else
						{
							sender.sendMessage("That arena does not exist!");
							return false;
						}
					}
					else
					{
						sender.sendMessage("Wrong number of arguments!");
						return false;
					
					}
				}
				else
				{
					sender.sendMessage("You do not have access to that command!");
					return false;
					}
			}
			if (args[0].equalsIgnoreCase("arenas"))
			{
				
				sender.sendMessage(ChatColor.GREEN + "|________Arenas________|");
				for (int i = 0; i < this.plugin.arenas.size(); i++)
				{
					sender.sendMessage(ChatColor.YELLOW + this.plugin.arenas.get(i).getName());
				}
				return true;
			}
		}
	}
		else
		{
			sender.sendMessage("You do not have access to that command!");	
			return false;
		}
		sender.sendMessage("incorrect useage!");
		return false;
		
	}
	Arena setArena()			//Sets a random arena, wont work until we get the arenas into a list
	{
		int num = this.plugin.arenas.size();
		Random rand = new Random();
		int index = rand.nextInt(num);
		return this.plugin.arenas.get(index);
	}
	public static boolean isNumeric(String str)
	{
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}
}
