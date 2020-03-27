package com.company.CountDiv;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] arr) {
        new com.company.CountDiv.Solution().solution(7, 12, 2);
    }

    public int solution(int A, int B, int K) {
        int firstDivisible = A % K == 0 ? A : A + (K - A % K);
        int lastDivisible = B + (K - B % K) - K;
        if (firstDivisible > B || lastDivisible < A) {
            return 0;
        }
        return (lastDivisible - firstDivisible) / K + 1;
    }


}