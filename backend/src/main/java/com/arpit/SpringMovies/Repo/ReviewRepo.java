package com.arpit.SpringMovies.Repo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arpit.SpringMovies.Model.Review;

@Repository
public interface ReviewRepo extends MongoRepository<Review, ObjectId> {
    
}
