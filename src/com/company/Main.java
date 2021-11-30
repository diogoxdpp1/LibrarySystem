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

    //ln 44

    private static File myFile = new File("Library.txt");

    public static void main(String[] args) throws IOException {
        createFile();
        MenuChoice();
        Scanner input = new Scanner(System.in);
        WriteToFile();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        switch (line){
            case "":
                System.out.println();
                break;
        }
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

    public static int MenuChoice() {
        Scanner input = new Scanner(System.in);// Finish Menu with search and finish search use Read Method.
        try {
            System.out.println("Welcome to the library, What would you like to do?" +
                    "1. Register a book" +
                    "2. Search for a book");
            int userchoice = input.nextInt();
            return userchoice;
        }catch (InputMismatchException e){
            System.out.println(e);
            return -1;
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

    public static void createFile(){
        try {
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
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

    public static void ReadFile(){
        try {
            Scanner myReader = new Scanner(myFile);
            System.out.println("Here are the Students with e or u in their name: ");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.contains("e")|| data.contains("u")){
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