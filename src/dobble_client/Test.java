/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anvy
 */
public class Test {

    public static void parserTest() {
        String mes = "" + "BA" + "ahoj";
        Parser p = new Parser();
        System.out.println(mes);
        ParsedMessage pm = p.parseMessage(mes);
        System.out.println(pm.getType());
        System.out.println(pm.getSubtype());
        /*System.out.println(pm.getLength());*/
        System.out.println(pm.getText());
        //String.format("%02X", )
    }

    public static void connectionTest() {
        try {
            Network nw = new Network("localhost", 10009);
            nw.sendMessage(nw.prepareMessage());
            nw.recieveMessage();
            new RoomWindow(17);

        } catch (IOException ex) {
            Logger.getLogger(Dobble_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void socketTest(String hostname) {
        try {
            Socket socket = new Socket();
            InetAddress address = InetAddress.getByName(hostname);
            System.out.println("Adresa: " + address.getHostAddress() + " name: " +address.getHostName()+"\n");
            try {
                socket.connect(new InetSocketAddress(address.getHostAddress(), 1222), 1000);
            } catch (IOException ex) {
                System.out.println("io\n");
                return;
            }
            
            
        } catch (UnknownHostException ex) {
            System.out.println("unknownhost");
        }
    }
}
