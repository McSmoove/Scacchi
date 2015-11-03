package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class ReTest{
    
    public ReTest(){}

    /**
     * Test of isMoved method, of class Re.
     */
    @Test
    public void testIsMoved(){
        
        System.out.println("isMoved");
        Re instance = null;
        boolean expResult = false;
        boolean result = instance.isMoved();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setMoved method, of class Re.
     */
    @Test
    public void testSetMoved(){
        
        System.out.println("setMoved");
        Re instance = null;
        instance.setMoved();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of moved method, of class Re.
     */
    @Test
    public void testMoved(){
        
        System.out.println("moved");
        boolean m = false;
        Re instance = null;
        instance.moved(m);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}