package chatRoom;

import main.Splash;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client
{
    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;
    static String userName = "Sohrab";

    public static void main(String[] args) throws IOException
    {
        socket = new Socket(Splash.ServerIP, 5555);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
        MainGUI.main(null);

        new Thread(() ->
        {

            while (true)
            {
                if (MainGUI.sendMessage.isSelected())
                {
                    try {
                        String message = MainGUI.messageBox.getText();
                        out.writeUTF(message);
                        MainGUI.display();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }).start();

        new Thread(() ->
        {
            while (true)
            {
                try {
                    String message = in.readUTF();
                    MainGUI.chatBox.append(message + "\n");
                    System.out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}