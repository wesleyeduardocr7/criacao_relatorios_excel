package aplicacao.utils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class QueryUtils {
    public static String getQuery(String nomeArquivoDaQuery) throws IOException {
        String path = "src/main/resources/dao/".concat(nomeArquivoDaQuery);
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
