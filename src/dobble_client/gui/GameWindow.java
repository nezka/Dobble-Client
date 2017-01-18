
package dobble_client.gui;


import dobble_client.game.Actions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public final class GameWindow extends JFrame{
    
    private WindowsManager wm;
    private Actions control;
    private GameStats stats = new GameStats();
    private JPanel centerPN = new JPanel();
    private JPanel mainPN;
    private Cards cards;
    
    public GameWindow (WindowsManager wm, Actions control) {
        this.wm = wm;
        this.control = control;
        Symbols symbols = new Symbols(control);
        this.cards = new Cards(symbols.getSymbols());
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
        mainPN = new JPanel();
        mainPN.setLayout(new BorderLayout());
        mainPN.add(stats, BorderLayout.EAST);
        addCenterPN();
        return mainPN;
        
    }
    
    private void addCenterPN() {
        mainPN.add(centerPN, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
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
    
    private JPanel prepareCard(int index) {
        Symbol[] sym = cards.getCard(index).getSymbols();
        return new CardPanel(control, sym);
        
    }
    
    
    public void drawCards(int myCard, int middle) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());  
        
        JPanel middleCard = prepareCard(middle);
        
        panel.add(middleCard, BorderLayout.NORTH);
        panel.add(prepareCard(myCard), BorderLayout.SOUTH);
        
        SwingUtilities.invokeLater(() -> {
            //
            mainPN.remove(centerPN);
            centerPN = panel;
            addCenterPN();
        });
    }
    
    public void showOpponentLeft() {
        SwingUtilities.invokeLater(() -> {
            mainPN.remove(centerPN);
            centerPN = new JPanel();
            centerPN.setLayout(new BorderLayout());
            String message = "   ->   Your opponent left. Try to wait a moment, they may return."; 
            JLabel lbl = new JLabel(message);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            centerPN.add(lbl , BorderLayout.WEST);
            addCenterPN();
  
        });
    }
    
    
    public void showServerDisconnect() {
        SwingUtilities.invokeLater(() -> {
            mainPN.remove(centerPN);
            centerPN = new JPanel();
            centerPN.setLayout(new BorderLayout());
            String message = "   ->   The connection with server was disconnected."; 
            JButton again = new JButton("Back");
            again.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    wm.showWindow(wm.getServerWindow());
                    control.resetGame();
                    showWaitMessage();
                    
                }
                
            });
            JLabel lbl = new JLabel(message);
            JPanel p = new JPanel();
            p.add(again);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            centerPN.add(p, BorderLayout.SOUTH);
            centerPN.add(lbl , BorderLayout.WEST);
            centerPN.add(lbl , BorderLayout.WEST);        
            addCenterPN();
  
        });
    }

    
    public void updateGameStats(String player, String opponent, String round){        
        SwingUtilities.invokeLater(() -> {
            stats.setStats(player, opponent, round);
        });
    }
    
    public void showWaitMessage() {
        SwingUtilities.invokeLater(() -> {
            mainPN.remove(centerPN);
            centerPN = new JPanel();
            centerPN.setLayout(new BorderLayout());
            String message = "   ->   There is no opponent for you. Try to wait a moment, please."; 
            JLabel lbl = new JLabel(message);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            centerPN.add(lbl , BorderLayout.WEST);
            addCenterPN();
  
        });
    }
    
    public void showVictoryMessage(boolean win) {
        SwingUtilities.invokeLater(() -> {
            mainPN.remove(centerPN);
            centerPN = new JPanel();
            centerPN.setLayout(new BorderLayout());
            String message;
            if (win) {
                message = "   ->   Yeeeeeaaaah, you WIN!"; 
            } else {
                message = "   ->   Sorry, you have lost :("; 
            }
            JButton again = new JButton("Play again!");
            again.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   control.playAgain();
                }
                
            });
            JLabel lbl = new JLabel(message);
            JPanel p = new JPanel();
            p.add(again);
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            centerPN.add(p, BorderLayout.SOUTH);
            centerPN.add(lbl , BorderLayout.WEST);
            addCenterPN();
  
        });
    }
    
    
    
    
}

