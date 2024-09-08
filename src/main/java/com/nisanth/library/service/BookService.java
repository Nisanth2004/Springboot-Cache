package com.nisanth.library.service;

import com.nisanth.library.entity.Book;

public interface BookService {

    Book addBook(Book book);

    Book updateBook(Book book);

    Book getBook(long id);

    String deleteBook(long id);
}
