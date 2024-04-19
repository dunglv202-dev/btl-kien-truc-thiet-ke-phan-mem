package vn.edu.ptit.kttk.catalog.service;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.ptit.kttk.catalog.dto.FoodUpdate;
import vn.edu.ptit.kttk.catalog.entity.Food;
import vn.edu.ptit.kttk.catalog.entity.Image;

import java.util.List;

public interface FoodImageService
{
    void addImages(Food food, List<MultipartFile> added);
    void updateImagesAndPreview(Food food, FoodUpdate foodUpdate);
    void removeImages(List<Image> removed);
}
