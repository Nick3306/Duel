package io.github.Nick3306.Duel;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class DuelListen implements Listener
{
	private Main plugin;
	public DuelListen(Main plugin)
	 {
	   this.plugin = plugin;
	 }
	@EventHandler
	public void playerDeathEvent(PlayerDeathEvent event)
	{
		Player deadPerson = event.getEntity();
		for(int i = 0; i< this.plugin.duels.size(); i++)
		{	
			if (this.plugin.duels.get(i).getPlayer1() == deadPerson || this.plugin.duels.get(i).getPlayer2() == deadPerson)
			{
				Duel duel = this.plugin.duels.get(i);
				this.plugin.duels.get(i).getPlayer2().sendMessage("Duel is over!");
				event.setKeepInventory(true);
				if(deadPerson == duel.getPlayer1())
				{
					duel.getPlayer2().teleport(duel.getLoc2());
				}
				if(deadPerson == duel.getPlayer2())
				{
					duel.getPlayer1().teleport(duel.getLoc1());
				}
			}
		}
	}
	@EventHandler
	public void playerSpawnEvent(PlayerRespawnEvent event)
	{
		Player loser = event.getPlayer();
		for(int i = 0; i< this.plugin.duels.size(); i++)
		{	
			if (this.plugin.duels.get(i).getPlayer1() == loser || this.plugin.duels.get(i).getPlayer2() == loser)
			{	
				loser.sendMessage("You lose!");
				
				if(this.plugin.duels.get(i).getPlayer1() == loser)
				{
					event.setRespawnLocation((this.plugin.duels.get(i).getLoc1()));
				}
				if(this.plugin.duels.get(i).getPlayer2() == loser)
				{
					event.setRespawnLocation((this.plugin.duels.get(i).getLoc2()));
				}
				this.plugin.duels.remove(i);
			}
			
		}
		
	}
	
}
