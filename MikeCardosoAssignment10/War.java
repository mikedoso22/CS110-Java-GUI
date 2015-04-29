
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
	private String statusMessage;
	private String response = "n";
	private boolean win = false;

	/**
	All commented System.out.prints and Scanner inputs are for playing the game via command line using the WarDriver class

	*/

	/**
      Used for keeping track of the game's state when using the step() method
     */
	public enum StepGameState
    {
    	/**
    	  The game is in a normal state, in between turns.
    	 */
        normal,
        /**
          A war is about to occur.
         */
        war,
        /**
          A card was just played.
         */
        cardJustPlayed,
        /**
          The game is over.
         */
        gameOver,
    }
    /**
      An instance of StepGameState used for keeping track of the game's sate when using the step() method.
     */
    private StepGameState stepGameState;

    /**
	  Creates a game and players ready to be played with respective Decks
    */
	public War()
	{

		Deck deck = new Deck();          	

		for(int i = 0; i < deck.deckSize()/2; i++)//fills player Deck
			humanDeck.add(deck.getDeck().get(i));

		for(int i = deck.deckSize()/2; i < deck.deckSize(); i++)//fills computer Deck
			computerDeck.add(deck.getDeck().get(i));

		computer = new Player(computerDeck);//creates AI
		human = new HumanPlayer(humanDeck);//creates Human Player

		
	}


	/**
	  In the case of a tie, go to War
	*/
	public boolean warGames(HumanPlayer hum, Player comp)
	{		
		this.stepGameState = StepGameState.war;
		boolean victor = false;
    	if(hum.getHumanDeck().size() > 1 && comp.getPlayerDeck().size() > 1)
    	{
			humanWar.add(hum.getHumanDeck().get(0));
			human.removeLoss();
			computerWar.add(comp.getPlayerDeck().get(0));
			computer.removeLoss();
			//System.out.println("One face down card.");
			faceDown.add(hum.getHumanDeck().get(0));
			human.removeLoss();
			faceDown.add(comp.getPlayerDeck().get(0));
			computer.removeLoss();
			//System.out.println("Player card: " + hum.getHumanDeck().get(0));
			//System.out.println("Computer card: " + comp.getPlayerDeck().get(0));
			humanWar.add(hum.getHumanDeck().get(0));
			human.removeLoss();
			computerWar.add(comp.getPlayerDeck().get(0));
			computer.removeLoss();
			
			if(hum.getHumanDeck().get(0).getRank() == comp.getPlayerDeck().get(0).getRank())
			{
				warGames(human, computer);
			}
			else if (hum.getHumanDeck().get(0).getRank() > comp.getPlayerDeck().get(0).getRank()) 
			{
				//System.out.println("Player 1 wins");
				victor = true;			
			}
			else
			{
				//System.out.println("Computer wins");
				victor = false;
			}			
		}
		else if(hum.getHumanDeck().size() == 1)
		{
			//System.out.println("Computer wins. Game over.");
			victor = false;
		}
		else if (comp.getPlayerDeck().size() == 1)
		{
			//System.out.println("Player 1 wins. Game over.");
			victor = true;
		}
		return victor;
	}


	/**
	  Gives access to Player and the data it contains
	*/
	public HumanPlayer getHumanPlayer()
	{
		return human;
	}


	/**
	  Gives access to Player and the data it contains
	*/
	public Player getAIPlayer()
	{
		return computer;
	}


	/**
	  Set's the HumanPlayer response based on input from the GUI checkbox
	*/
	public void setResponse(boolean input)
	{
		if(input)
			response = "y";
		else
			response = "n";
	}


	/**
	  Starts a SteppedGame if one has not already begun
	*/
	private void initializeSteppedGame()
    {
    	this.stepGameState = StepGameState.normal;
    	this.statusMessage = "";
    }

	public void step()
	{
		if (this.statusMessage == null)
		{
			initializeSteppedGame(); // This was the first time that this was called.

		}
		if (getGameState() != 0) // If the game is over...
		{
			if (getGameState() > 0)
			{
				this.statusMessage = "You win the game!";
			}
			else
			{
				this.statusMessage = "The computer wins the game.";
			}
		}
		//Scanner input = new Scanner(System.in);
		//System.out.println("Take a turn?(Y/N): ");
		//response = input.nextLine();
		while(human.takeTurn(response) != -1 && computer.takeTurn() != -1)//as long as both Deck's have cards and the Human still wishes to play
		{			
				//System.out.println("Player card: " + human.getHumanDeck().get(0));
				//System.out.println("Computer card: " + computer.getPlayerDeck().get(0));
				if(human.getHumanDeck().get(0).getRank() > computer.getPlayerDeck().get(0).getRank())//if the Human has a winning Card
				{
					human.addWin(computer.getPlayerDeck().get(0));//Add the Computer's Card to the Human's Deck
					human.addWin(human.getHumanDeck().get(0));//Add the Human's Card to the bottom of the Human's Deck
					human.removeLoss();					//Removes the top Card of the Human's Deck after it was added to the bottom
					computer.removeLoss();				//Removes the top Card of the Computer's Deck after it was added to the bottom
					//System.out.println("Player 1 wins");
				}
				else if (human.getHumanDeck().get(0).getRank() < computer.getPlayerDeck().get(0).getRank()) //if the Computer has a winning Card
				{
					computer.addWin(human.getHumanDeck().get(0));//Add the Human's Card to the Computer's Deck
					computer.addWin(computer.getPlayerDeck().get(0));//Add the Computer's Card to the Computer's Deck
					computer.removeLoss();						//Removes the top Card of the Computer's Deck after it was added to the bottom
					human.removeLoss();							//Removes the top Card of the Human's Deck after it was added to the bottom
					//System.out.println("Computer wins");
				}
				else//if there is a tie of two Card's
				{
					win = warGames(human, computer);//boolean equal to the return of warGames(see below)
					if(win)
					{	
						while(!humanWar.isEmpty())
						{
							human.addWin(humanWar.get(0));//add all Card's the Human won from the War
							humanWar.remove(0);			  //removes the Card's added to the Human's Deck from the pile
														  //this helps to avoid duplicates
						}
						while(!faceDown.isEmpty())
						{
							human.addWin(faceDown.get(0));//add all Card's the Human won from the War
							faceDown.remove(0);//removes the Card's added to the Human's Deck from the pile
						}
						while(!computerWar.isEmpty())
						{
							human.addWin(computerWar.get(0));	//add all Card's the Human won from the War
							computerWar.remove(0);				//removes the Card's added to the Human's Deck from the pile	
						}
					}
					else
					{
						while(!computerWar.isEmpty())
						{
							computer.addWin(computerWar.get(0));//add all Card's the Computer won from the War
							computerWar.remove(0);				//removes the Card's added to the Computer's Deck from the pile
						}
						while(!faceDown.isEmpty())
						{
							computer.addWin(faceDown.get(0));	//add all Card's the Computer won from the War
							faceDown.remove(0);					//removes the Card's added to the Computer's Deck from the pile
						}
						while(!humanWar.isEmpty())
						{
							computer.addWin(humanWar.get(0));	//add all Card's the Computer won from the War
							humanWar.remove(0);					//removes the Card's added to the Computer's Deck from the pile
						}
					}	
				}
			response ="n";
			//System.out.println("Take a turn?(Y/N): ");
			//response = input.nextLine();
		}
	}


	 /**
      Gets the state of the game.
      @return A negative number if the computer won, 0 if the game is still going on, a positive number if the player won.
     */
	public int getGameState()
    {
        if (human.getHumanDeck().isEmpty())
            return 1;
        if (computer.getPlayerDeck().isEmpty())
            return -1;
        return 0;
    }

    public String getStatusMessage()
	{
		return this.statusMessage;
	}
	/**
	  Gets the current state of the stepped game.
	  @return an enum representing the state of the game (Game.StepGameState). Can be normal, cardJustPlayed, gameOver, or war.
	 */
	public StepGameState getStepGameState()
	{
		return this.stepGameState;
	}
}