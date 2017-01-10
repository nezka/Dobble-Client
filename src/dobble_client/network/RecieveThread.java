/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.network;

import dobble_client.message.MessageStack;
import dobble_client.message.ParsedMessage;
import dobble_client.message.Parser;
import dobble_client.network.Network;
import java.io.IOException;

/**
 *
 * @author anvy
 */
public class RecieveThread implements Runnable{
    
    private MessageStack messages;
    private Network netConnection;
    private boolean shouldRun = true;
    
    public RecieveThread(MessageStack messages, Network netConnection) {
        this.messages = messages;
        this.netConnection = netConnection;
    }

    @Override
    public void run() {
        
        Parser parser = new Parser();
        String message;
        ParsedMessage parsedMessage;
        
        while(shouldRun) {
            
            try {
                message = netConnection.recieveMessage();
                parsedMessage = parser.parseMessage(message);
                messages.addMessage(parsedMessage);
            } catch (IOException ex) {
                System.err.println("IOException during recieving message: " + ex.getStackTrace());
            }
            
            
        }
        
    }
    
    public void stopThread() {
        shouldRun = false;
    }
    
}
