package controller;

import java.awt.event.ActionEvent;
import model.Pezzo;
import model.Spazio;
//import org.junit.After;
//import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import view.InterfacciaGrafica;

/**
 *
 * @author Tyrande
 */
public class GestoreBottoniTest {
    
    public GestoreBottoniTest(){}

    /**
    @Before
    public void setUp(){}
    
    @After
    public void tearDown(){}
*/
    /**
     * Test of bloccoBottoniIniziale method, of class GestoreBottoni.
     */
    @Test
    public void testBloccoBottoniIniziale(){
        
        System.out.println("bloccoBottoniIniziale");
        GestoreBottoni instance = null;
        instance.bloccoBottoniIniziale();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of pressionePulsanteScacchiera method, of class GestoreBottoni.
     */
    @Test
    public void testPressionePulsanteScacchiera(){
        
        System.out.println("pressionePulsanteScacchiera");
        ActionEvent e = null;
        GestoreBottoni instance = null;
        instance.pressionePulsanteScacchiera(e);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getInterfacciaGrafica method, of class GestoreBottoni.
     */
    @Test
    public void testGetInterfacciaGrafica(){
        
        System.out.println("getInterfacciaGrafica");
        GestoreBottoni instance = null;
        InterfacciaGrafica expResult = null;
        InterfacciaGrafica result = instance.getInterfacciaGrafica();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getMatriceSpazi method, of class GestoreBottoni.
     */
    @Test
    public void testGetMatriceSpazi(){
        
        System.out.println("getMatriceSpazi");
        GestoreBottoni instance = null;
        Spazio[][] expResult = null;
        Spazio[][] result = instance.getMatriceSpazi();
        assertArrayEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setPedoneTrasformato method, of class GestoreBottoni.
     */
    @Test
    public void testSetPedoneTrasformato(){
        
        System.out.println("setPedoneTrasformato");
        Pezzo p = null;
        GestoreBottoni instance = null;
        instance.setPedoneTrasformato(p);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}