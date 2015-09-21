/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkMate;
import controller.*;
import java.util.LinkedList;
import model.*;
import view.*;

/**
 * La threatBoard segnala tutte le caselle dove il threat è uguale a True. In
 * pratica è la base dello scacco matto.
 * 
 * Nota: ho pensato che può essere usata anche per semplificare i movimenti:
 * d'altro canto un pezzo si può muovere solo dove il suo threat vale 1.
 * 
 * Per sicurezza ho importato tutto (non si sa mai che possa servire)
 * 
 * @author λ - s(h)ound
 */
public class ThreatBoard {
    
    private Cell[][] board;
    
    public ThreatBoard(){
        board=new Cell[8][8];
        //Inizializzo la board
        for(int y = 0; y < 8; y++){ 
            for (int x = 0; x < 8; x++){
                System.err.println("board "+x+" "+y);
                board[x][y] = new Cell();
                
            }
            
        }
    
    }
    /*
    public ThreatBoard(Spazio[][] m){
        
        //da implementare...
        //... o forse no...
        
    
    }*/
    
    /**
     * Setto la board con i pezzi che passo col parametro m.
     * @param m matrice quadrata di tipo spazio. serve per sapere l'attuale stato
     *          della board
     */
    public void setBoard(Spazio[][] m){
        
        //Pedine bianche
        int whiteBishops = 0;
        int whiteKnights = 0;
        int whiteRooks = 0;
        int whitePawns = 0;
        
        //Pedine nere
        int blackBishops = 0;
        int blackKnights = 0;
        int blackRooks = 0;
        int blackPawns = 0;
        
        for(int y = 0; y < 8; y++){ 
            for (int x = 0; x < 8; x++){
            
                for(int i = 0; i < 16; i++){
                    
                    board[x][y].setThreat(false, false, i); //sarebbe???
                    board[x][y].setThreat(true, false, i); //significato di questa riga???????
                    
                }
            
            }
        }
        
        for(int y = 0; y < 8; y++){ 
            for (int x = 0; x < 8; x++){
                
                if(m[x][y].getOccupante().getColore() instanceof Bianco){
                    
                    if(m[x][y].getOccupante() instanceof Re)
                        board[x][y].setPiece(Pieces.king, false);
                
                    if(m[x][y].getOccupante() instanceof Regina)
                        board[x][y].setPiece(Pieces.queen, false);
                    
                    if(m[x][y].getOccupante() instanceof Alfiere){
                        if(whiteBishops == 0){
                            board[x][y].setPiece(Pieces.bishop, false);
                            whiteBishops++;
                        }
                        
                        else
                            board[x][y].setPiece(Pieces.bishop2, false);
                    }
                
                    if(m[x][y].getOccupante() instanceof Cavallo){
                        if(whiteKnights == 0){
                            board[x][y].setPiece(Pieces.knight, false);
                            whiteKnights++;
                        }
                        
                        else
                            board[x][y].setPiece(Pieces.knight2, false);
                    }
                    
                    if(m[x][y].getOccupante() instanceof Torre){
                        if(whiteRooks == 0){
                            board[x][y].setPiece(Pieces.rook, false);
                            whiteRooks++;
                        }
                        
                        else
                            board[x][y].setPiece(Pieces.rook2, false);
                    }
                    
                    if(m[x][y].getOccupante() instanceof Pedone){
                        if(whitePawns == 0){
                            board[x][y].setPiece(Pieces.pawn, false);
                            whitePawns++;
                        }
                        
                        else if(whitePawns == 1){
                            board[x][y].setPiece(Pieces.pawn2, false);
                            whitePawns++;
                        }
                        
                        else if(whitePawns == 2){
                            board[x][y].setPiece(Pieces.pawn3, false);
                            whitePawns++;
                        }
                        
                        else if(whitePawns == 3){
                            board[x][y].setPiece(Pieces.pawn4, false);
                            whitePawns++;
                        }
                        
                        else if(whitePawns == 4){
                            board[x][y].setPiece(Pieces.pawn5, false);
                            whitePawns++;
                        }
                        
                        else if(whitePawns == 5){
                            board[x][y].setPiece(Pieces.pawn6, false);
                            whitePawns++;
                        }
                        
                        else if(whitePawns == 6){
                            board[x][y].setPiece(Pieces.pawn7, false);
                            whitePawns++;
                        }
                        
                        else
                            board[x][y].setPiece(Pieces.pawn8, false);
                    }
                    
                }
                
                if(m[x][y].getOccupante().getColore() instanceof Nero){
                    
                    if(m[x][y].getOccupante() instanceof Re)
                        board[x][y].setPiece(Pieces.king, true);
                
                    if(m[x][y].getOccupante() instanceof Regina)
                        board[x][y].setPiece(Pieces.queen, true);
                    
                    if(m[x][y].getOccupante() instanceof Alfiere){
                        if(blackBishops == 0){
                            board[x][y].setPiece(Pieces.bishop, true);
                            blackBishops++;
                        }
                        
                        else
                            board[x][y].setPiece(Pieces.bishop2, true);
                    }
                
                    if(m[x][y].getOccupante() instanceof Cavallo){
                        if(blackKnights == 0){
                            board[x][y].setPiece(Pieces.knight, true);
                            blackKnights++;
                        }
                        
                        else
                            board[x][y].setPiece(Pieces.knight2, true);
                    }
                    
                    if(m[x][y].getOccupante() instanceof Torre){
                        if(blackRooks == 0){
                            board[x][y].setPiece(Pieces.rook, true);
                            blackRooks++;
                        }
                        
                        else
                            board[x][y].setPiece(Pieces.rook2, true);
                    }
                    
                    if(m[x][y].getOccupante() instanceof Pedone){
                        if(blackPawns == 0){
                            board[x][y].setPiece(Pieces.pawn, true);
                            blackPawns++;
                        }
                        
                        else if(blackPawns == 1){
                            board[x][y].setPiece(Pieces.pawn2, true);
                            blackPawns++;
                        }
                        
                        else if(blackPawns == 2){
                            board[x][y].setPiece(Pieces.pawn3, true);
                            blackPawns++;
                        }
                        
                        else if(blackPawns == 3){
                            board[x][y].setPiece(Pieces.pawn4, true);
                            blackPawns++;
                        }
                        
                        else if(blackPawns == 4){
                            board[x][y].setPiece(Pieces.pawn5, true);
                            blackPawns++;
                        }
                        
                        else if(blackPawns == 5){
                            board[x][y].setPiece(Pieces.pawn6, true);
                            blackPawns++;
                        }
                        
                        else if(blackPawns == 6){
                            board[x][y].setPiece(Pieces.pawn7, true);
                            blackPawns++;
                        }
                        
                        else
                            board[x][y].setPiece(Pieces.pawn8, true);
                    }
                    
                }
                
            }
        }
        
        setThreat();
        
    }
    
