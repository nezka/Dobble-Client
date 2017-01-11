
package dobble_client.gui;

import dobble_client.game.Card;
import dobble_client.game.Controller;
import dobble_client.game.Symbol;
import java.awt.BorderLayout;
import java.awt.Label;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public final class GameWindow extends JFrame{
    
    private WindowsManager wm;
    private Controller control;
    private GameStats stats = new GameStats();
    
    public GameWindow (WindowsManager wm, Controller control) {
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
        this.add(createMainPanel(), BorderLayout.CENTER);
        this.addOnCloseAction(); 
	this.setVisible(false);
    }
    
    private JPanel createMainPanel() {
        JPanel mainPN = new JPanel();
        mainPN.setLayout(new BorderLayout());
        mainPN.add(new GuiSymbol(control, Symbol.A), BorderLayout.CENTER);
        mainPN.add(stats, BorderLayout.EAST);
        return mainPN;
        
    }
    
    private JPanel createControlPanel() {
        JPanel controlPN = new JPanel();
        controlPN.setLayout(new BorderLayout());
        
        
        return controlPN;
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
    

    
    public void updateGameStats(int player, int opponent, int round){        
        SwingUtilities.invokeLater(() -> {
            stats.setStats(player, opponent, round);
        });
        
     
    }
    
    
}

