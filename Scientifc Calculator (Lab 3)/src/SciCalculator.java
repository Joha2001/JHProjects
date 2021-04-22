import java.util.Scanner;

public class SciCalculator {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String firstString, secondString;
        double first, second;
        double current = 0.0;
        double sumOfAvg = 0.0;
        int numOfCalc = 0;
        int choice;
        boolean menuLoop = true;
        boolean choiceLoop = true;
        while (menuLoop) { // The menuLoop allows the scientific calculator to run until the choice chosen is 0 (Exit).
            System.out.println("Current Result: " + current); // Beginning of menu display
            System.out.println("Calculator Menu");
            System.out.println("---------------");
            System.out.println("0. Exit Program");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Exponentiation");
            System.out.println("6. Logarithm");
            System.out.println("7. Display Average"); // End of menu display
            while (choiceLoop) { //Choice loop is primarily used for choice 7, so that after the average is displayed, other calculations can be done.
                System.out.println("Enter Menu Selection: ");
                choice = scnr.nextInt(); // Scans for choice 1-7
                if (choice == 0){ //Exit Program
                   menuLoop = false;
                   choiceLoop = false;
                  System.out.println("Thanks for using this calculator. Goodbye!");
                } else if (choice == 1) { // Addition
                    System.out.println("Enter first operand: ");
                    firstString = scnr.next();
                    if(firstString.equals("RESULT"))
                    {
                        first = current;
                    }
                    else
                    {
                        first = Double.parseDouble(firstString);
                    }
                    System.out.println("Enter second operand: ");
                    secondString = scnr.next();
                    if(secondString.equals("RESULT"))
                    {
                        second = current;
                    }
                    else
                    {
                        second = Double.parseDouble(secondString);
                    }
                    current = first + second;
                    sumOfAvg+= current;
                    numOfCalc++;
                    choiceLoop = false;
                } else if (choice == 2) { // Subtraction
                    System.out.println("Enter first operand: ");
                    firstString = scnr.next();
                    if(firstString.equals("RESULT"))
                    {
                        first = current;
                    }
                    else
                    {
                        first = Double.parseDouble(firstString);
                    }
                    System.out.println("Enter second operand: ");
                    secondString = scnr.next();
                    if(secondString.equals("RESULT"))
                    {
                        second = current;
                    }
                    else
                    {
                        second = Double.parseDouble(secondString);
                    }
                    current = first - second;
                    numOfCalc++;
                    sumOfAvg+= current;
                    choiceLoop = false;
                } else if (choice == 3) { // Multiplication
                    System.out.println("Enter first operand: ");
                    firstString = scnr.next();
                    if(firstString.equals("RESULT"))
                    {
                        first = current;
                    }
                    else
                    {
                        first = Double.parseDouble(firstString);
                    }
                    System.out.println("Enter second operand: ");
                    secondString = scnr.next();
                    if(secondString.equals("RESULT"))
                    {
                        second = current;
                    }
                    else
                    {
                        second = Double.parseDouble(secondString);
                    }
                    current = first*second;
                    numOfCalc++;
                    sumOfAvg+= current;
                    choiceLoop = false;
                } else if (choice == 4) { // Division
                    System.out.println("Enter first operand: ");
                    firstString = scnr.next();
                    if(firstString.equals("RESULT"))
                    {
                        first = current;
                    }
                    else
                    {
                        first = Double.parseDouble(firstString);
                    }
                    System.out.println("Enter second operand: ");
                    secondString = scnr.next();
                    if(secondString.equals("RESULT"))
                    {
                        second = current;
                    }
                    else
                    {
                        second = Double.parseDouble(secondString);
                    }
                    current = first/second;
                    numOfCalc++;
                    sumOfAvg+= current;
                    choiceLoop = false;
                } else if (choice == 5){ // Exponentiation
                    System.out.println("Enter first operand: ");
                    firstString = scnr.next();
                    if(firstString.equals("RESULT"))
                    {
                        first = current;
                    }
                    else
                    {
                        first = Double.parseDouble(firstString);
                    }
                    System.out.println("Enter second operand: ");
                    secondString = scnr.next();
                    if(secondString.equals("RESULT"))
                    {
                        second = current;
                    }
                    else
                    {
                        second = Double.parseDouble(secondString);
                    }
                    current = Math.pow(first, second);
                    numOfCalc++;
                    sumOfAvg+= current;
                    choiceLoop = false;
                } else if (choice == 6){ // Logarithm
                    System.out.println("Enter first operand: ");
                    firstString = scnr.next();
                    if(firstString.equals("RESULT"))
                    {
                        first = current;
                    }
                    else
                    {
                        first = Double.parseDouble(firstString);
                    }
                    System.out.println("Enter second operand: ");
                    secondString = scnr.next();
                    if(secondString.equals("RESULT"))
                    {
                        second = current;
                    }
                    else
                    {
                        second = Double.parseDouble(secondString);
                    }
                    current = Math.log(second)/Math.log(first);
                    numOfCalc++;
                    sumOfAvg+= current;
                    choiceLoop = false;
                } else if (choice == 7){ // Display Average
                    if (numOfCalc == 0)
                    {
                        System.out.println("Error: No calculations yet to average!");
                    }
                    else
                    {
                        System.out.print("Sum of calculations: " + sumOfAvg +"\nNumber of calculations: "+ numOfCalc + "\nAverage of calculations: ");
                        System.out.printf("%.2f",(sumOfAvg/numOfCalc));
                    }
                } else{ //Error for any output that isn't 1-7
                    System.out.println("Error: Invalid selection!");
                }
            }
            choiceLoop = true; //Resets the choiceLoop for the next cycle of the calculator (unless choice 0 is chosen).
        }
    }
}
