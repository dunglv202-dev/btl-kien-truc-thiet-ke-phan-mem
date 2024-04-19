package vn.edu.ptit.kttk.catalog.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.entity.Food;

import java.util.List;

public interface FoodImageService
{
    void addImages(Food food, List<MultipartFile> added);
}
