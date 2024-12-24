package Library_Manager;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LibraryManager {
    private ArrayList<User> users;
    private ArrayList<Book> books;
    private ArrayList<Order> orders;

    FileEdit fileEdit = new FileEdit();

    public static Scanner input = new Scanner(System.in);
    public LibraryManager(){
        users = new ArrayList<>();
        books = new ArrayList<>();
        orders = new ArrayList<>();
    }

    public void registerUser(){
        String username, password, role;
        System.out.print("Enter the username of the new user: ");
        username = input.nextLine();
        System.out.print("Enter the password for the new user: ");
        password = input.nextLine();
        System.out.print("Enter the role of the new user(admin/user): ");
        role = input.nextLine().toLowerCase();
        if((role.equals("admin")||role.equals("user")) && !username.isEmpty() && !password.isEmpty()){
            File file = new File("user.txt");

            if(fileEdit.isExist(file, username)){
                System.out.println("-------------------------------------------------------------");
                System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m" + " Username already exist");
                System.out.println("--------------------------------------------------------------");
                return;
            }

            User newUser = new User(username, password, role);
            users.add(newUser);
            fileEdit.writeToFile(file, newUser);
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
        File file = new File("books.txt");
        String title, author, category;
        double price;
        long bookCode;
        int stock;

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
        Book newBook = new Book(title, author, category, price, bookCode);
        books.add(newBook);
        fileEdit.writeToFile(file, newBook);

    }

    public void searchBook(){
        int id;
        String title, author, category;

        System.out.println("Search by: " + "\n");
        System.out.println("1- ID" + "\n" + "2- Title" + "\n" + "3- Category" + "\n" + "4- Author");
        System.out.print("\033[1m" + "Enter the number of search method: " + "\033[0m");

        Iterator<Book> iterator = books.iterator();
        boolean isFound = false;
        int choice = input.nextInt();

        switch (choice){
            case 1:
                System.out.print("Enter the book Id: ");
                id = input.nextInt();
                while (iterator.hasNext()){
                    Book book = iterator.next();
                    if(book.getId() == id){
                        System.out.println("---------------------------------");
                        System.out.println(book.toString());
                        System.out.println("---------------------------------");
                        isFound = true;
                    }
                }
                if (isFound == false){
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " The is no book with id: " + id);
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                }
                break;
            case 2:
                System.out.print("Enter the book title: ");
                title = input.nextLine();
                while (iterator.hasNext()){
                    Book book = iterator.next();
                    if(book.getTitle().equals(title)){
                        System.out.println("---------------------------------");
                        System.out.println(book.toString());
                        System.out.println("---------------------------------");
                        isFound = true;
                    }
                }
                if (isFound == false){
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " The is no book with title: " + title);
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                }
                break;
            case 3:
                System.out.print("Enter the book author: ");
                author = input.nextLine();
                while (iterator.hasNext()){
                    Book book = iterator.next();
                    if(book.getTitle().equals(author)){
                        System.out.println("---------------------------------");
                        System.out.println(book.toString());
                        System.out.println("---------------------------------");
                        isFound = true;
                    }
                }
                if(isFound == false) {
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " The is no book with title: " + author);
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                }
                break;
            case 4:
                System.out.print("Enter the book category: ");
                category = input.nextLine();
                while (iterator.hasNext()){
                    Book book = iterator.next();
                    if(book.getTitle().equals(category)){
                        System.out.println("---------------------------------");
                        System.out.println(book.toString());
                        System.out.println("---------------------------------");
                        isFound = true;
                    }
                }
                if (isFound == false){
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " The is no book with category: " + category);
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                }
                break;
            default:
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " The is no choice with number " + choice);
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                break;
        }
    }

    public void ordersList(){
        if (orders.isEmpty()) {
            System.out.println("--------------------------------------------------");
            System.out.println("No orders available.");
            System.out.println("--------------------------------------------------");
        } else {
            Iterator<Order> iterator = orders.iterator();
            while (iterator.hasNext()) {
                System.out.println("--------------------------------------------------");
                System.out.print(iterator.toString());
                System.out.println("--------------------------------------------------");
            }
        }
    }

    public void buyBook(){
        File file = new File("order.txt");

        System.out.println("Enter the Book code of the book you want to buy: ");
        long bookCode = input.nextLong();
        input.nextLine();

        Iterator<Book> iterator = books.iterator();

        while (iterator.hasNext()){
            Book book = iterator.next();
            if(book.getBookCode() == bookCode){

                Order order = new Order(User.currentUsername, book.getTitle(), book.getBookCode());
                orders.add(order);
                fileEdit.writeToFile(file, order);

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
            if(order.getUserName().equals(User.currentUsername)){
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
                System.out.println("--------------------------------------------------");
                System.out.print(iterator.toString());
                System.out.println("--------------------------------------------------");
            }
        }
    }

}
