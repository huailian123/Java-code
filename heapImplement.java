public class MinHeap <E>｛
  private E[] array;
  private size;
  
  
  public int size() {
  }
  public int isEmpty() {
  }
  public MinHeap(int initSize) {
    array = new E[initSize];
    size = 0;
  }
  
  public offer(E elem){
    if (size == array.length) {
      resize();
    } else {
      int curIdx = size;
      array[curIdx] = elem;
      while (curIdx > 0) {
        int paIdx = (size - 1) / 2;
        if (array[curIdx] < array[paIdx]) {  // swap it to it's parent
          int temp = array[curIdx];
          array[curIdx] = array[paIdx];
          array[paIdx] = temp;
        } else {
          break;
        }
      } // end of while
      size++; 
    }//end of else
  }
  
  public E peek(){
    if (size == 0) {
      return null;
    } else {
      return array[0];
    }
  }
  
  public E poll(){
    if (size == 0) {
      return null;
    } else { 
      int res = array[0];  //first, store the output;
      array[0] = array[size - 1];
      int curIdx = 0;
      while (curIdx < size - 1) {
        int lChildIdx = 2 * curIdx + 1;
        int rChildIdx = 2 * curIdx + 2;
        int min = Math.min(array[lChildIdx], array[lChildIdx]);
        if (array[curIdx] > min) {
          int minIdx = array[lChildIdx] < array[lChildIdx] ? lChildIdx : rChildIdx;
          array[minIdx] = array[curIdx];
          array[curIdx] = min;
        } else {
          break;
        }
      }
      size--;
      return res;
    }
  }
  
  private resize(); 
  
｝// end of class
