package me.decentos;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class MatrixFileUtilTest {

    @Test
    void getMatrixFromFile() throws IOException {
        MatrixFileUtil matrixFileUtil = new MatrixFileUtil();
        List<int[][]> matrixFromFile = matrixFileUtil.getMatrixFromFile("src/main/resources/matrix.txt");

        for (int i = 0; i < matrixFromFile.get(0).length; i++) {
            System.out.println(Arrays.toString(matrixFromFile.get(0)[i]));
        }
        System.out.println();
        for (int i = 0; i < matrixFromFile.get(1).length; i++) {
            System.out.println(Arrays.toString(matrixFromFile.get(1)[i]));
        }

        assertEquals(4, matrixFromFile.get(0).length);
        assertEquals(4, matrixFromFile.get(1).length);
    }

    @Test
    void handleMatrix() throws InterruptedException, ExecutionException, IOException {
        MatrixFileUtil matrixFileUtil = new MatrixFileUtil();
        matrixFileUtil.handleMatrix("src/main/resources/matrix.txt", "src/main/resources/matrixResult.txt");
        assertArrayEquals(Files.readAllBytes(new File("src/main/resources/matrixExpected.txt").toPath()), Files.readAllBytes(new File("src/main/resources/matrixResult.txt").toPath()));
    }
}