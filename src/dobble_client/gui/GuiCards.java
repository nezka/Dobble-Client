/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

/**
 *
 * @author anvy
 */
public class GuiCards {
    
    private GuiCard[] stack;
    
    public GuiCards(GuiSymbol[] symbols) {
        createCards(symbols);
    }
    
    public GuiCard getCard(int index) {
        if (index < stack.length) {
            return stack[index];
        } else {
            System.err.println("Error in cards indexes.");
            return null;
        }
    }
    
    private void createCards(GuiSymbol[] symbols) {
        stack = new GuiCard[]{
        new GuiCard(symbols[0], symbols[1], symbols[2], symbols[3], symbols[4]),
        new GuiCard(symbols[4], symbols[5], symbols[6], symbols[7], symbols[8]),
        new GuiCard(symbols[1], symbols[9], symbols[10], symbols[5], symbols[11]),
        new GuiCard(symbols[12], symbols[7], symbols[2], symbols[14], symbols[9]),
        new GuiCard(symbols[12], symbols[10], symbols[8], symbols[0], symbols[13]),
    };
    }
}
