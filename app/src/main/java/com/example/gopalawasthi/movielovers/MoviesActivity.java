package com.example.gopalawasthi.movielovers;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CATEGORY = "now_playing";
    public static final String LANGUGAGE = "en-US";
    public static final int PAGE =1;
    public static final String API_key ="f05e7eb1cb1d184b717962fc1230e9c1";

    RecyclerView recyclerView;

    MoviesAdapter adapter;
    List<Nowplaying.ResultBean> arrayList;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressBar = findViewById(R.id.progressbar);
        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);

            fetchdatafromnetwork();
            adapter =  new MoviesAdapter(arrayList,this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //ToDo nowplaying fetch moives
        private void fetchdatafromnetwork() {

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        Call<Nowplaying> nowplayingCall = ApiClient.getINSTANCE().getMoviesInterface().getnowplayingMovies(CATEGORY,API_key,LANGUGAGE,PAGE);
        nowplayingCall.enqueue(new Callback<Nowplaying>() {
            @Override
            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
                Nowplaying root =response.body();
                Nowplaying.ResultBean resultBean =(Nowplaying.ResultBean) root.getResults();
               arrayList.clear();
               arrayList.addAll(root.getResults());
               adapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Nowplaying> call, Throwable t) {
                Toast.makeText(MoviesActivity.this, t+"", Toast.LENGTH_LONG).show();
                Log.d("movies",t+"");
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
}
