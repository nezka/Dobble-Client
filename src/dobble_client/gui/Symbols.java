/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

import dobble_client.game.Control;
import java.awt.Color;

/**
 *
 * @author anvy
 */
public class Symbols {
    
    private Symbol[] symbols = new Symbol[]{
        new Symbol("WHITE", 0, Color.BLACK),
        new Symbol("BLUE", 1, Color.BLUE),
        new Symbol("BLACK", 2, Color.RED),
        new Symbol("RED", 3, Color.BLUE),
        new Symbol("BLUE", 4, Color.BLACK),
        new Symbol("RED", 5, Color.WHITE),
        new Symbol("BLACK", 6, Color.BLUE),
        new Symbol("WHITE", 7, Color.BLUE),
        new Symbol("WHITE", 8, Color.RED),
        new Symbol("WHITE", 9, Color.WHITE),
        new Symbol("BLACK", 10, Color.BLACK),
        new Symbol("BLACK", 11, Color.WHITE),
        new Symbol("BLUE", 12, Color.WHITE),
        new Symbol("BLUE", 13, Color.RED),
        new Symbol("RED", 14, Color.RED),
       /* new GuiSymbol("RED", 15, Color.BLACK),
        new GuiSymbol("BLUE", 16, Color.WHITE),
        new GuiSymbol("GREEN", 17, Color.RED),
        new GuiSymbol("GREEN", 18, Color.WHITE),
        new GuiSymbol("GREEN", 19, Color.BLACK),
        new GuiSymbol("GREEN", 20, Color.BLUE),*/
    };
    
    public Symbols(Control control) {
        setControls(control);
    }
    
    /*
    public Symbol getSymbol(int index) {
        if (index < symbols.length) {
            return symbols[index];
        } else {
            System.err.println("Error in symbols indexes.");
            return null;
        }
    }*/
    
    private void setControls(Control control) {
        for (int i = 0; i < symbols.length; i++) {
            symbols[i].setControl(control);
        }
    }
    
    protected Symbol[] getSymbols() {
        return symbols;
    }
}
