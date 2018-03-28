package com.example.gopalawasthi.movielovers;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.content.res.Resources;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Gopal Awasthi on 19-03-2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder>{

    List<Nowplaying.ResultsBean> resultBeans;
    Context context;
    onitemclicklistener listener;
    interface onitemclicklistener{
        void onItemclick(int position);
    }


    public static final String IMAGE ="http://image.tmdb.org/t/p/w780";
    public MoviesAdapter(List<Nowplaying.ResultsBean> resultBeans, Context context,onitemclicklistener listener) {
        this.resultBeans = resultBeans;
        this.context = context;
        this.listener =listener;

    }



    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.custom_nowplayingview,parent,false);
        MovieHolder holder = new MovieHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieHolder holder, final int position) {
        Nowplaying.ResultsBean bean = resultBeans.get(position);
        holder.name.setText(bean.getTitle());
       String  a=  Float.toString((float) bean.getVote_average());
        holder.rating.setText(a);
//        String b =bean.getBackdrop_path();
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            listener.onItemclick(holder.getAdapterPosition());
            }



        });
        Picasso.get()
                .load(IMAGE+bean.getBackdrop_path())
                .fit()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return resultBeans.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder{
        TextView name ;
        ImageView imageView;
        TextView rating;
        View itemView;

         MovieHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = itemView.findViewById(R.id.nowshowingtitle);
            imageView = itemView.findViewById(R.id.imagenowplaying);
            rating = itemView.findViewById(R.id.userrating);
        }
    }
}