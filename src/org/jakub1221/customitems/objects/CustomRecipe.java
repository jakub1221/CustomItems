package org.jakub1221.customitems.objects;

import java.util.ArrayList;

public class CustomRecipe {

	ArrayList<CustomID> recipe = null;

	public CustomRecipe(final ArrayList<CustomID> r) {
		recipe = r;
	}

	public ArrayList getData() {

		return recipe;

	}

}
