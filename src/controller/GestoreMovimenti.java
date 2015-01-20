package controller;

import model.Alfiere;
import model.Bianco;
import model.MatriceDeiPezzi;
import model.Nero;
import model.Pedone;
import model.Pezzo;
import model.Spazio;
import model.Torre;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Viktor
 */


public class GestoreMovimenti {
    MatriceDeiPezzi matrice; //collegamento con la matrice che salva le posizioni di tutti i pezzi
    Spazio[][] m;
    private final int MAXLENGTH=7;
    
    public GestoreMovimenti(MatriceDeiPezzi matrice){
        this.matrice=matrice;
        m=matrice.getMatrice();
        //da implementare gli altri collegamenti MCV
    }
    
    //chiamata dopo ogni mossa effettuata
    public void aggiornaMatriceDeiPezzi(MatriceDeiPezzi nuova){
        matrice=nuova;
        m=matrice.getMatrice();
    }
    
    //ritorna la matrice con le posizioni dove il pezzo potrebbe spostarsi
    public int[][] getPossibiliMovimenti(Pezzo p){
        if (p instanceof Pedone) 
            return movimentiPedone((Pedone) p);
        else
            if(p instanceof Torre)
                return movimentiTorre((Torre) p);
            else 
                if(p instanceof Alfiere)
                    return movimentiAlfiere((Alfiere) p);
            
        return null;//da implementare il resto
    }
    
    public int[][] movimentiPedone(Pedone p){
        int[][] scacchiera=new int[8][8];
        int x,y;
        x=p.getX();
        y=p.getY();
        
        if(p.getColore() instanceof Bianco){
            //immediatamente sopra
            if(!m[x][y+1].isBusy())
                scacchiera[x][y+1]=1;
            
            //in alto a sinistra
            if((x-1)>=0)//controllo posizione valida(probabilmente ridondante)
                if(m[x-1][y+1].isBusy() && m[x-1][y+1].getPezzo().getColore() instanceof Nero )
                    scacchiera[x-1][y+1]=1;
            
            //in alto a destra
            if((x+1)<=7)//controllo posizione valida (probabilmente ridondante)
                if(m[x+1][y+1].isBusy() && m[x+1][y+1].getPezzo().getColore() instanceof Nero )
                    scacchiera[x+1][y+1]=1;
            
        }     
        //manca il controllo di quando supero una pedina con un'altra (opzionale)
        //mancano controlli che impediscono la mossa nel caso di scacco
        
        //caso del nero
        else{
            if(!m[x][y-1].isBusy())
                scacchiera[x][y-1]=1;
            if(m[x-1][y-1].isBusy() && m[x-1][y-1].getPezzo().getColore() instanceof Nero )
                scacchiera[x+1][y-1]=1;
            if(m[x+1][y-1].isBusy() && m[x+1][y-1].getPezzo().getColore() instanceof Nero )
                scacchiera[x+1][y-1]=1;
        }
        return scacchiera;
    }

    private int[][] movimentiTorre(Torre torre) {
        int[][] scacchiera=new int[8][8];
        int x,y;
        int temp;
        x=torre.getX();
        y=torre.getY();
        
        
        //direzione verso destra
        if(x+1<=MAXLENGTH){
            for(temp=x+1;temp<=MAXLENGTH && !(m[temp][y].isBusy());temp++){
                scacchiera[temp][y]=1;
            }
            temp++;
            if(temp<=MAXLENGTH)//controllo per non uscire fuori dalla scachiera
                //se lo spazzio è occupato e contiene un pezzo del colore opposto
                if(m[temp][y].isBusy()&&(!m[temp][y].getPezzo().getColore().equals(torre.getColore())))
                    scacchiera[temp][y]=1;
        }
        
        //direzione verso sinistra
        if(x-1>=0){
            for(temp=x-1;temp>=0 && !(m[temp][y].isBusy());temp--){
                scacchiera[temp][y]=1;
            }
            temp--;
            if(temp>=0)//controllo per non uscire fuori dalla scachiera
                //se lo spazzio è occupato e contiene un pezzo del colore opposto
                if(m[temp][y].isBusy()&&(!m[temp][y].getPezzo().getColore().equals(torre.getColore())))
                    scacchiera[temp][y]=1;
        }
        
        //direzione verso l'alto
        if(y+1<=MAXLENGTH){
            for(temp=y+1;temp<=MAXLENGTH && !(m[x][temp].isBusy());temp++){
                scacchiera[x][temp]=1;
            }
            temp++;
            if(temp<=MAXLENGTH)//controllo per non uscire fuori dalla scachiera
                //se lo spazzio è occupato e contiene un pezzo del colore opposto
                if(m[x][temp].isBusy()&&(!m[x][temp].getPezzo().getColore().equals(torre.getColore())))
                    scacchiera[x][temp]=1;
        }
        
        //direzione verso il bsso
        if(y-1>=0){
            for(temp=y-1;temp>=0 && !(m[x][temp].isBusy());temp++){
                scacchiera[x][temp]=1;
            }
            temp--;
            if(temp>=0)//controllo per non uscire fuori dalla scachiera
                //se lo spazzio è occupato e contiene un pezzo del colore opposto
                if(m[x][temp].isBusy()&&(!m[x][temp].getPezzo().getColore().equals(torre.getColore())))
                    scacchiera[x][temp]=1;
        }
        
        return scacchiera;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int[][] movimentiAlfiere(Alfiere alfiere) {
        int[][] scacchiera=new int[8][8];
        int x,y;
        int temp1,temp2;
        x=alfiere.getX();
        y=alfiere.getY();
        
        
        //while()
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
