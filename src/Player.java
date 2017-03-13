public class Player{
  private String playerName;
  private int playerId;

  private static int playerIdCount = 0;

  pubic Player(String name){
    this.playerId = playerIdCount;
    playerIdCount++;
    this.playerName = name;
  }

  public String toString(){
    return "playerId " + this.playerId + "name :" + this.playerName;
  }
}
