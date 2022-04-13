package com.bridgelabz.bookstoreproject.service.bookService;

import com.bridgelabz.bookstoreproject.dto.BookDTO.BookDTO;
import com.bridgelabz.bookstoreproject.entity.Book;

import java.util.List;

public interface IBookService {

    public Book addBook(BookDTO bookDTO);

    public List<Book> getAllBooks();

    public Book getBookById(Long id);

    public Book updateBookDetails(Long id, BookDTO bookDTO);

    public Book removeBookFromStocks(Long id);
}
