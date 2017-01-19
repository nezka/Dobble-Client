
package dobble_client.network;

public class Parser {
    private final int TYPE_INDEX = 0;
    private final int SUBTYPE_INDEX = 1;
    private final int TEXT_INDEX = 2;
    
    
    public ParsedMessage parseMessage(String message) {
        if (message == null) {
            System.out.println("Connection ended\n");
        }
        String modifiedMessage;
        ParsedMessage pm = new ParsedMessage();
        getType(message, pm);
        getSubtype(message, pm);
        getText(message, pm);
        
        return pm;
    }
    
    private void getType(String message, ParsedMessage pm) {
        pm.setType(message.charAt(TYPE_INDEX));
    }
    
    private void getSubtype(String message, ParsedMessage pm) {
        pm.setSubtype(message.charAt(SUBTYPE_INDEX));
    }
 
    private void getText(String message, ParsedMessage pm) {
        pm.setText(message.substring(TEXT_INDEX));
    }
}
