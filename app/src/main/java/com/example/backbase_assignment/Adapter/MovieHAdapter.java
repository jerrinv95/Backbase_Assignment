package com.example.backbase_assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backbase_assignment.R;
import com.example.backbase_assignment.Model.MovieList;
import com.example.backbase_assignment.MovieDetailView;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MovieHAdapter extends RecyclerView.Adapter<MovieHAdapter.MyViewHolder> implements View.OnClickListener {

    public static final String POSTER = "poster";
    private int pos;

    private List<MovieList> movieLists;
    private Context context;

    public MovieHAdapter(List<MovieList> movieLists, Context context)
    {
        this.movieLists = movieLists;
        this.context = context;

    }
    @Override
    public void onClick(View view) {

        if (view.getTag() != null)
            pos = Integer.valueOf(String.valueOf(view.getTag()));

        MovieList movieList = movieLists.get(pos);

        context.startActivity(new Intent(context, MovieDetailView.class).setFlags(FLAG_ACTIVITY_NEW_TASK)
                .putExtra("TITLE",movieList.getTitle())
                .putExtra("RDATE",movieList.getReleaseDate())
                .putExtra("OVERVIEW",movieList.getOverview())
                .putExtra("GENRE",movieList.getGenreId())
                .putExtra("POSTER", "https://image.tmdb.org/t/p/w500"+movieList.getPosterPath()));

    }

    @NonNull
    @Override
    public MovieHAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movieh_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final MovieList movieList = movieLists.get(position);
        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(this);


        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500"+movieList.getPosterPath())
                .into(holder.imageview);




    }



    @Override
    public int getItemCount() {
        return movieLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageview;
        public LinearLayout linearLayout;
        View mView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            imageview = itemView.findViewById(R.id.hposter);
            mView = itemView;
            linearLayout = itemView.findViewById(R.id.hlinearLayout);

        }
    }
}
