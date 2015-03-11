package view;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class ExpandButtonTest{
    
    private final Border bordo = new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new LineBorder( Color.ORANGE ) );
    private final Image immagine[] = new Image[ 12 ];
    private final Image extra[] = new Image[ 3 ];
    private ImageButton[] ad = new ImageButton[4];
    
    public static void main(String[] args){
        
        new ExpandButtonTest();
    
    }
    
    public ExpandButtonTest(){
        
        EventQueue.invokeLater(() -> {
            try{
                
                immagine[ 0 ] = ImageIO.read( getClass().getResource( "../immagini/cavalloBianco.png" ) ).getScaledInstance( 64, 64, Image.SCALE_DEFAULT );
                immagine[ 1 ] = ImageIO.read( getClass().getResource( "../immagini/torreBianca.png" ) ).getScaledInstance( 64, 64, Image.SCALE_FAST );
                
                immagine[ 6 ] = ImageIO.read( getClass().getResource( "../immagini/cavalloNero.png" ) ).getScaledInstance( 64, 64, Image.SCALE_DEFAULT );
                immagine[ 7 ] = ImageIO.read( getClass().getResource( "../immagini/torreNera.png" ) ).getScaledInstance( 64, 64, Image.SCALE_FAST );
                
                extra[0] = ImageIO.read( getClass().getResource( "../immagini/bianco.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
                extra[1] = ImageIO.read( getClass().getResource( "../immagini/nero.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
                
            } catch( IOException e ){}
            
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            frame.setLayout(new GridLayout( 2, 2 ));
            
            for(int i=0;i<4;i++){
                
                ImageButton bott = new ImageButton();
                bott.setBorder(bordo);
                ad[i]=bott;
                frame.add( ad[i] );
                ad[i].setOpaque(true);
                ad[i].setContentAreaFilled(false);
                ad[i].setBorderPainted( true );
            
            }
            
             
            
            ad[0].setImage( extra[1] );
            ad[1].getWidth();
            //ad[0].setIcon(new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ 0 ] ) ) );
            ad[1].setIcon(new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ 6 ] ) ) );
            ad[2].setIcon(new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ 1 ] ) ) );
            ad[3].setIcon(new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ 7 ] ) ) );
            
            frame.pack();
            frame.setMinimumSize( new Dimension( 200, 200 ) );
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        });
    }
}
