package Library_Manager;

public class Order {
    int order_id;
    String userName, bookTitle;
    long bookCode;

    public Order(int order_id,String userName, String bookTitle, long bookCode) {
        this.userName = userName;
        this.bookTitle = bookTitle;
        this.bookCode = bookCode;
        this.order_id = order_id;
    }


    public int getOrder_id() {
        return order_id;
    }

    public String getUserName() {
        return userName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public long getBookCode() {
        return bookCode;
    }

    @Override
    public String toString() {
        return
                "order_id: " + order_id + "\n" +
                "userName: " + userName + '\n' +
                "bookTitle: " + bookTitle + '\n' +
                "bookCode: " + bookCode + '\n';
    }
}
