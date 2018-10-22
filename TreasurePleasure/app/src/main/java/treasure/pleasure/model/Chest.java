package treasure.pleasure.model;

import java.util.List;
import treasure.pleasure.data.Data;

/**
 * @author john
 */

public class Chest<T extends ItemCallBack> {

  private Location location;
  private double score;
  private int nrItemsInChest;

  /**
   * Constructor to call when all values need to be parameterized, for example when fetchign data
   * from db and re-creating the player.
   *
   * @param score current score of the player.
   * @param location sets the location of a chest
   */
  Chest(double score, Location location) {
    this.location = location;
    this.score = score;
    nrItemsInChest = 0;
  }

  /**
   * constructor of the chest to be called when a player is initilized.
   *
   * @param location sets the location of a chest
   */
  Chest(Location location) {
    this.score = Data.getInitialChestValue();
    this.location = location;
    this.nrItemsInChest = 0;
  }

  /**
   * Loops over and "sells" all items given
   * @param items
   * @return the collective value of all items
   */
  double sell(List<T> items) {
    double valueOfAllItems = 0;
    for (T item : items) {
      valueOfAllItems += sell(item);
      incrementNrItemsInChest();
    }
    return valueOfAllItems;
  }

  public int getNrItemsInChest() {
    return nrItemsInChest;
  }

  void incrementNrItemsInChest() {
    nrItemsInChest++;
  }

  /**
   * "Sells an item"
   * @param item
   * @return The value of the item
   */
  double sell(T item) {
    return item.getValueCallBack();
  }

  Location getLocation() {
    return new Location(location);
  }
}
