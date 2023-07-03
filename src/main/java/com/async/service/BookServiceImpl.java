package com.async.service;

import com.async.entity.Book;
import com.async.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private BookRepository bookRepository;

    @Override
    public CompletableFuture<Book> addBook(Book book) {
        logger.info("adding book with id - {}", book.getId());
        return CompletableFuture.completedFuture(bookRepository.save(book));
    }

    @Override
    public CompletableFuture<Book> updateBook(Book book) {
        bookRepository.updateAddress(book.getId(), book.getName());
        logger.info("book updated with new name");
        return CompletableFuture.completedFuture(book);
    }

    @Override
    public CompletableFuture<Optional<Book>> getBook(long id) {
        logger.info("fetching book from db");
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            return CompletableFuture.completedFuture(book);
        }

        return CompletableFuture.completedFuture(Optional.of(new Book()));

    }


    @Override
    @Async("asyncExecution")
    public CompletableFuture<List<Book>> getAllBooks() {

        List<Book> books = null;

        try{

            System.out.println("Sleep method started");
            Thread.sleep(10000);
            System.out.println("Sleep method ended");

            logger.info("fetching book from db");
            books = bookRepository.findAll();

            books.forEach(book -> {
                System.out.println(book.getName());
            });

            return CompletableFuture.completedFuture(books);

        }catch (InterruptedException e){
            e.printStackTrace();
        }


        return CompletableFuture.completedFuture(books);
    }

    @Override
    public CompletableFuture<String> deleteBook(long id) {
        bookRepository.deleteById(id);
        return CompletableFuture.completedFuture("Book deleted");
    }

}
