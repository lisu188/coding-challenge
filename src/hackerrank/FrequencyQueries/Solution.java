package hackerrank.FrequencyQueries;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        List<Integer> answer = new ArrayList<>();

        Counter<Long> counter = new Counter<>();
        ReverseCounter<Long> reverseCounter = new ReverseCounter<>();

        for (List<Integer> query : queries) {
            int opCode = query.get(0);
            int no = query.get(1);
            long currentFrequency = counter.get((long) no);
            switch (opCode) {
                case 1:
                    reverseCounter.remove(currentFrequency, (long) no);
                    reverseCounter.put(currentFrequency + 1, (long) no);
                    counter.increment((long) no);
                    break;
                case 2:
                    reverseCounter.remove(currentFrequency, (long) no);
                    reverseCounter.put(currentFrequency + -1, (long) no);
                    counter.decrement((long) no);
                    break;
                case 3:
                    if (reverseCounter.has((long) no)) {
                        answer.add(1);
                    } else {
                        answer.add(0);
                    }
                    break;
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

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
            if (counter.containsKey(object)) {
                if (counter.get(object) == 0) {
                    counter.remove(object);
                } else {
                    counter.put(object, counter.get(object) - 1);
                }
            }
        }

        public long get(T l) {
            return counter.getOrDefault(l, 0L);
        }
    }


    static class ReverseCounter<T> {
        private final Map<Long, Set<T>> counter = new HashMap<>();

        public void put(Long count, T t) {
            if (!counter.containsKey(count)) {
                counter.put(count, new HashSet<>());
            }
            counter.get(count).add(t);
        }

        public void remove(Long count, T t) {
            if (counter.containsKey(count)) {
                counter.get(count).remove(t);
                if (counter.get(count).isEmpty()) {
                    counter.remove(count);
                }
            }
        }

        public boolean has(Long l) {
            return counter.containsKey(l);
        }
    }
}
