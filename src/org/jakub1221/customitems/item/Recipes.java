package org.jakub1221.customitems.item;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.jakub1221.customitems.CustomItems;

public class Recipes {

	private CustomItems instance = null;

	public Recipes(CustomItems i) {
		this.instance = i;
	}

	public void setRecipes(ArrayList<Recipe> r) {
		Bukkit.getServer().resetRecipes();
		for (Recipe a : r) {
			Bukkit.getServer().addRecipe(a);
		}
	}

}
