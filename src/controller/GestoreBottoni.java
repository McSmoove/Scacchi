package controller;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import model.Bianco;
import model.Colore;
import model.MatriceDeiPezzi;
import model.Nero;
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
        int indice;
        JButton b;
        MatriceDeiPezzi matriceTemporanea;
        GestoreMovimenti gestoreTemporaneo;
        Colore turno=gestoreTurni.getTurno();
        JButton[][] matriceScacchiera=interfacciaGrafica.getMatriceBottoni();
        int indiciBottoni[][]=new int[8][8];
        indice = (new Integer(e.getActionCommand()));
        
        indiciBottoni[0][0]=new Integer(matriceScacchiera[0][0].getActionCommand());
        //... e tutti gli altri bottoni
        
        
        //in base al bottone modifico i dati che saranno utilizzati per il ricalcolo della nuova matrice
        //...
        
        b=(JButton)e.getSource();
        if(b.equals(matriceScacchiera[0][0])){
             x=0;
             y=0;
        }
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
                    
                    if(((turno instanceof Bianco) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco().getX(),gestoreMovimenti.getReBianco().getY(),new Bianco())==0)||
                       ((turno instanceof Nero) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero().getX(),gestoreMovimenti.getReNero().getY(),new Nero())==0)){
                        
                        //metto il pezzo premuto nella gestione turno e lo attivo
                        gestoreTurni.setSpazioAttivato(gestoreMovimenti.getMatrice().getMatrice()[x][y]);
                        gestoreTurni.attiva();
                        bloccoBottoniDopoAttivazione(gestoreMovimenti.getMatrice().getSpazio(x, y), gestoreMovimenti.getPossibiliMovimenti(gestoreMovimenti.getMatrice().getSpazio(x,y).getOccupante()));
                    
                        
                    }
                    //se il re è sotto scacco
                    
                    else if(((turno instanceof Bianco) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReBianco().getX(),gestoreMovimenti.getReBianco().getY(),new Bianco())==1)||
                            ((turno instanceof Nero) && gestoreMovimenti.controlloScacco(gestoreMovimenti.getReNero().getX(),gestoreMovimenti.getReNero().getY(),new Nero())==1)){
                      
                            //se il movimento salva il re
                            //devo controllare la matrice simulata, ma prima devo crearla
                            //matriceTemporanea=gestoreMovimenti.getMatrice();
                            
                            
                            
                        }
                        //altrimenti...
                }
            }
        }
        //altrimenti (quindi è stato premuto)
        else{
            //se la posizione dove premo è quella dove ho premuto prima
            if(gestoreMovimenti.getMatrice().getMatrice()[x][y].equals(gestoreTurni.getSpazioAttivato())){
                gestoreTurni.disattiva();
            }
            //altrimenti se è una posizione diversa
            else{
                //se è una locazione vuota
                if(!gestoreMovimenti.getMatrice().getMatrice()[x][y].eOccupato()){
                    //se è una locazione consentita dal pezzo
                    
                }
            }
            
        }
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
