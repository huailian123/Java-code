  /* list of methods
  public LianMap() {} // constructor
  public V put(K key, V value) {}
  public boolean containsKey (K key) {}
  public boolean containsValue (V value) {}
  public V get(K key) {}
  public boolean isEmpty () {}
  public int size() {}
  
  private long hashcode(){}
  private int hashToBuct(long hashcode, int range) {}
  private equals() {}
  private resize() {}
  private rehash() {}
  */
  
public class LianMAP <K, V> {

  static class Node <K, V> {
    K key;
    V value;
    Node<K, V> next;
    public Node (K key, V value) {
      this.value = value;
      this.value = value;
    }  
    void updata(V value) {
      this.value = value;
    }
  }
  
  Node[] buckets; 
  int initSize;
  int curSize;
  
  public LianMap(int initSize) {
    buckets = new Node[initSize];  // What is a good length to initialize ?
    this.initSize = initSize;
  }
 
  
  public V put(K key, V value) {
    if (curSize / initSize >= 0.75) {
      resize();
      rehash();
    }
    int idx = hashToBuct(key.hashcode(), initSize);
    if (buckets[idx] == null) {
      buckets[idx] = new Node(key, value);
    } else {
      Node head = buckets[idx];
      Node newHead = new Node(key, value);
      newHead.next = head;
      buckets[idx] = newHead;
    }
    curSize++;    
  }
  
  public boolean containsKey (K key) {
    int idx = hashToBuct(key.hashcode(), initSize);
    if (bucket[idx] == null) {
      return false;
    } else {
      Node head = bucket[idx];
      while (head != null) {
        if (head.key == key) {
        return true;
        } else {
          head = head.next;
        }
      } // end of while
      return false;
    }
  }
  
  
  // Time : o(n)
  public boolean containsValue (V value) {   
    for (int i = 0; i < curSize; i++) {
      if (bucket[idx] != null) {
        Node head = bucket[idx];
        while (head != null) {
          if (head.value == value) {
            return true;
          } else {
            head = head.next;
          }
        } // end of while
      }
    }// end of for
    return false;
  }
  
  public V get(K key) { // pretty similar to containsKey
    int idx = hashToBuct(key.hashcode(), initSize);
    if (bucket[idx] == null) {
      return false;
    } else {
      Node head = bucket[idx];
      while (head != null) {
        if (head.key == key) {
        return head.value;
        } else {
          head = head.next;
        }
      } // end of while
      return null;
    }
  }
  public boolean isEmpty () {
    return curSize == 0;
  }
  
  public int size() {
    return this.curSize;
  }
  
  private long hashcode(){}
  private int hashToBuct(long hashcode, int range) {}
  private equals() {}
  private resize() {}
  private rehash() {}
   
  
}
