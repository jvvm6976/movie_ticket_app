package com.example.myapplication.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.myapplication.adapters.TicketAdapter;
import com.example.myapplication.databinding.ActivityMyTicketsBinding;
import com.example.myapplication.firebase.FirebaseHelper;
import com.example.myapplication.models.Ticket;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class MyTicketsActivity extends AppCompatActivity {

    private ActivityMyTicketsBinding binding;
    private TicketAdapter adapter;
    private List<Ticket> ticketList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyTicketsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        ticketList = new ArrayList<>();
        adapter = new TicketAdapter(ticketList);
        binding.rvTickets.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTickets.setAdapter(adapter);

        loadTickets();
    }

    private void loadTickets() {
        binding.progressBar.setVisibility(View.VISIBLE);
        String userId = FirebaseHelper.getCurrentUserId();

        // Xóa tạm .orderBy("bookingTime", Query.Direction.DESCENDING) để tránh yêu cầu tạo Index trên Firestore
        // Bạn có thể thêm lại sau khi đã tạo Index trong Firebase Console.
        FirebaseHelper.getDb().collection("tickets")
                .whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(task -> {
                    binding.progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        ticketList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Ticket ticket = document.toObject(Ticket.class);
                            ticketList.add(ticket);
                        }
                        adapter.notifyDataSetChanged();
                        if (ticketList.isEmpty()) {
                            binding.tvEmpty.setVisibility(View.VISIBLE);
                        } else {
                            binding.tvEmpty.setVisibility(View.GONE);
                        }
                    } else {
                        Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}