/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author anvy
 */
public class GameStats extends JPanel {
    private JLabel player = new JLabel("0");
    private JLabel opponent = new JLabel("0");
    private JLabel round = new JLabel("1");
    
    public GameStats() {
        this.setLayout(new GridLayout(3, 2));
        this.add(new JLabel("Their score: "));
        this.add(opponent);
        this.add(new JLabel("Round: "));
        this.add(round);
        this.add(new JLabel("Your score: "));
        this.add(player);
    }
    
    public void resetStats() {
        player.setText("0");
        opponent.setText("0");
        round.setText("1");
                
    }
    
    public void addPointPlayer() {
        int points = Integer.parseInt(player.getText()) + 1;
        player.setText("" + points);
    }
    
    public void addPointOpponent() {
        int points = Integer.parseInt(opponent.getText()) + 1;
        opponent.setText("" + points);
    }
    
    public void addRound() {
        int points = Integer.parseInt(round.getText()) + 1;
        round.setText("" + points);
    }
    
    public void setStats(String player, String opponent, String round) {
        this.player.setText(player);
        this.opponent.setText(opponent);
        this.round.setText(round);
    }
    
}
