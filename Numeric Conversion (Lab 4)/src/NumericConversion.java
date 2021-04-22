import java.util.Scanner;

public class NumericConversion { //NumericConversion
    public static long hexStringDecode(String hex){ //Decodes an entire hexadecimal string and returns its value
        long value = 0;
        for (int i=0;i<hex.length();i++)
        {
            value += hexCharDecode(hex.charAt(i)) *Math.pow(16,hex.length()-i-1);
        }
        return value;
    }
    public static short hexCharDecode(char digit) //Decodes a single hexadecimal digit and returns its value
    {
        short value = 0;
        if (digit == '0') // Tests for 0
        {
            value = 0;
        }
        if (digit == '1') // Tests for 1
        {
            value = 1;
        }
        if (digit == '2') // Tests for 2
        {
            value = 2;
        }
        if (digit == '3') // Tests for 3
        {
            value = 3;
        }
        if (digit == '4') // Tests for 4
        {
            value = 4;
        }
        if (digit == '5') // Tests for 5
        {
            value = 5;
        }
        if (digit == '6') // Tests for 6
        {
            value = 6;
        }
        if (digit == '7') // Tests for 7
        {
            value = 7;
        }
        if (digit == '8') // Tests for 8
        {
            value = 8;
        }
        if (digit == '9') // Tests for 9
        {
            value = 9;
        }
        if (digit == 'A' || digit == 'a') // Tests for any form of a
        {
            value = 10;
        }
        if (digit == 'B' || digit == 'b') // Tests for any form of b
        {
            value = 11;
        }
        if (digit == 'C' || digit == 'c') // Tests for any form of c
        {
            value = 12;
        }
        if (digit == 'D' || digit == 'd') // Tests for any form of d
        {
            value = 13;
        }
        if (digit == 'E' || digit == 'e') // Tests for any form of e
        {
            value = 14;
        }
        if (digit == 'F'|| digit == 'f') // Tests for any form of f
        {
            value = 15;
        }
        if (digit == 'x'|| digit == 'X') // Tests for any form of x
        {
            value = 0;
        }

        return value; // Returns the decimal value of a hexadecimal char
    }
    public static short binaryStringDecode(String binary) //Decodes a binary string and returns its value
    {
        short value = 0;
        for(int i = 0; i < binary.length(); i++)
        {
            if(binary.charAt(i) == '0') // if the char is 0 then the value added is 0
            {
                value += 0;
            }
            if(binary.charAt(i) == '1') // If the char is 1 then the value added is dependent on position
            {
                value += Math.pow(2,(binary.length()-i-1));
            }
        }
        return value;
    }
    public static String binaryToHex(String binary) //Converts a binary number to a hexadecimal number
    {
         String stringValue = "";
         int value = binaryStringDecode(binary);
         while (value != 0)
         {
             if(value%16 == 10) // If the remainder is 10 then the character added is A
             {
                 stringValue = "A" + stringValue;
             }
             else if(value%16 == 11) // If the remainder is 11 then the character added is B
             {
                 stringValue = "B" + stringValue;
             }
             else if(value%16 == 12) // If the remainder is 12 then the character added is C
             {
                 stringValue = "C" + stringValue;
             }
             else if(value%16 == 13) // If the remainder is 13 then the character added is D
             {
                 stringValue = "D" + stringValue;
             }
             else if(value%16 == 14) // If the remainder is 14 then the character added is E
             {
                 stringValue = "E" + stringValue;
             }
             else if(value%16 == 15) // If the remainder is 15 then the character added is F
             {
                 stringValue = "F" + stringValue;
             }
             else // If the remainder is less than 10 then the character added is value itself
             {
                 stringValue = (value%16) + stringValue;
             }
             value /= 16;
         }


         return stringValue;
    }


    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        boolean menu = true;
        int choice;
        String stringToConvert;
        while(menu) { // Menu loop allows the user to repeat the program as many times as they want to
            System.out.println("Decoding Menu\n" + "-------------\n" + "1. Decode hexadecimal\n" + "2. Decode binary\n" + "3. Convert binary to hexadecimal\n" + "4. Quit");
            System.out.println("Please enter an option: ");
            choice = scnr.nextInt(); //Scans for the integer that denotes the option 1-4.
            switch(choice){
                case 1: //Decode hexadecimal
                    System.out.println("Please enter the numeric string to convert: ");
                    stringToConvert = scnr.next();
                    System.out.println("Result: " + hexStringDecode(stringToConvert));
                    break;
                case 2: //Decode binary
                    System.out.println("Please enter the numeric string to convert: ");
                    stringToConvert = scnr.next();
                    System.out.println("Result: " + binaryStringDecode(stringToConvert));
                    break;
                case 3: // Convert binary to hexadecimal
                    System.out.println("Please enter the numeric string to convert: ");
                    stringToConvert = scnr.next();
                    System.out.println("Result: " + binaryToHex(stringToConvert));
                    break;
                case 4: // Exit
                    menu = false;
                    System.out.println("Goodbye!");
                    break;

            }
        }
    }
}
