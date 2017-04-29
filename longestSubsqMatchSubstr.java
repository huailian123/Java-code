// find the longest substring of y which is a subsequence of x

// DP, deduction rule: (from left top to right bottom)
// m[i][j] represent the longest substring of y[0-i](Note: including y[i]) which is a subsequence of x[0-j]

// m[0][0] = 1 if y0 = x0;
// m[0][j] = 1 if current 2 char equals or m[0][j - 1] == 1;
// m[i][0] = 1 if current 2 char equals 
//	     0 if current 2 char not equals;
// m[i][j] = m[i - 1][j -1] + 1 ,  if current 2 char equals 
//	     m[i][j - 1] o.w;


//tested. I think this is correct
public int longestSubsequence(String x, String y) {
		// write your code here
		if (x == null || y == null || x.length() == 0 || y.length() == 0) {
			return 0;
		}
		
		int r = y.length();
		int c = x.length();

		int[][] m = new int[r][c];
		int res = 0;

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (i == 0 && j == 0) {
					m[i][j] = x.charAt(j) == y.charAt(i) ? 1 : 0; 
				} else if (i == 0) {
					m[i][j] = (m[i][j - 1] == 1) || (x.charAt(j) == y.charAt(i)) ? 1 : 0; 
				} else if (j == 0) {
					m[i][j] = (x.charAt(j) == y.charAt(i)) ? 1 : 0; 
				} else {
					if (x.charAt(j) == y.charAt(i)) { 
						m[i][j] = m[i - 1][j -1] + 1; 
					} else {
						m[i][j] = m[i][j - 1]; 
					}
				}
				res = Math.max(res, m[i][j]);
			}
		}
		return res;
	}
