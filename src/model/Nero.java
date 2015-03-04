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
public class Nero extends Colore{
    private int colore;
    public Nero(){
        colore=Colore.NERO;
    }
    
    public boolean equals(Object o){
        if(o instanceof Nero)
            return true;
        return false;
    }
}
