
import java.util.ArrayList;
import java.util.Collections;
public class HumanPlayer extends Player
{

	private ArrayList<Card> human = new ArrayList<Card>();
	/**
		constructs a new HumanPlayer that starts with half the Deck
      @param half passes constructor an ArrayList to be used as that Player's hand
	*/ 
	public HumanPlayer(ArrayList<Card> half)
	{
		super(half);
		human = super.getPlayerDeck();
	}


	/**
		overrides Player takeTurn using user input to determine if the HumanPlayer wishes to continue
      @param response takes in user input to continue playing
      @return int determining if the game can continue
	*/
	public int takeTurn(String response)
	{		
		int card = 0;
		if(response.equalsIgnoreCase("y"))
		{
			if(human.isEmpty())
			{
				card = -1;
			}
			card = human.get(0).getRank();
		}
		else if(response.equalsIgnoreCase("n"))
		{
			card = -1;
		}
		return card;
	}

	public ArrayList<Card> getHumanDeck()
	{			
		return human;
	}

	public void addWin(Card c)
	{
		human.add(c);
	}

	public void removeLoss()
	{
		human.remove(0);
	}
}