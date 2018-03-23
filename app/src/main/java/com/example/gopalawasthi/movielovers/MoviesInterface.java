package com.example.gopalawasthi.movielovers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Gopal Awasthi on 19-03-2018.
 */

public interface MoviesInterface {

    @GET("3/movie/{category}")
    Call<Nowplaying> getnowplayingMovies (@Path("category") String name,
                                         @Query("api_key") String Key,
                                         @Query("language") String en,
                                         @Query("page") int numpage);

    @GET("3/movie/{category}")
    Call<PopularMovies> getpopularMovies(@Path("category") String name,
                                         @Query("api_key") String Key,
                                         @Query("language") String en,
                                         @Query("page") int numpage);
}