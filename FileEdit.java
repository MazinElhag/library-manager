package Library_Manager;
import java.io.*;
import java.util.*;
public class FileEdit {
// كتبت اغلب الكلاس بمساعدة chatgpt



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
            writer.write(book.getId()+","+book.getTitle()+","+book.getAuthor()+","+ book.getCategory()+","+book.getPrice()+","+ book.getBookCode()+","+"\n");
            System.out.println("---------------------------------------------------------------------");
            System.out.println("\u001B[42m" +"SUCCESS:" +"\u001B[0m" + "Book created successfully");
            System.out.println("---------------------------------------------------------------------");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void writeToFile(File file, Order order){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(order.getOrder_id()+","+order.getUserName()+","+ order.getBookTitle()+","+order.getBookCode()+","+ "\n");
        } catch (IOException e) {
            e.getMessage();
        }
    }



}
