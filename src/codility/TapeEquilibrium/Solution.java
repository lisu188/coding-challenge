package codility.TapeEquilibrium;

public class Solution {
    public static void main(String[] arr) {
        new Solution().solution(new int[]{-1000, 1000});
    }

    private int[] buildCumulativeArray(int[] A) {
        int[] returnValue = new int[A.length];
        returnValue[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            returnValue[i] = A[i] + returnValue[i - 1];
        }
        return returnValue;
    }

    public int solution(int[] A) {
        int[] ints = buildCumulativeArray(A);
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < ints.length - 1; i++) {
            int left = ints[i];
            int right = ints[ints.length - 1] - left;
            int probableMinValue = Math.abs(left - right);
            if (probableMinValue < minValue) {
                minValue = probableMinValue;
            }
        }
        return minValue;
    }
}