package vn.edu.ptit.kttk.catalog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.FileUtils;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class OnDeviceStorageService implements StorageService {
    private final Path rootDirectory = Paths.get("./src/main/webapp");

    @Override
    public String saveFile(MultipartFile file) {
        String fileName = null;
        do {
            fileName = UUID.randomUUID() + FileUtils.getFileExtension(file);
        } while (Files.exists(rootDirectory.resolve(fileName)));

        try {
            Files.copy(file.getInputStream(), rootDirectory.resolve(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "/" + fileName;
    }

    @Override
    public void deleteFile(String path) {

    }
}
