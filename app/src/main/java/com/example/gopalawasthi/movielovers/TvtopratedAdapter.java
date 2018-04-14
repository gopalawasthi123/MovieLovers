package com.example.gopalawasthi.movielovers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
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
 * Created by Gopal Awasthi on 05-04-2018.
 */

public class TvtopratedAdapter extends RecyclerView.Adapter<TvtopratedAdapter.MyHolder> {
    Context context;
    List<TvClass.ResultsBean> list;
    onitemClicklistener clicklistener;



    interface  onitemClicklistener{
        void onitemclick(int position);
    }
    public TvtopratedAdapter(Context context, List<TvClass.ResultsBean> list, onitemClicklistener clicklistener) {
        this.context = context;
        this.list = list;
        this.clicklistener = clicklistener;
    }

    @NonNull
    @Override
    public TvtopratedAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_nowplayingview,parent,false);
        MyHolder holder = new MyHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TvtopratedAdapter.MyHolder holder, int position) {
       TvClass.ResultsBean resultsBean = list.get(position);
       String a = resultsBean.getBackdrop_path();
        Picasso.get().load(IMAGE+a).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicklistener.onitemclick(holder.getAdapterPosition());
            }
        });
        holder.name.setText(resultsBean.getName());
        String rate = Float.toString((float)resultsBean.getVote_average());
        holder.rating.setText(rate);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        View itemView ;
        ImageView imageView ;
        TextView name ;
        TextView rating;
        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.imagenowplaying);
            name = itemView.findViewById(R.id.nowshowingtitle);
            rating = itemView.findViewById(R.id.userrating);
        }

    }
}
