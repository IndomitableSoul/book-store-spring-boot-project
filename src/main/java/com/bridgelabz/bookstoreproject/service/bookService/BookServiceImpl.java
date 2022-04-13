package com.bridgelabz.bookstoreproject.service.bookService;

import com.bridgelabz.bookstoreproject.dto.BookDTO.BookDTO;
import com.bridgelabz.bookstoreproject.entity.Book;
import com.bridgelabz.bookstoreproject.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Book addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        bookRepository.save(book);
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> allBooks = bookRepository.findAll();
        return allBooks;
    }

    @Override
    public Book getBookById(Long id) {
        Book book = bookRepository.findById(id).get();
        return book;
    }

    @Override
    public Book updateBookDetails(Long id, BookDTO bookDTO) {
        Book bookById = this.getBookById(id);
        modelMapper.map(bookDTO, bookById);
        bookRepository.save(bookById);
        return bookById;
    }

    @Override
    public Book removeBookFromStocks(Long id) {
        Book book = this.getBookById(id);
        bookRepository.delete(book);
        return book;
    }
}
