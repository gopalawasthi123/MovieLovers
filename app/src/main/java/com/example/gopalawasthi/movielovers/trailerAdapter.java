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
 * Created by Gopal Awasthi on 06-04-2018.
 */

public class trailerAdapter extends RecyclerView.Adapter<trailerAdapter.TrailerHolder> {

    List<TrailersClass.ResultsBean> list;
    Context context;
    ontrailerclickListener listener;
    interface ontrailerclickListener{
        void ontrailerClick(int position);
    }

    public trailerAdapter(List<TrailersClass.ResultsBean> list, Context context, ontrailerclickListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TrailerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.trailer_layout,parent,false);
        TrailerHolder holder = new TrailerHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TrailerHolder holder, final int position) {
        TrailersClass.ResultsBean resultsBean =  list.get(position);
        holder.name.setText(resultsBean.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ontrailerClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TrailerHolder extends RecyclerView.ViewHolder{
        View itemView;
        ImageView imageView;
        TextView name;
        public TrailerHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.trailerimages);
            name = itemView.findViewById(R.id.trailername);

        }
    }

}
