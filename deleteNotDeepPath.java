/*
Given a binary tree, the path cost from the root to one leaf is defined to be the number of the nodes in that path. For example, node 5 has a path whose cost is 3. Try to delete all the nodes who have no paths with cost >= k.
 
Input: k = 4
                 1
               /      \
             2         3
           /    \       /   \
          4     5     6    7
         /  \             \     
        8  9             10
 
Output:
 	                1
               /      \
             2         3
           /    \       /   \
          4    ( 5 )    6   ( 7)
         /  \             \     
        8  9             10    

*/

TreeNode deleteNotDeepPath(TreeNode root, int k) {
  if(root == null) {
    return root;
  }
  int res = helper(root, 1, k);
  return res >= k ? root : null;
}

int helper(TreeNode root, int level, int k) {
  if(root == null) {
    return 0；
  }
  if(root.left == null && root.right == null) {
    return level;
  }
  int left = helper(root.left, level + 1, k);
  int right = helper(root.right, level + 1, k);
  if(left < k) {
    root.left = null;
  }
  if(right < k) {
    root.right = null;
  }
  
  return Math.max(left, right);
}
