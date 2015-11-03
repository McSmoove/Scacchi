package controller;

import java.util.LinkedList;
import model.Colore;
import model.MatriceDeiPezzi;
import model.Pedone;
import model.Pezzo;
import model.Re;
import model.Spazio;
import org.junit.Test;
import static org.junit.Assert.*;
import view.InterfacciaGrafica;

/**
 *
 * @author Tyrande
 */
public class GestoreMovimentiTest {
    
    public GestoreMovimentiTest(){}
    
    /**
     * Test of aggiornaMatriceDeiPezzi method, of class GestoreMovimenti.
     */
    @Test
    public void testAggiornaMatriceDeiPezzi(){
        
        System.out.println("aggiornaMatriceDeiPezzi");
        MatriceDeiPezzi nuova = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        instance.aggiornaMatriceDeiPezzi(nuova);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getMatrice method, of class GestoreMovimenti.
     */
    @Test
    public void testGetMatrice(){
        
        System.out.println("getMatrice");
        GestoreMovimenti instance = new GestoreMovimenti();
        MatriceDeiPezzi expResult = null;
        MatriceDeiPezzi result = instance.getMatrice();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getPossibiliMovimenti method, of class GestoreMovimenti.
     */
    @Test
    public void testGetPossibiliMovimenti(){
        
        System.out.println("getPossibiliMovimenti");
        Pezzo p = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        int[][] expResult = null;
        int[][] result = instance.getPossibiliMovimenti(p);
        assertArrayEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getReNero method, of class GestoreMovimenti.
     */
    @Test
    public void testGetReNero(){
        
        System.out.println("getReNero");
        GestoreMovimenti instance = new GestoreMovimenti();
        Re expResult = null;
        Re result = instance.getReNero();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getReBianco method, of class GestoreMovimenti.
     */
    @Test
    public void testGetReBianco(){
        
        System.out.println("getReBianco");
        GestoreMovimenti instance = new GestoreMovimenti();
        Re expResult = null;
        Re result = instance.getReBianco();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of movimentiPedone method, of class GestoreMovimenti.
     */
    @Test
    public void testMovimentiPedone(){
        System.out.println("movimentiPedone");
        Pedone p = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        int[][] expResult = null;
        int[][] result = instance.movimentiPedone(p);
        assertArrayEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of controlloScacco method, of class GestoreMovimenti.
     */
    @Test
    public void testControlloScacco_4args(){
        
        System.out.println("controlloScacco");
        int x = 0;
        int y = 0;
        Colore colore = null;
        Spazio[][] matrix = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        int expResult = 0;
        int result = instance.controlloScacco(x, y, colore, matrix);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of controlloScacco method, of class GestoreMovimenti.
     */
    @Test
    public void testControlloScacco_Re(){
        
        System.out.println("controlloScacco");
        Re r = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        int expResult = 0;
        int result = instance.controlloScacco(r);
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getPezziAttaccantiIlRe method, of class GestoreMovimenti.
     */
    @Test
    public void testGetPezziAttaccantiIlRe(){
        
        System.out.println("getPezziAttaccantiIlRe");
        Re r = null;
        Spazio[][] matrix = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        LinkedList<Pezzo> expResult = null;
        LinkedList<Pezzo> result = instance.getPezziAttaccantiIlRe(r, matrix);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of spostabileIn method, of class GestoreMovimenti.
     */
    @Test
    public void testSpostabileIn(){
        
        System.out.println("spostabileIn");
        Pezzo p = null;
        int x = 0;
        int y = 0;
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.spostabileIn(p, x, y);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getPezziSpostabiliQui method, of class GestoreMovimenti.
     */
    @Test
    public void testGetPezziSpostabiliQui() {
        
        System.out.println("getPezziSpostabiliQui");
        Spazio[][] mat = null;
        Spazio s = null;
        Colore c = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        LinkedList<Pezzo> expResult = null;
        LinkedList<Pezzo> result = instance.getPezziSpostabiliQui(mat, s, c);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getMatricePezziChePrevengonoScacco method, of class GestoreMovimenti.
     */
    @Test
    public void testGetMatricePezziChePrevengonoScacco(){
        
        System.out.println("getMatricePezziChePrevengonoScacco");
        int xRe = 0;
        int yRe = 0;
        MatriceDeiPezzi matrice = null;
        Colore turno = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        int[][] expResult = null;
        int[][] result = instance.getMatricePezziChePrevengonoScacco(xRe, yRe, matrice, turno);
        assertArrayEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getTurno method, of class GestoreMovimenti.
     */
    @Test
    public void testGetTurno(){
        
        System.out.println("getTurno");
        GestoreMovimenti instance = new GestoreMovimenti();
        int expResult = 0;
        int result = instance.getTurno();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setInterfacciaGrafica method, of class GestoreMovimenti.
     */
    @Test
    public void testSetInterfacciaGrafica(){
        
        System.out.println("setInterfacciaGrafica");
        InterfacciaGrafica i = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        instance.setInterfacciaGrafica(i);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setMatrice method, of class GestoreMovimenti.
     */
    @Test
    public void testSetMatrice(){
        
        System.out.println("setMatrice");
        MatriceDeiPezzi m = null;
        GestoreMovimenti instance = new GestoreMovimenti();
        instance.setMatrice(m);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of scaccoMatto method, of class GestoreMovimenti.
     */
    @Test
    public void testScaccoMatto(){
        
        System.out.println("scaccoMatto");
        GestoreMovimenti instance = new GestoreMovimenti();
        boolean expResult = false;
        boolean result = instance.scaccoMatto();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getMatriceSpazi method, of class GestoreMovimenti.
     */
    @Test
    public void testGetMatriceSpazi(){
        
        System.out.println("getMatriceSpazi");
        GestoreMovimenti instance = new GestoreMovimenti();
        Spazio[][] expResult = null;
        Spazio[][] result = instance.getMatriceSpazi();
        assertArrayEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}