package com.bridgelabz.bookstoreproject.dto.BookDTO;

import lombok.Data;

@Data
public class BookDTO {

    private String bookName;
    private String author;
    private double price;
    private long quantity;

}
