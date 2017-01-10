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

import dobble_client.gui.RoomWindow;
import dobble_client.network.Network;
import dobble_client.gui.ServerWindow;
import dobble_client.gui.WindowsManager;
import javax.swing.JDialog;


public class Dobble_Client {
 public static void main(String argv[]) {
    //Test.socketTest("abcd");
    //Test.roomDesign();
    
    //Network nw = new Network();
    WindowsManager wm = new WindowsManager();
    Network nw = new Network(wm);
   // nw.start();
    
    ServerWindow sw = new ServerWindow(nw, wm);
    RoomWindow rw = new RoomWindow(nw, wm);
    wm.setWindows(sw, rw);
    
    
     System.out.println("tady jjjjj");
     
    
            
 }
}

    
    

