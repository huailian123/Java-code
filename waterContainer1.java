//  -----LEETCODE 11----
// Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
// n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
// Find two lines, which together with x-axis forms a container, such that the container contains the most water.
// Note: You may not slant the container and n is at least 2.


//NOTE: different with LEETCODE 11.
// LEETCODE 11 : any 2 bars can form a containner no matter if there is a heigher bar bettwen i and j.  
// HERE:           .........can't .................if there is a heigher bar bettwen i and j.

//              INPUT                 OUTPUT
//                             HERE        LEETCODE
//           [1,2,3,2,1]         2              4
//           [1,1,2]             1              2

//SOLUTION:
// traverse from the left to right, using a stack to store the previous bar's index. 
//  1. if the current bar i shorter than i-1,  curVol = min(i, i-1), update the res.
//  2. ...................i  higher than i-1,  curVol = (i - idx(from i-1 to 0 the first one >= height[i]) ) * height[i]
//                    at the same time, poll out all the ones <= height[i]. (need to update res all the time).
//conclution: if i < i - 1, stack.offer, if i >= i - 1, revome the ones <= i until j > i or statck is empty.                    


// tested
public class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        Deque<Integer> stack = new LinkedList<>(); // store the index of previous bars
        int res = 0;
        stack.offerFirst(0);
        
        for (int i = 1; i < height.length; i++) {
            if (height[i] < height[i - 1]) {
                res = Math.max(res, height[i]);
            } else {
                while (!stack.isEmpty() && height[stack.peekFirst()] <= height[i]) {
                    int prevIdx = stack.pollFirst();
                    res = Math.max(res, height[prevIdx] * (i - prevIdx));
                }
            }
            stack.offerFirst(i);  // put in the idx
        }
        return res;
    }
    
    
}
