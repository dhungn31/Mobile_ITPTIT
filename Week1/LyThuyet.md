1. Tìm hiểu về các components của Android, bao gồm những gì, tác dụng là gì? hãy code example để đưa ra dẫn chứng.

- Android Components là những thành phần cơ bản tạo nên một ứng dụng Android

- Mỗi thành phần đảm nhận một nhiệm vụ riêng, chẳng hạn như hiển thị giao diện, xử lý dữ liệu, chạy tác vụ nền hoặc nhận thông báo từ hệ thống

- Bao gồm 4 thành phần chính: 

+ Activity:
    Là thành phần quản lý một màn hình giao diện của ứng dụng
    Dùng để hiển thị thông tin và xử lý tương tác của người dùng

VD:
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnHello);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                    MainActivity.this,
                    "Hungdzvcl",
                    Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}

+ Service:
    Là thành phần thực hiện các công việc không cần giao diện người dùng
    Có thể được sử dụng để phát nhạc hoặc thực hiện một số tác vụ nền

+ Broadcast Receiver:
    Là thành phần tiếp nhận và xử lý các thông điệp broadcast từ hệ thống hoặc ứng dụng khác

+ Content Provider:
    Là thành phần quản lý và cung cấp dữ liệu cho các ứng dụng hoặc thành phần khác thông qua một giao diện chuẩn.

2. 1 bộ khung xương của Android Studio gồm những thành phần nào, tác dụng của từng thành phần

- Bộ khung xương Android Studio gồm 6 thành phần chính:
    + AndroidManifest.xml: Khai báo cấu hình app, quyền, components
    + java/kotlin/: Mã nguồn (có MainActivity)
    + res/: Tài nguyên (layout, strings, colors, themes, drawable, mipmap)
    + build.gradle (Module): Cấu hình SDK version, dependencies, build types cho app
    + build.gradle (Project): Cấu hình plugin, repositories chung cho toàn dự án
    + Gradle Wrapper: hệ thống build tự động

3. Vòng đời của 1 app Android, bao gồm các state nào, tìm hiểu về các state đó.

- Vòng đời của ứng dụng Android thực chất là vòng đời của Activity. Khi người dùng mở, sử dụng, chuyển đổi hoặc đóng ứng dụng, Activity sẽ chuyển qua các trạng thái khác nhau. Tại mỗi lần chuyển trạng thái, hệ thống gọi một phương thức callback tương ứng.

- Các State: 
    1) onCreate() - Trạng thái: Created
    Được gọi đầu tiên khi Activity được tạo ra lần đầu.
    Chỉ được gọi một lần trong toàn bộ vòng đời.
    Tác dụng:
        Khởi tạo giao diện: setContentView(R.layout.activity_main).
        Khai báo biến, ánh xạ thành phần giao diện (Button, TextView…).
        Khôi phục trạng thái đã lưu (nếu có).
    Ví dụ: Người dùng nhấn icon ứng dụng → onCreate() được gọi.

    2) onStart() - Trạng thái: Started
    Được gọi sau onCreate() hoặc sau onRestart().
    Activity nhìn thấy được nhưng chưa tương tác được.
    Tác dụng:
        Chuẩn bị hiển thị lên màn hình.
        Đăng ký listener, cập nhật giao diện.

    3) onResume() - Trạng thái: Resumed (Running)
    Activity ở tiền cảnh (foreground).
    Người dùng nhìn thấy và tương tác được.
    Đây là trạng thái hoạt động chính.
    Tác dụng:
        Bắt đầu animation, phát video/nhạc, cập nhật dữ liệu, bật camera…
    
    4) onPause() - Trạng thái: Paused
    Activity bị che một phần hoặc mất focus.
    Vẫn còn trong bộ nhớ nhưng không ở tiền cảnh.
    Tác dụng:
        Tạm dừng hoạt động tốn tài nguyên (animation, video…).
        Lưu dữ liệu tạm thời.
    Ví dụ: Dialog xuất hiện, chế độ đa cửa sổ.

    5) onStop() - Trạng thái: Stopped
    Activity hoàn toàn không nhìn thấy (bị che hoàn toàn).
    Vẫn còn trong bộ nhớ nhưng không hiển thị.
    Tác dụng:
        Giải phóng tài nguyên không cần thiết.
        Lưu dữ liệu vào database hoặc SharedPreferences.
    Ví dụ: Nhấn nút Home, chuyển sang app khác.

    6) onRestart() - Chuyển: Stopped → Started
    Được gọi khi Activity quay lại từ trạng thái Stopped.
    Sau đó gọi tiếp onStart().
    Tác dụng:
        Khôi phục những gì đã dừng ở onStop().
    Ví dụ: Nhấn Home (→ onStop), mở lại app (→ onRestart → onStart → onResume).
   
    7) onDestroy() — Trạng thái: Destroyed
    Activity bị hủy hoàn toàn, loại bỏ khỏi bộ nhớ.
    Tác dụng:
        Giải phóng toàn bộ tài nguyên còn lại.
        Dọn dẹp bộ nhớ, hủy kết nối, thread…
    Ví dụ: Nhấn Back, hệ thống thiếu bộ nhớ, xoay màn hình.

4.