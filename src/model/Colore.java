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
public interface Colore {
    public static final int BIANCO=1;
    public static final int NERO=-1;
    
    @Override
    public abstract boolean equals(Object o);
    
}
