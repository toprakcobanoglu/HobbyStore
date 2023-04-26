package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.Book;
import com.shop.HobbyStore.service.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public List<Book> findAllBooks()    {
        return bookService.findAllBooks();
    }
    @GetMapping("{id}")
    public Book findBookById(@PathVariable("id")int id) {
        return bookService.findBookById(id);
    }
    @PostMapping
    public Book saveBook(@RequestBody Book book)    {
        return bookService.saveBook(book);
    }
    @PutMapping("{id}")
    public Book updateBook(@RequestBody Book book)  {
        return bookService.saveBook(book);
    }
    @DeleteMapping("{id}")
    void deleteBook(@PathVariable("id")int id)  {
        bookService.deleteBook(id);
    }
}
