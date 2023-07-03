package com.async.controller;

import com.async.entity.Book;
import com.async.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public CompletableFuture<Book> addBook(@RequestBody Book book){

        return bookService.addBook(book);
    }

    @PutMapping("/book")
    public CompletableFuture<Book> updateBook(@RequestBody Book book) {

        return bookService.updateBook(book);
    }

    @GetMapping("/book/{id}")
    public CompletableFuture<Optional<Book>> getBook(@PathVariable long id){

        CompletableFuture<Optional<Book>> book = bookService.getBook(id);

        return book;
    }

    @GetMapping("/books")
    public CompletableFuture<List<Book>> getAllBook(){

        CompletableFuture<List<Book>> books = bookService.getAllBooks();

        System.out.println("Books in controller "+books);

        return books;
    }

    @DeleteMapping("/book/{id}")
    public CompletableFuture<String> deleteBook(@PathVariable long id){

        return bookService.deleteBook(id);
    }

}
