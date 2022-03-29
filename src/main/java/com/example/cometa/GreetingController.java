package com.example.cometa;

import com.example.cometa.domain.Color;
import com.example.cometa.repos.ColorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private ColorRepo colorRepo;
    private double value;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name,
                           Map<String, Object> model) {
        model.put("name", name);

        return "greeting";
    }
    @GetMapping
    public String main(Map<String, Object> model){
        Iterable<Color> colors = colorRepo.findAll();
        model.put("colors",colors);
        return "main";
    }

    @PostMapping("add")
    public String add(@RequestParam Integer numberColor,
                      @RequestParam String coatingType,
                      @RequestParam String manufact,
                      @RequestParam double value,
                      Map<String, Object> model){
        Color color = new Color(numberColor, coatingType, manufact, value );
        colorRepo.save(color);
        Iterable<Color> colors = colorRepo.findAll();
        model.put("colors",colors);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam(required=false) Integer filter, Map<String, Object> model){
        Iterable<Color> colors;
        if (filter != null){
            colors = colorRepo.findByNumberColor(filter);
        } else {
            colors = colorRepo.findAll();
        }

        model.put("colors",colors);
        return "main";
    }
    @GetMapping("delete")
    String delete(@RequestParam (name="id", required = false) int id,Map<String,Object> model){
        if (colorRepo.findById(id) != null){
            colorRepo.deleteById(id);
        }
        Iterable<Color> colors = colorRepo.findAll();
        model.put("colors",colors);
        return "main";
    }

    @GetMapping("update")
    String update(@RequestParam int id, @RequestParam double value, Map<String,Object> model){
        Color color = colorRepo.findById(id);
        color.setValue(value);
        Iterable<Color> colors = colorRepo.findAll();
        colorRepo.save(color);
        model.put("colors",colors);
        return "main";
    }
}
