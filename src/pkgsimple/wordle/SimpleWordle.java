/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkgsimple.wordle;

import java.util.Random;
import java.util.Scanner;

/**
 * Name: Matthew Latta
 * Date: 12/01/22
 * Class: CS173B
 * A game of Wordle
 * @author mlatt
 */
public class SimpleWordle {
    
    
    
/**
     * This is what prompts the user to give a 5 letter input.
     * @return col (user input)
     */
    public static String askUserInput(){
        String col = "";
        
        boolean properInput = false;
        boolean quit = false;
        int rVal = 0;
            
        // Checks if user gave proper input or wants to quit
        while(!properInput && !quit){
            if(rVal == 0){
                rVal++;
                    
                // Ask user to enter 5 letter word
                System.out.println("Enter a 5 letter word");
                Scanner in = new Scanner(System.in);
            
                // Word input
                System.out.print("Word: ");
                col = in.next();
            
                col = col.toLowerCase();
                
                // Checks proper input
                properInput = col.length() == 5; // checks length
                
                char[] a = col.toCharArray();
                for(int i = 0; i < a.length; i++){ // checks for integers
                    if(Character.isDigit(a[i])){
                        System.out.println("Integer detected!");
                        properInput = false;
                        break;
                    }
                }
                
                
                    
                
            }else{
                // Ask user to enter 5 letter word
                System.out.println("Invalid input, enter 5 letter word"
                        + "or enter q to quit");
                Scanner in = new Scanner(System.in);
            
                // Word input
                System.out.print("Word: ");
                col = in.next();
                
                col = col.toLowerCase();
                
                // Checks if proper input
                properInput = col.length() == 5;
                
                char[] a = col.toCharArray();
                for(int i = 0; i < a.length; i++){
                    if(Character.isDigit(a[i])){
                        System.out.println("Integer detected!");
                        properInput = false;
                        break;
                    }
                }
                quit = col.equals("q");
            }
        }
        
        
        return col;
    }
    
    
    
    
    /**
     * Gives the introduction to the user.
     */
    public static void giveIntro(){
        
        final String INTRO = "Welcome to Wordle! Please press enter to continue.";
        final String DIRECTIONS = "In order to play Wordle, you must enter a"
                + " five letter word. \nOnce you enter a 5 letter word, you must look"
                + " at \nthe table to see if you guessed any correct letters."
                + "\nIf you haven't, give it another guess as you have 8 total tries!"
                + "\nIf you don't guess the wordle within 8 tries, you lose.";
        
        
        Scanner in = new Scanner(System.in);
        
        String dash = "-";
        System.out.println(dash.repeat(INTRO.length()));
        System.out.println(INTRO);
        System.out.println(dash.repeat(INTRO.length()));
        in.nextLine();
        
        System.out.println(dash.repeat(75));
        System.out.println(DIRECTIONS);
        System.out.println(dash.repeat(75));
        System.out.println();
        
        System.out.println(dash.repeat(21));
        System.out.println("Press enter to start!");
        System.out.println(dash.repeat(21));
        in.nextLine();
    }
    
    
    
    
    
    
    
