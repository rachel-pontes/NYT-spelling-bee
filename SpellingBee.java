import java.util.*;
import java.io.*;

public class SpellingBee {
  //declares variables
  Scanner sc;
  int points, gameMode, possiblePoints;
  String letters, word, name;
  String center, l2, l3, l4, l5, l6, l7;
  String[][] board;
  ArrayList<String> guessed;
  List<String> dictionari, possibleWords;
  boolean won;
  ArrayList<String> possibleGames;

  public SpellingBee(List<String> p)
  {
    points = 0;
    dictionari = p;
    won = false;
    sc = new Scanner(System.in);
    guessed = new ArrayList<String>();
    possibleGames = new ArrayList<String>();
    possibleWords = new ArrayList<String>();
    board = new String[5][5];
    for(int i=0; i<5; i++){
      for(int j=0; j<5; j++){
        board[i][j] = "   ";
      }
    }
    //creates array of possible games
    String[] arr = {"GADNRUV", "IBCOPRT", "MABELOZ", "ACHNOVY", "KACDELO", "EBGIKNP", "BCEILOT", "MAGIJNU", "VAEILNT", "GABDLUY"};
    for (int i=0; i<10; i++)
    {
      possibleGames.add(arr[i]);
    }
  } 
       
   public void intro() 
  {
    // asks for name
    System.out.println("Hello. What is your name?");
    name = sc.next();
    System.out.println("Hi " + name + "!");
    play();
    
  }
  
  public void play()
  {
    System.out.println("*****WELCOME TO THE SPELLING BEE GAME*****"); System.out.println("\u001b[33;1m⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠶⠦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡤⠶⠦⢤⣀⡞⠁⠀⠀⠀⠈⠻⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠃⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⠀⠀⢹⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡇⠀⠀⠀⠀⠀⢸⡀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢷⡀⠀⠀⠀⠀⠀⢧⠀⠀⠀⠀⠀⠀⢸⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣄⠀⠀⠀⠀⠈⢣⡀⠱⠀⠀⢀⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠲⠤⢀⣉⣢⣹⣦⣶⣶⣾⡷⠶⠶⠦⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⣿⣿⠁⠀⣾⣿⡇⠀⠀⠀⠀⠙⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠋⢰⣿⡏⠀⠀⣿⣿⡇⠀⠀⠀⠀⠀⠀⢻⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣧⠀⢸⣿⡇⠀⠀⣿⣿⣧⠀⢠⣇⢸⡆⠀⠘⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣿⣿⠀⢸⣿⣧⠀⠀⢻⣿⣿⡄⠀⠁⠀⠁⠀⠀⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠶⠿⠿⠿⣿⣿⠀⠸⣿⣿⡄⠀⠈⣿⣿⣷⡀⠀⠀⠀⠀⢸⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣧⠀⢻⣿⣷⡀⠀⠈⢿⣿⣷⣄⠀⢀⣰⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠶⣤⣙⣿⣷⣄⣀⣀⣻⣿⡿⠾⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\u001b[0m");
    System.out.println("Would you like to select the seven letters for the Spelling Bee Game or would you like to play a random Spelling Bee game?");
    System.out.println("Enter 0 to select your own letters and 1 to play a random game.");
    gameMode = validateInput(0,1);
    // assigns letters to board variables if user chooses to input letters
    if(gameMode==0)
    {
      System.out.println("Enter 7 letters that have a panagram.");
      word = sc.next();
      word = validateLetters(word);
      word = word.toLowerCase();
      center = word.substring(0, 1);
      l2 = word.substring(1, 2);
      l3 = word.substring(2, 3);
      l4 = word.substring(3, 4);
      l5 = word.substring(4, 5);
      l6 = word.substring(5, 6);
      l7 = word.substring(6, 7);
    }
    // assigns letters to board variables if user chooses to play a random game
    else if (gameMode==1)
    {
      word = possibleGames.get((int)(Math.random()*9)+1);
      word = word.toLowerCase();
      center = word.substring(0, 1);
      l2 = word.substring(1, 2);
      l3 = word.substring(2, 3);
      l4 = word.substring(3, 4);
      l5 = word.substring(4, 5);
      l6 = word.substring(5, 6);
      l7 = word.substring(6, 7);
    }
    //adds all possible answers to possibleWords array
    addPossibleWords();

    instructions();

   while (!won)
     {
       //prints output for each round
       String tempRank = rank();
       System.out.println();
      System.out.println("\u001B[36;1mRank: \u001b[0m" + tempRank + ", \u001B[36;1mPoints: \u001b[0m" + points);
      printBoard();
      System.out.println("Type out any word using letters above. You must use the center letter.");
      System.out.println("Type !info for detailed rules.");
      System.out.println("Type !list for the list of words you've found already.");
      System.out.println("Type !shuffle to shuffle the letters displayed.");
      System.out.println("Type !rank to display all ranks.");
      System.out.println("Type !quit to quit game");
      System.out.println();
      String next = sc.next();
      String tempz = validateWordInput(next);
      if (tempz.equals("quit"))
        break;
   }
    
  }

