package codility.FrogRiverOne;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int solution(int X, int[] A) {
        Set<Integer> fallenLeaves = new HashSet<>();
        for (int i = 0; i < A.length; i++) {
            fallenLeaves.add(A[i]);
            if (fallenLeaves.size() == X) {
                return i;
            }
        }
        return -1;
    }
}