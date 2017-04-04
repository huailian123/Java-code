/*
Implement a data structure that keeps track of 50 integers. 
How would you build this data structure to check if an element is present, 
and if the structure is full, remove the oldest element? There are also no duplicates.  
*/
// use 1. an circular array with start, end pointer and full indicator . so that removeOldest is o(1), is full is o(1)
//     2. a set to track if it is present in o(1)

//tested


public class DataStruct {
		int[] array;
		Set<Integer> set;
		private int start;
		private int end;
		private boolean full;
		private static final int INISIZE = 50;
		public DataStruct () {
			array = new int[INISIZE];
			set = new HashSet<>();
		}
		
		public boolean isFull() {
			return full == true;
		}
		public boolean isPresent(int value) {
			return set.contains(value);
		}
		public Integer removeOldest() {
			if (start == end && full == false) { // if empty, return null
				return null;
			} 
			if (full == true) {
				full = false;
			}
			set.remove(array[start]);
      if(start != INISIZE - 1){
				start++;
			} else {    // Note, need to set end to 0 once it reaches INISIZE - 1;
				start = 0;
			}
			if (start != 0) {        // make sure array[start - 1] exit when return
				return array[start - 1];
			} else {
				return array[INISIZE - 1];
			}
		}
		public Integer addTo(int value) {
			if (full == true || set.contains(value)) {
				return null;
			}
			array[end] = value;
			if(end != INISIZE - 1){
				end++;
			} else {    // Note, need to set end to 0 once it reaches INISIZE - 1;
				end = 0;
			}
			set.add(value);
			if (end == start) {
				full = true;
			}
			return value;
		}
	
	
	public static void main (String[] args) {
		DataStruct ds = new DataStruct();		
		for (int i = 1; i <= 5;i++)  {
			ds.addTo(i);
		}
		System.out.println(ds.isFull() + " full");
		ds.removeOldest();
		for (int i = 1; i <= 5;i++)  {
			ds.removeOldest();
		}
		for (int i = 1; i <= 5;i++)  {
			ds.addTo(i);
		}
		for (int i = 1; i <= 2;i++)  {
			ds.removeOldest();
		}
		//ds.removeOldest();
		System.out.println(ds.isPresent(3));
		System.out.println(ds.isPresent(2));
		System.out.println(ds.isFull() + " full");
	}
}
