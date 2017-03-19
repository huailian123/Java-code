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
      buckets[idx] = new Node();
    } 
    
  }
  public boolean containsKey (K key) {}
  public boolean containsValue (V value) {}
  public V get(K key) {}
  public boolean isEmpty () {}
  
  public int size() {
    return this.curSize;
  }
  
  private long hashcode(){}
  private int hashToBuct(long hashcode, int range) {}
  private equals() {}
  private resize() {}
  private rehash() {}
  

  
  
  
  
  
 
  
}
