package com.ne.starter.services;

import com.ne.starter.dtos.requests.CreateBookDto;
import com.ne.starter.models.Book;

import java.util.List;
import java.util.UUID;

public interface IBookService {
    List<Book> getBooks();
    Book getBookById(UUID id);
    Book addBook(CreateBookDto book);
    Book updateBook(UUID id, CreateBookDto book);
    void deleteBook(UUID id);
}
