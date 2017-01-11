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


import dobble_client.game.Actions;



import dobble_client.gui.GameWindow;
import dobble_client.network.Network;
import dobble_client.gui.ServerWindow;
import dobble_client.gui.WindowsManager;
import dobble_client.network.MessageStack;
import dobble_client.network.RecieveThread;
import dobble_client.network.SendThread;



public class Dobble_Client {
 public static void main(String argv[]) {
    //Test.socketTest("abcd");
    //Test.roomDesign();
    
    //Network nw = new Network();
    WindowsManager wm = new WindowsManager();
    Network nw = new Network();
    MessageStack recieved = new MessageStack();
    MessageStack toBeSend = new MessageStack();
    Actions control = new Actions(nw, recieved, toBeSend, wm);
    RecieveThread receiveThread = new RecieveThread(recieved, nw);
    SendThread sendThread = new SendThread(toBeSend, nw);
    nw.setSendAndRecieveThread(sendThread, receiveThread);
   // nw.start();
    
    ServerWindow sw = new ServerWindow(wm, control);
    GameWindow rw = new GameWindow(wm, control);
    wm.setWindows(sw, rw);
    control.waitForMessage();
    
     System.out.println("tady jjjjj");
     
    
            
 }
}

    
    

