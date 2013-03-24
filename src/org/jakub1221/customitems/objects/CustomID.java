package org.jakub1221.customitems.objects;

import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class CustomID {

	private int ID;
	private int DATA;
	
	public CustomID(String _data){
		if (_data!=null && _data.length()>0){
		String[] both = _data.split(":");
		ID=Integer.parseInt(both[0]);
		if (both.length>1){
			DATA=Integer.parseInt(both[1]);
		}else{
			DATA=0;
		}
		}else{
			ID=0;
			DATA=0;
		}
	}
	
	public int getID(){
		return ID;
	}
	
	public int getDATA(){
		return DATA;
	}
	
	public boolean isData(){
		if (DATA>0){
			return true;
		}
		return false;
	}
	
	public ItemStack getItemStack(){
		ItemStack item = null;
		if (DATA>0){
	 item = new ItemStack(ID,1,(byte) DATA);
		}else{
	 item = new ItemStack(ID);
		}
	    return item;
	    
	}
	
}
