package org.jakub1221.customitems.misc;

import org.bukkit.ChatColor;
import org.jakub1221.customitems.CustomItems;

public class Util {

	public static String edit(String text){
		return ChatColor.GOLD+"[CustomItems] "+ChatColor.WHITE+text;
	}
	public static String editConsole(String text){
		return "[CustomItems] "+text;
	}
	public static void itemError(String text){
		CustomItems.getInstance().getLog().warning(text);
	}
	public static void itemWarning(String text){
		CustomItems.getInstance().getLog().warning(text);
	}
	
}
