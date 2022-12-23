package com.example.cometa.controller;

import com.example.cometa.domain.robcom.CorrectStatus;
import com.example.cometa.domain.robcom.Defect;
import com.example.cometa.domain.robcom.DefectCorrection;
import com.example.cometa.repos.DefectCorrectionRepo;
import com.example.cometa.repos.DefectRepo;
import com.example.cometa.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
public class DefectCorrectionController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private DefectRepo defectRepo;

    @Autowired
    ImageService imageService;

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
        DefectCorrection defectCorrection = new DefectCorrection(message, culprit, defect);
        defectCorrection = imageService.addCorrectionImages(defectCorrection, files);
        defectCorrection.setStatus(CorrectStatus.REJECTED);
        defectCorrectionRepo.save(defectCorrection);
        return "redirect:/products/{product}/{defect}";
    }

    @PostMapping("/products/{product}/{defect}/addCorrectionImage")
    public String addCorrectionImage(@PathVariable Defect defect,
                                  @RequestParam("file_1") MultipartFile files[],
                                  Map<String, Object> model) throws IOException {
        DefectCorrection defectCorrection = imageService.addCorrectionImages(defect.getDefectCorrection(), files);
        defectCorrectionRepo.save(defectCorrection);
        return "redirect:/products/{product}/{defect}";
    }

    @PostMapping("/products/{product}/{defect}/changeDefectCorrectionStatus/{status}")
    public String changeDefectCorrectionStatus(@PathVariable Defect defect,
                                               @PathVariable Integer status,
                                               @RequestParam (required = false) String dtkComment){
        defect.getDefectCorrection().setStatus(CorrectStatus.values()[status]);
        defect.getDefectCorrection().setDtkComment(dtkComment);
        defectRepo.save(defect);
        return "redirect:/products/{product}/{defect}";
    }

    @GetMapping("/products/{product}/{defect}/deleteImgCorrection/{address}")
    public String deleteImgCorrection(@PathVariable String address,
                                      @PathVariable Defect defect){

        DefectCorrection defectCorrection = imageService.deleteImage(defect.getDefectCorrection(), address);
        defectCorrectionRepo.save(defectCorrection);
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
