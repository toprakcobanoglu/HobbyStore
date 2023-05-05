package com.shop.HobbyStore.service.concretes;

import com.shop.HobbyStore.entities.model.Book;
import com.shop.HobbyStore.repository.BookRepository;
import com.shop.HobbyStore.service.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookManager implements BookService {
    private final BookRepository bookRepository;

    public BookManager(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
