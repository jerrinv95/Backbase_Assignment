package com.example.backbase_assignment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.backbase_assignment.R;
import com.squareup.picasso.Picasso;

public class MovieDetailView extends AppCompatActivity {

    Toolbar toolbar;
    TextView title;
    TextView rdate;
    TextView overview;
    TextView genre;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_view);
        Bundle bundle = getIntent().getExtras();
        title = findViewById(R.id.title);
        title.setText(bundle.getString("TITLE"));
        rdate = findViewById(R.id.releasedate);
        rdate.setText(bundle.getString("RDATE"));
        overview=findViewById(R.id.overview);
        overview.setText(bundle.getString("OVERVIEW"));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        genre=findViewById(R.id.genre);
        genre.setText(bundle.getString("GENRE"));
        setSupportActionBar(toolbar);
        poster = findViewById(R.id.posterimage);
        Picasso.with(this)
                .load(bundle.getString("POSTER"))
                .into(poster);

        toolbar.setNavigationIcon(R.drawable.back);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


}