/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

import dobble_client.game.Actions;
import dobble_client.game.Symbol;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author anvy
 */
public class GuiSymbol extends JLabel{
    
    private int id;
    private String name;
    private Color color;
    private Actions control;
    
    protected GuiSymbol(String name, int id, Color color) {
        super(name);
        this.id = id;
        this.color = color;
        setClickedListener();
        this.setForeground(color);
    }
    
    private void setClickedListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.sendMessageCardClicked("" + id);
                System.out.println("click\n");
            }
        });
    }
    
    protected int getSymbolId() {
        return id;
    }
    
    protected void setControl(Actions control) {
        this.control = control;
    }
}
