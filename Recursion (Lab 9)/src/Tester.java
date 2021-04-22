import java.util.Scanner;
public class Tester {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        boolean test = true;
        while (test) {
            System.out.println("Which test do you want to do?\n1. Pure\n2. Tail\n3. Iterative\n4. Exit ");
            System.out.print("Choice (1-4): ");
            switch (scnr.nextInt()) {
                case 1:
                    System.out.println("\nWhat number do you want to test?");
                    System.out.println("The result for pureRecursive is " + Factorial.pureRecursive(scnr.nextInt()));
                    break;
                case 2:
                    System.out.println("\nWhat number do you want to test?");
                    System.out.println("The result for tailRecursive is " + Factorial.tailRecursive(scnr.nextInt()));
                    break;
                case 3:
                    System.out.println("\nWhat number do you want to test?");
                    System.out.println("The result for iterative is " + Factorial.iterative(scnr.nextInt()));
                    break;
                case 4:
                    test = false;
                    break;
            }
        }
    }
}