/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client;

import java.io.IOException;
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
            new Window(17);

        } catch (IOException ex) {
            Logger.getLogger(Dobble_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
