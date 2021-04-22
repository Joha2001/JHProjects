public class Factorial {

    public static long pureRecursive(int n){
        if(n > 0 && n != 1)//While greater than 1, the statement multiples n*n-1;
            return n * pureRecursive(n-1);
        else if (n == 1)//When n is equal to 1, it stops the function and returns 1. (So the function doesn't reach non-positive).
            return 1;
        else//If the initial n given is non-positive, it throws an IllegalArgumentException.
            throw new IllegalArgumentException("Undefined for non-positive numbers.");
    }

    public static long tailRecursive(int n){ //Recursion which uses another function (tail) at the end.
        if (n <= 0)// If the initial n is non-positive, it throws an IllegalArgumentException.
            throw new IllegalArgumentException("Undefined for non-positive numbers.");
        else//Otherwise, it uses the tail function to calculate the recursion.
            return tail(n,n-1);
    }

    private static long tail(int n, long m){//Does recursion for tailRecursive.
        if (n == 1)//When the tail function reaches 1, it returns 1 (so the function doesn't go any lower).
            return 1;
        else//Multiplies n by n-1 (done in reverse order, so it starts at 1, then goes upwards).
            return n*tail(n-1,m-1);
    }

    public static long iterative(int n){ //Does not use itself or another function, uses a for loop.
        long amount = 1;//Stores the amount, starts at 1.
        if (n <= 0)//Throws IllegalArgumentException if value given is non-positive.
            throw new IllegalArgumentException("Undefined for non-positive numbers.");
        else{//Otherwise, the function runs through a for loop that decrements by 1, starting at the initial n value.
            for (int i = n; i > 0; i--)
                amount *= i;//Multiplies the previous amount by the current iteration of i (which is some form of n - ?).
        }
        return amount;//Returns the final amount.
    }


}
