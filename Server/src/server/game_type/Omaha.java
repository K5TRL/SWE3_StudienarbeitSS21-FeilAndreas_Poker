package server.game_type;

public class Omaha extends TexasHoldEm{

    private static final int MAX_CARDS_ON_HAND = 4;
    private static final String NAME_OF_THE_GAME = "Omaha";
    private static final int MAX_BETTING_ROUNDS = 4;

    protected Omaha() {
        super(MAX_CARDS_ON_HAND, NAME_OF_THE_GAME, MAX_BETTING_ROUNDS);
    }
}