package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    // Setter method for BookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // A method to demonstrate functionality
    public void displayBooks() {
        // Assuming getBooks() returns a list of book titles
        bookRepository.getBooks().forEach(System.out::println);
    }
}
