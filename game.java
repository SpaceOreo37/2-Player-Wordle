import java.util.*;

public class game
{
    public static void main(String[] args)
    {
        game();
        
    }
    
    
    // Global Variables
    
    public static String player1Name;
    public static String player2Name;
    
    public static int playerNumber = 0;
    
    public static int player1Wins = 0;
    public static int player2Wins = 0;
    
    public static int gameNumber = 1;
    
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[30m";
    
    // Methods
    
    public static void game() {
        
        Scanner sc = new Scanner(System.in);
        
        //Introduction to game
        println("Welcome to 2-Player Wordle!");
        println("In this game, 2 players will compete to see who can win the most wordle games out of 3!");
        println("If there is a tie, a player must win by 2!");
        println("To Get Started, Please Enter The 2 Player's Names");
        
        // Input player 1 name
        println("Player 1's Name:");
        player1Name = sc.nextLine();
        println("Welcome " + player1Name);
        println("");
        
        // Input player 2 name
        println("Player 2's Name:");
        player2Name = sc.nextLine();
        println("Welcome " + player2Name);
        println("");
        
        println("The Game Will Now Begin!");
        
        //Game iteration loop
        
        while (!((player1Wins >= 2 || player2Wins >= 2) && (Math.abs(player1Wins - player2Wins) >= 2))) { // win by 2 logic (at least 2 games won)
            
            if (gameNumber % 2 == 1) {
                playerNumber = 1;
                wordle(player1Name, playerNumber);
            } else {
                playerNumber = 2;
                wordle(player2Name, playerNumber);
            }
            
        }
        
        // Printing out a lot of blank space
        for (int i = 0; i < 100; i++) {
            println("");
        }
        
        // Printing out final win scores
        println("The Game Has Now Ended");
        println("Press Enter to Reveal the Results");
        sc.nextLine();
        
        println("");
        println("");
        println("");
        
        println(player1Name + " had a total of " + player1Wins + " wins.");
        println(player2Name + " had a total of " + player2Wins + " wins.");
        
        
        // Checking the wins against each other
        if (player1Wins > player2Wins) {
            
            println("");
            println("");
            println("");
            
            println("THE WINNER OF THE WORDLE COMPETITION IS " + player1Name.toUpperCase() + "!!!");
            println("THE WINNER OF THE WORDLE COMPETITION IS " + player1Name.toUpperCase() + "!!!");
            println("THE WINNER OF THE WORDLE COMPETITION IS " + player1Name.toUpperCase() + "!!!");
            
            println("");
            println("");
            println("");
            println("");
            println("");
            
            println("Thank You For Playing the Wordle Competition Game!");
        } else {
            
            println("");
            println("");
            println("");
            
            println("THE WINNER OF THE WORDLE COMPETITION IS " + player2Name.toUpperCase() + "!!!");
            println("THE WINNER OF THE WORDLE COMPETITION IS " + player2Name.toUpperCase() + "!!!");
            println("THE WINNER OF THE WORDLE COMPETITION IS " + player2Name.toUpperCase() + "!!!");
            
            println("");
            println("");
            println("");
            println("");
            println("");
            
            println("Thank You For Playing the Wordle Competition Game!");
            
        }
        
        
    }
    
    public static void wordle(String playerName, int playerNum) {
        
        Scanner sc = new Scanner(System.in);
        
        // Array of all 5 letter words
        String[] wordleWords = {
            "ABOUT", "ALERT", "ABOUT", "BREAK", "ABOUT", "BREED", "ABOUT", "BRIEF", "ABOUT", "BRING",
            "ABOUT", "CHAIN", "ABOUT", "CHAIR", "ABOUT", "CHART", "ABOUT", "CHASE", "BEACH", "CROWD",
            "BEACH", "CROWN", "BEACH", "CURVE", "BEACH", "CYCLE", "DREAM", "GUARD", "FLOOD", "ADULT",
            "SIGHT", "ALARM", "FORCE", "WOUND", "BRAVE", "CABLE", "PANIC", "STUDY", "FAITH", "EQUAL",
            "GRADE", "AWARD", "BULLY", "VOICE", "DRIVE", "TITLE", "CHEER", "PAUSE", "LEGAL", "PLATE",
            "ERROR", "SMASH", "TRADE", "BURST", "FLASH", "SHAME", "FLOAT", "STING", "BOOST", "ALIEN",
            "BLEND", "PARTY", "STORE", "SHINE", "MATCH", "TRACK", "ROUTE", "CRASH", "CLOWN", "SHAKY",
            "VEGAN", "POWER", "TRUST", "ENJOY", "BRAIN", "ADOPT", "TOWER", "SHADE", "DELAY", "TWIST",
            "ALERT", "CHOKE", "SPLIT", "RHYME", "MUDDY", "PLANT", "OCCUR", "FAINT", "WASTE", "EMPTY",
            "BLAME", "CREEP", "SHIFT", "ABUSE", "HARSH", "QUEST", "VOCAL", "TAUNT", "DROOL", "CARRY"
        };
        
        // Selecting random word for the wordle game
        Random random = new Random();
        int wordIndex = random.nextInt(100);
        String correctWord = wordleWords[wordIndex];
        
        // print out the correct word of the wordle game (testing) if needed
        //println(correctWord);
        
        // Making an array containing values of the chosenword for checking repeated letters down the line during the guess word check
        char[] chosenWord = new char[5];
        for (int i = 0; i < 5; i++) {
            chosenWord[i] = correctWord.charAt(i);
        }
        
        // Creation of board of the game
        String[] board = new String[30];
        for (int i = 0; i < 30; i++) {
            board[i] = "-";
        }
        
        // Intro to the wordle part of game
        println("It is now " + playerName + "'s turn to play");
        System.out.println();
        println(playerName + ", Welcome To Wordle Game #" + gameNumber + "!");
        System.out.println();
        println("When you are ready to begin, hit enter");
        sc.nextLine();
        System.out.println();
        System.out.println();
        System.out.println();
        println("The Empty Board Will Appear Below, Good Luck and GAME ON!");
        System.out.println();
        
        // Some basic variables for the wordle loop
        int attempts = 1;
        boolean correctGuess = false;
        String guess = "";
        
        
        // Main loop for playing the game
        
        while (attempts <= 6 && !correctGuess) {
            
            int start = (attempts - 1) * 5;
            
            
            // Printing out board in its current state
            for (int i = 0; i < 30; i++) {
                System.out.print(board[i] + " ");
                
                if ((i + 1) % 5 == 0) {
                    println("");
                }
            }
            
            // Getting input from user to input their guess, and making sure it has no exceptions and is 5 letters wrong
            while (guess.length() != 5) {
                
                // Basic info and prompt
                System.out.println();
                println("This is attempt #" + attempts);
                println("Please enter your guess for the word now (5 letters long):");
                System.out.println();
                
                // try catch to make sure valid input, as well as 5 letters long    
                try {
                    
                    // Getting guess
                    guess = sc.next();
                    guess = guess.toUpperCase();
                    System.out.println();
                    
                    // Check to make sure guess is 5 letters long, and is of acceptable input
                    if (guess.length() != 5) {
                        println("Your guess is not 5 letters long. Plesae enter a guess that is 5 letters long.");
                    }
                    
                } catch (Exception e){
                    println("You entered an incompatibale guess, please try again:");
                }
            }
            
            // Checking user's guess against the correct word
            
            for (int i = 0; i < 5; i++) {
                
                if (guess.charAt(i) == chosenWord[i]) {
                    
                    // Change value in board to be the guess letter, with green color
                    board[start + i] = GREEN + guess.charAt(i) + RESET;
                    // Preventing repeats from being found
                    chosenWord[i] = '-';
                    
                } else if (correctWord.indexOf(guess.charAt(i)) != -1) {
                    
                    int index = correctWord.indexOf(guess.charAt(i));
                    
                    // if statement to check if the placement of the found letter (wrong position) is not going to become green in the future
                    if (guess.charAt(index) == chosenWord[index]) {
                        board[start + i] = "" + guess.charAt(i);
                    } else {
                        // If it passes all the tests, then save character to board with yellow color
                        board[start + i] = YELLOW + guess.charAt(i) + RESET;
                    }
                    
                    
                } else {
                    // If the letter is a dud, then just save the letter itself, no color
                    board[start + i] = "" + guess.charAt(i);
                }
                
                // Resetting chosenWord array back to normal for next go around
                for (int j = 0; j < 5; j++) {
                    chosenWord[i] = correctWord.charAt(i);
                }
            } 
            
            // Checking if the word is the correct word
            if (guess.equals(correctWord)) {
                correctGuess = true;
            }
            
            // Reset guess, and increase attempts
            guess = "";
            attempts++;
            
        }
        
        // Print the board one final time
        for (int i = 0; i < 30; i++) {
            System.out.print(board[i] + " ");
                
            if ((i + 1) % 5 == 0) {
                println("");
            }
        }
        
        // Win/Lose messages for the current wordle game
        if (correctGuess) {
            
            if (playerNum == 1) {
                
                // Update wins
                player1Wins++;
                
                println("");
                println("Congrats on Winning this Wordle Game!");
                println(playerName + " has won " + player1Wins + " game(s) so far.");
            } else {
                
                // Update Wins
                player2Wins++;
                
                println("");
                println("Congrats on Winning this Wordle Game!");
                println(playerName + " has won " + player2Wins + " game(s) so far.");
            }
            
        } else {
            println("");
            println("The Word Was: " + correctWord);
            println("Tough luck with this game " + playerName + ". Have a better run next time.");
            if (playerNum == 1) {
                println(playerName + " has won " + player1Wins + " game(s) so far.");
            } else {
                println(playerName + " has won " + player2Wins + " game(s) so far.");
            }
        }
        
        println("");
        println("");
        println("ONWARD");
        println("");
        println("");
        println("");
        
        // Update game number
        gameNumber++;
    
    }
    
    // simple method to print faster
    public static void println(String str) {
        System.out.println(str);
    }
}
