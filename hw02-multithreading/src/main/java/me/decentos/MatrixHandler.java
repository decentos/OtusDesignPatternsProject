package me.decentos;

import java.util.Arrays;
import java.util.concurrent.*;

public class MatrixHandler {

    public long[][] multiplication(int[][] a, int[][] b) throws ExecutionException, InterruptedException {
        int aLengthX = a.length;
        int bLengthX = b.length;
        if (aLengthX != bLengthX
                || Arrays.stream(a).anyMatch(e -> e.length != aLengthX)
                || Arrays.stream(b).anyMatch(e -> e.length != bLengthX)) throw new UnsupportedMatrixException();

        long[][] result = new long[aLengthX][aLengthX];
        Future<Long>[][] resultCallable = new Future[aLengthX][aLengthX];
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                GetElementMultiplicationResult getElementMultiplicationResult = new GetElementMultiplicationResult(a, b, i, j);
                resultCallable[i][j] = scheduledExecutorService.schedule(getElementMultiplicationResult, 1, TimeUnit.SECONDS);
            }
        }

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                result[i][j] = resultCallable[i][j].get();
            }
        }

        scheduledExecutorService.shutdown();
        return result;
    }
}
