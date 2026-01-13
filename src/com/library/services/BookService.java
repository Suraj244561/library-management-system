package com.library.services;

import com.library.models.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * BookService Class - Handles all book-related operations
 * 
 * Features:
 * - Add/Remove books
 * - Search by title, ISBN, author
 * - Availability checking
 * - Book listing
 * 
 * Design Pattern: Service Layer
 */
public class BookService {
    private List<Book> books;

    public BookService() {
        this.books = new ArrayList<>();
    }

    /**
     * Add a new book to the library
     */
    public void addBook(Book book) {
        if (book != null && searchByIsbn(book.getIsbn()) == null) {
            books.add(book);
            System.out.println("✓ Book added: " + book.getTitle());
        } else {
            System.out.println("✗ Book already exists or invalid");
        }
    }

    /**
     * Search book by title (case-insensitive)
     */
    public Book searchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                return book;
            }
        }
        System.out.println("✗ Book not found: " + title);
        return null;
    }

    /**
     * Search book by ISBN
     */
    public Book searchByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Search book by author
     */
    public List<Book> searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    /**
     * Get all books in the library
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Check if book is available
     */
    public boolean isAvailable(String isbn) {
        Book book = searchByIsbn(isbn);
        return book != null && book.getAvailableCopies() > 0;
    }

    /**
     * Remove book from library
     */
    public void removeBook(String isbn) {
        if (books.removeIf(b -> b.getIsbn().equals(isbn))) {
            System.out.println("✓ Book removed");
        } else {
            System.out.println("✗ Book not found");
        }
    }

    /**
     * Get total number of books in library
     */
    public int getTotalBooks() {
        return books.size();
    }

    /**
     * Get count of available copies across all books
     */
    public int getTotalAvailableCopies() {
        int total = 0;
        for (Book book : books) {
            total += book.getAvailableCopies();
        }
        return total;
    }
}
