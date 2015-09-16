/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkMate;

/**
 * Contiene le informazioni sul pezzo contenuto nella casella, il colore
 * del pezzo e il threat. Attenzione al parametro colore! E' un booleano che a
 * true significa nero. (Viene usato in tutto il package).
 * @author λ - s(h)ound
 */
public class Cell {
    
    private Pieces contains;
    private boolean color; //False bianco, true nero. Se non contiene pezzi non importa
    private boolean whiteThreat[];
    private boolean blackThreat[];
    
    /**
     * Costruttore principale. Inizializza il threat a 0.
     */
    public Cell(){
        
        //il contains inizializzato evita eventuali bachi
        contains = Pieces.empty;
        whiteThreat[16] = false;
        blackThreat[16] = false;
   
    }
    
    /**
     * Setta quale pezzo è contenuto nella casella
     * @param p il pezzo da settare.
     *          NB: di tipo Pieces, che è un'enumerazione
     * @param c il colore del pezzo. Booleano (true = nero)
     */
    public void setPiece(Pieces p, boolean c){
        
        contains = p;
        color = c;
        
    }
    
    /**
     * Setta il threat della casella
     * @param blackColor booleano. True = setta il nero
     * @param threat booleano, la casella è in threat?
     * @param piece intero che segna quale casella dell'array di threat segnare
     */
    public void setThreat(boolean blackColor, boolean threat, int piece){
        
        if(!blackColor)
            whiteThreat[piece] = threat;
        
        else
            blackThreat[piece] = threat;
        
    }
    
    /**
     * 
     * @return il pezzo (tipo Pieces) che contiene questa casella 
     */
    public Pieces getPiece(){
        
        return contains;
        
    }
    
    /**
     * 
     * @return booleano del colore contenuto in questa casella. Ovviamente se
     * non ci sono pedine non verrà preso in considerazione negli altri metodi.
     */
    public boolean getColor(){
        
        return color;
        
    }
    
    /**
     * 
     * @param c Il colore del threat che devo cercare
     * @param p Il pezzo del threat che devo cercare
     * @return 
     */
    public boolean getThreat(boolean c, int p){
        
        if(!c)
            return whiteThreat[p]; 
        
        else
            return blackThreat[p];
        
    }
    
}
