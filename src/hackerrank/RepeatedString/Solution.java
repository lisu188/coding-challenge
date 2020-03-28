package hackerrank.RepeatedString;

import java.io.*;
import java.util.*;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the repeatedString function below.
    static long repeatedString(String s, long n) {
        return count(s) * Math.floorDiv(n, s.length()) + count(s, Math.floorMod(n, s.length()));
    }

    private static long count(String s) {
        return s.chars().filter(c -> c == 'a').count();
    }

    private static long count(String s, long limit) {
        return s.chars().limit(limit).filter(c -> c == 'a').count();
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
