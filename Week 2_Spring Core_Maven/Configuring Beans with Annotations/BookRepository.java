package com.library.repository;

import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;

@Repository
public class BookRepository {

    public List<String> getBooks() {
        // Example method to return a list of book titles
        return Arrays.asList("Book 1", "Book 2", "Book 3");
    }
}
