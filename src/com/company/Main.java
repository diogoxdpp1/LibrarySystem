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

    public static void main(String[] args) {
        createFile(myFile);
        createFile(myFile2);
        LoginSystem();
        //MenuChoice();
        //RegisterAnotherBook();
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

    public static void LogIn(){

    }

    public static void LoginSystem(){
        try {
            System.out.println("Would you like to Register or Log In?" + "\n" +
                    "1. Register" + "\n" +
                    "2  Log In");
            int userchoice = input.nextInt();
            if(userchoice == 1){
                Register();
            } else if(userchoice == 2) {
                LogIn();
            }else{
                System.out.println("a");
            }
        }catch (InputMismatchException e){
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
                Search();
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
            return (bookName + "," + ISBN + "," + author + "," + genre);
        } catch (InputMismatchException e){
            System.out.print("Error: " + e);
            return "(Error)";
        }
    }

    public static void Search() throws IOException {
        System.out.println("Please input ISBN");
        Scanner scanner = new Scanner(System.in);
        String ISBN = scanner.next();
        ReadFile(ISBN);
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

    public static void ReadFile(String SearchPara) throws IOException {
        try {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains(SearchPara)|| data.contains(SearchPara)){
                    System.out.println(data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}