

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import game.*;

class newJButton extends JButton
{
    private Piece piece;
    private char X;
    private int Y;

    public newJButton()
    {
        this.piece = null;
        this.setIcon(null);
    }

    public newJButton(char X, int Y)
    {
        this.piece = null;
        this.X = X;
        this.Y = Y;
        this.setIcon(null);
    }

    public newJButton(Piece p, char X, int Y)
    {
        this.piece = p;
        this.X = X;
        this.Y = Y;
        Image img = null;

        try
        {
            //image ...
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
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
