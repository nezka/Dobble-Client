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

/**
 * This class controls the game logic. 
 * @author Anezka Jachymova
 */
public class Control {
    
    private int round = 1;   
    private MessageStack recieved;
    private MessageStack toBeSended;
    private WindowsManager wm;
    private Network nw;

    public Control(Network nw, MessageStack recieved, MessageStack toBeSended, WindowsManager wm) {
        this.recieved = recieved;
        this.toBeSended = toBeSended;
        this.nw = nw;
        this.wm = wm;
        
    }

 
    /**
     * Waits till there is a message in the queue for processing.
     */
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
    
    /**
     * Tries to make connection to the game server.
     * @param hostname server hostname
     * @param port server port
     * @return if everything went ok - null, otherwise error message
     */
    public String connectToGameServer(String hostname, int port) {
        return nw.connectToServer(hostname, port);
    }
    
    /**
     * Prepares message and adds it to the send queue.
     * @param text name of the symbol on a card
     */
    public void sendMessageCardClicked(String text) {
        text = round+";"+text;
        ParsedMessage m = new ParsedMessage('G', 'C', text);
        addToSendQueue(m);
       
    }
    
    /**
     * Prepares message and adds it to the send queue.
     */
    public void playAgain() {
        ParsedMessage message = new ParsedMessage('G', 'A', null); 
        addToSendQueue(message);
        ParsedMessage message2 = new ParsedMessage('G', 'N', null); 
        addToSendQueue(message2);
    }
    
    /**
     * Prepares message and adds it to the send queue.
     */
    public void leaveOpponent() {
        ParsedMessage message = new ParsedMessage('G', 'B', null); 
        addToSendQueue(message);
        ParsedMessage message2 = new ParsedMessage('G', 'N', null); 
        addToSendQueue(message2);
    }
    
    /**
     * Prepares message and adds it to the send queue.
     * @param retry 
     */
    public void joinGame(boolean retry) {
        String text = null;
        if (retry) {
            text = RetryManager.getRetrySecret();
        }
        ParsedMessage message = new ParsedMessage('G', 'N', text);
        addToSendQueue(message);
    }
    
    /**
     * Prepares message and adds it to the send queue.
     */
    public void exitGame() {
        ParsedMessage message = new ParsedMessage('G', 'L', null); 
        addToSendQueue(message);
        closeGame();
    }
    
    /**
     * Adds message to the send queue
     * @param message message to send
     */
    private void addToSendQueue(ParsedMessage message) {
        synchronized (toBeSended) {
            toBeSended.addMessage(message);
            toBeSended.notifyAll();
        } 
    }
    
    /**
     * Closes connection and exits game.
     */
    private void closeGame() {       
        nw.closeConnection();
        System.exit(0);
    }
    
    /**
     * Processes the incoming message.
     * @param message message to be processed
     */
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
    
    /**
     * Processes the incoming game message.
     * @param message message to parse
     */
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
    
    /**
     * Resets the gaming stats.
     */
    public void resetGame() {
        wm.getGameWindow().updateGameStats("0", "0", "0");
        round = 1;
    }
    
    /**
     * Processes the incoming service message.
     * @param message message to parse
     */
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
