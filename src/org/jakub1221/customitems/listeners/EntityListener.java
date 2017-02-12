package org.jakub1221.customitems.listeners;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jakub1221.customitems.CustomItems;

public class EntityListener implements Listener {

	private CustomItems instance = null;

	public EntityListener(CustomItems i) {
		instance = i;
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.getCause() != null) {
			if (event.getCause() == DamageCause.LIGHTNING) {
				if (event.getEntity() instanceof Player) {

					Player player = (Player) event.getEntity();
					if (player.getItemInHand() != null) {
						if (instance.getItemHandler().hasAbility(player.getItemInHand(), "Lighting")) {
							event.setDamage(0);
							event.setCancelled(true);
							return;
						}
					}
				}
			}
		}
		
		if (event instanceof EntityDamageByEntityEvent) {
			Player player = null;
			EntityDamageByEntityEvent dEvent = (EntityDamageByEntityEvent) event;

			if (dEvent.getDamager() != null) {
				if (dEvent.getDamager() instanceof Player || dEvent.getDamager() instanceof Projectile) {
					if (dEvent.getDamager() instanceof Player) {
						player = (Player) dEvent.getDamager();
					} else {
						if (dEvent.getDamager() instanceof Projectile) {
							if (dEvent.getDamager() instanceof Arrow) {
								Arrow arrow = (Arrow) dEvent.getDamager();
								if (arrow.getShooter() instanceof Player) {
									player = (Player) arrow.getShooter();
								}
							} else if (dEvent.getDamager() instanceof Snowball) {
								Snowball arrow = (Snowball) dEvent.getDamager();
								if (arrow.getShooter() instanceof Player) {
									player = (Player) arrow.getShooter();
								}
							}
						}
					}
					if (player != null) {
						if (player.getItemInHand() != null) {
							if (instance.getItemHandler().hasAbility(player.getItemInHand(), "Death")
									&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {
								event.setDamage(999999);

							}
							if (instance.getItemHandler().hasAbility(player.getItemInHand(), "SuperHit")
									&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {
								event.setDamage(event.getDamage() * 3);

							}
							if (instance.getItemHandler().hasAbility(player.getItemInHand(), "Poison")
									&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {
								if (event.getEntity() instanceof Player) {
									Player pe = (Player) event.getEntity();
									pe.addPotionEffect(new PotionEffect(PotionEffectType.POISON,
											instance.getConfigDB().Poison_Duration, 1));
								}

							}
							if (instance.getItemHandler().hasAbility(player.getItemInHand(), "Disorient")
									&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {
								if (event.getEntity() instanceof Player) {
									Player pe = (Player) event.getEntity();
									pe.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,
											instance.getConfigDB().Disorient_Duration, 1));
								}

							}
							if (instance.getItemHandler().hasAbility(player.getItemInHand(), "Blind")
									&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {
								if (event.getEntity() instanceof Player) {
									Player pe = (Player) event.getEntity();
									pe.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,
											instance.getConfigDB().Blind_Duration, 1));
								}

							}
							if (instance.getItemHandler().hasAbility(player.getItemInHand(), "LifeSteal")
									&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {

								if (player.getHealth() + (event.getDamage() / 3) > 20) {
									player.setHealth(20);
								} else {
									player.setHealth(player.getHealth() + (event.getDamage() / 3));
								}
							}

							if (instance.getItemHandler().hasAbility(player.getItemInHand(), "Heal")
									&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {

								if (event.getEntity() instanceof Player) {
									Player dPlayer = (Player) event.getEntity();
									if (dPlayer.getHealth() <= 18) {
										dPlayer.setHealth(dPlayer.getHealth() + 2);
									} else {
										dPlayer.setHealth(20);
									}
								}

							}

							if (instance.getItemHandler().hasAbility(player.getItemInHand(), "Fire")
									&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {

								event.getEntity().setFireTicks(100);

							}

						}
					}
				}

			}
		}
	}

	// *************************************************************************************************************************//
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {

		if (event.getEntity() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getEntity();
			if (arrow.getShooter() instanceof Player) {
				Player player = (Player) arrow.getShooter();
				if (player.getItemInHand() != null) {

					if (player.getItemInHand().getType() == Material.BOW) {

						if (instance.getItemHandler().hasAbility(player.getItemInHand(), "Teleport")
								&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {

							Location loc = arrow.getLocation();
							List<Entity> entityList = arrow.getNearbyEntities(2, 3, 2);
							boolean canTP = true;

							if (entityList.isEmpty()) {
								loc.setPitch(player.getLocation().getPitch());
								loc.setYaw(player.getLocation().getYaw());
								loc.setY(loc.getY() + 1);
								player.teleport(loc);
							} else {
								if (entityList.get(0) instanceof Player) {
									Player pp = (Player) entityList.get(0);
									if (pp == player) {
										loc.setPitch(player.getLocation().getPitch());
										loc.setYaw(player.getLocation().getYaw());
										loc.setY(loc.getY() + 1);
										player.teleport(loc);
									}
								} else {

									for (Entity e : entityList) {
										if (e.getEntityId() != player.getEntityId()) {
											if (e.getLastDamageCause() != null) {
												if (!e.getLastDamageCause().equals(DamageCause.PROJECTILE)) {
													canTP = false;
												}
											}
										}
									}

									if (canTP) {
										loc.setPitch(player.getLocation().getPitch());
										loc.setYaw(player.getLocation().getYaw());
										loc.setY(loc.getY() + 1);
										player.teleport(loc);
									}

								}

							}
						}

						if (instance.getItemHandler().hasAbility(player.getItemInHand(), "Lighting")
								&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {
							arrow.getLocation().getWorld().strikeLightning(arrow.getLocation());
						}

						if (instance.getItemHandler().hasAbility(player.getItemInHand(), "Explosion")
								&& instance.getItemHandler().hasPermission(player, player.getItemInHand())) {
							Location loc = arrow.getLocation();
							loc.getWorld().createExplosion(loc, 4);

						}

					}

				}
			}
		}
		if (event.getEntity() instanceof Snowball) {
			Snowball arrow = (Snowball) event.getEntity();
			if (arrow.getShooter() instanceof Player) {
				Player player = (Player) arrow.getShooter();

				if (instance.getPlayerData().getPlayerData(player, "SNOW_BALL") > 0) {
					String itmName = instance.getPlayerData().getPlayerDataString(player, "I");
					if (instance.getItemHandler().hasAbility(instance.getItemHandler().createItem(itmName), "Teleport")
							&& instance.getItemHandler().hasPermission(player,
									instance.getItemHandler().createItem(itmName))) {
						Location loc = arrow.getLocation();
						loc.setPitch(player.getLocation().getPitch());
						loc.setYaw(player.getLocation().getYaw());
						loc.setY(loc.getY() + 1);
						player.teleport(loc);

					}

					if (instance.getItemHandler().hasAbility(instance.getItemHandler().createItem(itmName), "Lighting")
							&& instance.getItemHandler().hasPermission(player,
									instance.getItemHandler().createItem(itmName))) {
						arrow.getLocation().getWorld().strikeLightning(arrow.getLocation());
					}

					if (instance.getItemHandler().hasAbility(instance.getItemHandler().createItem(itmName), "Explosion")
							&& instance.getItemHandler().hasPermission(player,
									instance.getItemHandler().createItem(itmName))) {
						Location loc = arrow.getLocation();
						loc.getWorld().createExplosion(loc, 4);

					}

					instance.getPlayerData().addPlayerData(player, "SNOW_BALL", -1);
					if (instance.getPlayerData().getPlayerData(player, "SNOW_BALL") == 0) {
						instance.getPlayerData().removePlayerDataString(player, "I");
					}

				}

			}
		}
	}

	// *************************************************************************************************************************//
	@EventHandler
	public void onProjectileLaunchEvent(ProjectileLaunchEvent event) {
		if (event.getEntity() instanceof Snowball) {
			Snowball snowball = (Snowball) event.getEntity();
			if (snowball.getShooter() != null) {
				if (snowball.getShooter() instanceof Player) {
					Player player = (Player) snowball.getShooter();
					if (player.getItemInHand() != null) {
						if (instance.getItemHandler().isCustom(player.getItemInHand())) {
							instance.getPlayerData().addPlayerData(player, "SNOW_BALL", 1);
							String loreF = player.getItemInHand().getItemMeta().getLore().get(0);
							if (instance.getPlayerData().isPlayerDataString(player, "I")) {
								instance.getPlayerData().removePlayerDataString(player, "I");
							}
							instance.getPlayerData().addPlayerDataString(player, "I", loreF);
						}
					}
				}
			}
		}
	}

}
