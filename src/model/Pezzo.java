package model;

import controller.GestoreMovimenti;
import static java.lang.Math.abs;
import view.InterfacciaGrafica;

/**
 *
 * @author Viktor
 */

public abstract class Pezzo{
    
    private int x, y; // Coordinate
    private Colore colore;
    
    public Pezzo( int x, int y, Colore colore ){
        
        this.x = x;
        this.y = y;
        this.colore = colore;
    
    }
    
    public Pezzo( int x, int y, int colore ){
        
        this.x = x;
        this.y = y;
        
        if( colore == 1 ){
            
            this.colore = new Bianco();
        
        } else {
            
            if( colore == -1 ){
                
                this.colore = new Nero();
            
            } else {

            // Colore non valido
            
            }
        
        }
    
    }

    // Da implementare
    public int getX(){
        
        return x;
    
    }
    
    public int getY(){
        
        return y;
    
    }
    
    public Colore getColore(){
        
        return colore;
    
    }
    
    // Metodo Chiamato Alla Distruzione Di Un Pezzo
    //(distruggo il pezzo mettendolo in una locazione non valida
    // e aggiorno la lista dei pezzi morti nell'interfaccia grafica)
    public void distruggi(InterfacciaGrafica interfacciaGrafica){ 
        x = -1;
        y = -1;
        interfacciaGrafica.aggiungiPezzoMorto(this);
    }
    
    
    public boolean spostabileIn(int x, int y,Spazio[][] matrice){
        int xp=this.x;
        int yp=this.y;
        int temp;//vriabile temporale per torre
        int temp1;//variabile temèorale per alfiere
        int temp2;// " "
        System.err.print("DEBUG: spostabileIn()");
        Spazio s=matrice[x][y];
        //devo controllare se nella posizione finale c'è uno spazio libero o un pezzo del colore opposto e poi
        //tutti gli spazi intermedi x tuti i tipi di pezzo tranne il cavallo
        
        
        //caso non considerato nei cicli sotto
        if(x==xp && y==yp)
            return true;
        
        
        //controllo se la posizione finale è vuota o contiene un pezzo del colore opposto
        //(non posso spostarmi in un locazione con un pezzo dello stesso colore)
        if(!s.eOccupato() || !s.getOccupante().getColore().equals(this.getColore()))
            //divido i controlli in base al pezzo
            if(this instanceof Torre){
                return percorsoTorre(this,x,y,matrice);
        }
        if(this instanceof Alfiere){
              return percorsoAlfiere(this,x,y,matrice);
        }
        
        if(this instanceof Cavallo){
            //caso base per la verifica successiva
            if(xp!=x && yp!=y){
                //funzione di verifica per la correttezza della posizione
                //(modulo della somma dei 2 delta=3)
                if(abs((double)x-xp)+abs((double)y-yp)==3)
                    return true;
            }
            return false;    
        }
        
        if(this instanceof Regina){
            return percorsoAlfiere(this,x,y,matrice)||percorsoTorre(this,x,y,matrice);
        }
        
        if(this instanceof Pedone){
            System.err.println(" di pedone");
            
            //caso di una cella vuota
            return percorsoPedone(matrice[xp][yp].getOccupante(),x,y,matrice);
        }
        
        if(this instanceof Re){
            System.err.println(" di re");
            return percorsoRe(this,x,y,matrice);
        }
        
        
        return false;
        
    }
    
    private boolean percorsoRe(Pezzo p,int x,int y,Spazio[][] matrice){
        System.err.println("DEBUG: percorsoRe in Pezzo");
        int xp=p.getX();
        int yp=p.getY();
        //escludo il caso base
        
        if(x!=xp || y!=yp){
            //posizioni adiacenti al re
            if((x==xp+1 || x==xp-1 || x==xp)&&(y==yp+1 || y==yp-1 || y==yp)){
                System.err.println("posizione adiacente al re");
                if(!matrice[x][y].eOccupato() ||
                    (matrice[x][y].eOccupato() && !matrice[x][y].getOccupante().getColore().equals(p.getColore())))
                    return true;
            }
            System.err.println("DEBUG: posizione non adiacente al re");
            //arrocco
            //devo aggiungere la condizione
            //secondo la quale le posizioni tra il re e la torre non sono sotto scacco
            //re mai mosso
            if(!((Re)p).isMoved()){
                if(x==xp+2){
                    if(matrice[7][y].eOccupato())
                        if(!((Torre)matrice[7][y].getOccupante()).isMoved())
                            if(!matrice[5][y].eOccupato() && !matrice[6][y].eOccupato())
                                return true;
                }
                if(x==xp-2){
                    if(matrice[0][y].eOccupato())
                        if(!((Torre)matrice[0][y].getOccupante()).isMoved())
                            if(!matrice[1][y].eOccupato() && 
                               !matrice[2][y].eOccupato() &&
                               !matrice[3][y].eOccupato())
                                return true;
                }
            }
            
        }
        
        return false;
    }
    
