package Library_Manager;

import java.io.*;

import java.util.Scanner;
public class Pages{
    public static String currentUsername;
    static Scanner input = new Scanner(System.in);
    LibraryManager libraryManager = new LibraryManager();
    FileEdit fileEdit = new FileEdit();
    File usersFile = new File("C:\\Users\\maz\\IdeaProjects\\Library Manger\\src\\Library_Manager\\user.txt");

    public void login(){
        fileEdit.nothing();
        String username, password;

        System.out.println("\033[1m" + "Login form:--- "+ "\033[0m");
        System.out.print("Enter username: ");
        username = input.nextLine();
        System.out.print("Enter password: ");
        password = input.nextLine();

        if(username.isEmpty() || password.isEmpty()){
            System.out.println("--------------------------------------------------------------");
            System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " All fields are required.");
            System.out.println("--------------------------------------------------------------");
            return;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(usersFile))) {
            String line;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                String fileUsername = userData[1];
                String filePassword = userData[2];
                String fileRole = userData[3];
                if(fileUsername.equals(username) && filePassword.equals(password)){
                    currentUsername = fileUsername;
                    if (fileRole.equals("admin"))
                        dashboard(username);
                    else
                        home(username);
                }
                else {
                    System.out.println("----------------------------------------------------------------");
                    System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " Wrong password or username");
                    System.out.println("----------------------------------------------------------------");
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("No file");
            return;
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void dashboard(String username){
        int choice;
        do {
            System.out.println("---------------------------------------");
            System.out.println("\033[1m" + "Welcome back " + username + "\033[0m");
            System.out.println("---------------------------------------");
            System.out.println("1- Create new book");
            System.out.println("2- Search of book");
            System.out.println("3- Show all books");
            System.out.println("4- Register a new user");
            System.out.println("5- Orders list");
            System.out.println("7- End the program" + '\n');
            System.out.print("\033[1m" + "Enter the number of the operation: " + "\033[0m");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    libraryManager.createBook();
                    break;
                case 2:
                    libraryManager.searchBook();
                    break;
                case 3:
                    libraryManager.showAllBooks();
                    break;
                case 4:
                    libraryManager.registerUser();
                    break;
                case 5:
                    libraryManager.ordersList();
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("------------------------------------------------------------------------------------------------------------------");
                    System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " There is no operation with number "+ choice);
                    System.out.println("------------------------------------------------------------------------------------------------------------------");

                    break;
            }
        }while (true);
    }
    public void home(String username){
        int choice;
        do {
            System.out.println("---------------------------------------");
            System.out.println("\033[1m" + "Welcome back " + username + "\033[0m");
            System.out.println("---------------------------------------");
            System.out.println("1- Buy a book");
            System.out.println("2- My cart");
            System.out.println("3- Show all books");
            System.out.println("4- Search Of book");
            System.out.println("5- End program");
            System.out.println();
            System.out.print("\033[1m" + "Enter the number of the operation: " + "\033[0m");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    libraryManager.buyBook();
                    break;
                case 2:
                    libraryManager.cart();
                    break;
                case 3:
                    libraryManager.showAllBooks();
                    break;
                case 4:
                    libraryManager.searchBook();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("------------------------------------------------------------------------------------------------------------------");
                    System.out.println("\u001B[41m" +"ERROR:" +"\u001B[0m"+ " There is no operation with number "+ choice);
                    System.out.println("------------------------------------------------------------------------------------------------------------------");
                    break;
            }
        }while (true);
    }
}
