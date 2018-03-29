package com.example.gopalawasthi.movielovers;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Gopal Awasthi on 29-03-2018.
 */

@Database(entities = Nowplaying.ResultsBean.class,version = 1)

public abstract class Moviedatabase extends RoomDatabase {

    private static Moviedatabase INSTANCE;

    public static  Moviedatabase getINSTANCE(Context context){
        if(INSTANCE==null){
        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),Moviedatabase.class,"movie_database")
                .allowMainThreadQueries()
                .build();
        }
        return INSTANCE;
    }


    abstract MoviesDao getMovieDao();
}
