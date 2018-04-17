package com.example.gopalawasthi.movielovers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.gopalawasthi.movielovers.MoviesActivity.MOVIEDATABASE_ID;

/**
 * A simple {@link Fragment} subclass.
 */
//Todo
public class FavouriteFragment extends Fragment implements MoviesAdapter.onitemclicklistener {

    RecyclerView favouriterecycler;
    MoviesAdapter adapter ;
    List<Nowplaying.ResultsBean> resultsBeans;
    MoviesDao dao;
    Moviedatabase moviedatabase;
    List<Nowplaying.ResultsBean> list;
    int id;

    onMovieclicklistener movieclicklistener;


  public interface  onMovieclicklistener{
       void onmovieClickfavourite(Nowplaying.ResultsBean bean);
   }
    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            movieclicklistener = (onMovieclicklistener) context;

        }catch (ClassCastException e){

        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        // Inflate the layout for this fragment
            if(getArguments()!=null) {
                id = getArguments().getInt(MOVIEDATABASE_ID);
            }
        favouriterecycler = view.findViewById(R.id.recyclerfavourite);
        resultsBeans = new ArrayList<>();
        moviedatabase = Moviedatabase.getINSTANCE(getContext());
        list = new ArrayList<>();
        dao = moviedatabase.getMovieDao();
           list.addAll(dao.getallmovies());
                if(id != 0) {
                    resultsBeans.add(dao.getMoviefavourite(id));
//                    dao.oninsertMovies(resultsBeans);
                }
//        resultsBeans.addAll( dao.getMoviefavourite(id)) ;
        adapter = new MoviesAdapter(list, getContext(),this);

        favouriterecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        favouriterecycler.setAdapter(adapter);
//        list.clear();

            list.addAll(resultsBeans);

        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onItemclick(int position) {
        Nowplaying.ResultsBean bean = list.get(position);
        movieclicklistener.onmovieClickfavourite(bean);
    }

    @Override
    public void onlongItemclick(int position) {

    }
}
