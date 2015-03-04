package model;

/**
 *
 * @author Viktor
 */

public class Pedone extends Pezzo{
    
    private int x, y; // Coordinate ( -1, -1 ) = Fuori Dal Campo = Distrutto
    private Colore colore;
    private boolean maiMossa = true; // Controllo Per Il Primo Movimento
    
    public Pedone( int x, int y, Colore colore ){
        
        super( x, y, colore );
    
    }
    
    public Pedone( int x, int y, int colore ){
        
        super( x, y, colore );
    
    }
    
    
    public boolean isMoved(){
        
        return maiMossa;
    
    }
    
    public void moved( boolean m ){
        
        maiMossa = m;
    
    }

}