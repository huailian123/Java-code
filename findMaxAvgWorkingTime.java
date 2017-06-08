/* 给一棵多叉树，表示公司内部的上下级关系。每个节点表示一个员工，节点包含的成员是他工作了几个月(int)，以及一个下属数组(ArrayList<Node>)。目标就是找到一棵子树，满足：这棵子树所有节点的工作月数的平均数是所有子树中最大的。
class Node { 
int val; 
ArrayList<Node> children; 
}
 
       CEO 100
       /     \
   vp1 120     vp2 80
  /  \    \         \ 
D5  d2 30 D 200    D 10
 
1.base case: if it is null, return {0, 0}  // {total num of ppl, total num of months}
2.recursion rule: 
	get all children nodes’ numP, and sumMonth
	add them up.
	add current numP & sumMonth
	calculate the avg and update max
  
  */
 
Node findMaxAvg(Node root) {
	if(root == null) {
		return null;
}
	double[] max = new double[] {Integer.MIN_VALUE};  // global max
	Node[] res = new Node[0];   // result
	helper(root, max, res);
	return res[0];
}
 
int[] helper(Node root, double[] max, Node[] res) {
	if(root == null) {
		return {0, 0};
}
int[] tmp = new int[2];
for (Node n : root.children) {
	int[] t = helper(n, max, res);
	tmp[0] += t[0];
	tmp[1] += t[1];
}
tmp[0]++;   // number of ppl
tmp[1] += root.val   // number of months
if(tmp[1] / tmp[0] > max[0]) {
	max[0] = tmp[1] / tmp[0];
	res[0] = root;
}
return tmp;
}
 
4. Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
Example:
Given
