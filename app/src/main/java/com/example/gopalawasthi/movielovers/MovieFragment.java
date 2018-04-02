package com.example.gopalawasthi.movielovers;


import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MoviesAdapter.onitemclicklistener {


    TextView viewallnowplaying;
    SearchView searchView;
    public static final String CATEGORY = "now_playing";
    public static final String POPULAR_CATEGORY= "popular";
    public static final String TOPRATED_CATEGORY ="top_rated";
    public static final String LANGUGAGE ="en-US";
    public static final String UPCOMING_CATEGORY = "upcoming";
    public static final int PAGE =1;
    public static final String API_key ="f05e7eb1cb1d184b717962fc1230e9c1";
    int screenSize;
    CustomSwipetoRefresh swipeRefreshLayout ;
    RecyclerView recyclerView;
    RecyclerView recyclerpopular;
    MoviesAdapter adapter;
    popularmovieadapter popularmovieadapter;
    List<Nowplaying.ResultsBean> popularList;
    List<Nowplaying.ResultsBean> toprated;
    topratedAdapter topratedAdapter;
    List<Nowplaying.ResultsBean> ListNow;
    ProgressBar progressBar;
    LinearLayout headernowplaying;
    LinearLayout headerpopular;
    RecyclerView recyclertoprated;
    RecyclerView recyclerupcoming;
    List<Nowplaying.ResultsBean> upcominglist;
    MoviesAdapter myupcoming;
    Moviedatabase moviedatabase;
    MoviesDao dao;
    onMovieClickInterfacecallback interfacecallback;
    public interface onMovieClickInterfacecallback{
        void onmovieClick(Nowplaying.ResultsBean nowplaying);
    }

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      try{
          interfacecallback = (onMovieClickInterfacecallback) context;

      }catch (ClassCastException e){

      }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final View view = inflater.inflate(R.layout.fragment_movie,container,false);
        // Inflate the layout for this fragment
        screenSize = getResources().getSystem().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        headernowplaying = view.findViewById(R.id.nowplayingheader);
        headerpopular = view.findViewById(R.id.popularheader);
        swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);
        viewallnowplaying = view.findViewById(R.id.showallnowplaying);


        //TOdo connectivity manager for the internet check


//         gridLayoutManager = new GridLayoutManager(this,2,LinearLayoutManager.HORIZONTAL,false);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return position == 10 ? 2:1 ;
//            }
//        });

        // for now playing
        createfornowplaying(view);

        //for popularmovies
//        creaforpopularmovies();

        //for top-ratedmovies
//        createfortoprated();

        //for upcoming movies
//        createforupcoming();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                createfornowplaying(view);
//                creaforpopularmovies();
//                createfortoprated();
//                createforupcoming();

            }
        });


        return view;
    }



    private void createfornowplaying(View view) {
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark),getResources().getColor(R.color.colorWhite));
        ListNow = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview);
//        movieDatabase = Room.databaseBuilder(this,MovieDatabase.class,"mymovies").allowMainThreadQueries().build();
//              movieDao=  movieDatabase.getMovieDao();
//              List<Nowplaying.ResultsBean> list =movieDao.getnowplaing();

        moviedatabase = Moviedatabase.getINSTANCE(getContext());
        dao = moviedatabase.getMovieDao();
        List<Nowplaying.ResultsBean> mydao = dao.getallmovies();
        Log.d("movies",mydao.get(2).getTitle());
//        recyclerView.setVisibility(View.GONE);
//        headernowplaying.setVisibility(View.GONE);
        adapter =  new MoviesAdapter(ListNow, getContext(),this );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.setOnFlingListener(null);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        ListNow.clear();
        ListNow.addAll(mydao);
        adapter.notifyDataSetChanged();
        fetchdatafromnetwork();


        swipeRefreshLayout.setRefreshing(false);

//        List.clear();
//        List.addAll(list);
//        adapter.notifyDataSetChanged();

    }

    private void fetchdatafromnetwork() {

        Call<Nowplaying> nowplayingCall = ApiClient.getINSTANCE().getMoviesInterface().getnowplayingMovies(CATEGORY,API_key,LANGUGAGE,PAGE);
        nowplayingCall.enqueue(new Callback<Nowplaying>() {
            @Override
            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
                if (response.isSuccessful()) {
                    Nowplaying root = response.body();
                    ListNow.clear();
                    ListNow.addAll(root.getResults());
                    adapter.notifyDataSetChanged();
                    dao.oninsertMovies(ListNow);
                    recyclerView.setVisibility(View.VISIBLE);
                    headernowplaying.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<Nowplaying> call, Throwable t) {

            }
        });
    }



    @Override
    public void onItemclick(int position) {
       Nowplaying.ResultsBean nowplayingresults = ListNow.get(position);
       interfacecallback.onmovieClick(nowplayingresults);
    }
}