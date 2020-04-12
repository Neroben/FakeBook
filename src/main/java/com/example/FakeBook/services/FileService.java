package com.example.FakeBook.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    public String add(MultipartFile file) throws IOException {
        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists())
                uploadDir.mkdir();

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File( uploadDir + "\\" + resultFilename));
            return resultFilename;
        }
        return "";
    }

}