    private boolean percorsoPedone(Pezzo p,int x,int y,Spazio[][]matrice){
        int xp=p.getX();
        int yp=p.getY();
        if(!matrice[x][y].eOccupato()){
                System.err.println("DEBUG: percorsoPedone()");
                //un pedone può spostari solo in avanti su celle vuote
                if(x==xp){
                    if(this.getColore() instanceof Nero){
                        //si è già mosso
                        if(((Pedone) p).isMoved()){
                            if(y==yp+1)
                                return true;
                        }
                        //p non si è mai mosso
                        else 
                            if(y==yp+1 || y==yp+2){
                                return true;
                        }
                    }
                    //Bianco
                    else{
                        //il pedone si è già mosso
                        if(((Pedone)this).isMoved()){
                            if((y==yp-1) && !matrice[x][y].eOccupato())
                                return true;
                        }
                        //p non si è mai mosso
                        else if(y==yp-1 || y==yp-2){
                            if(!matrice[x][y].eOccupato())
                                return true;
                        }
                    }
                }
                return false;
            }
            //posizione finale occupata da un pezzo avversario
            else{
                //posizione consentita nel range orizzontale
                if(x==xp+1 || x==xp-1){
                    if(this.getColore() instanceof Nero){
                        if(y==yp+1)
                            return true;
                        return false;
                    }
                    if(this.getColore() instanceof Bianco){
                        if(y==yp-1)
                            return true;
                        return false;
                    }
                }
                return false;
            }
        
    }    
        
    private boolean percorsoTorre(Pezzo p,int x,int y,Spazio[][]matrice){
        int xp=p.getX();
        int yp=p.getY();
        int temp;
        //vedo se la posizione in cui spostarsi è valida
        if (x == xp) {
            temp = yp;
            //ciclo bidirezionale
            while (temp != y) {
                if (temp < y) {
                    temp++;
                } else {
                    temp--;
                }
                if (matrice[x][temp].eOccupato()) {
                    return false;
                }
            }
            return true;
        }

        if (y == yp) {
            temp = xp;
            //ciclo bidirezionale
            while (temp != x) {
                if (temp < x) {
                    temp++;
                } else {
                    temp--;
                }
                if (matrice[temp][y].eOccupato()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private boolean percorsoAlfiere(Pezzo p,int x,int y,Spazio[][] matrice){
        int temp1,temp2;
        int xp=p.getX();
        int yp=p.getY();
        //controllo validità posizione
            temp1=xp;
            temp2=yp;
            //primo quadrante e terzo quadrante
            if(((double)(x-xp)/(y-yp))==1){
                //primo quadrante
                if(xp<x){
                    temp1++;
                    temp2++;
                    while(temp1<x){//comprende anche il caso delle y
                        if(matrice[temp1][temp2].eOccupato())
                            return false;
                        temp1++;
                        temp2++;
                    }
                    
                }
                //terzo quadrante
                else if(xp>x){
                    temp1--;
                    temp2--;
                    while(temp1<x){//comprende anche il caso delle y
                        if(matrice[temp1][temp2].eOccupato())
                            return false;
                        temp1--;
                        temp2--;
                    } 
                }
                return true;
            }
            
            //secondo quadrante e quarto quadrante
            if(((double)(x-xp)/(y-yp))==-1){
                if(xp<x){
                    temp1++;
                    temp2--;
                    while(temp1<x){//comprende anche il caso delle y
                        if(matrice[temp1][temp2].eOccupato())
                            return false;
                        temp1++;
                        temp2--;
                    }
                }
                else if(xp>x){
                    temp1--;
                    temp2++;
                    while(temp1>x){//comprende anche il caso delle y
                        if(matrice[temp1][temp2].eOccupato())
                            return false;
                        temp1--;
                        temp2++;
                    }
                }
                return true;
            }
            return false;  
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y=y;
    }


}