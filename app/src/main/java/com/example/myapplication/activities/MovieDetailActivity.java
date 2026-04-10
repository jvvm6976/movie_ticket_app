package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.myapplication.databinding.ActivityMovieDetailBinding;
import com.example.myapplication.models.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMovieDetailBinding binding;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        movie = (Movie) getIntent().getSerializableExtra("movie");

        if (movie != null) {
            binding.tvTitle.setText(movie.getTitle());
            binding.tvDescription.setText(movie.getDescription());
            binding.tvGenreDuration.setText(movie.getGenre() + " • " + movie.getDuration());
            
            Glide.with(this)
                    .load(movie.getPosterUrl())
                    .placeholder(android.R.color.darker_gray)
                    .into(binding.ivPosterDetail);
        }

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        binding.btnBookNow.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShowtimeListActivity.class);
            intent.putExtra("movieId", movie.getMovieId());
            intent.putExtra("movieTitle", movie.getTitle());
            startActivity(intent);
        });
    }
}