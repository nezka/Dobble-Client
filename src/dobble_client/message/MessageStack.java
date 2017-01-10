/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dobble_client.message;

import dobble_client.message.ParsedMessage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anvy
 */
public class MessageStack {
    private List<ParsedMessage> stack = new ArrayList<ParsedMessage>();
    
    public synchronized void addMessage(ParsedMessage m) {
        stack.add(m);
    }
    
    public synchronized ParsedMessage getMessage() {
        if (!stack.isEmpty()) {
            return stack.get(0);
        }
        return null;
    }
    
    public synchronized int getSize() {
        return stack.size();
    }
    
    public synchronized boolean isEmty() {
        if (stack.size() == 0) {
            return true;
        }
        return false;
    }
}
