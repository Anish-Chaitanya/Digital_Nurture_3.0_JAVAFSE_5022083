package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    // Setter for bookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Business methods can be added here
    public void displayBooks() {
        bookRepository.getBooks().forEach(System.out::println);
    }
}
