package com.example.myapplication.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BookingReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String movieTitle = intent.getStringExtra("movieTitle");
        String startTime = intent.getStringExtra("startTime");
        
        // Hiển thị thông báo nhắc nhở
        NotificationHelper.showNotification(
            context, 
            "Sắp đến giờ xem phim!", 
            "Phim " + movieTitle + " của bạn sẽ bắt đầu lúc " + startTime + ". Đừng bỏ lỡ nhé!"
        );
    }
}