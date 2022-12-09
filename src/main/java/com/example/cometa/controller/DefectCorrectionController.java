package com.example.cometa.controller;

import com.example.cometa.domain.CorrectStatus;
import com.example.cometa.domain.Defect;
import com.example.cometa.domain.DefectCorrection;
import com.example.cometa.repos.DefectCorrectionRepo;
import com.example.cometa.repos.DefectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class DefectCorrectionController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private DefectRepo defectRepo;

    @Autowired
    private DefectCorrectionRepo defectCorrectionRepo;

    @PostMapping("/products/{product}/{defect}/addDefectCorrection")
    public String addDefectCorrection(@PathVariable Defect defect,
                                      @RequestParam("file") MultipartFile files[],
                                      @RequestParam String culprit,
                                      @RequestParam String message) throws IOException {
        if (defectCorrectionRepo.findByDefectId(defect.getId()) != null){
            return "redirect:/products/{product}/{defect}";
        }
        Set<String> imageCorrections = new HashSet<>();
        for (MultipartFile file : files) {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                imageCorrections.add(resultFileName);
                file.transferTo(new File(uploadPath + "/" + resultFileName));

            }
        }
        DefectCorrection defectCorrection = new DefectCorrection(message, culprit, defect, imageCorrections);
        defectCorrection.setStatus(CorrectStatus.REJECTED);
        defectCorrectionRepo.save(defectCorrection);
        return "redirect:/products/{product}/{defect}";
    }

    @PostMapping("/products/{product}/{defect}/addCorrectionImage")
    public String addCorrectionImage(@PathVariable Defect defect,
                                  @RequestParam("file_1") MultipartFile files[],
                                  Map<String, Object> model) throws IOException {
        Set<String> imageCorrections = new HashSet<>();
        imageCorrections = defect.getDefectCorrection().getImageCorrections();
        for (MultipartFile file : files) {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                imageCorrections.add(resultFileName);
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            }
        }
        defect.getDefectCorrection().setImageCorrections(imageCorrections);
        defectRepo.save(defect);

        return "redirect:/products/{product}/{defect}";
    }

    @GetMapping("/products/{product}/{defect}/changeDefectCorrectionStatus/{status}")
    public String changeDefectCorrectionStatus(@PathVariable Defect defect,
                                               @PathVariable Integer status){
        switch (status){
            case 0:
                defect.getDefectCorrection().setStatus(CorrectStatus.ACCEPTED);
                break;
            case 1:
                defect.getDefectCorrection().setStatus(CorrectStatus.PENDING);
                break;
            case 2:
                defect.getDefectCorrection().setStatus(CorrectStatus.REJECTED);
                break;
        }
        defectRepo.save(defect);
        return "redirect:/products/{product}/{defect}";
    }

    @GetMapping("/products/{product}/{defect}/deleteImgCorrection/{address}")
    public String deleteImgCorrection(@PathVariable String address,
                                      @PathVariable Defect defect){

        File file = new File(uploadPath + "/" + address);
        file.delete();
        Set<String> imageCorrections = new HashSet<>();
        imageCorrections = defect.getDefectCorrection().getImageCorrections();
        imageCorrections.remove(address);
        defect.getDefectCorrection().setImageCorrections(imageCorrections);
        defectRepo.save(defect);
        return "redirect:/products/{product}/{defect}";
    }


    @PostMapping("/products/{product}/{defect}/updateCorrection")
    public String updateCorrection(@PathVariable Defect defect,
                               @RequestParam(name = "culprit") String culprit,
                               @RequestParam(name = "message") String message){

        defect.getDefectCorrection().setCulprit(culprit);
        defect.getDefectCorrection().setMessage(message);
        defectRepo.save(defect);
        return "redirect:/products/{product}/{defect}";
    }
}
