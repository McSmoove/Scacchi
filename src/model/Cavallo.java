package model;

/**
 * Classe del cavallo. Tutte le classi dei pezzi definiscono oggetti che 
 * vengono presi in esame nelle classi del package "contoller"
 * @author Viktor
 */

public class Cavallo extends Pezzo{
    
    private int x, y; // Coordinate ( -1, -1 ) = Fuori Dal Campo = Distrutto
    private Colore colore;
    

    public Cavallo( int x, int y, Colore colore ){
        
        super( x, y, colore );
    
    }
    
    public Cavallo( int x, int y, int colore ){
        
        super( x, y, colore );
    
    }

}