import java.util.*;

public class RleProgram {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        int menuChoice;
        byte[] loaded = null;
        boolean menuScreen = true;
        System.out.println("Welcome to the RLE image encoder!\nDisplaying Spectrum Image:"); //Welcome text
        ConsoleGfx.displayImage(ConsoleGfx.testRainbow); //Displays color spectrum (rainbow)
        while (menuScreen) {
            System.out.println("RLE Menu"); //Start of menu options
            System.out.println("--------");
            System.out.println("0. Exit");
            System.out.println("1. Load File");
            System.out.println("2. Load Test Image");
            System.out.println("3. Read RLE String");
            System.out.println("4. Read RLE Hex String");
            System.out.println("5. Read Data Hex String");
            System.out.println("6. Display Image");
            System.out.println("7. Display RLE String");
            System.out.println("8. Display Hex RLE Data");
            System.out.println("9. Display Hex Flat Data");
            System.out.print("Select a Menu Option: "); //End of menu options
            menuChoice = scnr.nextInt();
            switch (menuChoice) {
                case 0://Exit
                    menuScreen = false;
                    break;
                case 1://Loading a specific file
                    System.out.println("Enter name of file to load: ");
                    loaded = ConsoleGfx.loadFile(scnr.next());
                    break;
                case 2://Loading the testImage
                    loaded = ConsoleGfx.testImage;
                    System.out.println("Test image data loaded.");
                    break;
                case 3://Reading RLE String
                    System.out.println("Enter an RLE string to be decoded: ");
                    loaded = decodeRle(stringToRle((scnr.next())));
                    break;
                case 4://Reading RLE Hex Data
                    System.out.println("Enter the hex string holding RLE data: ");
                    loaded = decodeRle(stringToData(scnr.next()));
                    break;
                case 5://Reading Flat Data Hex String
                    System.out.println("Enter the hex string holding flat data: ");
                    loaded = stringToData(scnr.next());
                    break;
                case 6://Displaying the image
                    System.out.println("Displaying image...");
                    if (loaded == null)
                        System.out.println("(no data)");
                    else
                        ConsoleGfx.displayImage(loaded);
                    break;
                case 7://Displaying the RLE String
                    System.out.print("RLE representation: ");
                    if (loaded == null)
                        System.out.println("(no data)");
                    else {
                        System.out.print(toRleString(encodeRle(loaded)) + "\n");
                    }

                    break;
                case 8://Displaying the RLE Hex Data
                    System.out.print("RLE Hex values:");
                    if (loaded == null)
                        System.out.println("(no data)");
                    else {
                        System.out.print(toRleString(encodeRle(loaded)).replace(":", "") + "\n");
                    }

                    break;
                case 9: //Displaying the Flat Hex Data
                    System.out.print("Flat hex values: ");
                    if (loaded == null)
                        System.out.println("(no data)");
                    else
                        System.out.print(toHexString(loaded) + "\n");
                    break;
                default://Error message if the user inputs a value that is not between 0 and 9
                    System.out.println("Error! Invalid output.");
            }
        }

    }

    public static String toHexString(byte[] data) {//Changes each value to hexadecimal
        String toHex = "";
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 10) //The following if statements convert the decimal values 10-15 to their respective hexadecimal values a-f
                toHex += data[i];
            else if (data[i] == 10)
                toHex += "a";
            else if (data[i] == 11)
                toHex += "b";
            else if (data[i] == 12)
                toHex += "c";
            else if (data[i] == 13)
                toHex += "d";
            else if (data[i] == 14)
                toHex += "e";
            else if (data[i] == 15)
                toHex += "f";
        }
        return toHex;
    }//End of toHexString

    public static int countRuns(byte[] flatData) {//Returns the number of runs of data, used in encodeRle
        int count = 1;
        int repeatChk = 0;
        byte check = flatData[0];
        for (int i = 0; i < flatData.length; i++) {
            if (flatData[i] != check || repeatChk == 15)//repeatChk == 15 tests for 15 consecutive bytes that are the same
            {
                check = flatData[i];
                count++;
                repeatChk = 0;
            } else
                repeatChk++;
        }
        return count;
    }//End of countRuns

    public static byte[] encodeRle(byte[] flatData) {
        byte[] encode = new byte[countRuns(flatData) * 2];
        byte counter = 0;
        byte check = flatData[0];
        int slot = 0;
        for (int i = 0; i < flatData.length; i++) {
            if (flatData[i] != check || counter == 15)////counter == 15 tests for the same as repeatChk
            {
                encode[slot] = counter;
                encode[slot + 1] = check;
                check = flatData[i];
                counter = 1;
                slot += 2;
            } else
                counter++;
        }
        encode[slot] = counter;
        encode[slot + 1] = check;
        return encode;
    }//End of encodeRle

    public static int getDecodedLength(byte[] rleData)//Adds up every 2 values starting from index 0 to get the decoded length, used in decodeRle
    {
        int length = 0;
        for (int i = 0; i < rleData.length; i += 2) {
            length += rleData[i];
        }
        return length;
    }//End of getDecodedLength

    public static byte[] decodeRle(byte[] rleData) {//Returns decoded data set from RLE encoded data, used in options 3 and 4.
        byte[] decode = new byte[getDecodedLength(rleData)];
        int startOfNext = 0;
        for (int i = 0; i < rleData.length; i += 2) {
            for (int j = 0; j < rleData[i]; j++) {
                decode[j + startOfNext] = rleData[i + 1];
            }
            startOfNext += rleData[i];
        }
        return decode;
    }//End of decodeRle

    public static byte[] stringToData(String dataString) {//Converts a string into hexadecimal format, using substring to find each value within the string, if hex, changes it to the numerical value, else, it parses the integer.
        byte[] toData = new byte[dataString.length()];
        String test = dataString.toLowerCase();//Used to convert all letters to lowercase to have less test cases.
        for (int i = 0; i < test.length(); i++) {
            if ((test.substring(i, i + 1)).equals("a"))
                toData[i] = 10;
            else if ((test.substring(i, i + 1)).equals("b"))
                toData[i] = 11;
            else if ((test.substring(i, i + 1)).equals("c"))
                toData[i] = 12;
            else if ((test.substring(i, i + 1)).equals("d"))
                toData[i] = 13;
            else if ((test.substring(i, i + 1)).equals("e"))
                toData[i] = 14;
            else if ((test.substring(i, i + 1)).equals("f"))
                toData[i] = 15;
            else
                toData[i] = (byte) Integer.parseInt(test.substring(i, i + 1)); //Parses the integer if the value is less than 10.
        }
        return toData;
    }//End of stringToData

    public static String toRleString(byte[] rleData) {//Turns RLE data into readable representation
        String toRle = "";
        byte[] data = new byte[1];//Used to store the current value, so toHexString can be used to convert into hexadecimal.
        for (int i = 0; i < rleData.length; i++) {
            if (i + 1 == rleData.length) {
                data[0] = rleData[i];
                toRle += toHexString(data);
            } else if (i % 2 == 0)
                toRle += rleData[i];
            else {
                data[0] = rleData[i];
                toRle += toHexString(data) + ":";
            }
        }
        return toRle;
    }//End of toRleString

    public static byte[] stringToRle(String rleString) {//Changes a RLE format string into RLE byte data
        int count = 0;
        String changed = "";
        for (int i = 0; i < rleString.length(); i++) {
            int lastCase = i;//Last case is equal to the index but is used to obtain the last set of values
            if ((rleString.charAt(i)) == ':' || i + 1 == rleString.length())//The if statement runs if there is a delimiter colon or the current iteration of the loop is the last.
            {
                if (i + 1 == rleString.length()) //If the current iteration is the last one, then it increase it changes both the count and lastCase scenario.
                {
                    lastCase++;
                    count++;
                }
                if (count == 2) //If count is two, then there is no decimal value that needs to be converted to hexadecimal.
                {
                    changed += rleString.substring(lastCase - 2, lastCase);
                    count = 0;
                } else if (count == 3)//If count is three, then there is a decimal value that needs to be converted to hexadecimal.
                {
                    if (rleString.substring(lastCase - 3, lastCase - 1).equals("10")) //The following if statement converts the decimal to hexadecimal (and it must be the first 2 values after a delimiter).
                        changed += "a";
                    else if (rleString.substring(lastCase - 3, lastCase - 1).equals("11"))
                        changed += "b";
                    else if (rleString.substring(lastCase - 3, lastCase - 1).equals("12"))
                        changed += "c";
                    else if (rleString.substring(lastCase - 3, lastCase - 1).equals("13"))
                        changed += "d";
                    else if (rleString.substring(lastCase - 3, lastCase - 1).equals("14"))
                        changed += "e";
                    else if (rleString.substring(lastCase - 3, lastCase - 1).equals("15"))
                        changed += "f";
                    changed += rleString.substring(lastCase - 1, lastCase); //After the if statement, it places the last value before the next delimiter (this will always be in hexadecimal format).
                    count = 0;
                }
            } else
                count++;
        }
        return stringToData(changed);
    }//End of stringToRle

}
