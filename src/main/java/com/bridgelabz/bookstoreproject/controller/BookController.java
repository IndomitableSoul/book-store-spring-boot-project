package com.bridgelabz.bookstoreproject.controller;

import com.bridgelabz.bookstoreproject.dto.BookDTO.BookDTO;
import com.bridgelabz.bookstoreproject.dto.ResponseDTO;
import com.bridgelabz.bookstoreproject.entity.Book;
import com.bridgelabz.bookstoreproject.service.bookService.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BookController {

    @Autowired
    private IBookService bookService;

    //addBook
    @PostMapping("/addBookToStock")
    public ResponseEntity<ResponseDTO> addBookToStock(@RequestBody BookDTO bookDTO){
        Book book = bookService.addBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added Book to stocks successfully", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    //getAllBooks
    @GetMapping("getBooks")
    public ResponseEntity<ResponseDTO> getAllBooks(){
        List<Book> allBooks = bookService.getAllBooks();
        ResponseDTO responseDTO = new ResponseDTO("All Books in stocks are", allBooks);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //getBookById
    @GetMapping("/getBook/{id}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable Long id){
        Book bookById = bookService.getBookById(id);
        ResponseDTO responseDTO = new ResponseDTO("Book with ID " +id+ "is: ", bookById);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //updateBookByID
    @PostMapping("/updateBook/{id}")
    public ResponseEntity<ResponseDTO> updateBookDetails(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        Book book = bookService.updateBookDetails(id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book with ID " +id+ "is updated successfully: ", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //removeBook
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ResponseDTO> removeBookFromStocks(@PathVariable Long id){
        Book book = bookService.removeBookFromStocks(id);
        ResponseDTO responseDTO = new ResponseDTO("Book with ID " +id+ "is deleted successfully: ", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
