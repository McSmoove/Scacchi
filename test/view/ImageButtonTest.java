package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class ImageButtonTest{
    
    public ImageButtonTest(){}

    /**
     * Test of setImage method, of class ImageButton.
     */
    @Test
    public void testSetImage(){
        
        System.out.println("setImage");
        Image immagine = null;
        ImageButton instance = new ImageButton();
        instance.setImage(immagine);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getImage method, of class ImageButton.
     */
    @Test
    public void testGetImage(){
        
        System.out.println("getImage");
        ImageButton instance = new ImageButton();
        Image expResult = null;
        Image result = instance.getImage();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of setIcona method, of class ImageButton.
     */
    @Test
    public void testSetIcona(){
        
        System.out.println("setIcona");
        Image icona = null;
        ImageButton instance = new ImageButton();
        instance.setIcona(icona);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getIcona method, of class ImageButton.
     */
    @Test
    public void testGetIcona(){
        
        System.out.println("getIcona");
        ImageButton instance = new ImageButton();
        Image expResult = null;
        Image result = instance.getIcona();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of paintComponent method, of class ImageButton.
     */
    @Test
    public void testPaintComponent(){
        
        System.out.println("paintComponent");
        Graphics g = null;
        ImageButton instance = new ImageButton();
        instance.paintComponent(g);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of drawIcon method, of class ImageButton.
     */
    @Test
    public void testDrawIcon(){
        
        System.out.println("drawIcon");
        ImageButton instance = new ImageButton();
        Graphics2D expResult = null;
        Graphics2D result = instance.drawIcon();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}