    /**
     * This method initializes the game prompting the user input
     * @param hiddenWord
     * @param grid 
     */
    public static void startGame(char hiddenWord[], char [][] grid){
        
        int check;
        
        String col = "";
        
        // -- Modify Matrix
        for(int i = 0; i < 8; i++){
            col = askUserInput();
            
            
            // Set correct characters in grid
            modifyGrid(i, col, grid, hiddenWord);
            
            // Print Matrix
            printGrid(grid);
            
            check = i;
            
            if(checkWord(i, col, hiddenWord)){
                check = 0;
                break;
                
            }else if(col.equals("q")){
                System.out.println("Successfully quit");
                break;
            }
                
            
            if(check == 7){
                System.out.println("You didn't guess the word " + 
                    String.valueOf(hiddenWord) + " in 8 tries, sorry!");
            }
        
            
        }
    }
    
    
    
    
    
    
    
    
    /**
     * Initializes every element in parameter to an underscore
     * @param g matrix to be initialized
     * @param m 
     */
    public static void initializeGrid(char[][] g, char m){
        int numRows = g.length;
        int numColumns =g[0].length;
        
        // nested loop to iterate through each row and column of grid
        for (int r =0 ; r< numRows; r++){          // iterate over rows
            for (int c = 0; c< numColumns; c++){       // iterate over columns in current row
                g[r][c]= m;
            }
        }
        
    }
    
    
    
    
    
    
    
    
   /**
    * Prints a representation of the values in the matrix to the screen
    * 
    * @param g matrix to be printed
    */
        public static void printGrid(char[][] g){
        int numRows = g.length;
        int numColumns =g[0].length;
        // print column headers here
            System.out.print("   ");
        for (int i = 0; i<numColumns; i++){
            System.out.printf("%3d  ",i+1);
        }
            System.out.println("");
        // nested loop to iterate through each row and column of grid
        for (int r =0 ; r< numRows; r++){          // iterate over rows
            System.out.printf("%2d: ",r+1);
            for (int c = 0; c< numColumns; c++){       // iterate over columns in current row
 
                System.out.printf("[%c]  ",g[r][c]);
            }
            
            System.out.println("");
        }
        
    }
        
        
        
        
        
        
        
        
    /**
     * This modifies the grid depending on the user input
     * @param row
     * @param col
     * @param g
     * @param a 
     */    
    public static void modifyGrid(int row, String col, char[][] g, char[] a){
        
        String hiddenWord = String.valueOf(a);
        
        for(int i = 0; i < col.length(); i++){
            
            char x = col.charAt(i);
            String e = String.valueOf(x);
            
            
            if(col.charAt(i) == a[i]){
                char f = col.charAt(i);
                g[row][i] = Character.toUpperCase(f); // prints uppercase letter if in right spot
                
                
            }else if(hiddenWord.contains(e)){
                g[row][i] = col.charAt(i); // prints lowercase letter if in hidden word but wrong spot
            }
            
                
                
                
                
                
                
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * This method randomizes which word is the hidden word to guess
     * @return hiddenWord
     */
    public static char[] getWord(){
        char[] hiddenWord = new char[5];
        
        Random rand = new Random();
        
        
        
        int randomNum = rand.nextInt(20);
        
        // The list of words that the Wordle can use
        // There is a better way, my brain is still melted from the
        // calculus exam so I can't mentally process what I should be doing
        switch(randomNum){
            case 0: 
                hiddenWord[0] = 'a';
                hiddenWord[1] = 'p';
                hiddenWord[2] = 'p';
                hiddenWord[3] = 'l';
                hiddenWord[4] = 'e';
                break;
            case 1:
                hiddenWord[0] = 'a';
                hiddenWord[1] = 'l';
                hiddenWord[2] = 'i';
                hiddenWord[3] = 'e';
                hiddenWord[4] = 'n';
                break;
            case 2:
                hiddenWord[0] = 'v';
                hiddenWord[1] = 'e';
                hiddenWord[2] = 'n';
                hiddenWord[3] = 'o';
                hiddenWord[4] = 'm';
                break;
            case 3:
                hiddenWord[0] = 'p';
                hiddenWord[1] = 'r';
                hiddenWord[2] = 'o';
                hiddenWord[3] = 'n';
                hiddenWord[4] = 'g';
                break;
            case 4:
                hiddenWord[0] = 'v';
                hiddenWord[1] = 'a';
                hiddenWord[2] = 'g';
                hiddenWord[3] = 'u';
                hiddenWord[4] = 'e';
                break;
            case 5:
                hiddenWord[0] = 'a';
                hiddenWord[1] = 'u';
                hiddenWord[2] = 'd';
                hiddenWord[3] = 'i';
                hiddenWord[4] = 'o';
                break;
            case 6:
                hiddenWord[0] = 'f';
                hiddenWord[1] = 'l';
                hiddenWord[2] = 'o';
                hiddenWord[3] = 'c';
                hiddenWord[4] = 'k';
                break;
            case 7:
                hiddenWord[0] = 'w';
                hiddenWord[1] = 'r';
                hiddenWord[2] = 'o';
                hiddenWord[3] = 'n';
                hiddenWord[4] = 'g';
                break;
            case 8:
                hiddenWord[0] = 't';
                hiddenWord[1] = 'r';
                hiddenWord[2] = 'a';
                hiddenWord[3] = 'd';
                hiddenWord[4] = 'e';
                break;
            case 9:
                hiddenWord[0] = 'p';
                hiddenWord[1] = 'l';
                hiddenWord[2] = 'a';
                hiddenWord[3] = 'i';
                hiddenWord[4] = 'd';
                break;
            case 10:
                hiddenWord[0] = 's';
                hiddenWord[1] = 'c';
                hiddenWord[2] = 'a';
                hiddenWord[3] = 'r';
                hiddenWord[4] = 'y';
                break;
            case 11:
                hiddenWord[0] = 'd';
                hiddenWord[1] = 'e';
                hiddenWord[2] = 'a';
                hiddenWord[3] = 't';
                hiddenWord[4] = 'h';
                break;
            case 12:
                hiddenWord[0] = 'p';
                hiddenWord[1] = 'r';
                hiddenWord[2] = 'i';
                hiddenWord[3] = 'n';
                hiddenWord[4] = 't';
                break;
            case 13:
                hiddenWord[0] = 'b';
                hiddenWord[1] = 'l';
                hiddenWord[2] = 'a';
                hiddenWord[3] = 'c';
                hiddenWord[4] = 'k';
                break;
            case 14:
                hiddenWord[0] = 'g';
                hiddenWord[1] = 'r';
                hiddenWord[2] = 'e';
                hiddenWord[3] = 'e';
                hiddenWord[4] = 'n';
                break;
            case 15:
                hiddenWord[0] = 'w';
                hiddenWord[1] = 'h';
                hiddenWord[2] = 'i';
                hiddenWord[3] = 't';
                hiddenWord[4] = 'e';
                break;
            case 16:
                hiddenWord[0] = 's';
                hiddenWord[1] = 'o';
                hiddenWord[2] = 'c';
                hiddenWord[3] = 'k';
                hiddenWord[4] = 's';
                break;
            case 17:
                hiddenWord[0] = 'p';
                hiddenWord[1] = 'a';
                hiddenWord[2] = 'n';
                hiddenWord[3] = 't';
                hiddenWord[4] = 's';
                break;
            case 18:
                hiddenWord[0] = 's';
                hiddenWord[1] = 'h';
                hiddenWord[2] = 'i';
                hiddenWord[3] = 'r';
                hiddenWord[4] = 't';
                break;
            case 19:
                hiddenWord[0] = 't';
                hiddenWord[1] = 'a';
                hiddenWord[2] = 'x';
                hiddenWord[3] = 'e';
                hiddenWord[4] = 's';
                break;
        }
        
        return hiddenWord;
    }
    
    
    
    
    
    
    
    
    
    /**
     * This method checks if the user input correctly filled out any chars in 
     * the hidden word
     * @param row
     * @param g
     * @param a
     * @return correctWord
     */
    public static boolean checkWord(int row, String g, char [] a){
        boolean correctWord = false;
        
            
        
            String userInput = g;
            
            String hiddenWord = String.valueOf(a);
                
            
        
        if (userInput.equals(hiddenWord)){
            System.out.println("Congrats, you guessed the wordle: " + hiddenWord + "!");
            
            correctWord = true;
        }
        System.out.println();
        
        
        return correctWord;
    }
    
    
    
    
    
    
    
    public static void main(String[] args) {
        // -- Prompts the user with an introduction, gives them directions and how
        // the game works
        giveIntro();
        
        
        // -- Selects a hidden 5 letter word to solve
        char hiddenWord[] = getWord();
        
        // -- Initalize Matrix
        //    This grid will have 8 rows and 5 columns
        char [][] grid = new char[8][5];
        initializeGrid(grid, '-');
        
        // -- Print out grid
        printGrid(grid);
        
        // -- Start the game
        startGame(hiddenWord,grid);
        


    }
    
}

