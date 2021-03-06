package model;

/**
 * La classe spazio  essenziale per sapere quale pezzo sta occupando una 
 * casella di gioco o se la casella è libera
 * @author Viktor
 */

public class Spazio{ // Cella Della Scacchiera Caratterizzata Dalla Presenza O Non Di Una Pedina E Del Suo Tipo
    
    private boolean occupato;
    private Pezzo occupante;
    private int x, y;
    
    public Spazio(){
        
        occupato = false;
    
    }
    
    public Spazio(Spazio s){
        this.x=s.getX();
        this.y=s.getY();
        if(s.eOccupato()){
            occupato=true;
            occupante=s.getOccupante();
        }
        else
            occupato=false;
        
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
    
    public void setOccupante( Pezzo p,int x,int y ){      
        occupante = p;
        this.x=x;
        this.y=y;
        p.setX(x);
        p.setY(y);
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