/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.gui;

import dobble_client.game.Actions;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

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
        setClickedListener();
        
    }
    
    private void setClickedListener() {
        
    }
    
    protected int getSymbolId() {
        return id;
    }
    
    protected void setControl(Actions control) {
        this.control = control;
    }
    
    public String getName() {
        return name;
    }
    
    public Color getColor() {
        return color;
    }
}
