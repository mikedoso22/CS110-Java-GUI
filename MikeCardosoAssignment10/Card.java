/*
 * Mike Cardoso
 * CS110
 */
public class Card 
{

    public final static int SPADES = 0;   // Codes for the 4 suits, plus Joker.
    public final static int HEARTS = 1;
    public final static int DIAMONDS = 2;
    public final static int CLUBS = 3;

    public final static int ACE = 14;      // Codes for the non-numeric cards.
    public final static int JACK = 11;    //   Cards 2 through 10 have their 
    public final static int QUEEN = 12;   //   numerical values for their codes.
    public final static int KING = 13;
    private final int suit; 
    private final int rank;

    public Card(int value, int cardSuit) {
        rank = value;
        suit = cardSuit;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public String toString() { 
        String card_rank;
        String card_suit;
        String str;
        switch (rank) {
            case 2:   card_rank = "2";
            break;
            case 3:   card_rank = "3";
            break;
            case 4:   card_rank = "4";
            break;
            case 5:   card_rank = "5";
            break;
            case 6:   card_rank = "6";
            break;
            case 7:   card_rank = "7";
            break;
            case 8:   card_rank = "8";
            break;
            case 9:   card_rank = "9";
            break;
            case 10:  card_rank = "10";
            break;
            case 11:  card_rank = "Jack";
            break;
            case 12:  card_rank = "Queen";
            break;
            case 13:  card_rank = "King";
            break;
            case 14:   card_rank = "Ace";
            break;

            default:  card_rank = "??";
        }
        switch (suit) {
            case 0: card_suit = "Spades";
            break;
            case 1: card_suit = "Hearts";
            break;
            case 2: card_suit = "Diamonds";
            break;
            default: card_suit = "Clubs";
        }
        str = "Suit: " + card_suit + " Rank: " + card_rank;
        return str;
    }        

    public boolean equals(Card otherCard)
    {
        if(getRank() == otherCard.getRank())
            return true;
        else
            return false;
    }


}
