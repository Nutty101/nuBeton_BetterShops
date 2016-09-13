# nuBeton_BetterShops
Add's an event and condition to BetonQuest to open a bettershop's shop and validate shops are open/closed/exist.


Using this is pretty simple. 

Conditions:
  btrshops_isopen (Shop name)
    Lets you check if the shop is open or not. If it does not exist, returns false (as well as notes in your logs upon BQ loading the package)
Events:
  btrshops_opengui (Shopname)
    Opens the shop GUI for the calling player on the event.
  btrshops_setshopstate (open/closed) (ShopName)
    Opens and closes a shop based on your setting.
