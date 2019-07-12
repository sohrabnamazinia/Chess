package game;

import java.util.*;

public class Queen extends Piece{
    public Queen(char x, int y, boolean color, String name){
        super(x, y, color, name);
    }

    /**
     * Check if can move to (X, Y)
     * @author Omiid
     * @param x The X-Axis
     * @param y The Y-Axis
     * @return true if can move
     */
    public boolean canMove(char x, int y){ // Ignore the presence of other pieces
        if( Rook.canMove(this, x, y) || Bishop.canMove(this, x, y) )
        {

            return true;
        }
        return false;
    }

    /**
     * Check if way is free to go
     * @param pieces ArrayList of all pieces
     * @param x The X-Axis
     * @param y The Y-Axis
     * @author Omiid
     * @return true if is free to go
     */
    public boolean checkWay(ArrayList<Piece> pieces, char x, int y){
        if( Rook.checkWay(this, pieces, x, y) || Bishop.checkWay(this, pieces, x, y) ){

            return true;
        }
        return false;
    }
}
