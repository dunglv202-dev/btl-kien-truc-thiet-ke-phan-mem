package vn.edu.ptit.kttk.catalog.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OnDeviceStorageService implements StorageService {
    private final Path rootDirectory = Paths.get("./src/main/webapp");

    @Override
    public String storeFile(MultipartFile file) {
        Path filePath;

        do {
            String fileName = UUID.randomUUID() + getFileExtension(file);
            filePath = rootDirectory.resolve(fileName);
        } while (Files.exists(filePath));

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return rootDirectory.relativize(filePath).toString();
    }

    private String getFileExtension(MultipartFile file) {
        String filename = Objects.requireNonNull(file.getOriginalFilename());
        int dotPos = filename.lastIndexOf(".");

        if (dotPos >= 0) {
            return filename.substring(dotPos);
        } else {
            throw new RuntimeException("Extension is required for file to store");
        }
    }
}
