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
 * Created by Gopal Awasthi on 23-03-2018.
 */

public class topratedAdapter extends RecyclerView.Adapter<topratedAdapter.TopRatedHolder> {

    Context context;
    List<TopRated.ResultsBean> list;
    public static final String IMAGE = "http://image.tmdb.org/t/p/w1280";

    public topratedAdapter(Context context, List<TopRated.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public TopRatedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.custom_nowplayingview, parent, false);
        topratedAdapter.TopRatedHolder holder = new topratedAdapter.TopRatedHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedHolder holder, int position) {
     TopRated.ResultsBean bean = list.get(position);
     String a = bean.getBackdrop_path();
        Picasso.get().load(IMAGE+a).fit().into(holder.imageView);
      holder.name.setText(bean.getTitle());
        String  b=  Float.toString((float) bean.getVote_average());
      holder.rating.setText(b);
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
