package com.example.gopalawasthi.movielovers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gopalawasthi.movielovers.MovieFragment.API_key;
import static com.example.gopalawasthi.movielovers.MovieFragment.CATEGORY;
import static com.example.gopalawasthi.movielovers.MovieFragment.LANGUGAGE;
import static com.example.gopalawasthi.movielovers.MovieFragment.PAGE;
import static com.example.gopalawasthi.movielovers.MoviesAdapter.IMAGE;

public class MainActivity extends AppCompatActivity implements com.example.gopalawasthi.movielovers.trailerAdapter.ontrailerclickListener {
        CollapsingToolbarLayout layout;
//    CustomSwipetoRefresh swipeRefreshLayout ;
    RecyclerView recyclerView;
    List<MovieCredits.CastBean>List;

    DescriptionAdapter adapter;
    ImageView poster;
    ImageView backdrop;
    TextView description;
    int b;
    List<TrailersClass.ResultsBean> trailerslist;
    RecyclerView trailerrecycler;
    trailerAdapter trailerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        poster = findViewById(R.id.poster);
        backdrop = findViewById(R.id.backdrop);
        layout = findViewById(R.id.collapse);
        description = findViewById(R.id.description);
//        swipeRefreshLayout = findViewById(R.id.descriptionswipe);
        Intent intent = getIntent();
        String a = intent.getStringExtra("moviename");
         b = intent.getIntExtra("movieid",-1);
        String c = intent.getStringExtra("movieposter");
        String d =  intent.getStringExtra("moviebackdrop");
        String desc =intent.getStringExtra("description");
        Picasso.get().load(IMAGE+c).into(this.poster);
        Picasso.get().load(IMAGE+d).fit().into(this.backdrop);
        description.setText(desc);
        layout.setExpandedTitleGravity(Gravity.RIGHT|Gravity.BOTTOM);
        layout.setExpandedTitleMarginStart(15);
        layout.setTitle(a);
        createfornowplaying();
        createformovietrailers();
    }

    private void createformovietrailers() {
        trailerslist = new ArrayList<>();
        trailerrecycler = findViewById(R.id.recycleryoutube);
        fetchdatafromyoutube();
        trailerAdapter = new trailerAdapter(trailerslist,this,this);
        trailerrecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        trailerrecycler.setAdapter(trailerAdapter);
        trailerrecycler.setOnFlingListener(null);
    }

    private void fetchdatafromyoutube() {
        Call<TrailersClass> tvClassCall = ApiClient.getINSTANCE().getMoviesInterface().getmovietrailers(b,API_key,LANGUGAGE);
        tvClassCall.enqueue(new Callback<TrailersClass>() {
            @Override
            public void onResponse(Call<TrailersClass> call, Response<TrailersClass> response) {
                if(response.body()!=null) {
                  TrailersClass trailersClass = response.body();
                  trailerslist.clear();
                  trailerslist.addAll(trailersClass.getResults());
                    trailerAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<TrailersClass> call, Throwable t) {

            }
        });
    }


    private void createfornowplaying() {
//        swipeRefreshLayout.setRefreshing(true);
//        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark),getResources().getColor(R.color.colorWhite));
        List = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclermoviecast);
//        movieDatabase = Room.databaseBuilder(this,MovieDatabase.class,"mymovies").allowMainThreadQueries().build();
//              movieDao=  movieDatabase.getMovieDao();
//              List<Nowplaying.ResultsBean> list =movieDao.getnowplaing();

        fetchdatafromnetwork();
        adapter =  new DescriptionAdapter(List,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.setOnFlingListener(null);

        adapter.notifyDataSetChanged();
//        swipeRefreshLayout.setRefreshing(false);

//        List.clear();
//        List.addAll(list);
//        adapter.notifyDataSetChanged();
    }

    private void fetchdatafromnetwork() {

        Call<MovieCredits> nowplayingCall = ApiClient.getINSTANCE().getMoviesInterface().getcastMovies(b,API_key);
        nowplayingCall.enqueue(new Callback<MovieCredits>() {
            @Override
            public void onResponse(Call<MovieCredits> call, Response<MovieCredits> response) {
                if (response.isSuccessful()) {
                    MovieCredits root = response.body();
                    List.clear();
                    List.addAll(root.getCast());
                    adapter.notifyDataSetChanged();

                }

            }
            @Override
            public void onFailure(Call<MovieCredits> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail to load the activity", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void ontrailerClick(int position) {
        Intent intent = new Intent(this,VideoPlayerActivity.class);
        TrailersClass.ResultsBean bean = trailerslist.get(position);
        intent.putExtra("video_id",bean.getKey());
        startActivity(intent);
    }
}
