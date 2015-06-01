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
			if(args.length > 2)
			{
				 sender.sendMessage("Too many arguments!");
		         return false;
			}
			if(args[0] == "challenge")
			{
				if (args.length != 2)
				{
					sender.sendMessage("You must define a player to challenge!");
			        return false;
				}
				if(args.length == 2)
				{
					if (Bukkit.getPlayerExact(args[0]) == null)
					{
						sender.sendMessage("Player " + args[1] + " is not online!");
						return false;
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
			if(args[1] == "accept")
			{
				for(int i = 0; i< this.plugin.duels.size(); i++)
				{
					if (this.plugin.duels.get(i).getPlayer2() == sender)
					{
						
					}
				}
				sender.sendMessage("No one has challeneged you!");
				return false;
			}
			if(args[1] == "reject")
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
		}
		return false;
	}

}
