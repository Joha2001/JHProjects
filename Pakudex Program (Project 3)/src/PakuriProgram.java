import java.util.*;
import java.lang.String;

public class PakuriProgram {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System.in); //Scanner variable
        boolean menuLoop = true; //MenuLoop variable starts the whileLoop and ends it when it is false
        String choice; //Choice variable for the menu.
        String species; //Species variable used to hold a Scanner string
        boolean test = true;
        int capacity = 0;

        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!"); //Initial welcome statement to user
        while (test) {
            System.out.print("Enter max capacity of the Pakudex: "); //Prompt user for capacity
            try{ //Tests if the string is able to be parsed into an integer, if possible, loop ends, otherwise, it will continue to prompt until there is a valid input.
                capacity = Integer.parseInt(scnr.next());
                test = false;
                if(capacity < 0){ //Tests if the parsed integer is negative.
                    test = true;
                    System.out.println("Please enter a valid size.");
                }
            } catch (NumberFormatException | NullPointerException nfe){ //Catches if not possible to parsed into an integer.
                test = true;
                System.out.println("Please enter a valid size.");
            }

        }
        Pakudex pakudex = new Pakudex(capacity); //Creates pakudex with given capacity.
        System.out.println("The Pakudex can hold " + pakudex.getCapacity() + " species of Pakuri."); //Displays the given capacity.

        while (menuLoop) { //The beginning of the menu loop, option 6, exit, allows the menu loop to end.
            System.out.println("\nPakudex Main Menu"); //Start of the main menu
            System.out.println("-----------------");
            System.out.println("1. List Pakuri");
            System.out.println("2. Show Pakuri");
            System.out.println("3. Add Pakuri"); //I chose to use multiple lines because it would be clearer to see the actual menu.
            System.out.println("4. Evolve Pakuri"); //This helps to not have all of the menu on one line where it becomes cluttered.
            System.out.println("5. Sort Pakuri");
            System.out.println("6. Exit"); //End of main menu
            System.out.print("What would you like to do? ");
            choice = scnr.next(); //The choice becomes next String input.

            switch (choice) {
                case "1": //List Pakuri
                    String[] list = pakudex.getSpeciesArray();
                    if (pakudex.getSize() == 0) //If the Pakudex is empty, a corresponding message is displayed.
                        System.out.println("No Pakuri in Pakudex yet!");
                    else { //Otherwise, it displays the String array list.
                        System.out.println("Pakuri In Pakudex:");
                        for (int i = 0; i < list.length; i++)
                            System.out.println((i + 1) + ". " + list[i]);
                    }
                    break;
                case "2": //Show Pakuri
                    System.out.print("Enter the name of the species to display: ");//Prompt
                    species = scnr.next(); //Pakuri to be found.
                    int[] stats = pakudex.getStats(species); //String array that has the stats of the pakuri.
                    if (stats != null) { //Only prints out the list if the Pakuri exists.
                        System.out.println("\nSpecies: " + species); //Beginning of stats
                        System.out.println("Attack: " + stats[0]); //Listed separately for clarity
                        System.out.println("Defense: " + stats[1]);
                        System.out.println("Speed: " + stats[2]); //End of stats

                    }
                    break;
                case "3": //Add Pakuri
                    if (pakudex.getSize() == pakudex.getCapacity())//If the Pakudex is full, it will not add any more Pakuri to the Pakudex.
                        System.out.println("Error: Pakudex is full!");
                    else { //Otherwise, it prompts for the Pakuri to add.
                        System.out.print("Enter the name of the species to add: ");
                        String pakuriToAdd = scnr.next();
                        if (pakudex.addPakuri(pakuriToAdd)) //If it is a successful additional.
                            System.out.println("Pakuri species " + pakuriToAdd + " successfully added!");
                    }
                    break;
                case "4": //Evolve Pakuri
                    System.out.print("Enter the name of the species to evolve: ");
                    species = scnr.next();
                    if (pakudex.evolveSpecies(species))//If evolveSpecies returns true, displays the following message.
                        System.out.println(species + " has evolved!");
                    break;
                case "5": //Sort Pakuri
                    pakudex.sortPakuri();
                    System.out.println("Pakuri have been sorted!");
                    break;
                case "6": //Exit
                    menuLoop = false;//Stops the menu loop.
                    System.out.println("Thanks for using Pakudex! Bye!");//Goodbye text.
                    break;
                default: //If anything outside 1-6 is imputed, the default is displayed.
                    System.out.println("Unrecognized menu selection!");
                    break;
            }
        }
    }
}
