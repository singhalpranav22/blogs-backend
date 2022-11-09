package com.pranav.blog.services.impl;

import com.pranav.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        // upload file to the filePath
        // return the file name
        String name = file.getOriginalFilename();
        // xyz.png

        // random name generation of the file
        String randomId = UUID.randomUUID().toString();
        assert name != null;
        String fileName1 = randomId.concat(name.substring(name.lastIndexOf('.')));
        // 1234-1234-1234-1234.png

        // FUll path here
        String filePath = path + File.separator + fileName1;
        File f = new File(path);
         if(!f.exists()){
            f.mkdir();
        }
         // File copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
         return fileName1;

    }

    @Override
    public InputStream downloadFile(String path,String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
