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

import dobble_client.game.Controller;
import dobble_client.gui.RoomWindow;
import dobble_client.network.Network;
import dobble_client.gui.ServerWindow;
import dobble_client.gui.WindowsManager;
import dobble_client.network.MessageStack;
import dobble_client.network.RecieveThread;
import dobble_client.network.SendThread;
import javax.swing.JDialog;


public class Dobble_Client {
 public static void main(String argv[]) {
    //Test.socketTest("abcd");
    //Test.roomDesign();
    
    //Network nw = new Network();
    WindowsManager wm = new WindowsManager();
    Network nw = new Network();
    MessageStack recieved = new MessageStack();
    MessageStack toBeSend = new MessageStack();
    Controller control = new Controller(nw, recieved, toBeSend);
    RecieveThread receiveThread = new RecieveThread(recieved, nw);
    SendThread sendThread = new SendThread(toBeSend, nw);
    nw.setSendAndRecieveThread(sendThread, receiveThread);
   // nw.start();
    
    ServerWindow sw = new ServerWindow(wm, control);
    RoomWindow rw = new RoomWindow(wm, control);
    wm.setWindows(sw, rw);
    control.run();
    
     System.out.println("tady jjjjj");
     
    
            
 }
}

    
    

