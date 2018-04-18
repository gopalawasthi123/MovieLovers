package com.example.gopalawasthi.movielovers;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gopalawasthi.movielovers.MovieFragment.API_key;
import static com.example.gopalawasthi.movielovers.MovieFragment.LANGUGAGE;
import static com.example.gopalawasthi.movielovers.MovieFragment.PAGE;
import static com.example.gopalawasthi.movielovers.MovieFragment.POPULAR_CATEGORY;
import static com.example.gopalawasthi.movielovers.MovieFragment.TOPRATED_CATEGORY;
import static com.example.gopalawasthi.movielovers.MoviesActivity.MOVIEDATABASE_ID;


public class ShowallList extends AppCompatActivity  {
    int pagecount = 1;
    RecyclerView recyclerView;
    List<Nowplaying.ResultsBean> list;
    MoviesAdapter adapter;
    TvtopratedAdapter tvtopratedAdapter;
    List<TvClass.ResultsBean> populartv;
    MoviesDao dao;
    Moviedatabase moviedatabase;
    FavouriteFragment fragment;
    Button button;
    AVLoadingIndicatorView avi;
    Indicator indicator;
    FrameLayout frameLayout;

     String a;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showall_list);
        avi = findViewById(R.id.avi);
        Intent intent = getIntent();
        frameLayout = findViewById(R.id.frameshowall);



        if(intent.hasCategory("nowplaying")){
            a = "now_playing";
            createfornowplaying(a);
        }else if(intent.hasCategory("popular")){
            a = "popular";
            createfornowplaying(a);
        }else if(intent.hasCategory("toprated")){
            a = "top_rated";
            createfornowplaying(a);
        }else if(intent.hasCategory("upcoming")){
            a= "upcoming";
            createfornowplaying(a);
        }else if(intent.hasCategory("tvpopular")){
            a = "popular";
            createforpopulartv(a);
        }else if(intent.hasCategory("tvtoprated")){
            a = "top_rated";
            createforpopulartv(a);
        }
    }

    private void createforpopulartv(final String a) {
        fragment = new FavouriteFragment();
        populartv = new ArrayList<>();
        recyclerView = findViewById(R.id.showalllistrecycler);
        fetchdatafrompopulartv(a);
        tvtopratedAdapter = new TvtopratedAdapter(this, populartv, new TvtopratedAdapter.onitemClicklistener() {
            @Override
            public void onitemclick(int position) {
                Intent intent = new Intent(ShowallList.this,MainActivity.class);
                int a = populartv.get(position).getId();
                String b =  populartv.get(position).getName();
                intent.addCategory("TV");
                intent.putExtra("movieid",a);
                intent.putExtra("moviename",b);
                intent.putExtra("movieposter",populartv.get(position).getPoster_path());
                intent.putExtra("moviebackdrop",populartv.get(position).getBackdrop_path());
                intent.putExtra("description",populartv.get(position).getOverview());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(tvtopratedAdapter);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){

                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount =  linearLayoutManager.getItemCount();
                    pastVisiblesItems =  linearLayoutManager.findFirstVisibleItemPosition();

                    if(loading){

                        if(visibleItemCount + pastVisiblesItems >= totalItemCount){
                            pagecount =pagecount+1;
                            fetchdatafrompopulartv(a);

                        }

                    }
                }

                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }

    private void fetchdatafrompopulartv(String a) {
        retrofit2.Call<TvClass> nowplayingCall = ApiClient.getINSTANCE().getMoviesInterface().gettvpopular(a,API_key,LANGUGAGE,pagecount);
        nowplayingCall.enqueue(new Callback<TvClass>() {
            @Override
            public void onResponse(retrofit2.Call<TvClass> call, Response<TvClass> response) {
                if(response.body()!=null){
                    populartv.addAll(response.body().getResults());
                    tvtopratedAdapter.notifyDataSetChanged();
                }
                avi.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(retrofit2.Call<TvClass> call, Throwable t) {
                Toast.makeText(ShowallList.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createfornowplaying(final String a) {

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.showalllistrecycler);
//        movieDatabase = Room.databaseBuilder(this,MovieDatabase.class,"mymovies").allowMainThreadQueries().build();
//              movieDao=  movieDatabase.getMovieDao();
//              List<Nowplaying.ResultsBean> list =movieDao.getnowplaing();
        recyclerView.setVisibility(View.GONE);

        fetchdatafromnetwork(a);
        adapter =  new MoviesAdapter(list, this, new MoviesAdapter.onitemclicklistener() {
            @Override
            public void onItemclick(int position) {
                Toast.makeText(ShowallList.this, "item click at position"+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowallList.this,MainActivity.class);
                int a = list.get(position).getId();
                String b =  list.get(position).getTitle();
                intent.putExtra("movieid",a);
                intent.putExtra("moviename",b);
                intent.putExtra("movieposter",list.get(position).getPoster_path());
                intent.putExtra("moviebackdrop",list.get(position).getBackdrop_path());
                intent.putExtra("description",list.get(position).getOverview());
                startActivity(intent);
            }

            @Override
            public void onlongItemclick(int position) {
                 Nowplaying.ResultsBean bean  =  list.get(position);
                 int id = bean.getId();
                moviedatabase = Moviedatabase.getINSTANCE(ShowallList.this);
                dao = moviedatabase.getMovieDao();
                dao.oninsertFavouriteMovie(bean);
                Snackbar snackbar = Snackbar.make(frameLayout,"Added to Favourites",Snackbar.LENGTH_SHORT);
                snackbar.show();
//                Bundle bundle = new Bundle();
//                bundle.putInt(MOVIEDATABASE_ID,id);
//                fragment.setArguments(bundle);
            }
        });
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if(dy > 0){

                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount =  linearLayoutManager.getItemCount();
                    pastVisiblesItems =  linearLayoutManager.findFirstVisibleItemPosition();

                    if(loading){

                        if(visibleItemCount + pastVisiblesItems >= totalItemCount){
                            pagecount =pagecount+1;
                            fetchdatafromnetwork(a);
                        }

                    }
                }

                super.onScrolled(recyclerView, dx, dy);
            }
        });

//        List.clear();
//        List.addAll(list);
//        adapter.notifyDataSetChanged();


    }

    private void fetchdatafromnetwork(String a) {

        Call<Nowplaying> nowplayingCall = ApiClient.getINSTANCE().getMoviesInterface().getnowplayingMovies(
                a,API_key,LANGUGAGE,pagecount);
        nowplayingCall.enqueue(new Callback<Nowplaying>() {
            @Override
            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
                if (response.isSuccessful()) {
                    Nowplaying root = response.body();

                    list.addAll(root.getResults());
                    adapter.notifyDataSetChanged();

                    recyclerView.setVisibility(View.VISIBLE);
                }
                avi.setVisibility(View.GONE);

            }
            @Override
            public void onFailure(Call<Nowplaying> call, Throwable t) {

            }
        });


    }


    public void LoadMore(View view) {


    }

    public void LoadMoreSearch(View view) {
    }


}
