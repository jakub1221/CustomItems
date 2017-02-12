package org.jakub1221.customitems.listeners;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.jakub1221.customitems.CustomItems;

public class BlockListener implements Listener {

	private CustomItems instance = null;

	public BlockListener(CustomItems i) {
		instance = i;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		if (event.isCancelled())
			return;
		
		if (event.getPlayer() == null) 
			return;
		
		if (event.getBlock() != null)
			return;
		
		if (event.getPlayer().getItemInHand() != null)
			return;

		if (event.getBlock().getDrops() == null)
			return;
		
		if (event.getBlock().getDrops().isEmpty())
			return;
		
		if (event.getBlock().getDrops().iterator() == null) 
			return;

		if (instance.getItemHandler().hasAbility(event.getPlayer().getItemInHand(), "SuperFortune")
			&& instance.getItemHandler().hasPermission(event.getPlayer(), event.getPlayer().getItemInHand())) {
			
			int chance = new Random().nextInt(5);
			if (chance != 0) {		
				for (int i = 0; i <= chance; i++) {
					if (event.getBlock().getDrops().iterator().hasNext()) {
						event.getBlock().getWorld().dropItemNaturally(
							event.getBlock().getLocation(),
							event.getBlock().getDrops().iterator().next());
					}
				}
							
			}
		}		
	}

	@EventHandler
	public void onBlockDamageEvent(BlockDamageEvent event) {
		if (event.getPlayer() == null) 
			return;
		
		if (event.getBlock() == null)
			return;
		
		if (event.getPlayer().getItemInHand() == null)
			return;
		
		if (instance.getItemHandler().hasAbility(event.getPlayer().getItemInHand(), "Break") 
			&& instance.getItemHandler().hasPermission(event.getPlayer(), event.getPlayer().getItemInHand())) {
			
			if (event.getBlock().getType() != Material.BEDROCK) {
				event.setInstaBreak(true);
			}
			
		}
	}
	
}
