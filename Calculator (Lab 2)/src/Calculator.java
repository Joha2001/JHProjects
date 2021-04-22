import java.util.*;
public class Calculator {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        double first, second;
        int choice;
        System.out.println("Enter first operand: ");
        first = scnr.nextDouble();
        System.out.println("Enter second operand: ");
        second = scnr.nextDouble();
        System.out.println("Calculator Menu");
        System.out.println("---------------");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("Which operation do you want to perform? ");
        choice = scnr.nextInt();
        if(choice == 1)
        {
            System.out.println("The result of the operation is "+ (first+second) + ". Goodbye!");
        }
        else if(choice == 2)
        {
            System.out.println("The result of the operation is "+ (first-second) + ". Goodbye!");
        }
        else if(choice == 3)
        {
            System.out.println("The result of the operation is "+ (first*second) + ". Goodbye!");
        }
        else if(choice == 4)
        {
            System.out.println("The result of the operation is "+ (first/second) + ". Goodbye!");
        }
        else
        {
            System.out.println("Error: Invalid selection! Terminating program.");
        }
    }
}
