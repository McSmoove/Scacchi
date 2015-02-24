package view;

import controller.GestoreMovimenti;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import model.MatriceDeiPezzi;
import model.Pedone;
import model.Pezzo;

public class InterfacciaGrafica{
    private final int PEDONE_BIANCA=0;
    private final int TORRE_BIANCA=1;
    private final int CAVALLO_BIANCO=2;
    private final int ALFIERE_BIANCO=3;
    private final int REGINA_BIANCA=4;
    private final int RE_BIANCO=5;
    private final int PEDONE_NERO=6;
    private final int TORRE_NERA=7;
    private final int CAVALLO_NERO=8;
    private final int ALFIERE_NERO=9;
    private final int REGINA_NERA=10;
    private final int RE_NERO=11;
    
    
    
    private final JPanel interfacciaGrafica = new JPanel(new BorderLayout(3, 3));
    private JButton[][] quadratiScacchiera = new JButton[8][8];
    private final Image immagine[] = new Image[ 12 ];
    private JPanel scacchiera;
    private final JLabel messaggioInfo = new JLabel( "Tocca Al Bianco / Nero" );
    private static final String colonne = "ABCDEFGH";
    private GestoreMovimenti gm;
    private MatriceDeiPezzi matrice;
    

    InterfacciaGrafica(){
        matrice=new MatriceDeiPezzi();
        gm=new GestoreMovimenti(matrice);
        // Inizializza Interfaccia Grafica (Costruttore)
        interfacciaGrafica.setBorder( new EmptyBorder( 5, 5, 5, 5) );
        JToolBar menu = new JToolBar();
        menu.setFloatable( false );
        interfacciaGrafica.add( menu, BorderLayout.PAGE_START );
        
        Action iniziaNuovaPartita = new AbstractAction( "Nuova Partita" ){

            @Override
            public void actionPerformed( ActionEvent e ){ 
                iniziaPartita();
            }
        };
        
        menu.add( iniziaNuovaPartita );
        menu.addSeparator();
        menu.add( messaggioInfo );
        
        scacchiera = new JPanel( new GridLayout( 0, 9 ) ){
            
            @Override
            public final Dimension getPreferredSize() {
                
                Dimension dimensione = super.getPreferredSize();
                Dimension dimensioneCambiata = null;
                Component c = getParent();
                
                if ( c == null ){
                    
                    dimensioneCambiata = new Dimension( ( int )dimensione.getWidth(), ( int )dimensione.getHeight() );
                
                } else if ( c != null && c.getWidth() > dimensione.getWidth() && c.getHeight() > dimensione.getHeight() ){
                    
                    dimensioneCambiata = c.getSize();
                
                } else {
                    
                    dimensioneCambiata = dimensione;
                
                }
                
                int w = ( int )dimensioneCambiata.getWidth();
                int h = ( int )dimensioneCambiata.getHeight();
                
                // Prendo La Piu Piccola Tra Altezza E Larghezza Per Ridimensionare
                int scelta = ( w > h ? h : w);
                return new Dimension( scelta, scelta );
            
            } // Fine getPrefferedSize
        
        };
        
        scacchiera.setBorder( new CompoundBorder( new EmptyBorder( 10, 10, 10, 10), new LineBorder( Color.ORANGE ) ) );

        JPanel pannelloScacchiera = new JPanel( new GridBagLayout() );
        pannelloScacchiera.add( scacchiera );
        interfacciaGrafica.add( pannelloScacchiera );

        // Creo I JButton Del Pannello Della Scacchiera
        Insets margineBottoni = new Insets(0, 0, 0, 0); // Per Avere Margini = 0
        
        for (int i = 0; i < quadratiScacchiera.length; i++ ){
            
            for ( int j = 0; j < quadratiScacchiera[ i ].length; j++ ){
                
                JButton bottone = new JButton();
                bottone.setMargin( margineBottoni );
                
                // I Pezzi Sono Da 64x64 Pixel E Trasparenti ( Posso Modificare )           
                ImageIcon immaginePezzo = new ImageIcon( new BufferedImage( 64, 64, BufferedImage.TYPE_INT_ARGB ) );
                bottone.setIcon(immaginePezzo);
                
                
                // Coloro Lo Sfondo Dei Quadrati Se Sono Pari O Dispari
                if ( ( j % 2 == 1 && i % 2 == 1 ) || ( j % 2 == 0 && i % 2 == 0 ) ){
                    
                    bottone.setBackground( Color.WHITE );
                
                } else {
                    
                    bottone.setBackground( Color.BLACK );
                
                } // Fine If Else Con I Quadrati Colorati
                
                quadratiScacchiera[ j ][ i ] = bottone;
            
            } // Fine For Colonne
        
        } // Fine For Righe

        
        // Disegno Le Coordinate
        scacchiera.add( new JLabel() ); // Perche Devo Metterla ???

        // Disegno Le Lettere In Alto
        for( int i = 0; i < 8; i++ ){
            
            scacchiera.add( new JLabel( colonne.substring( i, i + 1 ), SwingConstants.CENTER ) );
        
        } // Fine For
        
        for( int i = 0; i < 8; i++ ){
            
            for ( int j = 0; j < 8; j++ ){
                
                switch ( j ){
                    
                    case 0: scacchiera.add( new JLabel( "" + ( 9 - ( i + 1 ) ), SwingConstants.CENTER ) );
                    default: scacchiera.add( quadratiScacchiera[ j ][ i ] );
                
                } // Fine Switch
            
            } // Fine For Colonne
        
        } // Fine For Righe
    
    } // Fine InterfacciaGrafica

