package org.jakub1221.customitems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.material.MaterialData;
import org.jakub1221.customitems.misc.Util;
import org.jakub1221.customitems.objects.CustomEnchantment;
import org.jakub1221.customitems.objects.CustomID;
import org.jakub1221.customitems.objects.CustomRecipe;

public class ConfigDB {

	private CustomItems instance=null;
	public YamlConfiguration config;
	public YamlConfiguration items;
	public YamlConfiguration recipes;
	public File configF = new File("plugins/CustomItems/config.yml");
	public File itemsF = new File("plugins/CustomItems/items.yml");
	public File recipesF = new File("plugins/CustomItems/recipes.yml");
	private ArrayList<String> RegisteredItems = new ArrayList<String>();
	private Map<String,CustomID> ItemType = new HashMap<String,CustomID>();
	private Map<String,ArrayList<String>> ItemAbilities = new HashMap<String,ArrayList<String>>();
	private Map<String,Boolean> ItemUsePermission = new HashMap<String,Boolean>();
	private Map<String,ArrayList<String>> ItemLore = new HashMap<String,ArrayList<String>>();
	private Map<String,String> ItemColor = new HashMap<String,String>();
	private Map<String,String> ItemName = new HashMap<String,String>();
	private Map<String,Boolean> ItemRecipe = new HashMap<String,Boolean>();
	private ArrayList<Recipe> ItemRecipes = new ArrayList<Recipe>();
	private ArrayList<String> ItemToDelete = new ArrayList<String>();
	private Map<String,Boolean> ItemUseCustom = new HashMap<String,Boolean>();
	private Map<String,ArrayList<CustomEnchantment>> ItemEnchantments = new HashMap<String,ArrayList<CustomEnchantment>>();
	
	public boolean UpdateItems=true;
	public int Poison_Duration=200;
	public int Disorient_Duration=800;
	public int Blind_Duration=1000;
	
