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
                
                        
                if (message == null || (message.charAt(0) == 0 && message.length() == 1) ) {
                    netConnection.closeConnection();
                    parsedMessage = new ParsedMessage();
                    parsedMessage.setType('S');
                    parsedMessage.setSubtype('E');
                }else if ((message.length() < 2) || (message.length() > 16)) {
                    netConnection.closeConnection();
                    parsedMessage = new ParsedMessage();
                    parsedMessage.setType('E');
                    parsedMessage.setSubtype('E');
                } else {
                    message = message.trim();
                    parsedMessage = parser.parseMessage(message);
                }
                
                synchronized (messages) {
                    messages.addMessage(parsedMessage);
                    messages.notifyAll();
                }
                
            } catch (IOException ex) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex1) {
                    //Logger.getLogger(RecieveThread.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            
            
        }
        
    }
    
    public void stopThread() {
        shouldRun = false;
    }
    
}
