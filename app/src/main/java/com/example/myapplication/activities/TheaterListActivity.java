package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.myapplication.adapters.TheaterAdapter;
import com.example.myapplication.databinding.ActivityTheaterListBinding;
import com.example.myapplication.firebase.FirebaseHelper;
import com.example.myapplication.models.Theater;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class TheaterListActivity extends AppCompatActivity {

    private ActivityTheaterListBinding binding;
    private TheaterAdapter adapter;
    private List<Theater> theaterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTheaterListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        theaterList = new ArrayList<>();
        adapter = new TheaterAdapter(theaterList, theater -> {
            Intent intent = new Intent(this, ShowtimeListActivity.class);
            intent.putExtra("theaterId", theater.getTheaterId());
            intent.putExtra("theaterName", theater.getName());
            startActivity(intent);
        });

        binding.rvTheaters.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTheaters.setAdapter(adapter);

        loadTheaters();
    }

    private void loadTheaters() {
        FirebaseHelper.getDb().collection("theaters")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        theaterList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Theater theater = document.toObject(Theater.class);
                            theater.setTheaterId(document.getId());
                            theaterList.add(theater);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}