    private void setThreat(){
        
        for(int y = 0; y < 8; y++){ 
            for (int x = 0; x < 8; x++){
                
                /*
                Nota per i cari threat definiti sotto:  nota che se una pedina 
                bianca minaccia un'altra pedina bianca non fa una grande 
                differenza. L'unico controllo che bisogna fare è che minacci una
                pedina nera e che non superi scacchi che non può superare.
                Questo accorgimento semplifica pedone, cavallo e re.
                */
                switch(board[x][y].getPiece()){
                    
                    //Per sicurezza, in caso di bug
                    default:
                        break;
                        
                    case king:
                        kingThreat(x, y, board[x][y].getColor());
                        break;
                        
                    case queen:
                        queenThreat(x, y, board[x][y].getColor());
                        break;
                    
                    case bishop:
                        bishopThreat(x, y, 2, board[x][y].getColor());
                        break;
                    
                    case bishop2:
                        bishopThreat(x, y, 3, board[x][y].getColor());
                        break;
                        
                    case knight:
                        knightThreat(x, y, 4, board[x][y].getColor());
                        break;
                        
                    case knight2:
                        knightThreat(x, y, 5, board[x][y].getColor());
                        break;
                        
                    case rook:
                        rookThreat(x, y, 6, board[x][y].getColor());
                        break;
                        
                    case rook2:
                        rookThreat(x, y, 7, board[x][y].getColor());
                        break;
                        
                    case pawn:
                        pawnThreat(x, y, 8, board[x][y].getColor());
                        break;
                        
                    case pawn2:
                        pawnThreat(x, y, 9, board[x][y].getColor());
                        break;
                        
                    case pawn3:
                        pawnThreat(x, y, 10, board[x][y].getColor());
                        break;
                        
                    case pawn4:
                        pawnThreat(x, y, 11, board[x][y].getColor());
                        break;
                        
                    case pawn5:
                        pawnThreat(x, y, 12, board[x][y].getColor());
                        break;
                        
                    case pawn6:
                        pawnThreat(x, y, 13, board[x][y].getColor());
                        break;
                        
                    case pawn7:
                        pawnThreat(x, y, 14, board[x][y].getColor());
                        break;
                        
                    case pawn8:
                        pawnThreat(x, y, 15, board[x][y].getColor());
                        break;
                        
                }
                
            }
            
        }
        
    }
    
