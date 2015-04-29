
import java.util.ArrayList;
import java.util.Collections;
public class HumanPlayer extends Player
{

	private ArrayList<Card> human = new ArrayList<Card>();
	public HumanPlayer(ArrayList<Card> half)
	{
		super(half);
		human = super.getPlayerDeck();
	}

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