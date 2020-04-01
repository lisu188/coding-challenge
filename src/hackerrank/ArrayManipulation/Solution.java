package hackerrank.ArrayManipulation;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.function.Consumer;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        NavigableMap<Integer, Long> arrayRepresentation = new TreeMap<>();
        arrayRepresentation.put(-1, 0L);
        arrayRepresentation.put(n, 0L);

        for (int[] query : queries) {
            int lowerIndex = query[0] - 1;
            int upperIndex = query[1];
            long value = query[2];

            if (arrayRepresentation.containsKey(lowerIndex)) {
                arrayRepresentation.put(lowerIndex, arrayRepresentation.get(lowerIndex) + value);
            } else {
                arrayRepresentation.put(lowerIndex, value);
            }

            if (arrayRepresentation.containsKey(upperIndex)) {
                arrayRepresentation.put(upperIndex, arrayRepresentation.get(upperIndex) - value);
            } else {
                arrayRepresentation.put(upperIndex, -value);
            }
        }
        long currentMax = 0;
        long currentValue = 0;
        for (Long val : arrayRepresentation.values()) {
            currentValue += val;
            if (currentValue > currentMax) {
                currentMax = currentValue;
            }
        }
        return currentMax;
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }


    static Range getLowerEntry(NavigableMap<Integer, Long> map, int index) {
        return map.get(index) != null ? buildRange(index, map.get(index)) : buildRange(map.lowerEntry(index));
    }

    private static Range buildRange(int index, long value) {
        return new Range(index, value);
    }

    private static Range buildRange(Map.Entry<Integer, Long> entry) {
        return new Range(entry.getKey(), entry.getValue());
    }

    static Range getUpperEntry(NavigableMap<Integer, Long> map, int index) {
        return buildRange(map.higherEntry(index));
    }

    static class Range {
        private final int index;
        private final long value;

        Range(int index, long value) {
            this.index = index;
            this.value = value;
        }

    }
}
