package hackerrank.TwoStrings;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.IntConsumer;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the checkMagazine function below.
    static String twoStrings(String s1, String s2) {
        HashTable hashTable = new HashTable(s1.length() * 4);
        s1.chars().mapToObj(c -> String.valueOf((char) c)).forEach(hashTable::insert);
        if (s2.chars().mapToObj(c -> String.valueOf((char) c)).anyMatch(hashTable::contains)) {
            return "YES";
        } else {
            return "NO";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

    static class HashTable {
        private String[][] hashtable;

        HashTable(int size) {
            hashtable = new String[size][];
        }

        public void insert(String string) {
            if (!contains(string)) {
                int bucket = hash(string);
                if (hashtable[bucket] == null) {
                    hashtable[bucket] = new String[1];
                    hashtable[bucket][0] = string;
                } else {
                    String[] tmp = hashtable[bucket];
                    hashtable[bucket] = new String[tmp.length + 1];
                    System.arraycopy(tmp, 0, hashtable[bucket], 0, tmp.length);
                }
                hashtable[bucket][hashtable[bucket].length - 1] = string;
            }
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
