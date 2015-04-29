# CS110-Java-GUI
work in progress

The War class has many System.out.println() commented out, along with user input statements.
Those statements are usable when uncommented and the game will run using the WarDriver class on the command line.

The game is currently set up to be implemented and integrated with the GUI. The game runs and the GUI allows the user to know
which player has how many cards, and thus who is winning at that point in time. The game is functional and will run until
a victor is determined. In certain edge cases as of 4/29 12:00pm, the game will crash during a War when ties are made
and it appears when during that tie a Player runs out of Cards in their Deck. I'm currently looking into fixing this bug.
Also the GUI does not give the best feedback in terms of when a War occurs and multiple cards are "on the field" and removed
from each Player's Deck. In the case of War, when there are multiple Cards "on the field" the total number of Cards in each 
Player's hand will not total to 52 Cards because they are stored elsewhere. After the War ends and a winner is determined,
all the Cards are returned to that Player's Deck, and the total number of Card's returns to 52.


5:00PM 4/29 I think I fixed the bug that crashed the game upon a tie if a Player has too few cards

8:00PM 4/29 All files are final and committed