    private void kingThreat(int x, int y, boolean color){
        
        //Può essere visto come il caso del cavallo
        //Ricorda: se la casella è occupata non è un problema, poiché
        //- Se è nemica, la pedina può mangiare il pezzo
        //Se è alleata non potrà muoversi lì.
        if(x + 1 <= 7 && y + 1 <= 7){
            board[x + 1][y + 1].setThreat(color, true, 0);
            board[x + 1][y].setThreat(color, true, 0);
        }
        
        if(x + 1 <= 7 && y - 1 >= 0){
            board[x + 1][y - 1].setThreat(color, true, 0);
            board[x][y - 1].setThreat(color, true, 0);
        }
        
        if(x - 1 >= 0 && y - 1 >= 0){
            board[x - 1][y - 1].setThreat(color, true, 0);
            board[x - 1][y].setThreat(color, true, 0);
        }
        
        if(x - 1 >= 0 && y + 1 <= 7){
            board[x - 1][y + 1].setThreat(color, true, 0);
            board[x][y + 1].setThreat(color, true, 0);
        }    
        
    }
    
    private void queenThreat(int x, int y, boolean color){
        
        //Unione di torre e alfiere
        board[x][y].setThreat(color, true, 1);
        
        bishopThreat(x, y, 1, true);
        rookThreat(x, y, 1, true);
        
    }
    
    /**
     * @param p ci sono due alfieri, bisogna riconoscere quello giusto
     * @param color 
     */
    private void bishopThreat(int x, int y, int p, boolean color){
        
        int tempX = x;
        board[x][y].setThreat(color, true, p);
        
        while(x <= 7 && y <= 7){
        
            x++;
            y++;
        
            //Blocca quando trova un pezzo sulla strada
            if(board[x][y].getPiece() != null)
                break;
            
        }
        
        int tempY = y;
        
        while(x >= 0 && y >= 0){
            
            board[x][y].setThreat(color, true, p);
            x--;
            y--;
            
            //Blocca quando trova un pezzo sulla strada
            if(board[x][y].getPiece() != null)
                break;
            
        }
        
        x = tempX;
        y = tempY;
        
        while(x >= 0){
         
            x--;
        
            //Blocca quando trova un pezzo sulla strada
            if(board[x][y].getPiece() != null)
                break;
            
        }
            
        while(x >= 0 && y >= 0){
            
            board[x][y].setThreat(color, true, p);
            x++;
            y--;
            
            //Blocca quando trova un pezzo sulla strada
            if(board[x][y].getPiece() != null)
                break;
            
        }
        
    }
    
