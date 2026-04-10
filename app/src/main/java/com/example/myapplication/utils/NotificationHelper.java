package com.example.myapplication.utils;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.Calendar;

public class NotificationHelper {
    private static final String CHANNEL_ID = "movie_booking_channel";

    public static void showNotification(Context context, String title, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Movie Bookings", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }

    public static void scheduleReminder(Context context, String movieTitle, String startTimeStr) {
        try {
            // Tách giờ và phút từ chuỗi "19:30"
            String[] parts = startTimeStr.split(":");
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);

            // Tính thời gian nhắc nhở: Trừ đi 30 phút
            long showtimeMillis = calendar.getTimeInMillis();
            long reminderTime = showtimeMillis - (30 * 60 * 1000); // Trừ 30 phút

            // Nếu giờ nhắc nhở đã qua trong ngày hôm nay, hãy đặt cho ngày mai (tùy chọn)
            if (reminderTime < System.currentTimeMillis()) {
                // Để demo, nếu giờ đã qua, chúng ta cộng thêm 10 giây để thấy ngay kết quả
                reminderTime = System.currentTimeMillis() + 10000;
                Log.d("Reminder", "Giờ chiếu đã qua hoặc quá sát, demo nhắc sau 10 giây");
            }

            Intent intent = new Intent(context, BookingReminderReceiver.class);
            intent.putExtra("movieTitle", movieTitle);
            intent.putExtra("startTime", startTimeStr);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context, 
                    (int) System.currentTimeMillis(), 
                    intent, 
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (alarmManager.canScheduleExactAlarms()) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, reminderTime, pendingIntent);
                    } else {
                        alarmManager.set(AlarmManager.RTC_WAKEUP, reminderTime, pendingIntent);
                    }
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, reminderTime, pendingIntent);
                }
            }
            
            Log.d("Reminder", "Đã đặt lịch nhắc phim " + movieTitle + " vào lúc " + reminderTime);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}