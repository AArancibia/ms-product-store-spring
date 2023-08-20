package com.bodega.api.ui.controller;

import com.bodega.api.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("files")
public class FileController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping
    public void uploadFile(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        /*log.info("Bytes: " + multipartFile.getBytes());
        log.info("Name: " + multipartFile.getName());
        log.info("OriginalFileName: " + multipartFile.getOriginalFilename());
        log.info("Size: " + multipartFile.getSize());
        log.info("InputStream: " + multipartFile.getInputStream());*/
        fileUploadService.listAllBlobs();
    }
}
