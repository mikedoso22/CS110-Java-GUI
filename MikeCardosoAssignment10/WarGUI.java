
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarGUI extends JFrame
{
	
	private War war;
	private ImageIcon cardBackImage;
	private ImageIcon[][] cardImages = new ImageIcon[4][15];
	private JLabel playerHand;
    private JLabel playerPile;
    private JLabel computerPile;
    private JLabel computerHand;
    private JLabel statusMessage;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton nextStepButton;
    private JCheckBox userInput;
	private final int RANKS = 15;
	private final int SUITS = 4;
	private String rank;

	public WarGUI() throws FileNotFoundException
	{
		war = new War();
		try
        {
            cardBackImage = new ImageIcon(ImageIO.read(new File("cardpics/back.jpg")));
        }
        catch (IOException ex)
        {
            throw new FileNotFoundException("Card image: back.jpg not found.");
        }

        for(int i = 0; i < SUITS; i++)
		{
			for(int j = 2; j < RANKS; j++)
			{
				if(i == 0)
					rank = "s";
				else if (i == 1) 
					rank = "h";
				else if (i == 2)
					rank ="d";
				else if (i == 3)
					rank = "c";
				String fileName = "cardpics/"+j+rank+".jpg";
				try
                {
                    cardImages[i][j] = new ImageIcon(ImageIO.read(new File(fileName)));
                }
                catch (IOException ex)
                {
                    throw new FileNotFoundException("Card image: "+j+rank+".jpg not found.");
                }
				
			}
		}

        playerHand = new JLabel(cardBackImage);
        computerHand = new JLabel(cardBackImage);
        // Leave the piles blank for now.
        playerPile = new JLabel();
        computerPile = new JLabel();

        setLayout(new GridLayout(2,1));
        topPanel = new JPanel(new GridLayout(1,4));
        topPanel.add(playerHand);
        topPanel.add(playerPile);
        topPanel.add(computerPile);
        topPanel.add(computerHand);
        bottomPanel = new JPanel(new GridLayout(2,1));
        statusMessage = new JLabel("Welcome to WAR!");
        nextStepButton = new JButton("Begin Game");
        userInput = new JCheckBox("Would you like to play?");
        nextStepButton.addActionListener(new ButtonListener());

        bottomPanel.add(userInput);
        bottomPanel.add(statusMessage);
        bottomPanel.add(nextStepButton);

        this.add(topPanel);
        this.add(bottomPanel);
	}

	

    private void updateCardImages()
    {
    	if(war.getHumanPlayer().getHumanDeck().size() != 0)
    			playerHand.setIcon(cardBackImage);
    		else
    			playerHand.setIcon(null);
    		playerHand.revalidate();
    		if(!war.getHumanPlayer().getHumanDeck().isEmpty())
    			playerPile.setIcon(cardImages[war.getHumanPlayer().getHumanDeck().get(0).getSuit()][war.getHumanPlayer().getHumanDeck().get(0).getRank()]);
    		else
    			playerPile.setIcon(null);
    		playerPile.revalidate();

    		if(war.getAIPlayer().getPlayerDeck().size() != 0)
    			computerHand.setIcon(cardBackImage);
    		else
    			computerHand.setIcon(null);
    		computerHand.revalidate();
    		if(!war.getAIPlayer().getPlayerDeck().isEmpty())
    			computerPile.setIcon(cardImages[war.getAIPlayer().getPlayerDeck().get(0).getSuit()][war.getAIPlayer().getPlayerDeck().get(0).getRank()]);
    		else
    			computerPile.setIcon(null);
    		computerPile.revalidate();
    }	

    private void updateStatusMessage()
    {
    	String newMessage = "<html>"+war.getStatusMessage()
    			+ "<br>Your hand size: " + war.getHumanPlayer().getHumanDeck().size()
    			+ "<br>Computer's hand size: " + war.getAIPlayer().getPlayerDeck().size()
    			+ "</html>";
    	try
    	{
    		statusMessage.setText(newMessage);
    
    	}
    	catch (NullPointerException ex)
    	{
    		return; //Work around a Java bug...
    	}
    }


    private class ButtonListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent a)
    	{

    		nextStepButton.setText("Continue Game");
    		if(userInput.isSelected())
    		{
    			war.setResponse(true);
    			//userInput.setSelected(false);
    			//nextStepButton.setEnabled(false);
    			
    			
    		}
    		else
    			war.setResponse(false);
    		updateCardImages(); 
    		updateStatusMessage();
    		war.step();
         	war.setResponse(false);
    		updateCardImages();
    		if (war.getGameState() != 0)
            {
            	// Figure out who won
	            if (war.getGameState() > 0)
	            {
	            	javax.swing.JOptionPane.showMessageDialog(null, "The computer won.");
	            }
	            else //g.getGameState() > 0
	            {
	            	javax.swing.JOptionPane.showMessageDialog(null, "You win!");
	            }
	            // And finish up.
	            updateStatusMessage(); // To get the "You win!"/"Computer Wins." message
	            nextStepButton.setEnabled(false); // Disable the continue button.
            }
    		
    		
    		
    	}
    }

    public static void main(String[] args)
    {
        WarGUI wgui;
        try
        {
            wgui = new WarGUI();
        }
        catch (FileNotFoundException ex)
        {
            javax.swing.JOptionPane.showMessageDialog(null,ex.toString());
            return;
        }
        wgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wgui.setSize(700,500);
        wgui.validate();
        wgui.setVisible(true);
    }
}