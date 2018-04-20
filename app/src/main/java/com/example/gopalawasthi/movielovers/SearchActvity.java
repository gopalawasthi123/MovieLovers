package com.example.gopalawasthi.movielovers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gopalawasthi.movielovers.MovieFragment.API_key;
import static com.example.gopalawasthi.movielovers.MovieFragment.LANGUGAGE;
import static com.example.gopalawasthi.movielovers.MovieFragment.PAGE;

public class SearchActvity extends AppCompatActivity implements SearchAdapter.searchmovielistener {
    List<SearchClass.ResultsBean> Searchlist;
    RecyclerView recyclerView;
    SearchAdapter adapter;

    String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_actvity);
        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        Searchlist = new ArrayList<>();
        recyclerView = findViewById(R.id.searchrecycler);
        fetchdataforSearch();
        adapter = new SearchAdapter(Searchlist,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
       // DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
       int color = getResources().getColor(R.color.colorWhite);
        Separatordivider separatordivider = new Separatordivider(this,color,1.5f);
        recyclerView.addItemDecoration(separatordivider);
        recyclerView.setAdapter(adapter);
    }

    private void fetchdataforSearch() {
        Call<SearchClass> searchClassCall = ApiClient.getINSTANCE().getMoviesInterface().getsearchall(API_key,LANGUGAGE,query,PAGE,false);
        searchClassCall.enqueue(new Callback<SearchClass>() {
            @Override
            public void onResponse(Call<SearchClass> call, Response<SearchClass> response) {

                Searchlist.clear();
                Searchlist.addAll(response.body().getResults());
                if(Searchlist.size()==0){
                    Toast.makeText(SearchActvity.this, "No result found!!", Toast.LENGTH_SHORT).show();
                }
              //  Log.d("SearchList",Searchlist.toString());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<SearchClass> call, Throwable t) {
                Toast.makeText(SearchActvity.this, "No Internet Connection!!", Toast.LENGTH_LONG).show();
                Log.d("SearchList",t+"");
            }
        });
    }


    @Override
    public void onmovieclick(int position) {

        Intent intent = new Intent(this,MainActivity.class);
        SearchClass.ResultsBean bean = Searchlist.get(position);
        String type = bean.getMedia_type();
        if(type.equals("movie")){
            int a = Searchlist.get(position).getId();
      String b =  Searchlist.get(position).getTitle();
        intent.putExtra("movieid",a);
        intent.putExtra("moviename",b);
        intent.putExtra("movieposter",Searchlist.get(position).getPoster_path());
        intent.putExtra("moviebackdrop",Searchlist.get(position).getBackdrop_path());
        intent.putExtra("description",Searchlist.get(position).getOverview());
        startActivity(intent);
        }else if(type.equals("tv")){
            int a = Searchlist.get(position).getId();
            String b =  Searchlist.get(position).getTitle();
            intent.addCategory("TV");
            intent.putExtra("movieid",a);
            intent.putExtra("moviename",b);
            intent.putExtra("movieposter",Searchlist.get(position).getPoster_path());
            intent.putExtra("moviebackdrop",Searchlist.get(position).getBackdrop_path());
            intent.putExtra("description",Searchlist.get(position).getOverview());
            startActivity(intent);
        }
    }
}
