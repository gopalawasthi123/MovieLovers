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

import static android.icu.lang.UProperty.AGE;
import static com.example.gopalawasthi.movielovers.MoviesAdapter.IMAGE;

/**
 * Created by Gopal Awasthi on 27-03-2018.
 */

public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.MyHolder> {
    List<MovieCredits.CastBean> beans ;
    Context context;
    onCastItemclick listener;
    interface onCastItemclick{
        void oncastclick(int position);
    }

    public DescriptionAdapter(List<MovieCredits.CastBean> beans, Context context,onCastItemclick listener) {
        this.beans = beans;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =layoutInflater.inflate(R.layout.custompopularmovies,parent,false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        MovieCredits.CastBean resultsBean = beans.get(position);
     //    holder.description.setText(resultsBean.);
         Picasso.get().load(IMAGE+resultsBean.getProfile_path()).fit().into(holder.moviecredits);
         holder.moviecredits.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 listener.oncastclick(holder.getAdapterPosition());
             }
         });
         holder.actorname.setText(resultsBean.getName());
//         Picasso.get().load(IMAGE+resultsBean.getPoster_path()).fit().into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        View itemView;
        ImageView moviecredits;
        TextView actorname;

        public MyHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
                moviecredits = itemView.findViewById(R.id.moviecredits);
                actorname = itemView.findViewById(R.id.actorname);
        }
    }
}
