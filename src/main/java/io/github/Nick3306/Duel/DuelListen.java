package io.github.Nick3306.Duel;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class DuelListen implements Listener
{
	private Main plugin;
	public DuelListen(Main plugin)
	 {
	   this.plugin = plugin;
	 }
	public void playerDeathEvent(PlayerDeathEvent event)
	{
		Player deadPerson = event.getEntity();
		for(int i = 0; i< this.plugin.duels.size(); i++)
		{	
			if (this.plugin.duels.get(i).getPlayer1() == deadPerson || this.plugin.duels.get(i).getPlayer2() == deadPerson)
			{
				event.setKeepInventory(true);
				Duel duel = this.plugin.duels.get(i);
				duel.getPlayer1().teleport(duel.getLoc1());
				duel.getPlayer2().teleport(duel.getLoc2());
			}
		}
	}
}
