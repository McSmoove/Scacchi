package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class TorreTest{
    
    public TorreTest(){}
    
    /**
     * Test of isMoved method, of class Torre.
     */
    @Test
    public void testIsMoved(){
        
        System.out.println("isMoved");
        Torre instance = null;
        boolean expResult = false;
        boolean result = instance.isMoved();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }
    
    /**
     * Test of setMoved method, of class Torre.
     */
    @Test
    public void testSetMoved(){
        
        System.out.println("setMoved");
        Torre instance = null;
        instance.setMoved();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }
    
    /**
     * Test of moved method, of class Torre.
     */
    @Test
    public void testMoved(){
        
        System.out.println("moved");
        boolean m = false;
        Torre instance = null;
        instance.moved(m);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}