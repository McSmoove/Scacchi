package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class MatriceDeiPezziTest {
    
    public MatriceDeiPezziTest(){}

    /**
     * Test of getMatrice method, of class MatriceDeiPezzi.
     */
    @Test
    public void testGetMatrice(){
        
        System.out.println("getMatrice");
        MatriceDeiPezzi instance = new MatriceDeiPezzi();
        Spazio[][] expResult = null;
        Spazio[][] result = instance.getMatrice();
        assertArrayEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }
    
    /**
     * Test of getSpazio method, of class MatriceDeiPezzi.
     */
    @Test
    public void testGetSpazio(){
        
        System.out.println("getSpazio");
        int x = 0;
        int y = 0;
        MatriceDeiPezzi instance = new MatriceDeiPezzi();
        Spazio expResult = null;
        Spazio result = instance.getSpazio(x, y);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setSpazio method, of class MatriceDeiPezzi.
     */
    @Test
    public void testSetSpazio_3args(){
        
        System.out.println("setSpazio");
        int x = 0;
        int y = 0;
        Spazio s = null;
        MatriceDeiPezzi instance = new MatriceDeiPezzi();
        instance.setSpazio(x, y, s);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }
    
    /**
     * Test of setSpazio method, of class MatriceDeiPezzi.
     */
    @Test
    public void testSetSpazio_Spazio(){
        
        System.out.println("setSpazio");
        Spazio s = null;
        MatriceDeiPezzi instance = new MatriceDeiPezzi();
        instance.setSpazio(s);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of spostaPezzo method, of class MatriceDeiPezzi.
     */
    @Test
    public void testSpostaPezzo(){
        
        System.out.println("spostaPezzo");
        Pezzo p = null;
        int x = 0;
        int y = 0;
        MatriceDeiPezzi instance = new MatriceDeiPezzi();
        instance.spostaPezzo(p, x, y);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of toString method, of class MatriceDeiPezzi.
     */
    @Test
    public void testToString(){
        
        System.out.println("toString");
        MatriceDeiPezzi instance = new MatriceDeiPezzi();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}