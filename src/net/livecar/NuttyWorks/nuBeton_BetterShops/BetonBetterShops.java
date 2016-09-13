package net.livecar.NuttyWorks.nuBeton_BetterShops;

import org.bukkit.ChatColor;
import pl.betoncraft.betonquest.BetonQuest;

public class BetonBetterShops extends org.bukkit.plugin.java.JavaPlugin implements org.bukkit.event.Listener 
{
	public static BetonBetterShops Instance;
	
	public void onEnable() {
		BetonBetterShops.Instance = this;
	    
		//Register with Beton.  
		
		//legacy names (Shouldn't have thought I wouldn't be adding more)
		BetonQuest.getInstance().registerEvents("nubettershops", NUBetterShops_Event_OpenShopGUI.class);
	    BetonQuest.getInstance().registerConditions("nubettershops", NUBetterShops_Condition_IsOpen.class);

	    //Conditions
	    BetonQuest.getInstance().registerConditions("btrshops_isopen", NUBetterShops_Condition_IsOpen.class);

	    //events
		BetonQuest.getInstance().registerEvents("btrshops_opengui", NUBetterShops_Event_OpenShopGUI.class);
		BetonQuest.getInstance().registerEvents("btrshops_setshopstate", NUBetterShops_Event_SetShopState.class);
	    
	    //objectives
		
		
		
		getLogger().log(java.util.logging.Level.ALL,ChatColor.GREEN + this.getDescription().getName() + " [V "+ this.getDescription().getVersion() + "] initialized");

		try {
			MCStatsMetrics metrics = new MCStatsMetrics(this);
			metrics.start();
		} catch (Exception e) {
			// Wheee no stats, oh well.
		}
	}
}
