/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

import dobble_client.game.Actions;
import dobble_client.gui.WindowsManager;
import dobble_client.network.Network;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ServerWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField ipTX;		
    private JTextField portTX;	
    private WindowsManager wm;
    private Actions control;
    

    public ServerWindow(WindowsManager wm, Actions control) {
        this.control = control;
        this.wm = wm;
        setUpFrame();
    }
    
    public void setUpFrame() {
        this.setTitle("Connection parameters");
        this.setSize(300, 135);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(addUpperPN(), BorderLayout.NORTH);
        this.add(addLowerPN(), BorderLayout.CENTER);
        this.add(addButtons(), BorderLayout.SOUTH);
        this.setVisible(true);
    }

    JPanel addUpperPN() {
        JPanel horniPN = new JPanel();
        horniPN.add(new JLabel("Server Address: "));
        ipTX = new JTextField();
        ipTX.setPreferredSize(new Dimension(150, 20));
        horniPN.add(ipTX);
        return horniPN;
    }

    JPanel addLowerPN() {
        JPanel dolniPN = new JPanel();
        dolniPN.add(new JLabel("Port: "));
        portTX = new JTextField();
        portTX.setPreferredSize(new Dimension(100, 20));
        dolniPN.add(portTX);
        return dolniPN;
    }

    JPanel addButtons() {
        JPanel panel = new JPanel();
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        panel.add(ok);
        panel.add(cancel);
        
        JCheckBox reconnect = new JCheckBox("Try reconnect");
        reconnect.setToolTipText("If your opponent is still waiting, you can try to join the last game.");
        reconnect.setSelected(false);
        panel.add(reconnect);

       ok.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                InputParametersValidator ipv = new InputParametersValidator();
                String error = ipv.checkParameters(ipTX.getText(), portTX.getText());
                
                if (error != null) {
                    JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (reconnect.isSelected()) {
                    
                }
                
                String retValue = control.connectToGameServer(ipv.validatedIP, ipv.validatedPort);
                if (retValue != null) {
                    error = "\nCheck that you have the correct server address!";
                    JOptionPane.showMessageDialog(null, retValue + error, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    control.joinGame(reconnect.isSelected());
                    wm.showWindow(wm.getGameWindow());
                }
                
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return panel;
    }
    
    private void closeWindow() {
        this.dispose();
    }
    
}
