package vn.edu.ptit.kttk.catalog.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String saveFile(MultipartFile file);
    void deleteFile(String path);
}
