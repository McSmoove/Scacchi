package controller;

import static java.lang.Math.abs;
import java.util.Iterator;
import java.util.LinkedList;
import model.*;
import view.InterfacciaGrafica;

/**
 * Setta i movimenti possibili dei pezzi sulla scacchiera
 * @author Viktor
 */

public class GestoreMovimenti{
    
    private int turno;
    private static final int BIANCO = 1;
    private static final int NERO = -1;
    private MatriceDeiPezzi matrice;
    private Spazio[][] m;
    private final int MAXLENGTH = 7;
    private InterfacciaGrafica ig;
    private Re reBianco;
    private Re reNero;
    
    public GestoreMovimenti(){
        
        // Throw New Exception ( Modificare Il Costruttore Senza Parametri )
        // Creare Una Matrice Con Le Posizioni Di Default
        // Semplificare Le Classi Corrispondenti Ai Colori
        
        turno = BIANCO;
        m = new Spazio[ 8 ][ 8 ];
        m[ 0 ][ 0 ].setOccupante( new Torre( 0, 0, NERO ) );
        m[ 7 ][ 0 ].setOccupante( new Torre( 7, 0, NERO ) );
        m[ 1 ][ 0 ].setOccupante( new Cavallo( 1, 0, NERO ) );
        m[ 6 ][ 0 ].setOccupante( new Cavallo( 6, 0, NERO ) );
        m[ 2 ][ 0 ].setOccupante( new Alfiere( 2, 0, NERO ) );
        m[ 5 ][ 0 ].setOccupante( new Alfiere( 5, 0, NERO ) );
        m[ 3 ][ 0 ].setOccupante( new Regina( 3, 0, NERO ) );
        m[ 4 ][ 0 ].setOccupante( new Re( 4, 0, NERO ) );
        
        for( int i = 0; i < 8; i++ ){
            
            m[ i ][ 1 ].setOccupante( new Pedone( i, 1, NERO ) );
        
        } // Fine Inizializzazione Pezzi Neri
        
        m[ 0 ][ 7 ].setOccupante( new Torre( 0, 0, BIANCO ) );
        m[ 7 ][ 7 ].setOccupante( new Torre( 7, 0, BIANCO ) );
        m[ 1 ][ 7 ].setOccupante( new Cavallo( 1, 0, BIANCO ) );
        m[ 6 ][ 7 ].setOccupante( new Cavallo( 6, 0, BIANCO ) );
        m[ 2 ][ 7 ].setOccupante( new Alfiere( 2, 0, BIANCO ) );
        m[ 5 ][ 7 ].setOccupante( new Alfiere( 5, 0, BIANCO ) );
        m[ 3 ][ 7 ].setOccupante( new Regina( 3, 0, BIANCO ) );
        m[ 4 ][ 7 ].setOccupante( new Re( 4, 0, BIANCO ) );
        
        for( int i = 0; i < 8; i++){
            
            m[ i ][ 6 ].setOccupante( new Pedone( i, 1, BIANCO ) );
        
        } // Fine Inizializzazione Pezzi Bianchi
        
        matrice = new MatriceDeiPezzi( m ); // Collegamento Con La Matrice Che Salva Le Posizioni Dei Pezzi
    
    }
    
    public GestoreMovimenti( MatriceDeiPezzi matrice ){
        
        this.matrice = matrice;
        m = matrice.getMatrice();
        // Da Implementare Gli Altri Collegamenti MCV
    
    }
    
    public GestoreMovimenti( MatriceDeiPezzi matrice, int turno ){
        
        this.turno = turno;
        this.matrice = matrice;
        m = matrice.getMatrice();
        // Da Implementare Gli Altri Collegamenti MCV
    
    }
    
    /**
     * Riscrive la matrice dei pezzi secondo la nuova configurazione
     * @param nuova 
     */
    public void aggiornaMatriceDeiPezzi( MatriceDeiPezzi nuova ){ // Chiamata Fatta Dopo Ogni Mossa Effettuata
        
        matrice = nuova;
        m = matrice.getMatrice();
    
    }
    
    public MatriceDeiPezzi getMatrice(){
        
        return matrice;
    
    }
    
    // Ritorna La Matrice Con Le Posizioni Mosse Dove Un Pezzo Puo Spostarsi
    /**
     * Ritorna una matrice con i possibili movimenti del pezzo passato come 
     * parametro. Una volta passato il parametro ricerca il metodo che 
     * determina il movimeto di tale pezzo e lo usa per scrivere la matrice
     * da ritornare come risultato.
     * @param p
     * @return 
     */
    public int[][] getPossibiliMovimenti( Pezzo p ){ 
        
        if( p instanceof Pedone ){
            
            return movimentiPedone( ( Pedone ) p );
        
        } else if( p instanceof Torre ){
            
            return movimentiTorre( ( Torre ) p );
        
        } else if( p instanceof Alfiere ){
            
            return movimentiAlfiere( ( Alfiere ) p );
        
        } else if( p instanceof Cavallo ){
            
            return movimentiCavallo( ( Cavallo ) p );
        
        } else if ( p instanceof Regina ){
            
            return movimentiRegina( ( Regina ) p );
        
        } else if( p instanceof Re ){
            
            return movimentiRe( ( Re ) p );
        
        } else {
            
            return null; // Da Implementare Il Resto ( Altre Sottoclassi Di Pezzo )
            // Caso Impossibile ( Da Togliere Alla Fine Della Implementazione )
        
        }
    
    }
    
    public Re getReNero(){
        
        return getRe(new Nero());
    
    }
    
    public Re getReBianco(){
        
        return getRe(new Bianco());
    
    }
    
