package dobble_client.game;

/**
 *
 * @author anvy
 */
public class Cards {
    
    private Card[] stack = new Card[]{
        new Card(Symbol.A, Symbol.B, Symbol.C, Symbol.D, Symbol.E),
        new Card(Symbol.A, Symbol.B, Symbol.C, Symbol.D, Symbol.E),
        new Card(Symbol.A, Symbol.B, Symbol.C, Symbol.D, Symbol.E),
        new Card(Symbol.A, Symbol.B, Symbol.C, Symbol.D, Symbol.E),
        new Card(Symbol.A, Symbol.B, Symbol.C, Symbol.D, Symbol.E)
    };
    
    public Card getCard(int index) {
        if (index < stack.length) {
            return stack[index];
        }
        return null;
    }

}
