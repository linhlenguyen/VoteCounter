public class User{
  private int userIdentifier;
  private int userId;

  private static int userIdCount = 0;

  public User(int identifier){
    this.userId = userIdCount;
    userIdCount++;
    this.userIdentifier = identifier;
  }

  public String toString(){
    return "userId: " + this.userId + " identifier: " + this.userIdentifier;
  }
}
