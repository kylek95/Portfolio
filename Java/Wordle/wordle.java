import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.HashMap;
import java.util.Arrays;
public class wordle{
    private static String[] word_bank;

    public wordle(){
        try{
            Path path = Paths.get("words.txt");
            word_bank = new String(Files.readAllBytes(path)).split(",");
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public void start(){
        Random random  = new Random();
        String[] answerLetters = word_bank[random.nextInt(word_bank.length)].split("");
        String[] guessedWords = new String[6];
        String[] colorArray = new String[6];
        int guesses = 0;
        Scanner scanner  = new Scanner(System.in);
        while(true){
            //Hashes the answer letters for guess checking
            HashMap<String,Integer> answerHash = new HashMap<>();
            for(int i = 0; i < 5;i++){
                if(answerHash.get(answerLetters[i]) == null){
                    answerHash.put(answerLetters[i],1);
                } else {
                    answerHash.put(answerLetters[i], answerHash.get(answerLetters[i]+1));
                }
            }

            //Prints out the format of the game
            gameFormat(guesses, guessedWords, colorArray);

            //Takes in the user's guess
            System.out.print("Your Guess: ");
            String guess = scanner.next();

            //checks to see if it is a valid input
            boolean valid = false;
            for(int i = 0; i < word_bank.length;i++){
                if(word_bank[i].equals(guess)){
                    valid = true;
                }
            }

            //checks the guess to see how accurate the guess is if the input is valid
            if(valid){
                String[] guessLetters = guess.split("");
                guessedWords[guesses] = guess;
                String color = "01234";

                for(int i = 0; i < 5; i++){
                    if(guessLetters[i].equals(answerLetters[i])){
                        color = color.replace(String.valueOf(i),"g");
                        answerHash.put(guessLetters[i], answerHash.get(guessLetters[i])-1);
                    }
                }
                for(int i = 0; i < 5; i++){
                    if(!guessLetters[i].equals(answerLetters[i])){
                        for(int a = 0; a < 5; a++){
                            if(guessLetters[i].equals(answerLetters[a])){
                                if(answerHash.get(guessLetters[i]) <=0){
                                    color = color.replace(String.valueOf(i),"b");
                                    break;
                                } else {
                                    color = color.replace(String.valueOf(i),"y");
                                    answerHash.put(guessLetters[i], answerHash.get(guessLetters[i])-1);
                                    break;
                                }
                            }
                        }
                    }
                }
                for(int i = 0; i < 5; i++){
                    color = color.replace(String.valueOf(i),"b");
                }
                colorArray[guesses] = color;
                guesses++;
                //checks to see if fully correct
                if(color.equals("ggggg")){
                    System.out.println("YOU WIN! CONGRATULATIONS!");
                    break;
                } else if(guesses == 6){
                    gameFormat(guesses, guessedWords, colorArray);
                    System.out.println("YOU LOSE! THE WORD WAS: "+
                        String.join("",answerLetters));
                    break;
                } else {
                    System.out.println("Guesses Left: " + String.valueOf(6-guesses));
                }
            }
            //Asks user to guess again with a valid word
            else {
                System.out.println("Word must be a real 5-letter word!");
                continue;
            }
        }
        scanner.close();
    }

    //Formats the game
    public void gameFormat(int guesses, String[] guessedWords, String[] colorArray){
        System.out.println("             Wordle             ");
        System.out.println("g = green| y = yellow| b = black");
        System.out.println("================================");
        System.out.println("    Words      |      Colors    ");
        for(int i = 0; i < 6;i++){
            if(colorArray[i] == null){
                System.out.println("    _____      |      _____     ");
            } else {
                System.out.println("    "+guessedWords[i]+"      |      "+colorArray[i]+"     ");
            }
        }
    }

    //Main function to start the game on file compile and run
    public static void main(String[] args){
        wordle game = new wordle();
        game.start();
    }
}

