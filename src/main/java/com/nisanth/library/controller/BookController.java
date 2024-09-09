package com.nisanth.library.controller;


import com.nisanth.library.entity.Book;
import com.nisanth.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final EmbeddedCacheManager cacheManager;
    private final BookService bookService;

    int x=10;
    int y=19;

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable long id){
        return bookService.getBook(id);
        
    }
    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable long id){
        return bookService.deleteBook(id);
    }


    @GetMapping("/all-books")
    public List<Book> getAllBooks()
    {
        List<Book> books= bookService.getAllBooks();
        return books;
    }


}
