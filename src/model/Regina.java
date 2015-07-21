package model;

/**
 * Classe della regina. Tutte le classi dei pezzi definiscono oggetti che 
 * vengono presi in esame nelle classi del package "contoller"
 * @author Viktor
 */

public class Regina extends Pezzo{
    
    private int x, y; // Coordinate ( -1, -1 ) = Fuori Dal Campo = Distrutto
    private Colore colore;
    
    public Regina( int x, int y, Colore colore ){
        
        super( x, y, colore );
    
    }
    
    public Regina( int x, int y, int colore ){
        
        super( x, y, colore );
    
    }

}