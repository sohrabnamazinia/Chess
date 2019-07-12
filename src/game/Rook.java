package game;

import java.util.*;

/**
 * Rook Class
 */
public class Rook extends Piece{

    public Rook(char x, int y, boolean color, String name){
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
        if( this.x - x == 0 || this.y - y == 0 ){

            return true;
        }
        return false;
    }

    /**
     * Check if can move to (X, Y) : Used for queen class
     * @author Omiid
     * @param p the piece to move
     * @param x The X-Axis
     * @param y The Y-Axis
     * @return true if can move
     */
    public static boolean canMove(Piece p, char x, int y){ // Ignore the presence of other pieces
        if( p.x - x == 0 || p.y - y == 0 ){

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
        int xShift = 0;
        int yShift = 0;
        if( this.x - x == 0 || this.y - y == 0 ){

            if( this.x - x != 0 ){

                xShift = (this.x - x) / Math.abs(x - this.x);
            }
            if( this.y - y != 0 ){

                yShift = (this.y - y) / Math.abs(y - this.y);
            }
            int i = 1;
            boolean flag = true;
            while( this.x != (char)(x + i * xShift) || this.y != y + i * yShift ){
                if( checkTaken(pieces, (char)(x + i * xShift), y + i * yShift) != null && !(checkTaken(pieces, (char)(x + i * xShift), y + i * yShift) instanceof King) ){

                    flag = false;
                }
                i++;
            }
            return flag;
        }else{
            return false;
        }
    }

    /**
     * Check if way is free to go
     *
     * @param p the piece to check
     * @param pieces ArrayList of all pieces
     * @param x The X-Axis
     * @param y The Y-Axis
     * @author Omiid
     * @return true if is free to go
     */
    public static boolean checkWay(Piece p, ArrayList<Piece> pieces, char x, int y){
        int xShift = 0;
        int yShift = 0;
        if( p.x - x != 0 || p.y - y != 0 ){

            if( p.x - x != 0 ){

                xShift = (x - p.x) / Math.abs(x - p.x);
            }
            if( p.y - y != 0 ){

                yShift = (y - p.y) / Math.abs(y - p.y);
            }
            int i = 1;
            while( x != (char)(p.x + i * xShift) || y != p.y + i * yShift ){
                if( checkTaken(pieces, (char)(p.x + i * xShift), p.y + i * yShift) != null && !(checkTaken(pieces, (char)(p.x + i * xShift), p.y + i * yShift) instanceof King) ){

                    return false;
                }
                i++;
            }
            return true;
        }else{
            return false;
        }
    }
}
