# 🎬 MovieTicketApp - Ứng Dụng Đặt Vé Xem Phim

<div align="center">

**Ứng dụng Android đặt vé xem phim trực tuyến với Firebase Backend**

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![Language](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)
[![Firebase](https://img.shields.io/badge/Backend-Firebase-yellow.svg)](https://firebase.google.com/)

</div>

---

## 📋 Mục Lục

- [Giới Thiệu](#-giới-thiệu)
- [Demo Ứng Dụng](#-demo-ứng-dụng)
- [Tính Năng](#-tính-năng)
- [Công Nghệ Sử Dụng](#-công-nghệ-sử-dụng)
- [Kiến Trúc Ứng Dụng](#-kiến-trúc-ứng-dụng)
- [Yêu Cầu Hệ Thống](#-yêu-cầu-hệ-thống)
- [Hướng Dẫn Cài Đặt](#-hướng-dẫn-cài-đặt)
- [Cấu Trúc Dữ Liệu](#-cấu-trúc-dữ-liệu)
- [Cấu Trúc Project](#-cấu-trúc-project)
- [Tác Giả](#-tác-giả)

---

## 🎯 Giới Thiệu

MovieTicketApp là một ứng dụng Android hoàn chỉnh cho phép người dùng đặt vé xem phim trực tuyến. Ứng dụng được xây dựng với mục đích học tập, minh họa các kỹ thuật phát triển ứng dụng Android hiện đại kết hợp với Firebase Backend.

Dự án này phù hợp cho:
- Sinh viên học lập trình Android
- Người mới bắt đầu với Firebase
- Đồ án môn học Phát triển ứng dụng di động

---

## 📸 Demo Ứng Dụng

**📱 Xem đầy đủ ảnh chụp màn hình tại:** [`Demo/demo.md`](./Demo/demo.md)

File demo.md chứa tất cả các ảnh chụp màn hình được sắp xếp theo thứ tự với mô tả chi tiết, bao gồm:
- Màn hình đăng nhập/đăng ký
- Danh sách phim đang chiếu
- Chi tiết phim và thông tin suất chiếu
- Chọn rạp và đặt ghế
- Xác nhận đặt vé và quản lý vé

---

## ✨ Tính Năng

### 🔐 Xác Thực Người Dùng
- Đăng ký tài khoản mới với Email/Password
- Đăng nhập an toàn qua Firebase Authentication
- Đăng xuất và quản lý phiên đăng nhập
- Màn hình Splash với kiểm tra trạng thái đăng nhập

### 🎥 Quản Lý Phim
- Hiển thị danh sách phim đang chiếu
- Xem chi tiết phim (poster, mô tả, thể loại, thời lượng, đánh giá)
- Tìm kiếm và lọc phim theo thể loại
- Load ảnh poster tối ưu với Glide

### 🏢 Hệ Thống Rạp & Suất Chiếu
- Danh sách các rạp chiếu phim
- Xem suất chiếu theo rạp và phim
- Hiển thị giờ chiếu và giá vé
- Chọn suất chiếu phù hợp

### 🎫 Đặt Vé & Chọn Ghế
- Sơ đồ ghế trực quan (A1-E5, 25 ghế)
- Chọn nhiều ghế cùng lúc
- Tính toán tổng tiền tự động
- Xác nhận đặt vé và lưu vào Firestore

### 📱 Quản Lý Vé Cá Nhân
- Xem lịch sử vé đã đặt trong "My Tickets"
- Hiển thị thông tin chi tiết: phim, rạp, ghế, giờ chiếu
- Trạng thái vé (đã đặt, đã sử dụng)

### 👤 Hồ Sơ Người Dùng
- Xem thông tin cá nhân
- Cập nhật profile
- Quản lý tài khoản

### 🔔 Thông Báo (Tùy Chọn)
- Firebase Cloud Messaging để nhận thông báo
- Nhắc nhở suất chiếu sắp tới
- Thông báo khuyến mãi

---

## 🛠 Công Nghệ Sử Dụng

### Core Technologies
- **Ngôn ngữ:** Java (JDK 11+)
- **IDE:** Android Studio (Hedgehog | 2023.1.1+)
- **Build System:** Gradle (Kotlin DSL)
- **Min SDK:** API 24 (Android 7.0)
- **Target SDK:** API 34 (Android 14)

### Firebase Services
- **Firebase Authentication:** Xác thực người dùng
- **Cloud Firestore:** Database NoSQL realtime
- **Firebase Cloud Messaging (FCM):** Push notifications
- **Firebase BoM:** Quản lý phiên bản Firebase

### Android Libraries
- **Material Design Components:** UI hiện đại
- **RecyclerView:** Hiển thị danh sách tối ưu
- **CardView:** Thiết kế card đẹp mắt
- **ViewBinding:** Truy cập view an toàn
- **Glide:** Load và cache ảnh hiệu quả

### Architecture & Patterns
- **MVC Pattern:** Model-View-Controller
- **Repository Pattern:** Quản lý data source
- **Adapter Pattern:** RecyclerView adapters
- **Singleton Pattern:** Firebase helper

---

## 🏗 Kiến Trúc Ứng Dụng

```
┌─────────────────────────────────────────┐
│           Presentation Layer            │
│  (Activities, Adapters, XML Layouts)    │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│           Business Logic Layer          │
│        (Models, Utils, Helpers)         │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│            Data Layer                   │
│    (Firebase Auth, Cloud Firestore)     │
└─────────────────────────────────────────┘
```

---

## 💻 Yêu Cầu Hệ Thống

### Môi Trường Phát Triển
- **Android Studio:** Hedgehog (2023.1.1) hoặc mới hơn
- **JDK:** Version 11 hoặc cao hơn
- **Gradle:** 8.0+
- **Android SDK:** API 24 - API 34

### Thiết Bị Chạy
- **Android Version:** 7.0 (Nougat) trở lên
- **RAM:** Tối thiểu 2GB
- **Kết nối Internet:** Bắt buộc (để kết nối Firebase)

---

## 🚀 Hướng Dẫn Cài Đặt

### Bước 1: Clone Repository

```bash
git clone https://github.com/jvvm6976/movie_ticket_app.git
cd MovieTicketApp
```

### Bước 2: Cấu Hình Firebase

1. **Tạo Firebase Project:**
   - Truy cập [Firebase Console](https://console.firebase.google.com/)
   - Nhấn "Add project" và làm theo hướng dẫn
   - Đặt tên project (ví dụ: "MovieTicketApp")

2. **Thêm Android App:**
   - Trong Firebase Console, chọn "Add app" → Android
   - Package name: `com.example.myapplication`
   - Tải file `google-services.json`
   - Copy file vào thư mục `app/` của project

3. **Bật Firebase Authentication:**
   - Vào mục "Authentication" → "Sign-in method"
   - Bật "Email/Password" provider
   - Lưu thay đổi

4. **Tạo Cloud Firestore Database:**
   - Vào mục "Firestore Database" → "Create database"
   - Chọn chế độ "Start in test mode" (cho development)
   - Chọn location gần nhất (ví dụ: asia-southeast1)

5. **Cấu Hình Firestore Rules (Tùy Chọn):**
   ```javascript
   rules_version = '2';
   service cloud.firestore {
     match /databases/{database}/documents {
       match /{document=**} {
         allow read, write: if request.auth != null;
       }
     }
   }
   ```

### Bước 3: Import Dữ Liệu Mẫu

Ứng dụng có sẵn `DataSeeder` class để tự động tạo dữ liệu mẫu:

1. Chạy ứng dụng lần đầu
2. `DataSeeder` sẽ tự động tạo dữ liệu mẫu vào Firestore
3. Hoặc gọi thủ công trong `MainActivity`:
   ```java
   DataSeeder.seedData();
   ```

### Bước 4: Build & Run

1. Mở project trong Android Studio
2. Sync Gradle: `File` → `Sync Project with Gradle Files`
3. Chọn device/emulator
4. Nhấn `Run` (Shift + F10) hoặc nút ▶️

### Bước 5: Tạo Tài Khoản Test

1. Chạy app và chọn "Register"
2. Nhập email và password
3. Đăng ký thành công → Tự động đăng nhập

---

## 📊 Cấu Trúc Dữ Liệu

### Collection: `users`
```json
{
  "userId": "string",
  "email": "string",
  "fullName": "string",
  "phoneNumber": "string",
  "createdAt": "timestamp"
}
```

### Collection: `movies`
```json
{
  "movieId": "string",
  "title": "string",
  "genre": "string",
  "duration": "number (minutes)",
  "rating": "number (0-10)",
  "description": "string",
  "posterUrl": "string",
  "releaseDate": "string",
  "director": "string",
  "cast": "string"
}
```

### Collection: `theaters`
```json
{
  "theaterId": "string",
  "name": "string",
  "address": "string",
  "city": "string",
  "phoneNumber": "string",
  "facilities": ["string"]
}
```

### Collection: `showtimes`
```json
{
  "showtimeId": "string",
  "movieId": "string",
  "theaterId": "string",
  "startTime": "string (HH:mm)",
  "date": "string (yyyy-MM-dd)",
  "price": "number",
  "availableSeats": "number"
}
```

### Collection: `tickets`
```json
{
  "ticketId": "string",
  "userId": "string",
  "movieId": "string",
  "theaterId": "string",
  "showtimeId": "string",
  "seats": ["string"],
  "totalPrice": "number",
  "bookingDate": "timestamp",
  "status": "string (booked/used/cancelled)"
}
```

---

## 📁 Cấu Trúc Project

```
app/src/main/java/com/example/myapplication/
│
├── activities/                    # Các màn hình Activity
│   ├── SplashActivity.java       # Màn hình khởi động
│   ├── LoginActivity.java        # Đăng nhập
│   ├── RegisterActivity.java     # Đăng ký
│   ├── MainActivity.java         # Màn hình chính
│   ├── MovieListActivity.java    # Danh sách phim
│   ├── MovieDetailActivity.java  # Chi tiết phim
│   ├── TheaterListActivity.java  # Danh sách rạp
│   ├── ShowtimeListActivity.java # Danh sách suất chiếu
│   ├── SeatSelectionActivity.java # Chọn ghế
│   ├── BookingConfirmActivity.java # Xác nhận đặt vé
│   ├── MyTicketsActivity.java    # Vé của tôi
│   └── ProfileActivity.java      # Hồ sơ cá nhân
│
├── adapters/                      # RecyclerView Adapters
│   ├── MovieAdapter.java         # Adapter cho danh sách phim
│   ├── TheaterAdapter.java       # Adapter cho danh sách rạp
│   ├── ShowtimeAdapter.java      # Adapter cho suất chiếu
│   └── TicketAdapter.java        # Adapter cho vé
│
├── models/                        # Data Models
│   ├── User.java                 # Model người dùng
│   ├── Movie.java                # Model phim
│   ├── Theater.java              # Model rạp
│   ├── Showtime.java             # Model suất chiếu
│   └── Ticket.java               # Model vé
│
├── firebase/                      # Firebase Integration
│   ├── FirebaseHelper.java       # Helper class cho Firebase
│   └── MyFirebaseMessagingService.java # FCM service
│
└── utils/                         # Utilities
    ├── DataSeeder.java           # Tạo dữ liệu mẫu
    ├── NotificationHelper.java   # Quản lý thông báo
    └── BookingReminderReceiver.java # Broadcast receiver
```

---

## 🎓 Kiến Thức Học Được

Qua project này, bạn sẽ học được:

- ✅ Xây dựng ứng dụng Android đa màn hình
- ✅ Tích hợp Firebase Authentication
- ✅ Làm việc với Cloud Firestore (CRUD operations)
- ✅ Sử dụng RecyclerView và Custom Adapters
- ✅ Material Design và UI/UX best practices
- ✅ ViewBinding và Data Binding
- ✅ Load ảnh với Glide
- ✅ Navigation giữa các Activity
- ✅ Xử lý sự kiện người dùng
- ✅ Quản lý state và lifecycle

---

## 🐛 Troubleshooting

### Lỗi thường gặp:

**1. "google-services.json not found"**
- Đảm bảo file `google-services.json` nằm trong thư mục `app/`
- Sync lại Gradle

**2. "Firebase Authentication failed"**
- Kiểm tra Email/Password provider đã được bật
- Kiểm tra kết nối Internet

**3. "Firestore permission denied"**
- Kiểm tra Firestore Rules
- Đảm bảo user đã đăng nhập

**4. "Build failed"**
- Clean project: `Build` → `Clean Project`
- Rebuild: `Build` → `Rebuild Project`
- Invalidate caches: `File` → `Invalidate Caches / Restart`

---

## 📝 License

Dự án này được tạo ra cho mục đích học tập và giáo dục.

---


