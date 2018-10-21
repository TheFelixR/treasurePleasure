package treasure.pleasure.model;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import treasure.pleasure.data.Data;

/**
 * Contains items and their location for all items on the map.
 * This class can also spawn new random items at random locations on the map.
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

    for (int i = 0; i < nrCollectibles; i++) {
      try {
        this.spawnRandomItem();
      } catch (Exception e) {
        Log.w("CollectibleItems", "Could not spawn a item since its to close to other items");
      }
    }
  }

  /**
   * Spawns a random item
   * @throws Exception
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
      throw new Exception("Could not get a new location within borders after: " + maxIterations + " tries");
    }
    Item collectible = createRandomItem();

    addItem(loc, collectible);
  }

  /**
   * Adds the item the location
   * @param loc
   * @param item
   */
  private void addItem(Location loc, Item item) {
    collectibles.put(loc, item);
  }

  private Item createRandomItem() {
    return new Item(availableItemTypes);
  }

  /**
   * Creates a Location within map constraints
   * @return Location
   */
  public Location getRandomLocationWithinBounds() {
    Location northWest = mapConstraint.get(0);
    Location southEast = mapConstraint.get(2);

    return northWest.getLocationWithinCoordinates(northWest, southEast);
  }

  /**
   * Checks if given location is valid i.e there are no other items within 'closeEnough' distance
   * @param loc
   * @return boolean if its valid
   * Todo: write test for this
   */
  Boolean isAvailableLocation(Location loc) {
    if (Data.isDebug()) return true;
      for (Location occupiedLoc : collectibles.keySet()
        ) {
      if (occupiedLoc.isCloseEnough(loc, loc.getMaxInteractionDistance() * 4)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Removes an item from collectibles arrayList
   * @param location
   */
  void removeItem(Location location) {
    collectibles.remove(location);
  }

  /**
   * Takes an item at specified location. Throws exception if there is no item at that location
   * @param location
   * @return
   * @throws Exception
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
   * @param nrOfCollectibles
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
