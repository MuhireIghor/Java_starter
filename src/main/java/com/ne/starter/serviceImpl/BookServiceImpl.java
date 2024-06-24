package com.ne.starter.serviceImpl;

import com.ne.starter.dtos.requests.CreateBookDto;
import com.ne.starter.exceptions.NotFoundException;
import com.ne.starter.models.Book;
import com.ne.starter.repositories.IBookRepository;
import com.ne.starter.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements IBookService {
    private IBookRepository bookRepository;

    @Autowired
    public BookServiceImpl(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @Override
    public Book getBookById(UUID id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Book with id %s is not found", id)));
    }

    @Override
    public Book addBook(CreateBookDto book) {
        Book newBook = new Book();
        newBook.setAuthor(book.getAuthor());
        newBook.setName(book.getTitle());
        return bookRepository.save(newBook);
    }

    @Override
    public Book updateBook(UUID id, CreateBookDto book) {
        Book oldBook = getBookById(id);
        oldBook.setAuthor(book.getAuthor());
        oldBook.setName(book.getTitle());
        return bookRepository.save(oldBook);

    }

    @Override
    public void deleteBook(UUID id) {
        Book bookDelete = getBookById(id);
        bookRepository.delete(bookDelete);

    }
}
