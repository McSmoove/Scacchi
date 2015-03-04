package controller;


import model.Alfiere;
import model.Bianco;
import model.Cavallo;
import model.Colore;
import model.MatriceDeiPezzi;
import model.Nero;
import model.Pedone;
import model.Pezzo;
import model.Re;
import model.Regina;
import model.Spazio;
import model.Torre;
import view.InterfacciaGrafica;

/**
 *
 * @author Viktor
 */


public class GestoreMovimenti2 {
    private int turno;
    private static final int BIANCO=1;
    private static final int NERO=-1;
    private MatriceDeiPezzi matrice; //collegamento con la matrice che salva le posizioni di tutti i pezzi
    private Spazio[][] m;
    private final int MAXLENGTH=7;
    private InterfacciaGrafica ig;
    private Re reBianco;
    private Re reNero;
    
    public GestoreMovimenti2(){
        //throw new Exception("modificare il costruttore senza parametri");
        //creare una matrice con le posizioni di default;
        //semplificare le classi corrispondenti ai colori
        turno=BIANCO;
        m=new Spazio[8][8];
        m[0][0].setPezzo(new Torre(0,0,NERO));
        m[7][0].setPezzo(new Torre(7,0,NERO));
        m[1][0].setPezzo(new Cavallo(1,0,NERO));
        m[6][0].setPezzo(new Cavallo(6,0,NERO));
        m[2][0].setPezzo(new Alfiere(2,0,NERO));
        m[5][0].setPezzo(new Alfiere(5,0,NERO));
        m[3][0].setPezzo(new Regina(3,0,NERO));
        m[4][0].setPezzo(new Re(4,0,NERO));
        reNero=(Re) m[4][0].getPezzo();
        
        for(int i=0;i<8;i++){
            m[i][1].setPezzo(new Pedone(i,1,NERO));
        }
        
        for(int i=0;i<8;i++){
            m[i][6].setPezzo(new Pedone(i,1,BIANCO));
        }
        
        m[0][7].setPezzo(new Torre(0,0,BIANCO));
        m[7][7].setPezzo(new Torre(7,0,BIANCO));
        m[1][7].setPezzo(new Cavallo(1,0,BIANCO));
        m[6][7].setPezzo(new Cavallo(6,0,BIANCO));
        m[2][7].setPezzo(new Alfiere(2,0,BIANCO));
        m[5][7].setPezzo(new Alfiere(5,0,BIANCO));
        m[3][7].setPezzo(new Regina(3,0,BIANCO));
        m[4][7].setPezzo(new Re(4,0,BIANCO));
        reBianco=(Re) m[4][7].getPezzo();
        
        matrice=new MatriceDeiPezzi(m);
    }
    
    public GestoreMovimenti2(MatriceDeiPezzi matrice){
        
        this.matrice=matrice;
        m=matrice.getMatrice();
        //da implementare gli altri collegamenti MCV 
    }
    
    public GestoreMovimenti2(MatriceDeiPezzi matrice,int turno){
        this.turno=turno;
        this.matrice=matrice;
        m=matrice.getMatrice();
        //da implementare gli altri collegamenti MCV 
    }
    
    //chiamata dopo ogni mossa effettuata
    public void aggiornaMatriceDeiPezzi(MatriceDeiPezzi nuova){
        matrice=nuova;
        m=matrice.getMatrice();
    }
    
    public MatriceDeiPezzi getMatrice(){
        return matrice;
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
                        else
                            if(p instanceof Re)
                                return movimentiRe((Re)p);
        
        //caso impossibile (da togliere alla fine dell'implementazione)
        return null;//da implementare il resto (altri sottoclassi di pezzo)
    }
    
    
    //metodo per controllare il pedone
    //manca il controllo di quando supero una pedina con un'altra (opzionale)
    //mancano controlli che impediscono la mossa nel caso di scacco
    //manca il controllo ultima riga -> trasformazione in un altro pezzo (solo dopo il movimento)
    //non controllo l'ultima riga xke in quella il pezzo si trasforma e cambiano le sue regole
    //aggiungere il controllo se il pedone non si è mai mosso può muoversi di 2 quadratini!!!
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
        int[][] scacchiera=new int[8][8];
        int[][] scacchiera1=new int[8][8];
        int[][] scacchiera2=new int[8][8];
        int x,y;
        x=regina.getX();
        y=regina.getY();
        scacchiera1=movimentiTorre(new Torre(x,y,regina.getColore()));
        scacchiera2=movimentiAlfiere(new Alfiere(x,y,regina.getColore()));
        