  //adds all words that are valid for the board letters to possibleWords array
  //adds all possible points up
  public void addPossibleWords()
  {
    for (int i=0; i<dictionari.size(); i++)
    {
      String temp = dictionari.get(i);
      if (validWord(temp))
      {
        possibleWords.add(temp);
        if (temp.length()==4)
          possiblePoints++;
        else
          possiblePoints+=temp.length();
        if (isPanagram(temp))
          possiblePoints+=7;
      }
    }
  }

  
  // checks if user wants to play again and resets variables
  public void playAgain()
  {
    System.out.println(name + ", would you like to play again?");
    System.out.println("Type 0 to play again.\nType 1 to quit.");
    int temp = validateInput(0,1);
    if (temp==0)
    {
      points = 0;
      won = false;
      while (guessed.size()>0)
        {
          guessed.remove(0);
        }
      while (possibleWords.size()>0)
        {
          possibleWords.remove(0);
        }
            for(int i=0; i<5; i++){
      for(int j=0; j<5; j++){
        board[i][j] = "   ";
      }
    }
      play();
    }
    else if (temp==1)
      return;
  }

  //returns ranks based on percentage of words possible
  public String rank ()
  {
    double numWords = possibleWords.size();
    if (points < numWords * 0.02)
      return "Beginner";
    else if (points < numWords * 0.05)
      return "Good Start";
    else if (points < numWords * 0.08)
      return "Moving Up";
    else if (points < numWords * 0.15)
      return "Good";
    else if (points < numWords * 0.25)
      return "Solid";
    else if (points < numWords * 0.40)
      return "Nice";
    else if (points < numWords * 0.50)
     return "Great";
    else if (points < numWords * 0.70)
      return "Amazing";
    else if (points < numWords)
      return "Genius";
    else 
      return "Queen Bee";
  }  

  // checks if word from dictionary is valid with given letters
  public boolean validWord(String t)
  {
    if (!t.contains(center))
      return false;
    for (int j=0; j<t.length(); j++)
      {
       if (!word.contains(t.substring(j,j+1)))
        return false;
      }
    return true;
  }

  //prints instructions of game
  public void instructions()
  {
    System.out.println("\nHOW TO PLAY:\nWords must contain at least 4 letters.\nWords must include the center letter. \nOur word list does not included words that are obscure, hyphenated, or proper nouns.\nLetters can be used more than once.\n");
    System.out.println("\nSCORING RULES:\n4-letter words are worth 1 point each.\nLonger words earn 1 point per letter.\nEach puzzle includes at least one \"panagram\" which uses every letter. These are worth 7 extra points!\n\n");
  }

  //checks if the word is a panagram
  public boolean isPanagram(String w)
  {
    {
      if (w.length()>=7)
        {
          String[] alpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
          int count = 0;
          for (int j=0; j<w.length(); j++)
            {
              for (int a=0; a<alpha.length; a++)
                {
                  if (alpha[a].equals(w.substring(j, j+1)))
                  {
                    alpha[a] = " ";
                    count++;
                  }
                }
            }
          if (count==7)
          {
            return true;
          }
        }
    }
    return false;
  }

