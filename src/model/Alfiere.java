package model;

/**
 *
 * @author Viktor
 */

public class Alfiere extends Pezzo{
    
    private int x, y; // Coordinate ( -1, -1 ) = Fuori Dal Campo = Distrutto
    private Colore colore;
    
    public Alfiere( int x, int y, Colore colore ){
        
        super( x, y, colore );
    
    }
    
    public Alfiere( int x, int y, int colore ){
        
        super( x, y, colore );
    
    }
    
}