package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody() Movie movie){

        movieService.addMovieService(movie);
        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }
    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody() Director director){

        movieService.addDirectorService(director);
        return new ResponseEntity<>("Director added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("director") String directorName, @RequestParam("movie") String movieName){

        movieService.addMovieDirectorPairService(directorName,movieName);
        return new ResponseEntity<>("Movie-Director Pair added successfully", HttpStatus.OK);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){

        Movie requestedMovie = movieService.getMovieByNameService(movieName);
        if(requestedMovie == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(requestedMovie, HttpStatus.OK);
        }
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){

        Director requestedDirector = movieService.getDirectorByNameService(directorName);
        if (requestedDirector == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(requestedDirector, HttpStatus.OK);
        }
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName){

        List<String> movieList = movieService.getAllMoviesByDirectorService(directorName);
        if (movieList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(movieList, HttpStatus.FOUND);
        }
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies(){

        List<Movie> movieList = movieService.getAllMoviesService();
        if (movieList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(movieList, HttpStatus.FOUND);
        }
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String directorName){

        movieService.deleteMovieByDirectorService(directorName);
        return new ResponseEntity<>("Director and his movies have been deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){ // should not delete movies not mapped to any director

        movieService.deleteAllDirectorsFromDB();
        return new ResponseEntity<>("All Directors and their movies have been deleted successfully", HttpStatus.OK);
    }
}
