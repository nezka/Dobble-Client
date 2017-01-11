/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.game;

import dobble_client.network.MessageStack;
import dobble_client.network.Network;
import dobble_client.network.ParsedMessage;


/**
 *
 * @author anvy
 */
public class Controller {
    private boolean inGame;
    private boolean gameEnded;
    
    private MessageStack recieved;
    private MessageStack toBeSended;
    private Network nw;
    
    
    
    public Controller(Network nw, MessageStack recieved, MessageStack toBeSended) {
        this.recieved = recieved;
        this.toBeSended = toBeSended;
        this.nw = nw;
        
    }

 
    public void run() {
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
    
    public void exitGame() {
        System.exit(0);
    }
    
}
