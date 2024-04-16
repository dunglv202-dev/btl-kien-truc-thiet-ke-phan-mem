package vn.edu.ptit.kttk.catalog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.FileUtils;
import vn.edu.ptit.kttk.catalog.service.StorageService;

import java.util.UUID;

@Service
public class OnDeviceStorageService implements StorageService {
    @Override
    public String saveFile(MultipartFile file) {
        return "/" + UUID.randomUUID() + FileUtils.getFileExtension(file);
    }
}
