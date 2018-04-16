package com.example.gopalawasthi.movielovers;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import org.w3c.dom.ls.LSInput;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

/**
 * Created by Gopal Awasthi on 29-03-2018.
 */

@Dao
public interface MoviesDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
    void oninsertMovies(List<Nowplaying.ResultsBean> beanList);

   @Query("SELECT * FROM beansList")
    List<Nowplaying.ResultsBean> getallmovies();

   @Query("SELECT * FROM beansList WHERE id = :id")
      List <Nowplaying.ResultsBean> getMoviefavourite(int id);

   @Insert(onConflict =  OnConflictStrategy.REPLACE)
    void oninsertFavouriteMovie(Nowplaying.ResultsBean bean);


}
 