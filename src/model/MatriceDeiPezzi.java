package model;

/**
 *
 * @author Viktor
 */

public class MatriceDeiPezzi{ // La Matrice Rappresentante La Scacchiera
    
    private final Spazio[][] scacchiera; // La Matrice Della Scacchiera Con Ogni Cella Di Tipo Spazio
    private final int BIANCO=1;
    private final int NERO=-1;
    
    public MatriceDeiPezzi(){
        
        scacchiera = new Spazio[ 8 ][ 8 ]; // Popolo La Scacchiera Con La Disposizione Iniziale
        
        scacchiera[ 0 ][ 0 ] = new Spazio( new Torre( 0, 0, NERO ) );
        scacchiera[ 7 ][ 0 ] = new Spazio( new Torre( 7, 0, NERO ) );
        scacchiera[ 1 ][ 0 ] = new Spazio( new Cavallo( 1, 0, NERO ) );
        scacchiera[ 6 ][ 0 ] = new Spazio( new Cavallo( 6, 0, NERO ) );
        scacchiera[ 2 ][ 0 ] = new Spazio( new Alfiere( 2, 0, NERO ) );
        scacchiera[ 5 ][ 0 ] = new Spazio( new Alfiere( 5, 0, NERO ) );
        scacchiera[ 3 ][ 0 ] = new Spazio( new Regina( 3, 0, NERO ) );
        scacchiera[ 4 ][ 0 ] = new Spazio( new Re( 4, 0, NERO ) );
        
        for( int i = 0; i < 8; i++ ){
            
            scacchiera[ i ][ 1 ] = new Spazio( new Pedone( i, 1, NERO ) );
        
        } // Fine Creazione Pezzi Neri
        
        scacchiera[ 0 ][ 7 ] = new Spazio( new Torre( 0, 7, BIANCO ) );
        scacchiera[ 7 ][ 7 ] = new Spazio( new Torre( 7, 7, BIANCO ) );
        scacchiera[ 1 ][ 7 ] = new Spazio( new Cavallo( 1, 7, BIANCO ) );
        scacchiera[ 6 ][ 7 ] = new Spazio( new Cavallo( 6, 7, BIANCO ) );
        scacchiera[ 2 ][ 7 ] = new Spazio( new Alfiere( 2, 7, BIANCO ) );
        scacchiera[ 5 ][ 7 ] = new Spazio( new Alfiere( 5, 7, BIANCO ) );
        scacchiera[ 3 ][ 7 ] = new Spazio( new Regina( 3, 7, BIANCO ) );
        scacchiera[ 4 ][ 7 ] = new Spazio( new Re( 4, 7, BIANCO ) );
        
        for( int i = 0; i < 8; i++ ){
            
            scacchiera[ i ][ 6 ] = new Spazio( new Pedone( i, 6, BIANCO ) );
        
        } // Fine Crezione Pezzi Bianchi
        
        for( int i = 2; i < 6; i++ ){   
            for( int j = 0; j < 8; j++ ){    
                scacchiera[j][i]=new Spazio();
            }
        }
    } // Fine matriceDeiPezzi
    
    public MatriceDeiPezzi( Spazio[][] s ){
        
        scacchiera = s;
    
    }
    
    public Spazio[][] getMatrice(){
        
        return scacchiera;
    
    }
    
    public Spazio getSpazio( int x,int y ){
        
        return scacchiera[ x ][ y ];
    
    }

    public void spostaPezzo(Pezzo p,int x, int y){
        int xp=p.getX();
        int yp=p.getY();
        
        /*
        scacchiera[x][y]=scacchiera[xp][yp];
        scacchiera[x][y].setX(x);
        scacchiera[x][y].setY(y);
        */
        p.setX(x);
        p.setY(y);
        scacchiera[xp][yp].setOccupato(false);
        scacchiera[xp][yp].setOccupante(null);
        scacchiera[x][y].setOccupante(p);
        scacchiera[x][y].setOccupato(true);
        
    }
}