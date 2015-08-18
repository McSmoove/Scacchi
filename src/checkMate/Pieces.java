/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkMate;

/**
 * Enumerazione che indica i pezzi degli scacchi. Toglie qualche problema a
 * fare una classe per ogni pezzo (quest'ultima scelta era valida solo per i 
 * movimenti)
 * @author λ - s(h)ound
 */
public enum Pieces {
    
    //Da 0 a 15, stessa notazione negli array d threat. Uso i nomi inglesi 
    //perché mi garbano assai

    //Tengo TUTTE le pedine nel caso mi servissero in un secondo momento
    king, //0 re
    queen, //1 regina
    bishop, //2 alfiere
    bishop2, //3
    knight, //4 cavallo
    knight2, //5
    rook, //6 torre
    rook2, //7
    pawn, //8 pedoni
    pawn2, //9
    pawn3, //10
    pawn4, //11
    pawn5, //12
    pawn6, //13
    pawn7, //14
    pawn8; //15
    
}
