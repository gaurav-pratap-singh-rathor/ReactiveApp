package com.Gaurav.ReactiveApp.repositories;

import com.Gaurav.ReactiveApp.entities.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book,Integer> {
    //1. Finder Method
        public Mono<Book> findByName(String name);

         Flux<Book> findByAuthor(String author);

         Flux<Book> findByNameAndAuthor(String name, String author);


    //2. Query Method

    @Query("select * from book_details where name  LIKE :title")
    Flux<Book> searchBookByTitle(String title);



    @Query("select * from book_details where name = :name AND author = :auth") // Here : author is named parameter , we get value from String auth parameter.
        Flux<Book> getAllBooksByNameAndAuthor(String name , @Param("auth") String author);   //Name is same but if auth and author are different then use @Param.



}
