package controller;


import java.awt.event.ActionEvent;
import javax.swing.JButton;
import model.Bianco;
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
import view.PromozionePedone;

/**
 * Gestisce i bottoni permettendo di cliccare solo quelli che permettono il 
 * movimento delle pedine. E' direttamentte collegata alla classe 
 * gestoreMovimenti
 * @author Viktor
 */
public class GestoreBottoni {
    GestoreTurni gestoreTurni;
    GestoreMovimenti gestoreMovimenti;
    InterfacciaGrafica interfacciaGrafica;
    JButton[][] matriceBottoni;
    Pezzo ultimoPedoneTrasformato;
    int x;
    int y;
    
    public GestoreBottoni(GestoreMovimenti gm, GestoreTurni gt, InterfacciaGrafica ig){
        gestoreMovimenti=gm;
        gestoreTurni=gt;
        interfacciaGrafica=ig;
        matriceBottoni=interfacciaGrafica.getMatriceBottoni();
    }
    
    
    //blocco spazi iniziale/generico (prima di aver schiacciato su un bottone
    //da chiamare all'inizio di ogni turno
    /**
     * Blocca le caselle occupate dalle pedine di cui non é il turno e quelle
     * vuote. Lo si chiama all'inizio di ogni turno.
     */
    public void bloccoBottoniIniziale(){
        System.err.println("DEBUG: blocco bottoni iniziale");
        for(int j=0;j<8;j++){
            for(int i=0;i<8;i++){
                //per evitare null pointer exception bisogna dividere l'if in 2 casi per non fare il controllo su un pezzo che non esiste
                //cerco locazioni vuote
                if(!(gestoreMovimenti.getMatrice().getSpazio(i,j).eOccupato()))
                    matriceBottoni[i][j].setEnabled(false);
                //con l'else sono sicuro che esiste l'occupante
                else 
                    //cerco locazioni del colore sbagliato
                    if(!(gestoreMovimenti.getMatrice().getSpazio(i, j).getOccupante().getColore().equals(gestoreTurni.getTurno())))
                        matriceBottoni[i][j].setEnabled(false);
            }
        }
    }
    
    /*
    //blocco spazi dopo aver schiacciato su un pezzo
    //chiamata dopo aver trovato i possibili movimenti del pezzo premuto
    //non serve dato che controllo ogni pezzo prima di ogni mossa
    public void bloccoBottoniDopoAttivazione(Spazio posizioneDelPezzo,int[][] matricePosizioniConsentite){
        System.err.println("DEBUG: chiamataBloccoBottoniDopoAttivazione");
        //prima disabilito tutti i bottoni
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                matriceBottoni[j][i].setEnabled(false);
            }
        }
        //poi abilito quelli che si possono premere
        
        //abilita il bottone sul quale abbiamo schiacciato (forse non serve->da ridefinire)
        matriceBottoni[posizioneDelPezzo.getX()][posizioneDelPezzo.getY()].setEnabled(true);
        
        //abilito i possibili movimenti del bottone
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(matricePosizioniConsentite[j][i]==1)
                    matriceBottoni[j][i].setEnabled(true);
            }
        } 
    }
    */
    
