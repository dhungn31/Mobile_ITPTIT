1. Tìm hiểu về các components của Android, bao gồm những gì, tác dụng là gì? hãy code example để đưa ra dẫn chứng.

- Android Components là những thành phần cơ bản tạo nên một ứng dụng Android

- Mỗi thành phần đảm nhận một nhiệm vụ riêng, chẳng hạn như hiển thị giao diện, xử lý dữ liệu, chạy tác vụ nền hoặc nhận thông báo từ hệ thống

- Bao gồm 4 thành phần chính: 

+ Activity:
    Là thành phần quản lý một màn hình giao diện của ứng dụng
    Dùng để hiển thị thông tin và xử lý tương tác của người dùng

VD:
    classMainActivity : AppCompatActivity(){
    override funonCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
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
   
    7) onDestroy() - Trạng thái: Destroyed
    Activity bị hủy hoàn toàn, loại bỏ khỏi bộ nhớ.
    Tác dụng:
        Giải phóng toàn bộ tài nguyên còn lại.
        Dọn dẹp bộ nhớ, hủy kết nối, thread…
    Ví dụ: Nhấn Back, hệ thống thiếu bộ nhớ, xoay màn hình.

4. Tìm hiểu về kiến trúc của android, trong kiến trúc của android có các pattern phổ biến nào?

4.1. Kiến trúc hệ điều hành Android

Android là hệ điều hành mã nguồn mở dựa trên nhân Linux, được tổ chức thành nhiều lớp. Mỗi lớp đảm nhiệm một chức năng riêng, giúp hệ thống hoạt động ổn định và hỗ trợ phát triển ứng dụng.

Applications: Bao gồm các ứng dụng hệ thống và ứng dụng do người dùng cài đặt, ví dụ: Điện thoại, Tin nhắn và các ứng dụng học tập.

Application Framework: Cung cấp các API và dịch vụ để lập trình viên phát triển ứng dụng, như quản lý Activity, thông báo và tài nguyên.

Android Runtime: Môi trường thực thi mã ứng dụng Android, hỗ trợ thực thi mã DEX và quản lý bộ nhớ.

Native Libraries: Cung cấp các thư viện phục vụ đồ họa, cơ sở dữ liệu, đa phương tiện và các chức năng hệ thống khác.

Hardware Abstraction Layer: Cung cấp giao diện giúp Android tương tác với phần cứng như camera, Bluetooth và cảm biến.

Linux Kernel: Quản lý tiến trình, bộ nhớ, trình điều khiển thiết bị, mạng và bảo mật ở cấp hệ điều hành.

4.2. Kiến trúc bên trong ứng dụng Android

Khi xây dựng ứng dụng Android, mã nguồn thường được chia thành các lớp để dễ phát triển, kiểm thử và bảo trì.

UI Layer: Hiển thị thông tin và tiếp nhận thao tác của người dùng.

Domain Layer: Chứa các quy tắc nghiệp vụ và Use Case. Lớp này có thể không cần thiết đối với ứng dụng đơn giản.

Data Layer: Quản lý dữ liệu từ cơ sở dữ liệu, API hoặc các nguồn dữ liệu khác.

4.3. Các Architecture Pattern phổ biến

a. MVC (Model – View – Controller)

Model: Quản lý dữ liệu.

View: Hiển thị giao diện.

Controller: Tiếp nhận thao tác của người dùng và điều phối xử lý.

Ưu điểm: Dễ hiểu, phù hợp với các ứng dụng đơn giản.

Nhược điểm: Khi ứng dụng lớn, Controller có thể chứa nhiều logic, gây khó khăn cho việc bảo trì.

b. MVP (Model – View – Presenter)

Model: Quản lý dữ liệu.

View: Hiển thị giao diện.

Presenter: Xử lý logic và điều phối giữa Model với View.

Ưu điểm: Tách biệt giao diện và logic xử lý, thuận tiện cho kiểm thử.

Nhược điểm: Có thể tạo ra nhiều lớp và mã nguồn hơn.

c. MVVM (Model – View – ViewModel)

Model: Quản lý dữ liệu.

View: Hiển thị giao diện.

ViewModel: Xử lý và lưu trữ dữ liệu phục vụ giao diện.

Ưu điểm: Giúp tách biệt giao diện với logic, quản lý trạng thái giao diện thuận tiện và hỗ trợ kiểm thử.

Nhược điểm: Cần tổ chức luồng dữ liệu hợp lý để tránh cấu trúc phức tạp.

Ví dụ: Trong ứng dụng quản lý sinh viên, View hiển thị danh sách, ViewModel xử lý trạng thái danh sách và Repository cung cấp dữ liệu sinh viên.

d. MVI (Model – View – Intent)

Model: Đại diện cho trạng thái giao diện.

View: Hiển thị trạng thái hiện tại.

Intent: Đại diện cho hành động của người dùng.

Ưu điểm: Luồng dữ liệu rõ ràng, trạng thái giao diện được quản lý tập trung.

Nhược điểm: Có thể phức tạp đối với ứng dụng nhỏ.

4.4. Các Design Pattern phổ biến trong Android

a. Repository Pattern

Tạo lớp trung gian giữa phần xử lý nghiệp vụ và nguồn dữ liệu. Repository giúp thống nhất cách lấy dữ liệu từ API hoặc cơ sở dữ liệu.

b. Observer Pattern

Cho phép một đối tượng theo dõi và nhận thông báo khi dữ liệu thay đổi. Ví dụ: giao diện cập nhật khi dữ liệu trong ViewModel thay đổi thông qua LiveData hoặc StateFlow.

c. Singleton Pattern

Đảm bảo một lớp chỉ có một thể hiện dùng chung. Pattern này có thể được sử dụng cho một số đối tượng cần dùng chung trong ứng dụng.

d. Factory Pattern

Đóng gói quá trình tạo đối tượng, giúp chương trình linh hoạt hơn khi cần tạo các đối tượng thuộc những loại khác nhau.

e. Adapter Pattern

Chuyển đổi giao diện của một thành phần để nó có thể làm việc với thành phần khác. Ví dụ: RecyclerView.Adapter kết nối dữ liệu với danh sách hiển thị trên màn hình.

f. Dependency Injection (DI)

Cung cấp các đối tượng phụ thuộc từ bên ngoài thay vì để một lớp tự tạo chúng. DI giúp giảm sự phụ thuộc giữa các lớp và hỗ trợ kiểm thử. Hilt là một công cụ DI phổ biến trong Android.