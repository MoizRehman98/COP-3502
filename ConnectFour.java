//editing lab file for github testing 
import java.util.Arrays;
import java.util.Scanner;


public class ConnectFour {

    public static void printBoard(char[][] array) {

            //Prints the board, but the vertical orientation is reversed. So the row at array 0 is actually the bottom row
            //and the row at array (height-1) is the top row.
        for ( int i = array.length - 1; i > -1; i--) {

            for ( int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }

            System.out.println();

        }

    }

    public static void initializeBoard(char[][] array) {
            //Fills the array with '-' character, using an array for loop and the array fill method
        for (char[] chars : array) {
            Arrays.fill(chars, '-');
        }
    }

    public static int insertChip(char[][] array, int column, char chipType) {
            //Initializes the row variable in order to be used later to check victory
        int row = 0;
            //Goes through board, starting from the bottom, and checking for open slots to convert to chips. Records the row and breaks when an open space is found
        for ( int j = 0; j < array.length; j++) {

            if ( array[j][column] == '-' ) {

                array[j][column] = chipType;
                row = j;
                break;
            }
        }
        return row;
    }

    public static boolean checkIfWinner(char[][] array, int column, int row, char chipType){
            //Begin two counters to check for the two victory conditions, horizontal or vertical connect 4, and the return boolean variable
        int counter = 0, counter2 = 0;
        boolean win = false;

        //Using an array for loop, going through every char in the array first vertically.
        // If the space in the array equals the chip type, 1 is added to the counter, but the counter is reset when a non-chip type is read
        for (char[] chars : array) {

            if (chars[column] == chipType) {

                counter += 1;
                    //If the counter reaches four, signifies victory and the boolean win variable is set to true
                if (counter == 4) {
                    win = true;
                }
            } else {
                counter = 0;
            }
        }

            //Again, this time checking for a horizontal victory using the row variable instead. Goes through every character in the specified row
        for ( int j = 0; j < array[row].length; j++) {
                //If the space in the specified row matches the chiptype, adds to the counter2
            if ( array[row][j] == chipType ) {

                counter2 += 1;

                if ( counter2 == 4) {
                    win = true;
                }
            }
            else {
                counter2 = 0;
            }
        }
            //If a win was detected, returns win true
        return win;
    }

    public static boolean checkIfDraw(char[][] array, int height) {
            //Separate method made specifically to check for a draw condition
        int draw = 0;
        boolean drawCondition;
            //Height is used as a variable to check the top row of the array, because the board that is printed
            //is actually upside down relative to the array position that is being referenced, vertically. So the
            //top row of the connect 4 board is actually the biggest array value, not 0. Therefore, height - 1 is needed
            //to determine the top row.
        for ( int j = 0; j < array[height - 1].length; j++) {

            if ( array[height-1][j] != '-') {
                draw += 1;
            }
        }
            //If there are no open slots on the top row, then the counter reaches the length of the top row, and the
            //Condition returns true
        drawCondition = ( draw == array[height-1].length );

        return drawCondition;

    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
            //Prompt for inputs
        System.out.println("What would you like the height of the board to be? ");
        int height = keyboard.nextInt();
        System.out.println("What would you like the length of the board to be? ");
        int width = keyboard.nextInt();
            //Begin array
        char[][] array = new char[height][width];
            //Run methods to initialize board and to print it first
        initializeBoard(array);
        printBoard(array);

        //Begin game and initialize variables
        System.out.println("\nPlayer 1: x\nPlayer 2: o\n");
        int column, row;
        char chipType;
            //Infinite while loop to keep the game going
        while (true) {
                //Player 1's turn. Prompt column to choose, declare chip type, print the board, and check if there's a draw or victory.
            System.out.println("Player 1: Which column would you like to choose? ");
            chipType = 'x';
            column = keyboard.nextInt();
            row = insertChip(array,column,chipType);
            printBoard(array);

            if (checkIfWinner(array, column, row, chipType)) {
                System.out.println("Player 1 won the game!");
                break;
            }
            else if (checkIfDraw(array,height)) {
                System.out.println("Draw. Nobody wins.");
                break;
            }
                //Similar to player 1, victory and draw conditions must be rechecked
            System.out.println("Player 2: Which column would you like to choose? ");
            chipType = 'o';
            column = keyboard.nextInt();
            row = insertChip(array,column,chipType);
            printBoard(array);

            if (checkIfWinner(array, column, row, chipType)) {
                System.out.println("Player 2 won the game!");
                break;
            }
            else if (checkIfDraw(array,height)) {
                System.out.println("Draw. Nobody wins.");
                break;
            }

        }




    }

}
