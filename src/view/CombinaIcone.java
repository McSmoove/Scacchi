package view;

import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

public class CombinaIcone implements Icon{
    
    private final Icon background;
    private final Icon pezzo;
    
    public CombinaIcone( Icon background, Icon pezzo ){
        
        this.background = background;
        this.pezzo = pezzo;
    
    }
    
    @Override
    public int getIconHeight() {
        
        return Math.max( background.getIconHeight(), pezzo.getIconHeight() );
    
    }
    
    @Override
    public int getIconWidth() {
        
        return Math.max( background.getIconWidth(), pezzo.getIconWidth() );
    
    }
    
    @Override
    public void paintIcon( Component c, Graphics g, int x, int y ){
        
        background.paintIcon( c, g, x, y );
        pezzo.paintIcon( c, g, x, y );
    
    }

}