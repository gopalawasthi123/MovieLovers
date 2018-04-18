package com.example.gopalawasthi.movielovers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wang.avi.AVLoadingIndicatorView;

import retrofit2.Callback;
import retrofit2.Response;

import static com.example.gopalawasthi.movielovers.MainActivity.CAST_ID;
import static com.example.gopalawasthi.movielovers.MovieFragment.API_key;
import static com.example.gopalawasthi.movielovers.MovieFragment.LANGUGAGE;
import static com.example.gopalawasthi.movielovers.popularmovieadapter.IMAGE;

public class actorInfo extends AppCompatActivity {
    TextView actorname;
    TextView dob;
    TextView popularity;
    TextView birthplace;
    ImageView actorimage;
    TextView biography;
    private  int id;
    AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_info);
        actorname = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        popularity = findViewById(R.id.popularity);
        birthplace = findViewById(R.id.birthplace);
        actorimage = findViewById(R.id.actorimage);
        biography =  findViewById(R.id.biography);
        avi = findViewById(R.id.actoravi);
        avi.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        id = intent.getIntExtra(CAST_ID,-1);
        retrofit2.Call<ActorBiopic> actorBiopicCall = ApiClient.getINSTANCE().getMoviesInterface().getActorInfo(id,API_key,LANGUGAGE);
        actorBiopicCall.enqueue(new Callback<ActorBiopic>() {
            @Override
            public void onResponse(retrofit2.Call<ActorBiopic> call, Response<ActorBiopic> response) {
            if(response.isSuccessful()){

                actorname.setText(response.body().getName());
                dob.setText(response.body().getBirthday());
                birthplace.setText(response.body().getPlace_of_birth());
                biography.setText(response.body().getBiography());
                double a = response.body().getPopularity();
                popularity.setText(a +"");
                Picasso.get().load(IMAGE + response.body().getProfile_path()).fit().into(actorimage);
                avi.setVisibility(View.GONE);
            }

            }

            @Override
            public void onFailure(retrofit2.Call<ActorBiopic> call, Throwable t) {

            }
        });
    }
}
