package com.arpit.SpringMovies.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.arpit.SpringMovies.Model.Movie;
import com.arpit.SpringMovies.Model.Review;
import com.arpit.SpringMovies.Repo.ReviewRepo;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepo repo;

    @Autowired
    private MongoTemplate temp;

    public Review createReview(String reviewBody, String imdbId){
        Review review = repo.insert(new Review(reviewBody, LocalDateTime.now(), LocalDateTime.now()));

        temp.update(Movie.class)
            .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review.getId()))
                .first();
        return review;
    }
}
