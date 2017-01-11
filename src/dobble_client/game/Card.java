
package dobble_client.game;


public class Card {
    
    private final int SYMBOL_COUNT = 5;
    
    private Symbol[] symbols = new Symbol[SYMBOL_COUNT];
    private int id;
    
    public Card(int id, Symbol first, Symbol sec, Symbol third, Symbol fourth, Symbol fifth) {
        
        this.symbols[0] = first;
        this.symbols[1] = sec;
        this.symbols[2] = third;
        this.symbols[3] = fourth;
        this.symbols[4] = fifth;
    }
    
    public int getCardId() {
        return id;
    }
}
