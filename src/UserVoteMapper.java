import java.util.*;

public class UserVoteMapper{
  private HashMap<Integer, BoundedSet<Integer>> userToPlayer;
  private HashMap<Integer, Integer> playerVoteCount;
  private int numberOfVotePerUser;
  private int numberOfPlayer;

  public UserVoteMapper(int votePerUser, int noOfPlayer){
    this.userToPlayer = new HashMap();
    this.playerVoteCount = new HashMap();
    this.numberOfVotePerUser = votePerUser;

    this.numberOfPlayer = noOfPlayer;
    for (int i = 0; i < noOfPlayer; i++){
      playerVoteCount.compute(i, (k,v) -> 0);
    }
  }

  public void AddVote(Integer userId, Integer playerId){
    if (this.userToPlayer.containsKey(userId)){
        //Update previous entry for the same user
        //Only takes into account most recent votes and decrement user's oldest vote by one
        BoundedSet<Integer> userVote = this.userToPlayer.get(userId);
        if (userVote.isFull()){
          this.playerVoteCount.compute(userVote.Get(), (k,v) -> v - 1);
        }
        userVote.Add(playerId);
    }
    else {
        //Add new user entry to user hasmap
        BoundedSet<Integer> userVote = new BoundedSet(this.numberOfVotePerUser);
        userVote.Add(playerId);
        this.userToPlayer.put(userId, userVote);
    }
    //Update vote count
    playerVoteCount.compute(playerId, (k,v) -> v != null ? v + 1 : 1);
  }

  public String ShowResult(){
    return playerVoteCount.toString();
  }

  public String ShowPlayerVotes(){
    return userToPlayer.toString();
  }

  public String ShowPlayerVote(int userId){
    if (this.userToPlayer.containsKey(userId)){
      return "userId " + userId + " voted for players " + this.userToPlayer.get(userId).toString();
    }
    else return "userId not found";
  }

  public int GetUserVotesCount(){
    int r = 0;
    for (Map.Entry<Integer, BoundedSet<Integer>> entry : userToPlayer.entrySet()){
        r += entry.getValue().Size();
    }
    return r;
  }

  public int TotalVoteCount(){
    int r = 0;
    for (Map.Entry<Integer, Integer> entry : playerVoteCount.entrySet()){
        r += entry.getValue();
    }
    return r;
  }
}
