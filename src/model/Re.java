package model;

/**
 *
 * @author Viktor
 */

public class Re extends Pezzo{
    
    private int x, y; // Coordinate ( -1, -1 ) = Fuori Dal Campo = Distrutto ( IL RE NON PUO ESSERE DISTRUTTO )
    private Colore colore;
    private boolean maiMosso = true; // Controllo Per Il Primo Movimento ( PERCHE )
    
    public Re( int x, int y, Colore colore ){
        
        super( x, y, colore );
    
    }
    
    public Re( int x, int y,int colore ){
        
        super( x, y, colore );
    
    }
    
    public boolean isMoved(){
        
        return maiMosso;
    
    }
    
    public void moved( boolean m ){
        
        maiMosso = m;
    
    }

}