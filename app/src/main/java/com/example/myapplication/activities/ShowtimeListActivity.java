package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.myapplication.adapters.ShowtimeAdapter;
import com.example.myapplication.databinding.ActivityShowtimeListBinding;
import com.example.myapplication.firebase.FirebaseHelper;
import com.example.myapplication.models.Showtime;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class ShowtimeListActivity extends AppCompatActivity {

    private ActivityShowtimeListBinding binding;
    private ShowtimeAdapter adapter;
    private List<Showtime> showtimeList;
    private String movieId, theaterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowtimeListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        movieId = getIntent().getStringExtra("movieId");
        theaterId = getIntent().getStringExtra("theaterId");

        String title = getIntent().getStringExtra("movieTitle");
        if (title == null) title = getIntent().getStringExtra("theaterName");
        if (title != null) binding.tvToolbarTitle.setText("Showtimes: " + title);

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        showtimeList = new ArrayList<>();
        adapter = new ShowtimeAdapter(showtimeList, showtime -> {
            Intent intent = new Intent(this, SeatSelectionActivity.class);
            intent.putExtra("showtime", showtime);
            intent.putExtra("movieTitle", getIntent().getStringExtra("movieTitle"));
            startActivity(intent);
        });

        binding.rvShowtimes.setLayoutManager(new LinearLayoutManager(this));
        binding.rvShowtimes.setAdapter(adapter);

        loadShowtimes();
    }

    private void loadShowtimes() {
        Query query = FirebaseHelper.getDb().collection("showtimes");
        
        if (movieId != null) {
            query = query.whereEqualTo("movieId", movieId);
        } else if (theaterId != null) {
            query = query.whereEqualTo("theaterId", theaterId);
        }

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                showtimeList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Showtime showtime = document.toObject(Showtime.class);
                    showtime.setShowtimeId(document.getId());
                    showtimeList.add(showtime);
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}