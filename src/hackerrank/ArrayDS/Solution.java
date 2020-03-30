package hackerrank.ArrayDS;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        return combine(IntStream.range(0, arr.length - 2).boxed(), IntStream.range(0, arr[0].length - 2).boxed())
                .map(integers -> getHourGlass(arr, integers.get(0), integers.get(1)))
                .max(Integer::compare).get();//we are sure that there is one
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    static int getHourGlass(int[][] arr, int x, int y) {
        return arr[y][x] + arr[y][x + 1] + arr[y][x + 2] + arr[y + 1][x + 1] + arr[y + 2][x] + arr[y + 2][x + 1] + arr[y + 2][x + 2];
    }


    private static <T> Stream<List<T>> combine(Stream<T>... collections) {
        Stream<List<T>> combined = null;
        for (Stream<T> next : collections) {
            if (combined == null) {
                combined = next.map(Collections::singletonList);
            } else {
                combined = combine(combined, next.map(Collections::singletonList));
            }
        }
        return combined;
    }

    private static <T> Stream<List<T>> combine(Stream<List<T>> first, Stream<List<T>> second) {
        List<List<T>> collect = second.collect(toList());//must be collected to avoid multiple consumption of same stream
        return first.flatMap(firstElement -> collect.stream().map(
                secondElement -> Stream.concat(firstElement.stream(), secondElement.stream()).collect(toList())));
    }
}
