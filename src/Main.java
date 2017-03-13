import java.io.*;
import java.util.*;

public class Main {
  private static int numberOfVotes;
  private static int numberOfUsers;
  private static int numberOfPlayer;
  private static int numberOfVotePerUser;
  private static UserVoteMapper dataStore;

  public static void main(String[] args){
      Console c = System.console();
      if (c == null) {
         System.err.println("No console.");
         System.exit(1);
      }

      initialiseData();
      Scanner readIn = new Scanner(System.in);

      while(true){
        String str;
        str = readIn.nextLine();
        processCommand(str);
      }
  }

  private static void showHelp(){
    System.out.println("Enter 's' to see result, 'p' to see all user votes, 'q' to quit and 'r' to re-initilise data");
    System.out.println("Enter userId to see which player the user has voted for");
    System.out.println("Enter userId followed by playerId the user voted for");
    System.out.println("E.g 12 8 8 9 ~ userId 12 voted for playerId 8 twice and 9 once");
    System.out.println("Number of votes can be however long (Most recent vote will replace the oldest vote)");
    System.out.println("Results are shown in hashMap with playerId followed by the number of votes received");
  }

  private static int tryInput(String message){
    Console c = System.console();
    String in;
    while(true){
      in = c.readLine(message);
      if (tryParseInt(in)){
        return Integer.parseInt(in);
      }
      else System.out.println("Invalid number");
    }
  }

  private static void initialiseData(){
    numberOfVotes = tryInput("Please enter the number of votes\n");
    numberOfUsers = tryInput("Please enter the number of users\n");
    numberOfPlayer = tryInput("Please enter the number of players\n");
    numberOfVotePerUser = tryInput("Please enter the number of votes per user\n");

    dataStore = new UserVoteMapper(numberOfVotePerUser, numberOfPlayer);

    System.out.println("Initialise data..");
    Random rand = new Random();
    for (int i = 0; i < numberOfVotes; i++){
        int user = rand.nextInt(numberOfUsers);
        int vote = rand.nextInt(numberOfPlayer);
        dataStore.AddVote(user, vote);
    }
    System.out.println(dataStore.ShowResult());
    showHelp();
  }

  private static void enterVotes(int[] userVotes){
    int user = userVotes[0];
    for (int i = 1; i < userVotes.length; i++){
      if (userVotes[i] >= numberOfPlayer || userVotes[i] < 0){
        System.out.println("PlayerId out of bound, no votes were added" + userVotes[i]);
        return;
      }
      dataStore.AddVote(user, userVotes[i]);
    }
    System.out.println(dataStore.ShowResult());
  }

  public static void processCommand(String command){
    if (command.equals("r")){
      initialiseData();
    }
    if (command.equals("s")){
      System.out.println(dataStore.ShowResult());
    }
    if (command.equals("p")){
      System.out.println(dataStore.ShowPlayerVotes());
    }
    else if (command.equals("q")){
      System.out.println("Terminating.. Thanks!");
      System.exit(0);
    }
    else if (command.equals("c")){
      System.out.println(dataStore.GetUserVotesCount());
      System.out.println(dataStore.TotalVoteCount());
    }
    else {
      String[] nums = command.split(" ");
      if (nums.length == 1){
        if (tryParseInt(nums[0])){
          System.out.println(dataStore.ShowPlayerVote(Integer.parseInt(nums[0])));
        }
        else System.out.println("Invalid inputs");
      }
      else {
        int[] input = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
          if (tryParseInt(nums[i])){
            input[i] = Integer.parseInt(nums[i]);
          }
          else {
            System.out.println("Invalid input, not an integer");
            break;
          }
        }
        enterVotes(input);
      }
    }
  }

  private static boolean tryParseInt(String value) {
     try {
         int i = Integer.parseInt(value);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
  }
}
