package hackerrank.CountingValleys;

import java.io.*;
import java.util.*;

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    // Complete the countingValleys function below.
    static int countingValleys(int n, String s) {
        ValleyCounter valleyCounter = new ValleyCounter();
        for (char a : s.toCharArray()) {
            valleyCounter.feed(a);
        }
        return valleyCounter.getValleysCounted();
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int result = countingValleys(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    static class ValleyCounter {
        private int currentLevel;
        private int valleysCounted;
        private boolean isInValley;

        public void feed(char a) {
            switch (a) {
                case 'D':
                    decrementLevel();
                    break;
                case 'U':
                    incrementLevel();
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

        private void incrementLevel() {
            if (currentLevel == -1) {
                stepOutOfValley();
            }
            currentLevel++;
        }

        private void stepOutOfValley() {
            if (isInValley) {
                isInValley = false;
                valleysCounted++;
            } else {
                throw new IllegalArgumentException("Tried to step out of valley when not in valley!");
            }
        }

        private void decrementLevel() {
            if (currentLevel == 0) {
                stepIntoValley();
            }
            currentLevel--;
        }

        private void stepIntoValley() {
            if (!isInValley) {
                isInValley = true;
            } else {
                throw new IllegalArgumentException("Tried to step into valley when already in valley!");
            }
        }

        public int getValleysCounted() {
            return valleysCounted;
        }
    }
}
