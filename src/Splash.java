import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Splash
{
    public void run() throws IOException
    {
        Object[] options = {"Create", "Join"};
        int result = JOptionPane.showOptionDialog(null, "  Create/Join  a  server  :", "Start Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

        // Server
        if( result == JOptionPane.YES_OPTION )
        {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Server Created");

            AppFrame frame = new AppFrame();
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            JLabel label = new JLabel("Waiting for opponent...");
            label.setBorder(new EmptyBorder(40,40,40,40));
            frame.add(label);
            frame.pack();
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
            frame.setVisible(true);

            Socket socket = server.accept();
            System.out.println("Client Connected");
            frame.dispose();

            server.close();

            Chess chess = new Chess("W", socket);
            chess.run();

        }

        // Client
        else
        {
            boolean flag = true;
            Socket socket = null;
            while(flag)
            {
                String s = (String)JOptionPane.showInputDialog(null, "Enter Server IP:", "Finding Server", JOptionPane.PLAIN_MESSAGE, null, null, "x.x.x.x");
                socket = new Socket(s, 5000);
                if( socket.isConnected() )
                {
                    flag = false;
                }
            }

            Chess chess = new Chess("B", socket);
            chess.run();
            System.out.println("Connected to server");


        }
    }
}