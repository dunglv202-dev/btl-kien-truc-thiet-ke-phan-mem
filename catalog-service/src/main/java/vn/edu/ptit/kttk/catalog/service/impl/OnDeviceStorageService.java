package vn.edu.ptit.kttk.catalog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.Objects;
import java.util.UUID;

@Service
public class OnDeviceStorageService implements StorageService {
    @Override
    public String storeFile(MultipartFile file) {
        return UUID.randomUUID() + getFileExtension(file);
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
