package com.example.cometa.service;

import com.example.cometa.domain.robcom.Defect;
import com.example.cometa.domain.robcom.DefectCorrection;
import com.example.cometa.domain.robcom.Product;
import com.example.cometa.domain.techstcom.Machine;
import com.example.cometa.repos.DefectCorrectionRepo;
import com.example.cometa.repos.DefectRepo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import com.example.cometa.repos.techstcom.MachineRepo;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;

@Service
public class ImageService {
    @Value("${upload.path}")
    String uploadPath;
    @Autowired
    DefectRepo defectRepo;
    @Autowired
    MachineRepo machineRepo;

    @Autowired
    DefectCorrectionRepo defectCorrectionRepo;

    public Defect deleteImage(Defect defect, String address) {
        File file = new File(this.uploadPath + "/" + defect.getProduct().getName() + "__" + defect.getProduct().getUUID() + "/" + defect.getId() + "/" + address);
        file.delete();
        List<String> setOfImages = defect.getDefectImages();
        setOfImages.remove(address);
        defect.setDefectImages(setOfImages);
        return defect;
    }

    public DefectCorrection deleteImage(DefectCorrection defectCorrection, String address) {
        File file = new File(uploadPath
                + "/" + defectCorrection.getDefect().getProduct().getName()
                + "__" + defectCorrection.getDefect().getProduct().getUUID()
                + "/" + defectCorrection.getDefect().getId()
                + "/" + defectCorrection.getId()
                + "/" + address);

        file.delete();
        List<String> setOfImages = defectCorrection.getImageCorrections();
        setOfImages.remove(address);
        defectCorrection.setImageCorrections(setOfImages);
        return defectCorrection;
    }

    public void deleteAllDefectImages(Defect defect) throws IOException {
        File defectDirectory = new File(uploadPath + "/" + defect.getProduct().getName() + "__" + defect.getProduct().getUUID() + "/" + defect.getId());
        FileUtils.deleteDirectory(defectDirectory);

        if (defect.getDefectCorrection() != null) {
            List<String> correctImages = defect.getDefectCorrection().getImageCorrections();
            Iterator var4 = correctImages.iterator();

            while(var4.hasNext()) {
                String correctImage = (String)var4.next();
                File file = new File(this.uploadPath + "/" + correctImage);
                file.delete();
            }
        }

    }

    public Defect addDefectImages(Defect defect, MultipartFile[] files) throws IOException {
        defectRepo.save(defect);
        List<String> setOfImages = new ArrayList<>();
        if (defect.getDefectImages() != null) {
            setOfImages = defect.getDefectImages();
        }


        String defectDirectory = uploadPath + "/" + defect.getProduct().getName() + "__" + defect.getProduct().getUUID() + "/" + defect.getId();

        for (MultipartFile file : files) {

            if (file != null && !file.getOriginalFilename().isEmpty()) {

                File uploadDir = new File(defectDirectory);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                (setOfImages).add(resultFileName);
                file.transferTo(new File(defectDirectory + "/" + resultFileName));
                resizeImages(defectDirectory + "/" + resultFileName);
            }
        }

        defect.setDefectImages(setOfImages);
        return defect;
    }

    public DefectCorrection addCorrectionImages(DefectCorrection defectCorrection, MultipartFile[] files) throws IOException {
        defectCorrectionRepo.save(defectCorrection);
        List<String> setOfImages = new ArrayList<>();
        if (defectCorrection.getImageCorrections() != null) {
            setOfImages = defectCorrection.getImageCorrections();
        }


        String defectCorrectionDirectory = uploadPath
                + "/" + defectCorrection.getDefect().getProduct().getName()
                + "__" + defectCorrection.getDefect().getProduct().getUUID()
                + "/" + defectCorrection.getDefect().getId()
                + "/" + defectCorrection.getId();

        for (MultipartFile file : files) {

            if (file != null && !file.getOriginalFilename().isEmpty()) {

                File uploadDir = new File(defectCorrectionDirectory);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                (setOfImages).add(resultFileName);
                file.transferTo(new File(defectCorrectionDirectory + "/" + resultFileName));
                resizeImages(defectCorrectionDirectory + "/" + resultFileName);
            }
        }

        defectCorrection.setImageCorrections(setOfImages);
        return defectCorrection;
    }

    public Product addProductImage(Product product, MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            String uuidFolder = UUID.randomUUID().toString();
            String productDirectory = uploadPath + "/" + product.getName() + "__" + uuidFolder;
            File uploadDir = new File(productDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(productDirectory + "/" + resultFileName));
            resizeImages(productDirectory + "/" + resultFileName);
            product.setUUID(uuidFolder);
            product.setFilename(resultFileName);
        }

        return product;
    }

    public Machine addMachineImage(Machine machine, MultipartFile file) throws IOException {
        machineRepo.save(machine);
        if (file != null && !file.getOriginalFilename().isEmpty()) {

            String machineDirectory = uploadPath + "/" + machine.getName() + "__" + machine.getId();
            File uploadDir = new File(machineDirectory);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(machineDirectory + "/" + resultFileName));
            resizeImages(machineDirectory + "/" + resultFileName);
            machine.setImageAddress(resultFileName);
        }

        return machine;
    }



    private void resizeImages(String address) throws IOException {

        BufferedImage bufferedImageInput = Thumbnails.of(new File(address)).scale(1).asBufferedImage();
        int imgHeight = bufferedImageInput.getHeight();
        int imgWidth = bufferedImageInput.getWidth();
        double ratio;
        double maxWidth = 1200;
        double maxHeight = 1600;
        if (imgWidth > imgHeight)
        {
            ratio = maxWidth / imgWidth;
        } else
        {
            ratio = maxHeight / imgHeight;
        }
        imgWidth = (int)(ratio * imgWidth);
        imgHeight = (int)(ratio * imgHeight);

        Image scalesImage = bufferedImageInput.getScaledInstance(imgWidth,imgHeight, Image.SCALE_DEFAULT);
        BufferedImage bufferedImageOutput = new BufferedImage(scalesImage.getWidth(null), scalesImage.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics2D = bufferedImageOutput.createGraphics();
        graphics2D.drawImage(scalesImage, 0, 0, null);
        graphics2D.dispose();

        ImageIO.write(bufferedImageOutput, "jpg", new File(address));


    }
}
