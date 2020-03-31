package hackerrank.NewYearChaos;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.IntPredicate;
import java.util.regex.*;
import java.util.stream.IntStream;


public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    static void minimumBribes(int[] q) {
        if (IntStream.range(0, q.length).anyMatch(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return q[value] - (value + 1) > 2;
            }
        })) {
            System.out.println("Too chaotic");
        } else {
            System.out.println(MergeSort.countSwaps(q));
        }
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }

    static class MergeSort {
        static int merge(int arr[], int temp[],
                         int left, int mid, int right) {
            int inv_count = 0;

            int i = left;
            int j = mid;
            int k = left;

            while ((i <= mid - 1) && (j <= right)) {
                if (arr[i] <= arr[j])
                    temp[k++] = arr[i++];
                else {
                    temp[k++] = arr[j++];
                    int inversions = mid - i;
                    inv_count = inv_count + inversions;
                }
            }

            while (i <= mid - 1)
                temp[k++] = arr[i++];

            while (j <= right)
                temp[k++] = arr[j++];

            for (i = left; i <= right; i++)
                arr[i] = temp[i];

            return inv_count;
        }

        static int _mergeSort(int arr[], int temp[],
                              int left, int right) {
            int mid, inv_count = 0;
            if (right > left) {
                mid = (right + left) / 2;

                inv_count = _mergeSort(arr, temp,
                        left, mid);

                inv_count += _mergeSort(arr, temp,
                        mid + 1, right);

                inv_count += merge(arr, temp,
                        left, mid + 1, right);
            }

            return inv_count;
        }


        static int countSwaps(int arr[]) {
            int temp[] = new int[arr.length];
            return _mergeSort(arr, temp, 0, arr.length - 1);
        }
    }
}
