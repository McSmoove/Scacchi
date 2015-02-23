package model;


import model.Spazio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Viktor
 */

//La matrice rappresentante la scacchiera
public class MatriceDeiPezzi {
    private Spazio[][] scacchiera;//la matrice della scacchiera con ogni cella di tipo Spazio
    
    public MatriceDeiPezzi(){
        scacchiera = new Spazio[8][8];
    }
    
    public MatriceDeiPezzi(Spazio[][] s){
        scacchiera = s;
    }
    
    public Spazio[][] getMatrice(){
        return scacchiera;
    }
    
    public Spazio getSpazio(int x,int y){
        return scacchiera[x][y];
    }
}
