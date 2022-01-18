package com.company;

public class Book {
    protected String Name;
    protected int ISBN;
    protected String Author;
    protected String Genre;

    public Book(String name, int ISBN, String author, String genre) {
        Name = name;
        this.ISBN = ISBN;
        Author = author;
        Genre = genre;
    }

    @Override
    public String toString() {
        return "Book: " +
                "Name = '" + Name + '\'' +
                ", ISBN = " + ISBN +
                ", Author = '" + Author + '\'' +
                ", Genre = '" + Genre + '\'';
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }
}
