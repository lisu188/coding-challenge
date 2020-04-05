package hackerrank.SherlockAndAnagrams;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        HashTable hashTable = new HashTable(s.length() * s.length() * 4);
        substrings(s).forEach(hashTable::insert);
        return substrings(s).mapToInt(s1 -> {
            if (hashTable.remove(s1) && hashTable.remove(reversed(s1))) {
                return 1;
            }
            return 0;
        }).sum();
    }

    private static String reversed(String s1) {
        return new StringBuilder().append(s1).reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

    static Stream<String> substrings(String s) {
        return IntStream.range(1, s.length()).
                boxed()
                .flatMap((Function<Integer, Stream<int[]>>) length -> IntStream.range(0, s.length() - length + 1)
                        .boxed()
                        .map(index -> new int[]{index, index + length}))
                .map(o -> s.substring(o[0], o[1]));
    }

    static class HashTable {
        private String[][] hashtable;

        HashTable(int size) {
            hashtable = new String[size][];
        }

        public void insert(String string) {
            int bucket = hash(string);
            if (hashtable[bucket] == null) {
                hashtable[bucket] = new String[1];
            } else {
                String[] tmp = hashtable[bucket];
                hashtable[bucket] = new String[tmp.length + 1];
                System.arraycopy(tmp, 0, hashtable[bucket], 0, tmp.length);
            }
            hashtable[bucket][hashtable[bucket].length - 1] = string;
        }

        private int hash(String string) {
            return Math.abs(string.hashCode() % hashtable.length);
        }

        public boolean remove(String string) {
            int bucket = hash(string);
            String[] strings = hashtable[bucket];
            if (strings == null) {
                return false;
            }
            int index = -1;
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].equals(string)) {
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

            String[] tmp = hashtable[bucket];
            hashtable[bucket] = new String[tmp.length - 1];
            System.arraycopy(tmp, 0, hashtable[bucket], 0, index);
            System.arraycopy(tmp, index + 1, hashtable[bucket], index, hashtable[bucket].length - index);

            return true;
        }

        public boolean contains(String string) {
            int bucket = hash(string);
            String[] strings = hashtable[bucket];
            if (strings == null) {
                return false;
            }
            return Arrays.stream(strings).anyMatch(data -> data.equals(string));
        }
    }
}
