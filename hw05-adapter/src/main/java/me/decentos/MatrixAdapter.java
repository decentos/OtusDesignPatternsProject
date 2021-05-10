package me.decentos;

public class MatrixAdapter implements MatrixCalculator {

    private final MatrixUtil matrixUtil;

    public MatrixAdapter(MatrixUtil matrixUtil) {
        this.matrixUtil = matrixUtil;
    }

    public void sum(String fileName) {
        try {
            String filePath = "src/main/resources/" + fileName + ".txt";
            String fileResultPath = "src/main/resources/" + fileName + "Result.txt";
            matrixUtil.handleMatrix(filePath, fileResultPath);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
