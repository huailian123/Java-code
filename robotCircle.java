/*
Check if a given sequence of moves for a robot is circular or not
Given a sequence of moves for a robot, check if the sequence is circular or not. A sequence of moves is circular if first and last positions of robot are same. A move can be on of the following.

  G - Go one unit
  L - Turn left
  R - Turn right 

Examples:

Input: path[] = "GLGLGLG"
Output: Given sequence of moves is circular 

Input: path[] = "GLLG"
Output: Given sequence of moves is circular 
*/


//TESTED
public class Test {
	    private static final int N = 0;
	    private static final int E = 1;
	    private static final int S = 2;
	    private static final int W = 3;
      
  public boolean moveCircle(String input) {
    if (input == null || input.length() == 0) {
      return true;
    }

    int x = 0;
    int y = 0;
    int dir = N;
    char[] array = input.toCharArray();
    for (int i = 0; i < array.length; i++) {
      if(array[i] == 'R') {       // if it is R
        dir = (dir + 1) % 4;
      } else if (array[i] == 'L') { // if it is L
        dir = (4 + dir - 1) % 4;
      } else {                  // if it is G
        if (dir == N) {
          y++;
        } else if (dir == E) {
          x++;
        } else if (dir == S) {
          y--;
        } else {
          x--;
        }      
      }
    }
    return x == 0 && y == 0;
  }
  
  public static void main(String[] args) {
    String a = "GLGLGLG";
    System.out.println(moveCircle(a));
    //OUTPUT : true
  }

}

