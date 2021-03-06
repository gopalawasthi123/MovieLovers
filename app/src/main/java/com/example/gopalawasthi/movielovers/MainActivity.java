package com.example.gopalawasthi.movielovers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.wang.avi.Indicator;

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
    public static final String CAST_ID = "cast";
    DescriptionAdapter adapter;
    ImageView poster;
    ImageView backdrop;
    TextView description;
    TextView cast;
    int b;
    CustomSwipetoRefresh refresh;
    List<TrailersClass.ResultsBean> trailerslist;
    RecyclerView trailerrecycler;
    trailerAdapter trailerAdapter;
    TextView trailers;
    Indicator avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        cast = findViewById(R.id.cast);
        trailers = findViewById(R.id.trailer);
        poster = findViewById(R.id.poster);
        backdrop = findViewById(R.id.backdrop);
        layout = findViewById(R.id.collapse);
        description = findViewById(R.id.description);

//        swipeRefreshLayout = findViewById(R.id.descriptionswipe);
      final  Intent intent = getIntent();
      refresh = findViewById(R.id.swiperefreshlayout);
      refresh.setRefreshing(true);
        String a = intent.getStringExtra("moviename");
        b = intent.getIntExtra("movieid", -1);
        String c = intent.getStringExtra("movieposter");
        String d = intent.getStringExtra("moviebackdrop");
        String desc = intent.getStringExtra("description");
        Picasso.get().load(IMAGE + c).into(this.poster);
        Picasso.get().load(IMAGE + d).fit().into(this.backdrop);
        description.setText(desc);
        layout.setExpandedTitleGravity(Gravity.END | Gravity.BOTTOM);
        layout.setExpandedTitleMarginStart(15);
        layout.setTitle(a);
        if (intent.hasCategory("TV")) {

            createforTVtrailers();
        } else {
            createformovietrailers();
        }
        createfornowplaying();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (intent.hasCategory("TV")) {

                    createforTVtrailers();
                } else {
                    createformovietrailers();
                }
                createfornowplaying();
            }
        });
    }


    private void createforTVtrailers(){
        refresh.setRefreshing(true);
        refresh.setColorSchemeColors(getResources().getColor(R.color.colorsplash),getResources().getColor(R.color.coloryellow));
        trailerslist = new ArrayList<>();
        trailerrecycler = findViewById(R.id.recycleryoutube);
        fetchdatafromTVyoutube();
        trailerAdapter = new trailerAdapter(trailerslist,this,this);
        trailerrecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        trailerrecycler.setAdapter(trailerAdapter);
        trailerrecycler.setOnFlingListener(null);
        refresh.setRefreshing(false);
    }

    private void fetchdatafromTVyoutube() {
        Call<TrailersClass> tvClassCall = ApiClient.getINSTANCE().getMoviesInterface().gettvtrailers(b,API_key,LANGUGAGE);
        tvClassCall.enqueue(new Callback<TrailersClass>() {
            @Override
            public void onResponse(Call<TrailersClass> call, Response<TrailersClass> response) {
                if(response.body()!=null) {
                    TrailersClass trailersClass = response.body();
                    trailerslist.clear();
                    if(response.body().getResults().size()!=0) {
                        trailerslist.addAll(trailersClass.getResults());
                        trailers.setVisibility(View.VISIBLE);
                    }
                    trailerAdapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<TrailersClass> call, Throwable t) {

            }
        });
    }

    private void createformovietrailers() {
        refresh.setRefreshing(true);
        refresh.setColorSchemeColors( getResources().getColor(R.color.colorsplash),getResources().getColor(R.color.coloryellow));
        trailerslist = new ArrayList<>();
        trailerrecycler = findViewById(R.id.recycleryoutube);
        fetchdatafromyoutube();
        trailerAdapter = new trailerAdapter(trailerslist,this,this);
        trailerrecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        trailerrecycler.setAdapter(trailerAdapter);
        refresh.setRefreshing(false);
    }

    private void fetchdatafromyoutube() {
        Call<TrailersClass> tvClassCall = ApiClient.getINSTANCE().getMoviesInterface().getmovietrailers(b,API_key,LANGUGAGE);
        tvClassCall.enqueue(new Callback<TrailersClass>() {
            @Override
            public void onResponse(Call<TrailersClass> call, Response<TrailersClass> response) {
                if(response.body()!=null) {
                  TrailersClass trailersClass = response.body();
                  trailerslist.clear();
                  if(response.body().getResults().size()!=0) {
                      trailerslist.addAll(trailersClass.getResults());
                      trailers.setVisibility(View.VISIBLE);
                  }trailerAdapter.notifyDataSetChanged();

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
        adapter =  new DescriptionAdapter(List, this, new DescriptionAdapter.onCastItemclick() {
            @Override
            public void oncastclick(int position) {
                MovieCredits.CastBean credits = List.get(position);
                int id = credits.getId();
                Intent intent = new Intent(MainActivity.this,actorInfo.class);
                intent.putExtra(CAST_ID,id);
                startActivity(intent);
            }
        });
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
                    if(root.getCast().size()!=0) {
                        List.addAll(root.getCast());
                        cast.setVisibility(View.VISIBLE);
                    }
                    adapter.notifyDataSetChanged();

                }

            }
            @Override
            public void onFailure(Call<MovieCredits> call, Throwable t) {
                Toast.makeText(MainActivity.this, "check the internet connection!!", Toast.LENGTH_SHORT).show();
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
