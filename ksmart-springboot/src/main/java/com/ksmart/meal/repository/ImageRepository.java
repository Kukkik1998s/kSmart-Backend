package com.ksmart.meal.repository;

import com.ksmart.meal.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ImageRepository extends MongoRepository<Image, String> {
    Optional<Image> findByRef(String ref);
}
