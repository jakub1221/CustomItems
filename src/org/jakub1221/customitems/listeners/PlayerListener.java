package org.jakub1221.customitems.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jakub1221.customitems.CustomItems;

public class PlayerListener implements Listener {

	private CustomItems instance = null;

	public PlayerListener(CustomItems i) {
		instance = i;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getPlayer() != null) {
			if (event.getClickedBlock() != null) {
				if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

					if (event.getPlayer().getItemInHand() != null) {
						if (event.getPlayer().getItemInHand().getType() != Material.BOW
								&& event.getPlayer().getItemInHand().getType() != Material.SNOW_BALL) {
							if (instance.getItemHandler().hasAbility(event.getPlayer().getItemInHand(), "Lighting")
									&& instance.getItemHandler().hasPermission(event.getPlayer(),
											event.getPlayer().getItemInHand())) {
								event.getClickedBlock().getLocation().getWorld()
										.strikeLightning(event.getClickedBlock().getLocation());
							}
							if (instance.getItemHandler().hasAbility(event.getPlayer().getItemInHand(), "Fire")
									&& instance.getItemHandler().hasPermission(event.getPlayer(),
											event.getPlayer().getItemInHand())) {
								Location loc = event.getClickedBlock().getLocation();
								loc.setY(loc.getY() + 1);
								if (loc.getWorld().getBlockAt(loc).getType() == Material.AIR) {
									loc.getWorld().getBlockAt(loc).setType(Material.FIRE);
								}
							}
							if (instance.getItemHandler().hasAbility(event.getPlayer().getItemInHand(), "Teleport")
									&& instance.getItemHandler().hasPermission(event.getPlayer(),
											event.getPlayer().getItemInHand())) {
								Location loc = event.getClickedBlock().getLocation();
								loc.setPitch(event.getPlayer().getLocation().getPitch());
								loc.setYaw(event.getPlayer().getLocation().getYaw());
								loc.setY(loc.getY() + 1);
								event.getPlayer().teleport(loc);

							}
							if (instance.getItemHandler().hasAbility(event.getPlayer().getItemInHand(), "Explosion")
									&& instance.getItemHandler().hasPermission(event.getPlayer(),
											event.getPlayer().getItemInHand())) {
								Location loc = event.getClickedBlock().getLocation();
								loc.getWorld().createExplosion(loc, 4);
							}
						}

					}

				}
			}
		}
	}

}
