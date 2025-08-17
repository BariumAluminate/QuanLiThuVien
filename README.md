# Em xin trình bày phần làm API của mình

## A. Giới thiệu chung
1) sinh viên phụ trách: Nguyễn Tiến Dũng (MSSV: 24021433)
2) Mục tiêu: tạo một API để có thể thao tác với cơ sở dữ liệu và gửi về cho phần GUI

## B. Cấu hình và thiết kế API

### 1. Cấu hình:
a) Máy ảo chạy API: sử dụng hệ thông Paas của Microsoft Azure  
b) Server cơ sở dữ liệu: sự dụng SQL server của Microsoft Azure  

### 2. Cách thiết kế API:
Sau đây là sơ đồ về các class và cách thiết kế API của em:
https://drive.google.com/file/d/1x52QAnwQozswnAYp03P8Ot-TSwxdXCsF/view?usp=sharing

Về cơ bản, trước khi chúng ta có thể làm bất cứ điều gì, chúng ta cần đăng nhập, thông tin đăng nhập bao gồm ID, tên và mật khẩu, khi đăng nhập thì server sẽ trả về 3 thông tin quan trọng, đó là :

a)stringID (ID người đọc)  
b)API_KEY (KEY API để request)  
c)CSRFtoken (một token để hỗ trợ xác thực)  


với 3 thông tin này thì chúng ta có thể thực hiện request lên API một các dễ dàng. Một người đọc sẽ được cấp quyền librarian nếu như trong cơ sở dữ liệu thì các thông tin của họ được đánh dấu là một librarian, khi này họ sẽ được thêm các quyền và các hành động khác

Về book, thì mỗi book sẽ được dại diện bởi các thông tin sau:

a)bookId: ID của sách  
b)title: tên sách  
c)author: tên tác giả  
d)booktag: thể loại sách  
e)BorrowerId: mã của người mượn  


và các hành động liên quan đến sách luôn cần các thông tin này


### 3. các tính năng bảo mật:
1) mật khẩu luôn được hash đúng cách
2) CSRFtoken được mã hóa trong database và key không lộ trong git
3) xác thực request cần xuất trình 3 mã (ID,API_KEY,CSRFtoken)
4) chống SQLinjection bằng cách sử dụng Jdbctemplate và hàm lọc các câu lệnh nguy hiểm



