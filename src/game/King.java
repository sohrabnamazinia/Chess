package game;

import java.util.*;

public class King extends Piece
{
    public King(char x, int y, boolean color, String name){
        super(x, y, color, name);
    }

    public boolean canMove(char x, int y){
        if( Math.abs(this.x - x) < 2 && Math.abs(this.y - y) < 2 ){

            return true;
        }
        return false;
    }

    public boolean checkWay(ArrayList<Piece> pieces, char x, int y){
        return true;
    }
}