    private void knightThreat(int x, int y, int p, boolean color){
        
        board[x][y].setThreat(color, true, p);
        
        //Bisogna per forza fare tutti i controlli
        if(x + 1 <= 7 && y + 2 <= 7)
            board[x + 1][y + 2].setThreat(color, true, p);
        
        if(x + 2 <= 7 && y + 1 <= 7)
            board[x + 2][y + 1].setThreat(color, true, p);
        
        if(x + 2 <= 7 && y - 1 >= 0)
            board[x + 2][y - 1].setThreat(color, true, p);
        
        if(x + 1 <= 7 && y - 2 >= 0)
            board[x + 1][y - 2].setThreat(color, true, p);
        
        if(x - 1 >= 0 && y - 2 >= 0)
            board[x - 1][y - 2].setThreat(color, true, p);
        
        if(x - 2 >= 0 && y - 1 >= 0)
            board[x - 2][y - 1].setThreat(color, true, p);
        
        if(x - 2 >= 0 && y + 1 <= 7)
            board[x - 2][y + 1].setThreat(color, true, p);
        
        if(x - 1 >= 0 && y + 2 <= 7)
            board[x - 1][y + 2].setThreat(color, true, p);
        
    }
    
    private void rookThreat(int x, int y, int p, boolean color){
        
        int temp = x;
        board[x][y].setThreat(color, true, p);
        
        while(x <= 7){
            
            x++;
        
            //Blocca quando trova un pezzo sulla strada
            if(board[x][y].getPiece() != null)
                break;
            
        }
        
        while(x >= 0){
            
            //Ricorda: se la casella è occupata non è un problema, poiché
            //- Se è nemica, la pedina può mangiare il pezzo
            //Se è alleata non potrà muoversi lì.
            board[x][y].setThreat(color, true, p);
            x--;
        
            //Blocca quando trova un pezzo sulla strada
            if(board[x][y].getPiece() != null)
                break;
        
        }
        
        x = temp;
        
        while(y <= 7){
         
            y++;
        
            //Blocca quando trova un pezzo sulla strada
            if(board[x][y].getPiece() != null)
                break;
            
        }
            
        while(y >= 0){
            
            board[x][y].setThreat(color, true, p);
            y--;
        
            //Blocca quando trova un pezzo sulla strada
            if(board[x][y].getPiece() != null)
                break;
        
        }
        
    }
    
    private void pawnThreat(int x, int y, int p, boolean color){
        
        //Ricorda: se la casella è occupata non è un problema, poiché
        //- Se è nemica, la pedina può mangiare il pezzo
        //Se è alleata non potrà muoversi lì.
        //Per il pedone c'è da fare anche il controllo del colore.
        board[x][y].setThreat(color, true, p);
        
        if(!color){
            
            if(y + 1 <= 7){
            
                if(x + 1 <= 7)
                    board[x + 1][y + 1].setThreat(color, true, p);
            
                if(x - 1 >= 0)
                    board[x - 1][y + 1].setThreat(color, true, p);
            
            }
            
        }
        
        if(color){
            
            if(y - 1 >= 0){
            
                if(x + 1 <= 7)
                    board[x + 1][y - 1].setThreat(color, true, p);
            
                if(x - 1 >= 0)
                    board[x - 1][y - 1].setThreat(color, true, p);
            
            }
            
        }
        
    }
    
