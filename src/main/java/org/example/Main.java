package org.example;	
import java.util.HashMap;
import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner;
 
public class Main{	
    
    public static void main(String[] args) {
               
              DatabaseInitializer databaseInitializer = new DatabaseInitializer();	
              databaseInitializer.initializeDatabase();
 
 	          MyUserManager userManager = new MyUserManager();	

               
              HashMap <String, MyUserAction> actionMap = new HashMap<>();
              actionMap.put("register", new MyUserRegisterAction());
              actionMap.put("login", new MyUserLoginAction());
              actionMap.put("adminLogin", new MyAdminLoginAction());
              actionMap.put("adminPassword", new MyAdminPasswordAction());
              actionMap.put("adminResetPassword", new MyAdminResetPasswordAction()); 
              actionMap.put("adminListUser", new MyAdminListUserAction());
              actionMap.put("addUser", new MyUserAddAction());
              actionMap.put("addProduct", new MyProductAddAction());
              actionMap.put("updateProduct", new MyProductUpdateAction());

             Scanner scanner = new Scanner(System.in);
 	         String userInput;

              //DriverManager.getConnection(DB_URL)

 
             while (true) {	
 	             System.out.println("请输入你的指令，exit退出");	
                 userInput = scanner.nextLine();
                 if (actionMap.containsKey(userInput)) {
                    actionMap.get(userInput).action();
                } else if (userInput.equals( "exit")){
                    break;
                 }else{
                    System.out.println("未知操作");
                }
 	             
    }
    }
}