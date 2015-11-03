package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class PedoneTest{
    
    public PedoneTest(){}

    /**
     * Test of isMoved method, of class Pedone.
     */
    @Test
    public void testIsMoved(){
        
        System.out.println("isMoved");
        Pedone instance = null;
        boolean expResult = false;
        boolean result = instance.isMoved();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setMoved method, of class Pedone.
     */
    @Test
    public void testSetMoved(){
        
        System.out.println("setMoved");
        Pedone instance = null;
        instance.setMoved();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of moved method, of class Pedone.
     */
    @Test
    public void testMoved(){
        
        System.out.println("moved");
        boolean m = false;
        Pedone instance = null;
        instance.moved(m);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}