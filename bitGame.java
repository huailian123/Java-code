// given two positive interger left, right. find the largest XOR out put <= k between 2 numbers between [left, right]
// eg. intput 2,4,8   output: 7
// explain:there are 3 numbers in [2,4] : 2,3,4.
// 2: 010
// 3: 011
// 4: 100
// 3 xor 4 = bin(111) = 7 <= 8;

// find out the most significant bit which has diff btw left and right.  set this bit and all the less significant bit as 1 of the result.
int largestXOR(int left, int right, int k) {
  int res = 0;
  while (left != right) {
    int tmp = (res << 1) + 1;
    res = tmp <= k ? tmp : res;
    left <<= 1;
    right <<= 1;
  }
  return res;
}
