package com.example.gopalawasthi.movielovers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Gopal Awasthi on 19-03-2018.
 */

public class ApiClient {

    private static ApiClient INSTANCE;

    private MoviesInterface moviesInterface;

    private ApiClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://developers.themoviedb.org/")
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build();
        moviesInterface = retrofit.create(MoviesInterface.class);
    }

    public static ApiClient getINSTANCE (){
        if(INSTANCE==null){
            INSTANCE= new ApiClient();
        }
        return INSTANCE;
    }

    public MoviesInterface getMoviesInterface (){
        return moviesInterface;
    }
}
