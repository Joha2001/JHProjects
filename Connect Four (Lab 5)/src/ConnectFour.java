import java.util.Scanner;

public class ConnectFour {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        boolean chkWinner = false, tieChk = false;
        int player = 2; //Starts at player 2 because the character switch is at the beginning (to switch it to player 1)
        int column, row, length, height;
        char chipType = 'x';
        System.out.println("What would you like the height of the board to be?");
        height = scnr.nextInt(); //Asks for input for board height (amount of rows in the array)
        System.out.println("What would you like the length of the board to be?");
        length = scnr.nextInt(); //Asks for input for board length (amount of columns in the array)
        char board[][] = new char[height][length]; //Creates a board with specified rows and columns
        initializeBoard(board);
        printBoard(board);
        System.out.println("Player 1: x\nPlayer 2: o");
        while(!chkWinner && !tieChk) //The game goes on until there is a winner or a tie.
        {
            switch(player) //The switch alternates between the players and their chips
            {
                case 1:
                    chipType = 'o';
                    player = 2;
                    break;
                case 2:
                    chipType ='x';
                    player = 1;
                    break;
            }
            System.out.println("Player " + player + ": Which column would you like to choose?");
            column = scnr.nextInt();
            row = insertChip(board,column,chipType);
            printBoard(board);
            chkWinner = checkIfWinner(board,column,row,chipType);

            for(int r = 0;r < board.length;r++) //Loop checks if all slots are filled (which is a tie)
            {
                tieChk = true;
                for(int c = 0;c<board[r].length;c++)
                {
                    if (board[r][c] == '-')
                    {
                        tieChk = false;
                    }
                }
            } //End of tieChk loop
        }//End of chkWinner loop
        if(tieChk)
            System.out.println("Draw. Nobody wins.");
        else
            System.out.println("Player " + player + " won the game!");
        }
    public static void printBoard(char[][] array) {//Method displays the board
        for(int row = 0;row < array.length;row++)
        {
            for(int col = 0;col<array[array.length-row-1].length;col++)
            {
                System.out.print(array[array.length-row-1][col] + " ");
            }
            System.out.println();
        }
    }
    public static void initializeBoard(char[][] array) {//Method sets every value in the array equal to '-'
        for(int row = 0;row < array.length;row++)
        {
            for(int col = 0;col < array[row].length;col++)
            array[array.length-row-1][col] = '-';
        }
    }
    public static int insertChip(char[][] array, int col, char chipType) {//Method inserts chip into designated column at lowest available row
        int rowCount = 0;
        for (int row = 0; row < array.length; row++)
        {
            if (array[row][col] == '-')
            {
                array[row][col] = chipType;
                break;
            }
            else
                rowCount++;
        }
        return rowCount;
    }
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) { //Method checks for a winner
        boolean chkWinner = false;
        int vertCount = 0, horizCount = 0;
        for (int j = 0; j <= col; j++) //The loop checks whether the row has 4 of the same chip type in succession
        {
            if(array[row][j] == chipType)
            {
                horizCount++;
            }
        } // End of loop
        for (int i = 0; i <= row; i++) //The loop checks whether the column has 4 of the same chip type in succession
        {
            if (array[i][col] == chipType)
            {
                vertCount++;
            }
        } //End of loop

        if (vertCount == 4 || horizCount == 4) //Checks if either direction had 4 in a row
            chkWinner = true;
        return chkWinner;
    }
}
