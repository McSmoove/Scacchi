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
    private final int BIANCO=1;
    private final int NERO=-1;
    private Spazio[][] scacchiera;//la matrice della scacchiera con ogni cella di tipo Spazio
    
    public MatriceDeiPezzi(){
        scacchiera = new Spazio[8][8];
        //popolare la scacchiera con la disposizione iniziale
        scacchiera[0][0]=new Spazio(new Torre(0,0,NERO));
        scacchiera[7][0]=new Spazio(new Torre(7,0,NERO));
        scacchiera[1][0]=new Spazio(new Cavallo(1,0,NERO));
        scacchiera[6][0]=new Spazio(new Cavallo(6,0,NERO));
        scacchiera[2][0]=new Spazio(new Alfiere(2,0,NERO));
        scacchiera[5][0]=new Spazio(new Alfiere(5,0,NERO));
        scacchiera[3][0]=new Spazio(new Regina(3,0,NERO));
        scacchiera[4][0]=new Spazio(new Re(4,0,NERO));
        
        for(int i=0;i<8;i++){
            scacchiera[i][1]=new Spazio(new Pedone(i,1,NERO));
        }
        
        scacchiera[0][7]=new Spazio(new Torre(0,7,BIANCO));
        scacchiera[7][7]=new Spazio(new Torre(7,7,BIANCO));
        scacchiera[1][7]=new Spazio(new Cavallo(1,7,BIANCO));
        scacchiera[6][7]=new Spazio(new Cavallo(6,7,BIANCO));
        scacchiera[2][7]=new Spazio(new Alfiere(2,7,BIANCO));
        scacchiera[5][7]=new Spazio(new Alfiere(5,7,BIANCO));
        scacchiera[3][7]=new Spazio(new Regina(3,7,BIANCO));
        scacchiera[4][7]=new Spazio(new Re(4,7,BIANCO));
        
        for(int i=0;i<8;i++){
            scacchiera[i][6]=new Spazio(new Pedone(i,6,BIANCO));
        }
        
        for(int i=2;i<6;i++){
            for(int j=0;j<8;j++){
                scacchiera[j][i]=new Spazio();
            }
        }
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
