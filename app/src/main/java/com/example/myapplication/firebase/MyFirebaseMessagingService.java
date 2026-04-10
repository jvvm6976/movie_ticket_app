package com.example.myapplication.firebase;

import android.util.Log;
import com.example.myapplication.utils.NotificationHelper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM_Service";

    // Khi nhận được thông báo từ Firebase
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Kiểm tra nếu thông báo có chứa nội dung
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            
            // Sử dụng Helper đã viết trước đó để hiển thị thông báo lên thanh trạng thái
            NotificationHelper.showNotification(getApplicationContext(), title, body);
        }
    }

    // Khi có Token mới (Dùng để định danh thiết bị này trên server)
    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, "Refreshed token: " + token);
        // Ở dự án thực tế, bạn sẽ gửi token này lên Firestore để lưu lại
    }
}