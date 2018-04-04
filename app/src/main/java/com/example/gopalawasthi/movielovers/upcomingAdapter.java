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

/**
 * Created by Gopal Awasthi on 04-04-2018.
 */

public class upcomingAdapter extends RecyclerView.Adapter<upcomingAdapter.TopRatedHolder > {


    Context context;
    List<Nowplaying.ResultsBean> list;
    public static final String IMAGE = "http://image.tmdb.org/t/p/w780";

    public upcomingAdapter(Context context, List<Nowplaying.ResultsBean> list, OnItemCickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    interface OnItemCickListener{
        void OnitemClickupcoming(int position);
    }
    OnItemCickListener listener;



    @NonNull
    @Override
    public TopRatedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.custom_nowplayingview, parent, false);
        upcomingAdapter.TopRatedHolder holder = new upcomingAdapter.TopRatedHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final TopRatedHolder holder, int position) {
        Nowplaying.ResultsBean bean = list.get(position);
        String a = bean.getBackdrop_path();
        Picasso.get().load(IMAGE+a).fit().into(holder.imageView);
        holder.name.setText(bean.getTitle());
        String  b=  Float.toString((float) bean.getVote_average());
        holder.rating.setText(b);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnitemClickupcoming(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TopRatedHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;
        TextView rating;
        View itemView;

        public TopRatedHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = itemView.findViewById(R.id.nowshowingtitle);
            imageView = itemView.findViewById(R.id.imagenowplaying);
            rating = itemView.findViewById(R.id.userrating);
        }

    }

}