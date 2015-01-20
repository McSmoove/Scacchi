package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Viktor
 */
public abstract class Pezzo {
    private int x,y;//coordinate
    private Colore colore;
    
    public Pezzo(int x,int y,Colore colore){
        this.x=x;
        this.y=y;
        this.colore=colore;
    
    }

    //da implementare
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Colore getColore(){
        return colore;
    }
    
    //metodo chiamato alla distruzione di un pezzo
    public void distruggi(){
        x=-1;
        y=-1;
    }
}
