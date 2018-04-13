package com.example.gopalawasthi.movielovers;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gopalawasthi.movielovers.MovieFragment.API_key;
import static com.example.gopalawasthi.movielovers.MovieFragment.LANGUGAGE;
import static com.example.gopalawasthi.movielovers.MovieFragment.PAGE;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvFragment extends Fragment{

    public static final String  TVPOPULAR = "popular";
    public static final String TVTOPRATED ="top_rated";
    RecyclerView tvopratedrecycler;
    RecyclerView tvpopularrecycler;
    List<TvClass.ResultsBean> tvtoprated;
    List<TvClass.ResultsBean> tvpopular;
    TvtopratedAdapter adapter;
    TvtopratedAdapter popularadapter;
    CustomSwipetoRefresh refresh;


    public TvFragment() {
        // Required empty public constructor
    }
        onTvclick tvclick;
    public interface onTvclick{
        void ontopratedmovieClick(TvClass.ResultsBean resultsBean);
        void onpopularmovieClick(TvClass.ResultsBean resultsBean);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            tvclick =(onTvclick) context;

        }catch (ClassCastException e){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.fragment_tv, container, false);;
        // Inflate the layout for this fragment

            refresh = view.findViewById(R.id.swiperefreshlayout);
            refresh.setRefreshing(true);
            createfortopratedtv(view);
            createforpoplartv(view);
        tvpopularrecycler = view.findViewById(R.id.recyclerpopulartv);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                createfortopratedtv(view);
                createforpoplartv(view);
            }
        });

        return view;
    }

    private void createforpoplartv(View view) {
        refresh.setRefreshing(true);
        tvpopularrecycler = view.findViewById(R.id.recyclerpopulartv);
        tvpopular = new ArrayList<>();
        fetchdatafrompopulartv();
        popularadapter = new TvtopratedAdapter(getContext(), tvpopular, new TvtopratedAdapter.onitemClicklistener() {
            @Override
            public void onitemclick(int position) {
                TvClass.ResultsBean myresult = tvpopular.get(position);
                tvclick.ontopratedmovieClick(myresult);
            }
        });
        tvpopularrecycler.setAdapter(popularadapter);
        tvpopularrecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        tvpopularrecycler.setOnFlingListener(null);
        refresh.setRefreshing(false);
    }

    private void fetchdatafrompopulartv() {
        retrofit2.Call<TvClass> nowplayingCall = ApiClient.getINSTANCE().getMoviesInterface().gettvpopular(TVPOPULAR,API_key,LANGUGAGE,PAGE);
        nowplayingCall.enqueue(new Callback<TvClass>() {
            @Override
            public void onResponse(retrofit2.Call<TvClass> call, Response<TvClass> response) {
                if(response.body()!=null){
                    tvpopular.clear();
                    tvpopular.addAll(response.body().getResults());
                    popularadapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<TvClass> call, Throwable t) {

            }
        });
    }

    private void createfortopratedtv(View view) {
        refresh.setRefreshing(true);

        tvopratedrecycler = view.findViewById(R.id.topratetvrecycler);
        tvtoprated = new ArrayList<>();
        fetchdatafromnetwork();
        adapter = new TvtopratedAdapter(getContext(), tvtoprated, new TvtopratedAdapter.onitemClicklistener() {
            @Override
            public void onitemclick(int position) {
                TvClass.ResultsBean myresult = tvtoprated.get(position);
                tvclick.onpopularmovieClick(myresult);
            }
        });
        tvopratedrecycler.setAdapter(adapter);
        tvopratedrecycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        tvopratedrecycler.setOnFlingListener(null);
        refresh.setRefreshing(false
        );
    }

    private void fetchdatafromnetwork() {
     retrofit2.Call<TvClass> nowplayingCall = ApiClient.getINSTANCE().getMoviesInterface().getvshows(TVTOPRATED,API_key,LANGUGAGE,PAGE);
        nowplayingCall.enqueue(new Callback<TvClass>() {
            @Override
            public void onResponse(retrofit2.Call<TvClass> call, Response<TvClass> response) {
                if(response.body()!=null){
                    tvtoprated.clear();
                    tvtoprated.addAll(response.body().getResults());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<TvClass> call, Throwable t) {

            }
        });
    }


}
