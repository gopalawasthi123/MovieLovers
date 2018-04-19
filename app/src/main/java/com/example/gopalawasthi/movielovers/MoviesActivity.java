package com.example.gopalawasthi.movielovers;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.wang.avi.AVLoadingIndicatorView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.MediaStore.Video.VideoColumns.LANGUAGE;
import static com.example.gopalawasthi.movielovers.MovieFragment.API_key;
import static com.example.gopalawasthi.movielovers.MovieFragment.LANGUGAGE;
import static com.example.gopalawasthi.movielovers.MovieFragment.PAGE;
import static java.util.Collections.addAll;

public class MoviesActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        MovieFragment.onMovieClickInterfacecallback,
        TvFragment.onTvclick,
        FavouriteFragment.onMovieclicklistener,
        FavouriteTvFragment.onTvclicklistener {

    List<Nowplaying.ResultsBean> ListNow;
    public static final String TV_DATABASE = "tv";
    Boolean onpressBack = false;

    @Override
    protected void onResume() {
        super.onResume();
        fragment = new FavouriteFragment();
    }

    //
    ListView listView;
    SearchView searchView;
    Dialog dialog;
    ArrayList<String> arrayList;
    Moviedatabase moviedatabase;
    FavouriteFragment fragment;
    FavouriteTvFragment myfragment;
    MoviesDao moviesDao;


    MoviesDao tvdao;
    public static final String MOVIEDATABASE_ID = "id";
    CoordinatorLayout coordinatorLayout;

    List<SearchClass.ResultsBean> searchClassList;
//    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragment = new FavouriteFragment();
        myfragment = new FavouriteTvFragment();


//        screenSize = getResources().getSystem().getConfiguration().screenLayout &
//                Configuration.SCREENLAYOUT_SIZE_MASK;
//        headernowplaying = findViewById(R.id.nowplayingheader);
//        headerpopular = findViewById(R.id.popularheader);
//        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
//        viewallnowplaying = findViewById(R.id.showallnowplaying);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                (AppCompatActivity) this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = this.findViewById(R.id.nav_view);

        coordinatorLayout = findViewById(R.id.moviecontainer);
        navigationView.setNavigationItemSelectedListener(this);
        setfragment(new MovieFragment());
        searchClassList = new ArrayList<>();
        moviedatabase = Moviedatabase.getINSTANCE(MoviesActivity.this);
        moviesDao = moviedatabase.getMovieDao();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            setTitle("Movies");
            setfragment(new MovieFragment());
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            setTitle("TvShows");
            setfragment(new TvFragment());
        } else if (id == R.id.nav_slideshow) {
            setTitle("Favourite");
            setfragment(new RootFragment());

        } else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    private void setfragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.moviecontainer, fragment).commit();


    }

    @Override
    public void onmovieClick(Nowplaying.ResultsBean nowplaying) {
        Intent intent = new Intent(this, MainActivity.class);

        int a = nowplaying.getId();
        String b = nowplaying.getTitle();
        intent.putExtra("movieid", a);
        intent.putExtra("moviename", b);
        intent.putExtra("movieposter", nowplaying.getPoster_path());
        intent.putExtra("moviebackdrop", nowplaying.getBackdrop_path());
        intent.putExtra("description", nowplaying.getOverview());
        startActivity(intent);
    }

    @Override
    public void onpopularmovieClick(Nowplaying.ResultsBean popular) {
        Intent intent = new Intent(this, MainActivity.class);
        int a = popular.getId();
        String b = popular.getTitle();
        intent.putExtra("movieid", a);
        intent.putExtra("moviename", b);
        intent.putExtra("movieposter", popular.getPoster_path());
        intent.putExtra("moviebackdrop", popular.getBackdrop_path());
        intent.putExtra("description", popular.getOverview());
        startActivity(intent);

    }

    @Override
    public void ontopratedmovieClick(Nowplaying.ResultsBean toprated) {
        Intent intent = new Intent(this, MainActivity.class);
        int a = toprated.getId();
        String b = toprated.getTitle();
        intent.putExtra("movieid", a);
        intent.putExtra("moviename", b);
        intent.putExtra("movieposter", toprated.getPoster_path());
        intent.putExtra("moviebackdrop", toprated.getBackdrop_path());
        intent.putExtra("description", toprated
                .getOverview());
        startActivity(intent);
    }

    @Override
    public void onupcomingmovieclick(Nowplaying.ResultsBean upcoming) {
        Intent intent = new Intent(this, MainActivity.class);

        int a = upcoming.getId();
        String b = upcoming.getTitle();
        intent.putExtra("movieid", a);
        intent.putExtra("moviename", b);
        intent.putExtra("movieposter", upcoming.getPoster_path());
        intent.putExtra("moviebackdrop", upcoming.getBackdrop_path());
        intent.putExtra("description", upcoming.getOverview());
        startActivity(intent);
    }

    // Todo
    @Override
    public void onnowplayinglongclick(Nowplaying.ResultsBean nowlong) {
        int id = nowlong.getId();
//       String title = nowlong.getTitle();
//       String  backdrop = nowlong.getBackdrop_path();
//       String userrating =  String.valueOf(nowlong.getVote_average());


        moviesDao.oninsertFavouriteMovie(nowlong);
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIEDATABASE_ID, id);

        fragment.setArguments(bundle);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Added to Favourites", Snackbar.LENGTH_SHORT);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void onpopularlongClick(Nowplaying.ResultsBean poplong) {
        int id = poplong.getId();
        moviesDao.oninsertFavouriteMovie(poplong);
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIEDATABASE_ID, id);
        fragment.setArguments(bundle);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Added to Favourites", Snackbar.LENGTH_SHORT);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void ontopratedlongClick(Nowplaying.ResultsBean toprated) {
        int id = toprated.getId();
        moviesDao.oninsertFavouriteMovie(toprated);
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIEDATABASE_ID, id);
        fragment.setArguments(bundle);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Added to Favourites", Snackbar.LENGTH_SHORT);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void onupcominglongClick(Nowplaying.ResultsBean upcoming) {
        int id = upcoming.getId();
        moviesDao.oninsertFavouriteMovie(upcoming);
        Bundle bundle = new Bundle();
        bundle.putInt(MOVIEDATABASE_ID, id);
        fragment.setArguments(bundle);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Added to Favourites", Snackbar.LENGTH_SHORT);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();

    }

    public void showallnowplaying(View view) {
        Intent intent = new Intent(this, ShowallList.class);
        intent.addCategory("nowplaying");
        startActivity(intent);

    }

    public void showallpopular(View view) {
        Intent intent = new Intent(this, ShowallList.class);
        intent.addCategory("popular");
        startActivity(intent);
    }

    public void showalltoprated(View view) {
        Intent intent = new Intent(this, ShowallList.class);
        intent.addCategory("toprated");
        startActivity(intent);
    }


    public void showallupcoming(View view) {
        Intent intent = new Intent(this, ShowallList.class);
        intent.addCategory("upcoming");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Handler handler = new Handler();
            if (onpressBack) {
                finish();
            }
            Toast.makeText(this, "press back again to exit!!", Toast.LENGTH_SHORT).show();
            onpressBack = true;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onpressBack = false;
                }
            }, 2000);

        }
    }


    @Override
    public void ontopratedmovieClick(TvClass.ResultsBean resultsBean) {
        Intent intent = new Intent(MoviesActivity.this, MainActivity.class);
        int a = resultsBean.getId();
        String b = resultsBean.getName();
        intent.addCategory("TV");
        intent.putExtra("movieid", a);
        intent.putExtra("moviename", b);
        intent.putExtra("movieposter", resultsBean.getPoster_path());
        intent.putExtra("moviebackdrop", resultsBean.getBackdrop_path());
        intent.putExtra("description", resultsBean.getOverview());
        startActivity(intent);
    }


    @Override
    public void onpopularmovieClick(TvClass.ResultsBean resultsBean) {
        Intent intent = new Intent(MoviesActivity.this, MainActivity.class);
        int a = resultsBean.getId();
        String b = resultsBean.getName();
        intent.addCategory("TV");
        intent.putExtra("movieid", a);
        intent.putExtra("moviename", b);
        intent.putExtra("movieposter", resultsBean.getPoster_path());
        intent.putExtra("moviebackdrop", resultsBean.getBackdrop_path());
        intent.putExtra("description", resultsBean.getOverview());
        startActivity(intent);
    }

    @Override
    public void ontopratedLongClick(TvClass.ResultsBean toprated) {

        int id = toprated.getId();
        moviesDao.oninsertFavouriteTvShow(toprated);
        Bundle bundle = new Bundle();
        bundle.putInt(TV_DATABASE, id);
        myfragment.setArguments(bundle);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Added to Favourites", Snackbar.LENGTH_SHORT);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();
    }

    @Override
    public void onpopularLongClick(TvClass.ResultsBean popular) {
        int id = popular.getId();
        moviesDao.oninsertFavouriteTvShow(popular);
        Bundle bundle = new Bundle();
        bundle.putInt(TV_DATABASE, id);
        myfragment.setArguments(bundle);
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Added to Favourites", Snackbar.LENGTH_SHORT);
        snackbar.setActionTextColor(Color.RED);
        snackbar.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main2, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_settings));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MoviesActivity.this, SearchActvity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return true;
            }


            @Override
            public boolean onQueryTextChange(final String newText) {
                searchClassList = new ArrayList<>();

                if (newText.length() >= 3) {

                    final retrofit2.Call<SearchClass> searchClassCall = ApiClient.getINSTANCE().getMoviesInterface().getsearchall(API_key, LANGUGAGE, newText, PAGE, false);
                    searchClassCall.enqueue(new Callback<SearchClass>() {
                        @Override
                        public void onResponse(retrofit2.Call<SearchClass> call, Response<SearchClass> response) {
                            if (response.isSuccessful()) {


                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<SearchClass> call, Throwable t) {

                        }
                    });
                } else {

                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {
            setfragment(new Searchhint());
        }
        return true;
    }


    @Override
    public void showallpopulartv(View view) {
        Intent intent = new Intent(this, ShowallList.class);
        intent.addCategory("tvpopular");
        startActivity(intent);

    }

    @Override
    public void showalltopratedtv(View view) {
        Intent intent = new Intent(this, ShowallList.class);
        intent.addCategory("tvtoprated");
        startActivity(intent);
    }


    @Override
    public void onmovieClickfavourite(Nowplaying.ResultsBean bean) {
        Intent intent = new Intent(this, MainActivity.class);
        int a = bean.getId();
        String b = bean.getTitle();
        intent.putExtra("movieid", a);
        intent.putExtra("moviename", b);
        intent.putExtra("movieposter", bean.getPoster_path());
        intent.putExtra("moviebackdrop", bean.getBackdrop_path());
        intent.putExtra("description", bean.getOverview());
        startActivity(intent);
    }

    @Override
    public void ontvClickfavourite(TvClass.ResultsBean bean) {
        Intent intent = new Intent(this, MainActivity.class);
        int a = bean.getId();
        String b = bean.getName();
        intent.putExtra("movieid", a);
        intent.putExtra("moviename", b);
        intent.putExtra("movieposter", bean.getPoster_path());
        intent.putExtra("moviebackdrop", bean.getBackdrop_path());
        intent.putExtra("description", bean.getOverview());
        startActivity(intent);
    }


}

