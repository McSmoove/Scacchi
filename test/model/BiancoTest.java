package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class BiancoTest{
    
    public BiancoTest(){}

    /**
     * Test of hashCode method, of class Bianco.
     */
    @Test
    public void testHashCode(){
        
        System.out.println("hashCode");
        Bianco instance = new Bianco();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of equals method, of class Bianco.
     */
    @Test
    public void testEquals(){
        
        System.out.println("equals");
        Object o = null;
        Bianco instance = new Bianco();
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of toString method, of class Bianco.
     */
    @Test
    public void testToString(){
        
        System.out.println("toString");
        Bianco instance = new Bianco();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}