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
                System.err.println("GESTORE TURNI: no scacco");
                break;
                
            case 1:
                //Da implementare scacco
                System.err.println("GESTORE TURNI: scacco");
                break;
                
            case 2:
                //Da implementare scacco matto
                System.err.println("GESTORE TURNI: scacco matto");
                break; 
               
        };
        
        
    }
    
    /**
     * Decide di chi è il turno e aggiorna l'interfaccia
     * @param t 
     */
    public void setTurno(int t){
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
        //gestoreBottoni.bloccoBottoniIniziale();
        board.setBoard(gestoreBottoni.getMatriceSpazi());
        switch(board.checkMate(getTurno(), gestoreBottoni.getMatriceSpazi())){
            
            default:
                //In caso di bug
                throw new IllegalArgumentException();
                
            case 0:
                //Da implementare (no scacco)
                System.err.println("GESTORE TURNI: no scacco");
                break;
                
            case 1:
                //Da implementare scacco
                System.err.println("GESTORE TURNI: scacco");
                break;
                
            case 2:
                //Da implementare scacco matto
                System.err.println("GESTORE TURNI: scacco matto");
                break; 
               
        };
    }
    
    /**
     * Se il turno era del bianco lo passa al nero e viceversa
     */
    public void passaTurno(){
        if(turno instanceof Bianco){
            turno=new Nero();
            gestoreBottoni.getInterfacciaGrafica().setMessaggio("Tocca al Nero");
        }
        else{
            turno=new Bianco();
            gestoreBottoni.getInterfacciaGrafica().setMessaggio("Tocca al Bianco");
        }
        //System.err.println("DEBUG:non chiamo il blocco bottoni ");
        //gestoreBottoni.bloccoBottoniIniziale();
        
        System.err.println("DEBUG: inizio chiamata prima di checkmate in passaTurno");
        
        board.setBoard(gestoreBottoni.getMatriceSpazi());
        switch(board.checkMate(getTurno(), gestoreBottoni.getMatriceSpazi())){
            
            default:
                //In caso di bug
                throw new IllegalArgumentException();
                
            case 0:
                //Da implementare (no scacco)
                System.err.println("GESTORE TURNI: no scacco");
                break;
                
            case 1:
                //Da implementare scacco
                System.err.println("GESTORE TURNI: scacco");
                break;
                
            case 2:
                //Da implementare scacco matto
                System.err.println("GESTORE TURNI: scacco matto");
                break; 
               
        };
        
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
