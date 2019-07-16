package chatRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI
{

    static JFrame      newFrame    = new JFrame("Chat Room");
    static JButton     sendMessage;
    static JTextField  messageBox;
    static JTextArea   chatBox;

    public static void main(String[] args)
    {
    display();
    }

    static public void display()
    {
        JPanel jPanel = new JPanel();

        jPanel.setLayout(new BorderLayout());
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());
        messageBox = new JTextField(30);
        messageBox.requestFocusInWindow();

        sendMessage = new JButton("Send Message");
        sendMessage.addActionListener(new sendMessageButtonListener());

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 14));
        chatBox.setLineWrap(true);

        jPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;

        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);
        jPanel.add(BorderLayout.SOUTH, southPanel);
        newFrame.add(jPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(470, 300);
        newFrame.setVisible(true);
    }

    static class sendMessageButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
                try {
                    Server.out.writeUTF(Server.userName + " : " + messageBox.getText());
                    chatBox.append(Server.userName + " : " + messageBox.getText() + "\n");
                }
                catch (Exception e)
                {
                }
                try {
                    Client.out.writeUTF(Client.userName + " : " + messageBox.getText());
                    chatBox.append(Client.userName + " : " + messageBox.getText() + "\n");
                }
                catch (Exception e)
                {
                    e.getStackTrace();
                }
                messageBox.setText("");
                messageBox.requestFocusInWindow();
        }
    }
}