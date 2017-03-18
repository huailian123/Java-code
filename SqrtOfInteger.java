class Solution {
  
  public static double helper(int x, int n) {
    if (n == 0) {
      return 1;
    }
    double half = helper(x, n / 2);
    if (n % 2 == 0) {
      return half * half;
    } else {
      return half * half * x;
    }
  }
  
  public static int sqRoot(int x, int n) {
    return binarySearch(1, x, n);
  }
  
  static int binarySearch(int small, int large, int n) {
    int left = small;
    int right = large;
    while (left < right - 1) {
      int mid = left + (right - left) / 2;
      if (helper(mid, n) == large) {
        return mid;
      } else if (helper(mid, n) > large) {
        right = mid;
      } else {
        left = mid;
      }
    } //end of while
    return Math.abs(helper(left, n) - large) < Math.abs(helper(right, n) - large) ? left : right; 
  }

