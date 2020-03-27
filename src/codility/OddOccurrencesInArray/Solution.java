package codility.OddOccurrencesInArray;

import java.util.Set;
import java.util.HashSet;

public class Solution {
    public int solution(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int val : A) {
            if (set.contains(val)) {
                set.remove(val);
            } else {
                set.add(val);
            }
        }
        return set.iterator().next();
    }
}