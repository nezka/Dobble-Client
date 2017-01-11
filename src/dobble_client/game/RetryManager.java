/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


/**
 *
 * @author anvy
 */
public class RetryManager {
    
    protected static String getRetrySecret() {
        String secret = null;
        BufferedReader br = null;
        File file = new File("retry.txt");
        if (!file.exists()) {
            return null;
        }
        try {
            br = new BufferedReader(new FileReader(file));
            secret = br.readLine();
        } catch(IOException e) {
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    System.err.println("Can't close the retry file.");
                }
            }
        }
        return secret;

    }
    
    protected static void setRetrySecret(String secret) {
        Writer wr = null;
        File file = new File("retry.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.err.println("Can't create the file for secret.");
                return;
            }
        }
        
        try {
            wr = new OutputStreamWriter(new FileOutputStream("retry.txt"));
            wr.write(secret);
            if (secret.charAt(secret.length()-1) != '\n') {
                wr.write("\n");
            }
           
        } catch (IOException ex) {
            
        } finally {
            try {
                if (wr != null) {
                    wr.close();
                }     
            } catch (Exception ex) {
                System.err.println("Can't close the retry file.");
                return;
            }
        }


    }
    
}
