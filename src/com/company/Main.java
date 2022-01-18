package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static final File myFile = new File("Library.txt");
    private static final File myFile2 = new File("UsernamePasswords.txt");
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        boolean check = false;
                while(check){
                    if(LoginSystem()){
                        MenuChoice();
                    }else{
                        System.out.println("please try again");
                    }
                }
    }

    public static void Register(){
        boolean check = false;
        boolean isNumber = false;
        boolean isUpper = false;
        boolean allCorrect = false;
        String uName = "";
        String uNamenPass = "";
        Scanner input = new Scanner(System.in);
        while(!check) {
            try {
                System.out.println("Please input your username (has to have a number)");
                while(!isNumber) {
                    uName = input.next();
                    char[] userName = new char[uName.length()];

                    for (int i = 0; i < uName.length(); i++) {
                        userName[i] = uName.charAt(i);
                    }
                    for (int i = 0; i < userName.length; i++) {
                        if (Character.isDigit(userName[i])) {
                            isNumber = true;
                            check = true;
                        }
                    }
                    if(!isNumber){
                        System.out.println("Please have a number in ur username");
                    }
                }
                if (isNumber) {
                    System.out.println("Please input a password (must contain a Capital letter)");
                    while (!isUpper) {
                        String uPassword = input.next();
                        char[] userPassword = new char[uPassword.length()];
                        for (int i = 0; i < uPassword.length(); i++) {
                            userPassword[i] = uPassword.charAt(i);
                        }
                        for (int i = 0; i < userPassword.length; i++) {
                            if (Character.isUpperCase(userPassword[i])) {
                                isUpper = true;
                                check = true;
                            }
                        }
                        if (!isUpper) {
                            System.out.println("Please Have a capital letter in your password");
                        }
                        if (isNumber && isUpper) {
                            allCorrect = true;
                        }
                        if (allCorrect) {
                            uNamenPass = uName + "," + uPassword;
                            WriteToFile(uNamenPass, myFile2);
                        }
                        check = true;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println(e);
            }
        }
    }

    public static boolean LogIn() throws IOException {
        boolean uName = false;
        System.out.println("Please input your username");
        try {
            String userName = input.next();
            System.out.println("Please input your password.");
            String userPassword = input.next();
            String SearchPara = userName + "," + userPassword;
            if (ReadFile(SearchPara, myFile2)) {
                uName = true;
            }else{

                if (ReadFile(userName, myFile2)){
                    System.out.println("Your password does not match the username");
                }else{
                    System.out.println("We do not have that username on record.");
                }
            }
            if(uName == true){
                return true;
                MenuChoice();
            }else{
                return false;
            }
        }catch (InputMismatchException e){
            System.out.println(e);
        }
    }

    public static boolean LoginSystem(){
        try {
            System.out.println("Would you like to Register or Log In?" + "\n" +
                    "1. Register" + "\n" +
                    "2  Log In");
            int userchoice = input.nextInt();
            if(userchoice == 1){
                Register();
            } else if(userchoice == 2) {
                if(LogIn()){
                    return true;
                }else{
                    return false;
                }
            }else{
                System.out.println("Please only input 1 or 2");
                return false;
            }
        }catch (InputMismatchException | IOException e){
            return false;
            System.out.println("e");
        }

    }

    public static void RegisterAnotherBook(){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Do you wish to register another book? (Y/N)");
            String check = input.next();
            if(check.contains("Y")||check.contains("y")){
                WriteToFile(bookDetails() , myFile);
            }else if(check.contains("N")||check.contains("n")){
                break;
            }

        }
    }

    public static void MenuChoice() throws IOException {
        int userchoice = 0;
        try {
            System.out.println("Welcome to the library, What would you like to do?" + "\n" +
                    "1. Register a book" + "\n" +
                    "2. Search for a book");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line = br.readLine();
            switch (line){
                case "1":
                    userchoice = 1;
                    break;
                case "2":
                    userchoice = 2;
                    break;
            }
            if(userchoice == 1){
                WriteToFile(bookDetails() , myFile);
            } else if(userchoice == 2) {
                if(Search()){
                    System.out.println("This book is in the file");
                }else{
                    System.out.println("Book is not in the file");
                }
            }
        }catch (InputMismatchException e){
            System.out.println(e);
        }
    }

    public static String bookDetails(){
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Please write the book name: ");
            String bookName = input.next();
            System.out.println("Please write the ISBN: ");
            int ISBN = input.nextInt();
            System.out.println("Please write the Author: ");
            String author = input.next();
            System.out.println("Please write the genre: ");
            String genre = input.next();
            Book book = new Book(bookName, ISBN, author, genre);
            return book.toString();
        } catch (InputMismatchException e){
            System.out.print("Error: " + e);
            return null;
        }
    }

    public static boolean Search() throws IOException {
        System.out.println("Please input ISBN");
        Scanner scanner = new Scanner(System.in);
        String ISBN = scanner.next();
        if (ReadFile(ISBN, myFile)){
            return true;
        }else{
            return false;
        }
    }

    public static void createFile(File fileName){
        try {
            if (fileName.createNewFile()) {
                System.out.println("File created: " + fileName.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void WriteToFile(String informationToWrite, File fileToWriteTo) {
        try {
            FileWriter myWriter = new FileWriter(fileToWriteTo.getName(), true); //True means append to file contents, False means overwrite
            myWriter.write(informationToWrite + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static boolean ReadFile(String SearchPara, File fileToRead) throws IOException {
        boolean a = false;
        try {
            Scanner myReader = new Scanner(fileToRead);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains(SearchPara)|| data.contains(SearchPara)){
                    a = true;
                    return a;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(a);
        a = false;
        return a;
    }
}