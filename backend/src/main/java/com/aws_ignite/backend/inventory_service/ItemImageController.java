package com.aws_ignite.backend.inventory_service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;



@RestController
@RequestMapping(value = "/images")
public class ItemImageController {
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename) throws IOException {
        var imgFile = new ClassPathResource("images/" + filename);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
        return ResponseEntity
        .ok()
        // .contentType(MediaType.IMAGE_JPEG)
        .body(bytes);
    }
}
