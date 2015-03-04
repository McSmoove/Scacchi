package model;

/**
 *
 * @author Viktor
 */

public abstract class Colore{
    
    private int colore;
    public static final int BIANCO = 1;
    public static final int NERO = -1;
    
    @Override
    public boolean equals( Object o ){
        
        Colore c;
        
        if( o instanceof Colore ){
            
            c = ( Colore )o;
            
            if( c.getColore() == this.colore ){ // If Rindondante
                
                return true;
            
            } else {
                
                return false;
            
            }
        }
        
        return false;
    
    }

    @Override
    public int hashCode(){ // La Equals Vuole La hashCode()
        
        return this.colore;
    
    }
    
    public int getColore(){
        
        return colore;
    
    }

}