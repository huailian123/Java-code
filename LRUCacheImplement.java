//Data Structure
//Implement LRU Cache
//Implement a least recently used cache. It should provide set(), get() operations. If not exists, return null (Java), false (C++).


//tested 
public class Solution<K, V> {
  
  private final int limit;
  private Node<K, V> head;
  private Node<K, V> tail; 
  private Map<K, Node<K, V>> map;
  
  public Solution(int limit) {
    map = new HashMap<>();
    this.limit = limit;
  }
  
  public void set(K key, V value) {
    Node<K, V> cur = map.get(key);
    if (cur != null) {  // already in cache
      cur.value = value;
      remove(cur);
      append(cur);
    } else {    // not in cache
      if (map.size() == limit) {  // full
        remove(tail);
      }
      append(new Node<K, V> (key, value));
    }
      
  }
  
  public V get(K key) {
    Node<K, V> node = map.get(key);
    if (node != null) {
      remove(node);
      append(node);
      return node.value;
    }
    return null;
  }
  
  private Node<K, V> append(Node<K, V> node) {
    map.put(node.key, node);
    if (head == null) {
      head = node;
      tail = node;
    } else {
      node.next = head;
      head.prev = node;
      head = node;
    }
    return node;
  } 
  
  private Node<K, V> remove(Node<K, V> node) {
    map.remove(node.key);
    if (node.prev != null) {
      node.prev.next = node.next;
    }
    if (node.next != null) {
      node.next.prev = node.prev;
    }
    if (node == head) {
      head = node.next;
    }
    if (node == tail) {
      tail = node.prev;
    }
    return node;
  }
  
  static class Node <K, V> {
    K key;
    V value;
    Node <K, V> next;
    Node <K, V> prev;
    
    public Node (K key, V value) {
      this.key = key;
      this.value = value;
    }
    
    void update(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
  
}
