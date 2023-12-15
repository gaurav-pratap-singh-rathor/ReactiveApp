package com.Gaurav.ReactiveApp.controllers;

import com.Gaurav.ReactiveApp.entities.Book;
import com.Gaurav.ReactiveApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookservice;

    @PostMapping
    public Mono<Book> create(@RequestBody Book book){
        return bookservice.create(book);
    }

    @GetMapping
    public Flux<Book> getall(){
        return bookservice.getAll();
    }

    @GetMapping("/{bid}")
    public Mono<Book> singlebook(@PathVariable int bid){
       return bookservice.get(bid);
    }

    @PutMapping("/{bid}")
    public Mono<Book> update(@RequestBody Book book ,@PathVariable int bid){
        return bookservice.update(book,bid);
    }

    @DeleteMapping("/{bid}")
    public Mono<Void> delete(@PathVariable int bid){
      return  bookservice.delete(bid);
    }

    @GetMapping("/search")
    public Flux<Book> getSearch(@RequestParam("query") String query){
        return this.bookservice.search(query);

    }

}
