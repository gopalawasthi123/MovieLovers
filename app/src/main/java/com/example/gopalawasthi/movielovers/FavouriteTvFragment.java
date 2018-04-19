package com.example.gopalawasthi.movielovers;


import android.arch.persistence.room.Dao;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.gopalawasthi.movielovers.MoviesActivity.TV_DATABASE;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteTvFragment extends Fragment implements TvtopratedAdapter.onitemClicklistener {

        RecyclerView recyclerView;
        TvtopratedAdapter adapter;
        List<TvClass.ResultsBean> mylist;
        Moviedatabase tvdatabase;
        MoviesDao dao;
        int id;
    onTvclicklistener tvclicklistener;
    public FavouriteTvFragment() {
        // Required empty public constructor
    }

    public interface  onTvclicklistener{
        void ontvClickfavourite(TvClass.ResultsBean bean);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            tvclicklistener  = (FavouriteTvFragment.onTvclicklistener) context;

        }catch (ClassCastException e){

        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite_tv, container, false);
        if(getArguments()!=null) {
            id = getArguments().getInt(TV_DATABASE);
        }
        recyclerView = view.findViewById(R.id.recyclerfavouritetv);
        mylist = new ArrayList<>();
       tvdatabase = Moviedatabase.getINSTANCE(getContext());
        dao = tvdatabase.getMovieDao();
        mylist.clear();
        mylist.addAll(dao.getalltvshows());

        adapter = new TvtopratedAdapter(getContext(), mylist, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onitemclick(int position) {
        TvClass.ResultsBean bean = mylist.get(position);
        tvclicklistener.ontvClickfavourite(bean);

    }

    @Override
    public void onitemLongclick(int position) {

    }
}
