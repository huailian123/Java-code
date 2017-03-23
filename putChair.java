Graph
Place To Put The Chair I
Given a gym with k pieces of equipment and some obstacles.  We bought a chair and wanted to put this chair into the gym such that  the sum of the shortest path cost from the chair to the k pieces of equipment is minimal. The gym is represented by a char matrix, ‘E’ denotes a cell with equipment, ‘O’ denotes a cell with an obstacle, 'C' denotes a cell without any equipment or obstacle. You can only move to neighboring cells (left, right, up, down) if the neighboring cell is not an obstacle. The cost of moving from one cell to its neighbor is 1. You can not put the chair on a cell with equipment or obstacle.

Assumptions

There is at least one equipment in the gym
The given gym is represented by a char matrix of size M * N, where M >= 1 and N >= 1, it is guaranteed to be not null
It is guaranteed that each 'C' cell is reachable from all 'E' cells.
If there does not exist such place to put the chair, just return null (Java) empty vector (C++)
Examples

{ { 'E', 'O', 'C' },

  {  'C', 'E',  'C' },

  {  'C',  'C',  'C' } }

we should put the chair at (1, 0), so that the sum of cost from the chair to the two equipment is 1 + 1 = 2, which is minimal.





// n^2logk
// 1.go through gym, find out each E and do BFSI.
// 2.for each E, using BFSI to find the shortest distance to each position 
//    and put in a matrix. added up the matrix for each E. Then each position will
//    have the shortest path sum to all E.
// 3.go through the matrix to find the smallest (if the original place is 'C').
// 4. if all nodes in matrix are 0 (which mean no updeta), means no result, return null.
// 

// Note: It is guaranteed that each 'C' cell is reachable from all 'E' cells. 
//       And any place not E or O will be C. So we do not need to initialize int[][] mat
//       to all Integer.MAX_VALUE. If not this assumption. We need to do that to avoid 
//       the non reachable place to the the smallest sum.


//tested
public class Solution {
  public List<Integer> putChair(char[][] gym) {
    
    int r = gym.length;
    int c = gym[0].length;
    // mat is used to store the shorted path weight from each E to C, added it up one the same matrix.
    int[][] mat = new int[r][c]; 
    
    // go through each node, if it if E, run BFS for this E.
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (gym[i][j] == 'E') {
          BFS(gym, i, j, mat);
        }
      }
    }// end of outer for
    // find the smallest num in int[][] mat, return it's indices
    int min = Integer.MAX_VALUE;
    int rowR = -1;  //Result row idx
    int colR = -1;  //Result col idx
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (gym[i][j] == 'C' && mat[i][j] < min) {
          min = mat[i][j];
          rowR = i;
          colR = j;
        }
      }
    } // end of outer for
    if (min == Integer.MAX_VALUE) {
      return null;
    }
    List<Integer> res = new ArrayList<>();
    res.add(rowR);
    res.add(colR);
    return res;
  }
  
  void BFS(char[][] gym, int r, int c, int[][] mat) {
    int row = gym.length;
    int col = gym[0].length;
    
    Queue<Cell> queue = new LinkedList<>();  // creat a queue
    queue.offer(new Cell(r, c, 0));
    boolean[][] visited = new boolean[row][col];  // de-dup
    visited[r][c] = true;
   
    while (!queue.isEmpty()) {
      //checke current Cell's 4 adjacent cells
      Cell cur = queue.poll();
      // if the position exist and is 'C'
      //up
      if (cur.r - 1 >= 0 && gym[cur.r - 1][cur.c] != 'O' && visited[cur.r - 1][cur.c] == false) {
        mat[cur.r - 1][cur.c] += cur.value + 1;
        visited[cur.r - 1][cur.c] = true;
        queue.offer(new Cell(cur.r - 1, cur.c, cur.value + 1));
      }
      //down
      if (cur.r + 1 < row && gym[cur.r + 1][cur.c] != 'O' && visited[cur.r + 1][cur.c] == false) {
        mat[cur.r + 1][cur.c] += cur.value + 1;
        visited[cur.r + 1][cur.c] = true;
        queue.offer(new Cell(cur.r + 1, cur.c, cur.value + 1));
      }
      //left
      if (cur.c - 1 >= 0 && gym[cur.r][cur.c - 1] != 'O' && visited[cur.r][cur.c - 1] == false) {
        mat[cur.r][cur.c - 1] += cur.value + 1;
        visited[cur.r][cur.c - 1] = true;
        queue.offer(new Cell(cur.r, cur.c - 1, cur.value + 1));
      }
      //right
      if (cur.c + 1 < col && gym[cur.r][cur.c + 1] != 'O' && visited[cur.r][cur.c + 1] == false) {
        mat[cur.r][cur.c + 1] += cur.value + 1;
        visited[cur.r][cur.c + 1] = true;
        queue.offer(new Cell(cur.r, cur.c + 1, cur.value + 1));
      }
    } // end of while
  }
  
  static class Cell {
    int r;
    int c;
    int value;
    
    Cell(int r, int c, int value) {
      this.r = r;
      this.c = c;
      this.value = value;
    }
  }
  
}
