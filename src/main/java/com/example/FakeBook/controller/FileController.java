package com.example.FakeBook.controller;

import com.example.FakeBook.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/addimage")
    public String addImg(){
        return "addImg";
    }

    @PostMapping("/addimage")
    public String add(
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {
        String filename = fileService.add(file);
        if(filename.isEmpty())
            return  "addImg";
        else
            return "redirect:" + "/getImg" + "?filename=" + filename;
    }

    @GetMapping("/getImg")
    public String getImg(
            @RequestParam String filename,
            Model model
    ){
        model.addAttribute("filename", filename);
        return "getImg";
    }

}
