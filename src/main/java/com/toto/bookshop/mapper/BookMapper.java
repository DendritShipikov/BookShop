package com.toto.bookshop.mapper;

import com.toto.bookshop.entities.Book;
import com.toto.bookshop.entities.User;
import com.toto.bookshop.dto.BookData;
import com.toto.bookshop.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Mapper<Book, BookData> {

    private UserRepository userRepository;
    
    @Autowired
    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }
    
    @Override
    public Book toEntity(BookData bookData) {
        Book book = new Book();
        book.setId(bookData.getId());
        book.setTitle(bookData.getTitle());
        book.setAuthors(bookData.getAuthors());
        book.setPages(bookData.getPages());
        book.setAmount(bookData.getAmount());
        User user = userRepository.getById(bookData.getUserId());
        book.setPublisher(user.getPublisher());
        return book;
    }

    @Override
    public BookData toData(Book book) {
        BookData bookData = new BookData();
        bookData.setId(book.getId());
        bookData.setTitle(book.getTitle());
        bookData.setAuthors(book.getAuthors());
        bookData.setPages(book.getPages());
        bookData.setAmount(book.getAmount());
        bookData.setUserId(book.getPublisher().getUser().getId());
        return bookData;
    }

}