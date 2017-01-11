
package dobble_client.gui;

import dobble_client.game.Card;
import dobble_client.game.Controller;
import dobble_client.network.Network;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class RoomWindow extends JFrame{
    
    private WindowsManager wm;
    private Controller control;
    
    public RoomWindow (WindowsManager wm, Controller control) {
        this.wm = wm;
        this.control = control;
        setUpFrame();
    }
    
    private void setUpFrame() {
       
        this.setTitle("The Dobble Game");
	this.setSize(1000, 700);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	
        this.setResizable(false);
	this.setLayout(new BorderLayout());
        JPanel pn = new JPanel();
        this.add(pn, BorderLayout.CENTER);
        this.addOnCloseAction();
       
        
	this.setVisible(false);
    }
    
    JPanel createMainPanel() {
        JPanel mainPN = new JPanel();
        mainPN.setLayout(new BorderLayout());
        
        return mainPN;
        
    }
    
    private void addOnCloseAction() {
         this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            int answer = JOptionPane.showConfirmDialog(null, 
                "Closing this window will exit game.\nDo you want to close it?", "Exit game", 
                 JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (answer == JOptionPane.YES_OPTION){
                control.exitGame();
            }
        }
        });
    }
    
    
    public void drawCard(Card card) {
        System.out.println("kreslim kartu: " + card.getCardId() + "\n");
    }
    

    
    
}
