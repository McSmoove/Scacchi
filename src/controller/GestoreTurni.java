/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Bianco;
import model.Colore;
import model.Nero;
import model.Pezzo; //import non usato NDGaetano
import model.Spazio;
import checkMate.*;
import static model.Colore.BIANCO;
import static model.Colore.NERO;

/**
 * Controlla il turno attuale e il cambio di turno
 * @author Viktor
 */
public class GestoreTurni {
    private Colore turno;
    private boolean attivato;
    private Spazio spazioAttivato;
    GestoreBottoni gestoreBottoni;
    ThreatBoard board = new ThreatBoard();
    
    public void setGestoreBottoni(GestoreBottoni gb){
        gestoreBottoni=gb;
    }
    
    
    public GestoreTurni(GestoreBottoni gb){
        gestoreBottoni=gb;
        turno=new Bianco();
        attivato=false;
    }
    
    public GestoreTurni(){
        turno=new Bianco();
        attivato=false;
    }
    
    public Colore getTurno(){
        return turno;
    }
    
    //Non usato (forse da usare in futuro) NDGaetano
    public void setTurno(Colore t){
        turno=t;
        gestoreBottoni.bloccoBottoniIniziale();
        gestoreBottoni.getInterfacciaGrafica().setMessaggio("Tocca al "+t.toString());
      
        
        board.setBoard(gestoreBottoni.getMatriceSpazi());
        switch(board.checkMate(getTurno(), gestoreBottoni.getMatriceSpazi())){
            
            default:
                //In caso di bug
                throw new IllegalArgumentException();
                
            case 0:
                //Da implementare (no scacco)
                System.err.println("DEBUG: No scacco");
                break;
                
            case 1:
                System.err.println("DEBUG: Scacco");
                //Da implementare scacco
                break;
                
            case 2:
                System.err.println("DEBUG: Scacco matto");
                //Da implementare scacco matto
                break; 
               
        };
        
        
    }
    
    /**
     * Decide di chi è il turno e aggiorna l'interfaccia
     * @param t 
     */
    public void setTurno(int t){
        if(t == BIANCO)
            setTurno(new Bianco());
        else
            setTurno(new Nero());
            
        /*
        if(t==Colore.BIANCO){
            turno=new Bianco();
            gestoreBottoni.getInterfacciaGrafica().setMessaggio("Tocca al Bianco");
        }
        else{
            if(t==Colore.NERO){
                turno=new Nero();
                gestoreBottoni.getInterfacciaGrafica().setMessaggio("Tocca al Nero");
            }
            else
                throw new IllegalArgumentException();
        }  
        gestoreBottoni.bloccoBottoniIniziale();*/
    }
    
    /**
     * Se il turno era del bianco lo passa al nero e viceversa
     */
    public void passaTurno(){
        if(turno instanceof Bianco){
            //turno=new Nero();
            setTurno(NERO);
            gestoreBottoni.getInterfacciaGrafica().setMessaggio("Tocca al Nero");
        }
        else{
            //turno=new Bianco();
            setTurno(BIANCO);
            gestoreBottoni.getInterfacciaGrafica().setMessaggio("Tocca al Bianco");
        }
        System.err.println("DEBUG:non chiamo il blocco bottoni ");
        //gestoreBottoni.bloccoBottoniIniziale();
        
        attivato=false;
    }
    
    /**
     * Inizia a muovere
     */
    public void attiva(){
        attivato=true;
        
    }
    
    /**
     * Finisce di muovere
     */
    public void disattiva(){
        attivato=false;
    }
    
    public Spazio getSpazioAttivato(){
        return spazioAttivato;
    }
    
    public void setSpazioAttivato(Spazio s){
        spazioAttivato=s;
    }
    
    public boolean isAttivato(){
        return attivato;
    }
    
}
