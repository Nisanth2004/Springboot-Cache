package com.nisanth.library.service;

import com.nisanth.library.entity.Book;
import com.nisanth.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        logger.info("Adding book with id - {}", book.getId());
        return bookRepository.save(book);
    }

    @Override
    @CachePut(cacheNames = "books", key = "#book.id")
    public Book updateBook(Book book) {
        logger.info("Updating book with id - {}", book.getId());
        bookRepository.updateAddress(book.getId(), book.getName());
        return book;
    }

    @Override
    @Cacheable(cacheNames = "books", key = "#id")
    public Book getBook(long id) {
        logger.info("Fetching book from DB for id - {}", id);
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    @CacheEvict(cacheNames = "books", key = "#id")
    public String deleteBook(long id) {
        logger.info("Deleting book with id - {}", id);
        bookRepository.deleteById(id);
        return "Book deleted successfully";
    }

    @Cacheable(cacheNames = "books")
    public List<Book> getAllBooks() {
        logger.info("Fetching all books from DB");
        return bookRepository.findAll();
    }
}
