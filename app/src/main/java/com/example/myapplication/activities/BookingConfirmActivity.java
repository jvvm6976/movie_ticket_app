package com.example.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivityBookingConfirmBinding;
import com.example.myapplication.firebase.FirebaseHelper;
import com.example.myapplication.models.Showtime;
import com.example.myapplication.models.Ticket;
import com.example.myapplication.utils.NotificationHelper;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class BookingConfirmActivity extends AppCompatActivity {

    private ActivityBookingConfirmBinding binding;
    private Showtime showtime;
    private List<String> selectedSeats;
    private double totalPrice;
    private String movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingConfirmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showtime = (Showtime) getIntent().getSerializableExtra("showtime");
        movieTitle = getIntent().getStringExtra("movieTitle");
        selectedSeats = getIntent().getStringArrayListExtra("selectedSeats");
        totalPrice = getIntent().getDoubleExtra("totalPrice", 0);

        binding.tvMovieTitle.setText(movieTitle);
        binding.tvShowtime.setText("Time: " + showtime.getStartTime());
        binding.tvSeats.setText("Seats: " + String.join(", ", selectedSeats));
        binding.tvTotal.setText(String.format(Locale.getDefault(), "Total: $%.2f", totalPrice));

        binding.btnPay.setOnClickListener(v -> saveTicketToFirestore());
    }

    private void saveTicketToFirestore() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.btnPay.setEnabled(false);

        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketId);
        ticket.setUserId(FirebaseHelper.getCurrentUserId());
        ticket.setMovieId(showtime.getMovieId());
        ticket.setMovieTitle(movieTitle);
        ticket.setTheaterId(showtime.getTheaterId());
        ticket.setShowtimeId(showtime.getShowtimeId());
        ticket.setStartTime(showtime.getStartTime());
        ticket.setSeatList(selectedSeats);
        ticket.setTotalPrice(totalPrice);
        ticket.setBookingTime(System.currentTimeMillis());
        ticket.setStatus("confirmed");

        FirebaseHelper.getDb().collection("tickets").document(ticketId)
                .set(ticket)
                .addOnCompleteListener(task -> {
                    binding.progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Booking Confirmed!", Toast.LENGTH_LONG).show();
                        
                        // Hiển thị thông báo xác nhận ngay lập tức
                        NotificationHelper.showNotification(this, "Đặt vé thành công!", "Bạn đã đặt vé phim " + movieTitle);
                        
                        // Đặt lịch nhắc nhở sau 10 giây (Demo)
                        NotificationHelper.scheduleReminder(this, movieTitle, showtime.getStartTime());

                        Intent intent = new Intent(this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        binding.btnPay.setEnabled(true);
                        Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}