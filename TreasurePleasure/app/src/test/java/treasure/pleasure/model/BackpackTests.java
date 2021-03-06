package treasure.pleasure.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.spec.ECField;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


public class BackpackTests {
  Backpack<Object> b;
  Object item;
  Object item2;
  Object item3;

  Object item4;

  @Before
  public void initBackPack() {
    b = new Backpack<Object>(3);
    item = new Object();
    item2 = new Object();
    item3 = new Object();
    item4 = new Object();


  }


  @Test
  public void testUpgrade_Pos_Input() {

    b.upgrade(1);
    assertEquals(4, b.getMaxSize());
    assertEquals(2, b.getBackPackLevel());

    testClassInvariant();


  }


  @Test
  public void testUpgrade_Neg_Input() {

    b.upgrade(-1);
    assertEquals(3, b.getMaxSize());
    assertEquals(1, b.getBackPackLevel());

    testClassInvariant();


  }


  @Test
  public void testUpgrade_0_Input() {

    b.upgrade(0);
    assertEquals(3, b.getMaxSize());
    assertEquals(1, b.getBackPackLevel());

    testClassInvariant();


  }

  @Test
  public void test_appended_value_does_not_exist() {
    Collection slots;

    try {
      b.add(item);
      b.add(item2);
      b.add(item3);

      b.add(item4);

    } catch (Exception e) {
      System.out.println(e);
    }

    slots = b.getAllItems();
    assertFalse(slots.contains(item4));

  }


  @Test
  public void test_appended_value_exists() {
    List slots;

    try {
      b.add(item);
    } catch (Exception e) {
      e.printStackTrace();
    }
    slots = b.getAllItems();
    assertTrue(slots.contains(item));

  }


  @Test
  public void test_remove_all(){

    try {
      b.add(item);
      b.add(item2);
      b.add(item3);
    }
    catch (Exception e){
      // no exception should be fired.
      assertFalse(true);

    }


    b.removeAll();

    assertEquals(b.getAllItems().size(), 0);
    assertEquals(b.getnOfBusySlots(), 0);
    assertEquals(b.getnOfEmptySlots(), b.getMaxSize());

    testClassInvariant();

  }


  @Test
  public void test_copy_constructor(){

    try{
      b.add(item);
    }catch(Exception e ){
      assertFalse(true);
    }

    Backpack copyPack = new Backpack(b);

    assertEquals(b.getnOfEmptySlots(),copyPack.getnOfEmptySlots());
    assertEquals(b.getMaxSize(),copyPack.getMaxSize());
    assertEquals(b.getBackPackLevel(),copyPack.getBackPackLevel());


    assertArrayEquals(b.getAllItems().toArray(),copyPack.getAllItems().toArray());



  }

  private void testClassInvariant() {

    assertTrue(b.getMaxSize() >= b.getnOfBusySlots());


  }


}
