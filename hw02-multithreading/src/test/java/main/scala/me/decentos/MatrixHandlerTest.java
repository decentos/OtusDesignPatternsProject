package main.scala.me.decentos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class MatrixHandlerTest {

    private MatrixHandler handler;

    @BeforeEach
    void setUp() {
        handler = new MatrixHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testForException() {
        int[][] a = {{1, 2, 3}, {2, 3 ,4}, {1, 1, 1}};
        int[][] b = {{4, 2, 42}, {3, 9 ,4}};
        assertThrows(UnsupportedMatrixException.class, () -> handler.multiplication(a, b));
    }

    @Test
    public void test3x3Matrix() throws ExecutionException, InterruptedException {
        int[][] a = {
                {1, 2, 3},
                {2, 3 ,4},
                {1, 1, 1}};
        int[][] b = {
                {4, 2, 42},
                {3, 9 ,4},
                {2, 3, 3}};
        long[][] expectedMatrix = {
                {4 + 6 + 6, 2 + 18 + 9, 42 + 8 + 9},
                {8 + 9 + 8, 4 + 27 + 12 , 84 + 12 + 12},
                {4 + 3 + 2, 2 + 9 + 3, 42 + 4 + 3}};
        long[][] result = handler.multiplication(a, b);
        assertArrayEquals(expectedMatrix, result);
    }

    @Test
    public void test1x1Matrix() throws ExecutionException, InterruptedException {
        int[][] a = {{7}};
        int[][] b = {{4}};
        long[][] expectedMatrix = {{28}};
        long[][] result = handler.multiplication(a, b);
        assertArrayEquals(expectedMatrix, result);
    }
}