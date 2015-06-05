package io.github.Nick3306.Duel;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
			sender.sendMessage("Duel initiated");
			if(args.length > 2)
			{
				 sender.sendMessage("Too many arguments!");
		         return false;
			}
			if(args[0] == "challenge")
			{
				sender.sendMessage("In challenge");
				if (args.length < 2)
				{
					sender.sendMessage("You must define a player to challenge!");
			        return false;
				}
				
				if(args.length == 2)
				{
					if (Bukkit.getPlayerExact(args[1]) == null)
					{
						sender.sendMessage("Player " + args[1] + " is not online!");
						return false;
					}
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
				 }
					else
					{
						Player player1 = (Player) sender;
						Player player2 = Bukkit.getPlayerExact(args[0]);
						Location loc1 = player1.getLocation();
						Location loc2 = player2.getLocation();
						Duel d = new Duel(player1, player2, loc1, loc2);
						this.plugin.duels.add(d);
						sender.sendMessage("Asking player " + args[1] + " to duel");
						player2.sendMessage("Player " + player1.getName() + " has asked you to duel. /duel accept to accept it /duel reject to reject");
						return true;
					}
				}
			}
			if(args[0] == "accept")
			{
				for(int i = 0; i< this.plugin.duels.size(); i++)
				{
					if (this.plugin.duels.get(i).getPlayer2() == sender)
					{
						this.plugin.duels.get(i).getPlayer1().teleport(this.plugin.duels.get(i).getLoc1());
						this.plugin.duels.get(i).getPlayer2().teleport(this.plugin.duels.get(i).getLoc1());
					}
				}
				sender.sendMessage("No one has challeneged you!");
				return false;
			}
			if(args[0] == "reject")
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
		
		return false;
	}

}
