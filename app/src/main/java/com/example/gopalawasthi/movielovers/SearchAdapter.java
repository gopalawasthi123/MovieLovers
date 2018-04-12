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
 * Created by Gopal Awasthi on 08-04-2018.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
   List<SearchClass.ResultsBean> resultsBeans;
   Context context;
   searchmovielistener listener;
    interface  searchmovielistener{
        void onmovieclick(int position);
    }

    public SearchAdapter(List<SearchClass.ResultsBean> resultsBeans, Context context,searchmovielistener listener ) {
        this.resultsBeans = resultsBeans;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View v = layoutInflater.inflate(R.layout.custom_search,parent,false);
         SearchHolder holder = new SearchHolder(v);
         return  holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final SearchHolder holder, int position) {
        SearchClass.ResultsBean searchClass = resultsBeans.get(position);
        String name = searchClass.getOriginal_title();
        holder.name.setText(name);
        String a = searchClass.getBackdrop_path();
        Picasso.get().load(IMAGE+a).fit().into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onmovieclick(holder.getAdapterPosition());
            }
        });
        holder.description.setText(searchClass.getOverview());

    }

    @Override
    public int getItemCount() {
        return resultsBeans.size();
    }

    class SearchHolder extends RecyclerView.ViewHolder{
       View itemView;
       ImageView imageView;
       TextView name;
       TextView description;

        public SearchHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.searchimage);
            name = itemView.findViewById(R.id.searchtitle);
            description = itemView.findViewById(R.id.searchdescription);

        }

    }
}
