package Library_Manager;
import java.io.*;
import java.util.*;
public class FileEdit {
// الله يخلي chatgpt بس



    public void nothing(){}
    public boolean isExist(File file, String username){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                if(userData[1].equals(username))
                    return true;
            }
        } catch (IOException e){
            e.getMessage();
        }

        return false;
    }
    public void writeToFile(File file, User user){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(user.getId()+","+ user.getUsername()+","+user.getPassword()+","+ user.getRole() + "\n");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("\u001B[42m" +"SUCCESS:" +"\u001B[0m" + " User registered successfully");
            System.out.println("---------------------------------------------------------------------");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void writeToFile(File file, Book book){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(book.getId()+","+book.getTitle()+","+book.getAuthor()+","+ book.getCategory()+","+book.getPrice()+","+ book.getBookCode()+"\n");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("\u001B[42m" +"SUCCESS:" +"\u001B[0m" + "Book created successfully");
            System.out.println("---------------------------------------------------------------------");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void writeToFile(File file, Order order){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(order.getOrder_id()+","+order.getUserName()+","+ order.getBookTitle()+","+order.getBookCode()+ "\n");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void readThenAddUsers(File file, ArrayList<User> users){
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line =scanner.nextLine().trim();
                if(line.isEmpty())
                    continue;
                String[] userData = scanner.nextLine().split(",");
                int id = Integer.parseInt(userData[0]);
                String username = userData[1];
                String password = userData[2];
                String role = userData[3];
                users.add(new User(id, username, password, role));
            }
            } catch (FileNotFoundException e) {
                e.getMessage();
            }

    }
    public void readThenAddBooks(File file, ArrayList<Book> books) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) { // التحقق من أن السطر غير فارغ
                    String[] parts = line.split(",");
                    if (parts.length == 6) { // التحقق من أن السطر مكتمل
                        int id = Integer.parseInt(parts[0]);
                        String title = parts[1];
                        String author = parts[2];
                        String category = parts[3];
                        double price = Double.parseDouble(parts[4]);
                        long bookCode = Integer.parseInt(parts[5]);
                        books.add(new Book(id, title, author, category, price, bookCode));
                    } else {
                        System.out.println("Invalid line format: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading books file: " + e.getMessage());
        }
    }


    public void readThenAddOrders(File file, ArrayList<Order> orders) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) { // التحقق من أن السطر غير فارغ
                    String[] parts = line.split(",");
                    if (parts.length == 4) { // التحقق من أن السطر مكتمل
                        int orderId = Integer.parseInt(parts[0]);
                        String username = parts[1];
                        String bookTitle = parts[2];
                        long bookCode = Long.parseLong(parts[3]);
                        orders.add(new Order(orderId, username, bookTitle, bookCode));
                    } else {
                        System.out.println("Invalid line format in orders file: " + line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading orders file: " + e.getMessage());
        }
    }


}
