package controller;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import model.Bianco;
import model.Colore;
import model.MatriceDeiPezzi;
import model.Nero;
import model.Pezzo;
import model.Spazio;
import view.InterfacciaGrafica;

/**
 *
 * @author Viktor
 */
public class GestoreBottoni {
    GestoreTurni gestoreTurni;
    GestoreMovimenti gestoreMovimenti;
    InterfacciaGrafica interfacciaGrafica;
    JButton[][] matriceBottoni;
    
    public GestoreBottoni(GestoreMovimenti gm, GestoreTurni gt,InterfacciaGrafica ig){
        gestoreMovimenti=gm;
        gestoreTurni=gt;
        interfacciaGrafica=ig;
        matriceBottoni=interfacciaGrafica.getMatriceBottoni();
    }
    
    //blocco spazi iniziale/generico (prima di aver schiacciato su un bottone
    //da chiamare all'inizio di ogni turno
    public void bloccoBottoniIniziale(){
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
    
    
    //blocco spazi dopo aver schiacciato su un pezzo
    //chiamata dopo aver trovato i possibili movimenti del pezzo premuto
    public void bloccoBottoniDopoAttivazione(Spazio posizioneDelPezzo,int[][] matricePosizioniConsentite){
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
    
    //da ridefinire e settare...
    public void pressionePulsanteScacchiera(ActionEvent e){
        //identifico il bottone 
        int x=0;
        int y=0;
        
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
        
        
        System.out.println("DEBUG:\nx:"+x+"\ny:"+y);
        //... tutti gli altri confronti
        
        
        
        //elaboro il risultato
        //...
        
        //guardo se nello stesso turno non è abilitato un bottone
        if(!gestoreTurni.isAttivato()){
            //guardo se lo spazio contiene qualcosa
            if(gestoreMovimenti.getMatrice().getMatrice()[x][y].eOccupato()){
                //se ciò che contiene ha lo stesso colore del turno corrente
                if(turno.equals(gestoreMovimenti.getMatrice().getMatrice()[x][y].getOccupante().getColore())){
                    //se il re del turno corrente non è sotto scacco
                    
                    if(((turno instanceof Bianco) && 
                            gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco().getX(),gestoreMovimenti.getReBianco().getY(),gestoreMovimenti.getReBianco().getColore(),gestoreMovimenti.getMatrice().getMatrice())==0)||
                       ((turno instanceof Nero) && 
                            gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero().getX(),gestoreMovimenti.getReNero().getY(),gestoreMovimenti.getReNero().getColore(),gestoreMovimenti.getMatrice().getMatrice())==0)){
                        
                        //metto il pezzo premuto nella gestione turno e lo attivo
                        attivaPosizione(x,y);
                    
                        
                    }
                    
                    //se il re è sotto scacco
                    else if(((turno instanceof Bianco) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco().getX(),gestoreMovimenti.getReBianco().getY(),new Bianco(),gestoreMovimenti.getMatrice().getMatrice())==1)||
                            ((turno instanceof Nero) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero().getX(),gestoreMovimenti.getReNero().getY(),new Nero(),gestoreMovimenti.getMatrice().getMatrice())==1)){
                      
                            //se il pezzo può salvare il re
                            //devo controllare la matrice simulata, ma prima devo crearla
                            //matriceTemporanea=gestoreMovimenti.getMatrice();
                            //in base al turno
                            if(turno instanceof Bianco){
                                if(gestoreMovimenti.getMatricePezziChePrevengonoScacco(gestoreMovimenti.getReBianco().getX(),
                                                                                       gestoreMovimenti.getReBianco().getY(),
                                                                                       gestoreMovimenti.getMatrice(),new Bianco())[x][y]==1)
                                    attivaPosizione(x,y);
                                //altrimenti non faccio niente
                            }
                            //Nero
                            else if(gestoreMovimenti.getMatricePezziChePrevengonoScacco(gestoreMovimenti.getReBianco().getX(),
                                                                                       gestoreMovimenti.getReBianco().getY(),
                                                                                       gestoreMovimenti.getMatrice(),new Nero())[x][y]==1)
                                    attivaPosizione(x,y);     
                                //else... altrimenti non faccio niente
                        }
                        
                }
                //else... altrimenti non faccio niente
            }
            //else... altrimenti non faccio niente
        }
        //altrimenti (quindi è stato premuto precedentemente un pezzo valido)
        else{
            //se la posizione dove premo è quella dove ho premuto prima
            if(gestoreMovimenti.getMatrice().getMatrice()[x][y].equals(gestoreTurni.getSpazioAttivato())){
                //imposto come se niente fosse stato premuto
                gestoreTurni.disattiva();
            }
            //altrimenti se è una posizione diversa
            else{
                //se è una locazione vuota
                if(!gestoreMovimenti.getMatrice().getMatrice()[x][y].eOccupato()){
                    //se è una locazione consentita dal pezzo
                    if(gestoreTurni.getSpazioAttivato().getOccupante().spostabileIn(x, y, gestoreMovimenti.getMatrice().getMatrice())){
                        //se spostando il pezzo in questa locazione non ho scacco/scacco matto
                        //(devo simulare la scachiera dopo la mossa)
                        matriceSimulata=gestoreMovimenti.getMatrice();
                        matriceSimulata.spostaPezzo(gestoreTurni.getSpazioAttivato().getOccupante(), x, y);
                        
                        if(((turno instanceof Bianco) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco().getX(),gestoreMovimenti.getReBianco().getY(),new Bianco(),matriceSimulata.getMatrice())==1)||
                            ((turno instanceof Nero) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero().getX(),gestoreMovimenti.getReNero().getY(),new Nero(),matriceSimulata.getMatrice())==1)){
                            //sposto il pezzo, aggiorno la matrice della scacchiera e tutto, cambio il turno
                            gestoreMovimenti.setMatrice(matriceSimulata);
                            gestoreTurni.passaTurno();
                        }
                    }
                    //se non è una locazione consentita dal pezzo
                    else{
                        //imposto come se niente fosse stato premuto
                        gestoreTurni.disattiva();
                    }
                }
                //se è una locazione non vuota
                else{
                    //se contiene un pezzo del colore diverso
                    if(!turno.equals(gestoreMovimenti.getMatrice().getMatrice()[x][y].getOccupante().getColore())){
                        //se non provoca scacco del proprio colore -> scacco matto
                        //(faccio la simulazione)
                        matriceSimulata=gestoreMovimenti.getMatrice();
                        matriceSimulata.spostaPezzo(gestoreTurni.getSpazioAttivato().getOccupante(), x, y);
                        
                        if(((turno instanceof Bianco) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco().getX(),gestoreMovimenti.getReBianco().getY(),new Bianco(),matriceSimulata.getMatrice())==1)||
                            ((turno instanceof Nero) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero().getX(),gestoreMovimenti.getReNero().getY(),new Nero(),matriceSimulata.getMatrice())==1)){
                            //mangia il pezzo in questa locazione
                            gestoreMovimenti.getMatrice().getSpazio(x, y).getOccupante().distruggi();
                            //sposto effettivamente il pezzo 
                            gestoreMovimenti.setMatrice(matriceSimulata);
                            gestoreTurni.passaTurno();
                            //verifico scacco matto
                            if(gestoreMovimenti.scaccoMatto())
                                interfacciaGrafica.finePartita();
                            else
                                gestoreTurni.passaTurno();
                        }
                    }
                    //se contiene un pezzo del colore corrente
                    else{
                        //imposto come se niente fosse stato premuto
                        gestoreTurni.disattiva();
                    }
                }
            }
            
        }
        
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //metto il pezzo premuto nella gestione turno e lo attivo
    private void attivaPosizione(int x,int y){
        gestoreTurni.setSpazioAttivato(gestoreMovimenti.getMatrice().getMatrice()[x][y]);
        gestoreTurni.attiva();
        bloccoBottoniDopoAttivazione(gestoreMovimenti.getMatrice().getSpazio(x, y), gestoreMovimenti.getPossibiliMovimenti(gestoreMovimenti.getMatrice().getSpazio(x,y).getOccupante()));
    }
    
    
}

