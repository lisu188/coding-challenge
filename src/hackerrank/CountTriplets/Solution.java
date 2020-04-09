package hackerrank.CountTriplets;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        Counter<Long> lefCounter = new Counter<>();
        Counter<Long> rightCounter = new Counter<>();

        for (long val : arr) {
            rightCounter.increment(val);
        }

        long totalTriplets = 0;
        for (long val : arr) {
            rightCounter.decrement(val);
            if (val % r == 0) {
                long leftCount = lefCounter.get(val / r);
                long rightCount = rightCounter.get(val * r);
                totalTriplets += leftCount * rightCount;
            }
            lefCounter.increment(val);
        }
        return totalTriplets;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    static class Counter<T> {
        private final Map<T, Long> counter = new HashMap<>();

        public void increment(T object) {
            if (!counter.containsKey(object)) {
                counter.put(object, 0L);
            }
            counter.put(object, counter.get(object) + 1);
        }

        public void decrement(T object) {
            if (counter.get(object) == 0) {
                counter.remove(object);
            } else {
                counter.put(object, counter.get(object) - 1);
            }
        }

        public long get(T l) {
            return counter.getOrDefault(l, 0L);
        }
    }
}
