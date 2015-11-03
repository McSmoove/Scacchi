package controller;

import model.Colore;
import model.Spazio;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class GestoreTurniTest{
    
    public GestoreTurniTest(){}
    
    /**
     * Test of setGestoreBottoni method, of class GestoreTurni.
     */
    @Test
    public void testSetGestoreBottoni(){
        
        System.out.println("setGestoreBottoni");
        GestoreBottoni gb = null;
        GestoreTurni instance = new GestoreTurni();
        instance.setGestoreBottoni(gb);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getTurno method, of class GestoreTurni.
     */
    @Test
    public void testGetTurno(){
        
        System.out.println("getTurno");
        GestoreTurni instance = new GestoreTurni();
        Colore expResult = null;
        Colore result = instance.getTurno();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setTurno method, of class GestoreTurni.
     */
    @Test
    public void testSetTurno_Colore(){
        
        System.out.println("setTurno");
        Colore t = null;
        GestoreTurni instance = new GestoreTurni();
        instance.setTurno(t);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setTurno method, of class GestoreTurni.
     */
    @Test
    public void testSetTurno_int(){
        
        System.out.println("setTurno");
        int t = 0;
        GestoreTurni instance = new GestoreTurni();
        instance.setTurno(t);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of passaTurno method, of class GestoreTurni.
     */
    @Test
    public void testPassaTurno(){
        
        System.out.println("passaTurno");
        GestoreTurni instance = new GestoreTurni();
        instance.passaTurno();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of attiva method, of class GestoreTurni.
     */
    @Test
    public void testAttiva(){
        
        System.out.println("attiva");
        GestoreTurni instance = new GestoreTurni();
        instance.attiva();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of disattiva method, of class GestoreTurni.
     */
    @Test
    public void testDisattiva(){
        
        System.out.println("disattiva");
        GestoreTurni instance = new GestoreTurni();
        instance.disattiva();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getSpazioAttivato method, of class GestoreTurni.
     */
    @Test
    public void testGetSpazioAttivato(){
        
        System.out.println("getSpazioAttivato");
        GestoreTurni instance = new GestoreTurni();
        Spazio expResult = null;
        Spazio result = instance.getSpazioAttivato();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }
    
    /**
     * Test of setSpazioAttivato method, of class GestoreTurni.
     */
    @Test
    public void testSetSpazioAttivato(){
        
        System.out.println("setSpazioAttivato");
        Spazio s = null;
        GestoreTurni instance = new GestoreTurni();
        instance.setSpazioAttivato(s);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of isAttivato method, of class GestoreTurni.
     */
    @Test
    public void testIsAttivato(){
        
        System.out.println("isAttivato");
        GestoreTurni instance = new GestoreTurni();
        boolean expResult = false;
        boolean result = instance.isAttivato();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}