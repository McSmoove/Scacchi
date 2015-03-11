package view;

import controller.GestoreBottoni;
import controller.GestoreMovimenti;
import controller.GestoreTurni;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.*;

public class Test{
    
    private final int PEDONE_BIANCO = 0;
    private final int TORRE_BIANCA = 1;
    private final int CAVALLO_BIANCO = 2;
    private final int ALFIERE_BIANCO = 3;
    private final int REGINA_BIANCA = 4;
    private final int RE_BIANCO = 5;
    private final int PEDONE_NERO = 6;
    private final int TORRE_NERA = 7;
    private final int CAVALLO_NERO = 8;
    private final int ALFIERE_NERO = 9;
    private final int REGINA_NERA = 10;
    private final int RE_NERO = 11;
    
    private final Image immagine[] = new Image[ 12 ];
    private final Image extra[] = new Image[ 3 ];
    private JFrame scacchiera = new JFrame();
    private JButton[][] quadratiScacchiera = new JButton[ 8 ][ 8 ];
    private static final String colonne = "ABCDEFGH";

    private GestoreMovimenti gm;
    private MatriceDeiPezzi matrice;
    private GestoreBottoni gestoreBottoni;
    private GestoreTurni gestoreTurni;
    
    Test(){
        
        try{
            
            immagine[ 0 ] = ImageIO.read( getClass().getResource( "../immagini/pedoneBianco.png" ) ).getScaledInstance( 64, 64, Image.SCALE_DEFAULT );
            immagine[ 1 ] = ImageIO.read( getClass().getResource( "../immagini/torreBianca.png" ) ).getScaledInstance( 64, 64, Image.SCALE_FAST );
            immagine[ 2 ] = ImageIO.read( getClass().getResource( "../immagini/cavalloBianco.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
            immagine[ 3 ] = ImageIO.read( getClass().getResource( "../immagini/alfiereBianco.png" ) ).getScaledInstance( 64, 64, Image.SCALE_DEFAULT );
            immagine[ 4 ] = ImageIO.read( getClass().getResource( "../immagini/reginaBianca.png" ) ).getScaledInstance( 64, 64, Image.SCALE_FAST );
            immagine[ 5 ] = ImageIO.read( getClass().getResource( "../immagini/reBianco.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );

            // Recupero le immagini pezzi neri
            immagine[ 6 ] = ImageIO.read( getClass().getResource( "../immagini/pedoneNero.png" ) ).getScaledInstance( 64, 64, Image.SCALE_DEFAULT );
            immagine[ 7 ] = ImageIO.read( getClass().getResource( "../immagini/torreNera.png" ) ).getScaledInstance( 64, 64, Image.SCALE_FAST );
            immagine[ 8 ] = ImageIO.read( getClass().getResource( "../immagini/cavalloNero.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
            immagine[ 9 ] = ImageIO.read( getClass().getResource( "../immagini/alfiereNero.png" ) ).getScaledInstance( 64, 64, Image.SCALE_DEFAULT );
            immagine[ 10 ] = ImageIO.read( getClass().getResource( "../immagini/reginaNera.png" ) ).getScaledInstance( 64, 64, Image.SCALE_FAST );
            immagine[ 11 ] = ImageIO.read( getClass().getResource( "../immagini/reNero.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
            extra[0] = ImageIO.read( getClass().getResource( "../immagini/bianco.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
            extra[1] = ImageIO.read( getClass().getResource( "../immagini/nero.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
        
        } catch( IOException e ){}
       
            scacchiera.setLayout( new GridLayout(8, 8) );
            /**
            @Override
            public final Dimension getPreferredSize(){
               
                Dimension dimensione = super.getPreferredSize();
                Dimension dimensioneCambiata;
                Component c = getParent();
                
                if ( c == null ){
                    
                    dimensioneCambiata = new Dimension( ( int )dimensione.getWidth(), ( int )dimensione.getHeight() );
                
                } else if ( c.getWidth() > dimensione.getWidth() && c.getHeight() > dimensione.getHeight() ){
                    
                    dimensioneCambiata = c.getSize();
                
                } else {
                    
                    dimensioneCambiata = dimensione;
                
                }
                
                int w = ( int )dimensioneCambiata.getWidth();
                int h = ( int )dimensioneCambiata.getHeight();
                
                // Prendo La Piu Piccola Tra Altezza E Larghezza Per Ridimensionare
                int scelta = ( w > h ? h : w );
                
                return new Dimension( scelta, scelta );
            
            } // Fine getPrefferedSize
       
            // Semmai Decidiamo Di Implementare La Scacchiera Come Immagine Statica
            /**
            @Override
            protected void paintComponent(Graphics g) {
                
                super.paintComponent(g);
                
                int h = extra[2].getHeight(null);
                int w = extra[2].getWidth(null);

                // Immagine Scalata In Orizzontale
                if ( w > this.getWidth() ){
                    
                    extra[2] = extra[2].getScaledInstance( getWidth(), -1, Image.SCALE_DEFAULT );
                    h = extra[2].getHeight(null);
                
                }

                // Scalo In Verticonale
                if ( h > this.getHeight() ){
                    
                    extra[2] = extra[2].getScaledInstance( -1, getHeight(), Image.SCALE_DEFAULT );
                
                }

                // Centro Le Immagini
                int x = (getWidth() - extra[2].getWidth(null)) / 2;
                int y = (getHeight() - extra[2].getHeight(null)) / 2;
                
                g.drawImage( extra[2], x, y, null ); // Disegno La Immagine
            
            }
            */
        
        
        //};
        
        Insets margineBottoni = new Insets( 0, 0, 0, 0 ); // Per Avere Margini = 0
        
        for (int i = 0; i < quadratiScacchiera.length; i++ ){
            
            for ( int j = 0; j < quadratiScacchiera[ i ].length; j++ ){
                
                JButton bottone = new JButton();
                bottone.setMargin( margineBottoni );
                
                // I Pezzi Sono Da 64x64 Pixel E Trasparenti ( Posso Modificare )
                ImageIcon immaginePezzo = new ImageIcon( new BufferedImage( 64, 64, BufferedImage.TYPE_INT_ARGB ) );
                bottone.setIcon( immaginePezzo );
                
                // Coloro Lo Sfondo Dei Quadrati Se Sono Pari O Dispari
                if ( ( j % 2 == 1 && i % 2 == 1 ) || ( j % 2 == 0 && i % 2 == 0 ) ){
                    
                    bottone.setBackground( Color.WHITE );
                    bottone.setIcon( new ImageIcon( extra[0] ) );
                
                } else {

                   bottone.setBackground( Color.BLACK );
                   bottone.setIcon( new ImageIcon( extra[1] ) );
                
                } // Fine If Else Con I Quadrati Colorati

                bottone.setOpaque(true);
                bottone.setContentAreaFilled(false);
                bottone.setBorderPainted( true );
               
                quadratiScacchiera[ j ][ i ] = bottone;
            
            } // Fine For Colonne
        
        } // Fine For Righe
            

        for( int i = 0; i < 8; i++ ){
            
            for ( int j = 0; j < 8; j++ ){
                
                switch ( j ){
                    
                    default: scacchiera.add( quadratiScacchiera[ j ][ i ] );
                
                }
            
            } // Fine For Righe
            
        } // Fine For Colonne

        matrice = new MatriceDeiPezzi(); // Inizializzata Con La Scacchiera Di Default
        gm = new GestoreMovimenti( matrice ); // Collegamento Interfaccia-Gestore
        gestoreTurni=new GestoreTurni();
        
        
        Pezzo p;
        
        for( int i = 0; i < 8; i++ ){
            for( int j = 0; j < 8; j++ ){
                if( matrice.getMatrice()[ i ][ j ].eOccupato() ){
                    p  = matrice.getMatrice()[ i ][ j ].getOccupante();
                    // Pensavo Di Utilizzare Uno Switch
                    if( p instanceof Pedone ){       
                        if( p.getColore() instanceof Bianco ){
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ PEDONE_BIANCO ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ PEDONE_BIANCO ] ) ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ PEDONE_NERO ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ PEDONE_NERO ] ) ) );
                            }
                        }
                    }
                    
                    if( p instanceof Alfiere ){ 
                        if( p.getColore() instanceof Bianco ){
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ ALFIERE_BIANCO ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ ALFIERE_BIANCO ] ) ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ ALFIERE_NERO ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ ALFIERE_NERO ] ) ) );
                            }
                        }
                    }
                    
                    if( p instanceof Torre ){
                        if( p.getColore() instanceof Bianco ){
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ TORRE_BIANCA ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ TORRE_BIANCA ] ) ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ TORRE_NERA ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ TORRE_NERA ] ) ) );
                            }
                        }
                    }
                    
                    if( p instanceof Cavallo ){                 
                        if( p.getColore() instanceof Bianco ){
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ CAVALLO_BIANCO ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ CAVALLO_BIANCO ] ) ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ CAVALLO_NERO ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ CAVALLO_NERO ] ) ) );
                            }
                        }
                    }
                    
                    if( p instanceof Regina ){
                        if( p.getColore() instanceof Bianco ){
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ REGINA_BIANCA ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ REGINA_BIANCA ] ) ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ REGINA_NERA ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ REGINA_NERA ] ) ) );
                            }
                        }
                    }
                    
                    if( p instanceof Re ){
                        if( p.getColore() instanceof Bianco ){ 
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ RE_BIANCO ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ RE_BIANCO ] ) ) );
                            }
                        } else {
                            if( quadratiScacchiera[ i ][ j ].getBackground() == Color.WHITE ){
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[0] ), new ImageIcon( immagine[ RE_NERO ] ) ) );
                            } else {
                                quadratiScacchiera[ i ][ j ].setIcon( new CombinaIcone( new ImageIcon( extra[1] ), new ImageIcon( immagine[ RE_NERO ] ) ) );
                            }
                        }
                    }
                }
            }
        }
        
        scacchiera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scacchiera.setMinimumSize( new Dimension( 500, 500 ) );
        scacchiera.setLocationByPlatform( true );
        scacchiera.pack();
        scacchiera.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        scacchiera.setVisible( true );
    
    }
 
    public static void main(String[] args) {
         Runnable run = () -> {
             new Test();
         };
        
        SwingUtilities.invokeLater(run);
    }
}