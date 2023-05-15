package com.shop.HobbyStore.controller;

import com.shop.HobbyStore.entities.model.Book;
import com.shop.HobbyStore.service.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView findAllBooks(Model model)   {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books" , books);
        return new ModelAndView("book-list");
    }

    @GetMapping("{id}")
    public Book findBookById(@PathVariable("id")int id) {
        return bookService.findBookById(id);
    }
    @PostMapping("/saveBook")
    public Book saveBook(@RequestBody Book book)    {
        return bookService.saveBook(book);
    }
    @PutMapping("/editBook/{bookId}")
    public Book updateBook(@PathVariable("bookId") int bookId, @RequestBody Book book) {
        Book existingBook = bookService.findBookById(bookId);
        if (existingBook != null) {
            existingBook.setName(book.getName());
            existingBook.setGenre(book.getGenre());
            existingBook.setReleaseDate(book.getReleaseDate());
            existingBook.setBasePrice(book.getBasePrice());
            existingBook.setAuthorName(book.getAuthorName());
            existingBook.setIsbnNumber(book.getIsbnNumber());

            return bookService.saveBook(existingBook);
        } else {
            throw new IllegalArgumentException("Book not found with id: " + bookId);
        }
    }

    @DeleteMapping("/deleteBook/{id}")
    void deleteBook(@PathVariable("id")int id)  {
        bookService.deleteBook(id);
    }
}
