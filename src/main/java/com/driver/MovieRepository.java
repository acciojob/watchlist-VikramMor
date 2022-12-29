package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
@Component
public class MovieRepository {
    HashMap<String, Movie> movieHashMap = new HashMap<>();
    HashMap<String, Director> directorHashMap = new HashMap<>();
    HashMap<String, List<String>> pairHashMap = new HashMap<>();

    public void addMovieInDB(Movie movie){
        movieHashMap.put(movie.getName(), movie);
    }

    public void addDirectorInDB(Director director){
        directorHashMap.put(director.getName(), director);
    }

    public void addMovieDirectorPairInDB(String movieName, String directorName){
        if(pairHashMap.containsKey(directorName)){
            pairHashMap.get(directorName).add(movieName);
        }else{
            List<String> list = new ArrayList<>();
            list.add(movieName);
            pairHashMap.put(directorName, list);
        }
        System.out.println("Pair added");
    }

    public Movie getMovieByNameFromDB(String name){
        return movieHashMap.get(name);
    }

    public Director getDirectorByNameFromDB(String name){
        return directorHashMap.get(name);
    }

    public List<String> findMoviesFromDirector(String director){
        List<String> moviesList = new ArrayList<>();
        if(pairHashMap.containsKey(director)){
            moviesList = pairHashMap.get(director);
        }
        return moviesList;
    }

    public List<String> findAllMoviesFromDB(){
        List<String> movieList = new ArrayList<>();
        for(String movieName: movieHashMap.keySet()){
            movieList.add(movieName);
        }

        return movieList;
    }

    public void deleteDirector(String director){
        List<String> movies = new ArrayList<>();
        if(pairHashMap.containsKey(director)){
            movies = pairHashMap.get(director);
            for(String movie: movies){
                if(movieHashMap.containsKey(movie)){
                    movieHashMap.remove(movie);
                }
            }

            pairHashMap.remove(director);
        }

        if(directorHashMap.containsKey(director)){
            directorHashMap.remove(director);
        }
    }

    public void deleteAllDirector(){
        HashSet<String> moviesSet = new HashSet<String>();

        //directorMap = new HashMap<>();

        for(String director: pairHashMap.keySet()){
            for(String movie: pairHashMap.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(movieHashMap.containsKey(movie)){
                movieHashMap.remove(movie);
            }
        }
    }
}