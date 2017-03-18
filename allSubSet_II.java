/*

Recursion
All Subsets II
Given a set of characters represented by a String, return a list containing all subsets of the characters.

Assumptions

There could be duplicate characters in the original set.
​Examples

Set = "abc", all the subsets are ["", "a", "ab", "abc", "ac", "b", "bc", "c"]
Set = "abb", all the subsets are ["", "a", "ab", "abb", "b", "bb"]
Set = "", all the subsets are [""]
Set = null, all the subsets are []
*/



/* method 2: reduced nodes. 
              branches : non-dup char after cur index
              level num :  total number of char
              each level represent all sets have level numbers.
              */
public class Solution {
  public List<String> subSets(String set) {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    if (set == null) {
      return res;
    }
    char[] array = set.toCharArray();
    Arrays.sort(array);
    helper(array, 0, sb, res);
    return res;
  }
  
  void helper(char[] array, int level, StringBuilder sb, List<String> res) {
    
    res.add(sb.toString());
    for (int i = level; i < array.length; i++) {
      sb.append(array[i]);
      helper(array, i + 1, sb, res);
      sb.deleteCharAt(sb.length() - 1);
      while(i < array.length - 1 && array[i] == array[i + 1]) {
        i++;
      }
    }//  end of for 
  }
  
}


/* method 1:  2 branches, level num == total char num */
// public class Solution {
//   public List<String> subSets(String set) {
//     List<String> res = new ArrayList<>();
//     StringBuilder sb = new StringBuilder();
//     if (set == null) {
//       return res;
//     }
//     char[] array = set.toCharArray();
//     Arrays.sort(array);
//     helper(array, 0, sb, res);
//     return res;
//   }
  
//   void helper(char[] array, int level, StringBuilder sb, List<String> res) {
//     if (level == array.length) {
//       res.add(sb.toString());
//       return;
//     }
//     sb.append(array[level]);
//     helper(array, level + 1, sb, res);
//     sb.deleteCharAt(sb.length() - 1);
//     while (level < array.length - 1 && (array[level] == array[level + 1])) {
//       level++;
//     }
//     helper(array, level + 1, sb, res);
//   }
// }
