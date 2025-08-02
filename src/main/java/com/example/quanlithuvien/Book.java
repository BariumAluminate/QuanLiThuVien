public class Book extends Item {
    public String author;
    public String bookTag;
    public String borrowedId;
    public boolean borrowedState;

    public Book(String id, String name) {
        super(id, name);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateAuthor(String author) {
        this.author = author;
    }

    public void addBookTag(String tag) {

    }

    public void removeBookTag(String tag) {

    }

    public void updateBookTag(String newTag) {
        this.bookTag = newTag;
    }

    public void updateAll(String name, String author, String bookTag) {
        this.author = author;
        this.name = name;
        this.bookTag = bookTag;
    }

    public String show() {
        return "Tác giả: " + author + ", Tag: " + bookTag;
    }

    public void lend(String someone) {

    }
}
