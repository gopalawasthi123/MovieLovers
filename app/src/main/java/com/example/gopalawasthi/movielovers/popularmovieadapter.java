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

import java.util.List;

import static com.example.gopalawasthi.movielovers.MoviesAdapter.IMAGE;

/**
 * Created by Gopal Awasthi on 22-03-2018.
 */

public class popularmovieadapter extends RecyclerView.Adapter<popularmovieadapter.MovieHolder>{

    Context context;
    List<Nowplaying.ResultsBean> beans;
    public static final String IMAGE ="http://image.tmdb.org/t/p/w780";
    interface  OnitemClicklistener{
        void OnitemClick(int position);
    }
    OnitemClicklistener clicklistener;
    public popularmovieadapter(Context context, List<Nowplaying.ResultsBean> beans,OnitemClicklistener clicklistener) {
        this.context = context;
        this.beans = beans;
        this.clicklistener = clicklistener;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.custom_nowplayingview,parent,false);
        popularmovieadapter.MovieHolder holder = new popularmovieadapter.MovieHolder(v);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final MovieHolder holder, int position) {
       Nowplaying.ResultsBean bean = beans.get(position);
        holder.name.setText(bean.getTitle());
        String  a=  Float.toString((float) bean.getVote_average());
        holder.rating.setText(a);
        String b =bean.getPoster_path();
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicklistener.OnitemClick(holder.getAdapterPosition());
            }
        });
        Picasso.get().load(IMAGE+bean.getBackdrop_path()).fit().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return beans.size();
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
