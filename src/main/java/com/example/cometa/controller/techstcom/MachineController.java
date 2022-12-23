package com.example.cometa.controller.techstcom;

import com.example.cometa.domain.User;
import com.example.cometa.domain.techstcom.Machine;
import com.example.cometa.repos.techstcom.MachineRepo;
import com.example.cometa.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class MachineController {

    @Autowired
    MachineRepo machineRepo;

    @Autowired
    ImageService imageService;

    @GetMapping("/machine")
    public String machine(@AuthenticationPrincipal User user,
                          Model model){
        Iterable<Machine> machines = machineRepo.findAll();
        model.addAttribute("machines", machines);
        model.addAttribute("user",user);
        return "techstcom/machine";
    }

    @PostMapping("/machine")
    public String addMachine (@RequestParam String name,
                               @RequestParam String category,
                               @RequestParam MultipartFile file) throws IOException {
        Machine machine = new Machine(name, category);
        machine = imageService.addMachineImage(machine, file);
        machineRepo.save(machine);
        return "redirect:/machine";
    }
}
