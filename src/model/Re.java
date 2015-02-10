package model;

/**
 *
 * @author Viktor
 */
public class Re extends Pezzo{
    private int x,y;//coordinate (-1,-1)=fuori dal campo=distrutto
    private Colore colore;
    private boolean maiMossa=true; //controllo per il primo movimento

    public Re(int x, int y, Colore colore) {
        super(x, y, colore);
    }
}
