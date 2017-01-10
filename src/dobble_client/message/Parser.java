
package dobble_client.message;

public class Parser {
    private final int TYPE_INDEX = 0;
    private final int SUBTYPE_INDEX = 1;
    private final int TEXT_INDEX = 2;
    
    
    public ParsedMessage parseMessage(String message) {
        String modifiedMessage;
        ParsedMessage pm = new ParsedMessage();
        getType(message, pm);
        getSubtype(message, pm);
      /*  getLength(message, pm);*/
        getText(message, pm);
        
        return pm;
    }
    
    private void getType(String message, ParsedMessage pm) {
        pm.setType(message.charAt(TYPE_INDEX));
    }
    
    private void getSubtype(String message, ParsedMessage pm) {
        pm.setSubtype(message.charAt(SUBTYPE_INDEX));
    }
   /* 
    private void getLength(String message, ParsedMessage pm) {
        char len = message.charAt(2);
        pm.setLength((byte)len);
    }
   */ 
    private void getText(String message, ParsedMessage pm) {
        pm.setText(message.substring(TEXT_INDEX));
    }
}
