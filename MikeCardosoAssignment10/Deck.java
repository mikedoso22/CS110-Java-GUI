/**
	Import ArrayList so deck can be mutable
	Import Collections to "shuffle" ArrayList
*/
import java.util.ArrayList;
import java.util.Collections;

public class Deck 
{
	
	/** 
		ArrayList for deck declared
		Number of RANKS and SUITS declared for Deck creation
	*/
	private ArrayList<Card> deck = new ArrayList<Card>();
	private final int RANKS = 14;
	private final int SUITS = 4;
	/**
		Constructs a regular 52 card Deck, and immediately shuffles 
		the deck using the Collections shuffle method
	*/
	public Deck()
	{

		for(int i = 0; i < SUITS; i++)
		{
			for(int j = 2; j < RANKS +1; j++)
			{
				Card newCard = new Card(j,i);
				deck.add(newCard);
			}
		}
		//Collections.shuffle(deck);
	}

	/**
	@return size of the ArrayList that is the Deck
	will be used to see if Deck is empty for a loss/victory
	*/
	public int deckSize()
	{
		return deck.size();
	}

	/**
	Gives access to values in the deck for comparison checks
   @return ArrayList the list of Cards the Deck contains
	*/
	public ArrayList<Card> getDeck()
	{
		return deck;
	}
}