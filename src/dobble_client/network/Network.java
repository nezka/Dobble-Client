/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.network;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

        
public class Network {
    
    private Socket socket = null;
    private OutputStreamWriter osw = null;
    private BufferedReader isr = null;
    private RecieveThread recieveThread = null;
    private SendThread sendThread = null;
    
    public Network() {


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
            recieveThread.start();
            sendThread.start();
            return null;
        } catch (IOException ex) {
                System.out.println("IO exception - connection");
                return "Can't connect to server.";
        }
        
    }
    
    
    protected void sendMessage(String message) throws IOException {
        osw.write(message);
        osw.flush();
        System.out.println("Message Send: " + message);
    }
    
    protected String recieveMessage() throws IOException {
        String message = isr.readLine();
        System.out.println("Message Received: " + message);
        return message;
    }
    
    public void closeConnection() throws IOException {
        sendThread.stopThread();
        recieveThread.stopThread();
        isr.close();
        osw.close();
        socket.close();
    }
    
    public void setSendAndRecieveThread(SendThread s, RecieveThread r) {
        sendThread = s;
        recieveThread = r;
    }
    
}
