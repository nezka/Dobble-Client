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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Stroke;
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
        setLayout(new GridLayout(3, 3));

        add(new JLabel());

        add(createSymbolLB(sym[0], SwingConstants.CENTER));
        add(new JLabel());
        add(createSymbolLB(sym[1], JLabel.RIGHT));
        add(createSymbolLB(sym[2], SwingConstants.CENTER));
        add(createSymbolLB(sym[3], JLabel.LEFT));

        add(new JLabel());
        add(createSymbolLB(sym[4], SwingConstants.CENTER));
        add(new JLabel());

    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        float thickness = 3;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(thickness));   
        Ellipse2D.Double circle = new Ellipse2D.Double(this.getWidth()/3 - 65,25,this.getWidth()/2,this.getHeight()-50);
        g2.draw(circle);       
        g2.setColor(Color.CYAN);
        g2.fill(circle);
    }

    public void printCard() {

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
    }

    private JLabel createSymbolLB(Symbol s, int cons) {
        JLabel l = new JLabel(s.getName(), cons);
        l.setForeground(s.getColor());
      
        l.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.sendMessageCardClicked("" + s.getSymbolId());
                System.out.println("click\n");
            }
        });
        return l;
    }
}
