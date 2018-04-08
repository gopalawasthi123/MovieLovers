package com.example.gopalawasthi.movielovers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SearchActvity extends AppCompatActivity {
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
        adapter = new SearchAdapter(Searchlist,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }

    private void fetchdataforSearch() {
        Call<SearchClass> searchClassCall = ApiClient.getINSTANCE().getMoviesInterface().getsearchall(API_key,LANGUGAGE,query,PAGE,false);
        searchClassCall.enqueue(new Callback<SearchClass>() {
            @Override
            public void onResponse(Call<SearchClass> call, Response<SearchClass> response) {

                Searchlist.clear();
                Searchlist.addAll(response.body().getResults());
              //  Log.d("SearchList",Searchlist.toString());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<SearchClass> call, Throwable t) {
                Toast.makeText(SearchActvity.this, t+"", Toast.LENGTH_LONG).show();
                Log.d("SearchList",t+"");
            }
        });
    }


}
