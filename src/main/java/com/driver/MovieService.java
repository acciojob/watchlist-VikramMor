package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovieToDB(Movie movie){
        movieRepository.addMovieInDB(movie);
    }

    public void addDirectorToDB(Director director){
        movieRepository.addDirectorInDB(director);
    }

    public void addMovieDirectorPairToDB(String movieName, String directorName){
        movieRepository.addMovieDirectorPairInDB(movieName, directorName);
    }

    public Movie getMovieByName(String name){
        return movieRepository.getMovieByNameFromDB(name);
    }

    public Director getDirectorByName(String name){
        return movieRepository.getDirectorByNameFromDB(name);
    }

    public String getDirectorByMovieName(String movie){
        return movieRepository.getDirectorByMovieFromDB(movie);
    }

    public List<String> findMoviesFromDirector(String director){
        return movieRepository.findMoviesFromDirector(director);
    }

    public List<String> findAllMovies(){
        return movieRepository.findAllMoviesFromDB();
    }

    public void deleteDirector(String director){
        movieRepository.deleteDirector(director);
    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirector();
    }

}