  //prints the board with letters
  public void printBoard()
  {
    board[2][2] = ConsoleColors.YELLOW_BACKGROUND + " " + center.toUpperCase() + " " + 
ConsoleColors.RESET;
    board[0][2] = ConsoleColors.GREEN_BACKGROUND + " " + l2.toUpperCase() + " " + 
ConsoleColors.RESET;
    board[1][1] = ConsoleColors.GREEN_BACKGROUND + " " + l3.toUpperCase() + " " + 
ConsoleColors.RESET;
    board[1][3] = ConsoleColors.GREEN_BACKGROUND + " " + l4.toUpperCase() + " " + 
ConsoleColors.RESET;
    board[3][1] = ConsoleColors.GREEN_BACKGROUND + " " + l5.toUpperCase() + " " +
ConsoleColors.RESET;
    board[3][3] = ConsoleColors.GREEN_BACKGROUND + " " + l6.toUpperCase() + " " + 
ConsoleColors.RESET;
    board[4][2] = ConsoleColors.GREEN_BACKGROUND + " " + l7.toUpperCase() + " " + 
ConsoleColors.RESET;
    System.out.println("************************");
    for(int r=0; r<5; r++)
      {
        for(int c=0; c<5; c++)
          {
            System.out.print(board[r][c] + "  ");
          }
        System.out.println();
      }
    System.out.println("************************");
  }

  // shuffles surrounding letters of game
  public void shuffleBoard()
  {
      String str=word.substring(1);
  		List<String> characters = Arrays.asList(str.split(""));
 		  Collections.shuffle(characters);
  		word = word.substring(0,1);
  		for (String character : characters)
      {
    			word += character;
  		} 
      l2 = word.substring(1, 2);
      l3 = word.substring(2, 3);
      l4 = word.substring(3, 4);
      l5 = word.substring(4, 5);
      l6 = word.substring(5, 6);
      l7 = word.substring(6, 7);
  }

  //adds points accordingly for each valid word
  public void addPoints(String s)
  { 
    if (s.length()==4)
    {
      points+=1;
    }
    else if (s.length()>4)
      points+=s.length();
    if (isPanagram(s))
    {
      points+=7;
    }

    if (s.length()==4)
      System.out.println("Good!");
    else if (s.length()==5||s.length()==6)
      System.out.println("Nice!");
    else if (isPanagram(s))
      System.out.println("You got a panagram!");
    else if (s.length()>=7)
      System.out.println("Awesome!");
  }

  //validates integer input 
  public int validateInput(int min, int max)
	{
		boolean doLoop = true;
		while(doLoop)
		{
			try {
        int filler = Integer.parseInt(sc.next());
				if(filler > max || filler < min)
				{
					System.out.print("Invalid input. Enter an integer between " + min + " and " + max + ": ");

				}
				else
				{
					doLoop = false;
					return filler;
				}
			}
      catch(Exception e) {

        System.out.print("Invalid input. Enter an integer between " + min + " and " + max + ": ");
      }
		}
		return -1;
	}

  //checks if user inputted letters form a panagram 
  public boolean checkLetter(String s)
  {
    word = s;
    center = s.substring(0,1);
    addPossibleWords();
    if (possibleWords.size()>0)
    {
      while (possibleWords.size()>0)
        {
          possibleWords.remove(0);
        }
      for (int i=0; i<dictionari.size(); i++)
      {
        String temp = dictionari.get(i);
        if (isPanagram(temp))
        {
          if (temp.contains(s.substring(0,1))&& temp.contains(s.substring(1,2))&&temp.contains(s.substring(2,3))&& temp.contains(s.substring(3,4))&& temp.contains(s.substring(4,5))&& temp.contains(s.substring(5,6)))
            return true;
        }
      }
    }
    while (possibleWords.size()>0)
        {
          possibleWords.remove(0);
        }
    return false;
  }

