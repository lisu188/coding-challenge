package hackerrank.RansomNote;

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
    static void checkMagazine(String[] magazine, String[] note) {
        HashTable hashTable = new HashTable(magazine.length * 4);
        Arrays.stream(magazine).forEach(hashTable::insert);
        if (Arrays.stream(note).allMatch(hashTable::remove)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
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
