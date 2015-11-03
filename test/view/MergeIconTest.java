package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tyrande
 */
public class MergeIconTest{
    
    public MergeIconTest(){}

    /**
     * Test of getIconWidth method, of class MergeIcon.
     */
    @Test
    public void testGetIconWidth(){
        
        System.out.println("getIconWidth");
        MergeIcon instance = null;
        int expResult = 0;
        int result = instance.getIconWidth();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of getIconHeight method, of class MergeIcon.
     */
    @Test
    public void testGetIconHeight(){
        
        System.out.println("getIconHeight");
        MergeIcon instance = null;
        int expResult = 0;
        int result = instance.getIconHeight();
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of paintIcon method, of class MergeIcon.
     */
    @Test
    public void testPaintIcon(){
        
        System.out.println("paintIcon");
        Component c = null;
        Graphics g = null;
        int x = 0;
        int y = 0;
        MergeIcon instance = null;
        instance.paintIcon(c, g, x, y);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of iconToBufferedImage method, of class MergeIcon.
     */
    @Test
    public void testIconToBufferedImage(){
        
        System.out.println("iconToBufferedImage");
        Icon icon = null;
        BufferedImage expResult = null;
        BufferedImage result = MergeIcon.iconToBufferedImage(icon);
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

    /**
     * Test of iconToImage method, of class MergeIcon.
     */
    @Test
    public void testIconToImage(){
        
        System.out.println("iconToImage");
        Icon icon = null;
        Image expResult = null;
        Image result = MergeIcon.iconToImage(icon);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    
    }

}