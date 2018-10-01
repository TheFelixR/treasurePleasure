package UnitTests;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import goteborgsuniversitet.maptestapp.core.Containers.BackPack;
import goteborgsuniversitet.maptestapp.core.Item;
import goteborgsuniversitet.maptestapp.core.Valuable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;



public class BackPackTests {
    BackPack<Object> b;
    Object item;
    Object item2;
    Object item3;

    Object item4;

    @Before
    public void initBackPack(){
        b = new BackPack<Object>(3);
        item = new Object();
        item2 = new Object();
        item3 = new Object();
        item4 = new Object();



    }


    @Test
    public void testUpgrade_Pos_Input(){

        b.upgrade(1);
        assertEquals(4, b.getMaxSize());
        assertEquals(2, b.getBackPackLevel());

        testClassInvariant();


    }


    @Test
    public void testUpgrade_Neg_Input(){


        b.upgrade(-1);
        assertEquals(3, b.getMaxSize());
        assertEquals(1, b.getBackPackLevel());

        testClassInvariant();


    }



    @Test
    public void testUpgrade_0_Input(){

        b.upgrade(0);
        assertEquals(3, b.getMaxSize());
        assertEquals(1, b.getBackPackLevel());

        testClassInvariant();


    }

    @Test
    public void test_appended_value_does_not_exist(){
        Collection slots;


        try {
            b.appendTo(item);
            b.appendTo(item2);
            b.appendTo(item3);

            b.appendTo(item4);

        } catch (Exception e) {

            System.out.println("IM AN EXCEPTION");

        }


        slots = b.getAllItems();
        assertFalse(slots.contains(item4));

    }


    @Test
    public void test_appended_value_exists(){
        List slots;


        try {
            b.appendTo(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        slots = b.getAllItems();
        assertTrue(slots.contains(item));

    }










    private void testClassInvariant(){

        assertTrue(b.getMaxSize() >= b.getnOfBusySlots());


    }









}
