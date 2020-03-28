package hackerrank.JumpingOnTheClouds;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        int[] distances = new int[c.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;
   
        TreeSet<Cloud> priorityQueue = new TreeSet<>(buildComparator());
        priorityQueue.add(new Cloud(0, 0));

        while (!priorityQueue.isEmpty()) {
            Cloud cloud = priorityQueue.pollFirst();
            List<Cloud> adjacent = getAdjacentClouds(cloud, c);
            for (Cloud adjacentCloud : adjacent) {
                if (adjacentCloud.distance < distances[adjacentCloud.index]) {
                    distances[adjacentCloud.index] = adjacentCloud.distance;
                    priorityQueue.add(adjacentCloud);
                }
            }
        }
        return distances[distances.length - 1];
    }

    private static Comparator<Cloud> buildComparator() {
        Comparator<Cloud> cloudComparator = Comparator.comparingInt(o -> o.distance);
        cloudComparator = cloudComparator.thenComparingInt(o -> o.index);
        return cloudComparator;
    }

    private static List<Cloud> getAdjacentClouds(Cloud cloud, int[] c) {
        List<Cloud> adjacentClouds = new ArrayList<>();
        if (cloud.index + 1 < c.length && c[cloud.index + 1] == 0) {
            adjacentClouds.add(new Cloud(cloud.index + 1, cloud.distance + 1));
        }
        if (cloud.index + 2 < c.length && c[cloud.index + 2] == 0) {
            adjacentClouds.add(new Cloud(cloud.index + 2, cloud.distance + 1));
        }
        return adjacentClouds;
    }


    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    private final static class Cloud {
        private final int index;
        private final int distance;

        private Cloud(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}
