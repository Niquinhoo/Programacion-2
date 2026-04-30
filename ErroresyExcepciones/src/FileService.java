

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileService {
    public void readPeopleFile(String filePath) {
        try (BufferedReader br = Files.newBufferedReader(Path.of(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("[ERROR] No se pudo procesar archivo: " + e.getMessage());
        }
    }
}
