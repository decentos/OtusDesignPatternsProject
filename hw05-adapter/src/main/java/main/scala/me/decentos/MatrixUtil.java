package main.scala.me.decentos;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface MatrixUtil {

    void handleMatrix(String file, String resultFile) throws IOException, ExecutionException, InterruptedException;
}
