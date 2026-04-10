# MovieTicketApp - Android Movie Booking System

Đây là đồ án mẫu ứng dụng đặt vé xem phim sử dụng Java và Firebase cho sinh viên.

## Chức năng chính
- **Xác thực:** Đăng ký, Đăng nhập, Đăng xuất qua Firebase Auth.
- **Duyệt phim:** Xem danh sách phim đang chiếu từ Firestore.
- **Chi tiết phim:** Xem mô tả, thể loại, thời lượng.
- **Rạp & Suất chiếu:** Chọn rạp và xem các suất chiếu tương ứng.
- **Đặt vé:** Chọn ghế (A1-E5) và tính tổng tiền.
- **Quản lý vé:** Xem lịch sử vé đã đặt trong "My Tickets".
- **Hồ sơ:** Xem thông tin cá nhân.

## Công nghệ sử dụng
- **Ngôn ngữ:** Java
- **Backend:** Firebase (Authentication, Cloud Firestore)
- **UI:** XML Layout, ViewBinding, Material Design, RecyclerView, CardView.
- **Thư viện:** Glide (load ảnh), Firebase BoM.

## Cấu trúc Project
- `activities/`: Chứa các màn hình của ứng dụng.
- `adapters/`: Các bộ điều hợp cho RecyclerView.
- `models/`: Các lớp dữ liệu tương ứng với Firestore collection.
- `firebase/`: Helper class quản lý kết nối Firebase.
- `utils/`: (Tùy chọn) Các hàm tiện ích.

## Hướng dẫn cài đặt
1. **Firebase Setup:**
   - Tạo project trên [Firebase Console](https://console.firebase.google.com/).
   - Thêm Android App với package name: `com.example.myapplication`.
   - Tải file `google-services.json` và bỏ vào thư mục `app/`.
   - Bật **Email/Password Authentication**.
   - Tạo **Cloud Firestore** database (chế độ test hoặc allow read/write).
2. **Import Data:**
   - Bạn cần tạo thủ công hoặc dùng mã nguồn để thêm dữ liệu mẫu vào Firestore các collection: `movies`, `theaters`, `showtimes`.
3. **Chạy ứng dụng:** Mở project trong Android Studio và nhấn Run.

## Dữ liệu mẫu gợi ý (Firestore)
- **movies:** `{ title: "Avengers", genre: "Action", posterUrl: "URL_ANH", description: "..." }`
- **theaters:** `{ name: "CGV Vincom", address: "Hanoi", city: "Hanoi" }`
- **showtimes:** `{ movieId: "ID_PHIM", theaterId: "ID_RAP", startTime: "19:00", price: 10.0 }`

---
*Chúc bạn hoàn thành bài tập tốt!*
