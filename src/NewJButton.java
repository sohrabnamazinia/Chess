

import game.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NewJButton extends JButton
{
    private Piece piece;
    private char X;
    private int Y;

    public NewJButton()
    {
        this.piece = null;
        this.setIcon(null);
    }

    public NewJButton(char X, int Y)
    {
        this.piece = null;
        this.X = X;
        this.Y = Y;
        this.setIcon(null);
    }

    public NewJButton(Piece p, char X, int Y)
    {
        this.piece = p;
        this.X = X;
        this.Y = Y;
        Image img = null;

        try
        {
           //it will soon be completed by next push
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        this.setIcon(new ImageIcon(img));
    }

    public Piece getPiece(){
        return this.piece;
    }

    public void setPiece(Piece p){
        this.piece = p;
    }

    public char getNewX(){
        return this.X;
    }

    public int getNewY(){
        return this.Y;
    }
}
