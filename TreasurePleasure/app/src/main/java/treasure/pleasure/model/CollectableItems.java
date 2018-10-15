package treasure.pleasure.model;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

class CollectableItems {

  // private TreasurePleasure model;
  /*
  public void setModel(TreasurePleasure model) {
    this.model = model;
  }
  */
  private int nrCollectibles;
  private HashMap<Location, Item> collectibles;
  private ArrayList<ItemType> availableItemTypes;
  private ArrayList<Location> mapConstraint;

  /**
   * Creates an instance of collectible items, only one of these exist for each game.
   *
   * @param nrCollectibles Number of collectibles on the map
   * @param availableItemTypes All possible items that can be created
   * @param mapConstraint The area of the map which all collectible items must be within
   */
  CollectableItems(int nrCollectibles, ArrayList<ItemType> availableItemTypes, ArrayList<Location>
      mapConstraint) {
    this.availableItemTypes = availableItemTypes;
    this.nrCollectibles = nrCollectibles;
    this.collectibles = new HashMap<>();
    this.mapConstraint = mapConstraint;

    for (int i = 0; i < nrCollectibles; i++) {
      this.spawnRandomItem();
    }
  }

  /**
   * Spawns a random item within the current map constraints
   */
  void spawnRandomItem() {
    int i = 0;
    int maxIterations = this.nrCollectibles * 1000;
    Location loc = getRandomLocationWithinBounds();
    while (!isAvailableLocation(loc) && i < maxIterations) {
      i++;
      loc = getRandomLocationWithinBounds();
    }
    if (i >= maxIterations) {
      // throw new RuntimeException("Could not get a new location within borders after: " + maxIterations + " tries");
    }
    Item collectible = createRandomItem();

    addItem(loc, collectible);

    // Old way to draw items on map
    // model.drawCollectibleOnMap(collectible.getType(), loc);
  }

  /**
   * Adds an item
   */
  private void addItem(Location loc, Item item) {
    collectibles.put(loc, item);
  }

  Item createRandomItem() {
    ItemType randomItemType = availableItemTypes.get((int) (Math.random() * availableItemTypes.size
        ()));
    int randomItemValue = (int) (Math.random() * 20);
    return new Item(randomItemType, randomItemValue);
  }

  /**
   * Creates a Location within map constraints
   *
   * @return Location
   */
  public Location getRandomLocationWithinBounds() {
    Location northWest = mapConstraint.get(0);
    Location southEast = mapConstraint.get(1);

    return northWest.getLocationWithinCoordinates(northWest, southEast);
  }

  // Todo: write test for this
  Boolean isAvailableLocation(Location loc) {
    for (Location occupiedLoc : collectibles.keySet()
        ) {
      if (occupiedLoc.isCloseEnough(loc)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Removes item located at given location
   */
  void removeItem(Location location) {
    collectibles.remove(location);
  }

  /**
   * Returns item at corresponding location and removes it from map
   */
  Item takeItem(Location location) throws Exception {
    Item item = collectibles.get(location);
    if (item == null) {
      throw new Exception("There's no corresponding item to collect");
    } else {
      this.removeItem(location);
      return item;
    }
  }

  public HashMap<Location, Item> getCollectibles() {
    return collectibles;
  }

}
