package treasure.pleasure.model;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import treasure.pleasure.data.Data;


class Player {

    private String username;
    private Avatar avatar;
    private Backpack<Item> backpack;
    private Chest chest;
    private double dropBonus;
    private Store store;



    Player(String username, Avatar avatar){

            this.username = username;
            this.avatar = avatar;
            this.chest = new Chest();
            this.dropBonus = Data.getDropBonus();
            this.backpack = new Backpack<>(Data.getBackpackMaxSize());
            this.store = new Store(this, new Location(57.6880122,11.9792264)); //TODO hardcoded for now
    }

    void placeUpgradeCenter(Location location){
        this.store = new Store(this, location);
    }

    String getUsername() {
        return this.username;
    }

    Avatar getAvatar() {
        return this.avatar;
    }

    double getDropBonus() {
        return this.dropBonus;
    }

    void setUsername(String username) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(username);
        boolean b = m.find();
        if(b){
            throw new IllegalArgumentException("Could not set username: Contains special characters."); // if only normal characters and numbers are allowed

        } else {
            this.username = username;
        }
    }

    void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    void setDropBonus(double dropBonus) {
        if(dropBonus < Data.getMaxDropBonus()) {
            throw new IllegalArgumentException("Could not change dropbonus: Dropbonus should be > 1");
        } else {
            this.dropBonus = dropBonus;
        }
    }

    void addToBackpack(Item i) throws Exception {
        backpack.add(i);
    }

    void emptyBackpackToChest() throws Exception {
        List<Item> items = backpack.getAllItems();
        chest.sell(items);
        backpack.removeAll();
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

    Backpack<Item> getBackpack(){
      return this.backpack;
    }

    Integer getBackpackMaxSize() {
        return this.backpack.getMaxSize();
    }

    Location getChestLocation() {
        return chest.getLocation();
    }

    Location getStoreLocation() {
    return store.getLocation();
  }

}