package com.toto.bookshop.services;

import com.toto.bookshop.entities.Book;
import com.toto.bookshop.entities.User;
import com.toto.bookshop.entities.Publisher;
import com.toto.bookshop.repositories.BookRepository;
import com.toto.bookshop.repositories.UserRepository;
import com.toto.bookshop.dto.BookData;
import com.toto.bookshop.mapper.Mapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Service
public class BookService {

    private BookRepository bookRepository;

    private UserRepository userRepository;

    private Mapper<Book, BookData> bookMapper;
    
    @Autowired
    public void setBookRepository(BookRepository bookRepository) { this.bookRepository = bookRepository; }

    @Autowired
    public void setUserRepository(UserRepository userRepository) { this.userRepository = userRepository; }
    
    @Autowired
    public void setBookMapper(Mapper<Book, BookData> bookMapper) { this.bookMapper = bookMapper; }

    @Transactional
    public List<BookData> findAll() {
        List<Book> books = bookRepository.findAll();
        List<BookData> bookDatas = bookMapper.toDatas(books);
        /*
        List<BookData> bookDatas = new ArrayList<>();
        for (Book book : books) {
            BookData bookData = new BookData();
            bookData.setId(book.getId());
            bookData.setTitle(book.getTitle());
            bookData.setAuthors(book.getAuthors());
            bookData.setPages(book.getPages());
            bookData.setAmount(book.getAmount());
            bookData.setUserId(book.getPublisher().getUser().getId());
            bookDatas.add(bookData);
        }
        */
        return bookDatas;
    }


    @Transactional
    public void save(BookData bookData) {
        Book book = bookMapper.toEntity(bookData);
        /*
        Book book = new Book();
        book.setId(bookData.getId());
        book.setTitle(bookData.getTitle());
        book.setAuthors(bookData.getAuthors());
        book.setPages(bookData.getPages());
        book.setAmount(bookData.getAmount());
        User user = userRepository.getById(bookData.getUserId());
        book.setPublisher(user.getPublisher());
        */
        bookRepository.save(book);
    }

    @Transactional
    public BookData getById(Long id) {
        Book book = bookRepository.getById(id);
        BookData bookData = bookMapper.toData(book);
        /*
        BookData bookData = new BookData();
        bookData.setId(book.getId());
        bookData.setTitle(book.getTitle());
        bookData.setAuthors(book.getAuthors());
        bookData.setPages(book.getPages());
        bookData.setAmount(book.getAmount());
        bookData.setUserId(book.getPublisher().getUser().getId());
        */
        return bookData;
    }

    @Transactional
    public List<BookData> getByUser(Long userId) {
        User user = userRepository.getById(userId);
        List<Book> books = bookRepository.findByPublisher(user.getPublisher());
        List<BookData> bookDatas = bookMapper.toDatas(books);
        /*
        List<BookData> bookDatas = new ArrayList<>();
        for (Book book : books) {
            BookData bookData = new BookData();
            bookData.setId(book.getId());
            bookData.setTitle(book.getTitle());
            bookData.setAuthors(book.getAuthors());
            bookData.setPages(book.getPages());
            bookData.setAmount(book.getAmount());
            bookData.setUserId(book.getPublisher().getUser().getId());
            bookDatas.add(bookData);
        }
        */
        return bookDatas;
    }
    
    /*@Transactional
    public void delete(BookData bookData) {
        bookRepository.delete(bookMapper.toEntity(bookData));
    }*/

}