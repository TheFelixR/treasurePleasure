package treasure.pleasure.model;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import treasure.pleasure.data.Data;

/**
 * Contains items and their location for all items on the map. This class can also spawn new random
 * items at random locations on the map.
 *
 * @author jesper, oskar and david
 */

class CollectibleItems {

  private int nrCollectibles;
  private HashMap<Location, Item> collectibles;
  private ArrayList<ItemType> availableItemTypes;
  private ArrayList<Location> mapConstraint;

  /**
   * Creates an instance of collectible items, only one of these exist for each game.
   *
   * @param availableItemTypes All possible items that can be created
   * @param mapConstraint The area of the map which all collectible items must be within
   */
  CollectibleItems(ArrayList<ItemType> availableItemTypes, ArrayList<Location>
      mapConstraint) {
    this.availableItemTypes = availableItemTypes;
    this.nrCollectibles = Data.getNrOfCollectables();
    this.collectibles = new HashMap<>();
    this.mapConstraint = mapConstraint;

    this.spawnInitialItems();
  }

  void spawnInitialItems() {
    if (Data.isDemo()) {
      addItem(new Location(57.687740, 11.978000), new Item(ItemType.DIAMOND, 99)); // first item
      addItem(new Location(57.688273, 11.978599), new Item(ItemType.GOLD, 71)); // second item
      addItem(new Location(57.688113, 11.979645), new Item(ItemType.IRON, 56)); // third item
      addItem(new Location(57.688713, 11.979545), new Item(ItemType.STONE, 32)); // forth item
    }
    {
      for (int i = this.collectibles.size() - 1; i < nrCollectibles; i++) {
        try {
          this.spawnRandomItem();
        } catch (Exception e) {
          Log.w("CollectibleItems", "Could not spawn a item since its to close to other items");
        }
      }
    }
  }

  /**
   * Spawns a random item
   */
  void spawnRandomItem() throws Exception {
    int i = 0;
    int maxIterations = this.nrCollectibles * Data.getNrCollecteblesIncrementer();
    Location loc = getRandomLocationWithinBounds();
    while (!isAvailableLocation(loc) && i < maxIterations) {
      i++;
      loc = getRandomLocationWithinBounds();
    }
    if (i >= maxIterations) {
      throw new Exception(
          "Could not get a new location within borders after: " + maxIterations + " tries");
    }
    Item collectible = createRandomItem();

    addItem(loc, collectible);
  }

  /**
   * Adds the item the location
   */
  private void addItem(Location loc, Item item) {
    collectibles.put(loc, item);
  }

  private Item createRandomItem() {
    return new Item(availableItemTypes);
  }

  /**
   * Creates a Location within map constraints
   *
   * @return Location
   */
  public Location getRandomLocationWithinBounds() {
    Location northWest = mapConstraint.get(0);
    Location southEast = mapConstraint.get(2);

    return northWest.getLocationWithinCoordinates(northWest, southEast);
  }

  /**
   * Checks if given location is valid i.e there are no other items within 'closeEnough' distance
   * closeEnough is DEFAULT locations maxDistance MULTIPLIES with itemDistanceMultiplier
   * @return boolean if its valid
   */
  Boolean isAvailableLocation(Location loc) {
    if (Data.isDebug()) {
      return true;
    }
    for (Location occupiedLoc : collectibles.keySet()
        ) {
      if (occupiedLoc.isCloseEnough(loc, Data.getMaxInteractionDistance() * Data.getItemDistanceMultiplier())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Removes an item from collectibles arrayList
   */
  void removeItem(Location location) {
    collectibles.remove(location);
  }

  /**
   * Takes an item at specified location. Throws exception if there is no item at that location
   */
  Item takeItem(Location location) throws Exception {
    Item item = collectibles.get(location);
    this.removeItem(location);

    if (item == null) {
      throw new Exception("There's no corresponding item to collect");
    }
    return item;
  }

  int getNrCollectibles() {
    return this.nrCollectibles;
  }

  /**
   * Increase the nrOfCollectibles that is on the map.
   */
  void setNrCollectibles(int nrOfCollectibles) {
    int oldCollectibles = this.nrCollectibles;
    this.nrCollectibles = nrOfCollectibles;
    for (int i = oldCollectibles; i < nrCollectibles; i++) {
      try {
        this.spawnRandomItem();
      } catch (Exception e) {
        Log.w("CollectibleItems", "Could not spawn a item since its to close to other items");
      }
    }
  }

  public HashMap<Location, Item> getCollectibles() {
    return collectibles;
  }

}
