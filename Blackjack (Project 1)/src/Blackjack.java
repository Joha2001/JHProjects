import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {


		boolean playGame = true; //While true, game will play, when false, game ends and goes to the thank you.
		int playerHand = 0, playerCount = 0;
		int dealerHand = 0, dealerCount = 0;
		int givenCard = 0;
		int gameCount = 1;
		int tieCount = 0;


		P1Random rng = new P1Random();
		Scanner scnr = new Scanner(System.in);


		int choice = 0;
		while (playGame == true) // Beginning of the playGame loop
		{
			System.out.println("START GAME #" + gameCount);
			givenCard = rng.nextInt(13) + 1;
			System.out.print("Your card is a ");
			if (givenCard == 1) // If the card is equal to 1, it displays ACE! Beginning of "GivenCard" Function
			{
				System.out.println("ACE!");
			}
			else if (givenCard <= 10) // If the card is equal to any number 2-10, it displays the numerical value.
			{
				System.out.println(givenCard + "!");
			}
			else if (givenCard == 11) // If the card is equal to 11, it displays JACK!
			{
				System.out.println("JACK!");
			}
			else if (givenCard == 12) // If the card is equal to 12, it displays QUEEN!
			{
				System.out.println("QUEEN!");
			}
			else if (givenCard == 13) // If the card is equal to 13, it displays KING!
			{
				System.out.println("KING!");
			} //there is no else statement because it is impossible to get a value greater than 10 or 0.
			if (givenCard <= 10) // The if statement makes it so Jack, Queen, and King are given the numerical value of 10, while all other values stay the same.
			{
				playerHand += givenCard;
				System.out.println("Your hand is: " + playerHand);
			}
			else
			{
				playerHand += 10;
				System.out.println("Your hand is: " + playerHand);
			} // Ending of "GivenCard" Function
			boolean choiceCycle = true; // Creates a boolean for the choiceCycle loop.
			while (choiceCycle == true) // Beginning of choiceCycle loop
			{
				System.out.print("1. Get another card \n2. Hold hand \n3. Print statistics \n4. Exit\nChoose an option: ");
				choice = scnr.nextInt();
				if (choice == 1) // Get another card
				{
					givenCard = rng.nextInt(13) + 1;
					System.out.print("Your card is a ");
					if (givenCard == 1) // Beginning of "GivenCard" Function (A repeat of the code from earlier)
					{
						System.out.println("ACE!");
					}
					else if (givenCard <= 10)
					{
						System.out.println(givenCard + "!");
					}
					else if (givenCard == 11) // If the card is equal to 11, it displays JACK!
					{
						System.out.println("JACK!");
					}
					else if (givenCard == 12) // If the card is equal to 12, it displays QUEEN!
					{
						System.out.println("QUEEN!");
					}
					else if (givenCard == 13) // If the card is equal to 13, it displays KING!
					{
						System.out.println("KING!");
					} //there is no else statement because it is impossible to get a value greater than 10 or 0.
					if (givenCard <= 10) // The if statement makes it so Jack, Queen, and King are given the numerical value of 10, while all other values stay the same.
					{
						playerHand += givenCard;
						System.out.println("Your hand is: " + playerHand);
					}
					else
					{
						playerHand += 10;
						System.out.println("Your hand is: " + playerHand);
					} // Ending of "GivenCard" Function
					if(playerHand > 21) //Test if the card given makes playerHand greater than 21
					{
						System.out.println("You exceeded 21! You lose.");
						choiceCycle = false;
						playerHand = 0;
						dealerCount++;
					}
					else if (playerHand == 21) //Tests if the player card given makes the playerHand 21
					{
						System.out.println("BLACKJACK! You win!");
						playerHand = 0;
						playerCount++;
						choiceCycle = false;
					}
				}
				else if (choice == 2) // Hold hand
				{
					dealerHand = rng.nextInt(11) + 16;
					System.out.println("Dealer's hand: " + dealerHand + "\nYour hand is: " + playerHand);
					if (dealerHand > 21 || dealerHand < playerHand)
					{ // Tests for player win
						System.out.println("You win!");
						playerCount++;
					}
					else if (dealerHand == playerHand)
					{ // Tests for tie
						System.out.println("It's a tie! No one wins!");
						tieCount++;
					}
					else // If other conditions are not met, player loses
					{
						System.out.println("Dealer wins!");
						dealerCount++;
					}
					playerHand = 0;
					dealerHand = 0;
					choiceCycle = false;
				}
				else if (choice == 3) // Statistics
				{
					System.out.println("Number of Player wins: " + playerCount);
					System.out.println("Number of Dealer wins: " + dealerCount);
					System.out.println("Number of tie games: " + tieCount);
					System.out.println("Total # of games played is: " + (gameCount-1));
					System.out.println("Percentage of Player wins: " + (playerCount*1.0/(gameCount-1)) * 100 + "%");
				}
				else if (choice == 4) // Exit
				{
					playGame = false;
					choiceCycle = false;
				}
				else // For choices not between 1 and 4.
					System.out.println("Invalid input!\nPlease enter an integer value between 1 and 4.");

			} // Ending of choiceCycle loop
			gameCount++; // Increases the game counter
		} // Ending of playGame loop
	}
}