        //combinazione dei risultati della torre e dell'alfiere
        for(int i=0;i<MAXLENGTH;i++){
            for(int j=0;j<MAXLENGTH;j++){
                if(scacchiera1[i][j]==1 || scacchiera2[i][j]==1)
                    scacchiera[i][j]=1;
            }
        }
        
        return scacchiera;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //prendo tutte le posizioni adiacenti togliendo gli spazi occupati da 
    //pezzi dello stesso colore, tolgo posizioni vuote che potrebbero essere
    //attaccate da altri pezzi e aggiungo laposizione per scambiare il re
    //con la torre se nessuno dei 2 si è mai mosso (precisazione: ci sono 2 torri)
    private int[][] movimentiRe(Re re) {
        int[][] scacchiera=new int[8][8];
        int x,y;
        x=re.getX();
        y=re.getY();
        
        //parte destra
        if(x+1<=MAXLENGTH){
            if(!m[x+1][y].isBusy() || !m[x+1][y].getPezzo().getColore().equals(re.getColore()))
                scacchiera[x+1][y]=1;
            if(y+1<=MAXLENGTH)
                if(!m[x+1][y+1].isBusy() || !m[x+1][y+1].getPezzo().getColore().equals(re.getColore()))
                    scacchiera[x+1][y+1]=1;
            if(y-1>=0)
                if(!m[x+1][y-1].isBusy() || !m[x+1][y-1].getPezzo().getColore().equals(re.getColore()))
                    scacchiera[x+1][y-1]=1;
        }
        //parte sinistra
        if(x-1>=0){
            if(!m[x-1][y].isBusy() || !m[x-1][y].getPezzo().getColore().equals(re.getColore()))
                scacchiera[x-1][y]=1;
            if(y+1<=MAXLENGTH)
                if(!m[x-1][y+1].isBusy() || !m[x-1][y+1].getPezzo().getColore().equals(re.getColore()))
                    scacchiera[x-1][y+1]=1;
            if(y-1>=0)
                if(!m[x-1][y-1].isBusy() || !m[x-1][y-1].getPezzo().getColore().equals(re.getColore()))
                    scacchiera[x-1][y-1]=1;
        }
        //rimanente spazio in alto
        if(y+1<=MAXLENGTH)
            if(!m[x][y+1].isBusy() || !m[x][y+1].getPezzo().getColore().equals(re.getColore()))
                scacchiera[x][y+1]=1;
        //rimanente spazio in basso
        if(y-1>=0)
            if(!m[x][y-1].isBusy() || !m[x][y-1].getPezzo().getColore().equals(re.getColore()))
                scacchiera[x][y-1]=1;
        
        //da implementare ancora i controlli dello scacco inverso/ pericolo
        for (int i=0;i<=MAXLENGTH;i++){
            for(int j=0;j<=MAXLENGTH;j++){
                if(scacchiera[i][j]==1)
                    scacchiera[i][j]=controlloScacco(i,j,re.getColore());
            }
        }
        
        //da implementare controllo scambio con una delle 2 torri (arrocco)
        //in questo caso il re si sposta di 2 caselle verso la torre e la torre gli si mette di fianco dall'altra parte
        //simulare la scacchiera modificata e vedere se lo scambio non porta ad una situazione di scacco matto
        
        if(!re.isMoved())//re mai mosso
            if(controlloScacco(x,y,re.getColore())==1)//se il re non è sotto scacco
                                
                //gli spazi tra il re e la torre sinistra sono liberi (generalizzato x entrambi i re
                if(!m[1][re.getY()].isBusy() && !m[2][re.getY()].isBusy() && !m[3][re.getY()].isBusy()){     
                    //a sinistra c'è una torre nella sua posizione originale
                    if(m[0][re.getY()].getPezzo() instanceof Torre)
                        //torre a sinistra mai mossa
                        if(!((Torre)m[0][re.getY()].getPezzo()).isMoved())
                            //controllo se tutti gli spazi tra la posizione attuale del re
                            //e la posizione finale del re non sono sotto scacco
                            if((controlloScacco(x-1,y,re.getColore())==1))
                                if((controlloScacco(x-2,y,re.getColore())==1))
                                    scacchiera[x-2][y]=1;
                }
                
                //gli spazi tra il re e la torre destra sono liberi (generalizzato x entrambi i re
                if(!m[5][re.getY()].isBusy() && !m[6][re.getY()].isBusy()){     
                    //a sinistra c'è una torre nella sua posizione originale
                    if(m[7][re.getY()].getPezzo() instanceof Torre)
                        //torre a sinistra mai mossa
                        if(!((Torre)m[7][re.getY()].getPezzo()).isMoved())
                            //controllo se tutti gli spazi tra la posizione attuale del re
                            //e la posizione finale del re non sono sotto scacco
                            if((controlloScacco(x+1,y,re.getColore())==1))
                                if((controlloScacco(x+2,y,re.getColore())==1))
                                    scacchiera[x+2][y]=1;
                }
        
        
        
        
        return scacchiera;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //minimizzare il codice regruppando re e pedone in casi specifici
    //(come regina con torre e alfiere)
    //si possono fare delle hiamate per minimizzare il codice
    public int controlloScacco(int x, int y,Colore colore) {
        // controllo x torri/regine in orizzontale e verticale
        int temp1,temp2;
        //verso destra
        boolean uscita=false;
        for(int i=x;i<=MAXLENGTH && !uscita;i++){
            if(m[i][y].isBusy()){
                uscita=true;//uscita dal ciclo dopo aver controllato il primo spazio per non fare controlli inutili
                if(!m[i][y].getPezzo().getColore().equals(colore))
                    if(m[i][y].getPezzo() instanceof Torre || m[i][y].getPezzo() instanceof Regina)
                        return 0;
                
            } 
        }
        //verso sinistra
        uscita=false;
        for(int i=x;i>=0 && !uscita;i--){
            if(m[i][y].isBusy()){
                uscita=true;//uscita dal ciclo dopo aver controllato il primo spazio per non fare controlli inutili
                if(!m[i][y].getPezzo().getColore().equals(colore))
                    if(m[i][y].getPezzo() instanceof Torre || m[i][y].getPezzo() instanceof Regina)
                        return 0;
                
            } 
        }
        //verso alto
        uscita=false;
        for(int i=y;i<=MAXLENGTH && !uscita;i++){
            if(m[x][i].isBusy()){
                uscita=true;//uscita dal ciclo dopo aver controllato il primo spazio per non fare controlli inutili
                if(!m[x][i].getPezzo().getColore().equals(colore))
                    if(m[x][i].getPezzo() instanceof Torre || m[x][i].getPezzo() instanceof Regina)
                        return 0;
            } 
        }
        //verso basso
        uscita=false;
        for(int i=y;i>=0 && !uscita;i--){
            if(m[x][i].isBusy()){
                uscita=true;//uscita dal ciclo dopo aver controllato il primo spazio per non fare controlli inutili
                if(!m[x][i].getPezzo().getColore().equals(colore))
                    if(m[x][i].getPezzo() instanceof Torre || m[x][i].getPezzo() instanceof Regina)
                        return 0;
            } 
        }
        
        //controlli in diagonale x alfieri e regine
        
        //verso alto destra
        temp1=x+1;
        temp2=y+1;
        while(temp1<=MAXLENGTH && temp2<=MAXLENGTH && !m[temp1][temp2].isBusy()){
            temp1++;
            temp2++;
        }
        if(temp1<=MAXLENGTH && temp2<=MAXLENGTH)
            if(!m[temp1][temp2].getPezzo().getColore().equals(colore))
                if(m[temp1][temp2].getPezzo() instanceof Alfiere || m[temp1][temp2].getPezzo() instanceof Regina)
                    return 0;
        
        //verso alto sinistra
        temp1=x-1;
        temp2=y+1;
        while(temp1>=0 && temp2<=MAXLENGTH && !m[temp1][temp2].isBusy()){
            temp1--;
            temp2++;
        }
        if(temp1>=0 && temp2<=MAXLENGTH)
            if(!m[temp1][temp2].getPezzo().getColore().equals(colore))
                if(m[temp1][temp2].getPezzo() instanceof Alfiere || m[temp1][temp2].getPezzo() instanceof Regina)
                    return 0;
        
        //verso basso sinistra
        temp1=x-1;
        temp2=y-1;
        while(temp1>=0 && temp2>=0 && !m[temp1][temp2].isBusy()){
            temp1--;
            temp2--;
        }
        if(temp1>=0 && temp2>=0)
            if(!m[temp1][temp2].getPezzo().getColore().equals(colore))
                if(m[temp1][temp2].getPezzo() instanceof Alfiere || m[temp1][temp2].getPezzo() instanceof Regina)
                    return 0;
        
        //verso basso destra
        temp1=x+1;
        temp2=y-1;
        while(temp1<=MAXLENGTH && temp2>=0 && !m[temp1][temp2].isBusy()){
            temp1++;
            temp2--;
        }
        if(temp1<=MAXLENGTH && temp2>=0)
            if(!m[temp1][temp2].getPezzo().getColore().equals(colore))
                if(m[temp1][temp2].getPezzo() instanceof Alfiere || m[temp1][temp2].getPezzo() instanceof Regina)
                    return 0;
        
        //controlli pedone (potrebbe essere integrato nell'alfiere in casi specifici)
        
        if(colore instanceof Nero){
            if(x+1<=MAXLENGTH && y+1<=MAXLENGTH)
                if(m[x+1][y+1].isBusy() && m[x+1][y+1].getPezzo() instanceof Pedone )
                    if(!m[x+1][y+1].getPezzo().getColore().equals(colore))
                        return 0;
            if(x-1>=0 && y+1<=MAXLENGTH)
                if(m[x-1][y+1].isBusy() && m[x+1][y-1].getPezzo() instanceof Pedone )
                    if(!m[x-1][y+1].getPezzo().getColore().equals(colore))
                        return 0;
        }
        else{
            if(x+1<=MAXLENGTH && y-1>=0)
                if(m[x+1][y-1].isBusy() && m[x+1][y-1].getPezzo() instanceof Pedone )
                    if(!m[x+1][y-1].getPezzo().getColore().equals(colore))
                        return 0;
            if(x-1>=0 && y-1>=0)
                if(m[x-1][y-1].isBusy() && m[x-1][y-1].getPezzo() instanceof Pedone )
                    if(!m[x-1][y-1].getPezzo().getColore().equals(colore))
                        return 0;
        }
        
        //controlli cavallo
        //alto destra
        if(x+1<=MAXLENGTH && y+2<=MAXLENGTH)
            if(m[x+1][y+2].isBusy())
                if(!m[x+1][y+2].getPezzo().getColore().equals(colore))
                    if(m[x+1][y+2].getPezzo() instanceof Cavallo)
                        return 0;
        //alto sinistra
        if(x-1>=0 && y+2<=MAXLENGTH)
            if(m[x-1][y+2].isBusy())
                if(!m[x-1][y+2].getPezzo().getColore().equals(colore))
                    if(m[x-1][y+2].getPezzo() instanceof Cavallo)
                        return 0;
        
        //destra alto
        if(x+2<=MAXLENGTH && y+1<=MAXLENGTH)
            if(m[x+2][y+1].isBusy())
                if(!m[x+2][y+1].getPezzo().getColore().equals(colore))
                    if(m[x+2][y+1].getPezzo() instanceof Cavallo)
                        return 0;
        //destra basso
        if(x+2<=MAXLENGTH && y-1>=0)
            if(m[x+2][y-1].isBusy())
                if(!m[x+2][y-1].getPezzo().getColore().equals(colore))
                    if(m[x+2][y+1].getPezzo() instanceof Cavallo)
                        return 0;
        //sinistra alto
        if(x-2>=0 && y+1<=MAXLENGTH)
            if(m[x-2][y+1].isBusy())
                if(!m[x-2][y+1].getPezzo().getColore().equals(colore))
                    if(m[x-2][y+1].getPezzo() instanceof Cavallo)
                        return 0;
        //sinistra basso
        if(x-2>=0 && y-1>=0)
            if(m[x-2][y-1].isBusy())
                if(!m[x-2][y-1].getPezzo().getColore().equals(colore))
                    if(m[x-2][y-1].getPezzo() instanceof Cavallo)
                        return 0;
        //basso destra
        if(x+1<=MAXLENGTH && y-2<=MAXLENGTH)
            if(m[x+1][y-2].isBusy())
                if(!m[x+1][y-2].getPezzo().getColore().equals(colore))
                    if(m[x+1][y-2].getPezzo() instanceof Cavallo)
                        return 0;
        //basso sinistra
        if(x-1>=0 && y-2>=0)
            if(m[x-1][y-2].isBusy())
                if(!m[x-1][y-2].getPezzo().getColore().equals(colore))
                    if(m[x-1][y-2].getPezzo() instanceof Cavallo)
                        return 0;
        
        //controllo re adiacente
        //destra
        if(x+1<=MAXLENGTH)
            if(m[x+1][y].isBusy())
                if(!m[x+1][y].getPezzo().getColore().equals(colore))
                    if(m[x+1][y].getPezzo() instanceof Re)
                        return 0;
        //sinistra
        if(x-1>=0)
            if(m[x-1][y].isBusy())
                if(!m[x-1][y].getPezzo().getColore().equals(colore))
                    if(m[x-1][y].getPezzo() instanceof Re)
                        return 0;
        //alto
        if(y+1<=MAXLENGTH)
            if(m[x][y+1].isBusy())
                if(!m[x][y+1].getPezzo().getColore().equals(colore))
                    if(m[x][y+1].getPezzo() instanceof Re)
                        return 0;
        //basso
        if(y-1>=0)
            if(m[x][y-1].isBusy())
                if(!m[x][y-1].getPezzo().getColore().equals(colore))
                    if(m[x][y-1].getPezzo() instanceof Re)
                        return 0;
        //alto destra
        if(x+1<=MAXLENGTH && y+1<=MAXLENGTH)
            if(m[x+1][y+1].isBusy())
                if(!m[x+1][y+1].getPezzo().getColore().equals(colore))
                    if(m[x+1][y+1].getPezzo() instanceof Re)
                        return 0;
        //alto sinistra
        if(x-1>=0 && y+1<=MAXLENGTH)
            if(m[x-1][y+1].isBusy())
                if(!m[x-1][y+1].getPezzo().getColore().equals(colore))
                    if(m[x-1][y+1].getPezzo() instanceof Re)
                        return 0;
        //basso destra
        if(x+1<=MAXLENGTH && y-1>=0)
            if(m[x+1][y-1].isBusy())
                if(!m[x+1][y-1].getPezzo().getColore().equals(colore))
                    if(m[x+1][y-1].getPezzo() instanceof Re)
                        return 0;
        //basso sinistra
        if(x-1>=0 && y-1>=0)
            if(m[x-1][y-1].isBusy())
                if(!m[x-1][y-1].getPezzo().getColore().equals(colore))
                    if(m[x-1][y-1].getPezzo() instanceof Re)
                        return 0;
        
        return 1;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int controlloScacco(Re r) {
        return controlloScacco(r.getX(),r.getY(),r.getColore());
    }
    
    
    public void setInterfacciaGrafica(InterfacciaGrafica i){
        ig=i;
    }
    
    public Re getReNero(){
        return reNero;
    }
    
    public Re getReBianco(){
        return reBianco;
    }
}
