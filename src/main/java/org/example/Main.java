package org.example;	
 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner;
 
public class Main{	
    
    public static void main(String[] args) {
              System.out.println("Hello world!");
 	
              DatabaseInitializer databaseInitializer = new DatabaseInitializer();	
              databaseInitializer.initializeDatabase();
 
 	          MyUserManager userManager = new MyUserManager();	
 
              Scanner scanner = new Scanner(System.in);
 
              List<MyAction> actionList = new ArrayList<MyAction>();
              
              MyHelpAction help = new MyHelpAction(scanner); 
              actionList.add(help);
              
              MyAboutAction about = new MyAboutAction(scanner);
 	          actionList.add(about);	
 
 	          MyUserRegisterAction userRegister = new MyUserRegisterAction(scanner,userManager);	
 	          actionList.add(userReqister);	
 
 	          MyUserLoginAction userLogin = new MyUserLoginAction(scanner,userManager):	
 	          actionList.add(userLogin);	
 
 	         String userInput = "";	
 
 	         while (true) {	
 	             System.out.println(x:"请输入你的指令，exit退出");	
                 System.out.print(s:"你当前在第一级目录下>"); 
                 userInput = scanner.nextLine();
 
 
 	             if (userInput.equals(an0bject:"exit")){