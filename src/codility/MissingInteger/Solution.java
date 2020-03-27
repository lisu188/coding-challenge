package codility.MissingInteger;

import java.util.Arrays;

public class Solution {
    public static void main(String[] arr) {
        new Solution().solution(new int[]{1, 3, 6, 4, 1, 2});
    }

    public int solution(int[] A) {
        Arrays.sort(A);
        int expected = 1;
        for (int i = 0; i < A.length; i++) {
            int value = A[i];
            if (value >= 1) {
                if ((i > 0 && A[i] == A[i - 1])) {
                    continue;
                }
                if (value != expected) {
                    break;
                }
                expected++;
            }

        }
        return expected;
    }
}