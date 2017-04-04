// int[] array input.
// find the sum of all the odd factor of all the numbers
// eg. {2, 4, 6, 7, 18} out= 1 + 1 + (1 + 3) + (1 + 7) + (1 + 3 + 9) = 27

//tested
public static int oddFactorSum(int[] array) {
		if (array == null || array.length == 0) {
			return 0;
		}
		int res = 0;
		for(int i = 0; i < array.length; i++) {
			if (array[i] > 0) {
      // for each number, loop from 1 to sqrt(n), if i is dividable, check n/i also. if i*i = n, only add once
				for (int j = 1; j * j <= array[i]; j++) {
					if (array[i] % j == 0 && j % 2 != 0) {
						res += j;
					}
					if (array[i] % j == 0 && j * j != array[i] && (((array[i] / j) % 2) != 0)) {
						res += array[i] / j;
					}
				}
				System.out.println(res);
			}
		}
		return res;
	}
