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
public class SendThread extends Thread {
    private MessageStack messages;
    private Network netConnection;
    private boolean shouldRun = true;
    
    public SendThread(MessageStack messages, Network netConnection) {
        this.messages = messages;
        this.netConnection = netConnection;
        this.setDaemon(true);
    }

    @Override
    public void run() {

        ParsedMessage parsedMessage;
        
        while(shouldRun) {
            
            synchronized (messages) {
                while (messages.isEmpty()) {
                    try {
                        messages.wait();
                    } catch (InterruptedException ex) {
                        System.err.println("Sending thread interrupted from beaty sleep.");
                                
                    }
                }
                
            }
            try {
                
                parsedMessage = messages.getMessage();
                netConnection.sendMessage(parsedMessage.prepareMessageForSending());
                
            } catch (IOException ex) {
                while(!messages.isEmpty()) {
                    messages.getMessage();
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex1) {
                    //Logger.getLogger(SendThread.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            
            
        }
        
    }
    
    public void stopThread() {
        shouldRun = false;
    }
}
