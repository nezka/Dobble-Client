package dobble_client.game;

/**
 *
 * @author anvy
 */
public class Cards {
    
    private Card[] stack = new Card[]{
        new Card(0, Symbol.A, Symbol.B, Symbol.C, Symbol.D, Symbol.E),
        new Card(1, Symbol.A, Symbol.B, Symbol.C, Symbol.D, Symbol.E),
        new Card(2, Symbol.A, Symbol.B, Symbol.C, Symbol.D, Symbol.E),
        new Card(3, Symbol.A, Symbol.B, Symbol.C, Symbol.D, Symbol.E),
        new Card(4, Symbol.A, Symbol.B, Symbol.C, Symbol.D, Symbol.E)
    };
    
    public Card getCard(int index) {
        if (index < stack.length) {
            return stack[index];
        }
        return null;
    }

}
