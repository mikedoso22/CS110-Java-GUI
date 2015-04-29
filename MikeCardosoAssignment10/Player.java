	
/**
	Import ArrayList so deck can be mutable
	Import Collections to "shuffle" ArrayList
*/
import java.util.Collections;
import java.util.ArrayList;
public class Player
{
	/**
	Creates ArrayList for the Player that is their "Deck" or "hand"
	*/
	private ArrayList<Card> player1 = new ArrayList<Card>();

	/**
	Constructs a player that takes the first half
	of the shuffled deck, and then shuffles again
      @param half passes constructor an ArrayList to be used as that Player's hand

	*/
	public Player(ArrayList<Card> half)
	{
		for(int i = 0; i < half.size(); i++)
		{
			player1.add(half.get(i));
		}
		Collections.shuffle(player1);
	}

	/**
	@return the ArrayList that represents the Deck
	that the Player currently possesses
	to allow for checking the elements in the deck
	*/
	public ArrayList<Card> getPlayerDeck()
	{
		return player1;
	}

	/**
	causes the Player to take their turn
	if the Deck is empty the method returns -1
	else the method returns the Rank of the Card on top of the Deck
   @return int to determine if the game continues
	*/
	public int takeTurn()
	{
		if(player1.isEmpty())
		{
			int card = -1;
		}
		int card = player1.get(0).getRank();		
		return card;
	}

	/**
	  Adds a Card to the Deck of the Player
	  
	 @param c the Card to be added to the Player's Deck
	 */
	public void addWin(Card c)
	{
		player1.add(c);
	}


	/**
	  Removes the top Card from the Deck of the Player
	 */
	public void removeLoss()
	{
		player1.remove(0);
	}
}