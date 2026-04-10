package com.example.myapplication.activities;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivityProfileBinding;
import com.example.myapplication.firebase.FirebaseHelper;
import com.example.myapplication.models.User;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        loadUserProfile();
    }

    private void loadUserProfile() {
        String userId = FirebaseHelper.getCurrentUserId();
        FirebaseHelper.getDb().collection("users").document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult().exists()) {
                        User user = task.getResult().toObject(User.class);
                        if (user != null) {
                            binding.tvFullName.setText(user.getFullName());
                            binding.tvEmail.setText(user.getEmail());
                            binding.tvPhone.setText(user.getPhone());
                        }
                    } else {
                        Toast.makeText(this, "Failed to load profile", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}