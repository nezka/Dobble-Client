/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.game;

import dobble_client.gui.GameWindow;
import dobble_client.gui.WindowsManager;
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
public class Actions {
    private boolean inGame = false;
    private boolean gameEnded = false;
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
        //MessageProcessor mp = new MessageProcessor();
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
                System.out.println(m.getText() + "   neeeee");
                processMessage(m);
                   
                
                
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
        text = round+";"+text;
        ParsedMessage m = new ParsedMessage('G', 'C', text);
        addToSendQueue(m);
        
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
    
    
    
    public void closeGame() {
        System.exit(0);
    }
    
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
        String[] parts = null;
        switch(message.getSubtype()) {
            case 'W': 
                wm.getGameWindow().showWaitMessage();
                
                System.out.println("Nedostatek hracu, cekej.\n");
                break;
            case 'G': 
                RetryManager.setRetrySecret(message.getText());
                System.out.println("Zacina hra\n");
                break;
            case 'V': 
                      
                parts = parseText(message.getText());
                wm.getGameWindow().updateGameStats(parts[0], parts[1], parts[2]);
                round = Integer.parseInt(parts[2]);
                wm.getGameWindow().drawCards(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                System.out.println("Vitez kola\n");
                break;
            case 'I': 
                System.out.println("Nespravna odpoved\n");
                break;
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
    
    private String[] parseText(String text) {
        String[] parts = text.split(";");
        for (String part : parts) {
            System.out.println(part);
        }
        return parts;
    }
    
}
