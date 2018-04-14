package com.example.gopalawasthi.movielovers;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements com.example.gopalawasthi.movielovers.popularmovieadapter.OnitemClicklistener, com.example.gopalawasthi.movielovers.topratedAdapter.OnItemCickListener {


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
    upcomingAdapter upcomingAdapter;

    Moviedatabase moviedatabase;
    MoviesDao dao;
    onMovieClickInterfacecallback interfacecallback;



    public interface onMovieClickInterfacecallback{
        void onmovieClick(Nowplaying.ResultsBean nowplaying);
        void onpopularmovieClick(Nowplaying.ResultsBean popular);
        void ontopratedmovieClick(Nowplaying.ResultsBean toprated);
        void onupcomingmovieclick(Nowplaying.ResultsBean upcoming);
        void onnowplayinglongclick(Nowplaying.ResultsBean nowlong);
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

        swipeRefreshLayout.setRefreshing(true);
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
        creaforpopularmovies(view);

        //for top-ratedmovies
        createfortoprated(view);

        //for upcoming movies
        createforupcoming(view);



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                createfornowplaying(view);
                creaforpopularmovies(view);
                createfortoprated(view);
                createforupcoming(view);

            }
        });


        return view;
    }



    private void createfornowplaying(View view) {
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorsplash),getResources().getColor(R.color.coloryellow));

        ListNow = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview);
//        movieDatabase = Room.databaseBuilder(this,MovieDatabase.class,"mymovies").allowMainThreadQueries().build();
//              movieDao=  movieDatabase.getMovieDao();
//              List<Nowplaying.ResultsBean> list =movieDao.getnowplaing();

        moviedatabase = Moviedatabase.getINSTANCE(getContext());
        dao = moviedatabase.getMovieDao();
        String type = "now_playing";
        List<Nowplaying.ResultsBean> mydao = dao.getallmovies();
//        Log.d("movies",mydao.get(2).getTitle());
//        recyclerView.setVisibility(View.GONE);
//        headernowplaying.setVisibility(View.GONE);
        adapter =  new MoviesAdapter(ListNow, getContext(), new MoviesAdapter.onitemclicklistener() {
            @Override
            public void onItemclick(int position) {
                Nowplaying.ResultsBean nowplayingresults = ListNow.get(position);
                interfacecallback.onmovieClick(nowplayingresults);
            }

            @Override
            public void onlongItemclick(int position) {
                Nowplaying.ResultsBean nowplayinglongclick = ListNow.get(position);
                interfacecallback.onnowplayinglongclick(nowplayinglongclick);
            }
        });
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
                    Nowplaying.ResultsBean bean = new Nowplaying.ResultsBean();
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


    private void creaforpopularmovies(View view) {
    recyclerpopular = view.findViewById(R.id.secondrecycler);
        popularList = new ArrayList<>();
        recyclerpopular.setVisibility(View.GONE);
        headerpopular.setVisibility(View.GONE);

        fetchdataforpopular();

        popularmovieadapter = new popularmovieadapter(getContext(),popularList,this);
        recyclerpopular.setAdapter(popularmovieadapter);
        recyclerpopular.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerpopular.setOnFlingListener(null);
       SnapHelper snapHelper = new LinearSnapHelper();
       snapHelper.attachToRecyclerView(recyclerpopular);


    }

    private void fetchdataforpopular() {

        Call<Nowplaying> popularMoviesCall = ApiClient.getINSTANCE().getMoviesInterface().getpopularMovies(POPULAR_CATEGORY,API_key,LANGUGAGE,PAGE);
        popularMoviesCall.enqueue(new Callback<Nowplaying>() {
            @Override
            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
                if(response.isSuccessful()){
                    Nowplaying popularMovies = response.body();
                    popularList.clear();
                    popularList.addAll(popularMovies.getResults());
                    popularmovieadapter.notifyDataSetChanged();

                    recyclerpopular.setVisibility(View.VISIBLE);
                    headerpopular.setVisibility(View.VISIBLE);


                }

            }

            @Override
            public void onFailure(Call<Nowplaying> call, Throwable t) {

            }
        });

    }


    private void createfortoprated(View view) {
        recyclertoprated = view.findViewById(R.id.thirdrecycler);
        toprated = new ArrayList<>();
        fetchdatafortoprated();
        topratedAdapter = new topratedAdapter(getContext(),toprated,this);
        recyclertoprated.setAdapter(topratedAdapter);
        recyclertoprated.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclertoprated.setOnFlingListener(null);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclertoprated);

    }

    private void fetchdatafortoprated() {
        Call<Nowplaying> topRatedCall = ApiClient.getINSTANCE().getMoviesInterface().gettopRatedMovies(TOPRATED_CATEGORY,API_key,LANGUGAGE,PAGE);
        topRatedCall.enqueue(new Callback<Nowplaying>() {
            @Override
            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
                if(response.body()!=null){
                    toprated.clear();
                    toprated.addAll(response.body().getResults());

                    topratedAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Nowplaying> call, Throwable t) {

            }
        });
    }

    private void createforupcoming(View view) {
        recyclerupcoming = view.findViewById(R.id.upcomingrecycler);
        upcominglist = new ArrayList<>();
        fetchdataforupcoming();
        upcomingAdapter = new upcomingAdapter(getContext(), upcominglist, new upcomingAdapter.OnItemCickListener() {
            @Override
            public void OnitemClickupcoming(int position) {
                Nowplaying.ResultsBean upcomingresults = upcominglist.get(position);
                interfacecallback.onupcomingmovieclick(upcomingresults);

            }
        });


        recyclerupcoming.setAdapter(upcomingAdapter);
        recyclerupcoming.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerupcoming.setOnFlingListener(null);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerupcoming);
    }

    private void fetchdataforupcoming() {
        Call<Nowplaying> topRatedCall = ApiClient.getINSTANCE().getMoviesInterface().gettopRatedMovies(UPCOMING_CATEGORY,API_key,LANGUGAGE,PAGE);
        topRatedCall.enqueue(new Callback<Nowplaying>() {
            @Override
            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
                if(response.body()!=null){
                    upcominglist.clear();
                    upcominglist.addAll(response.body().getResults());

                    upcomingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Nowplaying> call, Throwable t) {

            }
        });
    }



        // for the nowplaying category
    // for the popular category
    @Override
    public void OnitemClick(int position) {
        Nowplaying.ResultsBean popularresults = popularList.get(position);
        interfacecallback.onpopularmovieClick(popularresults);

    }
    // for the toprated category
    @Override
    public void OnitemClicktop(int position) {
        Nowplaying.ResultsBean topratedresults = toprated.get(position);
        interfacecallback.ontopratedmovieClick(topratedresults);

    }



}
