package com.Gaurav.ReactiveApp.services.impl;

import com.Gaurav.ReactiveApp.entities.Book;
import com.Gaurav.ReactiveApp.repositories.BookRepository;
import com.Gaurav.ReactiveApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Mono<Book> create(Book book) {
        Mono<Book> createdBook = bookRepository.save(book);
        return createdBook;
    }

    @Override
    public Flux<Book> getAll() {

        return bookRepository
                .findAll()
                .delayElements(Duration.ofSeconds(1))
                .log()
                .map(book -> {
                    book.setName(book.getName().toUpperCase());
                    return book;
                });
    }

    @Override
    public Mono<Book> get(int bookId) {
        Mono<Book> item = bookRepository.findById(bookId);
        return item;
    }

    @Override
    public Mono<Book> update(Book book, int bookId) {
        Mono<Book> oldBook = bookRepository.findById(bookId);
        return oldBook.flatMap(PuraniBook ->
                {
                    PuraniBook.setName(book.getName());
                    PuraniBook.setDescription(book.getDescription());
                    PuraniBook.setAuthor(book.getAuthor());
                    PuraniBook.setPublisher(book.getPublisher());
                    return bookRepository.save(PuraniBook);
                });

    }

    @Override
    public Mono<Void> delete(int bookId) {
        // return bookRepository.deleteById(bookId);  //Common easy way to deleteById but below we use deleteByBook
        return bookRepository.findById(bookId).flatMap(book -> bookRepository.delete(book));
    }

    @Override
    public Flux<Book> search(String titleKeyword) {
        return this.bookRepository.searchBookByTitle("%"+titleKeyword+"%");

    }
}
