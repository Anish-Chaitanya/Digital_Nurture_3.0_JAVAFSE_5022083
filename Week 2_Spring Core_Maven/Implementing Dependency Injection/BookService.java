package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    // Setter method for BookRepository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Business method that uses the BookRepository
    public void displayBooks() {
        bookRepository.getBooks().forEach(System.out::println);
    }
}
