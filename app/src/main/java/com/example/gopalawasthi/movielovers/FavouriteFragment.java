package com.example.gopalawasthi.movielovers;


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
public class FavouriteFragment extends Fragment implements MoviesAdapter.onitemclicklistener {

    RecyclerView favouriterecycler;
    MoviesAdapter adapter ;
    List<Nowplaying.ResultsBean> resultsBeans;
    MoviesDao dao;
    Moviedatabase moviedatabase;

    int id;
    Nowplaying.ResultsBean bean;
    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        // Inflate the layout for this fragment

         id = getArguments().getInt(MOVIEDATABASE_ID);

        favouriterecycler = view.findViewById(R.id.recyclerfavourite);
        resultsBeans = new ArrayList<>();
        moviedatabase = Moviedatabase.getINSTANCE(getContext());
           resultsBeans.add((Nowplaying.ResultsBean) dao.getMoviefavourite(id)) ;

//        resultsBeans.addAll( dao.getMoviefavourite(id)) ;
        adapter = new MoviesAdapter(resultsBeans, getContext(),this);
        favouriterecycler.setAdapter(adapter);
        favouriterecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onItemclick(int position) {

    }

    @Override
    public void onlongItemclick(int position) {

    }
}
