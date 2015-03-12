package view;

import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import model.*;

public class InterfacciaGrafica{
    
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
        
    private JPanel interfacciaGrafica = new JPanel( new BorderLayout() );
    private JPanel pezziBianchi = new JPanel( new GridLayout( 8, 2 ) );
    private JPanel pezziNeri = new JPanel( new GridLayout( 8, 2 ) );
    private JPanel scacchiera;
    
    private JButton[][] quadratiScacchiera = new JButton[ 8 ][ 8 ];
    
    private final Image immagine[] = new Image[ 12 ];
    private final Image extra[] = new Image[ 3 ];
    
    private final JLabel messaggioInfo = new JLabel( "Tocca Al Bianco / Nero" );
    
    private static final String colonne = "ABCDEFGH";
    
    private Border bordo = new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new LineBorder( Color.ORANGE ) );
    
    private GestoreMovimenti gm;
    private MatriceDeiPezzi matrice;
    private GestoreBottoni gestoreBottoni;
    private GestoreTurni gestoreTurni;
    
    InterfacciaGrafica(){ // Inizializza Interfaccia Grafica ( Costruttore )
    
        // Panello Pezzi Bianchi
        try {
            
            immagine[ 2 ] = ImageIO.read( getClass().getResource( "../immagini/cavalloBianco.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
        
        } catch (IOException ex){}
        
        for( int i = 0; i < 16; i++ ){
            
            pezziBianchi.add( new JLabel( new ImageIcon( immagine [ 2 ] ) ) );
        
        }
        
        try {
            
            extra[0] = ImageIO.read( getClass().getResource( "../immagini/bianco.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
            extra[1] = ImageIO.read( getClass().getResource( "../immagini/nero.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
            // extra[2] = ImageIO.read( getClass().getResource( "../immagini/scacchiera.png" ) ); // Semmai Decidiamo Di Implementare La Sccacchiera
        
        } catch (IOException ex) {}

        // Scacchiera E I Bottoni Associati
        scacchiera = new JPanel( new GridLayout( 10, 10 ) ){
            
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
        
        };
        
        // Creo I JButton Del Pannello Della Scacchiera
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
                bottone.setContentAreaFilled( false );
                bottone.setBorderPainted( true );
               
                quadratiScacchiera[ j ][ i ] = bottone;
            
            } // Fine For Colonne
        
        } // Fine For Righe

        // Disegno Le Lettere In Alto
        scacchiera.add( new JLabel() ); // Primo Spazio Vuoto Per La Riga Contenente Le Lettere In Alto
        
        for( int i = 0; i < 8; i++ ){
            
            scacchiera.add( new JLabel( colonne.substring( i, i + 1 ), SwingConstants.CENTER ) );
        
        } // Fine For
        
        scacchiera.add( new JLabel() ); // Ultimo Spazio Vuoto Per La Riga Contenente Le Lettere In Alto
        
        for( int i = 0; i < 8; i++ ){
            
            for ( int j = 0; j < 8; j++ ){
                
                switch ( j ){
                    
                    case 0: scacchiera.add( new JLabel( "" + ( 9 - ( i + 1 ) ), SwingConstants.CENTER ) );
                    default: scacchiera.add( quadratiScacchiera[ j ][ i ] );
                
                }
            
            } // Fine For Righe
            
            scacchiera.add( new JLabel( "" + ( 9 - ( i + 1 ) ), SwingConstants.CENTER ) );
            
        } // Fine For Colonne

        // Disegno Le Lettere In Basso
        scacchiera.add( new JLabel() ); // Primo Spazio Vuoto Per La Riga Contenente Le Lettere In Basso
        
        for( int i = 0; i < 8; i++ ){
            
            scacchiera.add( new JLabel( colonne.substring( i, i + 1 ), SwingConstants.CENTER ) );
        
        } // Fine For
        
        scacchiera.add( new JLabel() ); // Ultimo Spazio Vuoto Per La Riga Contenente Le Lettere In Basso

        // Panello Pezzi Neri
        try {
            
            immagine[ 2 ] = ImageIO.read( getClass().getResource( "../immagini/cavalloNero.png" ) ).getScaledInstance( 64, 64, Image.SCALE_SMOOTH );
        
        } catch (IOException ex){}
        
        for( int i = 0; i < 16; i++ ){
            
            pezziNeri.add( new JLabel( new ImageIcon( immagine[ 2 ] ) ) );
        
        }
        
        JToolBar menu = new JToolBar();
        menu.setFloatable( false );
        
        Action iniziaNuovaPartita = new AbstractAction( "Nuova Partita" ){
            
            @Override
            public void actionPerformed( ActionEvent e ){ 
                
                iniziaPartita();
            
            }
        
        };
        
        menu.add( iniziaNuovaPartita );
        menu.addSeparator();
        menu.add( messaggioInfo );
        interfacciaGrafica.add( menu, BorderLayout.PAGE_START );

        // Aggiungo I Componenti Al Pannello Main
        JPanel pannelloMain = new JPanel( new GridBagLayout() );
        GridBagConstraints posizione = new GridBagConstraints();
        
        posizione.fill = GridBagConstraints.EAST;
        pezziBianchi.setBorder( bordo );
        pannelloMain.add( pezziBianchi, posizione );
        
        posizione.fill = GridBagConstraints.CENTER;
        scacchiera.setBorder( bordo );
        pannelloMain.add( scacchiera, posizione );
        
        posizione.fill = GridBagConstraints.WEST;
        pezziNeri.setBorder( bordo );
        pannelloMain.add( pezziNeri, posizione );
        
        interfacciaGrafica.add( pannelloMain, BorderLayout.CENTER );
        interfacciaGrafica.setBorder( bordo );

    } // Fine InterfacciaGrafica

    // Qui Si Inizializzano Le Immagini Eccetera
    private void iniziaPartita(){
        
        matrice = new MatriceDeiPezzi(); // Inizializzata Con La Scacchiera Di Default
        gm = new GestoreMovimenti( matrice ); // Collegamento Interfaccia-Gestore
        gm.setInterfacciaGrafica( this ); // Collegamento Gestore-Interfaccia
        gestoreTurni=new GestoreTurni();
        gestoreBottoni=new GestoreBottoni(gm,gestoreTurni,this);
        
        // Da Modificare Il Testo In Base Al Turno
        messaggioInfo.setText( "Fai Una Mossa !!!");
        
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
        
        } catch( IOException e ){}

        // Qui Si Metteranno I Pezzi Collegandoli Alle Immagini
        
        Pezzo p; // Sposteo In Alto La Dichiarazione
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
        
        //aggiungo i listener a tutti i bottoni
        for(int i=0;i<quadratiScacchiera.length;i++){
            for(int j=0;j<quadratiScacchiera.length;j++){
                quadratiScacchiera[i][j].addActionListener(new ActionListener() {
 
                    @Override
                    public void actionPerformed(ActionEvent e){
                        gestoreBottoni.pressionePulsanteScacchiera( e );
                    }
                });      
            }
            
        }
    
    } // Fine iniziaPartita
    
    public void start() {
        
        Runnable run = new Runnable(){
            
            @Override
            public void run(){

                JFrame frame = new JFrame( "Scacchi Beta !!!" );
                frame.add( interfacciaGrafica );
                
                frame.setLocationByPlatform( true );
                frame.setMinimumSize( frame.getMinimumSize() );
                frame.pack();
                
                frame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
                frame.setVisible( true );
            
            }
        
        };
        
        SwingUtilities.invokeLater(run);
    
    } // Fine main
    
    /*
    public void actionPerformed( ActionEvent e ) {
        
        if( e.getSource() instanceof JButton ){
            
            gestoreBottoni.pressionePulsanteScacchiera( e );
        
        }
    
    }
    */
    public JButton[][] getMatriceBottoni(){
        
        return quadratiScacchiera;
    
    }

} // Fine Classe InterfacciaGrafica