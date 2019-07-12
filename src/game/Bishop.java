package game;

import java.util.*;

public class Bishop extends Piece
{
    public Bishop(char x, int y, boolean color, String name){
        super(x, y, color, name);
    }

    public boolean canMove(char x, int y){
        if( Math.abs(this.x - x) != 0 && Math.abs(this.y - y) != 0 && Math.abs((float)(this.x - x) / (this.y - y)) == 1 )
        {
            return true;
        }
        return false;
    }

    public static boolean canMove(Piece p, char x, int y){
        if(  Math.abs(p.x - x) != 0 && Math.abs(p.y - y) != 0 && Math.abs((float)(p.x - x) / (p.y - y)) == 1 )
        {
            return true;
        }
        return false;
    }

    public static boolean checkWay(Piece p, ArrayList<Piece> pieces, char x, int y)
    {
        if( p.x - x == 0 )
        {
            return false;
        }
        if( p.y - y == 0 )
        {
            return false;
        }
        int xShift = (x - p.x) / Math.abs(x - p.x);
        int yShift = (y - p.y) / Math.abs(y - p.y);
        int i = 1;
        while( x != (char)(p.x + i * xShift) && y != p.y + i * yShift )
        {
            if( checkTaken(pieces, (char)(p.x + i * xShift), p.y + i * yShift) != null && !(checkTaken(pieces, (char)(p.x + i * xShift), p.y + i * yShift) instanceof King) )
            {
                return false;
            }
            i++;
        }
        return true;
    }

    public boolean checkWay(ArrayList<Piece> pieces, char x, int y)
    {
        int xShift = (x - this.x) / Math.abs(x - this.x);
        int yShift = (y - this.y) / Math.abs(y - this.y);
        int i = 1;
        while( x != (char)(this.x + i * xShift) && y != this.y + i * yShift )
        {
            if( checkTaken(pieces, (char)(this.x + i * xShift), this.y + i * yShift) != null && !(checkTaken(pieces, (char)(this.x + i * xShift), this.y + i * yShift) instanceof King) )
            {
                return false;
            }
            i++;
        }
        return true;
    }
}