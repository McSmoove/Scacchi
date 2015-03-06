package controller;

import java.util.LinkedList;
import model.*;
import view.InterfacciaGrafica;

/**
 *
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
    
    public void aggiornaMatriceDeiPezzi( MatriceDeiPezzi nuova ){ // Chiamata Fatta Dopo Ogni Mossa Effettuata
        
        matrice = nuova;
        m = matrice.getMatrice();
    
    }
    
    public MatriceDeiPezzi getMatrice(){
        
        return matrice;
    
    }
    
    // Ritorna La Matrice Con Le Posizioni Mosse Dove Un Pezzo Puo Spostarsi
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
        
        return reNero;
    
    }
    
    public Re getReBianco(){
        
        return reBianco;
    
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
            if( !m[ x ][ y + 1 ].eOccupato() ){
                
                scacchiera[ x ][ y + 1 ] = 1;
            
            }
            
            // In Alto A Sinistra
            if( ( x - 1 ) >= 0 ){ // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x - 1 ][ y + 1 ].eOccupato() && m[ x - 1][ y + 1 ].getOccupante().getColore() instanceof Nero ){
                    
                    scacchiera[ x - 1 ][ y + 1 ] = 1;
                
                }
            
            }

            // In Alto A Destra
            if( ( x + 1 ) <= MAXLENGTH ){ // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x + 1 ][ y + 1 ].eOccupato() && m[ x + 1 ][ y + 1 ].getOccupante().getColore() instanceof Nero ){
                    
                    scacchiera[ x + 1 ][ y + 1 ] = 1;
                
                }
            
            }
        
        } else { // Fine IstanceOf Bianco, Inizio Caso IstanceOf Nero
            
            if( !m[ x ][ y - 1 ].eOccupato()){
                
                scacchiera[ x ][ y - 1 ] = 1;
            
            }
            
            if( ( x - 1 ) >= 0 ) // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x - 1 ][ y - 1 ].eOccupato() && m[ x - 1 ][ y - 1 ].getOccupante().getColore() instanceof Nero ){
                    
                    scacchiera[ x + 1 ][ y - 1 ] = 1;
                
                }
            
            if( ( x + 1 ) <= MAXLENGTH ){ // Controllo Posizione Valida ( Probabilmente Ridondante )
                
                if( m[ x + 1 ][ y - 1 ].eOccupato() && m[ x + 1 ][ y - 1 ].getOccupante().getColore() instanceof Nero ){
                    
                    scacchiera[ x + 1 ][ y - 1 ] = 1;
                
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
        
        int[][] scacchiera = new int[ 8 ][ 8 ];
        int x, y;
        
        x = re.getX();
        y = re.getY();
        
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

        // Da Implementare Ancora I Controlli Dello Scacco Inverso / Pericolo
        for( int i = 0; i <= MAXLENGTH; i++ ){
            
            for( int j = 0; j <= MAXLENGTH; j++ ){
                
                if( scacchiera[ i ][ j ] == 1 ){
                    
                    scacchiera[ i ][ j ] = controlloScacco( i, j, re.getColore(),m );
                
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
                        if( ( controlloScacco( x - 1, y, re.getColore(),m ) == 1 ) ){
                            
                            if( ( controlloScacco( x - 2, y, re.getColore(),m ) == 1 ) ){
                                
                                scacchiera[ x - 2 ][ y ] = 1;
                            
                            }
                        
                        }
                    
                    }
                
                }
            
            }

            // Gli Spazi Tra Re E Torre Destra Sono Liberi?
            if( !m[ 5 ][ re.getY() ].eOccupato() && !m[ 6 ][ re.getY() ].eOccupato() ){

                // A Sinistra Ce Uma Torre Nella Sua Posizione Originale
                if( m[ MAXLENGTH ][ re.getY() ].getOccupante() instanceof Torre ){

                    // Torre A Sinistra Mai Mossa
                    if( !( ( Torre ) m[ MAXLENGTH ][ re.getY() ].getOccupante()).isMoved() ){

                        // Controllo Se Tutti Gli Spazi Tra La Posizione Attuale Del Re E Quella Finale Non E Sotto Scacco
                        if( ( controlloScacco( x + 1, y, re.getColore(),m ) == 1 ) ){
                            
                            if( ( controlloScacco( x + 2, y, re.getColore(),m ) == 1 ) ){
                                
                                scacchiera[ x + 2 ][ y ] = 1;
                            
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
    public int controlloScacco( int x, int y, Colore colore,Spazio[][] matrix){
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
        
        if( temp1 <= MAXLENGTH && temp2 >= 0 && !mat[ temp1 ][ temp2 ].getOccupante().getColore().equals( colore ) ){
            
            if( mat[ temp1 ][ temp2 ].getOccupante() instanceof Alfiere || mat[ temp1 ][ temp2 ].getOccupante() instanceof Regina ){
                
                return 0;
            
            }
        
        }

        // Controlli Per Il Pedone ( Potrebbe Essere Integrato Nell'Alfiere In Casi Specifici )
        
        if( colore instanceof Nero ){ // Inizio Controllo Pedoni Neri
            
            if( x + 1 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    return 0;
                
                }
            
            }
            
            if( x - 1 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 1 ][ y + 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    return 0;
                
                }
            
            }
        
        } else { // Inizio Controllo Pedoni Bianchi
            
            if( x + 1 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    return 0;
                
                }
            
            }
            
            if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Pedone ){
                
                if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) ){
                    
                    return 0;
                
                }
            
            }
        
        }

        // Controlli Del Cavallo

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
        if( x + 1 <= MAXLENGTH && y - 2 <= MAXLENGTH && mat[ x + 1 ][ y - 2 ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                
                return 0;
            
            }
        
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 2 >= 0 && mat[ x - 1 ][ y - 2 ].eOccupato() ){
            
            if( !mat[ x - 1 ][ y - 2 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 2 ].getOccupante() instanceof Cavallo ){
                
                return 0;
            
            }
        
        }

        // Controllo Re Adiacente

        // Destra
        if( x + 1 <= MAXLENGTH && mat[ x + 1 ][ y ].eOccupato() ){
            
            if( !mat[ x + 1 ][ y ].getOccupante().getColore().equals( colore ) && m[ x + 1 ][ y ].getOccupante() instanceof Re ){
                
                return 0;
            
            }
        
        }

        // Sinistra
        if( x - 1 >= 0 && mat[ x - 1 ][ y ].eOccupato() ){          
            if( !mat[ x - 1 ][ y ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y ].getOccupante() instanceof Re ){
                return 0;
            }
        }

        // Alto
        if( y + 1 <= MAXLENGTH && m[ x ][ y + 1 ].eOccupato() ){ 
            if( !mat[ x ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y + 1 ].getOccupante() instanceof Re ){             
                return 0;
            }
        }

        // Basso
        if( y - 1 >= 0 && mat[ x ][ y - 1 ].eOccupato() ){
            if( !mat[ x ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x ][ y - 1 ].getOccupante() instanceof Re ){
                return 0;
            }
        }

        // In Alto A Destra
        if( x + 1 <= MAXLENGTH && y + 1 <= MAXLENGTH && mat[ x + 1 ][ y + 1 ].eOccupato() ){
            if( !mat[ x + 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                return 0;
            }
        }

        // In Alto A Sinistra
        if( x - 1 >= 0 && y + 1 <= MAXLENGTH && mat[ x - 1 ][ y + 1 ].eOccupato() ){
            if( !mat[ x - 1 ][ y + 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y + 1 ].getOccupante() instanceof Re ){ 
                return 0;
            }
        }

        // In Basso A Destra
        if( x + 1 <= MAXLENGTH && y - 1 >= 0 && mat[ x + 1 ][ y - 1 ].eOccupato() ){
            if( !mat[ x + 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x + 1 ][ y - 1 ].getOccupante() instanceof Re ){      
                return 0;
            }
        }

        // In Basso A Sinistra
        if( x - 1 >= 0 && y - 1 >= 0 && mat[ x - 1 ][ y - 1 ].eOccupato() ){         
            if( !mat[ x - 1 ][ y - 1 ].getOccupante().getColore().equals( colore ) && mat[ x - 1 ][ y - 1 ].getOccupante() instanceof Re ){   
                return 0;
            }
        }
        
        return 1;
        // Throw New UnsupportedOperationException
    
    }
    
    public int controlloScacco(Re r) {
        
        return controlloScacco( r.getX(), r.getY(), r.getColore(),m );
    
    }
    
    public LinkedList<Pezzo> getPezziAttaccantiIlRe(Re r){
        Re re=r;
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    
    public int[][] getMatricePezziChePrevengonoScacco(int xRe,int yRe,MatriceDeiPezzi matrice,Colore turno){
        MatriceDeiPezzi originale=matrice;
        int[][] matricePosizioni=new int[8][8];
        
        //azzero la matrice per sicurezza
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                matricePosizioni[i][j]=0;
            }
        }
        
        
        
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){         
                if(j!=xRe && i!=yRe)
                    if(originale.getSpazio(j, i).eOccupato())
                        //prendo tutti i pezzi del colore giusto
                        if(originale.getSpazio(j, i).getOccupante().getColore().equals(turno))
                            matricePosizioni[j][i]=1;
            }
        }
        
        //scarto i pezzi che non possono salvare il re (e da qualche altra parte controllerò se la posizione sclta da loro va bene)
        
        //forse utilizzo l'algoritmo + efficace che non usa combinazioni inutili
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    
    public int getTurno(){
        
        return turno;
    
    }
    
    public void setInterfacciaGrafica( InterfacciaGrafica i ){
        
        ig = i;
    
    }

    
}