package com.example.gopalawasthi.movielovers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gopal Awasthi on 19-03-2018.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder>{

    List<Nowplaying.ResultBean> resultBeans;
    Context context;

    public MoviesAdapter(List<Nowplaying.ResultBean> resultBeans, Context context) {
        this.resultBeans = resultBeans;
        this.context = context;
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
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Nowplaying.ResultBean bean = resultBeans.get(position);
        holder.name.setText(bean.getTitle());
        holder.rating.setText(bean.getVote_average()+"");
        Picasso.get().load(bean.getPoster_id()).into(holder.imageView);

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
        public MovieHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = itemView.findViewById(R.id.nowshowingtitle);
            imageView = itemView.findViewById(R.id.imagenowplaying);
            rating = itemView.findViewById(R.id.userrating);


        }
    }
}
