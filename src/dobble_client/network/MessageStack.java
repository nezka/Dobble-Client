/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.network;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anvy
 */
public class MessageStack {
    private final List<ParsedMessage> stack = new ArrayList<ParsedMessage>();
    
    public synchronized void addMessage(ParsedMessage m) {
        stack.add(m);
    }
    
    public synchronized ParsedMessage getMessage() {
        if (!stack.isEmpty()) {                    
            return stack.remove(0);
        }
        return null;
    }
    
    public synchronized int getSize() {
        return stack.size();
    }
    
    public synchronized boolean isEmpty() {
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
}
