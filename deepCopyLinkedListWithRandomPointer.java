//Data Structure
//Deep Copy Linked List With Random Pointer
//Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. Make a deep copy of the original list.


/**
 * class RandomListNode {
 *   public int value;
 *   public RandomListNode next;
 *   public RandomListNode random;
 *   public RandomListNode(int value) {
 *     this.value = value;
 *   }
 * }
 */
 // tested 3.19.2017
 
public class Solution {
  public RandomListNode copy(RandomListNode head) {
    if (head == null) {
      return null;
    }
    RandomListNode newHead = new RandomListNode(head.value);
    RandomListNode cur = newHead;
    Map<RandomListNode, RandomListNode> map = new HashMap<>();
    map.put(head, newHead);
    while (head.next != null) {
      if (!map.containsKey(head.next)) { //creat cur.next
        RandomListNode tmp = new RandomListNode(head.next.value);
        cur.next = tmp;
        map.put(head.next, tmp);
      } else {
        cur.next = map.get(head.next);
      }
      if (head.random != null && !map.containsKey(head.random)) { //creat cur.random
        RandomListNode tmp = new RandomListNode(head.random.value);
        cur.random = tmp;
        map.put(head.random, tmp);
      } else {
        cur.random = map.get(head.random);
      }
      cur = cur.next;
      head = head.next;
    }
    if (head.random != null) {
      cur.random = map.get(head.random);
    }
    return newHead;
  }
}
