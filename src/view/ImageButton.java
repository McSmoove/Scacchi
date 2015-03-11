package view;

import java.awt.*;
import javax.swing.*;

public class ImageButton extends JButton{
    
    private Image immagine;
    private final Rectangle area = new Rectangle();
    
    public void setImage( Image immagine ){
        
        this.immagine = immagine;
        repaint();
    
    }
    
    public Image getImage(){
        
        return immagine;
    
    }
    
    @Override
    protected void paintComponent( Graphics g ){
        
        super.paintComponent( g );
        
        if( immagine != null){
            
            SwingUtilities.calculateInnerArea( this, area );
            g.drawImage( immagine, area.x, area.y, area.width, area.height, this );
        
        }
    
    }
    
}