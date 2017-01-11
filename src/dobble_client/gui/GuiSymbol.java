/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

import dobble_client.game.Controller;
import dobble_client.game.Symbol;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

/**
 *
 * @author anvy
 */
public class GuiSymbol extends JLabel{
    
    private Symbol symbol;
    private Controller control;
    
    protected GuiSymbol(Controller control, Symbol symbol) {
        super("Nanananananana");
        this.symbol = symbol;
        this.control = control;
        setClickedListener();
    }
    
    private void setClickedListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.sendMessageCardClicked("message");
                System.out.println("click\n");
            }
        });
    }
    
    protected int getSymbolId() {
        return symbol.ordinal();
    }
}
