package vn.edu.ptit.kttk.catalog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.service.StorageService;

@Service
public class OnDeviceStorageService implements StorageService {
    @Override
    public String storeFile(MultipartFile file) {
        return file.getOriginalFilename();
    }
}
