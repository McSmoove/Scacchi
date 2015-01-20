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
public class Pedone extends Pezzo {
    private int x,y;//coordinate (-1,-1)=fuori dal campo=distrutto
    private Colore colore;
    private boolean maiMossa=true; //controllo per il primo movimento

    public Pedone(int x, int y, Colore colore) {
        super(x, y, colore);
    }
    
    
}
