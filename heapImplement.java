public class LianPQ <E>｛
  private E[] array;
  private size;
  
  
  public int size() {  //date length stored inside
    return size;
  }
  public int isEmpty() {
    return size == 0;
  }
  public LianPQ(int inputSize) {
    array = new E[inputSize];
    size = 0;
  }
  
  public LianPQ(E[] inputArray) {
    array = inputArray;
    size = inputArray.length;
    heapify();
  }
  
  public offer(E elem){
//     if (size == array.length) {
//       resize();
//     } else {
      int curIdx = size;
      array[curIdx] = elem;
      percolateUp(curIdx);
      size++; 
//     }//end of else
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
      percolateDown(0);
      size--;
      return res;
    }
  }

  void swap (E[] array, int left, int right) {
    E tmp = array[left];
    array[left] = array[right];
    array[right] = tmp;
  }
  
  private void percolateDown(int idx) {
    
    while(idx * 2 + 1 < size) {
      int idxL = idx * 2 + 1;
      int idxR = idx * 2 + 2;
      int min = idxL;
      if (idxR < size && array[idxR].compareTo(array[idxL]) < 0) {
        min = idxR;
      }
      if (arary[idx].compareTo(array[min]) > 0) {
        swap(array, idx, min);
        idx = min;
      } else {
        break;
      } 
    }
  }

  private void percolateUp(int idx) {
    while (idx > 0) {
      int idxP = (idx - 1) / 2;
      if (array[idx].compareTo(array[idxP]) < 0) {
        swap(array, idx, idxp);
        idx = idxP;
      } else {
        break;
      }
    }
  }
  
  void heapify () { 
    int lastP = (array.length - 1) / 2;
    for (int i = lastP; i >=0; i--) {
      percolateDown(array[i]);
    }
  }
//   private resize(); 
  
｝// end of class
