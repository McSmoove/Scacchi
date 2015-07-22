package model;

/** 
 * Classe della torre. Tutte le classi dei pezzi definiscono oggetti che 
 * vengono presi in esame durante i controlli nelle classi del package 
 * "contoller"
 * @author Viktor
 */

public class Torre extends Pezzo{
    
    private int x, y; // Coordinate ( -1, -1 ) = Fuori Dal Campo = Distrutto
    private Colore colore;
    private boolean maiMossa = true; // Controllo Per Il Primo Movimento (A CHE SERVE)
    
    public Torre( int x, int y, Colore colore ){
        
        super( x, y, colore );
    
    }
    
    public Torre( int x, int y, int colore ){
        
        super( x, y, colore );
    
    }
    
    public boolean isMoved(){
        return !maiMossa;
    }
    
    public void setMoved(){
        maiMossa=false;
    }
    
    public void moved( boolean m ){
        
        maiMossa = m;
    
    }

}