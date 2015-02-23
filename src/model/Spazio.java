package model;


import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Viktor
 */

//cella della scacchiera caratterizzata dalla presenza o l'assenza di una pedina
//e del tipo di pedina stessa
public class Spazio{
    private boolean occupato;
    private Pezzo occupante;
    private int x,y; //forse servirà ma potrebbe essere ridondante con l'analogia dei pezzi
    //da finire
    
    public boolean isBusy(){
        return occupato;
    }
    
    public Pezzo getPezzo(){
        return occupante;
    }
    
    public void setPezzo(Pezzo p){
        occupante=p;
    }
}
