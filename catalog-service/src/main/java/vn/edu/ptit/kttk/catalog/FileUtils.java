package vn.edu.ptit.kttk.catalog;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class FileUtils {
    public static String getFileExtension(MultipartFile file) {
        String name = Objects.requireNonNullElse(file.getOriginalFilename(), "");
        int dotIndex = name.lastIndexOf(".");
        if (dotIndex < 0) {
            throw new RuntimeException("Invalid file");
        }
        return name.substring(dotIndex);
    }
}
