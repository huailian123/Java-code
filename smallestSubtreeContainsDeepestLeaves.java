/*
Given a tree, find the smallest subtree that contains all of the tree's deepest nodes. 
      a 
    / | \ 
    b c d 
   / \ | 
   e f g 
  / / \ 
  h i j 
   
   depth of tree: 4 
   deepest nodes: 
   least common ancestor of : b 
   return: b 

链接: https://instant.1point3acres.com/thread/155556
来源: 一亩三分地

*/

//就是算height。如果所有subtree height都一样，那就返回root，否则返回height最高那个subtree所返回的答案
// not tested
TreeNode smallestSubtree(TreeNode root) {
  if(root == null) {
    return root;
  }
  int left = height(root.left);
  int right = height(root.right);
  if(left == right) {
    return root;
  }
  if(right > left) {
    return smallestSubtree(root.right);
  } else {
    return smallestSubtree(root.left);
  }
  
}

int height(TreeNode root) {
  if(root == null) {
    return 0;
  }
  int left = height(root.left);
  int right = height(root.right);
  return Math.max(left, right) + 1;
}


// method 2:
class Height {
      int h;
}
TreeNode smallestSubtree(TreeNode root) {
  Height h = new Height();
  return height(root, h);
}

TreeNode height(TreeNode root, Height h) {
  if(root == null) {
    h = 0;
    return root;
  }  
  Height hl = new Height();
  Height hr = new Height();
      
  TreeNode left = height(root.left, hl);
  TreeNode right = height(root.right, hr);
  
  h = Math.max(hl, hr) + 1;
  if(hl == hr) {
        return root;
  }
  return hl == hr ? root : (hl > hr ? left : right);
}
