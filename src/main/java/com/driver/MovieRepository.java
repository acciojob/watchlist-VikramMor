package com.driver;


import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    HashMap<String, Movie> movieDB;

    HashMap<String, Director> directorDB;

    HashMap<Movie, Director> movieDirectorDB;

    void addMovieToDB(Movie movie){

        String movieName = movie.getName();
        movieDB.put(movieName, movie);
    }

    void addDirectorToDB(Director director){

        String directorName = director.getName();
        directorDB.put(directorName, director);
    }

    void addMovieDirectorPairToDB(String directorName, String movieName){

        if(directorDB.containsKey(directorName) && movieDB.containsKey(movieName)) {
            Movie movie = movieDB.get(movieName);
            movieDirectorDB.put(movie, directorDB.get(directorName));
        }
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

    List<Movie> getAllMoviesByDirectorFromDB(String directorName){
        ArrayList<Movie> movieList = new ArrayList<>();
        for(Map.Entry<Movie, Director> entry: movieDirectorDB.entrySet()){
            if(entry.getValue().getName().equals(directorName)){
                movieList.add(entry.getKey());
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
