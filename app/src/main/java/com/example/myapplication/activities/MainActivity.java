package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.firebase.FirebaseHelper;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (FirebaseHelper.getCurrentUser() != null) {
            binding.tvUserEmail.setText(FirebaseHelper.getCurrentUser().getEmail());
        }

        binding.cardMovies.setOnClickListener(v -> startActivity(new Intent(this, MovieListActivity.class)));
        binding.cardTheaters.setOnClickListener(v -> startActivity(new Intent(this, TheaterListActivity.class)));
        binding.cardMyTickets.setOnClickListener(v -> startActivity(new Intent(this, MyTicketsActivity.class)));
        binding.cardProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));

        binding.btnLogout.setOnClickListener(v -> {
            FirebaseHelper.logout();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }
}