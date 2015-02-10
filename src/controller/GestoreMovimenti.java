package controller;


import model.Alfiere;
import model.Bianco;
import model.Cavallo;
import model.MatriceDeiPezzi;
import model.Nero;
import model.Pedone;
import model.Pezzo;
import model.Regina;
import model.Spazio;
import model.Torre;

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
                else
                    if (p instanceof Cavallo)
                        return movimentiCavallo((Cavallo)p);
                    else
                        if (p instanceof Regina)
                            return movimentiRegina((Regina)p);
            
        return null;//da implementare il resto (altri sottoclassi di pezzo)
    }
    
    
    //metodo per controllare il pedone
    //manca il controllo di quando supero una pedina con un'altra (opzionale)
    //mancano controlli che impediscono la mossa nel caso di scacco
    //manca il controllo ultima riga -> trasformazione in un altro pezzo (solo dopo il movimento)
    //non controllo l'ultima riga xke in quella il pezzo si trasforma e cambiano le sue regole
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
            if((x+1)<=MAXLENGTH)//controllo posizione valida (probabilmente ridondante)
                if(m[x+1][y+1].isBusy() && m[x+1][y+1].getPezzo().getColore() instanceof Nero )
                    scacchiera[x+1][y+1]=1;
            
        }     
        
        
        //caso del nero
        else{
            if(!m[x][y-1].isBusy())
                scacchiera[x][y-1]=1;
            if((x-1)>=0)//controllo posizione valida(probabilmente ridondante)
                if(m[x-1][y-1].isBusy() && m[x-1][y-1].getPezzo().getColore() instanceof Nero )
                    scacchiera[x+1][y-1]=1;
            if((x+1)<=7)//controllo posizione valida (probabilmente ridondante)
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
            //spazi liberi a destra
            for(temp=x+1;temp<=MAXLENGTH && !(m[temp][y].isBusy());temp++){
                scacchiera[temp][y]=1;
            }
            //primo spazio occupato a destra
            temp++;
            if(temp<=MAXLENGTH)//controllo per non uscire fuori dalla scachiera
                //se lo spazzio è occupato e contiene un pezzo del colore opposto
                //controllo isBusy() obsoleto
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
        temp1=x+1;
        temp2=y+1;
        
        //direzione verso alto a destra
        //spazi verso alto a destra liberi
        while(temp1<=MAXLENGTH && temp2<=MAXLENGTH && !m[temp1][temp2].isBusy()){
            scacchiera[temp1][temp2]=1;
            temp1++;
            temp2++;
        }
        //il primo spazio occupato
        //incremento effettuato dento il ciclo while
        //temp1++;
        //temp2++;
        //potrebbe essersi verificata la terza condizione del ciclo while ma non le prime 2
        if(temp1<=MAXLENGTH)
            if(temp2<=MAXLENGTH)
                //se lo spazzio è occupato e contiene un pezzo del colore opposto
                if(m[temp1][temp2].isBusy()&&(!m[temp1][temp2].getPezzo().getColore().equals(alfiere.getColore())))
                    scacchiera[temp1][temp2]=1;
        
        //direzione verso basso destra
        temp1=x+1;
        temp2=y-1;
        while(temp1<=MAXLENGTH && temp2>=0 && !m[temp1][temp2].isBusy()){
            scacchiera[temp1][temp2]=1;
            temp1++;
            temp2--;
        }
        if(temp1<=MAXLENGTH)
            if(temp2>=0)
                //se lo spazzio è occupato e contiene un pezzo del colore opposto
                if(m[temp1][temp2].isBusy()&&(!m[temp1][temp2].getPezzo().getColore().equals(alfiere.getColore())))
                    scacchiera[temp1][temp2]=1;
        
        //direzione alto sinistra
        temp1=x-1;
        temp2=y+1;
        while(temp1>=0 && temp2<=MAXLENGTH && !m[temp1][temp2].isBusy()){
            scacchiera[temp1][temp2]=1;
            temp1--;
            temp2++;
        }
        if(temp1>=0)
            if(temp2<=MAXLENGTH)
                //se lo spazzio è occupato e contiene un pezzo del colore opposto
                if(m[temp1][temp2].isBusy()&&(!m[temp1][temp2].getPezzo().getColore().equals(alfiere.getColore())))
                    scacchiera[temp1][temp2]=1;
        
        //direzione basso sinistra
        temp1=x-1;
        temp2=y-1;
        while(temp1>=0 && temp2>=0 && !m[temp1][temp2].isBusy()){
            scacchiera[temp1][temp2]=1;
            temp1--;
            temp2--;
        }
        if(temp1>=0)
            if(temp2>=0)
                //se lo spazzio è occupato e contiene un pezzo del colore opposto
                if(m[temp1][temp2].isBusy()&&(!m[temp1][temp2].getPezzo().getColore().equals(alfiere.getColore())))
                    scacchiera[temp1][temp2]=1;
        
        return scacchiera;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //da migliorare i controlli delle posizioni permesse ragruppando gli if
    private int[][] movimentiCavallo(Cavallo cavallo) {
        int[][] scacchiera=new int[8][8];
        int x,y;
        int temp;
        x=cavallo.getX();
        y=cavallo.getY();
        //alto destra
        if(x+1<=MAXLENGTH && y+2<=MAXLENGTH)
            //libero o dell'avversario
            if (!m[x+1][y+2].isBusy() || (m[x+1][y+2].isBusy() && !m[x+1][y+2].getPezzo().getColore().equals(cavallo.getColore()) ))
                scacchiera[x+1][y+2]=1;
        
        //alto sinistra
        if(x-1>=0 && y+2<=MAXLENGTH)
            //libero o dell'avversario
            if (!m[x-1][y+2].isBusy() || (m[x-1][y+2].isBusy() && !m[x-1][y+2].getPezzo().getColore().equals(cavallo.getColore()) ))
                scacchiera[x-1][y+2]=1;
        
        //destra alto
        if(x+2<=MAXLENGTH && y+1<=MAXLENGTH)
            //libero o dell'avversario
            if (!m[x+2][y+1].isBusy() || (m[x+2][y+1].isBusy() && !m[x+2][y+1].getPezzo().getColore().equals(cavallo.getColore()) ))
                scacchiera[x+2][y+1]=1;
        
        //destra basso
        if(x+2<=MAXLENGTH && y-1>=0)
            //libero o dell'avversario
            if (!m[x+2][y-1].isBusy() || (m[x+2][y-1].isBusy() && !m[x+2][y-1].getPezzo().getColore().equals(cavallo.getColore()) ))
                scacchiera[x+2][y-1]=1;
        
        
        //sinistra alto
        if(x-2>=0 && y+1<=MAXLENGTH)
            //libero o dell'avversario
            if (!m[x-2][y+1].isBusy() || (m[x-2][y+1].isBusy() && !m[x-2][y+1].getPezzo().getColore().equals(cavallo.getColore()) ))
                scacchiera[x-2][y+1]=1;
        
        //sinistra basso
        if(x-2>=0 && y-1>=0)
            //libero o dell'avversario
            if (!m[x-2][y-1].isBusy() || (m[x-2][y-1].isBusy() && !m[x-2][y-1].getPezzo().getColore().equals(cavallo.getColore()) ))
                scacchiera[x-2][y-1]=1;
        
        //basso desra
        if(x+1<=MAXLENGTH && y-2>=0)
            //libero o dell'avversario
            if (!m[x+1][y-2].isBusy() || (m[x+1][y-2].isBusy() && !m[x+1][y-2].getPezzo().getColore().equals(cavallo.getColore()) ))
                scacchiera[x+1][y-2]=1;
        
        //basso sinistra
        if(x-1>=0 && y-2>=0)
            //libero o dell'avversario
            if (!m[x-1][y-2].isBusy() || (m[x-1][y-2].isBusy() && !m[x-1][y-2].getPezzo().getColore().equals(cavallo.getColore()) ))
                scacchiera[x-1][y-2]=1;
        
        
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return scacchiera;
    }

    private int[][] movimentiRegina(Regina regina) {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
