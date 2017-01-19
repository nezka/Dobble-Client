/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

import dobble_client.game.Actions;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author anvy
 */
public class CardPanel extends JPanel {

    private final Actions control;

    public CardPanel(Actions control, Symbol[] sym) {
        this.control = control;
        this.setPreferredSize(new Dimension(200,300));
        setLayout(new GridLayout(5, 5));
        makeGrid(sym);
        

    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        float thickness = 3;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(thickness));   
        Ellipse2D.Double circle = new Ellipse2D.Double(50,10,this.getWidth()-100,this.getHeight()-20);
        g2.draw(circle);       
        g2.setColor(Color.CYAN);
        g2.fill(circle);
    }

  /*  public void printCard() {

        int componentCount = this.getComponentCount();

        for (int i = 0; i < componentCount; i++) {
            Component component = this.getComponent(i);
            System.out.print(component.getClass().getSimpleName());
            if (component instanceof JLabel) {
                JLabel lbl = (JLabel) component;
                System.out.print(lbl.getText() + " [" + lbl.getForeground().toString() + "]");
            }
            System.out.println("");
        }
    }*/

    private JLabel createSymbolLB(Symbol s, int cons) {
        JLabel l = new JLabel(s.getName(), cons);
        l.setForeground(s.getColor());
        l.setFont(new Font("Default", Font.BOLD, 14));

        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.sendMessageCardClicked("" + s.getSymbolId());
            }
        });
        return l;
    }
    
    private void makeGrid(Symbol[] sym) {
             
        add(new JLabel());
        add(new JLabel());
        add(createSymbolLB(sym[0], SwingConstants.CENTER));
        
        for (int i = 3; i < 10; i++) {
            add(new JLabel());
        }
        
        add(createSymbolLB(sym[1], JLabel.RIGHT));
        add(new JLabel());
        add(createSymbolLB(sym[2], SwingConstants.CENTER));
        add(new JLabel());
        add(createSymbolLB(sym[3], JLabel.LEFT));

        for (int i = 15; i < 22; i++) {
            add(new JLabel());
        }
        
        add(createSymbolLB(sym[4], SwingConstants.CENTER));
        add(new JLabel());    
        add(new JLabel());
    }
}
