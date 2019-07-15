import chatRoom.Client;
import chatRoom.Server;
import game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

class Chess implements MouseListener {
    private Socket socket = null;
    private DataInputStream input   = null;
    private DataOutputStream output = null;
    private Board board = null;
    private boolean me;
    private String color = "W";
    private boolean isSelected = false;
    private NewJButton inMove = null;
    private AppFrame frame;
    private ArrayList<NewJButton> btns = new ArrayList<NewJButton>();
    private ArrayList<NewJButton> whiteLost = new ArrayList<>();
    private ArrayList<NewJButton> blackLost = new ArrayList<>();
    private JLabel caption;
    public boolean isServer;

    public Chess(String me, Socket socket, boolean isServer) throws Exception
    {
        this.isServer = isServer;

        if( me.equals("W") )
        {
            this.me = true;
        }
        else
        {
            this.me = false;
        }
        this.socket = socket;
        this.input  = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        this.output = new DataOutputStream(this.socket.getOutputStream());

        if(isServer)
        {
            Server.main(null);
        }
        else
        {
            Client.main(null);
        }

    }

    public void run() throws IOException
    {
        AppFrame frame = new AppFrame("Chess Frame");
        frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        // Biggest Panel
        JPanel fullPanel = new JPanel(new BorderLayout());
        // Board Panel
        JPanel boardPanel = new JPanel(new GridLayout(8,8));
        ArrayList<Piece> whitePieces = new ArrayList<Piece>();
        ArrayList<Piece> blackPieces = new ArrayList<Piece>();
        if( this.me )
        {
            for(int i = 1; i < 9; i++)
            {
                for(char j = 'A'; j < 'I'; j++)
                {
                    NewJButton btn = null;
                    if( i == 8 )
                    {
                        if( j == 'A' || j == 'H' )
                        {
                            Rook newRook = new Rook(j, i, true, "WR");
                            whitePieces.add(newRook);
                            btn = new NewJButton(newRook, j, i);
                        }
                        else if( j == 'B' || j == 'G' )
                        {
                            Knight newKnight = new Knight(j, i, true, "WN");
                            whitePieces.add(newKnight);
                            btn = new NewJButton(newKnight, j, i);
                        }else if( j == 'C' || j == 'F' ){

                            Bishop newBishop = new Bishop(j, i, true, "WB");
                            whitePieces.add(newBishop);
                            btn = new NewJButton(newBishop, j, i);
                        }
                        else if( j == 'D' )
                        {
                            Queen whiteQueen = new Queen(j, i, true,"WQ");
                            whitePieces.add(whiteQueen);
                            btn = new NewJButton(whiteQueen, j, i);
                        }
                        else if( j == 'E' )
                        {
                            King whiteKing = new King(j, i, true, "WK");
                            whitePieces.add(whiteKing);
                            btn = new NewJButton(whiteKing, j, i);
                        }
                    }
                    else if( i == 7 )
                    {
                        Pawn newPawn = new Pawn(j, i, true, "WP");
                        whitePieces.add(newPawn);
                        btn = new NewJButton(newPawn, j, i);
                    }
                    else if( i == 1 )
                    {
                        if( j == 'A' || j == 'H' )
                        {
                            Rook newRook = new Rook(j, i, false, "BR");
                            blackPieces.add(newRook);
                            btn = new NewJButton(newRook, j, i);
                        }
                        else if( j == 'B' || j == 'G' )
                        {
                            Knight newKnight = new Knight(j, i, false, "BN");
                            blackPieces.add(newKnight);
                            btn = new NewJButton(newKnight, j, i);
                        }
                        else if( j == 'C' || j == 'F' )
                        {

                            Bishop newBishop = new Bishop(j, i, false, "BB");
                            blackPieces.add(newBishop);
                            btn = new NewJButton(newBishop, j, i);
                        }else if( j == 'D' ){

                            Queen blackQueen = new Queen(j, i, false,"BQ");
                            blackPieces.add(blackQueen);
                            btn = new NewJButton(blackQueen, j, i);
                        }else if( j == 'E' ){
                            King blackKing = new King(j, i, false, "BK");
                            blackPieces.add(blackKing);
                            btn = new NewJButton(blackKing, j, i);
                        }
                    }
                    else if( i == 2 )
                    {
                        Pawn newPawn = new Pawn(j, i, false, "BP");
                        blackPieces.add(newPawn);
                        btn = new NewJButton(newPawn, j, i);
//                        btn = new newJButton(j, i);
                    }
                    else
                    {
                        btn = new NewJButton(j, i);
                    }
                    this.board = new Board(whitePieces, blackPieces);
                    this.btns.add(btn);
                    btn.addMouseListener(this);
                    btn.setFocusable(false);
                    if( (i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0) )
                    {
                        btn.setBackground(new Color(219,217,164));
                    }
                    else
                    {
                        btn.setBackground(new Color(106, 55, 7));
                    }
                    boardPanel.add(btn);
                }
            }
        }
        else
        {
            for(int i = 8; i >= 1; i--)
            {
                for(char j = 'H'; j >= 'A'; j--)
                {
                    NewJButton btn = null;
                    if( i == 8 )
                    {
                        if( j == 'A' || j == 'H' )
                        {
                            Rook newRook = new Rook(j, i, true, "WR");
                            whitePieces.add(newRook);
                            btn = new NewJButton(newRook, j, i);
                        }
                        else if( j == 'B' || j == 'G' )
                        {
                            Knight newKnight = new Knight(j, i, true, "WN");
                            whitePieces.add(newKnight);
                            btn = new NewJButton(newKnight, j, i);
                        }
                        else if( j == 'C' || j == 'F' )
                        {
                            Bishop newBishop = new Bishop(j, i, true, "WB");
                            whitePieces.add(newBishop);
                            btn = new NewJButton(newBishop, j, i);
                        }
                        else if( j == 'D' )
                        {
                            Queen whiteQueen = new Queen(j, i, true,"WQ");
                            whitePieces.add(whiteQueen);
                            btn = new NewJButton(whiteQueen, j, i);
                        }
                        else if( j == 'E' )
                        {
                            King whiteKing = new King(j, i, true, "WK");
                            whitePieces.add(whiteKing);
                            btn = new NewJButton(whiteKing, j, i);
                        }
                    }
                    else if( i == 7 )
                    {
                        Pawn newPawn = new Pawn(j, i, true, "WP");
                        whitePieces.add(newPawn);
                        btn = new NewJButton(newPawn, j, i);
                    }
                    else if( i == 1 )
                    {
                        if( j == 'A' || j == 'H' )
                        {

                            Rook newRook = new Rook(j, i, false, "BR");
                            blackPieces.add(newRook);
                            btn = new NewJButton(newRook, j, i);
                        }
                        else if( j == 'B' || j == 'G' )
                        {
                            Knight newKnight = new Knight(j, i, false, "BN");
                            blackPieces.add(newKnight);
                            btn = new NewJButton(newKnight, j, i);
                        }
                        else if( j == 'C' || j == 'F' )
                        {

                            Bishop newBishop = new Bishop(j, i, false, "BB");
                            blackPieces.add(newBishop);
                            btn = new NewJButton(newBishop, j, i);
                        }
                        else if( j == 'D' )
                        {
                            Queen blackQueen = new Queen(j, i, false,"BQ");
                            blackPieces.add(blackQueen);
                            btn = new NewJButton(blackQueen, j, i);
                        }
                        else if( j == 'E' )
                        {
                            King blackKing = new King(j, i, false, "BK");
                            blackPieces.add(blackKing);
                            btn = new NewJButton(blackKing, j, i);
                        }
                    }
                    else if( i == 2 )
                    {
                        Pawn newPawn = new Pawn(j, i, false, "BP");
                        blackPieces.add(newPawn);
                        btn = new NewJButton(newPawn, j, i);

                    }
                    else
                    {
                        btn = new NewJButton(j, i);
                    }
                    this.board = new Board(whitePieces, blackPieces);
                    this.btns.add(btn);
                    btn.addMouseListener(this);
                    btn.setFocusable(false);
                    if( (i % 2 == 1 && j % 2 == 1) || (i % 2 == 0 && j % 2 == 0) ){
                        btn.setBackground(new Color(219,217,164));
                    }else{
                        btn.setBackground(new Color(106, 55, 7));
                    }
                    boardPanel.add(btn);
                }
            }
        }

        // Left Divider Panel
        JPanel leftDivider = new JPanel(new GridLayout(3, 1));
        // Top Left
        JPanel topLeft = new JPanel(new GridLayout(2, 8));
        topLeft.setBorder(new EmptyBorder(50, 10, 50, 0));
        for(char i = 0; i < 2; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                NewJButton btn = new NewJButton();
                ImageIcon icon = new ImageIcon(new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB));
                btn.setIcon(icon);
                btn.setPreferredSize(new Dimension(70, 70));
                btn.setFocusable(false);
                btn.setBackground(new Color(106, 55, 7));
                topLeft.add(btn);
                whiteLost.add(btn);
            }
        }
        // Middle Left
        JPanel middleLeft = new JPanel();
        middleLeft.setBorder(new EmptyBorder(100, 0, 0, 0));
        JLabel playing = new JLabel("White Player is playing");
        playing.setFont(new Font("Segoe UI", Font.BOLD, 32));
        caption = playing;
        middleLeft.add(playing);
        leftDivider.add(middleLeft);
        // Bottom Left
        JPanel bottomLeft = new JPanel(new GridLayout(2, 8));
        bottomLeft.setBorder(new EmptyBorder(50, 10, 50, 0));
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                NewJButton btn = new NewJButton();
                ImageIcon icon = new ImageIcon(new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB));
                btn.setPreferredSize(new Dimension(70, 70));
                btn.setIcon(icon);
                btn.setFocusable(false);
                btn.setBackground(new Color(219,217,164));
                bottomLeft.add(btn);
                blackLost.add(btn);
            }
        }
        // Add Panels
        leftDivider.add(topLeft);
        leftDivider.add(middleLeft);
        leftDivider.add(bottomLeft);

        fullPanel.add(boardPanel, BorderLayout.EAST);
        fullPanel.add(leftDivider, BorderLayout.WEST);

        frame.add(fullPanel);
        // Show Frame
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        this.frame = frame;

        String line = "";
        while (true)
        {
            try
            {
                line = this.input.readUTF();
                if( line.equals("Over") )
                {
                    break;
                }
                String[] splited = line.split(" ");
                for(NewJButton btn : this.btns)
                {
                    if( btn.getNewX() == splited[0].charAt(1) && btn.getNewY() == splited[0].charAt(0) - '0' )
                    {
                        this.inMove = btn;
                    }
                }
                NewJButton source = null;
                for(NewJButton btn : this.btns)
                {
                    if( btn.getNewX() == splited[1].charAt(1) && btn.getNewY() == splited[1].charAt(0) - '0' )
                    {
                        source = btn;
                    }
                }
                if( this.board.canGo(this.inMove.getPiece(), source.getNewX(), source.getNewY(), this.color) )
                {
                    if( this.board.canAttack(this.inMove.getPiece(), source.getNewX(), source.getNewY(), this.color) )
                    {
                        Piece temp = source.getPiece();
                        source.setPiece(this.inMove.getPiece());
                        this.inMove.setPiece(null);
                        if( this.color.equals("W") )
                        {
                            killBlack(temp);
                        }
                        else
                        {
                            killWhite(temp);
                        }
                    }
                    else
                    {
                        Piece temp = this.inMove.getPiece();
                        this.inMove.setPiece(source.getPiece());
                        source.setPiece(temp);
                    }
                    System.out.println(this.board.move( inMove.getNewY() + Character.toString(inMove.getNewX()), source.getNewY() + Character.toString(source.getNewX()), this.color));
                    if( this.color.equals("W"))
                    {
                        caption.setText("Black Player is playing");
                        this.color = "B";
                    }
                    else
                    {
                        caption.setText("White Player is playing");
                        this.color = "W";
                    }
                    this.isSelected = false;
                    this.inMove = null;
                }
                repaint();
                try
                {
                    checkMate();
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        if( this.output != null )
        {
            try
            {
                this.output.writeUTF("Over");
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
        System.out.println("Closing connection");

        this.socket.close();
        this.input.close();
        this.output.close();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        NewJButton source = (NewJButton)(e.getSource());
        if( !this.isSelected )
        {
            boolean flag = false;
            if( this.me && this.color.equals("W") )
            {
                flag = true;
            }
            else if( !this.me && this.color.equals("B") )
            {
                flag = true;
            }
            if( source.getPiece() != null && source.getPiece().getColor() == this.me && flag )
            {
                for(NewJButton btn : this.btns)
                {
                    if( source != btn && source.getPiece() != null && this.board.canGo(source.getPiece(), btn.getNewX(), btn.getNewY(), this.color) )
                    {
                        if( this.board.canAttack(source.getPiece(), btn.getNewX(), btn.getNewY(), this.color) )
                        {
                            btn.setBackground(new Color(255, 0, 0));
                        }
                        else
                        {
                            Image img = null;
                            try
                            {
                                img = ImageIO.read(Chess.class.getResource("resources/images/dot.png"));
                            }
                            catch (IOException e1)
                            {
                                e1.printStackTrace();
                            }
                            btn.setIcon(new ImageIcon(img, "dot"));
                        }
                        this.isSelected = true;
                        this.inMove = source;
                    }
                }
            }
        }
        else
        {
            if( this.board.canGo(this.inMove.getPiece(), source.getNewX(), source.getNewY(), this.color) )
            {
                if( this.board.canAttack(this.inMove.getPiece(), source.getNewX(), source.getNewY(), this.color) )
                {
                    Piece temp = source.getPiece();
                    source.setPiece(this.inMove.getPiece());
                    this.inMove.setPiece(null);
                    if( this.color.equals("W") )
                    {
                        killBlack(temp);
                    }
                    else
                    {
                        killWhite(temp);
                    }
                }
                else
                {
                    Piece temp = this.inMove.getPiece();
                    this.inMove.setPiece(source.getPiece());
                    source.setPiece(temp);
                }
                if( this.board.move( inMove.getNewY() + Character.toString(inMove.getNewX()), source.getNewY() + Character.toString(source.getNewX()), this.color) )
                {
                    try
                    {
                        this.output.writeUTF(inMove.getNewY() + Character.toString(inMove.getNewX()) + " " + source.getNewY() + source.getNewX());
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    System.out.println(true);
                }
                if( this.color.equals("W"))
                {
                    caption.setText("Black Player is playing");
                    this.color = "B";
                }
                else
                {
                    caption.setText("White Player is playing");
                    this.color = "W";
                }
            }
            this.isSelected = false;
            this.inMove = null;
            repaint();
            try
            {
                checkMate();
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
    }

    public void repaint()
    {
        for(NewJButton btn : this.btns)
        {
            if( btn.getPiece() != null )
            {
                Image img = null;
                try
                {
                    img = ImageIO.read(Chess.class.getResource("resources/images/" + btn.getPiece().getName().toLowerCase() + ".png"));
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                btn.setIcon(new ImageIcon(img));
            }
            else
            {
                btn.setIcon(null);
            }
            if((btn.getNewY() % 2 == 1 && btn.getNewX() % 2 == 1) || (btn.getNewY() % 2 == 0 && btn.getNewX() % 2 == 0))
            {
                btn.setBackground(new Color(219,217,164));
            }
            else
            {
                btn.setBackground(new Color(106, 55, 7));
            }
        }
        for(NewJButton btn : this.whiteLost)
        {
            if( btn.getPiece() != null )
            {
                Image img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
                try
                {
                    img = ImageIO.read(Chess.class.getResource("resources/images/" + btn.getPiece().getName().toLowerCase() + ".png"));
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                btn.setIcon(new ImageIcon(img));
            }
            else
            {
                btn.setIcon(new ImageIcon(new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB)));
            }
        }

        for(NewJButton btn : this.blackLost)
        {
            if( btn.getPiece() != null )
            {
                Image img = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
                try
                {
                    img = ImageIO.read(Chess.class.getResource("resources/images/" + btn.getPiece().getName().toLowerCase() + ".png"));
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
                btn.setIcon(new ImageIcon(img));
            }
            else
            {
                btn.setIcon(new ImageIcon(new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB)));
            }
        }
    }

    public void checkMate() throws IOException
    {
        if( this.board.checkMate() != null )
        {
            if ( !this.socket.isClosed() )
            {
                try
                {
                    this.output.writeUTF("Over");
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
            String prompt = "";

            if (this.board.checkMate().equals("W"))
            {
                prompt = prompt.concat("<html><body><center>Black Player Won!M</center>");
            }
            else
            {
                prompt = prompt.concat("<html><body><center>White Player Won!</center>");
            }

            JOptionPane optionPane = new JOptionPane(new JLabel(prompt,JLabel.CENTER));
            JDialog dialog = optionPane.createDialog("Finished");
            dialog.setModal(true);
            dialog.setVisible(true);
            System.exit(0);
        }
    }

    public boolean killWhite(Piece p)
    {
        for(NewJButton btn : this.whiteLost)
        {
            if( btn.getPiece() == null )
            {
                btn.setPiece(p);
                return true;
            }
        }
        return false;
    }

    public boolean killBlack(Piece p)
    {
        for(NewJButton btn : this.blackLost)
        {
            if( btn.getPiece() == null )
            {
                btn.setPiece(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}