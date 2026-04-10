package com.example.myapplication.utils;

import android.util.Log;
import com.example.myapplication.firebase.FirebaseHelper;
import com.example.myapplication.models.Movie;
import com.example.myapplication.models.Showtime;
import com.example.myapplication.models.Theater;
import com.google.firebase.firestore.WriteBatch;
import java.util.ArrayList;
import java.util.List;

public class DataSeeder {
    public static void seedAllData() {
        WriteBatch batch = FirebaseHelper.getDb().batch();

        // Danh sách phim thực tế với URL ảnh chất lượng cao từ TMDB
        String[] titles = {
            "Deadpool & Wolverine", 
            "Inside Out 2", 
            "Despicable Me 4", 
            "Kingdom of the Planet of the Apes", 
            "Furiosa: A Mad Max Saga"
        };
        String[] genres = {"Hành động/Hài", "Hoạt hình/Phiêu lưu", "Hoạt hình/Hài", "Hành động/Viễn tưởng", "Hành động/Phiêu lưu"};
        String[] posters = {
            "https://image.tmdb.org/t/p/w500/8cdWjvZQUmOZp9v9Y9C7W9HneZ5.jpg",
            "https://image.tmdb.org/t/p/w500/vpnVM9B6NMmQpWeZvzLv1oWy1yB.jpg",
            "https://image.tmdb.org/t/p/w500/wWba3eoJaME867jYmS6KHiU2R6z.jpg",
            "https://image.tmdb.org/t/p/w500/gKkl37v96G3exVE0CccF1z78Ydz.jpg",
            "https://image.tmdb.org/t/p/w500/iADOeb9zCrW7RsS6p2SKuNNZ0kp.jpg"
        };
        String[] descriptions = {
            "Deadpool và Wolverine hợp lực để thực hiện một sứ mệnh sẽ thay đổi lịch sử của MCU.",
            "Cảm xúc mới mang tên Lo Âu xuất hiện trong đầu cô bé Riley khi bước vào tuổi dậy thì.",
            "Gru và gia đình đối mặt với kẻ thù mới đầy nguy hiểm và những rắc rối từ các con.",
            "Nhiều năm sau sự cai trị của Caesar, một chú khỉ trẻ dấn thân vào hành trình tìm kiếm tự do.",
            "Nguồn gốc của chiến binh Furiosa trước khi cô gặp Mad Max trong thế giới hậu tận thế."
        };
        
        List<String> movieIds = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            String id = "movie_" + i;
            movieIds.add(id);
            Movie m = new Movie();
            m.setMovieId(id);
            m.setTitle(titles[i]);
            m.setGenre(genres[i]);
            m.setDuration("120 phút");
            m.setPosterUrl(posters[i]);
            m.setDescription(descriptions[i]);
            m.setAgeRating(i == 0 ? "18+" : "P");
            batch.set(FirebaseHelper.getDb().collection("movies").document(id), m);
        }

        // Tạo Rạp mẫu
        String[] theaterNames = {"CGV Vincom Bà Triệu", "Lotte Cinema Landmark 72", "BHD Star Phạm Ngọc Thạch"};
        List<String> theaterIds = new ArrayList<>();
        for (int i = 0; i < theaterNames.length; i++) {
            String id = "theater_" + i;
            theaterIds.add(id);
            Theater t = new Theater();
            t.setTheaterId(id);
            t.setName(theaterNames[i]);
            t.setAddress("Tầng " + (i+4) + ", Trung tâm thương mại " + theaterNames[i]);
            t.setCity("Hà Nội");
            batch.set(FirebaseHelper.getDb().collection("theaters").document(id), t);
        }

        // Tạo Suất chiếu mẫu - Đặt giờ chiếu là 11:30 để test thông báo nhắc nhở
        for (String mId : movieIds) {
            for (int i = 0; i < theaterIds.size(); i++) {
                String tId = theaterIds.get(i);
                String sId = "showtime_" + mId + "_" + tId;
                Showtime s = new Showtime();
                s.setShowtimeId(sId);
                s.setMovieId(mId);
                s.setTheaterId(tId);
                s.setStartTime("11:30"); // Giờ chiếu 11h30
                s.setPrice(15.0);
                s.setRoomName("Cinema 0" + (i + 1));
                batch.set(FirebaseHelper.getDb().collection("showtimes").document(sId), s);
            }
        }

        batch.commit().addOnSuccessListener(aVoid -> Log.d("DataSeeder", "Dữ liệu phim thật và suất chiếu 11h30 đã cập nhật!"))
                .addOnFailureListener(e -> Log.e("DataSeeder", "Lỗi: " + e.getMessage()));
    }
}