package com.company.PermMissingElem;

import java.util.Set;
import java.util.HashSet;

public class Solution {
    public int solution(int[] A) {
        boolean[] aux = new boolean[A.length + 1];
        for (int x : A) {
            aux[x - 1] = true;
        }
        for (int i = 0; i < aux.length; i++) {
            if (!aux[i]) {
                return i + 1;
            }
        }
        return -1;//will not happen with assumptions
    }
}