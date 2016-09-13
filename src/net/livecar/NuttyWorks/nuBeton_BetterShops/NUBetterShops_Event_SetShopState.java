package net.livecar.NuttyWorks.nuBeton_BetterShops;
import max.hubbard.bettershops.ShopManager;
import max.hubbard.bettershops.Configurations.Language;
import max.hubbard.bettershops.Shops.Shop;

import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.QuestEvent;

public class NUBetterShops_Event_SetShopState extends QuestEvent
{
	private final Shop betterShop;
	private final Boolean openShop;

	public NUBetterShops_Event_SetShopState(String packName, String instructions) throws InstructionParseException 
	{
		super(packName, instructions);
		String[] instParts = instructions.split(" ");
		if (instParts.length < 3) {
			throw new InstructionParseException("Not enough arguments (State Open/Closed) (Shop name) ");
		}

		if (instParts[1].equalsIgnoreCase("closed"))
		{
			openShop = false;
		} else if (instParts[1].equalsIgnoreCase("open"))
		{
			openShop = true;
		} else {
			throw new InstructionParseException("Invalid arguments (State Open/Closed) (Shop name) ");
		}

		//Since shop names can be more than one word.
		String sShopName = instParts[2];

		for (int i = 3; i < instParts.length; i++) {
			sShopName = sShopName + " " + instParts[i];
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
	public void run(String playerID) 
	{
		if (betterShop != null) {
			betterShop.setOpen(openShop);
		}
	}
}
