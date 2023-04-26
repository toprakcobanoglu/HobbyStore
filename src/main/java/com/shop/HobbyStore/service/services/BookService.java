package com.shop.HobbyStore.service.services;

import com.shop.HobbyStore.entities.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();
    Book findBookById(int id);
    Book saveBook(Book book);
    Book updateBook(Book book);
    void deleteBook(int id);

}
