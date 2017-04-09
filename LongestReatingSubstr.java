/* 
Longest repeating and non-overlapping substring
Given a string str, find the longest repeating non-overlapping substring in it. In other words find 2 identical substrings of maximum length which do not overlap. If there exists more than one such substring return any of them.

Examples:

Input : str = "geeksforgeeks"
Output : geeks

Input : str = "aab"
Output : a

Input : str = "aabaabaaba"
Output : aaba

Input : str = "aaaaaaaaaaa"
Output : aaaaa

Input : str = "banana"
Output : an 
         or na
         */
         
/*******
using 2d DP to solve.

m[i][j] represents the repeating length so far for ending with ith and ending with jth(both inclusive).
induction rule:
  m[i][j]  = m[i - 1][j - 1] + 1    if : str[i] == str[j] &&  (j - i) > m[i - 1][j - 1]
             0                       o.w
base case :
 m[i][i] = 0;
             
             

*******/
//test
class Test {
    public static String longestReatingSubstr(String input) {
    if (input == null || input.length() <= 1) {
      return "";
    }
    int len = input.length();
    int[][] m = new int[len][len];
    int max = Integer.MIN_VALUE;
    int idx = 0;
    for (int i = 0; i < len; i++) {
      for (int j = i; j < len; j++) {
          
        if (i != j && input.charAt(i) == input.charAt(j)) {
            if ((i - 1 >= 0 && j - 1 >= 0) && (j - i) > m[i - 1][j - 1]) {
                //(j - i) > m[i - 1][j - 1] is to make sure no overlap
                m[i][j] = m[i - 1][j - 1] + 1; 
            } else {
                m[i][j] = 1;
            }
        }
        if (m[i][j] > max) {
          max = m[i][j];
          idx = i;  //or idx = j  is the same
        }
      }
    }
    return input.substring(idx - max + 1, idx + 1);
}
	public static void main (String[] args) {
		String a = "geeksforgeeks";
		System.out.println(longestReatingSubstr(a));
		a = "aabaabaaba";
		System.out.println(longestReatingSubstr(a));
		a = "aaaaaaaaaaa";
		System.out.println(longestReatingSubstr(a));
		a = "banana";
		System.out.println(longestReatingSubstr(a));
		
	}
}
