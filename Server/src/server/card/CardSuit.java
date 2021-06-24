package server.card;

public enum CardSuit {
    HEARTS{
        @Override
        public String toString() {
            return "hearts";
        }
    },
    DIAMONDS{
        @Override
        public String toString() {
            return "diamonds";
        }
    },
    CLUBS{
        @Override
        public String toString() {
            return "clubs";
        }
    },
    SPADES{
        @Override
        public String toString() {
            return "spades";
        }
    },
}
