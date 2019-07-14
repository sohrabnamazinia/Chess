package game;

import java.util.*;

public class Knight extends Piece
{
    public Knight(char x, int y, boolean color, String name){
        super(x, y, color, name);
    }

    public boolean canMove(char x, int y){
        int X[] = { 2, 1, -1, -2, -2, -1, 1, 2 };
        int Y[] = { 1, 2, 2, 1, -1, -2, -2, -1 };
        for (int i = 0; i < 8; i++) {
            if( y == this.y + Y[i] && x == (char)(this.x + X[i]) ){

                return true;
            }
        }
        return false;
    }

    public boolean checkWay(ArrayList<Piece> pieces, char x, int y){
        return true;
    }
}