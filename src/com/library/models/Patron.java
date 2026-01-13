package com.library.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Patron Model Class - Represents a library member
 * 
 * Features:
 * - Member details (ID, name, email, phone)
 * - Borrowing history tracking
 * - Fine management
 * - Account status (ACTIVE, SUSPENDED)
 */
public class Patron {
    private String patronId;
    private String name;
    private String email;
    private String phone;
    private List<String> borrowedBooks;
    private List<String> borrowingHistory;
    private double fineAmount;
    private String status;  // ACTIVE, SUSPENDED

    // Constructor
    public Patron(String patronId, String name, String email, String phone) {
        this.patronId = patronId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.borrowedBooks = new ArrayList<>();
        this.borrowingHistory = new ArrayList<>();
        this.fineAmount = 0.0;
        this.status = "ACTIVE";
    }

    // ===== GETTERS =====
    public String getPatronId() {
        return patronId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public List<String> getBorrowingHistory() {
        return new ArrayList<>(borrowingHistory);
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public String getStatus() {
        return status;
    }

    // ===== SETTERS & METHODS =====
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addBorrowedBook(String bookIsbn) {
        this.borrowedBooks.add(bookIsbn);
    }

    public void removeBorrowedBook(String bookIsbn) {
        this.borrowedBooks.remove(bookIsbn);
    }

    public void addToHistory(String bookIsbn) {
        this.borrowingHistory.add(bookIsbn);
    }

    public void addFine(double amount) {
        this.fineAmount += amount;
    }

    public void payFine(double amount) {
        if (amount <= fineAmount) {
            this.fineAmount -= amount;
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patron{" +
                "id='" + patronId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", borrowed=" + borrowedBooks.size() +
                ", fine=" + fineAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
