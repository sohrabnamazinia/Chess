package game;

import java.util.*;

public abstract class Piece{
    protected int y;
    protected char x;
    protected boolean color;
    private String name;

    public Piece(char x, int y, boolean color, String name){
        setX(x);
        setY(y);
        setColor(color);
        setName(name);
    }

    public void setY(int y){
        this.y = y;
    }

    public void setX(char x){
        this.x = x;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setColor(boolean color){
        this.color = color;
    }

    public int getY(){
        return this.y;
    }

    public char getX(){
        return this.x;
    }

    public String getName(){
        return this.name;
    }

    public boolean getColor(){
        return this.color;
    }

    public static Piece checkTaken(ArrayList<Piece> pieces, char x, int y){
        for(Piece p : pieces){
            if( p.x == x && p.y == y ){

                return p;
            }
        }
        return null;
    }

    public boolean crossMove(char x, int y){
        return false;
    }

    public abstract boolean canMove(char x, int y);

    public abstract boolean checkWay(ArrayList<Piece> pieces, char x, int y);
}