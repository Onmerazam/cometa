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

    public Set<String> deleteImage (Set<String> setOfImages, String address){
        File file = new File(uploadPath + "/" + address);
        file.delete();
        setOfImages.remove(address);
        return setOfImages;
    }

    public void deleteAllDefectImages (Defect defect){

        Set<String> defectImages = defect.getDefectImages();
        Set<String> correctImages = defect.getDefectCorrection().getImageCorrections();
        for (String defectImage : defectImages){
            File file = new File(uploadPath + "/" + defectImage);
            file.delete();
        }
        for (String correctImage : correctImages){
            File file = new File(uploadPath + "/" + correctImage);
            file.delete();
        }

    }

    public Set<String> addImages (MultipartFile files[]) throws IOException {
        Set<String> setOfImages = new HashSet<>();
        for (MultipartFile file : files){
            if (file != null && !file.getOriginalFilename().isEmpty()){
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()){
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                setOfImages.add(resultFileName);
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            }
        }
        return setOfImages;
    }

    public Set<String> addImages (Set<String> setOfImages, MultipartFile files[]) throws IOException {

        for (MultipartFile file : files){
            if (file != null && !file.getOriginalFilename().isEmpty()){
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()){
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                setOfImages.add(resultFileName);
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            }
        }
        return setOfImages;
    }

}
