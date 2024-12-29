package Library_Manager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LibraryManager {
    private ArrayList<User> users;
    private ArrayList<Book> books;
    private ArrayList<Order> orders;

    int counter = 0;
    File usersFile = new File("C:\\Users\\maz\\IdeaProjects\\Library Manger\\src\\Library_Manager\\user.txt");
    File ordersFile = new File("C:\\Users\\maz\\IdeaProjects\\Library Manger\\src\\Library_Manager\\order.txt");
    File booksFile = new File("C:\\Users\\maz\\IdeaProjects\\Library Manger\\src\\Library_Manager\\books.txt");
    FileEdit fileEdit = new FileEdit();

    public static Scanner input = new Scanner(System.in);
    public LibraryManager(){
        users = new ArrayList<>();
        books = new ArrayList<>();
        orders = new ArrayList<>();
        if(counter < 1){
            fileEdit.readThenAddUsers(usersFile, users);
            fileEdit.readThenAddBooks(booksFile, books);
            fileEdit.readThenAddOrders(ordersFile, orders);
            counter = 1;
        }
    }

    public void registerUser(){
        int id;
        String username, password, role;
        System.out.print("Enter the id of the new user: ");
        id = input.nextInt();
        input.nextLine();
        System.out.print("Enter the username of the new user: ");
        username = input.nextLine();
        System.out.print("Enter the password for the new user: ");
        password = input.nextLine();
        System.out.print("Enter the role of the new user(admin/user): ");
        role = input.nextLine().toLowerCase();
        if((role.equals("admin")||role.equals("user")) && !username.isEmpty() && !password.isEmpty()){

            if(fileEdit.isExist(usersFile, username)){
                System.out.println("-------------------------------------------------------------");
                System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m" + " Username already exist");
                System.out.println("--------------------------------------------------------------");
                return;
            }

            User newUser = new User(id,username, password, role);
            users.add(newUser);
            fileEdit.writeToFile(usersFile, newUser);
        }
        else if(username.isEmpty() || password.isEmpty()) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " All fields are required.");
            System.out.println("--------------------------------------------------------------");
        }
        else {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " The role should be admin or user.");
            System.out.println("-----------------------------------------------------------------------");
        }
    }

    public void createBook(){
        String title, author, category;
        double price;
        long bookCode;
        int id;
        System.out.print("Enter the book Id: ");
        id = input.nextInt();
        input.nextLine();
        System.out.print("Enter the book title: ");
        title = input.nextLine().toLowerCase();
        System.out.print("Enter the book author: ");
        author = input.nextLine().toLowerCase();
        System.out.print("Enter the book category: ");
        category = input.nextLine().toLowerCase();
        System.out.print("Enter the book price: ");
        price = input.nextDouble();
        input.nextLine();
        System.out.print("Enter the book code: ");
        bookCode = input.nextLong();
        input.nextLine();
        if(title.isEmpty() || author.isEmpty() || category.isEmpty() || price < 0|| bookCode < 0){
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " All fields are required and (price/book code) should all be above zero");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            return;
        }
        Book newBook = new Book(id,title, author, category, price, bookCode);
        books.add(newBook);
        fileEdit.writeToFile(booksFile, newBook);

    }

    public void searchBook() {
        int id;
        String title, author, category;

        System.out.println("Search by: ");
        System.out.println("1- ID");
        System.out.println("2- Title");
        System.out.println("3- Category");
        System.out.println("4- Author");
        System.out.print("\033[1m" + "Enter the number of search method: " + "\033[0m");

        int choice = input.nextInt();
        input.nextLine(); // للتخلص من السطر الجديد المتبقي

        boolean isFound = false;

        switch (choice) {
            case 1:
                System.out.print("Enter the book Id: ");
                id = input.nextInt();
                input.nextLine();
                for (Book book : books) {
                    if (book.getId() == id) {
                        System.out.println("---------------------------------");
                        System.out.println(book.toString());
                        System.out.println("---------------------------------");
                        isFound = true;
                    }
                }
                if (!isFound) {
                    System.out.println("No book with id: " + id);
                }
                break;
            case 2:
                System.out.print("Enter the book title: ");
                title = input.nextLine();
                for (Book book : books) {
                    if (book.getTitle().equalsIgnoreCase(title)) {
                        System.out.println("---------------------------------");
                        System.out.println(book.toString());
                        System.out.println("---------------------------------");
                        isFound = true;
                    }
                }
                if (!isFound) {
                    System.out.println("No book with title: " + title);
                }
                break;
            case 3:
                System.out.print("Enter the book category: ");
                category = input.nextLine();
                for (Book book : books) {
                    if (book.getCategory().equalsIgnoreCase(category)) {
                        System.out.println("---------------------------------");
                        System.out.println(book.toString());
                        System.out.println("---------------------------------");
                        isFound = true;
                    }
                }
                if (!isFound) {
                    System.out.println("No book with category: " + category);
                }
                break;
            case 4:
                System.out.print("Enter the book author: ");
                author = input.nextLine();
                for (Book book : books) {
                    if (book.getAuthor().equalsIgnoreCase(author)) {
                        System.out.println("---------------------------------");
                        System.out.println(book.toString());
                        System.out.println("---------------------------------");
                        isFound = true;
                    }
                }
                if (!isFound) {
                    System.out.println("No book with author: " + author);
                }
                break;
            default:
                System.out.println("Invalid choice: " + choice);
                break;
        }
    }


    public void ordersList(){
        if (orders.isEmpty()) {
            System.out.println("--------------------------------------------------");
            System.out.println("There is No orders yet.");
            System.out.println("--------------------------------------------------");
        } else {
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order order = iterator.next();
                System.out.println("--------------------------------------------------");
                System.out.print(order.toString());
                System.out.println("--------------------------------------------------");
            }
        }
    }

    public void buyBook(){

        System.out.println("Enter the Book code of the book you want to buy: ");
        long bookCode = input.nextLong();
        input.nextLine();

        Iterator<Book> iterator = books.iterator();

        while (iterator.hasNext()){
            Book book = iterator.next();
            if(book.getBookCode() == bookCode){

                Order order = new Order(book.getId(),Pages.currentUsername, book.getTitle(), book.getBookCode());
                orders.add(order);
                fileEdit.writeToFile(ordersFile, order);

                System.out.println("---------------------------------------------------------------------");
                System.out.println("\u001B[42m" +"SUCCESS: " +"\u001B[0m" + book.getTitle() +" added to your cart successfully");
                System.out.println("---------------------------------------------------------------------");
                return;
            }
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("The is no book with book code" + bookCode);
        System.out.println("------------------------------------------------------------------------------------------------------------------");

    }

    public void cart(){
        boolean isEmpty = true;
        Iterator<Order> iterator = orders.iterator();

        while (iterator.hasNext()){
            Order order = iterator.next();
            if(order.getUserName().equals(Pages.currentUsername)){
                System.out.println("--------------------------------------");
                System.out.println(order.toString());
                System.out.println("--------------------------------------");
                isEmpty = false;
            }
        }

        if(isEmpty == true){
            System.out.println("------------------------------------------------------------------------------------------------------------------");
            System.out.println("Your cart is empty");
            System.out.println("------------------------------------------------------------------------------------------------------------------");
        }

    }

    public void showAllBooks(){
        if (books.isEmpty()) {
            System.out.println("--------------------------------------------------");
            System.out.println("No Books available.");
            System.out.println("--------------------------------------------------");
        } else {
            Iterator<Book> iterator = books.iterator();
            while (iterator.hasNext()) {
                Book book = iterator.next();
                System.out.println("--------------------------------------------------");
                System.out.print(book.toString());
                System.out.println("--------------------------------------------------");
            }
        }
    }

}
