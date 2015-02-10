package model;

import com.oracle.jrockit.jfr.InvalidValueException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Viktor
 */
public abstract class Colore {
    public static final int BIANCO=1;
    public static final int NERO=-1;
    private int colore;
    
    public Colore(int c) throws InvalidValueException{
        if(c==BIANCO)
            colore=BIANCO;
        else
            if(c==NERO)
                colore=NERO;
            else
                throw new InvalidValueException("Colore non valido");
    }
    
    @Override
    public boolean equals(Object o){
        Colore c;
        if(o instanceof Colore){
            c=(Colore)o;
            if(c.getColore()==this.colore)
                return true;
            else return false;
        }
        return false;
    }
    
    public int getColore(){
        return colore;
    }
    
}
