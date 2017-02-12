package org.jakub1221.customitems;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jakub1221.customitems.commands.CmdExecutor;
import org.jakub1221.customitems.data.PlayerData;
import org.jakub1221.customitems.item.ItemHandler;
import org.jakub1221.customitems.item.Recipes;
import org.jakub1221.customitems.misc.Util;

public class CustomItems extends JavaPlugin implements Listener{

	public String version="1.6.0";
	public String build="0602";
	private ConfigDB configDB=null;
	private ItemHandler itemHandler=null;
	private API api=null;
	private PlayerData playerData=null;
	private Recipes recipes=null;
	
	private Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable(){
		
		// Init

		configDB=new ConfigDB(this);
		itemHandler=new ItemHandler(this);
		api=new API(this);
		playerData=new PlayerData(this);
		recipes=new Recipes(this);
		
		// Config startup
		
		configDB.Startup();
		
		// Register listeners
		
		getServer().getPluginManager().registerEvents((Listener) new org.jakub1221.customitems.listeners.BlockListener(this), this);
		getServer().getPluginManager().registerEvents((Listener) new org.jakub1221.customitems.listeners.EntityListener(this), this);
		getServer().getPluginManager().registerEvents((Listener) new org.jakub1221.customitems.listeners.PlayerListener(this), this);
		
		// Set command executor
		
		this.getCommand("ci").setExecutor((CommandExecutor) new CmdExecutor(this));
		
		log.info(Util.editConsole("Plugin loaded! Version "+version+" / Build: "+build));
		
		
	}
	public void onDisable(){
		
		log.info(Util.editConsole("Plugin stopped!"));
		
	}
	
	public boolean hasPermission(Player player,String str){
		if (player.hasPermission(str)){
			return true;
		}else if (player.hasPermission("custom-items.*")){
			return true;
			}
		return false;
	}
	
	public ConfigDB getConfigDB(){
		return this.configDB;
	}
	
	public ItemHandler getItemHandler(){
		return this.itemHandler;
	}
	
	public Logger getLog(){
		return this.log;
	}
	
	public API getAPI(){
		return api;
	}
	
	public PlayerData getPlayerData(){
		return this.playerData;
	}
	
	public Recipes getRecipes(){
		return this.recipes;
	}
	
}
