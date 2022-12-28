package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    void addMovieService(Movie movie){
        movieRepository.addMovieToDB(movie);
    }

    void addDirectorService(Director director){
        movieRepository.addDirectorToDB(director);
    }

    void addMovieDirectorPairService(String directorName, String movieName){
        movieRepository.addMovieDirectorPairToDB(directorName, movieName);
    }

    Movie getMovieByNameService(String movieName){
        return movieRepository.getMovieByNameFromDB(movieName);
    }

    Director getDirectorByNameService(String directorName){
        return movieRepository.getDirectorByNameFromDB(directorName);
    }

    List<String> getAllMoviesByDirectorService(String directorName){
        return movieRepository.getAllMoviesByDirectorFromDB(directorName);
    }

    List<Movie> getAllMoviesService(){
        return movieRepository.getAllMoviesFromDB();
    }

    void deleteMovieByDirectorService(String directorName){
        movieRepository.deleteMovieByDirectorFromDB(directorName);
    }

    void deleteAllDirectorsFromDB(){
        movieRepository.deleteAllDirectorsFromDB();
    }
}
