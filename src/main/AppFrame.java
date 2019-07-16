package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

class AppFrame extends JFrame implements WindowListener
{
    public AppFrame()
    {
        super();
        this.addWindowListener(this);
    }

    public AppFrame(String name)
    {
        super(name);
        this.addWindowListener(this);
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e)
    {
        dispose();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}