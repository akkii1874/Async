package com.async.service;

import com.async.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public interface BookService {

    CompletableFuture<Book> addBook(Book book);

    CompletableFuture<Book> updateBook(Book book);

    CompletableFuture<Optional<Book>> getBook(long id);

    CompletableFuture<List<Book>> getAllBooks();

    CompletableFuture<String> deleteBook(long id);
}
