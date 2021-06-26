package com.ksmart.medicine;


import com.ksmart.meal.model.Food;
import com.ksmart.medicine.model.Medicine;
import com.ksmart.medicine.repository.MedicineRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/*@Service
public class MedService {

    @Autowired
    MedicineRepository medRepo;

    public String addMedImage(Medicine med, MultipartFile file) throws IOException {
        med.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        medRepo.save(med);
        return "success add medicine";
    }

    public String updateImage(Medicine med, MultipartFile image) throws IOException{
        med.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        medRepo.save(med);
        return "success";
    }

    public Optional<Medicine> findById(String id) {
        return medRepo.findById(id);
    }

    public List<Medicine> medList(){
        return medRepo.findAll();
    }

}*/
