/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Viktor
 */
public class Regina extends Pezzo{
    private int x,y;//coordinate (-1,-1)=fuori dal campo=distrutto
    private Colore colore;
    

    public Regina(int x, int y, Colore colore) {
        super(x, y, colore);
    }
    
    public Regina(int x, int y, int colore) {
        super(x, y, colore);
    }
}