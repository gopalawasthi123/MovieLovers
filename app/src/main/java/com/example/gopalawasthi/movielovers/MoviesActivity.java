package com.example.gopalawasthi.movielovers;

import android.app.SearchManager;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,MoviesAdapter.onitemclicklistener {

    TextView viewallnowplaying;

    public static final String CATEGORY = "now_playing";
    public static final String POPULAR_CATEGORY= "popular";
    public static final String TOPRATED_CATEGORY ="top_rated";
    public static final String LANGUGAGE ="en-US";
    public static final int PAGE =1;
    public static final String API_key ="f05e7eb1cb1d184b717962fc1230e9c1";
    int screenSize;

    RecyclerView recyclerView;
    RecyclerView recyclerpopular;
    MoviesAdapter adapter;
    popularmovieadapter popularmovieadapter;
    List<PopularMovies.ResultsBean> popularList;
    List<TopRated.ResultsBean> toprated;
    topratedAdapter topratedAdapter;
    List<Nowplaying.ResultsBean> List;
    ProgressBar progressBar;
    LinearLayout headernowplaying;
    LinearLayout headerpopular;
    RecyclerView recyclertoprated;

//    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);
        screenSize = getResources().getSystem().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
        headernowplaying = findViewById(R.id.nowplayingheader);
        headerpopular = findViewById(R.id.popularheader);

        viewallnowplaying = findViewById(R.id.showallnowplaying);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TOdo connectivity manager for the internet check


//         gridLayoutManager = new GridLayoutManager(this,2,LinearLayoutManager.HORIZONTAL,false);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return position == 10 ? 2:1 ;
//            }
//        });

        // for now playing
        createfornowplaying();

        //for popularmovies
        creaforpopularmovies();

        //for top-ratedmovies
        createfortoprated();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void createfortoprated() {
        recyclertoprated = findViewById(R.id.thirdrecycler);
        toprated = new ArrayList<>();
        fetchdatafortoprated();
        topratedAdapter = new topratedAdapter(this,toprated);
        recyclertoprated.setAdapter(topratedAdapter);
        recyclertoprated.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclertoprated);

    }

    private void fetchdatafortoprated() {
        Call<TopRated> topRatedCall = ApiClient.getINSTANCE().getMoviesInterface().gettopRatedMovies(TOPRATED_CATEGORY,API_key,LANGUGAGE,PAGE);
        topRatedCall.enqueue(new Callback<TopRated>() {
            @Override
            public void onResponse(Call<TopRated> call, Response<TopRated> response) {
                if(response.body()!=null){
                    toprated.clear();
                    toprated.addAll(response.body().getResults());

                    topratedAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TopRated> call, Throwable t) {

            }
        });
    }

    private void creaforpopularmovies() {
    recyclerpopular = findViewById(R.id.secondrecycler);
        popularList = new ArrayList<>();
        recyclerpopular.setVisibility(View.GONE);
        headerpopular.setVisibility(View.GONE);

        fetchdataforpopular();

        popularmovieadapter = new popularmovieadapter(this,popularList);
        recyclerpopular.setAdapter(popularmovieadapter);
        recyclerpopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
       SnapHelper snapHelper = new LinearSnapHelper();
       snapHelper.attachToRecyclerView(recyclerpopular);


    }

    private void fetchdataforpopular() {

        Call<PopularMovies> popularMoviesCall = ApiClient.getINSTANCE().getMoviesInterface().getpopularMovies(POPULAR_CATEGORY,API_key,LANGUGAGE,PAGE);
        popularMoviesCall.enqueue(new Callback<PopularMovies>() {
            @Override
            public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
                if(response.isSuccessful()){
                    PopularMovies popularMovies = response.body();
                    popularList.clear();
                    popularList.addAll(popularMovies.getResults());
                    popularmovieadapter.notifyDataSetChanged();

                    recyclerpopular.setVisibility(View.VISIBLE);
                    headerpopular.setVisibility(View.VISIBLE);


                }

            }

            @Override
            public void onFailure(Call<PopularMovies> call, Throwable t) {

            }
        });

    }


    private void createfornowplaying() {

        List = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
//        movieDatabase = Room.databaseBuilder(this,MovieDatabase.class,"mymovies").allowMainThreadQueries().build();
//              movieDao=  movieDatabase.getMovieDao();
//              List<Nowplaying.ResultsBean> list =movieDao.getnowplaing();
        recyclerView.setVisibility(View.GONE);
        headernowplaying.setVisibility(View.GONE);
        fetchdatafromnetwork();
        adapter =  new MoviesAdapter(List,this,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
//        List.clear();
//        List.addAll(list);
//        adapter.notifyDataSetChanged();

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

    }

        private void fetchdatafromnetwork() {

        Call<Nowplaying> nowplayingCall = ApiClient.getINSTANCE().getMoviesInterface().getnowplayingMovies(CATEGORY,API_key,LANGUGAGE,PAGE);
        nowplayingCall.enqueue(new Callback<Nowplaying>() {
            @Override
            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
                if (response.isSuccessful()) {
                    Nowplaying root = response.body();
                    List.clear();
                    List.addAll(root.getResults());
                    adapter.notifyDataSetChanged();

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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_settings));
        SearchManager searchManager =(SearchManager) getSystemService(SEARCH_SERVICE);

        if(searchManager.getSearchableInfo(getComponentName())!=null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showallnowplaying(View view) {



    }

    @Override
    public void onItemclick(int position) {
        Toast.makeText(this, "Item open new activity", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
