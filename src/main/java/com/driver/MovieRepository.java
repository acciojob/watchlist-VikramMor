package com.driver;


import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;

    public MovieRepository(){
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    void addMovieToDB(Movie movie){

        movieMap.put(movie.getName(), movie);
    }

    void addDirectorToDB(Director director){

        directorMap.put(director.getName(), director);
    }

    void addMovieDirectorPairToDB(String director, String movie){

        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            movieMap.put(movie, movieMap.get(movie));
            directorMap.put(director, directorMap.get(director));
            List<String> currentMovies = new ArrayList<String>();
            if(directorMovieMapping.containsKey(director)) currentMovies = directorMovieMapping.get(director);
            currentMovies.add(movie);
            directorMovieMapping.put(director, currentMovies);
        }
    }

    Movie getMovieByNameFromDB(String movieName){

        return movieMap.get(movieName);
    }

    Director getDirectorByNameFromDB(String directorName){

        return directorMap.get(directorName);
    }

    List<String> getAllMoviesByDirectorFromDB(String directorName){

        List<String> moviesList = new ArrayList<String>();
        if(directorMovieMapping.containsKey(directorName)) moviesList = directorMovieMapping.get(directorName);
        return moviesList;
    }

    List<Movie> getAllMoviesFromDB(){
        ArrayList<Movie> movieList = new ArrayList<>();
        for(Map.Entry<String, Movie> entry: movieMap.entrySet()){
            movieList.add(entry.getValue());
        }
        return movieList;
    }

    void deleteMovieByDirectorFromDB(String directorName){

        List<String> movies = new ArrayList<String>();
        if(directorMovieMapping.containsKey(directorName)){
            movies = directorMovieMapping.get(directorName);
            for(String movie: movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }

            directorMovieMapping.remove(directorName);
        }

        if(directorMap.containsKey(directorName)){
            directorMap.remove(directorName);
        }
    }

    void deleteAllDirectorsFromDB(){

        HashSet<String> moviesSet = new HashSet<String>();

        for(String director: directorMovieMapping.keySet()){
            for(String movie: directorMovieMapping.get(director)){
                moviesSet.add(movie);
            }
        }

        for(String movie: moviesSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
    }
}
