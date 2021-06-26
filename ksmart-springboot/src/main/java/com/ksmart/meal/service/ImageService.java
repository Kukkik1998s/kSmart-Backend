package com.ksmart.meal.service;


import com.ksmart.meal.model.Image;
import com.ksmart.meal.repository.ImageRepository;
import org.apache.commons.io.IOUtils;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    private String ref;

    public void getRef(String ref){
        this.ref = ref;
    }

    public String addImage(String name, MultipartFile file, Date update) throws IOException {
        Image image = new Image(ref);
        image.setName(name);
        image.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image.setLatestUpdate(update);
        image = imageRepository.insert(image); return image.getId();
    }

    public String updateImage(String ref, MultipartFile file) throws IOException {
        Image image = imageRepository.findByRef(ref).get();
        image.setImage( new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image.setLatestUpdate(new Date());
        imageRepository.save(image);
        return "updated image success";
    }

    public  Image getImage(String id){
        return  imageRepository.findById(id).get();
    }

    public List<Image> getAllImage(){
        return  imageRepository.findAll();
    }

    public Binary findByRef(String ref) throws IOException, ParseException {
        Image image = imageRepository.findByRef(ref).get();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2021-04-19");
        if (image.getLatestUpdate()!=null && image.getLatestUpdate().compareTo(date) > 0) {
            return imageRepository.findByRef(ref).get().getImage();
        } else {
            InputStream in = getClass()
                    .getResourceAsStream("/static/img/no-image.png");
            return new Binary(BsonBinarySubType.BINARY, IOUtils.toByteArray(in));
        }
    }



}
