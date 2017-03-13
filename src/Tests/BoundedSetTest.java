import static org.junit.Assert.*;
import org.junit.Test;

public class BoundedSetTest{
  @Test
  public void TestBound(){
    int bound = 3;
    BoundedSet<Integer> mySet = new BoundedSet(bound);
    mySet.Add(1);
    mySet.Add(2);
    mySet.Add(3);
    mySet.Add(4);
    mySet.Add(5);
    assertEquals("[4, 5, 3]", mySet.toString());
  }

  @Test
  public void TestBound2(){
    int bound = 1;
    BoundedSet<Integer> mySet = new BoundedSet(bound);
    mySet.Add(1);
    mySet.Add(4);
    mySet.Add(5);
    assertEquals("[5]", mySet.toString());
  }

  @Test
  public void TestBound3(){
    int bound = 0;
    BoundedSet<Integer> mySet = new BoundedSet(bound);
    mySet.Add(1);
    mySet.Add(2);
    assertEquals("[]", mySet.toString());
  }

  @Test
  public void TestGet(){
    int bound = 3;
    BoundedSet<Integer> mySet = new BoundedSet(bound);
    mySet.Add(1);
    mySet.Add(2);
    mySet.Add(3);
    mySet.Add(4);
    mySet.Add(5);
    assertEquals(Long.valueOf(3L), mySet.Get());
  }

  public void TestGet2(){
    int bound = 3;
    BoundedSet<String> mySet = new BoundedSet(bound);
    mySet.Add("Hello");
    mySet.Add("World");
    mySet.Add("!");
    assertEquals("Hello", mySet.Get());
  }
}
