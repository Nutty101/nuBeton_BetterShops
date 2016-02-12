# nuBeton_BetterShops
Add's an event and condition to BetonQuest to open a bettershop's shop and validate shops are open/closed/exist.


Using this is pretty simple. 

conditions.yml:
test_condition: 'nubettershops Example Shop'  #validates if the shop is open. Any other state returns false.

Events.yml:
test_event: 'nubettershops Example Shop'  # opens the shop gui to the player
