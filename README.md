# Em xin trình bày phần làm API của mình

## A. Giới thiệu chung
1. **Sinh viên phụ trách:** Nguyễn Tiến Dũng (MSSV: 24021433)  
2. **Mục tiêu:** Xây dựng một API cho phép thao tác với cơ sở dữ liệu và cung cấp dữ liệu cho phần giao diện người dùng (GUI).

---

## B. Cấu hình và thiết kế API

### 1. Cấu hình hệ thống
- **Máy ảo chạy API:** triển khai trên nền tảng PaaS của Microsoft Azure.  
- **Cơ sở dữ liệu:** sử dụng Microsoft SQL Server, cũng triển khai trên dịch vụ Azure.  

### 2. Thiết kế API
Sơ đồ class và kiến trúc tổng thể được mô tả tại liên kết sau:  
👉 [Sơ đồ thiết kế API](https://drive.google.com/file/d/1x52QAnwQozswnAYp03P8Ot-TSwxdXCsF/view?usp=sharing)

#### Quy trình đăng nhập
- Trước khi thực hiện các hành động khác, người dùng cần đăng nhập.  
- Thông tin đăng nhập bao gồm: **ID**, **Tên**, và **Mật khẩu**.  
- Sau khi đăng nhập thành công, hệ thống trả về 3 thông tin quan trọng:  
  1. `stringID`: mã định danh của người đọc.  
  2. `API_KEY`: khóa để xác thực API request.  
  3. `CSRFtoken`: token bổ sung để tăng cường xác thực và bảo mật.  

#### Phân quyền người dùng
- Người đọc có thể được cấp quyền **librarian** nếu trong cơ sở dữ liệu, tài khoản của họ được đánh dấu là librarian.  
- Người có quyền librarian sẽ có thêm các chức năng quản trị (thêm, xóa, chỉnh sửa sách, v.v).  

#### Thông tin về Book
Mỗi cuốn sách trong hệ thống được mô tả bởi các thuộc tính:  
- `bookId`: mã định danh sách.  
- `title`: tên sách.  
- `author`: tác giả.  
- `booktag`: thể loại sách.  
- `borrowerId`: mã định danh người đang mượn sách (nếu có).  

#### Các hành động trên Book
- Các API liên quan đến sách (thêm, mượn, trả, xóa, tìm kiếm, hiển thị toàn bộ, …) đều yêu cầu đầy đủ các thông tin trên để xử lý chính xác.



### C. Các yêu cầu trong lập trình hướng đối tượng
1. **Tính đóng gói và trừu tượng**  
   - Các thuộc tính trong lớp được khai báo ở phạm vi truy cập phù hợp (private/protected) và chỉ được truy xuất thông qua getter/setter.  
   - Các lớp được thiết kế tập trung vào nhiệm vụ riêng, thể hiện nguyên tắc **encapsulation** (đóng gói dữ liệu và hành vi).  

2. **Đa luồng**  
   - Ứng dụng chạy trên Spring Boot với Tomcat làm Servlet container.  
   - Tomcat quản lý **thread pool**: mỗi request HTTP được xử lý trên một luồng riêng biệt.  
   - Điều này đảm bảo hệ thống có khả năng xử lý song song nhiều request (đa luồng).  

3. **Mẫu thiết kế (Design Pattern)**  
   - Ứng dụng áp dụng **Command Pattern** (thuộc nhóm Behavioral).  
   - Mỗi hành động (action) của hệ thống được triển khai trong một class riêng (ví dụ: hiển thị danh sách sách, thêm sách, xóa sách).  
   - Thiết kế này giúp dễ dàng mở rộng thêm chức năng mới mà không ảnh hưởng đến code cũ.  

---

### D. Các tính năng bảo mật
1. **Bảo mật mật khẩu**  
   - Mật khẩu người dùng được hash bằng thuật toán SHA-256 trước khi lưu vào cơ sở dữ liệu.  

2. **Bảo vệ CSRF Token**  
   - CSRF token được sinh ngẫu nhiên và lưu trong cơ sở dữ liệu.  
   - Khóa bí mật không được lưu trong Git để tránh rò rỉ thông tin nhạy cảm.  

3. **Xác thực nhiều lớp**  
   - Mỗi request cần xuất trình đồng thời 3 thông tin: **ID**, **API_KEY**, và **CSRF token**.  
   - Cơ chế này đảm bảo xác thực chặt chẽ, giảm thiểu nguy cơ giả mạo request.  

4. **Phòng chống SQL Injection**  
   - Hệ thống sử dụng `JdbcTemplate` với cơ chế Prepared Statement để ngăn chặn SQL injection.  
   - Các câu lệnh SQL được kiểm tra, lọc trước khi thực thi nhằm loại bỏ các mẫu nguy hiểm.  



