package com.arpit.SpringMovies.Service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arpit.SpringMovies.Model.Movie;
import com.arpit.SpringMovies.Repo.MovieRepo;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepo movieRepository;

    public List<Movie> allMovies(){
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovie(ObjectId id) {
        return movieRepository.findById(id);
    }

    //Optional--> if movie does'nt exist
    public Optional<Movie> findMovieByImdbId(String imdbId) {
        return movieRepository.findMovieByImdbId(imdbId);
    }

}
