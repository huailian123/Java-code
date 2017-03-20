  /* list of methods
  public LianMap() {} // constructor
  public V put(K key, V value) {} 
  public boolean containsKey (K key) {}
  public boolean containsValue (V value) {}
  public V get(K key) {}
  public boolean isEmpty () {}
  public int size() {}
   
  private int hashToBuct(long hashcode, int range) {}
  private rehash() {}  // resize()
  private findKey(buckets[idx], key) {};
  */
 
//hashcode() is Key's hashcode() which is inherited from Object class. Need to override it in Key class;
//equals() is the same;
public class LianMAP <K, V> {

  static class Node <K, V> {
    K key;
    V value;
    Node<K, V> next;
    public Node (K key, V value) {
      this.key = key;
      this.value = value;
    }  
    void updata(V value) {
      this.value = value;
    }
  }
  
  Node[] buckets; 
  private static final int INITSIZE = 16;
  private static final float LOAD_FACTOR = 0.75;
  int curSize;
  
  public LianMap(int inputSize) {
    buckets = new Node[inputSize];  
  }
 
  public LianMap() {
    buckets = new Node[INITSIZE]; 
  }
  
  // return null if the key didn't exist before, or return the previous value, and update it 
  public V put(K key, V value) {
    if ((float) curSize / buckets.length >= LOAD_FACTOR) {
      rehash();
    }
    
    int idx = hashToBuct(key.hashcode(), buckets.length);
    
    Node res = findKey(buckets[idx], key);
    if (res == null) {
      Node head = buckets[idx];
      Node newHead = new Node(key, value);
      newHead.next = head;
      buckets[idx] = newHead;
      curSize++;
      return null;
    } else {      
      V temp = res.value;
      res.update(value);
      return temp;
    } 
  }
  
  public boolean containsKey (K key) {
    int idx = hashToBuct(key.hashcode(), buckets.length);   
    return findKey(buckets[idx], key) != null;   
  }
  
  
  // Time : o(n)
  public boolean containsValue (V value) {   
    for (int i = 0; i < buckets.length; i++) {
      if (bucket[i] != null) {
        Node head = bucket[i];
        while (head != null) {
          if (head.value.equals(value)) {
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
    int idx = hashToBuct(key.hashcode(), buckets.length);
    Node tmp = findKey(buckets[idx], key);
    return tmp == null ? null : tmp.value;
  }
  
  private Node findKey(buckets[idx], key) {
    Node head = bucket[idx];
    while (head != null) {
      if (head.key.equals(key)) {
        return head;
      }
      head = head.next;
    }
    return null;   
  }
  
  public V remove (K key) {
    int idx = hashToBuct(key.hashcode(), buckets.length);
    Node head = bucket[idx];
    if (head == null) {
      return null;
    }
    if (head.key.equals(key)) {
      bucket[idx] = head.next;
      return head.value;
    }
    while (head.next != null) {
      if (head.next.key.equals(key)) {
        head.next = head.next.next;
        return head.next.value;
      }
    }//end of while
  }
  
  public boolean isEmpty () {
    return curSize == 0;
  }
  
  public int size() {
    return this.curSize;
  }

  
  
  private int hashToBuct(long hashcode, int range) {
    return hashcode % range;
  }
  private rehash() {}
   
  
}
