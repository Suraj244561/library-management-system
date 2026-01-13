package com.library;

import com.library.models.Book;
import com.library.models.Patron;
import com.library.services.BookService;
import com.library.services.PatronService;
import com.library.services.LendingService;
import java.time.LocalDate;

/**
 * Main Application Class - Library Management System
 * 
 * This application demonstrates:
 * - Book management (add, search, list)
 * - Patron management (register, list)
 * - Lending operations (checkout, return, fines)
 * 
 * Features Implemented:
 * - Complete CRUD operations
 * - Fine calculation for overdue books
 * - Borrowing history tracking
 * - Patron account status management
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("\t📚 LIBRARY MANAGEMENT SYSTEM 📚");
        System.out.println("=".repeat(60));
        System.out.println();

        // ===== INITIALIZE SERVICES =====
        BookService bookService = new BookService();
        PatronService patronService = new PatronService();
        LendingService lendingService = new LendingService();

        // ===== 1. ADD BOOKS =====
        System.out.println("\n[1] ADDING BOOKS TO LIBRARY");
        System.out.println("-".repeat(60));
        
        Book book1 = new Book("ISBN001", "The Great Gatsby", "F. Scott Fitzgerald", "Scribner", 1925, 3);
        Book book2 = new Book("ISBN002", "To Kill a Mockingbird", "Harper Lee", "Lippincott", 1960, 2);
        Book book3 = new Book("ISBN003", "1984", "George Orwell", "Secker & Warburg", 1949, 4);
        Book book4 = new Book("ISBN004", "Pride and Prejudice", "Jane Austen", "T. Egerton", 1813, 2);

        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);

        // ===== 2. REGISTER PATRONS =====
        System.out.println("\n[2] REGISTERING LIBRARY MEMBERS");
        System.out.println("-".repeat(60));
        
        Patron patron1 = new Patron("P001", "Raj Kumar", "raj@email.com", "9876543210");
        Patron patron2 = new Patron("P002", "Priya Sharma", "priya@email.com", "9876543211");
        Patron patron3 = new Patron("P003", "Amit Patel", "amit@email.com", "9876543212");

        patronService.registerPatron(patron1);
        patronService.registerPatron(patron2);
        patronService.registerPatron(patron3);

        // ===== 3. SEARCH BOOKS =====
        System.out.println("\n[3] SEARCHING FOR BOOKS");
        System.out.println("-".repeat(60));
        
        Book searchResult = bookService.searchByTitle("1984");
        if (searchResult != null) {
            System.out.println("Found: " + searchResult);
        }

        // ===== 4. CHECKOUT BOOKS =====
        System.out.println("\n[4] CHECKOUT BOOKS FOR PATRONS");
        System.out.println("-".repeat(60));
        
        LocalDate today = LocalDate.now();
        
        lendingService.checkoutBook(book1, patron1, today);
        lendingService.checkoutBook(book2, patron1, today);
        lendingService.checkoutBook(book3, patron2, today);
        lendingService.checkoutBook(book4, patron3, today.minusDays(20)); // Checked out 20 days ago

        // ===== 5. DISPLAY PATRON INFORMATION =====
        System.out.println("\n[5] PATRON BORROWING STATUS");
        System.out.println("-".repeat(60));
        
        System.out.println(patron1);
        System.out.println("   Borrowed books: " + patron1.getBorrowedBooks());
        System.out.println("   Borrowing history: " + patron1.getBorrowingHistory());
        
        System.out.println("\n" + patron2);
        System.out.println("   Borrowed books: " + patron2.getBorrowedBooks());
        
        System.out.println("\n" + patron3);
        System.out.println("   Borrowed books: " + patron3.getBorrowedBooks());

        // ===== 6. RETURN BOOKS =====
        System.out.println("\n[6] RETURNING BOOKS");
        System.out.println("-".repeat(60));
        
        lendingService.returnBook(book1, patron1, today.plusDays(5)); // On time
        lendingService.returnBook(book2, patron1, today.plusDays(20)); // 6 days overdue
        lendingService.returnBook(book4, patron3, today); // 20 days overdue

        // ===== 7. DISPLAY FINE INFORMATION =====
        System.out.println("\n[7] FINE INFORMATION");
        System.out.println("-".repeat(60));
        
        System.out.println("Patron: " + patron1.getName());
        System.out.println("Total Fine: Rs. " + patron1.getFineAmount());
        
        System.out.println("\nPatron: " + patron3.getName());
        System.out.println("Total Fine: Rs. " + patron3.getFineAmount());

        // ===== 8. DISPLAY ALL BOOKS WITH AVAILABILITY =====
        System.out.println("\n[8] ALL BOOKS IN LIBRARY");
        System.out.println("-".repeat(60));
        
        for (Book b : bookService.getAllBooks()) {
            System.out.println(b);
        }
        
        System.out.println("\nTotal Books: " + bookService.getTotalBooks());
        System.out.println("Total Available Copies: " + bookService.getTotalAvailableCopies());

        // ===== 9. DISPLAY ALL PATRONS =====
        System.out.println("\n[9] ALL REGISTERED PATRONS");
        System.out.println("-".repeat(60));
        
        for (Patron p : patronService.getAllPatrons()) {
            System.out.println(p);
        }
        
        System.out.println("\nTotal Patrons: " + patronService.getTotalPatrons());
        System.out.println("Active Patrons: " + patronService.getActivePatronsCount());

        // ===== 10. SHOW PATRONS WITH OUTSTANDING FINES =====
        System.out.println("\n[10] PATRONS WITH OUTSTANDING FINES");
        System.out.println("-".repeat(60));
        
        for (Patron p : patronService.getPatronsWithFines()) {
            System.out.println(p.getName() + " - Fine: Rs. " + p.getFineAmount());
        }

        // ===== COMPLETION =====
        System.out.println("\n" + "=".repeat(60));
        System.out.println("\t✓ DEMO COMPLETED SUCCESSFULLY!");
        System.out.println("=".repeat(60));
        System.out.println("\nFeatures Demonstrated:");
        System.out.println("✓ Book Management (Add, Search, List)");
        System.out.println("✓ Patron Management (Register, List, Fine tracking)");
        System.out.println("✓ Lending Operations (Checkout, Return, Fines)");
        System.out.println("✓ History Tracking (Borrowing history)");
        System.out.println("✓ Account Management (Status, Suspension)");
        System.out.println();
    }
}
