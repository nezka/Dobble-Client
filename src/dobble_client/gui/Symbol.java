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
public class Symbol{
    
    private int id;
    private String name;
    private Color color;
    private Actions control;
    
    protected Symbol(String name, int id, Color color) {
        this.name = name;
        this.id = id;
        this.color = color;
        
    }
    
    protected int getSymbolId() {
        return id;
    }
    
    protected void setControl(Actions control) {
        this.control = control;
    }
    
    protected String getName() {
        return name;
    }
    
    protected Color getColor() {
        return color;
    }
}
