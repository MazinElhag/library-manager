package Library_Manager;

import java.io.*;
import java.util.Scanner;

public class User {
    private int id;
    private String username, password;
    private String role;

    public User(int id,String username, String password, String role){
        this.id= id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId(){
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole(){
        return role;
    }



    @Override
    public String toString() {
        return
                "id: " + id + "\n" +
                "User Name: " + username + '\n' +
                "password: " + password + '\n' +
                "role: " + role + "\n";
    }
}
