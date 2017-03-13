import static org.junit.Assert.*;
import org.junit.Test;

public class BoundedSetTest{
  @Test
  public void TestCount(){
    UserVoteMapper dataStore = new UserVoteMapper(3, 5);
    dataStore.AddVote(11312,0);
    dataStore.AddVote(1522,1);
    dataStore.AddVote(32123,3);
    assertEquals(3, dataStore.TotalVoteCount());
    assertEquals(3, dataStore.GetUserVotesCount());
  }

  @Test
  public void TestCount2(){
    UserVoteMapper dataStore = new UserVoteMapper(1, 5);
    dataStore.AddVote(1312,0);
    dataStore.AddVote(522,1);
    dataStore.AddVote(2123,3);
    dataStore.AddVote(2123,0);
    dataStore.AddVote(2123,4);
    dataStore.AddVote(2123,4);
    assertEquals(3, dataStore.TotalVoteCount());
    assertEquals(3, dataStore.GetUserVotesCount());
  }

  @Test
  public void TestEntry(){
    UserVoteMapper dataStore = new UserVoteMapper(3, 5);
    dataStore.AddVote(1112,0);
    dataStore.AddVote(1522,5);
    dataStore.AddVote(3123,3);
    assertEquals(3, dataStore.TotalVoteCount());
    assertEquals(2, dataStore.GetUserVotesCount());
  }
}
