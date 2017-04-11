//LEETCODE 11
//Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

//Note: You may not slant the container and n is at least 2.

//Note: in the problem, any 2 bars can form a containner even if there is a bar in between and higher than both ends
/*
    solution: make 2 pointers at both ends, alway move the shorter one towards middle. Always update the res.
    prove: from 0 ~ len-1. eg if height[0] < height[len-1], the biggest container height[0] can form is from 0 ~ len-1. so we can skip height[0]. The other way is the same.
*/


//INPUT [1,2,1] OUTPUT: 2
public class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int res = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r) {
            int lh = height[l];
            int rh = height[r];
            res = Math.max(res, Math.min(lh, rh) * (r - l)); //update res
            if (lh < rh) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }
    
    
}
