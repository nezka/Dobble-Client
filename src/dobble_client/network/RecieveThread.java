/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.network;

import java.io.IOException;

/**
 *
 * @author anvy
 */
public class RecieveThread extends Thread {
    
    private MessageStack messages;
    private Network netConnection;
    private boolean shouldRun = true;
    
    public RecieveThread(MessageStack messages, Network netConnection) {
        this.messages = messages;
        this.netConnection = netConnection;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        
        Parser parser = new Parser();
        String message;
        ParsedMessage parsedMessage;
        
        while(shouldRun) {
            
            try {
                message = netConnection.recieveMessage();
                message = message.trim();
                parsedMessage = parser.parseMessage(message);
                synchronized (messages) {
                    System.out.println("zprava: " + parsedMessage.getType()+ parsedMessage.getSubtype()+ parsedMessage.getText() );
                    messages.addMessage(parsedMessage);
                    messages.notifyAll();
                }
                
            } catch (IOException ex) {
                System.err.println("IOException during recieving message: " + ex.getStackTrace());
            }
            
            
        }
        
    }
    
    public void stopThread() {
        shouldRun = false;
    }
    
}
