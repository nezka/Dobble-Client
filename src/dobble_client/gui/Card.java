/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

import java.util.ArrayList;

/**
 *
 * @author anvy
 */
public class Card {
    
    private final static int SYMBOL_COUNT = 5;
    
    private Symbol[] symbols = new Symbol[SYMBOL_COUNT];
    
    public Card(Symbol one, Symbol two, Symbol three, Symbol four, Symbol five) {
        symbols[0] = one;
        symbols[1] = two;
        symbols[2] = three;
        symbols[3] = four;
        symbols[4] = five;
                
    }
    
    public Symbol[] getSymbols() {
        return symbols;
    }
    
    public static int getSymbolCount() {
        return SYMBOL_COUNT;
    }
    
    
}
