/*
leetcode 10. Regular Expression Matching
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/

// 2D DP
// boolean m[i][j] (m[s.length()+1][p.length()+1])represent if s[i] - p[j] can match. m[0][0] represent if "" and "" can match
//  deduction rule:
//     m[i][j] = T   if (p[j] is '.' or both char equals) && m[i - 1][j - 1] is T
//               T   if (p[j] is '*' && (p[j-1] is '.' or equal to s[i]) && m[i - 1][j - 2] is T 
//               F   all other cases when there is a '*'.
//  base case:
//          m[0][0] = T.  
//          m[i][0] = F (except m[0][0])
//          m[0][j] = F   if p[j] is a char or '.'
//                    T   if p[j] is a '*' && m[0][j - 2] == T
//
// Fill the table. first: 0-th row and 0-th col. second: uper left to right bottom
//
//  return m[lens][lenp]

//not working yet
// NOTE: This problem assume only the second input is regular expression
public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) {
            return true;
        } else if (s == null || p == null) {
            return false;
        } 
        int lens = s.length();
        int lenp = p.length();
        boolean[][] m = new boolean[lens + 1][lenp + 1];
        for (int i = 0; i < lens; i++) {
            for (int j = 0; j < lenp; j++) {
                if(i == 0 && j == 0) {   //m[0][0]
                    m[i][j] = true; 
                } else if (j == 0) {     //m[i][0]
                    continue;
                } else if (i == 0) {     //m[0][j]
                    m[i][j] = p.charAt(j) == '*' && j - 2 >= 0 && m[i][j - 2] ? true : false;
                } else {                 //m[i][j] when i,j != 0
                    if (p.charAt(j) == '.' || s.charAt(i) == p.charAt(j) && m[i - 1][j - 1]) {
                        m[i][j] = true;
                    } else if (p.charAt(j) == '*' && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i)) && ((j - 2 < 0) || m[i - 1][j - 2])) {
                        m[i][j] = true;
                    } else {
                        m[i][j] = false;
                    }
                }
            }
        }
        return m[lens][lenp];
    }
    
    
}