    /**
     * Controlla se è scacco matto: prima di tutto osserva quanti pezzi minacciano
     * il re. Se almeno un pezzo lo minaccia, comincia col bloccare tutti gli scacchi.
     * A questo punto controlla se il re si può muovere e lo blocca in caso non potesse.
     * Se solo un pezzo minaccia il re procede col trovare tutti i pezzi che possono 
     * salvare il re e li sblocca. Se tutti i pezzi sono bloccati è scacco matto.
     * NON CONTROLLA se i movimenti fatti dal giocatore sono accettabili. è da implementare
     * a parte.
     * @param c l'attuale turno
     * @param m l'attuale disposizione dei pezzi  OCIO: è una matrice quadrata.
     * @return un intero tra 0 e 2 (compresi).
     * 0: no scacco;
     * 1: scacco;
     * 2: scacco matto;
     */
    public int checkMate(Colore c, Spazio[][] m){
        
        //Posizione del re
        int actualX = 0;
        int actualY = 0;
        
        //Quanti scacchi minacciano il re
        int danger = 0;
        
        //Turno del nero?
        boolean black;
        
        //Livello di scacco
        int result = 0;
        
        //Lista dei pezzi che minacciano il re
        LinkedList list = new LinkedList();
        
        //Devo vedere che turno è e adattarlo alle regole di questo package
        if(c instanceof Bianco)
            black = false;
        
        else
            black = true;
        
        if(!black){
            
            //Cerco il re
            for(int y = 0; y < 8; y++){
                for(int x = 0; x < 8; x++){
                    
                    if(board[x][y].getPiece() == Pieces.king &&
                            !board[x][y].getColor()){
                        
                        actualX = x;
                        actualY = y;
                        
                        break;
                   
                    }
                    
                }
            }
            
            //Primo passo: controlliamo chi e quanti minacciano il re
            for(int p = 0; p < 16; p++){
                
                if(board[actualX][actualY].getThreat(black, p)){
                    list.add(p);
                    danger++;
                }
            
            }
            
            //Il re è in scacco. Un piccolo controllo da fare
            if(danger > 0){
                
                result++;
                
                //Blocco tutti gli scacchi e sblocco in seguito solo quelli che salvano il
                //re
                for(int y = 0; y < 8; y++){
                    for(int x = 0; x < 8; x++){
                        
                        //Odio gli instanceof... lsciamelo dire
                        if(m[x][y].getOccupante().getColore() instanceof Bianco
                                && !(m[x][y].getOccupante() instanceof Re))
                            m[x][y].getOccupante().setLock(true);
                        
                    }
                }
                
            }
            
            //Secondo passo: controlliamo se il re è bloccato
            if(checkKingThreat(actualX, actualY, !black))
                m[actualX][actualY].getOccupante().setLock(true);

            //Controllo il livello di danger (se 0 al massimo blocco il re)
            if(danger == 1){
                
                findSafePieces(list, m, !black);
                
            }
             
            //Cerco i pezzi bloccati
            for(int y = 0; y < 8; y++){
                for(int x = 0; x < 8; x++){
                
                    if(!(m[x][y].getOccupante().getLock()))
                        return result;
                
                }
               
            }
            
            result++;
            return result;
            
        }
        
        else{
            
            //Cerco il re
            for(int y = 0; y < 8; y++){
                for(int x = 0; x < 8; x++){
                    
                    if(board[x][y].getPiece() == Pieces.king &&
                            board[x][y].getColor()){
                        
                        actualX = x;
                        actualY = y;
                        
                        break;
                   
                    }
                    
                }
            }
            
            //Primo passo: controlliamo chi e quanti minacciano il re
            for(int p = 0; p < 16; p++){
                
                if(board[actualX][actualY].getThreat(!black, p)){
                    list.add(p);
                    danger++;
                }
            
            }
            
            //Il re è in scacco. Un piccolo controllo da fare
            if(danger > 0){
                
                result++;
                
                //Blocco tutti gli scacchi e sblocco in seguito solo quelli che salvano il
                //re
                for(int y = 0; y < 8; y++){
                    for(int x = 0; x < 8; x++){
                        
                        //Odio gli instanceof... lsciamelo dire
                        if(m[x][y].getOccupante().getColore() instanceof Nero
                                && !(m[x][y].getOccupante() instanceof Re))
                            m[x][y].getOccupante().setLock(true);
                        
                    }
                }
                
            }
            
            //Secondo passo: controlliamo se il re è bloccato
            if(checkKingThreat(actualX, actualY, black))
                m[actualX][actualY].getOccupante().setLock(true);

            //Controllo il livello di danger (se 0 al massimo blocco il re)
            if(danger == 1){
                
                findSafePieces(list, m, black);
                
            }
             
            //Se tutto bloccato SCACCO MATTO
            //Cerco i pezzi bloccati
            for(int y = 0; y < 8; y++){
                for(int x = 0; x < 8; x++){
                
                    if(!(m[x][y].getOccupante().getLock()))
                        return result;
                
                }
               
            }
            
            result++;
            return result;
        
        }
        
    }
    
