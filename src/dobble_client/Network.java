/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


        
public class Network {
    
    private Socket socket;
    private OutputStreamWriter osw;
    private BufferedReader isr;
    
    public Network(String hostname, int port) {
        try {
            connectToServer(hostname, port);
        } catch (IOException ex) {
            System.out.print("Connection Error!");
        }
    }
    
    protected void connectToServer(String hostname, int port) throws IOException {
        socket = new Socket(hostname, port);
        InetAddress adresa = socket.getInetAddress();
        System.out.print("Connecting to: "+adresa.getHostAddress()+" with hostname: "+adresa.getHostName()+"\n" );
        isr = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
        osw = new OutputStreamWriter(socket.getOutputStream());
    }
    
    void sendMessage(String message) throws IOException {
        osw.write(message);
        osw.flush();
    }
    
    String recieveMessage() throws IOException {
        String message = isr.readLine();
        System.out.println("Message Received: " + message);
        return message;
    }
    
    void closeConnection() throws IOException {
        isr.close();
        osw.close();
        socket.close();
    }
    
    String prepareMessage() throws IOException {
       String str = "Ahoj\n";
        if (str.charAt(str.length()-1) != '\n') {
            str = str+'\n';
        }
        return str;
    }
}
