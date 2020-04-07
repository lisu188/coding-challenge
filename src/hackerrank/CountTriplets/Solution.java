package hackerrank.CountTriplets;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        HashMap hashMap = new HashMap(arr.size());
        for (int i = 0; i < arr.size(); i++) {
            hashMap.insert(arr.get(i), i);
        }

        int triplets = 0;
        for (int i = 0; i < arr.size(); i++) {
            List<Integer> possibleSecond = getPossibleNext(hashMap, arr.get(i), i, r);
            for (int secondIndex : possibleSecond) {
                triplets += getPossibleNext(hashMap, arr.get(secondIndex), secondIndex, r).size();
            }
        }
        return triplets;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

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

    static List<Integer> getPossibleNext(HashMap hashMap, long number, int index, long r) {
        long secondNumber = number * r;
        return hashMap.get(secondNumber).stream().filter(n -> n > index).collect(toList());
    }

    static class HashMap {
        private Entry[][] hashtable;

        HashMap(int size) {
            hashtable = new Entry[size][];
        }

        public void insert(long key, int value) {
            Entry entry = new Entry(key, value);
            int bucket = hash(entry.key);
            if (hashtable[bucket] == null) {
                hashtable[bucket] = new Entry[1];
            } else {
                Entry[] tmp = hashtable[bucket];
                hashtable[bucket] = new Entry[tmp.length + 1];
                System.arraycopy(tmp, 0, hashtable[bucket], 0, tmp.length);
            }
            hashtable[bucket][hashtable[bucket].length - 1] = entry;
        }

        public List<Integer> get(long key) {
            int bucket = hash(key);
            if (hashtable[bucket] != null) {
                return Arrays.stream(hashtable[bucket]).filter(entry -> entry.key == key).map(entry -> entry.value).collect(toList());
            }
            return Collections.emptyList();
        }

        private int hash(long key) {
            return Math.abs(Long.hashCode(key) % hashtable.length);
        }

        public boolean remove(long key, int value) {
            Entry entry = new Entry(key, value);
            int bucket = hash(entry.key);
            Entry[] entries = hashtable[bucket];
            if (entries == null) {
                return false;
            }
            int index = -1;
            for (int i = 0; i < entries.length; i++) {
                if (entries[i].key == entry.key && entries[i].value == entry.value) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                return false;
            }

            if (hashtable[bucket].length == 1) {
                hashtable[bucket] = null;
                return true;
            }

            Entry[] tmp = hashtable[bucket];
            hashtable[bucket] = new Entry[tmp.length - 1];
            System.arraycopy(tmp, 0, hashtable[bucket], 0, index);
            System.arraycopy(tmp, index + 1, hashtable[bucket], index, hashtable[bucket].length - index);

            return true;
        }

        private static class Entry {
            private final long key;
            private final int value;

            private Entry(long key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
