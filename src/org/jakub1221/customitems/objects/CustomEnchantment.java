package org.jakub1221.customitems.objects;

import org.bukkit.enchantments.Enchantment;

public class CustomEnchantment {

	private Enchantment ench=null;
	private int level=0;
	
	public CustomEnchantment(Enchantment e,int l){
		ench=e;
		level=l;
	}

    public Enchantment getEnchantment(){
    	return this.ench;
    }	
    public int getLevel(){
    	return this.level;
    }
	
}
