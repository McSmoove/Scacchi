package model;

/**
 * Classe dell'alfiere
 * Tutte le classi dei pezzi definiscono oggetti che vengono presi in esame 
 * durante i controlli nelle classi del package "contoller"
 * @author Viktor
 */

public class Alfiere extends Pezzo{
    
    private int x, y; // Coordinate ( -1, -1 ) = Fuori Dal Campo = Distrutto
    private Colore colore;
    
    public Alfiere( int x, int y, Colore colore ){
        
        super( x, y, colore );
    
    }
    
    public Alfiere(Colore colore){
        super(-1,-1,colore);
    }
    public Alfiere( int x, int y, int colore ){
        
        super( x, y, colore );
    
    }
    
}