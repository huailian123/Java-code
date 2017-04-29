// find the longest substring of y which is a subsequence of x

// DP, deduction rule: (from left top to right bottom)
// m[i][j] represent the longest substring of y[0-j] which is a subsequence of x[0-i]

	public int longestSubsequence(String x, String y) {
		// write your code here
		if (x == null || y == null || x.length() == 0 || y.length() == 0) {
			return 0;
		}

		int r = x.length();
		int c = y.length();

		int[][] m = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (i == 0 && j == 0) {
					m[i][j] = x.charAt(i) == y.charAt(j) ? 1 : 0; 
				} else if (i == 0) {
					m[i][j] = (m[i][j - 1] == 1) || (x.charAt(i) == y.charAt(j)) ? 1 : 0; 
				} else if (j == 0) {
					m[i][j] = (m[i - 1][j] == 1) || (x.charAt(i) == y.charAt(j)) ? 1 : 0; 
				} else {
					if (x.charAt(i) == y.charAt(j)) { 
						m[i][j] = m[i - 1][j -1] + 1; 
					} else {
						m[i][j] = Math.max(m[i - 1][j], m[i][j - 1]); 
					}
				}
			}
		}
		return m[r - 1][c - 1];
	}

