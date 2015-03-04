
package model;

/**
 *
 * @author Viktor
 */
public class Cavallo extends Pezzo{
    private int x,y;//coordinate (-1,-1)=fuori dal campo=distrutto
    private Colore colore;
    

    public Cavallo(int x, int y, Colore colore) {
        super(x, y, colore);
    }
    
    public Cavallo(int x, int y, int colore) {
        super(x, y, colore);
    }
}
