package UnitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import treasure.pleasure.model.CollectableItem;
import treasure.pleasure.model.ItemType;
import treasure.pleasure.model.Item;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class CollectabeItemUnitTest {
  CollectableItem collectableItems;

  @Before
  public void initLocations() {
    int maxItems = 50;
    ArrayList<Item> availableItems = new ArrayList<>();
    availableItems.add(new Item(ItemType.DIAMOND , 30));
    availableItems.add(new Item(ItemType.GOLD , 10));
    availableItems.add(new Item(ItemType.STONE , 5));
    collectableItems = new CollectableItem(maxItems, availableItems);
  }

  @Test
  public void setUniqueLocations() {

  }
}
