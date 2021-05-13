package main.scala.me.decentos;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MatrixFileUtil implements MatrixUtil {

    public void handleMatrix(String file, String resultFile) throws IOException, ExecutionException, InterruptedException {
        List<int[][]> matrixFromFile = getMatrixFromFile(file);
        MatrixHandler matrixHandler = new MatrixHandler();
        long[][] multiplication = matrixHandler.multiplication(matrixFromFile.get(0), matrixFromFile.get(1));
        saveMatrixToFile(resultFile, multiplication);
    }

    protected List<int[][]> getMatrixFromFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        List<String> lines = new ArrayList<String>();
        while (br.ready()) {
            lines.add(br.readLine());
        }

        int indexOfEmptyLine = 0;

        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).length() == 0) {
                indexOfEmptyLine = i;
                break;
            }
        }

        int matrixWidth = lines.get(0).split(" ").length;
        int matrixHeight = lines.size();

        int[][] matrixA = new int[indexOfEmptyLine][matrixWidth];
        int[][] matrixB = new int[indexOfEmptyLine][matrixWidth];

        for (int i = 0; i < indexOfEmptyLine; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                String[] line = lines.get(i).split(" ");
                matrixA[i][j] = Integer.parseInt(line[j]);
            }
        }

        for (int i = indexOfEmptyLine + 1; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                String[] line = lines.get(i).split(" ");
                matrixB[i - indexOfEmptyLine - 1][j] = Integer.parseInt(line[j]);
            }
        }

        return Arrays.asList(matrixA, matrixB);
    }

    protected void saveMatrixToFile(String path, long[][] matrix) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));

        for (long[] longs : matrix) {
            for (int j = 0; j < longs.length; j++) {
                bw.write(longs[j] + "");
                if (j != longs.length - 1) bw.write(" ");
            }
            bw.newLine();
        }
        bw.flush();
    }
}
