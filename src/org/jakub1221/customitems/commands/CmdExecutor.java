package org.jakub1221.customitems.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jakub1221.customitems.CustomItems;
import org.jakub1221.customitems.misc.Util;

public class CmdExecutor implements CommandExecutor {

	private CustomItems instance = null;

	public CmdExecutor(CustomItems i) {
		instance = i;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.isOp()) {
				if (args.length > 0) {
					if (args[0].equalsIgnoreCase("create")) {
						if (instance.hasPermission(player, "custom-items.create")) {
							if (args.length == 3) {
								if (instance.getItemHandler().getItemType().containsKey(args[1])) {
									if (Bukkit.getPlayer(args[2]) != null) {
										if (Bukkit.getPlayer(args[2]).isOnline()) {
											if (Bukkit.getPlayer(args[2]).getInventory().firstEmpty() != -1) {
												Bukkit.getPlayer(args[2]).getInventory().setItem(
														Bukkit.getPlayer(args[2]).getInventory().firstEmpty(),
														instance.getItemHandler().createItem(args[1]));
												player.sendMessage(
														Util.edit("Item has been added to player�s inventory."));
											} else {
												player.sendMessage(Util.edit("Player has full inventory."));
											}
										} else {
											player.sendMessage(Util.edit("Player is offline."));
										}
									} else {
										player.sendMessage(Util.edit("Player is offline."));
									}
								} else {
									player.sendMessage(Util.edit("Item does not exist!"));
								}

							} else {
								player.sendMessage(Util.edit("Usage: /ci create <item name> <player>"));
							}
						} else {
							player.sendMessage(Util.edit(ChatColor.RED + "You dont have permission!"));
						}
					} else if (args[0].equalsIgnoreCase("info")) {
						if (instance.hasPermission(player, "custom-items.info")) {
							if (player.getItemInHand() != null) {
								if (instance.getItemHandler().isCustom(player.getItemInHand())) {
									player.sendMessage(ChatColor.GREEN + "List of item abilities:");
									ArrayList<String> list = instance.getItemHandler()
											.getItemAbilities(player.getItemInHand());
									for (String ab : list) {
										player.sendMessage(ab);
									}
								} else {
									player.sendMessage(Util.edit("You dont have a custom item in your hands!"));
								}
							} else {
								player.sendMessage(Util.edit("You dont have a custom item in your hands!"));
							}
						} else {
							player.sendMessage(Util.edit(ChatColor.RED + "You dont have permission!"));
						}
					} else if (args[0].equalsIgnoreCase("help")) {
						if (instance.hasPermission(player, "custom-items.help")) {
							player.sendMessage(ChatColor.GOLD + "-------------------------------------------");
							player.sendMessage(Util.edit("Commands:"));
							player.sendMessage(ChatColor.GOLD + "/ci create <item name> <player> " + ChatColor.WHITE
									+ "- Gives custom item to the player");
							player.sendMessage(
									ChatColor.GOLD + "/ci info " + ChatColor.WHITE + "- Shows info about item in hand");
							player.sendMessage(
									ChatColor.GOLD + "/ci reload " + ChatColor.WHITE + "- Reloads config and items");
							player.sendMessage(ChatColor.GOLD + "/ci help " + ChatColor.WHITE + "- Shows all commands");
							player.sendMessage(ChatColor.GOLD + "-------------------------------------------");
						} else {
							player.sendMessage(Util.edit(ChatColor.RED + "You dont have permission!"));
						}
					} else if (args[0].equalsIgnoreCase("reload")) {
						if (instance.hasPermission(player, "custom-items.reload")) {
							instance.getConfigDB().Reload();
							player.sendMessage(Util.edit("Config/Items reloaded!"));

						} else {
							player.sendMessage(Util.edit(ChatColor.RED + "You dont have permission!"));
						}
					} else {
						player.sendMessage(Util.edit("Usage: /ci help"));
					}
				} else {
					player.sendMessage(Util.edit("Usage: /ci help"));
				}
			} else {
				player.sendMessage(Util.edit(ChatColor.RED + "You dont have permission!"));
			}

			// Console

		} else {

			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("create")) {
					if (args.length == 3) {
						if (instance.getItemHandler().getItemType().containsKey(args[1])) {
							if (Bukkit.getPlayer(args[2]) != null) {
								if (Bukkit.getPlayer(args[2]).isOnline()) {
									if (Bukkit.getPlayer(args[2]).getInventory().firstEmpty() != -1) {
										Bukkit.getPlayer(args[2]).getInventory().setItem(
												Bukkit.getPlayer(args[2]).getInventory().firstEmpty(),
												instance.getItemHandler().createItem(args[1]));
										instance.getLog()
												.info(Util.editConsole("Item has been added to player�s inventory."));
									} else {
										instance.getLog().info(Util.editConsole("Player has full inventory."));
									}
								} else {
									instance.getLog().info(Util.editConsole("Player is offline."));
								}
							} else {
								instance.getLog().info(Util.editConsole("Player is offline."));
							}
						} else {
							instance.getLog().info(Util.editConsole("Item does not exist!"));
						}

					} else {
						instance.getLog().info(Util.editConsole("Usage: /ci create <item name> <player>"));
					}

				} else if (args[0].equalsIgnoreCase("info")) {

					instance.getLog().info(Util.editConsole("This is not console command!"));

				} else if (args[0].equalsIgnoreCase("help")) {
					instance.getLog().info("-------------------------------------------");
					instance.getLog().info(Util.editConsole("Commands:"));
					instance.getLog().info("/ci create <item name> <player> - Gives custom item to the player");
					instance.getLog().info("/ci info - Shows info about item in hand");
					instance.getLog().info("/ci reload - Reloads config and items");
					instance.getLog().info("/ci help - Shows all commands");
					instance.getLog().info("-------------------------------------------");

				} else if (args[0].equalsIgnoreCase("reload")) {

					instance.getConfigDB().Reload();
					instance.getLog().info(Util.editConsole("Config/Items reloaded!"));

				} else {
					instance.getLog().info(Util.editConsole("Usage: /ci help"));
				}

			} else {
				instance.getLog().info(Util.editConsole("Usage: /ci help"));
			}
		}

		return true;
	}

}
