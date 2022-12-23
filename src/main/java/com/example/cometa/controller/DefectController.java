package com.example.cometa.controller;

import com.example.cometa.domain.robcom.Defect;
import com.example.cometa.domain.robcom.Product;
import com.example.cometa.domain.User;
import com.example.cometa.repos.DefectRepo;
import com.example.cometa.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class DefectController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private DefectRepo defectRepo;

    @Autowired
    ImageService imageService;

    @PostMapping("/products/{product}")
    public String addDefect(@PathVariable Product product,
                            @RequestParam("file") MultipartFile files[],
                            String desc) throws IOException {
        Defect defect = new Defect(desc, product);
        defect = imageService.addDefectImages(defect, files);
        defectRepo.save(defect);
        return "redirect:/products/{product}";
    }

    @PostMapping("/products/{product}/{defect}")
    public String addDefectImages(@PathVariable Defect defect,
                         @RequestParam("file") MultipartFile files[]
                         ) throws IOException {


        defect = imageService.addDefectImages(defect, files);
        defectRepo.save(defect);
        return "redirect:/products/{product}/{defect}";
    }

    @GetMapping("/products/{product}/{defect}")
    public String defectView(@PathVariable Defect defect,
                             @AuthenticationPrincipal User user,
                           Model model){
        Defect def = defectRepo.getById(defect.getId());
        model.addAttribute("defect", def);
        model.addAttribute("user", user);
        return "defectView";
    }

    @GetMapping("/products/{product}/delete")
    public String defectDelete(@RequestParam (name="id", required = false) int id,
                               Model model) throws IOException {
        if (defectRepo.findById(id) != null){
            imageService.deleteAllDefectImages(defectRepo.findById(id));
            defectRepo.deleteById(id);
        }
        return "redirect:/products/{product}";
    }

    @PostMapping("/products/{product}/{defect}/updateDefect")
    public String updateDefect(@PathVariable Defect defect,
                               @RequestParam(name = "desc") String description){
        defect.setDescription(description);
        defectRepo.save(defect);
        return "redirect:/products/{product}/{defect}";
    }

    @GetMapping("/products/{product}/{defect}/deleteDefectImg/{address}")
    public String deleteImgCorrection(@PathVariable String address,
                                      @PathVariable Defect defect){

        defect = imageService.deleteImage(defect, address);
        defectRepo.save(defect);
        return "redirect:/products/{product}/{defect}";
    }


}

