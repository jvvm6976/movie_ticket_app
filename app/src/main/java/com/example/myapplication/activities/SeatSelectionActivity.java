package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivitySeatSelectionBinding;
import com.example.myapplication.models.Showtime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SeatSelectionActivity extends AppCompatActivity {

    private ActivitySeatSelectionBinding binding;
    private Showtime showtime;
    private List<String> selectedSeats = new ArrayList<>();
    private double totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySeatSelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showtime = (Showtime) getIntent().getSerializableExtra("showtime");
        String movieTitle = getIntent().getStringExtra("movieTitle");

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        setupSeatGrid();

        binding.btnConfirm.setOnClickListener(v -> {
            if (selectedSeats.isEmpty()) {
                Toast.makeText(this, "Please select at least one seat", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, BookingConfirmActivity.class);
            intent.putExtra("showtime", showtime);
            intent.putExtra("movieTitle", movieTitle);
            intent.putExtra("selectedSeats", new ArrayList<>(selectedSeats));
            intent.putExtra("totalPrice", totalPrice);
            startActivity(intent);
        });
    }

    private void setupSeatGrid() {
        int rows = 5;
        int cols = 5;
        binding.seatGrid.setRowCount(rows);
        binding.seatGrid.setColumnCount(cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char rowChar = (char) ('A' + i);
                String seatName = rowChar + String.valueOf(j + 1);

                CheckBox seat = new CheckBox(this);
                seat.setText(seatName);
                seat.setPadding(8, 8, 8, 8);
                
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.setMargins(8, 8, 8, 8);
                seat.setLayoutParams(params);

                seat.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        selectedSeats.add(seatName);
                        totalPrice += showtime.getPrice();
                    } else {
                        selectedSeats.remove(seatName);
                        totalPrice -= showtime.getPrice();
                    }
                    updateUI();
                });

                binding.seatGrid.addView(seat);
            }
        }
    }

    private void updateUI() {
        if (selectedSeats.isEmpty()) {
            binding.tvSelectedSeats.setText("Selected: None");
        } else {
            binding.tvSelectedSeats.setText("Selected: " + String.join(", ", selectedSeats));
        }
        binding.tvTotalPrice.setText(String.format(Locale.getDefault(), "Total: $%.2f", totalPrice));
    }
}