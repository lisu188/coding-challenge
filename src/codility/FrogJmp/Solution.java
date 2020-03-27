package codility.FrogJmp;

import java.util.Set;
import java.util.HashSet;

public class Solution {
    public int solution(int X, int Y, int D) {
        return (int) Math.ceil((Y - X) / (D + 0.0));
    }
}