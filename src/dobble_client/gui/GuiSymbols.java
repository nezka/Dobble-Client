/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

import dobble_client.game.Actions;
import java.awt.Color;

/**
 *
 * @author anvy
 */
public class GuiSymbols {
    
    private GuiSymbol[] symbols = new GuiSymbol[]{
        new GuiSymbol("WHITE", 0, Color.BLACK),
        new GuiSymbol("BLUE", 1, Color.BLUE),
        new GuiSymbol("BLACK", 2, Color.RED),
        new GuiSymbol("RED", 3, Color.BLUE),
        new GuiSymbol("BLUE", 4, Color.BLACK),
        new GuiSymbol("RED", 5, Color.WHITE),
        new GuiSymbol("BLACK", 6, Color.BLUE),
        new GuiSymbol("WHITE", 7, Color.BLUE),
        new GuiSymbol("WHITE", 8, Color.RED),
        new GuiSymbol("WHITE", 9, Color.WHITE),
        new GuiSymbol("BLACK", 10, Color.BLACK),
        new GuiSymbol("BLACK", 11, Color.WHITE),
        new GuiSymbol("BLUE", 12, Color.WHITE),
        new GuiSymbol("BLUE", 13, Color.RED),
        new GuiSymbol("RED", 14, Color.RED),
       /* new GuiSymbol("RED", 15, Color.BLACK),
        new GuiSymbol("BLUE", 16, Color.WHITE),
        new GuiSymbol("GREEN", 17, Color.RED),
        new GuiSymbol("GREEN", 18, Color.WHITE),
        new GuiSymbol("GREEN", 19, Color.BLACK),
        new GuiSymbol("GREEN", 20, Color.BLUE),*/
    };
    
    public GuiSymbols(Actions control) {
        setControls(control);
    }
    
    
    public GuiSymbol getSymbol(int index) {
        if (index < symbols.length) {
            return symbols[index];
        } else {
            System.err.println("Error in symbols indexes.");
            return null;
        }
    }
    
    public void setControls(Actions control) {
        for (int i = 0; i < symbols.length; i++) {
            symbols[i].setControl(control);
        }
    }
    
    protected GuiSymbol[] getSymbols() {
        return symbols;
    }
}
