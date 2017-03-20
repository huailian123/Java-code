public class MinHeap <E>｛
  private E[] array;
  private size;
  
  
  public int size() {
  }
  public int isEmpty() {
  }
  public MinHeap(int size) {
    array = new E[size];
    this.size = size;
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
      return array[0];
      update(); //TODO
      size--;
    }
  }
  
  private resize();
  
  
  
｝
