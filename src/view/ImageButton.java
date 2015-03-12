package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ImageButton extends JButton{
    
    private Image immagine;
    private Image icona;
    private final Rectangle area = new Rectangle();
    
    public ImageButton(){}
    
    public void setImage( Image immagine ){
        
        this.immagine = immagine;
        repaint();
    
    }
    
    public Image getImage(){ return immagine; }
    
    public void setIcona( Image icona ){
        
        this.icona = icona;
        repaint();
    
    }
    
    public Image getIcona(){ return icona; }
    
    @Override
    protected void paintComponent( Graphics g ){
        
        super.paintComponent( g );
        
        if( immagine != null ){
            
            SwingUtilities.calculateInnerArea( this, area );
            g.drawImage( immagine, area.x, area.y, area.width, area.height, this );
        
        }
    
    }
    
    public Graphics2D drawIcon(){
        
        int larghezza = immagine.getWidth(this);
        int altezza = immagine.getHeight(this);
        
        final BufferedImage combinedImage = new BufferedImage( larghezza, altezza, BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = combinedImage.createGraphics();
        g.drawImage( immagine, 0, 0, null );
        g.drawImage( icona, 0, 0, null );
        g.dispose();
        
        return g;
        
    }
    
}