	public ConfigDB(CustomItems i){
		instance=i;
	}
	
	
	public void Startup(){
		boolean isNewConfig=false;
		boolean isNewRecipes=false;
		new File("plugins/CustomItems").mkdirs();
		
		if (!configF.exists())
			try {
				instance.getLog().info(Util.editConsole("Creating new config..."));
				configF.createNewFile();
                
		
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		if (!recipesF.exists())
			try {
				instance.getLog().info(Util.editConsole("Creating new recipes..."));
				recipesF.createNewFile();
                isNewRecipes=true;
		
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		if (!itemsF.exists())
			try {
				instance.getLog().info(Util.editConsole("Creating new items..."));
				itemsF.createNewFile();
				isNewConfig=true;
		
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		recipes = new YamlConfiguration();
		items = new YamlConfiguration();
		config = new YamlConfiguration();


		
		try {
			config.load(configF);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
		
			e.printStackTrace();
		}
		
		try {
			items.load(itemsF);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
		
			e.printStackTrace();
		}
		
		
		try {
			recipes.load(recipesF);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
		
			e.printStackTrace();
		}
		
		
		if (isNewConfig){
			config.set("version", instance.version);
			config.set("build", instance.build);
			config.set("UpdateItems", true);
			config.set("AbilitiesData.Poison_Duration", 200);
			config.set("AbilitiesData.Disorient_Duration", 800);
			config.set("AbilitiesData.Blind_Duration", 1000);
			try {
				config.save(configF);
			} catch (IOException e) {
	
				e.printStackTrace();
			}
			
			ArrayList<String> abilities2 = new ArrayList<String>();
			ArrayList<String> abilities = new ArrayList<String>();
			ArrayList<String> lore = new ArrayList<String>();
			ArrayList<String> lore2 = new ArrayList<String>();
			ArrayList<String> itm = new ArrayList<String>();
			ArrayList<String> ench = new ArrayList<String>();
			ench.add("KNOCKBACK");
			itm.add("SwordExample");
			itm.add("BowExample");
			items.set("RegisteredItems", itm);
			
			lore.add("This is lore");
			lore.add("This is another lore");
		
			lore2.add("This is lore");
			lore2.add("This is another lore");
			
			abilities.add("Poison");
			abilities.add("Lighting");
			abilities.add("Death");
			
			items.set("Items.SwordExample.ID", 276);
			items.set("Items.SwordExample.DisplayName", "Sword Example");
			items.set("Items.SwordExample.UsePermission", false);
			items.set("Items.SwordExample.Abilities", abilities);
			items.set("Items.SwordExample.Lore", lore);
			items.set("Items.SwordExample.Color", "§6");
			items.set("Items.SwordExample.UseRecipe", false);
			items.set("Items.SwordExample.UseCustom", true);
			items.set("Items.SwordExample.Enchantments", ench);
			items.set("Items.SwordExample.EnchantmentsLevel.KNOCKBACK",2);
			abilities2.add("Teleport");
			
			items.set("Items.BowExample.ID", 261);
			items.set("Items.BowExample.DisplayName", "Bow Example");
			items.set("Items.BowExample.UsePermission", false);
			items.set("Items.BowExample.Abilities", abilities2);
			items.set("Items.BowExample.Lore", lore2);
			items.set("Items.BowExample.Color", "§a");
			items.set("Items.BowExample.UseRecipe", true);
			items.set("Items.BowExample.UseCustom", true);
			
			try {
				items.save(itemsF);
			} catch (IOException e) {
	
				e.printStackTrace();
			}
			
		}
		
		if (isNewRecipes){
		
			recipes.set("Recipes.BowExample.1", 0);
			recipes.set("Recipes.BowExample.2", 0);
			recipes.set("Recipes.BowExample.3", 0);
			recipes.set("Recipes.BowExample.4", 1);
			recipes.set("Recipes.BowExample.5", 1);
			recipes.set("Recipes.BowExample.6", 1);
			recipes.set("Recipes.BowExample.7", 0);
			recipes.set("Recipes.BowExample.8", 0);
			recipes.set("Recipes.BowExample.9", 0);
			
			try {
				recipes.save(recipesF);
			} catch (IOException e) {
	
				e.printStackTrace();
			}
			
		}
		
		// Version check
		
		try {
			config.load(configF);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
		
			e.printStackTrace();
		}
		
		if (!config.contains("UpdateItems")){
			config.set("UpdateItems", true);
		}
		
		if (!config.contains("AbilitiesData")){
			config.set("AbilitiesData.Poison_Duration", 200);
			config.set("AbilitiesData.Disorient_Duration", 800);
		}
		
		if (!config.contains("AbilitiesData.Blind_Duration")){
			config.set("AbilitiesData.Blind_Duration", 1000);
		}
		
		if (config.contains("version")){
			if (!config.getString("version").equals(instance.version)){
				config.set("version", instance.version);
			}
		}else{
			config.set("version", instance.version);
		}
		
		if (config.contains("build")){
			if (!config.getString("build").equals(instance.build)){
				config.set("build", instance.build);
			}
		}else{
			config.set("build", instance.build);
		}
	
		config.set("build", instance.build);
		
		try {
			config.save(configF);
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		Reload();
		

	}
	
	@SuppressWarnings("unchecked")
	public void addItem(String name,String displayname,int ID,boolean permission,String color,boolean userecipe,boolean usecustom,ArrayList<String> lore,ArrayList<String> abilities,CustomRecipe recipe,ArrayList<CustomEnchantment> customenchantments){
		try {
			items.load(itemsF);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
		
			e.printStackTrace();
		}
		
		try {
			recipes.load(recipesF);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
		
			e.printStackTrace();
		}
	
		ArrayList<String> itm = (ArrayList<String>) items.getList("RegisteredItems");
		ArrayList<String> ench_name = new ArrayList<String>();
		
		itm.add(name);
		items.set("RegisteredItems", itm);	
		items.set("Items."+name+".ID", ID);
		items.set("Items."+name+".DisplayName", displayname);
		items.set("Items."+name+".UsePermission", permission);
		items.set("Items."+name+".Abilities", abilities);
		items.set("Items."+name+".Lore", lore);
		items.set("Items."+name+".Color", color);
		items.set("Items."+name+".UseRecipe", userecipe);
		items.set("Items."+name+".UseCustom", usecustom);
		if (customenchantments!=null){
		for(CustomEnchantment e : customenchantments){
		ench_name.add(e.getEnchantment().toString());
		items.set("Items."+name+".EnchantmentsLevel."+e.getEnchantment().toString(), e.getLevel());
		}
		items.set("Items."+name+".Enchantments", ench_name);
		}
		
		if (userecipe){
        int ii=0;
        for(ii=0;ii<=8;ii++){
			recipes.set("Recipes."+name+"."+(ii+1), ((Integer) recipe.getData().get(ii)).intValue());
        }
		}
		
		try {
			items.save(itemsF);
		} catch (IOException e) {

			e.printStackTrace();
		}
		try {
			recipes.save(recipesF);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void Reload(){
		
		// Load all
		
		try {
			config.load(configF);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
		
			e.printStackTrace();
		}
		
		try {
			items.load(itemsF);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
		
			e.printStackTrace();
		}
		
		
		try {
		    recipes.load(recipesF);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
		
			e.printStackTrace();
		}
		
		// Load end
		
		Poison_Duration=config.getInt("AbilitiesData.Poison_Duration");
		Disorient_Duration=config.getInt("AbilitiesData.Disorient_Duration");
		UpdateItems=config.getBoolean("UpdateItems");
		instance.getLog().info(Util.editConsole("Loading config and items..."));
		int i=0;
		RegisteredItems.clear();
		ItemType.clear();
		ItemAbilities.clear();
		ItemUsePermission.clear();
		ItemLore.clear();
		ItemColor.clear();
		ItemName.clear();
		ItemRecipe.clear();
		ItemRecipes.clear();
		ItemToDelete.clear();
		ItemUseCustom.clear();
		ItemEnchantments.clear();
		RegisteredItems=(ArrayList<String>) items.getList("RegisteredItems");
		if(RegisteredItems.size()>0){
		for (String str : RegisteredItems){
			if (checkData(str)){
			ItemType.put(str, new CustomID(items.getString("Items."+str+".ID")));
			ItemAbilities.put(str, (ArrayList<String>) items.getList("Items."+str+".Abilities"));
			ItemUsePermission.put(str, items.getBoolean("Items."+str+".UsePermission"));
			ItemName.put(str, items.getString("Items."+str+".DisplayName"));
			ItemRecipe.put(str, items.getBoolean("Items."+str+".UseRecipe"));
			if(items.contains("Items."+str+".UseCustom")){
			ItemUseCustom.put(str, items.getBoolean("Items."+str+".UseCustom"));
			}else{
                   ItemUseCustom.put(str, true);
			}
			if (items.contains("Items."+str+".Lore")){
				
			ItemLore.put(str, (ArrayList<String>) items.getList("Items."+str+".Lore"));
			}else{
				ArrayList<String> ar = new ArrayList<String>();
				ar.add(" ");
				ItemLore.put(str, ar);
				
			}
			if (items.contains("Items."+str+".Color")){
			
				ItemColor.put(str, items.getString("Items."+str+".Color"));
				
				}else{
				
					ItemColor.put(str, "§f");
					
				}
			
			if (items.contains("Items."+str+".Enchantments")){
				
				ArrayList<CustomEnchantment> enchs = new ArrayList<CustomEnchantment>();
				if (items.getList("Items."+str+".Enchantments")!=null){
					for(String e : (ArrayList<String>) items.getList("Items."+str+".Enchantments")){
				           enchs.add(new CustomEnchantment(Enchantment.getByName(e),items.getInt("Items."+str+".EnchantmentsLevel."+e)));
					}
				ItemEnchantments.put(str, enchs);
				}
			}
			i++;
			}else{instance.getLog().warning(Util.editConsole(str+" item cannot be loaded!"));ItemToDelete.add(str);}
		}
		instance.getItemHandler().setItemType(ItemType);
		instance.getItemHandler().setItemAbilities(ItemAbilities);
		instance.getItemHandler().setItemUsePermission(ItemUsePermission);
		instance.getItemHandler().setItemLore(ItemLore);
		instance.getItemHandler().setItemColor(ItemColor);
		instance.getItemHandler().setItemName(ItemName);
		instance.getItemHandler().setItemCustom(ItemUseCustom);
		instance.getItemHandler().setItemEnchantments(ItemEnchantments);
		
		// Remove wrong items
		
		for (String str : ItemToDelete){
			RegisteredItems.remove(str);
		}
		
		 //--------------------------------------------------------------------------------
		 // Recipe generator
	   	 //--------------------------------------------------------------------------------
		instance.getLog().info(Util.editConsole("Loading recipes..."));
		for(String a : RegisteredItems){
			if (ItemRecipe.get(a).booleanValue()){
				ArrayList<String> lstr = new ArrayList<String>();
				lstr.add("R");
				lstr.add("A");
				lstr.add("B");
				lstr.add("C");
				lstr.add("D");
				lstr.add("E");
				lstr.add("F");
				lstr.add("G");
				lstr.add("H");
				lstr.add("I");
             
				ShapedRecipe sh = new ShapedRecipe(instance.getItemHandler().createItem(a));
                 String l1="";
                 String l2="";
                 String l3="";
                 int ii=1;
                 for (ii=1;ii<=9;ii++){
                	 if(new CustomID(recipes.getString("Recipes."+a+"."+ii)).getID()==0){
                		 if (ii==1 || ii==2 || ii==3){
                			 l1=l1+"R";
                		 }else if (ii==4 || ii==5 || ii==6){
                			 l2=l2+"R";
                		 }else if (ii==7 || ii==8 || ii==9){
                			 l3=l3+"R";
                		 }
                	 }else{
                		 if (ii==1 || ii==2 || ii==3){
                			 l1=l1+lstr.get(ii);
                			
                		 }else if (ii==4 || ii==5 || ii==6){
                			 l2=l2+lstr.get(ii);
                		
                		 }else if (ii==7 || ii==8 || ii==9){
                			 l3=l3+lstr.get(ii);
                			
                		 }
                	 }
                 }
                 sh.shape(l1,l2,l3);
                 ii=1;
                 for (ii=1;ii<=9;ii++){
                	 if(new CustomID(recipes.getString("Recipes."+a+"."+ii)).getID()!=0){
                	 if (ii==1 || ii==2 || ii==3){
            			 l1=l1+lstr.get(ii);
            
            			 sh.setIngredient(lstr.get(ii).charAt(0), Material.getMaterial(new CustomID(recipes.getString("Recipes."+a+"."+ii)).getID()));
            	
                	 }else if (ii==4 || ii==5 || ii==6){
            			 l2=l2+lstr.get(ii);
            			 sh.setIngredient(lstr.get(ii).charAt(0), Material.getMaterial(new CustomID(recipes.getString("Recipes."+a+"."+ii)).getID()));
                   	
            		 }else if (ii==7 || ii==8 || ii==9){
            			 l3=l3+lstr.get(ii);
            			 sh.setIngredient(lstr.get(ii).charAt(0), Material.getMaterial(new CustomID(recipes.getString("Recipes."+a+"."+ii)).getID()));
                   	
            		 }
                	 }
                 }
             
                 ItemRecipes.add(sh);
                 instance.getRecipes().setRecipes(ItemRecipes);				
			}
		}
		
		 //--------------------------------------------------------------------------------
		 // Recipe generator end
	   	 //--------------------------------------------------------------------------------
		
		instance.getLog().info(Util.editConsole(i+" items successfully registered!"));
		instance.getLog().info(Util.editConsole("Config and items loaded!"));
	}else{
		instance.getLog().warning(Util.editConsole("There are not any registered items!"));
	}
		// Auto update inventory
		 Player [] AllOnPlayers = Bukkit.getServer().getOnlinePlayers();
		   if (Bukkit.getServer().getOnlinePlayers().length>0){
		   int _i = 0;
		   for (_i=0;_i<=Bukkit.getServer().getOnlinePlayers().length-1;_i++){
			  UpdatePlayer(AllOnPlayers[_i]);
		   }
		   }

		
	}
	
	public void UpdatePlayer(Player player){
		if (UpdateItems){
			for(ItemStack i : player.getInventory().getContents()){
			if(i!=null){	
				if (i.getItemMeta()!=null){
					if (i.getItemMeta().getLore()!=null){
					ItemStack itm = instance.getItemHandler().UpdateItem(i);
					if(itm!=null){
						int ii=0;
						for(ii=0;ii<=3;ii++){
						if (player.getInventory().first(i)!=-1){
							player.getInventory().setItem(player.getInventory().first(i),itm);
					
								player.updateInventory();
							
						}
					
						}
						}
					}
				}
				}
			}
		}
	}
	
	public boolean checkData(String str){
	 boolean status = true;
	 ArrayList<String> errorText = new ArrayList<String>();
	 if (items.contains("Items."+str)){
	 if(items.getInt("Items."+str+".ID")== Math.round(items.getInt("Items."+str+".ID"))){}
	 else{
		 errorText.add(str+": ID is not a number!");
		 
		 status=false;
	 }
	 ArrayList<String> arraySTR = (ArrayList<String>) items.getList("Items."+str+".Abilities");
	 if (arraySTR.isEmpty()){
		
		
	 }else{
	 for (String a : arraySTR){
		 if (!instance.getItemHandler().getAbilities().contains(a)){
			 errorText.add(str+": "+a+": Ability does not exist!");
			 status=false;
		 }
	 }
	 }
	 
	 
	 }else{
		 status=false;
		 errorText.add(str+" : Item does not exist!");
	
	 }
	 if (status){
	 ItemStack item = new CustomID(items.getString("Items."+str+".ID")).getItemStack();
	 
		if (items.contains("Items."+str+".Enchantments")){
			if (items.getList("Items."+str+".Enchantments")!=null){
		for(String e : (ArrayList<String>) items.getList("Items."+str+".Enchantments")){
			if (Enchantment.getByName(e)!=null){
			if (Enchantment.getByName(e).canEnchantItem(item)){
			if (items.contains("Items."+str+".EnchantmentsLevel."+e)){
	        if (32767<items.getInt("Items."+str+".EnchantmentsLevel."+e)){
	        	errorText.add("Enchantment "+e+" have level "+items.getInt("Items."+str+".EnchantmentsLevel."+e)+" , but max is 1000!");
				status=false;
				
			}
			}else{
				errorText.add("Enchantment "+e+" doesn´t have own level! ");
				status=false;
			}
		}else{
			
			errorText.add("Enchantment "+e+" cannot be applied to "+item.getType().toString());
			status=false;
		}
		}else{
			errorText.add("Enchantment "+e+" does not exist!");
			status=false;
		}
		}
			}
		}
	 }
	 
	 if (status){
		 if (items.getBoolean("Items."+str+".UseRecipe")){
			
			 int i=1;
			 for (i=1;i<=9;i++){
				 if (recipes.getInt("Recipes."+str+"."+i)!=0){
					 if (Material.getMaterial(recipes.getInt("Recipes."+str+"."+i))==null){
					errorText.add("Recipe error - ID "+recipes.getInt("Recipes."+str+"."+i)+" does not exist!");
					status=false;
			 }
			 }
			 }
		 }
	 }
	 
	 if (!status){
		 instance.getLog().warning(Util.editConsole("==========================================="));
		 instance.getLog().warning(Util.editConsole("======Error while loading "+str+":======"));
		 for (String a : errorText){
			 instance.getLog().warning(a);
		 }
		 instance.getLog().warning(Util.editConsole("==========================================="));
		 instance.getLog().warning(Util.editConsole("==========================================="));
	 }
	 return status;
	}
	
}

