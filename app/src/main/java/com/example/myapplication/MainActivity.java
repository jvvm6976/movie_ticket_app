package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.activities.SplashActivity;
import com.example.myapplication.utils.DataSeeder;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // TỰ ĐỘNG ĐỔ DỮ LIỆU LÊN FIRESTORE (CHỈ CẦN CHẠY 1 LẦN)
        // Lần đầu chạy hãy để dòng này, sau khi có dữ liệu rồi thì hãy thêm // vào trước
        DataSeeder.seedAllData();

        // CHỈ CHẠY MÀN HÌNH SPLASH ĐỂ KIỂM TRA ĐĂNG NHẬP
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }
}