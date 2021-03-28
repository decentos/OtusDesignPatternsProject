package me.decentos;

import java.util.concurrent.Callable;

public class GetElementMultiplicationResult implements Callable<Long> {

    private final int[][] a;
    private final int[][] b;
    private final int row;
    private final int col;

    GetElementMultiplicationResult(int[][] a, int[][] b, int row, int col) {
        this.a = a;
        this.b = b;
        this.row = row;
        this.col = col;
    }

    @Override
    public Long call() {
        long result = 0;
        for (int i = 0; i < a.length; i++) {
            result += a[row][i] * b[i][col];
        }
        return result;
    }
}
