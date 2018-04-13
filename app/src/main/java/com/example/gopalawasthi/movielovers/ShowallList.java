package com.example.gopalawasthi.movielovers;

import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gopalawasthi.movielovers.MovieFragment.API_key;
import static com.example.gopalawasthi.movielovers.MovieFragment.LANGUGAGE;


public class ShowallList extends AppCompatActivity {
    int pagecount = 1;
    RecyclerView recyclerView;
    List<Nowplaying.ResultsBean> list;
    MoviesAdapter adapter;
    Button button;
     String a;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showall_list);
        Intent intent = getIntent();
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
        }


        //

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
