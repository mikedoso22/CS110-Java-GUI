
import java.util.Scanner;
import java.util.ArrayList;
public class War
{

	private ArrayList<Card> faceDown = new ArrayList<Card>();
	private ArrayList<Card> humanWar = new ArrayList<Card>();
	private ArrayList<Card> computerWar = new ArrayList<Card>();
	private ArrayList<Card> humanDeck = new ArrayList<Card>();
	private ArrayList<Card> computerDeck = new ArrayList<Card>();


	private HumanPlayer human;
	private Player computer;
	private String response;
	private int turns = 0;



	public War()
	{
		System.out.println("Let's play a game of War.");
		Scanner input = new Scanner(System.in);
		Deck deck = new Deck();          	
		boolean win = false;

		for(int i = 0; i < deck.deckSize()/2; i++)//fills player Deck
			humanDeck.add(deck.getDeck().get(i));

		for(int i = deck.deckSize()/2; i < deck.deckSize(); i++)//fills computer Deck
			computerDeck.add(deck.getDeck().get(i));

		computer = new Player(humanDeck);//creates AI
		human = new HumanPlayer(computerDeck);//creates Human Player

		System.out.print("Take turn? (Y/N): ");
		response = input.nextLine();
		while(human.takeTurn(response) != -1 && computer.takeTurn() != -1)//as long as both Deck's have cards and the Human still wishes to play
		{			
				System.out.println("Player card: " + human.getHumanDeck().get(0));
				System.out.println("Computer card: " + computer.getPlayerDeck().get(0));
				if(human.getHumanDeck().get(0).getRank() > computer.getPlayerDeck().get(0).getRank())//if the Human has a winning Card
				{
					human.addWin(computer.getPlayerDeck().get(0));//Add the Computer's Card to the Human's Deck
					human.addWin(human.getHumanDeck().get(0));//Add the Human's Card to the bottom of the Human's Deck
					human.removeLoss();					//Removes the top Card of the Human's Deck after it was added to the bottom
					computer.removeLoss();				//Removes the top Card of the Computer's Deck after it was added to the bottom
					System.out.println("Player 1 wins");
				}
				else if (human.getHumanDeck().get(0).getRank() < computer.getPlayerDeck().get(0).getRank()) //if the Computer has a winning Card
				{
					computer.addWin(human.getHumanDeck().get(0));//Add the Human's Card to the Computer's Deck
					computer.addWin(computer.getPlayerDeck().get(0));//Add the Computer's Card to the Computer's Deck
					computer.removeLoss();						//Removes the top Card of the Computer's Deck after it was added to the bottom
					human.removeLoss();							//Removes the top Card of the Human's Deck after it was added to the bottom
					System.out.println("Computer wins");
				}
				else//if there is a tie of two Card's
				{
					win = warGames(human, computer);//boolean equal to the return of warGames(see below)
					if(win)
					{	
						while(!humanWar.isEmpty())
						{
							human.addWin(humanWar.get(0));//add all Card's the Human
							humanWar.remove(0);
						}
						while(!faceDown.isEmpty())
						{
							human.addWin(faceDown.get(0));
							faceDown.remove(0);
						}
						while(!computerWar.isEmpty())
						{
							human.addWin(computerWar.get(0));	
							computerWar.remove(0);					
						}
					}
					else
					{
						while(!computerWar.isEmpty())
						{
							computer.addWin(computerWar.get(0));
							computerWar.remove(0);
						}
						while(!faceDown.isEmpty())
						{
							computer.addWin(faceDown.get(0));
							faceDown.remove(0);
						}
						while(!humanWar.isEmpty())
						{
							computer.addWin(humanWar.get(0));
							humanWar.remove(0);
						}
					}	
				}
				turns++;
				System.out.print("Take turn? (Y/N): ");
				response = input.nextLine();										
		}
		System.out.println("The war took: " + turns + " turns to end.");
		if(human.getHumanDeck().size() > computer.getPlayerDeck().size())
			System.out.println("Player 1 wins the War.");
		else if(human.getHumanDeck().size() < computer.getPlayerDeck().size())
			System.out.println("Computer wins the War.");
		else
			System.out.println("There are no ties in War. Coward.");
	}

	public boolean warGames(HumanPlayer hum, Player comp)
	{		
		boolean victor = false;
    	if(hum.getHumanDeck().size() > 1 && comp.getPlayerDeck().size() > 1)
    	{
			humanWar.add(hum.getHumanDeck().get(0));
			human.removeLoss();
			computerWar.add(comp.getPlayerDeck().get(0));
			computer.removeLoss();
			System.out.println("One face down card.");
			faceDown.add(hum.getHumanDeck().get(1));
			human.removeLoss();
			faceDown.add(comp.getPlayerDeck().get(1));
			computer.removeLoss();
			System.out.println("Player card: " + hum.getHumanDeck().get(0));
			System.out.println("Computer card: " + comp.getPlayerDeck().get(0));
			//humanWar.add(hum.getHumanDeck().get(2));
			//human.removeLoss();
			//computerWar.add(comp.getPlayerDeck().get(2));
			//computer.removeLoss();
			
			if(hum.getHumanDeck().get(0).getRank() == comp.getPlayerDeck().get(0).getRank())
			{
				warGames(human, computer);
			}
			else if (hum.getHumanDeck().get(0).getRank() > comp.getPlayerDeck().get(0).getRank()) 
			{
				System.out.println("Player 1 wins");
				victor = true;			
			}
			else
			{
				System.out.println("Computer wins");
				victor = false;
			}			
		}
		else if(hum.getHumanDeck().size() == 1)
		{
			System.out.println("Computer wins. Game over.");
			victor = false;
		}
		else if (comp.getPlayerDeck().size() == 1)
		{
			System.out.println("Player 1 wins. Game over.");
			victor = true;
		}
		return victor;
	}
}