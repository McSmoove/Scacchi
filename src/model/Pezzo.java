package model;

/**
 *
 * @author Viktor
 */

public abstract class Pezzo{
    
    private int x, y; // Coordinate
    private Colore colore;
    
    public Pezzo( int x, int y, Colore colore ){
        
        this.x = x;
        this.y = y;
        this.colore = colore;
    
    }
    
    public Pezzo( int x, int y, int colore ){
        
        this.x = x;
        this.y = y;
        
        if( colore == 1 ){
            
            this.colore = new Bianco();
        
        } else {
            
            if( colore == -1 ){
                
                this.colore = new Nero();
            
            } else {

            // Colore non valido
            
            }
        
        }
    
    }

    // Da implementare
    public int getX(){
        
        return x;
    
    }
    
    public int getY(){
        
        return y;
    
    }
    
    public Colore getColore(){
        
        return colore;
    
    }
    
    public void distruggi(){ // Metodo Chiamato Alla Distruzione Di Un Pezzo
        
        x = -1;
        y = -1;
    
    }

}