    //da ridefinire e settare...
    /**
     * Viene chiamata quando si clicca una casella. Controlla se il la casella
     * cliccata è valida (colore = turno e movimento valido).
     * @param e 
     */
    public void pressionePulsanteScacchiera(ActionEvent e){
        //identifico il bottone dove premo
        x=0;
        y=0;
        
        JButton b;
        //Pezzo morente;
        MatriceDeiPezzi matriceSimulata=null;
        GestoreMovimenti gestoreTemporaneo;
        Colore turno=gestoreTurni.getTurno();
        JButton[][] matriceScacchiera=interfacciaGrafica.getMatriceBottoni();
        int indiciBottoni[][]=new int[8][8];
        //Spazio[][] matriceSimulata;
        
        b=(JButton)e.getSource();
        
        //ricerca inversa della posizione del bottone
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(b.equals(matriceScacchiera[j][i])){
                    x=j;
                    y=i;
                    break;
                }
                    
            }
        }
        
        //System.out.println("DEBUG:\nx:"+x+"\ny:"+y);
        

        //guardo se nello stesso turno non è abilitato un bottone
        if(!gestoreTurni.isAttivato()){
            //guardo se lo spazio contiene qualcosa
            if(gestoreMovimenti.getMatrice().getMatrice()[x][y].eOccupato()){
                //se ciò che contiene ha lo stesso colore del turno corrente
                if(turno.equals(gestoreMovimenti.getMatrice().getMatrice()[x][y].getOccupante().getColore())){
                    //se il pezzo nella posizione indicata non è bloccato
                    //throw new UnsupportedOperationException();
                    //if(!gestoreMovimenti.getMatrice().getMatrice()[x][y].getOccupante().getLock()){
                        System.err.println("GESTORE BOTTONI: Pezzo non bloccato");
                        //se il re del turno corrente non è sotto scacco
                        //System.err.println("controllo scacco fare debug");
                        
                        //il re non è sotto scacco
                        if(((turno instanceof Bianco) && 
                                gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco().getX(),gestoreMovimenti.getReBianco().getY(),gestoreMovimenti.getReBianco().getColore(),gestoreMovimenti.getMatrice().getMatrice())==0)||
                            ((turno instanceof Nero) && 
                                gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero().getX(),gestoreMovimenti.getReNero().getY(),gestoreMovimenti.getReNero().getColore(),gestoreMovimenti.getMatrice().getMatrice())==0)){
                            System.err.println("GESTORE BOTTONI: il re non è sotto scacco");
                            attivaPosizione(x,y); 
                        }
                        //variante al posto di controllo scacco, getPezziAttaccantiIlRe
                        /*
                        if(((turno instanceof Bianco) && 
                            (gestoreMovimenti.getPezziAttaccantiIlRe(gestoreMovimenti.getReBianco(), gestoreMovimenti.getMatrice().getMatrice()).isEmpty()))||
                            (turno instanceof Nero) && 
                            (gestoreMovimenti.getPezziAttaccantiIlRe(gestoreMovimenti.getReNero(), gestoreMovimenti.getMatrice().getMatrice()).isEmpty())){
                        */
                        
                        //per non fare debug su scacco matto
                        /*if(true){
                            //abilito le posizioni dove può muoversi il pezzo(non implementato)
                            //metto il pezzo premuto nella gestione turno e lo attivo
                            attivaPosizione(x,y);    
                        }*/
                    
                        //NON ENTRO MAI QUI
                        //se il re è sotto scacco
                        else if(((turno instanceof Bianco) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco().getX(),gestoreMovimenti.getReBianco().getY(),new Bianco(),gestoreMovimenti.getMatrice().getMatrice())==1)||
                            ((turno instanceof Nero) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero().getX(),gestoreMovimenti.getReNero().getY(),new Nero(),gestoreMovimenti.getMatrice().getMatrice())==1)){
                            System.err.println("DEBUG: il re è sotto scacco");
                            //se il pezzo può salvare il re
                            //devo controllare la matrice simulata, ma prima devo crearla
                            //matriceTemporanea=gestoreMovimenti.getMatrice();
                            //in base al turno
                            if(turno instanceof Bianco){
                                if(gestoreMovimenti.getMatricePezziChePrevengonoScacco(gestoreMovimenti.getReBianco().getX(),
                                                                                       gestoreMovimenti.getReBianco().getY(),
                                                                                       gestoreMovimenti.getMatrice(),new Bianco())[x][y]==1)
                                    System.err.println("DEBUG: il pezzo può salvare il re");
                                    attivaPosizione(x,y);     
                                //altrimenti non faccio niente
                            }
                            //Nero
                            else if(gestoreMovimenti.getMatricePezziChePrevengonoScacco(gestoreMovimenti.getReBianco().getX(),
                                                                                       gestoreMovimenti.getReBianco().getY(),
                                                                                       gestoreMovimenti.getMatrice(),new Nero())[x][y]==1)
                                    System.err.println("DEBUG: il pezzo può salvare il re");
                                    attivaPosizione(x,y);     
                                //else... altrimenti non faccio niente
                        }
                        System.err.println("DEBUG: posizione attivata dopo controllo scacco");
                    //}
                    //else
                    //    System.err.println("DEBUG: Pezzo bloccato");
                }
                //else... altrimenti non faccio niente
            }
            //else... altrimenti non faccio niente
        }
        //altrimenti (quindi è stato premuto precedentemente un pezzo valido)
        else{
            //se la posizione dove premo è quella dove ho premuto prima
            if(gestoreMovimenti.getMatrice().getMatrice()[x][y].equals(gestoreTurni.getSpazioAttivato())){
                System.err.println("DEBUG: premuto sulla posizione precedente");
                //imposto come se niente fosse stato premuto
                gestoreTurni.disattiva();
            }
            //altrimenti se è una posizione diversa
            else{
                //se è una locazione vuota
                if(!gestoreMovimenti.getMatrice().getMatrice()[x][y].eOccupato()){
                    System.err.println("DEBUG: premuto su una locazione vuota");
                    //se è una locazione consentita dal pezzo
                    //if(gestoreTurni.getSpazioAttivato().getOccupante().spostabileIn(x, y, gestoreMovimenti.getMatrice().getMatrice())){
                    //alternativa in gestoreMovimenti
                    if(gestoreMovimenti.spostabileIn(gestoreTurni.getSpazioAttivato().getOccupante(),x, y)){
                        System.err.println("DEBUG: premuto in una posizione valida");
                        //se spostando il pezzo in questa locazione non ho scacco/scacco matto
                        //(devo simulare la scachiera dopo la mossa)
                        matriceSimulata=coppiaMatrice(gestoreMovimenti.getMatrice());
                        matriceSimulata.spostaPezzo(gestoreTurni.getSpazioAttivato().getOccupante(), x, y);
                        
                        /*
                        if(((turno instanceof Bianco) && 
                                gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco().getX(),gestoreMovimenti.getReBianco().getY(),new Bianco(),matriceSimulata.getMatrice())==1)||
                            ((turno instanceof Nero) && 
                                gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero().getX(),gestoreMovimenti.getReNero().getY(),new Nero(),matriceSimulata.getMatrice())==1)){
                            //sposto il pezzo, aggiorno la matrice della scacchiera e tutto, cambio il turno
                        */
                        //modificare l parte soprastante nel contorllo se è stato rilevato ce il re è sotto scacco
                        if(true){
                            System.err.println("RE: posizione del re bianco: "+gestoreMovimenti.getReBianco().getX()+" "+gestoreMovimenti.getReBianco().getY());
                            //gestoreTurni.getSpazioAttivato().setOccupato(false);
                            //contrassegno lo spazio occupato prima come non occupato
                            //matriceSimulata.getSpazio(gestoreTurni.getSpazioAttivato().getX(), gestoreTurni.getSpazioAttivato().getY()).setOccupato(false);
                            gestoreMovimenti.setMatrice(matriceSimulata);
                            //tolgo il bordo del bottone
                            disattivaPosizione();
                            gestoreTurni.passaTurno();
                            
                            //se il pezzo spostato precedentemente è un pedone o un re e se aveva il flag spostato=false
                            if(gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante() instanceof Pedone &&
                              (!((Pedone)gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante()).isMoved())){
                                ((Pedone)gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante()).setMoved();
                                System.err.println("DEBUG: setto il pedone in "+x+","+y+" come mosso");
                            }
                            
                            if (gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante() instanceof Re &&
                               (!((Re)gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante()).isMoved())){
                                System.err.println("DEBUG: setto il re in "+x+","+y+" come mosso");
                                    ((Re)gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante()).setMoved();
                                    //se la nuova posizione del re differisce di 2 celle da quella originale (fatto l'arrocco)
                                    if(x==2){
                                        System.err.print("DEBUG: arrocco lungo");
                                        gestoreMovimenti.getMatrice().getSpazio(3, y).setOccupante(gestoreMovimenti.getMatrice().getSpazio(0, y).getOccupante(),3,y);
                                        gestoreMovimenti.getMatrice().getSpazio(3,y).setOccupato(true);
                                        gestoreMovimenti.getMatrice().getSpazio(0,y).setOccupante(null);
                                        gestoreMovimenti.getMatrice().getSpazio(0,y).setOccupato(false);
                                    }
                                    else if(x==6){
                                        System.err.print("DEBUG: arrocco corto");
                                        gestoreMovimenti.getMatrice().getSpazio(5, y).setOccupante(gestoreMovimenti.getMatrice().getSpazio(7, y).getOccupante(),5,y);
                                        gestoreMovimenti.getMatrice().getSpazio(5,y).setOccupato(true);
                                        gestoreMovimenti.getMatrice().getSpazio(7,y).setOccupante(null);
                                        gestoreMovimenti.getMatrice().getSpazio(7,y).setOccupato(false);
                                    }
                            }
                            if    (gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante() instanceof Torre &&
                                (!((Torre)gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante()).isMoved())){
                                    //spostato=true;
                                    System.err.println("DEBUG: setto la torre in "+x+","+y+" come mosso");
                                    ((Torre)gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante()).setMoved();
                                    
                            }
                            //se ho appena spostato un pedone
                            if(gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante() instanceof Pedone){
                                //divido in 2 casi in base al colore
                                if(gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante().getColore() instanceof Bianco){
                                    //se il pedone è in fondo alla scacchiera
                                    if(y==0){
                                        //trasforma il pedone in altro
                                        System.err.println("DEBUG: trasformo il pedone in altro");
                                        PromozionePedone promozione;
                                        promozione = new PromozionePedone(this,new Bianco());
                                        promozione.start();
                                        //ultimoPedoneTrasformato=gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante();
                                        //ultimoPedoneTrasformato=promozione.getPezzo();
                                        /*
                                        while(ultimoPedoneTrasformato.equals(null)){
                                            
                                        }*/
                                        //ultimoPedoneTrasformato=null;   
                                        //gestoreMovimenti.getMatrice().getSpazio(x, y).setOccupante(new Regina(x,y,new Bianco()),x,y);
                                    }
                                }
                                //nero
                                else{
                                    //se il pedone è in fondo alla scacchiera
                                    if(y==7){
                                        //trasforma il pedone in altro
                                        System.err.println("DEBUG: trasformo il pedone in altro");
                                        PromozionePedone promozione;
                                        promozione = new PromozionePedone(this,new Nero());
                                        promozione.start();
                                        //gestoreMovimenti.getMatrice().getSpazio(x, y).setOccupante(new Regina(x,y,new Nero()),x,y);
                                    }
                                }
                            }
                            
                            //aggiorna la visuale
                            interfacciaGrafica.aggiornaBottoni(gestoreMovimenti.getMatrice());
                        }
                        //se provoco scacco matto
                        else{
                            //imposto come se niente fosse stato premuto
                            disattivaPosizione();
                        }
                        
                    }
                    //se non è una locazione consentita dal pezzo
                    else{
                        System.err.println("DEBUG: premuto in una posizione non consentita dal pezzo, bisogna farla diventare rossa");
                        //il seguente codice commentato da spostare eventualmente nel package view
                        //matriceBottoni[x][y].setBorder(...);//RED
                        //imposto come se niente fosse stato premuto
                        //System.err.println("DEBUG: premuto su una posizione non consentita -> annulla tutto");
                        disattivaPosizione();
                    }
                }
                //se è una locazione non vuota
                else{
                    System.err.print("DEBUG: schiaccio su una locazione non vuota");
                    //se è una locazione consentita dal pezzo e contiene un pezzo del colore diverso
                    if(gestoreMovimenti.spostabileIn(gestoreTurni.getSpazioAttivato().getOccupante(),x, y)){
                    //alternativa    
                    //if(gestoreTurni.getSpazioAttivato().getOccupante().spostabileIn(x, y, gestoreMovimenti.getMatrice().getMatrice())){
                            System.err.println(" del colore diverso");
                            //se non provoca scacco del proprio colore -> scacco matto
                            //(faccio la simulazione)
                            
                            matriceSimulata=coppiaMatrice(gestoreMovimenti.getMatrice());
                            
                            matriceSimulata.spostaPezzo(gestoreTurni.getSpazioAttivato().getOccupante(), x, y);
                        
                            System.err.println("DEBUG: non faccio controllo scacco QUI");
                            /*
                            if(((turno instanceof Bianco) && 
                                    gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco().getX(),gestoreMovimenti.getReBianco().getY(),new Bianco(),matriceSimulata.getMatrice())==1)||
                                ((turno instanceof Nero) && 
                                    gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero().getX(),gestoreMovimenti.getReNero().getY(),new Nero(),matriceSimulata.getMatrice())==1)){
                            */
                            if(true){    
                            
                                //mangia il pezzo in questa locazione
                                
                                gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante().distruggi(interfacciaGrafica);
                                //sposto effettivamente il pezzo 
                                gestoreMovimenti.setMatrice(matriceSimulata);
                                //contrassegno lo spazio occupato prima come non occupato
                                matriceSimulata.getSpazio(gestoreTurni.getSpazioAttivato().getX(), gestoreTurni.getSpazioAttivato().getY()).setOccupato(false);
                                //prova per debug
                                matriceSimulata.getSpazio(gestoreTurni.getSpazioAttivato().getX(), gestoreTurni.getSpazioAttivato().getY()).setOccupante(null);
                                //disattiva bordo
                                disattivaPosizione();
                                gestoreTurni.passaTurno();
                                
                                //controllo pedone (per la promozione)
                                if(gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante() instanceof Pedone){
                                    //divido in 2 casi in base al colore
                                    if(gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante().getColore() instanceof Bianco){
                                        //se il pedone è in fondo alla scacchiera
                                        if(y==0){
                                            //trasforma il pedone in altro
                                            System.err.println("DEBUG: trasformo il pedone in altro");
                                            PromozionePedone promozione;
                                            promozione = new PromozionePedone(this,new Bianco());
                                            promozione.start();
                                            //ultimoPedoneTrasformato=gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante();
                                            //ultimoPedoneTrasformato=promozione.getPezzo();
                                            /*
                                            while(ultimoPedoneTrasformato.equals(null)){
                                            
                                            }*/
                                            //ultimoPedoneTrasformato=null;   
                                            //gestoreMovimenti.getMatrice().getSpazio(x, y).setOccupante(new Regina(x,y,new Bianco()),x,y);
                                        }
                                    }
                                    //nero
                                    else{
                                        //se il pedone è in fondo alla scacchiera
                                        if(y==7){
                                            //trasforma il pedone in altro
                                            System.err.println("DEBUG: trasformo il pedone in altro");
                                            PromozionePedone promozione;
                                            promozione = new PromozionePedone(this,new Nero());
                                            promozione.start();
                                            //gestoreMovimenti.getMatrice().getSpazio(x, y).setOccupante(new Regina(x,y,new Nero()),x,y);
                                        }
                                    }
                                }
                            
                                
                                //aggiorna la visuale
                                interfacciaGrafica.aggiornaBottoni(gestoreMovimenti.getMatrice());
                            
                                //verifico scacco matto
                                if(gestoreMovimenti.scaccoMatto()){
                                    interfacciaGrafica.finePartita();
                                }
                                else{
                                    //se il pezzo spostato precedentemente è un pedone o un re e se aveva il flag spostato=false
                                    if((gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante() instanceof Pedone &&
                                        (((Pedone)gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante()).isMoved())) ||
                                        (gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante() instanceof Re &&
                                            (((Re)gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante()).isMoved())) ||
                                        (gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante() instanceof Torre &&
                                            (((Torre)gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante()).isMoved())))
					//spostato=true;
                                        ((Pedone)gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante()).setMoved();
								
                                    System.err.println("DEBUG: non ho scacco matto e passo il turno");
                                    gestoreTurni.passaTurno();
                                    //soluzione facile per togliere il bug 
                                    //del caso in cui mangiando un pezzo
                                    //non viene cambiato il turno
                                    gestoreTurni.passaTurno();
                                    
                                    
                                }
                        }
                        //se provoco scacco matto
                        else{
                            //imposto come se niente fosse stato premuto
                            disattivaPosizione();
                        }
                    }
                    //se contiene un pezzo del colore corrente
                    else{
                        //imposto come se niente fosse stato premuto
                        disattivaPosizione();
                    }
                }
            }
            
        }
        
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //metto il pezzo premuto nella gestione turno e lo attivo
    /**
     * Indico che ho selezionato quella casella (disegno il bordo di selezione)
     * @param x
     * @param y 
     */
    private void attivaPosizione(int x,int y){
        gestoreTurni.setSpazioAttivato(gestoreMovimenti.getMatrice().getMatrice()[x][y]);
        gestoreTurni.attiva();
        System.err.println("DEBUG: attivaPosizione");
        //System.err.println("DEBUG: non blocco le posizioni nel metodo attivaPosizione (contiene dei bug)");
        //bloccoBottoniDopoAttivazione(gestoreMovimenti.getMatrice().getSpazio(x, y), gestoreMovimenti.getPossibiliMovimenti(gestoreMovimenti.getMatrice().getSpazio(x,y).getOccupante()));
        //evidenzio la cella
        
        //da fare una chiamata in interfacciaGrafica
        interfacciaGrafica.attivaBordo(x,y);
    }
    
    /**
     * Segno che la casella non è più selezionata (cancello il bordo)
     * @param x
     * @param y 
     */
    private void disattivaPosizione(int x,int y){
        gestoreTurni.disattiva();
        System.err.println("DEBUG: disattivaPosizione");
        //interfacciaGrafica.disattivaBordo(x,y);
    }
    
    /**
     * Facilita l'uso vera funzione disattivaPosizione(x, y), recuperando i
     * parametri una volta sola
     */
    private void disattivaPosizione(){
        disattivaPosizione(gestoreTurni.getSpazioAttivato().getX(),gestoreTurni.getSpazioAttivato().getY());
    }
    
    public InterfacciaGrafica getInterfacciaGrafica(){
        return interfacciaGrafica;
    }
    
    /**
     * Simula una nuova matrice
     * @param m
     * @return 
     */
    //Il parametro passato non viene usato NDGaetano
    private MatriceDeiPezzi coppiaMatrice(MatriceDeiPezzi m){
         MatriceDeiPezzi matriceSimulata=new MatriceDeiPezzi(new Spazio[8][8]);
        //copio la matrice originale senza tenere le referenze
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                matriceSimulata.setSpazio(i, j,new Spazio(gestoreMovimenti.getMatrice().getSpazio(i,j)));
            }
        }
        return matriceSimulata;
    }
    

    public Spazio[][] getMatriceSpazi(){
        
        return gestoreMovimenti.getMatriceSpazi();
        
    }
    

    public void setPedoneTrasformato(Pezzo p){
        ultimoPedoneTrasformato=gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante();
        p.setX(x);
        p.setY(y);
        gestoreMovimenti.getMatrice().getSpazio(x, y).setOccupante(p, x, y);
        //aggiorna la visuale
        interfacciaGrafica.aggiornaBottoni(gestoreMovimenti.getMatrice());
    }

}

