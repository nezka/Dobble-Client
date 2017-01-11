/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

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
    
    protected GuiSymbol(Symbol symbol) {
        super();
        this.symbol = symbol;
        setClickedListener();
    }
    
    private void setClickedListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("image clicked");
            }
        });
    }
    
    protected int getSymbolId() {
        return symbol.ordinal();
    }
}
