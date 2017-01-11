/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.game;

import dobble_client.network.ParsedMessage;

/**
 *
 * @author anvy
 */
public class MessageProcessor {
    
    public void processMessage(ParsedMessage message) {
        switch(message.getType()) {
            case 'G': 
                processGameMessage(message);
                break;
            case 'S': 
                processServiceMessage(message);
                break;
                    default: 
                        
        }
    }
    
    private void processGameMessage(ParsedMessage message) {
        switch(message.getSubtype()) {
            case 'W': 
                System.out.println("Nedostatek hracu, cekej.\n");
                break;
            case 'G': 
                System.out.println("Zacina hra\n");
                break;
            case 'V': 
                System.out.println("Vitez kola\n");
                break;
            case 'I': 
                System.out.println("Nespravna odpoved\n");
                break;
            case 'F': 
                System.out.println("Hra skoncila\n");
                break;
            default: 
                System.out.println("podezrely server");
                        
        }
    }
    
    private void processServiceMessage(ParsedMessage message) {
        switch(message.getSubtype()) {
            case 'E': 
                System.out.println("Vypina se server :(\n");
                break;
            case 'P': 
                System.out.println("Protihrac se odpojil :(\n");
                break;
            case 'C': 
                System.out.println("Servrovi se nelibime :(\n");
                break;
            default: 
                System.out.println("Podezrely server.");
                //poslat zpravu podezrely server a odpojit se
                        
        }
    }
    
}
