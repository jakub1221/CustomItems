package org.jakub1221.customitems.misc;

import org.bukkit.ChatColor;
import org.jakub1221.customitems.CustomItems;

public class Util {

	public static String edit(String text) {
		return ChatColor.GOLD + "[CustomItems] " + ChatColor.WHITE + text;
	}

	public static String editConsole(String text) {
		return "[CustomItems] " + text;
	}

	public static void itemError(CustomItems instance, String text) {
		instance.getLog().warning(text);
	}

	public static void itemWarning(CustomItems instance, String text) {
		instance.getLog().warning(text);
	} 

}
