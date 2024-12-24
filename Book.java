package Library_Manager;

public class Book {
    private int id = 0;
    private String title, author, category;
    private double price;
    private long bookCode;
    private int stock;

    public Book(String title, String author, String category, double price, long bookCode){
        id++;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.bookCode = bookCode;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public long getBookCode() {
        return bookCode;
    }

    @Override
    public String toString() {
        return
                "id: " + id + "\n" +
                "title: " + title + '\n' +
                "author: " + author + '\n' +
                "category: " + category + '\n' +
                "price: " + price +"\n"+
                "bookCode: " + bookCode+ "\n";
    }
}
