package model;

/**
 *
 * @author Viktor
 */

public class Spazio{ // Cella Della Scacchiera Caratterizzata Dalla Presenza O Non Di Una Pedina E Del Suo Tipo
    
    private boolean occupato;
    private Pezzo occupante;
    private int x, y;
    
    public Spazio(){
        
        occupato = false;
    
    }
    
    public Spazio( int x,int y ){
        
        occupato = false;
        this.x = x;
        this.y = y;
    
    }
    
    public Spazio( int x, int y, Pezzo p ){
        
        occupato = true;
        this.x = x;
        this.y = y;
        occupante = p;
    
    }
    
    public Spazio( Pezzo p ){
        
        occupante = p;
        x = p.getX();
        y = p.getY();
        occupato = true;
    
    }
    
    public boolean eOccupato(){
        
        return occupato;
    
    }
    
    public Pezzo getOccupante(){
        
        return occupante;
    
    }
    
    public void setOccupante( Pezzo p ){
        
        occupante = p;
    
    }
    
    public void setOccupato(boolean b){
        occupato=b;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y=y;
    }

}