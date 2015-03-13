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
    GestoreBottoni gestoreBottoni;
    
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
    
    public void setTurno(Colore t){
        turno=t;
        gestoreBottoni.bloccoBottoniIniziale();
        gestoreBottoni.getInterfacciaGrafica().setMessaggio("Tocca al "+t.toString());
    }
    
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
        gestoreBottoni.bloccoBottoniIniziale();
    }
    
    public void passaTurno(){
        if(turno instanceof Bianco){
            turno=new Nero();
            gestoreBottoni.getInterfacciaGrafica().setMessaggio("Tocca al Nero");
        }
        else{
            turno=new Bianco();
            gestoreBottoni.getInterfacciaGrafica().setMessaggio("Tocca al Bianco");
        }
        gestoreBottoni.bloccoBottoniIniziale();
        //volevo usare repaint
        gestoreBottoni.getInterfacciaGrafica().notify();
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
