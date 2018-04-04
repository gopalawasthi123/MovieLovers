package com.example.gopalawasthi.movielovers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import java.util.List;

public class MoviesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,MovieFragment.onMovieClickInterfacecallback {

//    TextView viewallnowplaying;
//    SearchView searchView;
//    public static final String CATEGORY = "now_playing";
//    public static final String POPULAR_CATEGORY= "popular";
//    public static final String TOPRATED_CATEGORY ="top_rated";
//    public static final String LANGUGAGE ="en-US";
//    public static final String UPCOMING_CATEGORY = "upcoming";
//    public static final int PAGE =1;
//    public static final String API_key ="f05e7eb1cb1d184b717962fc1230e9c1";
//    int screenSize;
//    CustomSwipetoRefresh swipeRefreshLayout ;
//    RecyclerView recyclerView;
//    RecyclerView recyclerpopular;
//    MoviesAdapter adapter;
//    popularmovieadapter popularmovieadapter;
//    List<Nowplaying.ResultsBean> popularList;
//    List<Nowplaying.ResultsBean> toprated;
//    topratedAdapter topratedAdapter;
    List<Nowplaying.ResultsBean> ListNow;
//    ProgressBar progressBar;
//    LinearLayout headernowplaying;
//    LinearLayout headerpopular;
//    RecyclerView recyclertoprated;
//    RecyclerView recyclerupcoming;
//    List<Nowplaying.ResultsBean> upcominglist;
//    MoviesAdapter myupcoming;
//    Moviedatabase moviedatabase;
//    MoviesDao dao;
//

//    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        navigationView.setNavigationItemSelectedListener(this);
        setfragment(new MovieFragment());


//        //TOdo connectivity manager for the internet check
//
//
////         gridLayoutManager = new GridLayoutManager(this,2,LinearLayoutManager.HORIZONTAL,false);
////        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
////            @Override
////            public int getSpanSize(int position) {
////                return position == 10 ? 2:1 ;
////            }
////        });
//
//        // for now playing
//        createfornowplaying();
//
//        //for popularmovies
//        creaforpopularmovies();
//
//        //for top-ratedmovies
//        createfortoprated();
//
//        //for upcoming movies
//        createforupcoming();
//
//
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                createfornowplaying();
//                creaforpopularmovies();
//                createfortoprated();
//                createforupcoming();
//
//            }
//        });
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//    }
//
//    private void createforupcoming() {
//        recyclerupcoming = findViewById(R.id.upcomingrecycler);
//        upcominglist = new ArrayList<>();
//        fetchdataforupcoming();
//        myupcoming = new MoviesAdapter(upcominglist, this, new MoviesAdapter.onitemclicklistener() {
//            @Override
//            public void onItemclick(int position) {
//                Intent intent = new Intent(MoviesActivity.this,MainActivity.class);
//                int a = upcominglist.get(position).getId();
//                String b =  upcominglist.get(position).getTitle();
//                intent.putExtra("movieid",a);
//                intent.putExtra("moviename",b);
//                intent.putExtra("movieposter",upcominglist.get(position).getPoster_path());
//                intent.putExtra("moviebackdrop",upcominglist.get(position).getBackdrop_path());
//                intent.putExtra("description",upcominglist
//                        .get(position).getOverview());
//                startActivity(intent);
//                Toast.makeText(MoviesActivity.this, "position is "+position, Toast.LENGTH_SHORT).show();
//            }
//        });
//        recyclerupcoming.setAdapter(myupcoming);
//        recyclerupcoming.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerupcoming.setOnFlingListener(null);
//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerupcoming);
//    }
//
//    private void fetchdataforupcoming() {
//        Call<Nowplaying> topRatedCall = ApiClient.getINSTANCE().getMoviesInterface().gettopRatedMovies(UPCOMING_CATEGORY,API_key,LANGUGAGE,PAGE);
//        topRatedCall.enqueue(new Callback<Nowplaying>() {
//            @Override
//            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
//                if(response.body()!=null){
//                    upcominglist.clear();
//                    upcominglist.addAll(response.body().getResults());
//
//                    myupcoming.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Nowplaying> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void createfortoprated() {
//        recyclertoprated = findViewById(R.id.thirdrecycler);
//        toprated = new ArrayList<>();
//        fetchdatafortoprated();
//        topratedAdapter = new topratedAdapter(this,toprated,this);
//        recyclertoprated.setAdapter(topratedAdapter);
//        recyclertoprated.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclertoprated.setOnFlingListener(null);
//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclertoprated);
//
//    }
//
//    private void fetchdatafortoprated() {
//        Call<Nowplaying> topRatedCall = ApiClient.getINSTANCE().getMoviesInterface().gettopRatedMovies(TOPRATED_CATEGORY,API_key,LANGUGAGE,PAGE);
//        topRatedCall.enqueue(new Callback<Nowplaying>() {
//            @Override
//            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
//                if(response.body()!=null){
//                    toprated.clear();
//                    toprated.addAll(response.body().getResults());
//
//                    topratedAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Nowplaying> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void creaforpopularmovies() {
//    recyclerpopular = findViewById(R.id.secondrecycler);
//        popularList = new ArrayList<>();
//        recyclerpopular.setVisibility(View.GONE);
//        headerpopular.setVisibility(View.GONE);
//
//        fetchdataforpopular();
//
//        popularmovieadapter = new popularmovieadapter(this,popularList,this);
//        recyclerpopular.setAdapter(popularmovieadapter);
//        recyclerpopular.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerpopular.setOnFlingListener(null);
//       SnapHelper snapHelper = new LinearSnapHelper();
//       snapHelper.attachToRecyclerView(recyclerpopular);
//
//
//    }
//
//    private void fetchdataforpopular() {
//
//        Call<Nowplaying> popularMoviesCall = ApiClient.getINSTANCE().getMoviesInterface().getpopularMovies(POPULAR_CATEGORY,API_key,LANGUGAGE,PAGE);
//        popularMoviesCall.enqueue(new Callback<Nowplaying>() {
//            @Override
//            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
//                if(response.isSuccessful()){
//                    Nowplaying popularMovies = response.body();
//                    popularList.clear();
//                    popularList.addAll(popularMovies.getResults());
//                    popularmovieadapter.notifyDataSetChanged();
//
//                    recyclerpopular.setVisibility(View.VISIBLE);
//                    headerpopular.setVisibility(View.VISIBLE);
//
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Nowplaying> call, Throwable t) {
//
//            }
//        });
//
//    }
//
//
//    private void createfornowplaying() {
//        swipeRefreshLayout.setRefreshing(true);
//        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark),getResources().getColor(R.color.colorWhite));
//        ListNow = new ArrayList<>();
//        recyclerView = findViewById(R.id.recyclerview);
////        movieDatabase = Room.databaseBuilder(this,MovieDatabase.class,"mymovies").allowMainThreadQueries().build();
////              movieDao=  movieDatabase.getMovieDao();
////              List<Nowplaying.ResultsBean> list =movieDao.getnowplaing();
//
//        moviedatabase = Moviedatabase.getINSTANCE(this);
//        dao = moviedatabase.getMovieDao();
//        List<Nowplaying.ResultsBean> mydao = dao.getallmovies();
//        Log.d("movies",mydao.get(2).getTitle());
////        recyclerView.setVisibility(View.GONE);
////        headernowplaying.setVisibility(View.GONE);
//        adapter =  new MoviesAdapter(ListNow, this,this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
////        recyclerView.setItemAnimator( new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
//
//        recyclerView.setOnFlingListener(null);
//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(recyclerView);
//        ListNow.clear();
//        ListNow.addAll(mydao);
//        adapter.notifyDataSetChanged();
//        fetchdatafromnetwork();
//
//
//        swipeRefreshLayout.setRefreshing(false);
//
////        List.clear();
////        List.addAll(list);
////        adapter.notifyDataSetChanged();
//
//    }
//
//        private void fetchdatafromnetwork() {
//
//        Call<Nowplaying> nowplayingCall = ApiClient.getINSTANCE().getMoviesInterface().getnowplayingMovies(CATEGORY,API_key,LANGUGAGE,PAGE);
//        nowplayingCall.enqueue(new Callback<Nowplaying>() {
//            @Override
//            public void onResponse(Call<Nowplaying> call, Response<Nowplaying> response) {
//                if (response.isSuccessful()) {
//                    Nowplaying root = response.body();
//                    ListNow.clear();
//                    ListNow.addAll(root.getResults());
//                    adapter.notifyDataSetChanged();
//                    dao.oninsertMovies(ListNow);
//                    recyclerView.setVisibility(View.VISIBLE);
//                    headernowplaying.setVisibility(View.VISIBLE);
//                }
//            }
//            @Override
//            public void onFailure(Call<Nowplaying> call, Throwable t) {
//
//            }
//        });
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main2, menu);
//        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_settings));
//        SearchManager searchManager =(SearchManager) getSystemService(SEARCH_SERVICE);
//        if(searchManager.getSearchableInfo(getComponentName())!=null) {
//            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    public void showallnowplaying(View view) {
//        Intent intent = new Intent(this,ShowallList.class);
//        intent.addCategory("nowplaying");
//        startActivity(intent);
//
//    }
//
//
//    public void showallpopular(View view) {
//        Intent intent = new Intent(this,ShowallList.class);
//        intent.addCategory("popular");
//        startActivity(intent);
//    }
//
//    public void showalltoprated(View view) {
//        Intent intent = new Intent(this,ShowallList.class);
//        intent.addCategory("toprated");
//        startActivity(intent);
//    }
//
//    @Override
//    public void onItemclick(int position) {
//        Intent intent = new Intent(this,MainActivity.class);
//        int a = ListNow.get(position).getId();
//      String b =  ListNow.get(position).getTitle();
//        intent.putExtra("movieid",a);
//        intent.putExtra("moviename",b);
//        intent.putExtra("movieposter",ListNow.get(position).getPoster_path());
//        intent.putExtra("moviebackdrop",ListNow.get(position).getBackdrop_path());
//        intent.putExtra("description",ListNow.get(position).getOverview());
//        startActivity(intent);
//        Toast.makeText(this, "position is "+position, Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void OnitemClick(int position) {
//        Intent intent = new Intent(this,MainActivity.class);
//        int a = popularList.get(position).getId();
//        String b =  popularList.get(position).getTitle();
//        intent.putExtra("movieid",a);
//        intent.putExtra("moviename",b);
//        intent.putExtra("movieposter",popularList.get(position).getPoster_path());
//        intent.putExtra("moviebackdrop",popularList.get(position).getBackdrop_path());
//        intent.putExtra("description",popularList.get(position).getOverview());
//        startActivity(intent);
//        Toast.makeText(this, "position is "+position, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void OnitemClicktop(int position) {
//        Intent intent = new Intent(this,MainActivity.class);
//        int a = toprated.get(position).getId();
//        String b =  toprated.get(position).getTitle();
//        intent.putExtra("movieid",a);
//        intent.putExtra("moviename",b);
//        intent.putExtra("movieposter",toprated.get(position).getPoster_path());
//        intent.putExtra("moviebackdrop",toprated.get(position).getBackdrop_path());
//        intent.putExtra("description",toprated
//                .get(position).getOverview());
//        startActivity(intent);
//        Toast.makeText(this, "position is "+position, Toast.LENGTH_SHORT).show();
//    }
//
//    public void showallupcoming(View view) {
//        Intent intent = new Intent(this,ShowallList.class);
//        intent.addCategory("upcoming");
//        startActivity(intent);
//
//
   }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_camera) {

            setfragment(new MovieFragment());
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            setfragment(new TvFragment());
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    private void setfragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.moviecontainer,fragment).commit();


    }

    @Override
    public void onmovieClick(Nowplaying.ResultsBean nowplaying) {
        Intent intent = new Intent(this,MainActivity.class);

        int a =  nowplaying.getId();
      String b =  nowplaying.getTitle();
        intent.putExtra("movieid",a);
        intent.putExtra("moviename",b);
        intent.putExtra("movieposter",nowplaying.getPoster_path());
        intent.putExtra("moviebackdrop",nowplaying.getBackdrop_path());
        intent.putExtra("description",nowplaying.getOverview());
        startActivity(intent);
    }

    @Override
    public void onpopularmovieClick(Nowplaying.ResultsBean popular) {
        Intent intent = new Intent(this,MainActivity.class);
        int a = popular.getId();
        String b =  popular.getTitle();
        intent.putExtra("movieid",a);
        intent.putExtra("moviename",b);
        intent.putExtra("movieposter",popular.getPoster_path());
        intent.putExtra("moviebackdrop",popular.getBackdrop_path());
        intent.putExtra("description",popular.getOverview());
        startActivity(intent);

    }

    @Override
    public void ontopratedmovieClick(Nowplaying.ResultsBean toprated) {
        Intent intent = new Intent(this,MainActivity.class);
        int a = toprated.getId();
        String b =  toprated.getTitle();
        intent.putExtra("movieid",a);
        intent.putExtra("moviename",b);
        intent.putExtra("movieposter",toprated.getPoster_path());
        intent.putExtra("moviebackdrop",toprated.getBackdrop_path());
        intent.putExtra("description",toprated
                .getOverview());
        startActivity(intent);


    }

    @Override
    public void onupcomingmovieclick(Nowplaying.ResultsBean upcoming) {
        Intent intent = new Intent(this,MainActivity.class);

        int a =  upcoming.getId();
        String b =  upcoming.getTitle();
        intent.putExtra("movieid",a);
        intent.putExtra("moviename",b);
        intent.putExtra("movieposter",upcoming.getPoster_path());
        intent.putExtra("moviebackdrop",upcoming.getBackdrop_path());
        intent.putExtra("description",upcoming.getOverview());
        startActivity(intent);
    }

    public void showallnowplaying(View view) {
        Intent intent = new Intent(this,ShowallList.class);
        intent.addCategory("nowplaying");
        startActivity(intent);

    }

    public void showallpopular(View view) {
        Intent intent = new Intent(this,ShowallList.class);
        intent.addCategory("popular");
        startActivity(intent);
    }

    public void showalltoprated(View view) {
        Intent intent = new Intent(this,ShowallList.class);
        intent.addCategory("toprated");
        startActivity(intent);
    }


    public void showallupcoming(View view) {
        Intent intent = new Intent(this,ShowallList.class);
        intent.addCategory("upcoming");
        startActivity(intent);
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


}
