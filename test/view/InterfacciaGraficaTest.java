package view;

import javax.swing.JButton;
import model.MatriceDeiPezzi;
import model.Pezzo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class InterfacciaGraficaTest{
    
    public InterfacciaGraficaTest(){}

    /**
     * Test of start method, of class InterfacciaGrafica.
     */
    @Test
    public void testStart() {
        
        System.out.println("start");
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.start();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getMatriceBottoni method, of class InterfacciaGrafica.
     */
    @Test
    public void testGetMatriceBottoni(){
        
        System.out.println("getMatriceBottoni");
        InterfacciaGrafica instance = new InterfacciaGrafica();
        JButton[][] expResult = null;
        JButton[][] result = instance.getMatriceBottoni();
        assertArrayEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of finePartita method, of class InterfacciaGrafica.
     */
    @Test
    public void testFinePartita(){
        
        System.out.println("finePartita");
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.finePartita();
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setMessaggio method, of class InterfacciaGrafica.
     */
    @Test
    public void testSetMessaggio(){
        
        System.out.println("setMessaggio");
        String s = "";
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.setMessaggio(s);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of aggiungiPezzoMorto method, of class InterfacciaGrafica.
     */
    @Test
    public void testAggiungiPezzoMorto() {
        
        System.out.println("aggiungiPezzoMorto");
        Pezzo p = null;
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.aggiungiPezzoMorto(p);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of aggiornaBottoni method, of class InterfacciaGrafica.
     */
    @Test
    public void testAggiornaBottoni(){
        
        System.out.println("aggiornaBottoni");
        MatriceDeiPezzi matrice = null;
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.aggiornaBottoni(matrice);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of attivaBordo method, of class InterfacciaGrafica.
     */
    @Test
    public void testAttivaBordo(){
        
        System.out.println("attivaBordo");
        int x = 0;
        int y = 0;
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.attivaBordo(x, y);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of disattivaBordo method, of class InterfacciaGrafica.
     */
    @Test
    public void testDisattivaBordo(){
        
        System.out.println("disattivaBordo");
        int x = 0;
        int y = 0;
        InterfacciaGrafica instance = new InterfacciaGrafica();
        instance.disattivaBordo(x, y);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}