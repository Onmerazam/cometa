package com.example.cometa.service;


import com.example.cometa.domain.Defect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${upload.path}")
    String uploadPath;

    public Defect deleteDefectImage (Defect defect, String address){
        File file = new File(uploadPath + "/" + address);
        file.delete();
        Set<String> defectImages = defect.getDefectImages();
        defectImages.remove(address);
        defect.setDefectImages(defectImages);
        return defect;
    }

    public void deleteAllDefectImagesFromStorage (Defect defect){

        Set<String> defectImages = defect.getDefectImages();
        for (String defectImage : defectImages){
            File file = new File(uploadPath + "/" + defectImage);
            file.delete();
        }
    }

    public Set<String> addDefectImages (MultipartFile files[]) throws IOException {
        Set<String> defectImages = new HashSet<>();
        for (MultipartFile file : files){
            if (file != null && !file.getOriginalFilename().isEmpty()){
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()){
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                defectImages.add(resultFileName);
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            }
        }
        return defectImages;
    }

    public Set<String> addDefectImages (Set<String> defectImages, MultipartFile files[]) throws IOException {

        for (MultipartFile file : files){
            if (file != null && !file.getOriginalFilename().isEmpty()){
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()){
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                defectImages.add(resultFileName);
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            }
        }
        return defectImages;
    }

}
