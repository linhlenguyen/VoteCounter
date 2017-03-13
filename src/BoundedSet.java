import java.util.*;
import java.io.*;

public class BoundedSet<T> {
  /*
  A bounded set with limited size that behaves similar to a queue
  Adding to a full set will remove the oldest item in the set and replace it with new item
  It uses a list and a simple head pointer to add/replace element
  This allow us to maintain a list of user to votes which only take into account n most recent votes
  */

  private List<T> dataStorage;
  private int size;
  private int head;

  public BoundedSet(int size){
    this.dataStorage = new ArrayList();
    this.size = size;
    this.head = 0;
  }

  private void incHead(){
    this.head++;
    if (this.head == size){
      this.head = 0;
    }
  }

  public int Size(){
    return this.dataStorage.size();
  }

  public T Get(){
    return this.dataStorage.get(this.head);
  }

  public boolean isFull(){
    return this.dataStorage.size() == this.size;
  }

  public void Add(T item){
    if (this.size == 0)
      return;

    if (this.dataStorage.size() < this.size){
      this.dataStorage.add(item);
    }
    else {
      this.dataStorage.set(this.head,item);
    }
    this.incHead();
  }

  public String toString(){
    String r = "[";
    for (int i = 0; i < this.dataStorage.size(); i++){
      if (i == 0) r += this.dataStorage.get(i).toString();
      else r += ", " + this.dataStorage.get(i).toString();
    }
    r += "]";
    return r;
  }
}
