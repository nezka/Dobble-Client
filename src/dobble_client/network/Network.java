/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.network;

import dobble_client.gui.WindowsManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

        
public class Network {
    
    private Socket socket;
    private OutputStreamWriter osw;
    private BufferedReader isr;
    private int port;
    private String hostname;
    private WindowsManager windows;
    
    public Network(WindowsManager windows) {
        socket = null;
        osw = null;
        isr = null;
        port = -1;
        hostname = null;
        this.windows = windows;
    }
    
    
       public String connectToServer(String hostname, int port) {
        InetAddress address;
        try {
            address = InetAddress.getByName(hostname);
            
        } catch (UnknownHostException e ) {
            System.out.println("Uknown host");
            return "Invalid hostname for server.";
        }
        
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(address.getHostAddress(), port), 1000);
            System.out.print("Connecting to: "+address.getHostAddress()+" with hostname: "+address.getHostName()+"\n" );
            
            isr = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
            osw = new OutputStreamWriter(socket.getOutputStream());
            
            return null;
        } catch (IOException ex) {
                System.out.println("IO exception - connection");
                return "Can't connect to server.";
        }
        
    }
    
    
    void sendMessage(String message) throws IOException {
        osw.write(message);
        osw.flush();
        System.out.println("Message Send: " + message);
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
    
}
