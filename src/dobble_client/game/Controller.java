/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.game;

import dobble_client.message.MessageStack;

/**
 *
 * @author anvy
 */
public class Controller implements Runnable{
    private boolean inGame;
    private boolean gameEnded;
    
    private MessageStack recieved;
    private MessageStack toBeSended;
    
    
    
    public Controller(MessageStack recieved, MessageStack toBeSended) {
        this.recieved = recieved;
        this.toBeSended = toBeSended;
        
    }

    @Override
    public void run() {
        
    }
    
    public void symbolClicked(int id) {
        
    }
    
}
