package org.jakub1221.customitems.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jakub1221.customitems.CustomItems;
import org.jakub1221.customitems.objects.CustomEnchantment;
import org.jakub1221.customitems.objects.CustomID;
import org.jakub1221.customitems.misc.Util;

public class ItemHandler {

	private CustomItems instance=null;
	private ArrayList<String> AbilityList = new ArrayList<String>();
	private Map<String,CustomID> ItemType = new HashMap<String,CustomID>();
	private Map<String,ArrayList<String>> ItemAbilities = new HashMap<String,ArrayList<String>>();
	private Map<String,Boolean> ItemUsePermission = new HashMap<String,Boolean>();
	private Map<String,ArrayList<String>> ItemLore = new HashMap<String,ArrayList<String>>();
	private Map<String,String> ItemColor = new HashMap<String,String>();
	private Map<String,String> ItemName = new HashMap<String,String>();
	private Map<String,Boolean> ItemCustom = new HashMap<String,Boolean>();
	private Map<String,ArrayList<CustomEnchantment>> ItemEnchantments = new HashMap<String,ArrayList<CustomEnchantment>>();
	
	public ItemHandler(CustomItems i){
		instance=i;
		AbilityList.add("Lighting");
		AbilityList.add("SuperFortune");
		AbilityList.add("Death");
		AbilityList.add("SuperHit");
		AbilityList.add("Break");
		AbilityList.add("Teleport");
		AbilityList.add("Poison");
		AbilityList.add("Disorient");
		AbilityList.add("Explosion");
		AbilityList.add("LifeSteal");
		AbilityList.add("Blind");
		AbilityList.add("Fire");
		AbilityList.add("Heal");
	}
	
	public void addAbility(String str){
		if (!AbilityList.contains(str)){
			AbilityList.add(str);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList getAbilities(){
		return AbilityList;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setItemType(Map i){
		ItemType=i;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setItemAbilities(Map i){
		ItemAbilities=i;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setItemUsePermission(Map i){
		ItemUsePermission=i;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getItemType(){
		return ItemType;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getItemLore(){
		return ItemLore;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setItemLore(Map i){
		ItemLore=i;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setItemColor(Map i){
		ItemColor=i;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getItemColor(){
		return ItemColor;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setItemName(Map i){
		ItemName=i;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getItemName(){
		return ItemName;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setItemCustom(Map i){
		ItemCustom = i;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getItemEnchantments(){
		return ItemEnchantments;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setItemEnchantments(Map i){
		ItemEnchantments=i;
	}
	
	public boolean isCustom(ItemStack item){
		if (item!=null){
			if (item.getItemMeta()!=null){
				if (item.getItemMeta().getLore()!=null){
			return ItemType.containsKey(item.getItemMeta().getLore().get(0));
		}
			}
		}
		return false;
	}
	
	public ItemStack createItem(String name){
		if (ItemType.containsKey(name)){
		ItemStack item = ItemType.get(name).getItemStack();
		ItemMeta meta=item.getItemMeta();
		meta.setDisplayName(ItemColor.get(name)+ItemName.get(name));
		ArrayList<String> lore = new ArrayList<String>();
		lore.clear();
		if (ItemCustom.get(name).booleanValue()){
		lore.add(name);
		}
		lore.addAll(ItemLore.get(name));
		meta.setLore(lore);
		item.setItemMeta(meta);
		item=addEnchantments(item,name);
		return item;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public ArrayList getItemAbilities(ItemStack item){
		if (item.getItemMeta()!=null){
			if (item.getItemMeta().getLore()!=null){
				if (item.getItemMeta().getLore().size()>0){
					if (item.getItemMeta().getLore().get(0)!=null){
						if (ItemAbilities.get(item.getItemMeta().getLore().get(0))!=null){
				return ItemAbilities.get(item.getItemMeta().getLore().get(0));
			}
					}
			}
			}
		}
		return null;
	}
	
	public boolean hasAbility(ItemStack item,String ab){
		if (item.getItemMeta()!=null){
			if (item.getItemMeta().getLore()!=null){
				if (item.getItemMeta().getLore().size()>0){
					if (item.getItemMeta().getLore().get(0)!=null){
						if (ItemAbilities.get(item.getItemMeta().getLore().get(0))!=null){
				return ItemAbilities.get(item.getItemMeta().getLore().get(0)).contains(ab);
			}
		}
			}
			}
		}
		return false;
	}	
	
	public boolean hasPermission(Player player,ItemStack item){
		if (item.getItemMeta()!=null){
			if (item.getItemMeta().getLore()!=null){
				if (item.getItemMeta().getLore().size()>0){
					if (item.getItemMeta().getLore().get(0)!=null){
						if (ItemUsePermission.get(item.getItemMeta().getLore().get(0))!=null){
						if(ItemUsePermission.get(item.getItemMeta().getLore().get(0))){
							if (player.hasPermission("custom-items."+item.getItemMeta().getLore().get(0))){
								return true;
							}else{
								return false;
							}
						}
						}
						}
						}
					}
						}
		return true;
	}
	
	public ItemStack UpdateItem(ItemStack i){
	if (isCustom(i)){
		ItemMeta meta=i.getItemMeta();
		String name = meta.getLore().get(0);
		
		return createItem(name);
	}	
		return null;
	}
	
	public ItemStack addEnchantments(ItemStack item,String name){
		if (ItemEnchantments.containsKey(name)){
		
		for(CustomEnchantment e : ItemEnchantments.get(name)){
			if (e.getEnchantment().canEnchantItem(item)){
	    item.addUnsafeEnchantment(e.getEnchantment(), e.getLevel());	
		}else{
		
		}
		}
		}
		return item;
	}

	
}
