package com.ksmart.meal.controller;

import com.ksmart.authen.payload.response.MessageResponse;
import com.ksmart.meal.model.Image;
import com.ksmart.meal.payload.ImageRequest;
import com.ksmart.meal.repository.ImageRepository;
import com.ksmart.meal.service.ImageService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {


    @Autowired
    ImageService imageService;


    @PostMapping("/add")
    public ResponseEntity<?> addImage(@Valid @RequestParam("name") String name,
                                      @RequestParam("image") MultipartFile image) throws IOException {
        String id = imageService.addImage(name,image,new Date());

        return ResponseEntity.ok(new MessageResponse(id));
    }


    @GetMapping(value = "/get/ref/{ref}",
            produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE})
    public byte[] getImageByRef(@Valid @PathVariable("ref") String ref) throws IOException, ParseException {
        Binary data = imageService.findByRef(ref);
        //byte[] temp = Base64.getDecoder().decode(data.getData());
        //return Base64.getDecoder().decode(data.getData());
        return data.getData();
    }

    @GetMapping("/get")
    public List<Image> getImages(){
        return imageService.getAllImage();
    }

    @PutMapping("/update/{ref}")
    public  ResponseEntity<?> updateImage(@PathVariable("ref") String ref, @RequestParam MultipartFile image) throws IOException {
        String message = imageService.updateImage(ref,image);
        return ResponseEntity.ok(new MessageResponse(message));
    }


}
