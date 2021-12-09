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

    private static File myFile = new File("Library.txt");
    private static File myFile2 = new File("Username/Passwords.txt");

    public static void main(String[] args) throws IOException {
        createFile();
        createFile2();
        Loginsystem();
        MenuChoice();
        RegisterAnotherBook();
    }

    public static void Register(){
        System.out.println("");
    }

    public static void Loginsystem(){
        try {
            System.out.println("Would you like to Register or Log In?" + "\n" +
                    "1. Register" + "\n" +
                    "2 . Log In");
            int userchoice = 0;
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
                Register();
            } else if(userchoice == 2) {
                LogIn();
            }
        }catch (InputMismatchException e){

        }

    }

    public static void RegisterAnotherBook(){
            Scanner input = new Scanner(System.in);
            while(true){
                System.out.println("Do you wish to register another book? (Y/N)");
                String check = input.next();
                if(check.contains("Y")||check.contains("y")){
                    WriteToFile();
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
                WriteToFile();
            } else if(userchoice == 2) {
                ReadFile();
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

    public static String Search() throws IOException {
        System.out.println("Please input ISBN");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        return line;
    }

    public static void createFile(){
        try {
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void createFile2(){
        try {
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile2.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void WriteToFile() {
        try {
            FileWriter myWriter = new FileWriter(myFile.getName(), true); //True means append to file contents, False means overwrite
            myWriter.write(bookDetails() + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void ReadFile() throws IOException {
        try {
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains(Search())|| data.contains(Search())){
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