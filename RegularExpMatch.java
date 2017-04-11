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
// 2D DP
// boolean m[i][j] (m[s.length()+1][p.length()+1])represent if s[i] - p[j] can match. m[0][0] represent if "" and "" can match
//  deduction rule:
// 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
// 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
// 3, If p.charAt(j) == '*': 
//   here are two sub conditions:
//               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
//               2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
//                               dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
//                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
//                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
//
// Fill the table. first: 0-th row and 0-th col. second: uper left to right bottom
//
//  return m[lens][lenp]

// tested
// NOTE: This problem assume only the second input is regular expression
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        } 
        int lens = s.length();
        int lenp = p.length();
        boolean[][] m = new boolean[lens + 1][lenp + 1];
        for (int i = 0; i < lens + 1; i++) {
            for (int j = 0; j < lenp + 1; j++) {
                if(i == 0 && j == 0) {   //m[0][0]
                    m[i][j] = true; 
                } else if (j == 0) {     //m[i][0]
                    continue;
                } else if (i == 0) {     //m[0][j]
                    m[i][j] = (p.charAt(j - 1) == '*' && j - 2 >= 0 && m[i][j - 2]) ? true : false;
                } else {                 //m[i][j] when i,j != 0
                    if (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1)) {
                        m[i][j] = m[i - 1][j - 1];
                    } else if (p.charAt(j - 1) == '*') {
                    	if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                    		m[i][j] = m[i][j - 2] || m[i][j - 1] || m[i - 1][j];
                    	} else {
                    		m[i][j] = m[i][j - 2];
                    	}
                    } 
                }
            }
        }
        return m[lens][lenp];
    }

}
