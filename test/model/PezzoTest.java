package model;

import org.junit.Test;
import static org.junit.Assert.*;
import view.InterfacciaGrafica;

/**
 *
 * @author Tyrande
 */
public class PezzoTest{
    
    public PezzoTest(){}
    
    public class PezzoImpl extends Pezzo{
        
        public PezzoImpl(){
            
            super(0, 0, null);
        
        }
    
    }
    
    /**
     * Test of getX method, of class Pezzo.
     */
    @Test
    public void testGetX(){
        
        System.out.println("getX");
        Pezzo instance = null;
        int expResult = 0;
        int result = instance.getX();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getY method, of class Pezzo.
     */
    @Test
    public void testGetY(){
        
        System.out.println("getY");
        Pezzo instance = null;
        int expResult = 0;
        int result = instance.getY();
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getColore method, of class Pezzo.
     */
    @Test
    public void testGetColore(){
        
        System.out.println("getColore");
        Pezzo instance = null;
        Colore expResult = null;
        Colore result = instance.getColore();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of distruggi method, of class Pezzo.
     */
    @Test
    public void testDistruggi(){
        
        System.out.println("distruggi");
        InterfacciaGrafica interfacciaGrafica = null;
        Pezzo instance = null;
        instance.distruggi(interfacciaGrafica);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of spostabileIn method, of class Pezzo.
     */
    @Test
    public void testSpostabileIn(){
        
        System.out.println("spostabileIn");
        int x = 0;
        int y = 0;
        Spazio[][] matrice = null;
        Pezzo instance = null;
        boolean expResult = false;
        boolean result = instance.spostabileIn(x, y, matrice);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setX method, of class Pezzo.
     */
    @Test
    public void testSetX(){
        
        System.out.println("setX");
        int x = 0;
        Pezzo instance = null;
        instance.setX(x);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setY method, of class Pezzo.
     */
    @Test
    public void testSetY(){
        
        System.out.println("setY");
        int y = 0;
        Pezzo instance = null;
        instance.setY(y);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setLock method, of class Pezzo.
     */
    @Test
    public void testSetLock(){
        
        System.out.println("setLock");
        boolean l = false;
        Pezzo instance = null;
        instance.setLock(l);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getLock method, of class Pezzo.
     */
    @Test
    public void testGetLock(){
        
        System.out.println("getLock");
        Pezzo instance = null;
        boolean expResult = false;
        boolean result = instance.getLock();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}