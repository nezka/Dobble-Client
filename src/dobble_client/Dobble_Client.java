/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client;

/**
 *
 * @author anvy
 */
;


import dobble_client.game.Control;



import dobble_client.gui.GameWindow;
import dobble_client.network.Network;
import dobble_client.gui.ServerWindow;
import dobble_client.gui.WindowsManager;
import dobble_client.network.MessageStack;
import dobble_client.network.RecieveThread;
import dobble_client.network.SendThread;



import dobble_client.gui.GameWindow;
import dobble_client.network.Network;
import dobble_client.gui.ServerWindow;
import dobble_client.gui.WindowsManager;
import dobble_client.network.MessageStack;
import dobble_client.network.RecieveThread;
import dobble_client.network.SendThread;


/**
 * Client for the Word Dobble game. The client is used for displaying infromation from the game server.
 * @author Anezka Jachymova
 */
public class Dobble_Client {
 public static void main(String argv[]) {
    WindowsManager wm = new WindowsManager();
    Network nw = new Network();
    MessageStack recieved = new MessageStack();
    MessageStack toBeSend = new MessageStack();
    Control control = new Control(nw, recieved, toBeSend, wm);
    RecieveThread receiveThread = new RecieveThread(recieved, nw);
    SendThread sendThread = new SendThread(toBeSend, nw);
    nw.setSendAndRecieveThread(sendThread, receiveThread);   
    ServerWindow sw = new ServerWindow(wm, control);
    GameWindow rw = new GameWindow(wm, control);
    wm.setWindows(sw, rw);  
    control.waitForMessage();
            
 }
}

    
    

