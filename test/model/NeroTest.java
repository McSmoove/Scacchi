package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class NeroTest{
    
    public NeroTest(){}

    /**
     * Test of equals method, of class Nero.
     */
    @Test
    public void testEquals(){
        
        System.out.println("equals");
        Object o = null;
        Nero instance = new Nero();
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of hashCode method, of class Nero.
     */
    @Test
    public void testHashCode(){
        
        System.out.println("hashCode");
        Nero instance = new Nero();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of toString method, of class Nero.
     */
    @Test
    public void testToString(){
        
        System.out.println("toString");
        Nero instance = new Nero();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}