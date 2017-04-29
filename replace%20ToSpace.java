/*
in-place replace all "%20" in a string to a space, ex:

abc%20def -> abc def
abc%20%20%2 -> abc  %2

*/
// easy solution: every time if current is "%" and followed by "20", add " " to result and move current forward for 3, otherwise just add the cur char to result.
// tested
public String replaceString(String input) {
		if (input == null || input.length() <= 2) {
			return input;
		}
		char[] array = input.toCharArray();
		int start = 0; // the left part of start(excluding) are results
		for (int i = 0; i < array.length; i++) {
			if (i + 2 < array.length && array[i] == '%' && array[i + 1] == '2' && array[i + 2] == '0') {
				array[start++] = ' ';
				i += 2;
			} else {
				array[start++] = array[i];
			}
		}
		return new String(Arrays.copyOf(array, start));
 }
