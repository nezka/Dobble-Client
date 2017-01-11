
package dobble_client.network;

import java.io.IOException;

public class ParsedMessage {
    private final char MAX_TYPE = 'Z';
    private final char MIN_TYPE = 'A';
    private final int TEXT_SIZE = 420;
    private char type;
    private char subtype;
    private String text;
    
    public ParsedMessage() {
        type = '0';
        subtype = 0;
        //length = 0;
        text = null;
    }
    
    public ParsedMessage(char type, char subtype, byte length, String text) {
        this.type = type;
        this.subtype = subtype;
        //this.length = length;
        this.text = text;
    }
    
    public void setType(char type) {
        if (type >= MIN_TYPE && type <= MAX_TYPE) {
            this.type = type;
        }
    }
    
    public void setSubtype(char subtype) {
        if (subtype >= MIN_TYPE && subtype <= MAX_TYPE) {
            this.subtype = subtype;
        }
    }
 /*   
    public void setLength(byte length) {
        if (length <= TEXT_SIZE) {
            this.length = length;
            System.out.println("length: "+length);
        }
    }
  */  
    public void setText(String text) {
        if (text.length() <= TEXT_SIZE) {
            this.text = text;
        }
    }
    
    public char getType() {
        return this.type;
    }
    
    public char getSubtype() {
        return this.subtype;
    }
    /*
    public byte getLength() {
        return this.length;
    }
    */
    public String getText() {
        return this.text;
    }
    
    public String prepareMessageForSending() {
       String message = type + subtype + text;
        if (message.charAt(message.length()-1) != '\n') {
            message = message+'\n';
        }
        return message;
    }
    
}
