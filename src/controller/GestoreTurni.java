/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Bianco;
import model.Colore;
import model.Nero;
import model.Pezzo;
import model.Spazio;

/**
 *
 * @author Viktor
 */
public class GestoreTurni {
    private Colore turno;
    private boolean attivato;
    private Spazio spazioAttivato;
    
    
    public GestoreTurni(){
        turno=new Bianco();
        attivato=false;
    }
    
    public Colore getTurno(){
        return turno;
    }
    
    public void setTurno(Colore t){
        turno=t;
    }
    
    public void setTurno(int t){
        if(t==Colore.BIANCO)
            turno=new Bianco();
        else{
            if(t==Colore.NERO)
                turno=new Nero();
            else
                throw new IllegalArgumentException();
        }  
    }
    
    public void passaTurno(){
        if(turno instanceof Bianco)
            turno=new Nero();
        else
            turno=new Bianco();
    }
    
    public void attiva(){
        attivato=true;
    }
    
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