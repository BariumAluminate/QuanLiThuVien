package com.example.quanlithuvien;

public class Reader {
    private String stringId;
    protected String api_KEY;
    protected String csrftoken;
    protected String password;

    /**
     * Phương thức khởi tạo.
     * @param stringId Id của người đọc
     * @param password Mật khẩu
     * @param csrftoken csrftoken
     * @param api_KEY api_KEY
     */
    public Reader(String stringId, String password, String csrftoken, String api_KEY) {
        this.stringId = stringId;
        this.password = password;
        this.api_KEY = api_KEY;
        this.csrftoken = csrftoken;
    }

    public String getStringId() {
        return stringId;
    }

    public void borrow(String bookId) {

    }

    public void findById(String stringId) {

    }

    public void findByName(String name) {

    }

    public void findByTag(String tag) {

    }
}
