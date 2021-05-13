package main.scala.me.decentos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class MatrixFileGenerator {

    private final MatrixCalculator matrixCalculator;

    public MatrixFileGenerator(MatrixCalculator matrixCalculator) {
        this.matrixCalculator = matrixCalculator;
    }

    public void generate(String fileName, byte size) throws IOException {
        String filePath = "src/main/resources/" + fileName + ".txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        SecureRandom secureRandom = new SecureRandom();

        writerHelper(size, writer, secureRandom);
        writer.newLine();
        writerHelper(size, writer, secureRandom);

        writer.flush();
        matrixCalculator.sum(fileName);
    }

    private void writerHelper(byte size, BufferedWriter bw, SecureRandom secureRandom) throws IOException {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bw.write(secureRandom.nextInt() + "");
                if (j != size - 1) bw.write(" ");
            }
            bw.newLine();
        }
    }
}
