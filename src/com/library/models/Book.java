package com.library.models;

/**
 * Book Model Class - Represents a book in the library
 * 
 * Features:
 * - Book details (ISBN, title, author, etc.)
 * - Availability tracking
 * - Status management (AVAILABLE, BORROWED, RESERVED)
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String status;        // AVAILABLE, BORROWED, RESERVED
    private int totalCopies;
    private int availableCopies;

    // Constructor
    public Book(String isbn, String title, String author, String publisher, int year, int totalCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.status = "AVAILABLE";
    }

    // ===== GETTERS =====
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    // ===== SETTERS & METHODS =====
    public void setStatus(String status) {
        this.status = status;
    }

    public void setAvailableCopies(int count) {
        this.availableCopies = count;
    }

    public void borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            if (availableCopies == 0) {
                status = "BORROWED";
            }
        }
    }

    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
            if (availableCopies > 0) {
                status = "AVAILABLE";
            }
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + availableCopies + "/" + totalCopies +
                ", status='" + status + '\'' +
                '}';
    }
}
