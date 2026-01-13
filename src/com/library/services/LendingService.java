package com.library.services;

import com.library.models.Book;
import com.library.models.Patron;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * LendingService Class - Handles lending operations
 * 
 * Features:
 * - Checkout books
 * - Return books
 * - Fine calculation
 * - Late fee tracking
 * 
 * Rules:
 * - Loan period: 14 days
 * - Fine: Rs. 10 per day (overdue)
 */
public class LendingService {
    private static final int LOAN_PERIOD_DAYS = 14;
    private static final double FINE_PER_DAY = 10.0;

    /**
     * Checkout a book for a patron
     */
    public boolean checkoutBook(Book book, Patron patron, LocalDate checkoutDate) {
        if (book.getAvailableCopies() <= 0) {
            System.out.println("✗ Book not available: " + book.getTitle());
            return false;
        }

        if (!"ACTIVE".equals(patron.getStatus())) {
            System.out.println("✗ Patron account is suspended");
            return false;
        }

        // Borrow the book
        book.borrowBook();
        patron.addBorrowedBook(book.getIsbn());
        patron.addToHistory(book.getIsbn());
        
        LocalDate dueDate = checkoutDate.plusDays(LOAN_PERIOD_DAYS);
        System.out.println("✓ Book checked out: " + book.getTitle());
        System.out.println("   Patron: " + patron.getName());
        System.out.println("   Due Date: " + dueDate);
        
        return true;
    }

    /**
     * Return a book and calculate fines if overdue
     */
    public void returnBook(Book book, Patron patron, LocalDate returnDate) {
        if (!patron.getBorrowedBooks().contains(book.getIsbn())) {
            System.out.println("✗ Patron has not borrowed this book");
            return;
        }

        // Calculate fine if overdue
        LocalDate dueDate = LocalDate.now().minusDays(LOAN_PERIOD_DAYS);
        double fine = calculateFine(returnDate);
        
        if (fine > 0) {
            patron.addFine(fine);
            System.out.println("⚠ Fine added: Rs. " + fine);
        }

        // Return the book
        book.returnBook();
        patron.removeBorrowedBook(book.getIsbn());
        System.out.println("✓ Book returned: " + book.getTitle());
        System.out.println("   By: " + patron.getName());
    }

    /**
     * Calculate fine based on return date
     */
    public double calculateFine(LocalDate returnDate) {
        LocalDate dueDate = LocalDate.now().minusDays(LOAN_PERIOD_DAYS);
        
        if (returnDate.isAfter(dueDate)) {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
            return daysOverdue * FINE_PER_DAY;
        }
        return 0;
    }

    /**
     * Get loan period
     */
    public int getLoanPeriodDays() {
        return LOAN_PERIOD_DAYS;
    }

    /**
     * Get fine per day
     */
    public double getFinePerDay() {
        return FINE_PER_DAY;
    }
}
