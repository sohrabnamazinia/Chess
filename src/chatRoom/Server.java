package chatRoom;

import entry.Login;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream in;
    static DataOutputStream out;
    static String userName = Login.chatName;

    public static void main(String[] args) throws Exception
    {
        serverSocket = new ServerSocket(5555);
        socket = serverSocket.accept();
        MainGUI.main(null);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());

        new Thread(() ->
        {
            while (true)
            {
                String message = null;
                try {
                    message = in.readUTF();
                    MainGUI.chatBox.append(message + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}