  // validates user inputted letters
  public String validateLetters(String s)
  {
    String temp = s;
    boolean doLoop = true;
    while(doLoop)
      {
        try {
          temp = temp.toLowerCase();
          if (temp.length()!=7)
          {
            System.out.println("Please enter seven letters.");
            temp = sc.next();
          }
          else if(!checkLetter(temp))
          {
            System.out.println("The letters you entered do not have a panagram. Please enter 7 letters with a panagram.");
            temp = sc.next();      
          }
          else {
            doLoop=false;
            return temp;
          }
        }
        catch(Exception e) 
        {
          System.out.println("Your guess is not valid. Please enter another guess.");
        }
      }
    return "fail";
  }

  //validates word guesses and executes commands for exclamation commmands
  public String validateWordInput(String s)
	  {
      String temp = s;
      boolean doLoop = true;
      while(doLoop)
      {
        try {
          temp = temp.toLowerCase();
          if (temp.equals("!info"))
          {
            instructions();
            return temp;
          }
          else if (temp.equals("!list"))
          {
            for (int i=0; i<guessed.size(); i++)
              {
                System.out.println(guessed.get(0));
              }
            return temp;
          }
          else if (temp.equals("!shuffle"))
          {
            shuffleBoard();
            return temp;
          }
          else if (temp.equals("!rank"))
          {
            System.out.println("Rankings:");
            System.out.println("Ranks are based on a percentage of possible points in a puzzle.\nThe minimum scores to reach each rank are:");
            System.out.println("Beginner (0)\nGood Start (" + (int)(possibleWords.size()*.02) + ")\nMoving Up (" + (int)(possibleWords.size()*.05) + ")\nGood (" + (int)(possibleWords.size()*.08) + ")\nSolid (" + (int)(possibleWords.size()*.15) + ")\nNice (" + (int)(possibleWords.size()*.25) + ")\nGreat (" + (int)(possibleWords.size()*.40) + ")\nAmazing (" + (int)(possibleWords.size()*.50) + ")\nGenius (" + (int)(possibleWords.size()*.70) + ")");
            return temp;
          }
          else if (temp.equals("!quit"))
          {
            String tempRank = rank();
            System.out.println("Rank: " + tempRank + ", Points: " + points);
            System.out.println("Would you like to see the solution for the Spelling Bee game you just played?");
            System.out.println("Type 0 to see the word list. \nType 1 to pass.");
            int tempz = validateInput(0,1);
            if (tempz==0)
            {
              System.out.println("Total possible points: " + possiblePoints);
              System.out.println("Total possible words: " + possibleWords.size());
              System.out.println("* found words\n");
              for (int i=0; i<possibleWords.size(); i++)
                {
                  if (guessed.contains(possibleWords.get(i)) && isPanagram(possibleWords.get(i)))
                    System.out.println("* " + possibleWords.get(i)+ "   PANAGRAM");
                  else if (guessed.contains(possibleWords.get(i)))
                    System.out.println("* " + possibleWords.get(i));
                  else if (isPanagram(possibleWords.get(i)))
                    System.out.println(possibleWords.get(i)+ "   PANAGRAM");
                  else
                    System.out.println(possibleWords.get(i));
                }
            }
            playAgain();
            return "quit";
          }
          else if(temp.length()<=3)
          {
            System.out.println("Too short. Please enter another guess.");
            temp = sc.next();
          }
          else if (guessed.contains(temp))
            {
            System.out.println("You already guessed this word. Please enter another guess.");
              temp = sc.next();
            }
          else if(!temp.contains(center))
          {
            System.out.println("Missing center letter. Please enter another guess.");
            temp = sc.next();
          }
            else if(!validWord(temp))
          {
            System.out.println("Please only use letters displayed in game. Enter another guess.");
            temp = sc.next();
          }
          else if(!possibleWords.contains(temp))
          {
            System.out.println("Not in the word list. Please enter another guess.");
            temp = sc.next();
          }
          else
          {
            doLoop = false;
            guessed.add(temp);
            addPoints(temp);
            return temp;
          }
        }
        catch(Exception e) {
          System.out.println("Your guess is not valid. Please enter another guess.");
        }
      }
      return "fail";
	  }
 
}