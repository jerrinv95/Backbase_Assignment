package com.example.backbase_assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.backbase_assignment.R;
import com.example.backbase_assignment.Model.MovieList;
import com.example.backbase_assignment.MovieDetailView;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements View.OnClickListener {

    public static final String TITLE = "title";
    public static final String RDATE = "releasedate";
    public static final String VOTE = "voteaverage";
    public static final String POSTER = "poster";
    private int pos;

    private List<MovieList> movieLists;
    private Context context;

    public MovieAdapter(List<MovieList> movieLists, Context context)
    {
        this.movieLists = movieLists;
        this.context = context;

    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {

        final MovieList movieList = movieLists.get(position);
        holder.itemView.setTag(position);
        holder.mtitle.setText(movieList.getTitle());
        holder.rdate.setText(movieList.getReleaseDate());
        holder.itemView.setOnClickListener(this);
        //holder.voteaverage.setText("Ratings: "+movieList.getVoteAverage()+"/10");
        Float convert = Float.parseFloat(movieList.getVoteAverage())/2;
        holder.ratingBar.setRating(convert);



        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500"+movieList.getPosterPath())
                .into(holder.imageview);



    }

    @Override
    public int getItemCount() {
        return movieLists.size();
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


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mtitle;
        public TextView rdate;
        public TextView voteaverage;
        public RelativeLayout linearLayout;
        public RatingBar ratingBar;
        public ImageView imageview;
        View mView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mtitle = itemView.findViewById(R.id.movietitle);
            rdate = itemView.findViewById(R.id.releasedate);
            //voteaverage = itemView.findViewById(R.id.voteaverage);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            ratingBar = itemView.findViewById(R.id.ratingbar);
            imageview = itemView.findViewById(R.id.poster);
            mView = itemView;

        }
    }
}
