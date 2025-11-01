package testUtils;

import java.io.IOException;
import java.nio.file.*;

public final class FileUtils {

    private static final String DEFAULT_UPLOAD_DIR = "src/test/resources/testdata/upload";

    private FileUtils() {}

    public static String getOrCreateUploadFile(String fileName, String content) {
        try {
            Path dir = Paths.get(DEFAULT_UPLOAD_DIR).toAbsolutePath();
            Files.createDirectories(dir);

            Path filePath = dir.resolve(fileName);
            if (Files.notExists(filePath)) {
                Files.writeString(filePath, content);
            }

            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to prepare upload file: " + fileName, e);
        }
    }
}