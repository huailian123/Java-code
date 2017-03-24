
/*
Recursion
All Valid Permutations Of Parentheses II
Get all valid permutations of l pairs of (), m pairs of [] and n pairs of {}.

Assumptions

l, m, n >= 0
Examples

l = 1, m = 1, n = 0, all the valid permutations are ["()[]", "([])", "[()]", "[]()"]
*/


//tested
public class Solution {
  	public static List<String> validParentheses(int l, int m, int n) {
		List<String> res = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		Deque<Character> stack = new LinkedList<>();
		helper(l, m, n, 0, 0, 0, 0, 0, 0, sb, res, stack);
		return res;
	}

	static void helper(int l, int m, int n, int numlL, int numlR, int nummL, int nummR, int numnL, int numnR, StringBuilder sb, List<String> res, Deque<Character> stack) {
		if (l == numlL && l == numlR && m == nummL && m == nummR && n == numnL && n == numnR) {
			res.add(sb.toString());
			return;
		}
		if (numlL < l) {
			sb.append('(');
			stack.offerFirst('(');
			helper(l, m, n, numlL + 1, numlR, nummL, nummR, numnL, numnR, sb, res, stack);
			sb.deleteCharAt(sb.length() - 1);
			stack.pollFirst();
		}
		if (nummL < m) {
			sb.append('[');
			stack.offerFirst('[');
			helper(l, m, n, numlL, numlR, nummL + 1, nummR, numnL, numnR, sb, res, stack);
			sb.deleteCharAt(sb.length() - 1);
			stack.pollFirst();
		}
		if (numnL < n) {
			sb.append('{');
			stack.offerFirst('{');
			helper(l, m, n, numlL, numlR, nummL, nummR, numnL + 1, numnR, sb, res, stack);
			sb.deleteCharAt(sb.length() - 1);
			stack.pollFirst();
		}
		if (numlR < numlL && stack.peekFirst() == '(') {
			stack.pollFirst();
			sb.append(')');
			helper(l, m, n, numlL, numlR + 1, nummL, nummR, numnL, numnR, sb, res, stack);
			sb.deleteCharAt(sb.length() - 1);
			stack.offerFirst('(');
		}
		if (nummR < nummL && stack.peekFirst() == '[') {
			stack.pollFirst();
			sb.append(']');
			helper(l, m, n, numlL, numlR, nummL, nummR + 1, numnL, numnR, sb, res, stack);
			sb.deleteCharAt(sb.length() - 1);
			stack.offerFirst('[');
		}
		if (numnR < numnL && stack.peekFirst() == '{') {
			stack.pollFirst();
			sb.append('}');
			helper(l, m, n, numlL, numlR, nummL, nummR, numnL, numnR + 1, sb, res, stack);
			sb.deleteCharAt(sb.length() - 1);
			stack.offerFirst('{');
		}
	}
}
