/*
All Subsets I
Given a set of characters represented by a String, return a list containing all subsets of the characters.

Assumptions

There are no duplicate characters in the original set.
​Examples

Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
Set = "", all the subsets are [""]
Set = null, all the subsets are []
*/


public class Solution {
  public List<String> subSets(String set) {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    if (set == null) {
      return res;
    }
    helper(set, 0, sb, res);
    return res;
  }
  
  void helper(String set, int level, StringBuilder sb, List<String> res) {
    if (level == set.length()) {
      res.add(sb.toString());
      return;
    }
    sb.append(set.charAt(level));
    helper(set, level + 1, sb, res);
    sb.deleteCharAt(sb.length() - 1);
    helper(set, level + 1, sb, res);
  }
  
}
