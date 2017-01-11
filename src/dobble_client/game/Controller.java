/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.game;

import dobble_client.network.MessageStack;
import dobble_client.network.Network;
import dobble_client.network.ParsedMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author anvy
 */
public class Controller {
    private boolean inGame = false;
    private boolean gameEnded = false;
    private int round = 0;
    
    private MessageStack recieved;
    private MessageStack toBeSended;
    private Network nw;
    
    
    
    public Controller(Network nw, MessageStack recieved, MessageStack toBeSended) {
        this.recieved = recieved;
        this.toBeSended = toBeSended;
        this.nw = nw;
        
    }

 
    public void waitForMessage() {
        MessageProcessor mp = new MessageProcessor();
        while (true) {
            synchronized (recieved) {
                while (recieved.isEmpty()) {
                    try {
                        recieved.wait();
                    } catch (InterruptedException ex) {
                        System.out.println("interrupted wait controller");
                    }
                }
                ParsedMessage m = recieved.getMessage();
                System.out.println(m.getText());
                mp.processMessage(m);
                   
                
                
            }
        }
    }
    
    public void symbolClicked(int id) {
        System.out.println("Kliknuto na symbol: " + id + "\n");
    }
    
    
    
    public String connectToGameServer(String hostname, int port) {
        return nw.connectToServer(hostname, port);
    }
    
    public void sendMessageCardClicked(String text) {
        ParsedMessage m = new ParsedMessage('A', 'H', "oj");
        addToSendQueue(m);
        
    }
    
    public void joinGame(boolean retry) {
        String text = null;
        if (retry) {
            text = getRetrySecret();
        }
        ParsedMessage message = new ParsedMessage('G', 'J', text);
        addToSendQueue(message);
    }
    
    public void exitGame() {
        ParsedMessage message = new ParsedMessage('G', 'L', null); 
        addToSendQueue(message);
    }
    
    private void addToSendQueue(ParsedMessage message) {
        synchronized (toBeSended) {
            toBeSended.addMessage(message);
            toBeSended.notifyAll();
        } 
    }
    
    private String getRetrySecret() {
        String secret = null;
        BufferedReader br = null;
        File file = new File("retry.txt");
        if (!file.exists()) {
            return null;
        }
        try {
            br = new BufferedReader(new FileReader(file));
            secret = br.readLine();
        } catch(IOException e) {
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    System.err.println("Can't close the retry file.");
                }
            }
        }
        return secret;

    }
    
    public void closeGame() {
        System.exit(0);
    }
    
}
