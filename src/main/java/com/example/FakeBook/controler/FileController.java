package com.example.FakeBook.controler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileController {

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/addimage")
    public String addImg(){
        return "addImg";
    }

    @PostMapping("/addimage")
    public String add(
            @RequestParam("file") MultipartFile file,
            Model model
    ) throws IOException {
        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists())
                uploadDir.mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File( uploadDir + "\\" + resultFilename));


            //model.addAttribute("filename", resultFilename);
            return "redirect:" + "/getImg" + "?filename=" + resultFilename;
        }
        return  "addImg";
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
