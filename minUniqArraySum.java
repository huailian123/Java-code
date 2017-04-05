/*
Minimum unique array sum
Question: Given a sorted integer array, return sum of array so that each element is unique by adding some numbers to duplicate elements so that sum of unique elements is minimum.

i.e., if all elements in the array are unique, return the sum. if some elements are duplicates, then add some numbers to make all elements are unique so that these unique elements sum is minimum

For example,

input1[] = { 2, 3, 4, 5 }, return 19 ( = 2+3+4+5 since all elements are unique, just add them up)

input2[] = { 1, 2, 2 }, return 6 ( = 1 + 2 + 3 since 2 is duplicate, so make input2[2]+1 = 3 ==> we get unique elements 1 + 2 + 3 )

input3[] = { 2, 2, 4, 5 }, return ( = 2 + 3+ 4+ 5 since input3[1] = 2 which is duplicate so make them 3)
*/

// if unsorted array, sort first, would be o(nlogn)

//tested . o(n)
public int minUniqArraySum(int[] array) {
  if (array == null || array.length == 0) {
    return 0;
  }
  int res = array[0];
  int cur = array[0];
  for (int i = 1; i < array.length; i++) {
    if (array[i] == cur) {
      array[i]++;   
    } 
    cur = array[i];
    res += array[i];
  }
  return res;
}
