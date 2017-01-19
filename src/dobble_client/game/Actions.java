/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.game;

import dobble_client.gui.WindowsManager;
import dobble_client.network.MessageStack;
import dobble_client.network.Network;
import dobble_client.network.ParsedMessage;


public class Actions {
    
    private int round = 1;   
    private MessageStack recieved;
    private MessageStack toBeSended;
    private WindowsManager wm;
    private Network nw;
    
    
    
    public Actions(Network nw, MessageStack recieved, MessageStack toBeSended, WindowsManager wm) {
        this.recieved = recieved;
        this.toBeSended = toBeSended;
        this.nw = nw;
        this.wm = wm;
        
    }
   
    

 
    public void waitForMessage() {

        while (true) {
            synchronized (recieved) {
                while (recieved.isEmpty()) {
                    try {
                        recieved.wait();
                    } catch (InterruptedException ex) {
                        System.err.println("interrupted wait controller");
                    }
                }
                ParsedMessage m = recieved.getMessage();
                processMessage(m);  
                
            }
        }
    }
    
    public String connectToGameServer(String hostname, int port) {
        return nw.connectToServer(hostname, port);
    }
    
    
    public void sendMessageCardClicked(String text) {
        text = round+";"+text;
        ParsedMessage m = new ParsedMessage('G', 'C', text);
        addToSendQueue(m);
       
    }
    
    public void playAgain() {
        ParsedMessage message = new ParsedMessage('G', 'A', null); 
        addToSendQueue(message);
        ParsedMessage message2 = new ParsedMessage('G', 'N', null); 
        addToSendQueue(message2);
    }
    
    public void leaveOpponent() {
        ParsedMessage message = new ParsedMessage('G', 'B', null); 
        addToSendQueue(message);
        ParsedMessage message2 = new ParsedMessage('G', 'N', null); 
        addToSendQueue(message2);
    }
    
    public void joinGame(boolean retry) {
        String text = null;
        if (retry) {
            text = RetryManager.getRetrySecret();
        }
        ParsedMessage message = new ParsedMessage('G', 'N', text);
        addToSendQueue(message);
    }
    
    public void exitGame() {
        ParsedMessage message = new ParsedMessage('G', 'L', null); 
        addToSendQueue(message);
        closeGame();
    }
    
    private void addToSendQueue(ParsedMessage message) {
        synchronized (toBeSended) {
            toBeSended.addMessage(message);
            toBeSended.notifyAll();
        } 
    }
    
    
    
    private void closeGame() {       
        nw.closeConnection();
        System.exit(0);
    }
    
    private void processMessage(ParsedMessage message) {
        switch(message.getType()) {
            case 'G': 
                processGameMessage(message);
                break;
            case 'S': 
                processServiceMessage(message);
                break;
            default: 
                wm.getGameWindow().maliciousServer();
                        
        }
    }
    
    private void processGameMessage(ParsedMessage message) {
        String[] parts = null;
        switch(message.getSubtype()) {
            case 'W': 
                wm.getGameWindow().showWaitMessage();
                break;
            case 'G': 
                RetryManager.setRetrySecret(message.getText());
                break;
            case 'V':      
                parts = parseText(message.getText());
                wm.getGameWindow().updateGameStats(parts[0], parts[1], parts[2]);
                round = Integer.parseInt(parts[2]);
                wm.getGameWindow().drawCards(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                break;
        /*    case 'I': 
                System.out.println("Nespravna odpoved\n");
                break;*/
            case 'F': 
                parts = parseText(message.getText());
                int my = Integer.parseInt(parts[0]);
                int you = Integer.parseInt(parts[1]);
                wm.getGameWindow().updateGameStats(parts[0], parts[1], round+"");
                if (my > you) {
                    wm.getGameWindow().showVictoryMessage(true);
                } else {
                    wm.getGameWindow().showVictoryMessage(false);
                }
                break;
            default: 
                wm.getGameWindow().maliciousServer();           
        }
    }
    
    public void resetGame() {
        wm.getGameWindow().updateGameStats("0", "0", "0");
        round = 1;
    }
    
    private void processServiceMessage(ParsedMessage message) {
        switch(message.getSubtype()) {
            case 'E': 
                wm.getGameWindow().showServerDisconnect();
                break;
            case 'O': 
                wm.getGameWindow().showOpponentLeft();
                break;
            default: 
                wm.getGameWindow().maliciousServer();
                        
        }
    }
    
    private String[] parseText(String text) {
        String[] parts = text.split(";");
        return parts;
    }
    
}
