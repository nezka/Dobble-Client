/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.game;

import dobble_client.message.ParsedMessage;

/**
 *
 * @author anvy
 */
public class MessageProcessor {
    
    public void processMessage(ParsedMessage message) {
        switch(message.getType()) {
            case 'N': break;
            case 'A': break;
                    default: 
                        
        }
    }
    
    public void processGameMessage(ParsedMessage message) {
        switch(message.getType()) {
            case 'N': break;
            case 'A': break;
                    default: 
                        
        }
    }
    
}