    // Qui Si Inizializzano Le Immagini Eccetera...
    private final void iniziaPartita() {
        
        //da modificare il testo in base al turno
        messaggioInfo.setText( "Fai Una Mossa !!!");
        
        try{
            
            immagine[ 0 ] = ImageIO.read( getClass().getResource( "../immagini/pedoneBianco.png" ) );
            // imm[ 1 ] = ImageIO.read( getClass().getResource( "img/Torre_Bianca.gif" ) );
            // imm[ 2 ] = ImageIO.read( getClass().getResource( "img/Cavallo_Bianco.gif" ) );
            // imm[ 3 ] = ImageIO.read( getClass().getResource( "img/Alfiere_Bianco.gif" ) );
            // imm[ 4 ] = ImageIO.read( getClass().getResource( "img/Regina_Bianca.gif" ) );
            // imm[ 5 ] = ImageIO.read( getClass().getResource( "img/Re_Bianco.gif" ) );
            
            // Recupero le immagini pezzi neri
             immagine[ 6 ] = ImageIO.read( getClass().getResource( "../immagini/PedoneNero.png" ) );
            // imm[ 7 ] = ImageIO.read( getClass().getResource( "img/Torre_Nera.gif" ) );
            // imm[ 8 ] = ImageIO.read( getClass().getResource( "img/Cavallo_Nero.gif" ) );
            // imm[ 9 ] = ImageIO.read( getClass().getResource( "img/Alfiere_Nero.gif" ) );
            // imm[ 10 ] = ImageIO.read( getClass().getResource( "img/Regina_Nera.gif" ) );
            // imm[ 11 ] = ImageIO.read( getClass().getResource( "img/Re_Nero.gif" ) );
        
        } catch( IOException e ){}
        
        
        //qui si metteranno i pezzi collegandoli alle immagini
        
        Pezzo p;//sposterò in alto la dichiarazione
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                p=matrice.getMatrice()[i][j].getPezzo();
                //a mettere tutti i casidei vari pezzi
                
            }
        }
        
        
    
    } // Fine iniziaPartita
    
    public void start() {
        
        Runnable run = new Runnable() {
            
            @Override
            public void run() {

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

} // Fine Classe InterfacciaGrafica