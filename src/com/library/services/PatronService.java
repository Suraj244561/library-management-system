package com.library.services;

import com.library.models.Patron;
import java.util.ArrayList;
import java.util.List;

/**
 * PatronService Class - Handles all patron/member operations
 * 
 * Features:
 * - Register new patrons
 * - Search patrons by ID or name
 * - Update patron information
 * - Fine tracking
 * - Patron status management
 * 
 * Design Pattern: Service Layer
 */
public class PatronService {
    private List<Patron> patrons;

    public PatronService() {
        this.patrons = new ArrayList<>();
    }

    /**
     * Register a new patron in the library
     */
    public void registerPatron(Patron patron) {
        if (patron != null && findPatron(patron.getPatronId()) == null) {
            patrons.add(patron);
            System.out.println("✓ Patron registered: " + patron.getName());
        } else {
            System.out.println("✗ Patron already exists or invalid");
        }
    }

    /**
     * Find patron by ID
     */
    public Patron findPatron(String patronId) {
        for (Patron p : patrons) {
            if (p.getPatronId().equals(patronId)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Search patrons by name (partial match)
     */
    public List<Patron> searchByName(String name) {
        List<Patron> result = new ArrayList<>();
        for (Patron p : patrons) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    /**
     * Get all patrons
     */
    public List<Patron> getAllPatrons() {
        return new ArrayList<>(patrons);
    }

    /**
     * Get patrons with outstanding fines
     */
    public List<Patron> getPatronsWithFines() {
        List<Patron> result = new ArrayList<>();
        for (Patron p : patrons) {
            if (p.getFineAmount() > 0) {
                result.add(p);
            }
        }
        return result;
    }

    /**
     * Remove patron from system
     */
    public void removePatron(String patronId) {
        if (patrons.removeIf(p -> p.getPatronId().equals(patronId))) {
            System.out.println("✓ Patron removed");
        } else {
            System.out.println("✗ Patron not found");
        }
    }

    /**
     * Suspend patron account
     */
    public void suspendPatron(String patronId) {
        Patron patron = findPatron(patronId);
        if (patron != null) {
            patron.setStatus("SUSPENDED");
            System.out.println("✓ Patron suspended: " + patron.getName());
        }
    }

    /**
     * Activate patron account
     */
    public void activatePatron(String patronId) {
        Patron patron = findPatron(patronId);
        if (patron != null) {
            patron.setStatus("ACTIVE");
            System.out.println("✓ Patron activated: " + patron.getName());
        }
    }

    /**
     * Get total number of patrons
     */
    public int getTotalPatrons() {
        return patrons.size();
    }

    /**
     * Get active patrons count
     */
    public int getActivePatronsCount() {
        int count = 0;
        for (Patron p : patrons) {
            if ("ACTIVE".equals(p.getStatus())) {
                count++;
            }
        }
        return count;
    }
}
