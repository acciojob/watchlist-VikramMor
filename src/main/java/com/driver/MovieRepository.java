package com.driver;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieDB = new HashMap<>();

    HashMap<String, Director> directorDB = new HashMap<>();

    HashMap<Movie, Director> movieDirectorDB = new HashMap<>();

    void addMovieToDB(Movie movie){

        String movieName = movie.getName();
        movieDB.put(movieName, movie);
    }

    void addDirectorToDB(Director director){

        String directorName = director.getName();
        directorDB.put(directorName, director);
    }

    void addMovieDirectorPairToDB(String directorName, String movieName){

        Movie movie = movieDB.get(movieName);
        Director director = directorDB.get(directorName);
        movieDirectorDB.put(movie, director);
    }

    Movie getMovieByNameFromDB(String movieName){
        if(movieDB.containsKey(movieName)){
            return movieDB.get(movieName);
        }
        else{
            return null;
        }
    }

    Director getDirectorByNameFromDB(String directorName){
        if(directorDB.containsKey(directorName)){
            return directorDB.get(directorName);
        }
        else{
            return null;
        }
    }

    List<String> getAllMoviesByDirectorFromDB(String directorName){
        List<String> movieList = new ArrayList<>();
        for(Movie movie:movieDirectorDB.keySet()){
            if(movieDirectorDB.get(movie).getName().equals(directorName)){
                movieList.add((movie).getName());
            }
        }
        return movieList;
    }

    List<Movie> getAllMoviesFromDB(){
        ArrayList<Movie> movieList = new ArrayList<>();
        for(Map.Entry<String, Movie> entry: movieDB.entrySet()){
            movieList.add(entry.getValue());
        }
        return movieList;
    }

    void deleteMovieByDirectorFromDB(String directorName){

        for(Map.Entry<Movie, Director> entry: movieDirectorDB.entrySet()){
            if(entry.getValue().getName().equals(directorName)){
                movieDB.remove(entry.getKey().getName());
                movieDirectorDB.remove(entry.getKey());
            }
        }
    }

    void deleteAllDirectorsFromDB(){
        for(Map.Entry<Movie, Director> entry: movieDirectorDB.entrySet()){
            movieDB.remove(entry.getKey().getName());
            directorDB.clear();
            movieDirectorDB.remove(entry.getKey());
        }
    }
}
