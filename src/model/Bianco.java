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
public class Bianco extends Colore{
    private int colore;
    
    public Bianco(){
        colore=Colore.BIANCO;
    }
    
    public boolean equals(Object o){
        if(o instanceof Bianco)
            return true;
        return false;
    }
    
}
