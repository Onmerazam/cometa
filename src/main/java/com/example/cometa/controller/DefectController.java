package com.example.cometa.controller;

import com.example.cometa.domain.Defect;
import com.example.cometa.domain.DefectImage;
import com.example.cometa.domain.Product;
import com.example.cometa.repos.DefectImageRepo;
import com.example.cometa.repos.DefectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
public class DefectController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    DefectImageRepo defectImageRepo;

    @Autowired
    private DefectRepo defectRepo;

    @PostMapping("/products/{product}")
    public String addDefect(@PathVariable Product product,
                            @RequestParam("file") MultipartFile files[],
                            String name,
                            String desc,
                            Map<String, Object> model) throws IOException {
        Defect defect = new Defect(name,desc,product.getId());
        defectRepo.save(defect);
        for (MultipartFile file : files){
            if (file != null && !file.getOriginalFilename().isEmpty()){

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()){
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                DefectImage defectImage = new DefectImage(defect.getId(), resultFileName);
                defectImageRepo.save(defectImage);
                file.transferTo(new File(uploadPath + "/" + resultFileName));
            }
        }
        return "redirect:/products/{product}";
    }

    @GetMapping("/products/{product}/{defect}")
    public String defectView(@PathVariable Defect defect,
                           Model model){

        Defect def = defectRepo.getById(defect.getId());
        Iterable<DefectImage> defectImages = defectImageRepo.findByDefectid(defect.getId());

        model.addAttribute("defect", def);
        model.addAttribute("defectImages", defectImages);
        return "defectView";
    }
}
