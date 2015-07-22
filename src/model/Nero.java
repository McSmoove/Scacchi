package model;

/**
 * Classe del colore nero. Tutte le classi dei colori definiscono oggetti che 
 * vengono presi in esame durante i controlli nelle classi del package 
 * "contoller"
 * @author Viktor
 */

public class Nero extends Colore{
    
    private final int colore;
    
    public Nero(){
        
        colore = Colore.NERO;
    
    }
    
    @Override
    public boolean equals( Object o ){
        
        if( o instanceof Nero ){ // If Rindondante
            
            return true;
        
        } else {
            
            return false;
        
        }
    
    }

    @Override
    public int hashCode(){ // La Equals Vuole La hashCode()
        return colore;
    }
    
    @Override
    public String toString(){
        return "NERO";
    }

}