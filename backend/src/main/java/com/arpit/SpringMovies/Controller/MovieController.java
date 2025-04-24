package com.arpit.SpringMovies.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.arpit.SpringMovies.Service.MovieService;
import com.arpit.SpringMovies.Model.Movie;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }


    //FindingMovieBy Object Id
    @GetMapping("/api/v1/movies/{MovieId}")
    public  ResponseEntity<Optional<Movie>> getMovieById(@PathVariable ObjectId MovieId){
        return new ResponseEntity<Optional<Movie>>(movieService.getMovie(MovieId), HttpStatus.OK);
    }


    //FindingMovieBy Imdb Id
    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable String imdbId){
        return new ResponseEntity<Optional<Movie>>(movieService.findMovieByImdbId(imdbId), HttpStatus.OK);
    }
}
