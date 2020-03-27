package com.company.PermCheck;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class Solution {

    public int solution(int[] A) {
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                return 0;
            }
        }
        return 1;
    }
}