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
public class LianMap <K, V> {

  static class Node <K, V> {
    K key;
    V value;
    Node<K, V> next;
    public Node (K key, V value) {
      this.key = key;
      this.value = value;
    }  
    void update(V value) {
      this.value = value;
    }
  }
  
  Node<K,V>[] buckets; 
  private static final int INITSIZE = 16;
  private static final float LOAD_FACTOR = (float) 0.75;
  private static final int RESIZE = 2;
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
    
    int idx = hashToBuct(key.hashCode(), buckets.length);
    
    Node<K, V> res = findKey(buckets[idx], key);
    if (res == null) {
      Node<K, V> head = buckets[idx];
      Node<K, V> newHead = new Node<>(key, value);
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
    int idx = hashToBuct(key.hashCode(), buckets.length);   
    return findKey(buckets[idx], key) != null;   
  }
  
  
  // Time : o(n)
  public boolean containsValue (V value) {   
    for (int i = 0; i < buckets.length; i++) {
      if (buckets[i] != null) {
        Node<K, V> head = buckets[i];
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
    int idx = hashToBuct(key.hashCode(), buckets.length);
    Node<K, V> tmp = findKey(buckets[idx], key);
    return tmp == null ? null : tmp.value;
  }
  
  private Node<K, V> findKey(Node<K, V> cur, K key) {
    Node<K, V> head = cur;
    while (head != null) {
      if (head.key.equals(key)) {
        return head;
      }
      head = head.next;
    }
    return null;   
  }
  
  public V remove (K key) {
    int idx = hashToBuct(key.hashCode(), buckets.length);
    Node<K, V> head = buckets[idx];
    if (head == null) {
      return null;
    }
    if (head.key.equals(key)) {
      buckets[idx] = head.next;
      return head.value;
    }
    while (head.next != null) {
      if (head.next.key.equals(key)) {
        head.next = head.next.next;
        return head.next.value;
      }
    }//end of while
	return null;
  }
  
  public boolean isEmpty () {
    return curSize == 0;
  }
  
  public int size() {
    return this.curSize;
  }
  
  public int bucketSize() {
	    return buckets.length;
  }
  
  
  private int hashToBuct(long hashcode, int range) {
    return (int) (Math.abs(hashcode) % range);
  }
  
  private void rehash() {
    Node<K, V>[] newBuckets = new Node[buckets.length * RESIZE];
    for (int i = 0; i < buckets.length; i++) {
        if (buckets[i] != null) {
          Node<K, V> cur = buckets[i];
          while (cur != null) {
            int idx = hashToBuct(cur.key.hashCode(), newBuckets.length);
            Node<K, V> newHead = new Node<>(cur.key, cur.value);
            newHead.next = newBuckets[idx];           
            newBuckets[idx] = newHead;        
          }         
        }//end of if   
    }// end of for
  }   
  
}
