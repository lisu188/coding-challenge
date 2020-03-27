package com.company.MaxCounters;

import java.util.*;

public class Solution {
    private int getNextValue(Map<Integer, Integer> counters, int command) {
        if (!counters.containsKey(command)) {
            return 1;
        } else {
            return counters.get(command) + 1;
        }
    }

    public int[] solution(int N, int[] A) {
        int currentAll = 0;
        int currentMax = 0;
        Map<Integer, Integer> countersToIncrement = new HashMap<>();

        for (int command : A) {
            if (command == N + 1) {
                currentAll = currentAll + currentMax;
                currentMax = 0;
                countersToIncrement.clear();
            } else {
                int nextValue = getNextValue(countersToIncrement, command);
                countersToIncrement.put(command, nextValue);
                if (nextValue > currentMax) {
                    currentMax = nextValue;
                }
            }
        }

        int[] retVal = new int[N];
        for (int i = 0; i < N; i++) {
            if (!countersToIncrement.containsKey(i + 1)) {
                retVal[i] = currentAll;
            } else {
                retVal[i] = currentAll + countersToIncrement.get(i + 1);
            }
        }

        return retVal;
    }
}