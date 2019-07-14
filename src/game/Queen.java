package game;

import java.util.*;

public class Queen extends Piece{
    public Queen(char x, int y, boolean color, String name){
        super(x, y, color, name);
    }


    public boolean canMove(char x, int y){ // Ignore the presence of other pieces
        if( Rook.canMove(this, x, y) || Bishop.canMove(this, x, y) )
        {

            return true;
        }
        return false;
    }


    public boolean checkWay(ArrayList<Piece> pieces, char x, int y){
        if( Rook.checkWay(this, pieces, x, y) || Bishop.checkWay(this, pieces, x, y) ){

            return true;
        }
        return false;
    }
}