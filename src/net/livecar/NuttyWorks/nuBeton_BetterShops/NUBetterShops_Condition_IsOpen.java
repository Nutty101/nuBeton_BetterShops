package net.livecar.NuttyWorks.nuBeton_BetterShops;
import max.hubbard.bettershops.ShopManager;
import max.hubbard.bettershops.Configurations.Language;
import max.hubbard.bettershops.Shops.Shop;
import pl.betoncraft.betonquest.InstructionParseException;
import pl.betoncraft.betonquest.api.Condition;

public class NUBetterShops_Condition_IsOpen extends Condition
{
	private final String sBetterShop;

	public NUBetterShops_Condition_IsOpen(String packName, String instruction) throws InstructionParseException
	{
		super(packName, instruction);
		String[] sParts = instructions.split(" ");
		if (sParts.length < 2) {
			throw new InstructionParseException("Not enough arguments (Shop name?)");
		}
        //Since shop names can be more than one word.
        String sShopName = sParts[1];

        for (int i = 2; i < sParts.length; i++) {
        	sShopName = sShopName + " " + sParts[i];
        }
        
    	Shop shop = ShopManager.fromString(sShopName);
		if (shop != null) {
			this.sBetterShop = sShopName;
		} else {
			if (ShopManager.loadingTotal == ShopManager.getShops().size()) {
				throw new InstructionParseException(Language.getString("Messages", "Prefix") + Language.getString("Messages", "InvalidShop"));
			} else {
				throw new InstructionParseException(Language.getString("Messages", "Prefix") + Language.getString("Messages", "Loading"));
			}
		}
	}

	public boolean check(String playerID)
	{
		Shop shop = ShopManager.fromString(this.sBetterShop);
		if (shop == null)
			return false;
		if (!shop.isOpen())
			return false;
		return true;
	}
}
