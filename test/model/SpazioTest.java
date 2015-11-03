package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class SpazioTest{
    
    public SpazioTest(){}

    /**
     * Test of eOccupato method, of class Spazio.
     */
    @Test
    public void testEOccupato(){
        
        System.out.println("eOccupato");
        Spazio instance = new Spazio();
        boolean expResult = false;
        boolean result = instance.eOccupato();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getOccupante method, of class Spazio.
     */
    @Test
    public void testGetOccupante(){
        
        System.out.println("getOccupante");
        Spazio instance = new Spazio();
        Pezzo expResult = null;
        Pezzo result = instance.getOccupante();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setOccupante method, of class Spazio.
     */
    @Test
    public void testSetOccupante_Pezzo(){
        
        System.out.println("setOccupante");
        Pezzo p = null;
        Spazio instance = new Spazio();
        instance.setOccupante(p);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setOccupante method, of class Spazio.
     */
    @Test
    public void testSetOccupante_3args(){
        
        System.out.println("setOccupante");
        Pezzo p = null;
        int x = 0;
        int y = 0;
        Spazio instance = new Spazio();
        instance.setOccupante(p, x, y);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setOccupato method, of class Spazio.
     */
    @Test
    public void testSetOccupato(){
        
        System.out.println("setOccupato");
        boolean b = false;
        Spazio instance = new Spazio();
        instance.setOccupato(b);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getX method, of class Spazio.
     */
    @Test
    public void testGetX(){
        
        System.out.println("getX");
        Spazio instance = new Spazio();
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getY method, of class Spazio.
     */
    @Test
    public void testGetY(){
        
        System.out.println("getY");
        Spazio instance = new Spazio();
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setX method, of class Spazio.
     */
    @Test
    public void testSetX(){
        
        System.out.println("setX");
        int x = 0;
        Spazio instance = new Spazio();
        instance.setX(x);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setY method, of class Spazio.
     */
    @Test
    public void testSetY(){
        
        System.out.println("setY");
        int y = 0;
        Spazio instance = new Spazio();
        instance.setY(y);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}