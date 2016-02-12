package net.livecar.NuttyWorks.nuBeton_BetterShops;
import max.hubbard.bettershops.ShopManager;
import max.hubbard.bettershops.Configurations.Language;
import max.hubbard.bettershops.Listeners.Opener;
import max.hubbard.bettershops.Shops.Shop;

import org.bukkit.entity.Player;

import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.QuestEvent;
import pl.betoncraft.betonquest.utils.PlayerConverter;

public class NUBetterShops_Event extends QuestEvent
{
	private final Shop betterShop;

    public NUBetterShops_Event(String packName, String instructions) throws InstructionParseException 
    {
        super(packName, instructions);
        String[] sParts = instructions.split(" ");
        if (sParts.length < 2) {
            throw new InstructionParseException("Not enough arguments  (Shop name)");
        }
        
        //Since shop names can be more than one word.
        String sShopName = sParts[1];

        for (int i = 2; i < sParts.length; i++) {
        	sShopName = sShopName + " " + sParts[i];
        }
        
    	Shop shop = ShopManager.fromString(sShopName);
    	if (shop != null) {
    		this.betterShop = shop;
        } else {
            if (ShopManager.loadingTotal == ShopManager.getShops().size()) 
            {
            	throw new InstructionParseException(Language.getString("Messages", "Prefix") + Language.getString("Messages", "InvalidShop"));
            } else {
            	throw new InstructionParseException(Language.getString("Messages", "Prefix") + Language.getString("Messages", "Loading"));
            }
        }
    }

    @Override
    public void run(String playerID) {
        Player callingPlayer = PlayerConverter.getPlayer(playerID);
        if (betterShop != null) {
            if (!betterShop.getBlacklist().contains(callingPlayer)) {
                if (betterShop.getOwner() != null) {
                    Opener.open(callingPlayer, betterShop);
                } else {
                	callingPlayer.sendMessage(Language.getString("Messages", "Prefix") + Language.getString("Messages", "FakeShop"));
                }
            } else {
            	callingPlayer.sendMessage(Language.getString("Messages", "Prefix") + Language.getString("Messages", "NotAllowed"));
            }
        } else {
            if (ShopManager.loadingTotal == ShopManager.getShops().size()) {
            	callingPlayer.sendMessage(Language.getString("Messages", "Prefix") + Language.getString("Messages", "InvalidShop"));
            } else {
            	callingPlayer.sendMessage(Language.getString("Messages", "Prefix") + Language.getString("Messages", "Loading"));
            }
        }
    }
}