    private boolean checkKingThreat(int x, int y, boolean c){
        
        int moves = 8;
        
        if(x + 1 <= 7 && y + 1 <= 7){
            
            for(int p = 0; p < 16; p++){
                if(board[x + 1][y + 1].getThreat(!c, p)){
                    moves--;
                    break;
                }
                    
            }
            
            for(int p = 0; p < 16; p++){
                if(board[x + 1][y].getThreat(!c, p)){
                    moves--;
                    break;
                }
                    
            }
            
        }    
        
        if(x + 1 <= 7 && y - 1 >= 0){
            
            for(int p = 0; p < 16; p++){
                if(board[x + 1][y - 1].getThreat(!c, p)){
                    moves--;
                    break;
                }
                    
            }
           
            for(int p = 0; p < 16; p++){
                if(board[x][y - 1].getThreat(!c, p)){
                    moves--;
                    break;
                }
                    
            }
            
        }  
        
        if(x - 1 >= 0 && y - 1 >= 0){
            
            for(int p = 0; p < 16; p++){
                if(board[x - 1][y - 1].getThreat(!c, p)){
                    moves--;
                    break;
                }
                    
            }
            
            for(int p = 0; p < 16; p++){
                if(board[x - 1][y].getThreat(!c, p)){
                    moves--;
                    break;
                }
                    
            }
            
        }
        
        if(x - 1 >= 0 && y + 1 <= 7){
            
            for(int p = 0; p < 16; p++){
                if(board[x - 1][y + 1].getThreat(!c, p)){
                    moves--;
                    break;
                }
                    
            }
            
            for(int p = 0; p < 16; p++){
                if(board[x][y + 1].getThreat(!c, p)){
                    moves--;
                    break;
                }
                    
            }
            
        }
            
        if(moves == 0)
            return true;
        
        else
            return false;
        
    }
    
    private void findSafePieces(LinkedList list, Spazio[][] m, boolean c){
        
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
             
                for(int i = 0; i < list.size(); i++){
                    
                    if(board[x][y].getThreat(!c, (int)list.get(i))){
                        
                        for(int p = 0; p < 16; p++){
                            
                            if(board[x][y].getThreat(c, p)){
                                
                                switch(p){
                                    
                                    default:
                                        throw new IllegalArgumentException();
                                        
                                    case 0:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.king)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 1:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.queen)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 2:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.bishop)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 3:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.bishop2)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 4:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.knight)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 5:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.knight2)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 6:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.rook)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 7:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.rook2)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 8:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.pawn)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 9:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.pawn2)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 10:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.pawn3)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 11:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.pawn4)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 12:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.pawn5)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 13:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.pawn6)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 14:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.pawn7)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                    case 15:
                                        for(int y2 = 0; y2 < 8; y2++){
                                            for(int x2 = 0; x2 < 8; x2++){
                                                
                                                if(board[x2][y2].getPiece() == Pieces.pawn8)
                                                    m[x][y].getOccupante().setLock(false);
                                                
                                            }
                                        }
                                        break;
                                        
                                }
                                
                            }
                        }
                    }
                    
                }
                
            }
        }
    }
    
}