    /**
     * Cerca il re sulla scacchiera e lo ritorna (controlla casella per casella)
     * @param c
     * @return 
     */
    private Re getRe(Colore c){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(m[i][j].getOccupante() instanceof Re)
                    if(m[i][j].getOccupante().getColore().equals(c))
                        return (Re)m[i][j].getOccupante();
            }
        }
        return null;
    }

    // Metodo Per Controllare Il Pedone
    // Manca Il Controllo Di Quando Supero Una Pedina Con Un'Altra
    // Mancan Il Controllo Che Impedisce La Mossa Nel Caso Di Scacco
    // Manca Il Controllo Della Ultima Riga -> Trasformazione In Un Altro Pezzo Solo Dopo Il Movimento
    // Non Controllo L'Ultima Riga Perche Il Pezzo Si Trasforma
    // Aggiungere Il Controllo Se Il Pedone Non Si E Mai Mosso Puo Moversi Di 2 Quadratini
    public int[][] movimentiPedone( Pedone p ){
        
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        
        x = p.getX();
        y = p.getY();
        
        if( p.getColore() instanceof Bianco ){
            
            // Immediatamente sopra
            if( !m[ x ][ y - 1 ].eOccupato() ){
                System.err.println("DEBUG: spostamento in avanti con pedone bianco ok");
                scacchiera[ x ][ y - 1 ] = 1;
            }
            
            //2 caselle più in alto
            if( !m[ x ][ y - 2 ].eOccupato() && !p.isMoved()){
                System.err.println("DEBUG: spostamento in avanti con pedone bianco ok");
                scacchiera[ x ][ y - 2 ] = 1;
            }
            
            // In Alto A Sinistra
            if( ( x - 1 ) >= 0 ){ // Controllo Posizione Valida ( Probabilmente Ridondante )   
                if( m[ x - 1 ][ y - 1 ].eOccupato() && m[ x - 1][ y - 1 ].getOccupante().getColore() instanceof Nero ){   
                    scacchiera[ x - 1 ][ y - 1 ] = 1;
                }
            }

            // In Alto A Destra
            if( ( x + 1 ) <= MAXLENGTH ){ // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x + 1 ][ y - 1 ].eOccupato() && m[ x + 1 ][ y - 1 ].getOccupante().getColore() instanceof Nero ){
                    
                    scacchiera[ x + 1 ][ y - 1 ] = 1;
                
                }
            
            }
        
        } else { // Fine IstanceOf Bianco, Inizio Caso IstanceOf Nero
            
            if( !m[ x ][ y + 1 ].eOccupato()){
                System.err.println("DEBUG: spostamento in avanti con pedone nero ok");
                scacchiera[ x ][ y + 1 ] = 1;
            }
            //2 caselle in basso
            if( !m[ x ][ y + 2 ].eOccupato() && !p.isMoved() ){
                System.err.println("DEBUG: spostamento in avanti con pedone bianco ok");
                scacchiera[ x ][ y + 2 ] = 1;
            }
            
            if( ( x - 1 ) >= 0 ) // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x - 1 ][ y + 1 ].eOccupato() && m[ x - 1 ][ y + 1 ].getOccupante().getColore() instanceof Nero ){
                    
                    scacchiera[ x + 1 ][ y + 1 ] = 1;
                
                }
            
            if( ( x + 1 ) <= MAXLENGTH ){ // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x + 1 ][ y + 1 ].eOccupato() && m[ x + 1 ][ y + 1 ].getOccupante().getColore() instanceof Nero ){
                    
                    scacchiera[ x + 1 ][ y + 1 ] = 1;
                
                }
            
            }
        
        }
        
        return scacchiera;
    
    } // Fine movimentiPedone
    
    private int[][] movimentiTorre( Torre torre ){
        
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        int temp;
        
        x = torre.getX();
        y = torre.getY();

        // Direzione Verso Destra
        if( x + 1 <= MAXLENGTH ){

            // Spazi Liberi A Destra
            for( temp = x + 1; temp <= MAXLENGTH && !( m[ temp ][ y ].eOccupato()); temp++ ){
                
                scacchiera[ temp ][ y ] = 1;
            
            }

            // Primo Spazio Occupato A Destra
            temp++;
            
            if( temp <= MAXLENGTH ){ // Controllo Per Non Andare Fuori Scachiera

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto... Controllo isBusy() Obsoleto
                if( m[ temp ][ y ].eOccupato() && ( !m[ temp ][ y ].getOccupante().getColore().equals( torre.getColore() ) ) ){
                    
                    scacchiera[ temp ][ y ] = 1;
                
                }
            
            }
        
        }

        // Direzione Verso Sinistra
        if( x - 1 >= 0 ){
            
            for( temp = x - 1; temp >= 0 && !( m[ temp ][ y ].eOccupato()); temp-- ){
                
                scacchiera[ temp ][ y ] = 1;
            
            }
            
            temp--;
            
            if( temp >= 0 ){ // Controllo Per Non Andare Fuori Scachiera

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
                if( m[ temp ][ y ].eOccupato() && ( !m[ temp ][ y ].getOccupante().getColore().equals( torre.getColore() ) ) ){
                    
                    scacchiera[ temp ][ y ] = 1;
                
                }
            
            }
        
        }

        // Direzione Verso L'Alto
        if( y + 1 <= MAXLENGTH ){
            
            for( temp = y + 1; temp <= MAXLENGTH && ! ( m[ x ][ temp ].eOccupato()); temp++ ){
                
                scacchiera[ x ][ temp ] = 1;
            
            }
            
            temp++;
            
            if( temp <= MAXLENGTH ){ // Controllo Per Non Uscire Fuori Dalla Scachiera

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
                if( m[ x ][ temp ].eOccupato() && ( !m[ x ][ temp ].getOccupante().getColore().equals( torre.getColore() ) ) ){
                    
                    scacchiera[ x ][ temp ] = 1;
                
                }
            
            }
        
        }

        // Direzione Verso Il Basso
        if( y - 1 >= 0 ){
            
            for( temp = y - 1; temp >= 0 && !( m[ x ][ temp ].eOccupato()); temp++ ){
                
                scacchiera[ x ][ temp ] = 1;
            }
            
            temp--;
            
            if( temp >= 0 ){//controllo per non uscire fuori dalla scachiera

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
                if( m[ x ][ temp ].eOccupato() && ( !m[ x ][ temp ].getOccupante().getColore().equals( torre.getColore() ) ) ){
                    
                    scacchiera[ x ][ temp ] = 1;
                
                }
            
            }
        
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine movimentiTorre
    
    private int[][] movimentiAlfiere( Alfiere alfiere ){
        
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        int temp1, temp2; // Variabili Di Aiuto Per Spostamenti In Diagonale
        
        x = alfiere.getX();
        y = alfiere.getY();
        temp1 = x + 1; // Spostamento Sul Asse X
        temp2 = y + 1; // Spostamento Sul Asse Y
        
        // Direzione Verso Alto A Destra, Spazi Verso Alto A Destra Liberi
        while( temp1 <= MAXLENGTH && temp2 <= MAXLENGTH && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1++;
            temp2++;
        
        }
        
        // Il primo spazio occupato
        // Incremento effettuato dento il ciclo while
        // Temp1++;
        // Temp2++;
        // Potrebbe Essersi Verificata La Terza Condizione Del Ciclo While Ma Non Le Prime Due
        if( temp1 <=MAXLENGTH && temp2 <= MAXLENGTH ){

                // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
                if( m[ temp1 ][ temp2 ].eOccupato() && ( !m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ) ) ){
                    
                    scacchiera[ temp1 ][ temp2 ] = 1;
                
                }
        
        }

        // Direzione Verso Basso Destra
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= MAXLENGTH && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= MAXLENGTH && temp2 >= 0 ){

            // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
            if( m[ temp1 ][ temp2 ].eOccupato() && ( !m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ) ) ){
                
                scacchiera[ temp1 ][ temp2 ] = 1;
            }
        
        }

        // Direzione Alto Sinistra
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= MAXLENGTH && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1--;
            temp2++;
        
        }
        
        if( temp1 >= 0 && temp2 <= MAXLENGTH ){

            // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
            if( m[ temp1 ][ temp2 ].eOccupato() && ( !m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ) ) ){
                
                scacchiera[ temp1 ][ temp2 ] = 1;
            
            }
        
        }

        // Direzione Basso Sinistra
        temp1 = x - 1;
        temp2 = y - 1;
        
        while( temp1 >= 0 && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            scacchiera[ temp1 ][ temp2 ] = 1;
            temp1--;
            temp2--;
        
        }
        
        if( temp1 >= 0 && temp2 >= 0 ){

            // Se Lo Spazzio E Occupato E Contiene Un Pezzo Del Colore Opposto
            if( m[ temp1 ][ temp2 ].eOccupato() && ( !m[ temp1 ][ temp2 ].getOccupante().getColore().equals( alfiere.getColore() ) ) ){
                
                scacchiera[ temp1 ][ temp2 ] = 1;
            
            }
        
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine MovimentiAlfiere

    // Da Migliorare I Controlli Delle Posizioni Permesse Ragruppando Gli If
    private int[][] movimentiCavallo( Cavallo cavallo ){
        
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        int temp;
        x = cavallo.getX();
        y = cavallo.getY();
        
        // Alto Destra
        if( x + 1 <= MAXLENGTH && y + 2 <= MAXLENGTH ){ // Libero O Del Avversario
            
            if( !m[ x + 1 ][ y + 2 ].eOccupato() || ( m[ x + 1 ][ y + 2 ].eOccupato() && !m[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( cavallo.getColore() ) ) ){
                
                scacchiera[ x + 1 ][ y + 2 ] = 1;
            
            }
        
        }
        
        // Alto Sinistra
        if( x - 1 >= 0 && y + 2 <= MAXLENGTH ){ // Libero O Del Avversario
            
            if( !m[ x - 1 ][ y + 2 ].eOccupato() || ( m[ x - 1 ][ y + 2 ].eOccupato() && !m[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( cavallo.getColore() ) ) ){
                
                scacchiera[ x - 1 ][ y + 2 ] = 1;
            
            }
        
        }

        // Destra Alto
        if( x + 2 <= MAXLENGTH && y + 1 <= MAXLENGTH ){ // Libero O Del Avversario
            
            if( !m[ x + 2 ][ y + 1 ].eOccupato() || ( m[ x + 2 ][ y + 1 ].eOccupato() && !m[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( cavallo.getColore() ) ) ){
                
                scacchiera[ x + 2 ][ y + 1 ] = 1;
            
            }
        
        }

        // Destra Basso
        if( x + 2 <= MAXLENGTH && y - 1 >= 0 ){ // Libero O Del Avversario
            
            if( !m[ x + 2 ][ y - 1 ].eOccupato() || ( m[ x + 2 ][ y - 1 ].eOccupato() && !m[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( cavallo.getColore() ) ) ){
                
                scacchiera[ x + 2 ][ y - 1 ] = 1;
            
            }
        
        }

        // Sinistra Alto
        if( x - 2 >= 0 && y + 1 <= MAXLENGTH ){ // Libero O Del Avversario
            
            if( !m[ x - 2 ][ y + 1 ].eOccupato() || ( m[ x - 2 ][ y + 1 ].eOccupato() && !m[ x - 2 ][ y + 1 ].getOccupante().getColore().equals( cavallo.getColore() ) ) ){
                
                scacchiera[ x - 2 ][ y + 1 ] = 1;
            
            }
        
        }

        // Sinistra Basso
        if( x - 2 >= 0 && y - 1 >= 0 ){ // Libero O Del Avversario
            
            if( !m[ x - 2 ][ y - 1 ].eOccupato() || ( m[ x - 2 ][ y - 1 ].eOccupato() && !m[ x - 2 ][ y - 1 ].getOccupante().getColore().equals( cavallo.getColore() ) ) ){
                
                scacchiera[ x - 2 ][ y - 1 ] = 1;
            
            }
        
        }
        
        // Basso Desra
        if( x + 1 <= MAXLENGTH && y - 2 >= 0 ){ // Libero O Del Avversario
            
            if( !m[ x + 1 ][ y - 2 ].eOccupato() || ( m[ x + 1 ][ y - 2 ].eOccupato() && !m[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( cavallo.getColore() ) ) ){
                
                scacchiera[ x + 1 ][ y - 2 ] = 1;
            
            }
        
        }
        
        // Basso Sinistra
        if( x - 1 >= 0 && y - 2 >= 0 ){ // Libero O Del Avversario
            
            if( !m[ x - 1 ][ y - 2 ].eOccupato() || ( m[ x - 1 ][ y - 2 ].eOccupato() && !m[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( cavallo.getColore() ) ) ){
                
                scacchiera[ x - 1 ][ y - 2 ] = 1;
            
            }
        
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine movimentiCavallo
    
    private int[][] movimentiRegina( Regina regina ){
        
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int[][] scacchiera1 = new int[ 8 ][ 8 ];
        int[][] scacchiera2 = new int[ 8 ][ 8 ];
        
        int x, y;
        
        x = regina.getX();
        y = regina.getY();
        
        scacchiera1 = movimentiTorre( new Torre( x, y, regina.getColore() ) );
        scacchiera2 = movimentiAlfiere( new Alfiere( x, y, regina.getColore() ) );
        
        // Combinazione Dei Risultati Della Torre E Alfiere
        for( int i = 0; i < MAXLENGTH; i++ ){
            
            for( int j = 0; j < MAXLENGTH; j++ ){
                
                if( scacchiera1[ i ][ j ] == 1 || scacchiera2[ i ][ j ] == 1 ){
                    
                    scacchiera[ i ][ j ] = 1;
                
                }
            
            }
        
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine movimentoRegina

    // Prendo Tutte Le Posizioni Adiacenti Togliendo Gli Spazi Occupati Da 
    // Pezzi Dello Stesso Colore, Tolgo Posizioni Vuote Che Potrebbero Essere
    // Attaccate Da Altri Pezzi E Aggiungo La Posizione Per Scambiare Il Re
    // Con La Torre Se Nessuno Dei 2 Si E Mosso
    
    private int[][] movimentiRe( Re re ){
        System.err.println("DEBUG: movimentiRe in GestoreMovimenti");
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        
        x = re.getX();
        y = re.getY();
        System.err.println("DEBUG: entrato in getMovimentiRe");
        // Parte Destra
        if( x + 1 <= MAXLENGTH ){
            
            if( !m[ x + 1 ][ y ].eOccupato() || !m[ x + 1 ][ y ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x + 1 ][ y ] = 1;
            
            }
            
            if( y + 1 <= MAXLENGTH && !m[ x + 1 ][ y + 1 ].eOccupato() || !m[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x + 1 ][ y + 1 ] = 1;
            
            }
            
            if( y - 1 >= 0 && !m[ x + 1 ][ y - 1 ].eOccupato() || !m[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x + 1 ][ y - 1 ] = 1;
            
            }
        
        }

        // Parte Sinistra
        if( x - 1 >= 0){
            
            if( !m[ x - 1 ][ y ].eOccupato() || !m[ x - 1 ][ y ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x - 1 ][ y ] = 1;
            
            }
            
            if( y + 1 <= MAXLENGTH && !m[ x - 1 ][ y + 1 ].eOccupato() || !m[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x - 1 ][ y + 1 ] = 1;
            
            }
            
            if( y - 1 >= 0 && !m[ x - 1 ][ y - 1 ].eOccupato() || !m[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( re.getColore() ) ){
                
                scacchiera[ x - 1 ][ y - 1 ] = 1;
            
            }
        
        }

        // Rimanente Spazio In Alto
        if( y + 1 <= MAXLENGTH && !m[ x ][ y + 1 ].eOccupato() || !m[ x ][ y + 1 ].getOccupante().getColore().equals( re.getColore() ) ){
            
            scacchiera[ x ][ y + 1 ] = 1;
        
        }

        // Rimanente Spazio In Basso
        if( y - 1 >= 0 && !m[ x ][ y - 1 ].eOccupato() || !m[ x ][ y - 1 ].getOccupante().getColore().equals( re.getColore() ) ){
            
            scacchiera[ x ][ y - 1 ] = 1;
        
        }
        System.err.println("DEBUG: calcolato le posizioni dove spostarsi senza considerare gli spazi sotto scacco");
        System.err.println(scacchiera.toString());
        // Da Implementare Ancora I Controlli Dello Scacco Inverso / Pericolo
        for( int i = 0; i <= MAXLENGTH; i++ ){
            
            for( int j = 0; j <= MAXLENGTH; j++ ){
                
                if( scacchiera[ i ][ j ] == 1 ){
                    
                    //scacchiera[ i ][ j ] = controlloScacco( i, j, re.getColore(),m );
                    
                    //getPezziAttaccantiIlRe(re,this.matrice.getMatrice());
                    //vedo se ci sono pezzi del colore opposto che possono muoversi nella locazione dove potrebbe spostarsi il Re
                    //se è cosi contrassegno la posizione come non consentita
                    if(re.getColore() instanceof Bianco){
                        if(!(getPezziSpostabiliQui(matrice.getMatrice(),matrice.getSpazio(i, j),new Nero()).isEmpty()))
                            scacchiera[i][j]=0;
                    }
                    else{
                        if(!(getPezziSpostabiliQui(matrice.getMatrice(),matrice.getSpazio(i, j),new Bianco()).isEmpty()))
                            scacchiera[i][j]=0;
                    }
                }
            }
        }

        // Da Implementare Il Controllo Dello Scambio Con Una Delle 2 Torri ( Arrocco )
        // In Questo Caso Il Re Si Sposta Di 2 Caselle Verso La Torre E La Torre Gli Si Mette Di Fianco Dall'Altra Parte
        // Simulare La Scacchiera Modificata E Vedere Se Lo Scambio Non Porta A Una Situazione Di Scacco Matto
        if( !re.isMoved() && controlloScacco( x, y, re.getColore(),m ) == 1 ){ // Re Mai Mosso E Non E Sotto Scacco

            // Gli Spazi Fra Re E Torre Sinistra Sono Liberi?
            if( !m[ 1 ][ re.getY() ].eOccupato() && !m[ 2 ][ re.getY() ].eOccupato() && !m[ 3 ][ re.getY() ].eOccupato() ){

                // A Sinistra Ce Una Torre Nella Sua Posizione Originale
                if( m[ 0 ][ re.getY() ].getOccupante() instanceof Torre){

                    // Torre A Sinistra Mai Mossa
                    if( !( ( Torre ) m[ 0 ][ re.getY() ].getOccupante()).isMoved() ){

                        // Controllo Se Tutti Gli Spazi Tra La Posizione Attuale Del Re E Quella Finale Non E Sotto Scacco
                        /*
                        if( ( controlloScacco( x - 1, y, re.getColore(),m ) == 1 ) ){
                            
                            if( ( controlloScacco( x - 2, y, re.getColore(),m ) == 1 ) ){
                                
                                scacchiera[ x - 2 ][ y ] = 1;
                            
                            }
                        
                        }
                        */
                        //sostituisco il codice sopra con una chiamata alternativa
                        if(re.getColore() instanceof Bianco){
                            if( ( getPezziSpostabiliQui( matrice.getMatrice(),matrice.getSpazio(x-1, y), new Nero()).isEmpty() ) ){
                                if( ( getPezziSpostabiliQui( matrice.getMatrice(),matrice.getSpazio(x-2, y), new Nero()).isEmpty() )  ){
                                    scacchiera[ x - 2 ][ y ] = 1;
                                }
                            }
                        }
                        else{
                            if( ( getPezziSpostabiliQui( matrice.getMatrice(),matrice.getSpazio(x-1, y), new Bianco()).isEmpty() ) ){
                                if( ( getPezziSpostabiliQui( matrice.getMatrice(),matrice.getSpazio(x-2, y), new Bianco()).isEmpty() )  ){
                                    scacchiera[ x - 2 ][ y ] = 1;
                                }
                            }
                        }
                    }
                
                }
            
            }

            // Gli Spazi Tra Re E Torre Destra Sono Liberi?
            if( !m[ 5 ][ re.getY() ].eOccupato() && !m[ 6 ][ re.getY() ].eOccupato() ){

                // A Sinistra Ce Una Torre Nella Sua Posizione Originale
                if( m[ MAXLENGTH ][ re.getY() ].getOccupante() instanceof Torre ){

                    // Torre A Sinistra Mai Mossa
                    if( !( ( Torre ) m[ MAXLENGTH ][ re.getY() ].getOccupante()).isMoved() ){

                        // Controllo Se Tutti Gli Spazi Tra La Posizione Attuale Del Re E Quella Finale Non Sono Sotto Scacco
                        if(re.getColore() instanceof Bianco){
                            if( ( getPezziSpostabiliQui( matrice.getMatrice(),matrice.getSpazio(x+1, y), new Nero()).isEmpty() ) ){
                                if( ( getPezziSpostabiliQui( matrice.getMatrice(),matrice.getSpazio(x+2, y), new Nero()).isEmpty() )  ){
                                    scacchiera[ x + 2 ][ y ] = 1;
                                }
                            }
                        }
                        else{
                            if( ( getPezziSpostabiliQui( matrice.getMatrice(),matrice.getSpazio(x+1, y), new Bianco()).isEmpty() ) ){
                                if( ( getPezziSpostabiliQui( matrice.getMatrice(),matrice.getSpazio(x+2, y), new Bianco()).isEmpty() )  ){
                                    scacchiera[ x + 2 ][ y ] = 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return scacchiera;
        // Throw New UnsupportedOperationException
    
    } // Fine movimentiRe

    // Minimizzare Il Codice Ragruppando Re E Pedone In Casi Specifici ( Come Regina Con Torre E Alfiere )
    // Si Possono Fare Delle Chiamate Per Minimizzare Il Codice
    //0=c'è scacco
    //1=non c'è scacco
    /**
     * Determina se c'è scacco matto
     * @param x
     * @param y
     * @param colore
     * @param matrix
     * @return 
     */
    public int controlloScacco( int x, int y, Colore colore,Spazio[][] matrix){
        System.err.println("DEBUG: inizia controlloScacco");
        Spazio[][] mat=matrix;
        
        int temp1, temp2; 
        // Controllo Per Torri / Regine In Orizzontale E Verticale

        // Verso Destra
        boolean uscita = false;
        for( int i = x; i <= MAXLENGTH && !uscita; i++ ){
            if( mat[ i ][ y ].eOccupato() ){  
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore )){
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        return 0;
                    }
                }
            }
        }

        // Verso Sinistra
        uscita = false;
        
        for( int i = x; i >= 0 && !uscita; i-- ){
            if( mat[ i ][ y ].eOccupato() ){
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore ) ){
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        return 0;
                    }
                }
            }
        }

        // Verso L'Alto
        uscita = false;
        
        for( int i = y; i <= MAXLENGTH && !uscita; i++ ){
            if( mat[ x ][ i ].eOccupato() ){ 
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){  
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        return 0;
                    }
                }
            }
        }

        // Verso Il Basso
        uscita = false;
        
        for( int i = y; i >= 0 && !uscita; i-- ){
            
            if( mat[ x ][ i ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        
                        return 0;
                    
                    }
                
                }
            
            }
        
        }

        // Controlli In Diagonale Per Alfieri E Regine
        System.err.println("DEBUG: controlloAlfiere in controlloScacco");
        // Verso L'Alto A Destra
        temp1 = x + 1;
        temp2 = y + 1;
        
        while( temp1 <= MAXLENGTH && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1++;
            temp2++;
        
        }
        
        if( temp1 <= MAXLENGTH && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                return 0;
            
            }
        
        }

        // Verso L'Alto A Sinistra
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1--;
            temp2++;
        
        }
        
        if( temp1 >= 0 && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                return 0;
            
            }
        
        }

        // Verso Il Basso A Sinistra
        temp1 = x - 1;
        temp2 = y - 1;
        
        while( temp1 >= 0 && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1--;
            temp2--;
        
        }
        
        if( temp1 >= 0 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                return 0;
            
            }
        
        }

        // Verso Il Basso A Destra
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= MAXLENGTH && temp2 >= 0 && !mat[ temp1 ][ temp2 ].eOccupato() ){
            temp1++;
            temp2--;
        }
        
        if( temp1 <= MAXLENGTH && temp2 >= 0 ){
            if(!mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore )){
                if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){         
                    return 0;
                }
            }
        }

        // Controlli Per Il Pedone ( Potrebbe Essere Integrato Nell'Alfiere In Casi Specifici )
        System.err.println("DEBUG: controllo pedone in controlloScacco");
        if( colore instanceof Nero ){ // Inizio Controllo Pedoni Neri
            
            if( x + 1 <= MAXLENGTH && y + 1 <= MAXLENGTH ){
                if(mat[ x + 1 ][ y + 1 ].eOccupato()){
                    if(mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Pedone){
                        if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                            return 0;
                        }
                    }
                }
            }
            
            if( x - 1 >= 0 && y + 1 <= MAXLENGTH ){
                if(mat[ x - 1 ][ y + 1 ].eOccupato()){
                    if(mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Pedone){
                        if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                            return 0;
                        }
                    }
                }
            
            }
        
        } else { // Inizio Controllo Pedoni Bianchi
            
            if( x + 1 <= MAXLENGTH && y - 1 >= 0  ){
                if(mat[ x + 1 ][ y - 1 ].eOccupato()){
                    if(mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone){
                        if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                            return 0;
                        }
                    }
                }
            }
            
            if( x - 1 >= 0 && y - 1 >= 0 ){
                if(mat[ x - 1 ][ y - 1 ].eOccupato() ){
                    if(mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Pedone){
                        if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                            return 0;
                        }
                    }
                }
            }
        
        }

        // Controlli Del Cavallo
        System.err.println("DEBUG: controlloCavallo in controllOScacco");
        //In Alto A Destra
        if( x + 1 <= MAXLENGTH && y + 2 <= MAXLENGTH && mat[ x + 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                return 0;
            
            }
        
        }

        //In Alto A Sinistra
        if( x - 1 >= 0 && y + 2 <= MAXLENGTH && mat[ x - 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                return 0;
            
            }
        
        }

        // A Destra In Alto
        if( x + 2 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 2 ][ y + 1 ].eOccupato() ){
            
            if( !mat[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                return 0;
            
            }
        
        }

        // A Destra In Basso
        if( x + 2 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 2 ][ y - 1 ].eOccupato() ){       
            if( !mat[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){      
                return 0;
            }
        }

        // A Sinistra In Alto
        if( x - 2 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 2 ][ y + 1 ].eOccupato() ){    
            if( !mat[ x - 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){    
                return 0;
            }
        }

        // A Sinistra In Basso
        if( x - 2 >= 0 && y - 1 >= 0 && mat[ x - 2 ][ y - 1 ].eOccupato() ){
            
            if( !mat[ x - 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y - 1 ].getOccupante() instanceof Cavallo ){
                
                return 0;
            
            }
        
        }

        // In Basso A Destra
        if( (x + 1) <= MAXLENGTH && (y - 2) >= 0 ){
            //System.err.println("DEBUG: mat in "+(x+1)+" "+(y-2));
            if(mat[ x + 1 ][ y - 2 ].eOccupato()){
                if( !mat[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){            
                    return 0;
                }
            }
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 2 >= 0 ){
            if(mat[ x - 1 ][ y - 2 ].eOccupato()){
                if( !mat[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){  
                    return 0;
                }
            }
        }

        // Controllo Re Adiacente
        System.err.println("DEBUG: controlloRe in controlloScacco");
        // Destra
        if( x + 1 <= MAXLENGTH ){
            if(mat[ x + 1 ][ y ].eOccupato()){
                if( !mat[ x + 1 ][ y ].getOccupante().getColore().equals( colore ) && m[ x + 1 ][ y ].getOccupante() instanceof Re ){
                    return 0;
                }
            }
        }

        // Sinistra
        if( x - 1 >= 0 ){    
            if(mat[ x - 1 ][ y ].eOccupato()){
                if( !mat[ x - 1 ][ y ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y ].getOccupante() instanceof Re ){
                    return 0;
                }
            }
        }

        // Alto
        if( y + 1 <= MAXLENGTH ){ 
            if(m[ x ][ y + 1 ].eOccupato()){
                if( !mat[ x ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y + 1 ].getOccupante() instanceof Re ){             
                    return 0;
                }
            }
        }

        // Basso
        if( y - 1 >= 0 ){
            if(mat[ x ][ y - 1 ].eOccupato()){
                if( !mat[ x ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y - 1 ].getOccupante() instanceof Re ){
                    return 0;
                }
            }
        }

        // In Alto A Destra
        if( x + 1 <= MAXLENGTH && y + 1 <= MAXLENGTH ){
            if(mat[ x + 1 ][ y + 1 ].eOccupato()){
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                    return 0;
                }
            }
        }

        // In Alto A Sinistra
        if( x - 1 >= 0 && y + 1 <= MAXLENGTH ){
            if(mat[ x - 1 ][ y + 1 ].eOccupato()){
                if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                    return 0;
                }
            }
        }

        // In Basso A Destra
        if( x + 1 <= MAXLENGTH && y - 1 >= 0 ){
            if(mat[ x + 1 ][ y - 1 ].eOccupato()){
                if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Re ){      
                    return 0;
                }
            }
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 1 >= 0 ){   
            if(mat[ x - 1 ][ y - 1 ].eOccupato()){
                if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Re ){   
                    return 0;
                }
            }
        }
        
        return 1;
        // Throw New UnsupportedOperationException
    
    }
    
    /**
     * Funzione di supporto per facilitare l'uso di controlloScacco
     * @param r
     * @return 
     */
    public int controlloScacco(Re r) {  
        return controlloScacco( r.getX(), r.getY(), r.getColore(),m );
    }
    
    /**
     * Controlla se il re è messo in pericolo da eventuali pezzi nemici
     * @param r
     * @param matrix
     * @return 
     */
    public LinkedList<Pezzo> getPezziAttaccantiIlRe(Re r,Spazio[][] matrix){
        LinkedList<Pezzo> lista; 
        Spazio[][] mat=matrix;
        lista = new LinkedList<>();
        Re re=r;
        int x=re.getX();
        int y=re.getY();
        Colore colore=re.getColore();
        
        int temp1, temp2; 
        // Controllo Per Torri / Regine In Orizzontale E Verticale

        // Verso Destra
        boolean uscita = false;
        for( int i = x; i <= MAXLENGTH && !uscita; i++ ){
            if( mat[ i ][ y ].eOccupato() ){  
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore )){
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        lista.add(mat[i][y].getOccupante());
                    }
                }
            }
        }

        // Verso Sinistra
        uscita = false;
        
        for( int i = x; i >= 0 && !uscita; i-- ){
            if( mat[ i ][ y ].eOccupato() ){
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ i ][ y ].getOccupante().getColore().equals( colore ) ){
                    if( mat[ i ][ y ].getOccupante() instanceof Torre || mat[ i ][ y ].getOccupante() instanceof Regina ){
                        lista.add(mat[i][y].getOccupante());
                    }
                }
            }
        }

        // Verso L'Alto
        uscita = false;
        
        for( int i = y; i <= MAXLENGTH && !uscita; i++ ){
            if( mat[ x ][ i ].eOccupato() ){ 
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){  
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        lista.add(mat[x][i].getOccupante());
                    }
                }
            }
        }

        // Verso Il Basso
        uscita = false;
        
        for( int i = y; i >= 0 && !uscita; i-- ){
            
            if( mat[ x ][ i ].eOccupato() ){
                
                uscita = true; // Uscita Dal Ciclo Dopo Aver Controllato Il Primo Spazio Per Non Fare Controlli Inutili
                
                if( !mat[ x ][ i ].getOccupante().getColore().equals( colore ) ){
                    
                    if( mat[ x ][ i ].getOccupante() instanceof Torre || mat[ x ][ i ].getOccupante() instanceof Regina ){
                        
                        lista.add(mat[x][i].getOccupante());
                    
                    }
                
                }
            
            }
        
        }

        // Controlli In Diagonale Per Alfieri E Regine

        // Verso L'Alto A Destra
        temp1 = x + 1;
        temp2 = y + 1;
        
        while( temp1 <= MAXLENGTH && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1++;
            temp2++;
        
        }
        
        if( temp1 <= MAXLENGTH && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add(mat[temp1][temp2].getOccupante());
            
            }
        
        }

        // Verso L'Alto A Sinistra
        temp1 = x - 1;
        temp2 = y + 1;
        
        while( temp1 >= 0 && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1--;
            temp2++;
        
        }
        
        if( temp1 >= 0 && temp2 <= MAXLENGTH && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add(mat[temp1][temp2].getOccupante());
            
            }
        
        }

        // Verso Il Basso A Sinistra
        temp1 = x - 1;
        temp2 = y - 1;
        
        while( temp1 >= 0 && temp2 >= 0 && !m[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1--;
            temp2--;
        
        }
        
        if( temp1 >= 0 && temp2 >= 0 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add(mat[temp1][temp2].getOccupante());
            
            }
        
        }

        // Verso Il Basso A Destra
        temp1 = x + 1;
        temp2 = y - 1;
        
        while( temp1 <= MAXLENGTH && temp2 >= 0 && !mat[ temp1 ][ temp2 ].eOccupato() ){
            
            temp1++;
            temp2--;
        
        }
        
        if( temp1 <= MAXLENGTH && temp2 >= 0 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                lista.add(mat[temp1][temp2].getOccupante());
            
            }
        
        }

        // Controlli Per Il Pedone ( Potrebbe Essere Integrato Nell'Alfiere In Casi Specifici )
        
        if( colore instanceof Nero ){ // Inizio Controllo Pedoni Neri
            
            if( x + 1 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add(mat[x+1][y+1].getOccupante());
                
                }
            
            }
            
            if( x - 1 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add(mat[x-1][y+1].getOccupante());
                
                }
            
            }
        
        } else { // Inizio Controllo Pedoni Bianchi
            
            if( x + 1 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add(mat[x+1][y-1].getOccupante());
                }
            
            }
            
            if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    lista.add(mat[x-1][y-1].getOccupante());
                
                }
            
            }
        
        }

        // Controlli Del Cavallo

        //In Alto A Destra
        if( x + 1 <= MAXLENGTH && y + 2 <= MAXLENGTH && mat[ x + 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+1][y+2].getOccupante());
            
            }
        
        }

        //In Alto A Sinistra
        if( x - 1 >= 0 && y + 2 <= MAXLENGTH && mat[ x - 1 ][ y + 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y + 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x-1][y+2].getOccupante());
            
            }
        
        }

        // A Destra In Alto
        if( x + 2 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 2 ][ y + 1 ].eOccupato() ){
            
            if( !mat[ x + 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+2][y+1].getOccupante());
            
            }
        
        }

        // A Destra In Basso
        if( x + 2 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 2 ][ y - 1 ].eOccupato() ){
            
            if( !mat[ x + 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+2][y-1].getOccupante());
            
            }
        
        }

        // A Sinistra In Alto
        if( x - 2 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 2 ][ y + 1 ].eOccupato() ){
            
            if( !mat[ x - 2 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y + 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x-2][y+1].getOccupante());
            
            }
        
        }

        // A Sinistra In Basso
        if( x - 2 >= 0 && y - 1 >= 0 && mat[ x - 2 ][ y - 1 ].eOccupato() ){
            
            if( !mat[ x - 2 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 2 ][ y - 1 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x-2][y-1].getOccupante());
            
            }
        
        }

        // In Basso A Destra
        if( x + 1 <= MAXLENGTH && y - 2 <= MAXLENGTH && mat[ x + 1 ][ y - 2 ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x+1][y-2].getOccupante());
            
            }
        
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 2 >= 0 && mat[ x - 1 ][ y - 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                
                lista.add(mat[x-1][y-2].getOccupante());
            
            }
        
        }

        // Controllo Re Adiacente

        // Destra
        if( x + 1 <= MAXLENGTH && mat[ x + 1 ][ y ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y ].getOccupante().getColore().equals( colore ) && m[ x + 1 ][ y ].getOccupante() instanceof Re ){
                
                lista.add(mat[x+1][y].getOccupante());
            
            }
        
        }

        // Sinistra
        if( x - 1 >= 0 && mat[ x - 1 ][ y ].eOccupato() ){          
            if( !mat[ x - 1 ][ y ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y ].getOccupante() instanceof Re ){
                lista.add(mat[x-1][y].getOccupante());
            }
        }

        // Alto
        if( y + 1 <= MAXLENGTH && m[ x ][ y + 1 ].eOccupato() ){ 
            if( !mat[ x ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y + 1 ].getOccupante() instanceof Re ){             
                lista.add(mat[x][y+1].getOccupante());
            }
        }

        // Basso
        if( y - 1 >= 0 && mat[ x ][ y - 1 ].eOccupato() ){
            if( !mat[ x ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y - 1 ].getOccupante() instanceof Re ){
                lista.add(mat[x][y-1].getOccupante());
            }
        }

        // In Alto A Destra
        if( x + 1 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 1 ][ y + 1 ].eOccupato() ){
            if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                lista.add(mat[x+1][y+1].getOccupante());
            }
        }

        // In Alto A Sinistra
        if( x - 1 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 1 ][ y + 1 ].eOccupato() ){
            if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                lista.add(mat[x-1][y+1].getOccupante());
            }
        }

        // In Basso A Destra
        if( x + 1 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() ){
            if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Re ){      
                lista.add(mat[x+1][y-1].getOccupante());
            }
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() ){         
            if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Re ){   
                lista.add(mat[x-1][y-1].getOccupante());
            }
        }
        
        return lista;
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    
    
    private boolean percorsoTorre(Pezzo p,int x,int y){
        int xp=p.getX();
        int yp=p.getY();
        int temp;
        //vedo se la posizione in cui spostarsi è valida
        if (x == xp) {
            temp = yp;
            //ciclo bidirezionale
            while (temp != y) {
                if (temp < y) {
                    temp++;
                } else {
                    temp--;
                }
                if (matrice.getMatrice()[x][temp].eOccupato()) {
                    return false;
                }
            }
            return true;
        }

        if (y == yp) {
            temp = xp;
            //ciclo bidirezionale
            while (temp != x) {
                if (temp < x) {
                    temp++;
                } else {
                    temp--;
                }
                if (matrice.getMatrice()[temp][y].eOccupato()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    
    private boolean percorsoAlfiere(Pezzo p,int x,int y){
        int temp1,temp2;
        int xp=p.getX();
        int yp=p.getY();
        //controllo validità posizione
            temp1=xp;
            temp2=yp;
            //primo quadrante e terzo quadrante
            if(((double)(x-xp)/(y-yp))==1){
                //primo quadrante
                if(xp<x){
                    temp1++;
                    temp2++;
                    while(temp1<x){//comprende anche il caso delle y
                        if(matrice.getMatrice()[temp1][temp2].eOccupato())
                            return false;
                        temp1++;
                        temp2++;
                    }
                    
                }
                //terzo quadrante
                else if(xp>x){
                    temp1--;
                    temp2--;
                    while(temp1<x){//comprende anche il caso delle y
                        if(matrice.getMatrice()[temp1][temp2].eOccupato())
                            return false;
                        temp1--;
                        temp2--;
                    } 
                }
                return true;
            }
            
            //secondo quadrante e quarto quadrante
            if(((double)(x-xp)/(y-yp))==-1){
                if(xp<x){
                    temp1++;
                    temp2--;
                    while(temp1<x){//comprende anche il caso delle y
                        if(matrice.getMatrice()[temp1][temp2].eOccupato())
                            return false;
                        temp1++;
                        temp2--;
                    }
                }
                else if(xp>x){
                    temp1--;
                    temp2++;
                    while(temp1>x){//comprende anche il caso delle y
                        if(matrice.getMatrice()[temp1][temp2].eOccupato())
                            return false;
                        temp1--;
                        temp2++;
                    }
                }
                return true;
            }
            return false;  
    }
    
    /**
     * Determina on quale posizione può essere spostato il pezzo
     * @param p
     * @param x
     * @param y
     * @return 
     */
    public boolean spostabileIn(Pezzo p,int x, int y){
        int xp=p.getX();
        int yp=p.getY();
        int temp;//vriabile temporale per torre
        int temp1;//variabile temèorale per alfiere
        int temp2;// " "
        System.err.println("DEBUG: entro nel metodo spostabileIn");
        Spazio s=matrice.getMatrice()[x][y];
        //devo controllare se nella posizione finale c'è uno spazio libero o un pezzo del colore opposto e poi
        //tutti gli spazi intermedi x tuti i tipi di pezzo tranne il cavallo
        
        
        //caso non considerato nei cicli sotto
        if(x==xp && y==yp)
            return true;
        
        
        //controllo se la posizione finale è vuota o contiene un pezzo del colore opposto
        //(non posso spostarmi in un locazione con un pezzo dello stesso colore)
        if(!s.eOccupato() || !s.getOccupante().getColore().equals(p.getColore())){
            //divido i controlli in base al pezzo
            if (p instanceof Torre) {
                return percorsoTorre(p, x, y);
            }
            if (p instanceof Alfiere) {
                return percorsoAlfiere(p, x, y);
            }

            if (p instanceof Cavallo) {
                //caso base per la verifica successiva
                if (xp != x && yp != y) {
                //funzione di verifica per la correttezza della posizione
                    //(modulo della somma dei 2 delta=3)
                    if (abs((double) x - xp) + abs((double) y - yp) == 3) {
                        return true;
                    }
                }
                return false;
            }

            if (p instanceof Regina) {
                return percorsoAlfiere(p, x, y) || percorsoTorre(p, x, y);
            }

            if (p instanceof Pedone) {
                //System.err.println("chiamo percorsoPedone");
                matrice.toString();
                return percorsoPedone(matrice.getSpazio(xp, yp).getOccupante(), x, y, matrice.getMatrice());
            }

            if (p instanceof Re) {

            //escludo il caso base
                if (x != xp || y != yp) {
                    //posizioni adiacenti al re
                    if ((x == xp + 1 || x == xp - 1 || x == xp) && (y == yp + 1 || y == yp - 1 || y == yp)) {
                        System.err.println("posizione adiacente al re");
                        if (!matrice.getSpazio(x, y).eOccupato()
                                || (matrice.getSpazio(x, y).eOccupato()
                                && !matrice.getSpazio(x, y).getOccupante().getColore().equals(p.getColore()))) {
                            return true;
                        }
                    }
                    System.err.println("DEBUG: posizione non adiacente al re");
                //arrocco
                    //devo aggiungere la condizione
                    //secondo la quale le posizioni tra il re e la torre non sono sotto scacco
                    //re mai mosso
                    if (!((Re) p).isMoved()) {
                        if (x == xp + 2) {
                            if (matrice.getSpazio(7, y).eOccupato() && matrice.getSpazio(7, y).getOccupante() instanceof Torre ) {
                                if (!((Torre) matrice.getSpazio(7, y).getOccupante()).isMoved()) {
                                    if (!matrice.getSpazio(5, y).eOccupato() && !matrice.getSpazio(6, y).eOccupato()) {
                                        return true;
                                    }
                                }
                            }
                        }
                        if (x == xp - 2) {
                            if (matrice.getSpazio(0, y).eOccupato() && matrice.getSpazio(0, y).getOccupante() instanceof Torre) {
                                if (!((Torre) matrice.getSpazio(0, y).getOccupante()).isMoved()) {
                                    if (!matrice.getSpazio(1, y).eOccupato()
                                            && !matrice.getSpazio(2, y).eOccupato()
                                            && !matrice.getSpazio(3, y).eOccupato()) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
        }
        return false;
    }
    
    
    
    private boolean percorsoPedone(Pezzo p,int x,int y,Spazio[][]matrice){
        int xp=p.getX();
        int yp=p.getY();
        if(!matrice[x][y].eOccupato()){
                System.err.println("DEBUG: percorsoPedone()");
                //un pedone può spostari solo in avanti su celle vuote
                if(x==xp){
                    if(p.getColore() instanceof Nero){
                        //si è già mosso
                        if(((Pedone) p).isMoved()){
                            if(y==yp+1)
                                return true;
                        }
                        //p non si è mai mosso
                        else {
                            if(y==yp+1 || y==yp+2){
                                return true;
                            }
                        }    
                    }
                    //Bianco
                    else{
                        //il pedone si è già mosso
                        if(((Pedone)p).isMoved()){
                            if((y==yp-1) && !matrice[x][y].eOccupato())
                                return true;
                        }
                        //p non si è mai mosso
                        else if(y==yp-1 || y==yp-2){
                            if(!matrice[x][y].eOccupato())
                                return true;
                        }
                    }
                }
                return false;
            }
            //posizione finale occupata da un pezzo avversario
            else{
                //posizione consentita nel range orizzontale
                if(x==xp+1 || x==xp-1){
                    if(p.getColore() instanceof Nero){
                        if(y==yp+1)
                            return true;
                        return false;
                    }
                    if(p.getColore() instanceof Bianco){
                        if(y==yp-1)
                            return true;
                        return false;
                    }
                }
                return false;
            }
        
    }    
    
    
    //passa come parametro il colore considerato != colore Re
    /**
     * Controlla ogni casella sulla scacchiera e definisce quali pezzi possono
     * essere spostati in quella posizione
     * @param mat
     * @param s
     * @param c
     * @return 
     */
    public LinkedList<Pezzo> getPezziSpostabiliQui(Spazio[][] mat,Spazio s,Colore c){
        LinkedList<Pezzo> lista;
        lista=new LinkedList<>();
        Spazio[][] matrix=mat;
        Spazio spazio=s;
        Colore colore=c;
        int x=spazio.getX();
        int y=spazio.getY();
        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                if(matrix[i][j].eOccupato())
                    if(matrix[i][j].getOccupante().getColore().equals(colore))
                        if(matrix[i][j].getOccupante().spostabileIn(x, y, matrix))  
                            lista.add(matrix[i][j].getOccupante());
            }
        }
        return lista;
    }
    
    /**
     * Controlla quali pezzi della matrice impediscono lo scacco matto
     * @param xRe
     * @param yRe
     * @param matrice
     * @param turno
     * @return 
     */
    public int[][] getMatricePezziChePrevengonoScacco(int xRe,int yRe,MatriceDeiPezzi matrice,Colore turno){
        MatriceDeiPezzi originale=matrice;
        LinkedList<Pezzo> listaAttaccanti=new LinkedList<>();
        LinkedList<Pezzo> listaSalvatori=new LinkedList<>();
        Re re=(Re) originale.getSpazio(xRe, yRe).getOccupante();
        int[][] matricePosizioni=new int[8][8];
        Iterator iter=listaAttaccanti.iterator();
        boolean cavalloAttaccante=false;
        int x; //x provvisoria per il passaggio da linked list a matrice di inter
        int y; //y provvisoria " "
        int[][] matriceRisultante = new int[8][8];
        
        //azzero le matrici
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                matricePosizioni[i][j]=0;
                matriceRisultante[i][j]=0;
            }
        }
        
        //scorro la scacchiera
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){         
                if(j!=xRe && i!=yRe)
                    //prendo gli spazi non vuoti
                    if(originale.getSpazio(j, i).eOccupato())
                        //prendo tutti i pezzi del colore del turno corrente
                        if(originale.getSpazio(j, i).getOccupante().getColore().equals(turno))
                            //setto a 1 le posizioni dove i pezzi sono del colore del turno corrente
                            matricePosizioni[j][i]=1;
            }
        }
        
        //guardo i pezzi del colore opposto che possono fare scacco al re
        //(chi può spostarsi nella sua locazione)
        //li salvo in una lista
        if(re.getColore() instanceof Bianco)
            listaAttaccanti=getPezziSpostabiliQui(originale.getMatrice(),originale.getSpazio(xRe, yRe),new Nero());
        else
            listaAttaccanti=getPezziSpostabiliQui(originale.getMatrice(),originale.getSpazio(xRe, yRe),new Bianco());
        
        //per ogni pezzo mio vedo chi può neutralizzarli tutti contemporaneamente e li salvo in un'altra lista
        //ritorno la lista coi pezzi che salvano il re

        //forse utilizzo l'algoritmo + efficace che non usa combinazioni inutili
        
        /*si può salvare il re cosi:
            1)Il re può spostarsi (si salva da solo)
            2)Mi metto tra l'attaccante e il Re (non nel caso del cavallo che attacca)
            3)Mangio l'attaccante (se è uno solo)
        Per fare il punto 2 potrei salvarmi le posizioni intermedie tra gli attaccanti e il re (esclusa la torre)    
        
        */
        //1)
        if(reSiSalvaDaScacco(re,originale.getMatrice()))
            listaSalvatori.add(re);
        //2)
        
        
        
        //QUI devo mettere la chiamata del metodo che prende tutti i pezzi che potrebbero salvare il re e incrociare 
        //i loro percorsi con tutti i percorsi degli attaccanti
        
        //...
        //NON funziona se tra gli attaccanti c'è un cavallo
        //guardo il tipo di pezzo dell'attaccante o degli attaccanti
        //sommo le 2 matrici ricevute avendo tutte le posizioni che intercorrono tra l'attaccante e il salvatore
        //le posizioni di intersezione saranno le posizioni nelle quali spostarsi per evitare lo scacco
        //il numero nelle celle da usare deve essere uguale al numero di attaccanti
        //nel caso contrario il metodo 2 non può funzionare
        //mi ricavo le posizioni intermedie
        
        
        
        //vedo se tra gli attaccanti c'è un cavallo
        while(iter.hasNext()){
            if((Pezzo)iter.next() instanceof Cavallo)
                cavalloAttaccante=true;
        }
        
        //se ci sono più pezzi che attaccano il re il salvatore deve poter 
        //arrivare nell'intersezione di tutti altrimenti il secondo caso 
        //non ha senso di essere usato
        //CORREZIONE
        //se ci sono più di un attaccante il secondo caso non è valido
        
        //nel caso contrario si procede nel caso 2
        if(!cavalloAttaccante){
            int contatoreAttaccanti=0;
            contatoreAttaccanti++;
            iter=listaAttaccanti.iterator();
            while(iter.hasNext())
                contatoreAttaccanti++;
            if(contatoreAttaccanti==1){
                LinkedList<Pezzo> temp=pezziCheSalvanoIlReCasoDue(listaAttaccanti,matricePosizioni,re);
                iter=temp.iterator();
                while(iter.hasNext()){
                    listaSalvatori.add((Pezzo)iter.next());
                }
            }
        }
        
        
        
        //3)
        //c'è solo un elemento nella lista (non posso prevenire lo scacco eliminando tutti gli attaccanti contemporaneamente)
        
        if(!listaAttaccanti.isEmpty()){
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(matricePosizioni[i][j]==1)
                        if(matrice.getSpazio(i, j).getOccupante().spostabileIn(listaAttaccanti.getFirst().getX(),listaAttaccanti.getFirst().getY(),m))
                            listaSalvatori.add(matrice.getSpazio(i, j).getOccupante());
                }
            }
            
            /*
            for(Pezzo p:listaSalvatori){
                if(spostabileIn(p,listaAttaccanti.getFirst().getX(),listaAttaccanti.getFirst().getY()))
                    listaSalvatori.add(p);
            }*/
        }
        
        //assegno i pezzi trovati nella matricePosizioni
        iter=listaSalvatori.iterator();
        Pezzo pezzoTemporaneo;
        if(!listaSalvatori.isEmpty()){
            while(iter.hasNext()){
                pezzoTemporaneo=(Pezzo)iter.next();
                x=pezzoTemporaneo.getX();
                y=pezzoTemporaneo.getY();
                matriceRisultante[x][y]=1;
            }
        }
        return matriceRisultante;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    
    //devo vedere se il pezzo salvatore è spostabile in almeno una delle posizioni che si trovano tra il re e TUTTI gli attaccanti
    //non funziona se ci sono più di un pezzo che attaccano
    public LinkedList<Pezzo> pezziCheSalvanoIlReCasoDue(LinkedList<Pezzo> attaccanti, int[][] matricePotenzialiSalvatori,Re re){
        LinkedList salvatori;
        salvatori = new LinkedList<Pezzo>();
        Iterator iter;
        Pezzo attaccante;
        int[][] percorsoAttaccante=new int[8][8];
        //caso in cui non ci sono attaccanti, forse si può portare la soluzione del caso più in alto
        //non dovrei mai entrare qui
        if(attaccanti.isEmpty()){
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(matricePotenzialiSalvatori[i][j]==1)
                        salvatori.add(matrice.getSpazio(i, j).getOccupante());
                }
            }
            return salvatori;
        }
        
        int xRe=re.getX();
        int yRe=re.getY();
        int[][] percorsoTemporaneo;
        iter=attaccanti.iterator();
        attaccante=(Pezzo)iter.next();
        percorsoAttaccante=getPercorsoFinoAlRe(re,attaccante);
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(matricePotenzialiSalvatori[i][j]==1){
                    percorsoTemporaneo=getPercorsoFinoAlRe(re,matrice.getSpazio(i, j).getOccupante());
                    if(intersezionePercorsiNonVuota(percorsoAttaccante,percorsoTemporaneo))
                        salvatori.add(matrice.getSpazio(i, j).getOccupante());
                }      
            }
        }
        return salvatori;
        //throw new UnsupportedOperationException("Not supported yet.");
        
    }
    //uso per ricavare tutte le posizioni tra il re e il pezzo in questione che può
    //essere uno che vuole attaccare il re o uno che può salvarlo
    private int[][] getPercorsoFinoAlRe(Re re,Pezzo pezzo,MatriceDeiPezzi matrice){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private int[][] getPercorsoFinoAlRe(Re re,Pezzo pezzo){
        int xRe=re.getX();
        int yRe=re.getY();
        int xPezzo=pezzo.getX();
        int yPezzo=pezzo.getY();
        int[][] matrice=new int[8][8];
        int temp;
        int temp2;
        //inizializzo la matrice
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                matrice[i][j]=0;
            }
        }
        //il pedone non ha percorsi
        if(pezzo instanceof Pedone)
            return matrice;
        
        if(pezzo instanceof Torre){
            //La torre non può fisicamente attaccare il re => non esiste un percorso
            if(xPezzo != xRe && yPezzo != yRe)
                return matrice;
            else
                if(xPezzo == xRe){
                    //la torre è sopra al re
                    if(yPezzo>yRe){
                        temp=yPezzo-1;
                        while(temp>yRe){
                            matrice[xPezzo][temp]=1;
                            temp--;
                        }
                        return matrice;
                    }
                    else{
                        //la torre è sotto al re
                        if(yPezzo<yRe){
                            temp=yPezzo+1;
                            while(temp<yRe){
                                matrice[xPezzo][temp]=1;
                                temp++;
                            }
                            return matrice;
                        }
                    }
                }
                //yPezzo == yRe
                else{
                    //la torre è 
                    if(xPezzo>xRe){
                        temp=xPezzo-1;
                        while(temp>xRe){
                            matrice[temp][yPezzo]=1;
                            temp--;
                        }
                        return matrice;
                    }
                    else{
                        temp=xPezzo+1;
                        while(temp<xRe){
                            matrice[yPezzo][temp]=1;
                            temp++;
                        }
                        return matrice;
                    }
                }
            
        }
        
        //mancano gli altri casi: alfiere, regina
        if(pezzo instanceof Alfiere){
            //l'alfiere non è in diagonale col re
            if(!((xPezzo - xRe == yPezzo - yRe)||(xPezzo - xRe == -yPezzo-yRe)))
                return matrice;
            if(xPezzo>xRe){
                if(yPezzo>yRe){
                    temp=xPezzo-1;
                    temp2=yPezzo-1;
                    while(temp>xRe){
                        matrice[temp][temp2]=1;
                        temp--;
                        temp2--;
                    }
                    return matrice;
                }
                //else
                
            }
        }
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private int[][] intersezioneTraPercorsi(int[][] primo,int[][]secondo){
        int[][] risultato=new int[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(primo[i][j]==1 && secondo[i][j]==1){
                    risultato[i][j]=1;
                }
                else
                    risultato[i][j]=0;
            }
        }
        return risultato;
    }
    
    private boolean intersezionePercorsiNonVuota(int [][]primo,int [][] secondo){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(primo[i][j]==1 && secondo[i][j]==1)
                    return true;    
            }
        }
        return false;
    }
    
    //private boolean percorsiCheSiincrociano();
    
    private boolean reSiSalvaDaScacco(Re re,Spazio[][] matrice){
        Spazio[][] originale=matrice;
        Spazio[][] matSimulata;
        int x=re.getX();
        int y=re.getY();
        
        boolean scacco=true;
        //vedo se l'attaccante è adiacente al re? (non posso farlo senza avere la lista degli attaccanti[elaborioso])
        //sposto il re nelle locazioni nelle quali può andare e controllo se rimane la situazione di scacco
        
        //provo in alto a sinistra
        if(x>0 && y>0){
            matSimulata=matrice;
            matSimulata[x-1][y-1].setOccupante(new Re(x-1,y-1,re.getColore()));
            matSimulata[x][y].setOccupante(null);
            matSimulata[x][y].setOccupato(false);
            if(controlloScacco(x-1,y-1,re.getColore(),matSimulata)==0)
                    scacco=true;
            else
                return true;
        }
        //provo in alto
        if(y>0){ 
            matSimulata=matrice;
            matSimulata[x][y-1].setOccupante(new Re(x,y-1,re.getColore()));
            matSimulata[x][y].setOccupante(null);
            matSimulata[x][y].setOccupato(false);
            if(controlloScacco(x,y-1,re.getColore(),matSimulata)==0)
                    scacco=true;
            else
                return true;
        }
        //provo in alto a destra
        if(x<8 && y>0){ 
            matSimulata=matrice;
            matSimulata[x][y-1].setOccupante(new Re(x+1,y-1,re.getColore()));
            matSimulata[x][y].setOccupante(null);
            matSimulata[x][y].setOccupato(false);
            if(controlloScacco(x,y-1,re.getColore(),matSimulata)==0)
                    scacco=true;
            else
                return true;
        }
        
        //provo a sinistra
        if(x>0){ 
            matSimulata=matrice;
            matSimulata[x-1][y].setOccupante(new Re(x-1,y,re.getColore()));
            matSimulata[x][y].setOccupante(null);
            matSimulata[x][y].setOccupato(false);
            if(controlloScacco(x-1,y,re.getColore(),matSimulata)==0)
                    scacco=true;
            else
                return true;
        }
        
        //provo a destra
        if(x<8){ 
            matSimulata=matrice;
            matSimulata[x+1][y].setOccupante(new Re(x+1,y,re.getColore()));
            matSimulata[x][y].setOccupante(null);
            matSimulata[x][y].setOccupato(false);
            if(controlloScacco(x+1,y,re.getColore(),matSimulata)==0)
                    scacco=true;
            else
                return true;
        }
        
        //provo in basso a sinistra
        if(y<8 && x>0){ 
            matSimulata=matrice;
            matSimulata[x-1][y+1].setOccupante(new Re(x-1,y+1,re.getColore()));
            matSimulata[x][y].setOccupante(null);
            matSimulata[x][y].setOccupato(false);
            if(controlloScacco(x-1,y+1,re.getColore(),matSimulata)==0)
                    scacco=true;
            else
                return true;
        }
        
        //provo in basso
        if(y<8){ 
            matSimulata=matrice;
            matSimulata[x][y+1].setOccupante(new Re(x,y+1,re.getColore()));
            matSimulata[x][y].setOccupante(null);
            matSimulata[x][y].setOccupato(false);
            if(controlloScacco(x,y+1,re.getColore(),matSimulata)==0)
                    scacco=true;
            else
                return true;
        }
        
        //provo in basso a destra
        if(y<8 && x<8){ 
            matSimulata=matrice;
            matSimulata[x+1][y+1].setOccupante(new Re(x+1,y+1,re.getColore()));
            matSimulata[x][y].setOccupante(null);
            matSimulata[x][y].setOccupato(false);
            if(controlloScacco(x+1,y+1,re.getColore(),matSimulata)==0)
                    scacco=true;
            else
                return true;
        }
        return false; 
    }
    
    public int getTurno(){    
        return turno;
    }
    
    public void setInterfacciaGrafica( InterfacciaGrafica i ){
        
        ig = i;
    
    }

    public void setMatrice(MatriceDeiPezzi m){
        matrice=m;
    }

    public boolean scaccoMatto() {
        System.err.println("DEBUG: scaccoMatto non implementato");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return false;
    }
    
    public Spazio[][] getMatriceSpazi(){
        
        return m;
        
    }
    
}