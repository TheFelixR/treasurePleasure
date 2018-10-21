package treasure.pleasure.model;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import treasure.pleasure.data.Data;

/**
 * TODO
 *
 * @author Oskar
 */

class Player {

  private String username;
  private Avatar avatar;
  private Backpack<Item> backpack;
  private Chest chest;


  private ArrayList<StoreProduct> storeProducts;
  private double valueMultiplier;
  private float score;
  private double interactionDistance;
  private int nrOfCollectibles;


  Player(String username, Avatar avatar, ArrayList<StoreProduct> storeProducts) {
    this.username = username;
    this.avatar = avatar;
    this.backpack = new Backpack<>(Data.getBackpackMaxSize());
    this.storeProducts = storeProducts;
    this.score = 0;
    this.valueMultiplier = 1;
  }

  String getUsername() {
    return this.username;
  }

  Avatar getAvatar() {
    return this.avatar;
  }

  public double getValueMultiplier() {
    return valueMultiplier;
  }

  public void setValueMultiplier(double valueMultiplier) {
    this.valueMultiplier = valueMultiplier;
  }

  void setChest(Location location) {
    this.chest = new Chest(location);
  }

  void setUsername(String username) {
    Pattern p = Pattern.compile("[^A-Za-z0-9]");
    Matcher m = p.matcher(username);
    boolean b = m.find();
    if (b) {
      throw new IllegalArgumentException(
          "Could not set username: Contains special characters."); // if only normal characters and numbers are allowed

    } else {
      this.username = username;
    }
  }

  void setAvatar(Avatar avatar) {
    this.avatar = avatar;
  }

  void addToBackpack(Item i) throws Exception {
    backpack.add(i);
  }

  void emptyBackpackToChest() {
    List<Item> items = backpack.getAllItems();
    for (int i = 0; i < items.size(); i++) {
      addScore((float) (items.get(i).getValue() * this.valueMultiplier));
      chest.incrementNrItemsInChest();
    }
    backpack.removeAll();
  }

  void emptyBackpack() {
    backpack.removeAll();
  }

  void addScore(float newScorePoints) {
    this.score += newScorePoints;
    Log.w("STORE", "new money: " +newScorePoints);
    Log.w("STORE", "Players money: " + this.score);
  }
  void removeScore(float newScorePoints) {
    this.score -= newScorePoints;
  }

  void setScore(float score) {this.score = score;}

  public Integer getScore() {
    return Math.round(this.score);
  }

  boolean backpackIsFull() {
    return this.backpack.isFull();
  }

  boolean backpackIsEmpty() {
    return this.backpack.isEmpty();
  }

  List<Item> getBackpackItems() {
    return this.backpack.getAllItems();
  }

  Backpack<Item> getBackpack() {
    return this.backpack;
  }

  Integer getBackpackMaxSize() {
    return this.backpack.getMaxSize();
  }

  void setBackpackMaxSize(int backpackMaxSize) {
    this.backpack.setMaxSize(backpackMaxSize);
  }

  Location getChestLocation() {
    return chest.getLocation();
  }

  public double getInteractionDistance() {
    return interactionDistance;
  }

  public void setInteractionDistance(double interactionDistance) {
    this.interactionDistance = interactionDistance;
  }

  public int getNrOfCollectibles() {
    return nrOfCollectibles;
  }

  public void setNrOfCollectibles(int nrOfCollectibles) {
    this.nrOfCollectibles = nrOfCollectibles;
  }

  public Chest getChest() {
    return chest;
  }

  public ArrayList<StoreProduct> getStoreProducts() {
    return this.storeProducts;
  }

  public StoreProduct getStoreProduct(ProductType pt) throws Exception {

      for (StoreProduct sp: storeProducts
          ) {
        if (sp.getProductType() == pt) return sp;
      }

      throw new Exception("Product not found");
  }

}